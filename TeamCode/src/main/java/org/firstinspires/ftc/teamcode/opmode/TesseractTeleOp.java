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
    // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
    private RobotCore robot = new RobotCore(hardwareMap, PlanarDrivetrain.class, 0.02, 2); // (Drivetrain, DeadzoneRadius, ControlExponent)

    @Override
    public void init() {
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
