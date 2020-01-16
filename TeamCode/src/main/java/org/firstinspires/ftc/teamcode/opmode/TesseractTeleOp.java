/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.drivetrain.PlanarDrivetrain;

@TeleOp(name="$HAWTY")
public class TesseractTeleOp extends OpMode {
    private RobotCore robot;

    @Override
    public void init() {
        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        robot = new RobotCore(hardwareMap, PlanarDrivetrain.class, 0.02, 2.0); // (Drivetrain, DeadzoneRadius, ControlExponent)
        robot.registerDefaults();
        telemetry.addData("Drivetrain: ", "%s", robot.drivetrain.getClass().getSimpleName());
        telemetry.update();
    }

    @Override
    public void loop() {
        robot.gamepadDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_stick_y);

        telemetry.addData("Left stick: ", "X[%.3f], Y[%.3f]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("Right stick: ", "X[%.3f], Y[%.3f]", gamepad1.right_stick_x, gamepad1.right_stick_y);
        telemetry.update();
    }
}
