/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutonomousConfig;
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
    private CRServo Rotate;
    private Servo Claw;
    private Servo ClampLeft;
    private Servo ClampRight;

    private double cycle = 0;
    private boolean ClawOpen = false;
    private boolean ClampDown = false;

    private double cooldown;

    @Override
    public void init() {


        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        robot = new RobotCore(hardwareMap, HolonomicDrivetrain.class);
        robot.registerDefaults();

        Boom = hardwareMap.get(DcMotor.class, "Lift");
        Rotate = hardwareMap.get(CRServo.class, "Rotate");
        Claw = hardwareMap.get(Servo.class, "Claw");
        ClampLeft = hardwareMap.get(Servo.class, "ClampLeft");
        ClampRight = hardwareMap.get(Servo.class, "ClampRight");

        Boom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Boom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Boom.setDirection(DcMotor.Direction.FORWARD);

        gamepad1.setJoystickDeadzone(0.02f);
    }

    @Override
    public void loop() {
        robot.gamepadDrive(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_y);

        Boom.setPower(gamepad1.right_stick_y);
        Rotate.setPower(Math.signum(gamepad1.right_stick_x) * 0.3);

        Claw.setPosition(ClawOpen ? 0.1 : 0.5);
        //ClampLeft.setPosition(ClampDown ? 0 : 0.3);
        //ClampRight.setPosition(ClampDown ? 0 : 0.3);

        if (gamepad1.a && getRuntime() - cooldown > .5) {
            ClawOpen = !ClawOpen;
            cooldown = getRuntime();
        }

        telemetry.addData("LS:", "X[%.3f] Y[%.3f]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("RS:", "X[%.3f] Y[%.3f]", gamepad1.right_stick_x, gamepad1.right_stick_y);
        telemetry.update();
    }
}
