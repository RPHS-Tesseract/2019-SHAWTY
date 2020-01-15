package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name="Crude")
public class Crude extends OpMode {
    private static DcMotor frontLeft;
    private static DcMotor frontRight;
    private static DcMotor rearLeft;
    private static DcMotor rearRight;
    private static DcMotor lift;
    private final double deadzone = 0.05;


    @Override
    public void init() {
        // Map physical motors to variables
        frontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "RearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "RearRight");
        lift = hardwareMap.get(DcMotor.class, "lift");

        // Set motor RunMode
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set motor Direction
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.REVERSE);
        lift.setDirection(DcMotor.Direction.FORWARD);

        // Set motor ZeroPowerBehavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double FL, double FR, double RL, double RR, double LP) {
        frontLeft.setPower(FL);
        frontRight.setPower(FR);
        rearLeft.setPower(RL);
        rearRight.setPower(RR);
        lift.setPower(LP);
    }

    @Override
    public void loop() {
        double longitinudal = 0;
        double lat = 0;
        double yaw = 0;
        double liftPower = 0;
        boolean skip = false;

        setPower(0,0,0,0, 0);

        if (gamepad1.a) {
            liftPower = .5;
            setPower(0,0,0,0, liftPower);
        } else if (gamepad1.b) {
            liftPower = -.5;
            setPower(0,0,0,0, liftPower);
        }

        if (Math.abs(gamepad1.left_stick_y) > deadzone && !skip) {
            skip = true;
            longitinudal= gamepad1.left_stick_y;
            setPower(longitinudal, longitinudal, -longitinudal, -longitinudal, liftPower);
        }

        if (Math.abs(gamepad1.left_trigger - gamepad1.right_trigger) > deadzone && !skip) {
            skip = true;
            lat = gamepad1.left_trigger - gamepad1.right_trigger;
            //setPower(lat, -lat, -lat, lat, liftPower);
            setPower(-lat, lat, -lat, lat, liftPower);
        }

        if (Math.abs(gamepad1.right_stick_x) > deadzone && !skip) {
            skip = true;
            yaw = gamepad1.right_stick_x;
            //setPower(yaw, -yaw, yaw, -yaw, liftPower);
            setPower(-yaw, yaw, yaw, -yaw, liftPower);
        }

            if (gamepad1.left_stick_button && gamepad1.y ) {
            setPower(0,0,0,0,0);
            System.exit(0);
        }
    }
}
