/**
 * Jacob Bazata
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCore;

@TeleOp(name="Tesseract_Planular")
public class Tesseract_Planular extends OpMode {
    private RobotCore robot = new RobotCore();

    private final double DEADZONERADIUS = 0.08;
    private final double CONTROLEXPONENT = 2;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {


        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        telemetry.addData("Left stick: ", "X[%d], Y[%d]", gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("Right stick: ", "X[%d], Y[%d]", gamepad1.right_stick_x, gamepad1.right_stick_y);
    }
}
