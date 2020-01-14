/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.translator.PlanularTranslator;

@TeleOp(name="$HAWTY")
public class PlanularTeleOp extends OpMode {
    private RobotCore robot = new RobotCore(PlanularTranslator.class);

    // TO CHANGE THESE GO TO THE CORRESPONDING TRANSLATOR.
    private final double DEADZONERADIUS = 0.08;
    private final double CONTROLEXPONENT = 2; // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Translator: ", "%s", robot.translator.getClass().getName());
        telemetry.update();
    }

    @Override
    public void loop() {
        robot.gamepadDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_stick_y);

        telemetry.addData("Left stick: ", "X[%f], Y[%f]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("Right stick: ", "X[%f], Y[%f]", gamepad1.right_stick_x, gamepad1.right_stick_y);
        telemetry.update();
    }
}
