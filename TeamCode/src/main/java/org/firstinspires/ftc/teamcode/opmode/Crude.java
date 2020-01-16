package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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

    @Override
    public void loop() {
        double FL = 0;
        double FR = 0;
        double RL = 0;
        double RR = 0;
        double LP = 0;

        LP = gamepad1.a ? 0.5 : gamepad1.b ? -0.5 : 0;

        if (Math.abs(gamepad1.left_stick_y) > deadzone) {
            FL = gamepad1.left_stick_y;
            FR = gamepad1.left_stick_y;
            RL = -gamepad1.left_stick_y;
            RR = -gamepad1.left_stick_y;
        } else if (Math.abs(gamepad1.left_trigger - gamepad1.right_trigger) > deadzone) {
            double lat = gamepad1.left_trigger - gamepad1.right_trigger;
            FL = -lat;
            FR = lat;
            RL = -lat;
            RR = lat;
        } else if (Math.abs(gamepad1.right_stick_x) > deadzone) {
            FL = -gamepad1.right_stick_x;
            FR = gamepad1.right_stick_x;
            RL = gamepad1.right_stick_x;
            RR = -gamepad1.right_stick_x;
        }

        if (gamepad1.left_stick_button && gamepad1.y ) {
            FL = 0;
            FR = 0;
            RL = 0;
            RR = 0;
            LP = 0;
            System.exit(-1);
        }

        frontLeft.setPower(FL);
        frontRight.setPower(FR);
        rearLeft.setPower(RL);
        rearRight.setPower(RR);
        lift.setPower(LP);
    }
}
