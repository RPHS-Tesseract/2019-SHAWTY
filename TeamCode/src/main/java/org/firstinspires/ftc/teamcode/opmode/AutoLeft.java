package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto_Left")
public class AutoLeft extends LinearOpMode {
    private static DcMotor frontLeft;
    private static DcMotor frontRight;
    private static DcMotor rearLeft;
    private static DcMotor rearRight;

    private static ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        // Map physical motors to variables
        frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "RearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "RearRight");

        // Set motor RunMode
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set motor Direction
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        // Set motor ZeroPowerBehavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        timer.reset();

        while (timer.seconds() <= 1.5) {
            frontLeft.setPower(1);
            frontRight.setPower(1);
            rearLeft.setPower(-1);
            rearRight.setPower(-1);
        }

        while (timer.seconds() <=  3) {
            frontLeft.setPower(-1);
            frontRight.setPower(1);
            rearLeft.setPower(-1);
            rearRight.setPower(1);
        }
    }
}
