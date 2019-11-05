/**
 * Jacob Bazata
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.translator;

import com.qualcomm.robotcore.util.Range;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Planular implements Translator {
    private final RealMatrix INVERSEMATRIX = MatrixUtils.createRealMatrix(new double[][] {{1, 1}, {1, -1}, {1, -1}, {1,1}});
    /* [ 1  1 ]
       [ 1 -1 ]
       [ 1 -1 ]
       [ 1  1 ] */

    @Override
    public double[] fromVector(RealMatrix VelocityColumn) {
        RealVector product = INVERSEMATRIX.multiply(VelocityColumn).getColumnVector(1);
        return new double[] {
                product.getEntry(1),
                product.getEntry(2),
                product.getEntry(3),
                product.getEntry(4),
        };
    }

    public double[] fromVector(double longitudinal, double lateral) {
        return fromVector(MatrixUtils.createColumnRealMatrix(new double[] {longitudinal, lateral}));
    }

    @Override
    public double[] manipulateRaw(double rawLeftX, double rawLeftY, double rawRightX, double rawRightY, double[] options) {
        double LeftX = rawLeftX;
        double LeftY = rawLeftY;
        double RightX = rawRightX;
        double RightY = rawRightY;

        double deadzoneRadius = options[1];
        double exponent = options[2];

        // Sanitize input
        LeftX = Range.clip(LeftX, -1, 1);
        LeftY = Range.clip(LeftY, -1, 1);
        RightX = Range.clip(RightX, -1, 1);
        RightY = Range.clip(RightY, -1, 1);

        // Get stick distance from center
        double rL = Math.sqrt((LeftX * LeftX) + (LeftY * LeftY));
        double rR = Math.sqrt((RightX * RightX) + (RightY * RightX));

        // Check if stick is within radial dead zone. If not, 0 its values.
        LeftX = (rL > deadzoneRadius) ? LeftX : 0;
        LeftY = (rL > deadzoneRadius) ? LeftY : 0;
        RightX = (rR > deadzoneRadius) ? RightX : 0;
        RightY = (rR > deadzoneRadius) ? RightY : 0;

        // Apply logarithmic control scale
        LeftX = Math.pow(LeftX, exponent);
        LeftY = Math.pow(LeftY, exponent);
        RightX = Math.pow(RightX, exponent);
        RightY = Math.pow(RightY, exponent);

        // Round to 3 decimal places (Do not remove the decimal, it will cause floating point imprecision)
        LeftX = Math.round(LeftX * 1000)/1000.0;
        LeftY = Math.round(LeftY * 1000)/1000.0;
        RightX = Math.round(RightX * 1000)/1000.0;
        RightY = Math.round(RightY * 1000)/1000.0;

        // Give yaw priority.
        LeftX = rR == 0 ? LeftX : 0;
        LeftY = rR == 0 ? LeftY : 0;

        return new double[]{LeftX, LeftY, RightX, RightY};
    }
}
