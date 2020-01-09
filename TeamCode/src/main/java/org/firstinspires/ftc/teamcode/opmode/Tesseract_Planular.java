/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.translator.Planular;

@TeleOp(name="Planular")
public class Tesseract_Planular extends OpMode {
    private RobotCore robot = new RobotCore(Planular.class);

    // TO CHANGE THESE GO TO THE CORRESPONDING TRANSLATOR.
    private final double DEADZONERADIUS = 0.08; // DO NOT CHANGE
    private final double CONTROLEXPONENT = 2; // DO NOT CHANGE

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.gamepadDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_stick_y);

        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        telemetry.addData("Left stick: ", "X[%d], Y[%d]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("Right stick: ", "X[%d], Y[%d]", gamepad1.right_stick_x, gamepad1.right_stick_y);
    }
}
