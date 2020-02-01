/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.drivetrain.HolonomicDrivetrain;

@TeleOp(name="$HAWTY")
public class Teleop_Shawty extends OpMode {
    private FtcDashboard dashboard;
    private Telemetry telemetry;

    private RobotCore robot;
    private RobotConstants constants;

    private DcMotor Boom;
    private Servo Rotate;
    private Servo Claw;

    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        robot = new RobotCore(hardwareMap, HolonomicDrivetrain.class);
        robot.registerDefaults();

        Boom = hardwareMap.get(DcMotor.class, "Lift");
        Rotate = hardwareMap.get(Servo.class, "Rotate");
        Claw = hardwareMap.get(Servo.class, "Claw");

        Boom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Boom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Boom.setDirection(DcMotor.Direction.FORWARD);

        gamepad1.setJoystickDeadzone(0.02f);
    }

    @Override
    public void loop() {
        robot.gamepadDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_y);

        double signRSX = (Math.abs(gamepad1.right_stick_x) > 0.1) ? Math.signum(gamepad1.right_stick_x) : 0;
        double signRSY = (Math.abs(gamepad1.right_stick_y) > 0.1) ? Math.signum(gamepad1.right_stick_y) : 0;

        Boom.setPower(gamepad1.right_stick_y);
        Rotate.setPosition(Rotate.getPosition() + (signRSX * 0.05));


        telemetry.addData("LS:", "X[%.3f] Y[%.3f]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("RS:", "X[%.3f] Y[%.3f]", gamepad1.right_stick_x, gamepad1.right_stick_y);
        telemetry.update();
    }
}
