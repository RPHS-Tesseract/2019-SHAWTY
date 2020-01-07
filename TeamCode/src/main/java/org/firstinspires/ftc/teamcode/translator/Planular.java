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

    private double deadzoneRadius = 0.08;
    private double exponent = 2;

    public Planular() {

    }

    /*public Planular(double dR, double exp) { // Takes two args, deadzoneRadius and exponent
        deadzoneRadius = dR;
        exponent = exp;
    }*/

    @Override
    public double[] vectorTranslate(double longitudinal, double lateral, double yaw) {
        if (yaw == 0) { // Linear
            RealMatrix VelocityColumn = MatrixUtils.createColumnRealMatrix(new double[] {longitudinal, lateral});
            RealVector product = INVERSEMATRIX.multiply(VelocityColumn).getColumnVector(1);
            return new double[] {
                    product.getEntry(1),
                    product.getEntry(2),
                    product.getEntry(3),
                    product.getEntry(4),
            };
        } else { // Non-linear
            return new double[] { -yaw, yaw, -yaw, yaw };
        }
    }

    @Override
    public double[] gamepadTranslate(double rawLeftX, double rawLeftY, double rawRightX, double rawRightY) {
        double LeftX = rawLeftX;
        double LeftY = rawLeftY;
        double RightX = rawRightX;
        double RightY = rawRightY;

        double radiusL;
        double radiusR;

        //double deadzoneRadius = options[1];
        //double exponent = options[2];

        // Clip input
        LeftX = Range.clip(LeftX, -1, 1);
        LeftY = Range.clip(LeftY, -1, 1);
        RightX = Range.clip(RightX, -1, 1);
        RightY = Range.clip(RightY, -1, 1);

        // Get stick distance from center
        radiusL = Math.sqrt((LeftX * LeftX) + (LeftY * LeftY)); // Radius: left stick
        radiusR = Math.sqrt((RightX * RightX) + (RightY * RightX)); // Radius: right stick

        // Check if stick is within radial dead zone. If not, 0 its values.
        LeftX = (radiusL > deadzoneRadius) ? LeftX : 0;
        LeftY = (radiusL > deadzoneRadius) ? LeftY : 0;
        RightX = (radiusR > deadzoneRadius) ? RightX : 0;
        RightY = (radiusR > deadzoneRadius) ? RightY : 0;

        // Recalculate radius
        /*radiusL = Math.sqrt((LeftX * LeftX) + (LeftY * LeftY)); // Radius: left stick
        radiusR = Math.sqrt((RightX * RightX) + (RightY * RightX)); // Radius: right stick*/

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
        /*LeftX = (radiusR == 0) ? LeftX : 0;
        LeftY = (radiusR == 0) ? LeftY : 0;*/

        return vectorTranslate(LeftY, LeftX, RightX);
        //return new double[]{LeftX, LeftY, RightX, RightY};
    }
}
