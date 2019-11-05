/**
 * Jacob Bazata
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotCore {
    private static DcMotor frontLeft;
    private static DcMotor frontRight;
    private static DcMotor rearLeft;
    private static DcMotor rearRight;

    public ElapsedTime runtime = new ElapsedTime();

    public RobotCore() {}

    public void init(HardwareMap map) {

        // Map physical motors to variables
        frontLeft = map.get(DcMotor.class, "FrontLeft");
        frontRight = map.get(DcMotor.class, "FrontRight");
        rearLeft = map.get(DcMotor.class, "RearLeft");
        rearRight = map.get(DcMotor.class, "RearRight");

        // Set motor RunMode
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set motor Direction
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);

        // Set motor ZeroPowerBehavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    public void setTranslator() {

    }

    public void setDrivePower(double FrontLeftP, double FrontRightP, double RearLeftP, double RearRightP) {
        frontLeft.setPower(FrontLeftP);
        frontRight.setPower(FrontRightP);
        rearLeft.setPower(RearLeftP);
        rearRight.setPower(RearRightP);
    }
}
