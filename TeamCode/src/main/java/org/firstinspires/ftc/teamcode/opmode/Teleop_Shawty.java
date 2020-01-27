/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConstants;
import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.drivetrain.HolonomicDrivetrain;

import java.util.Locale;

@TeleOp(name="$HAWTY")
public class Teleop_Shawty extends OpMode {
    private FtcDashboard dashboard = FtcDashboard.getInstance();

    private RobotCore robot;
    private RobotConstants constants;

    @Override
    public void init() {
        // Visualize scale: https://www.desmos.com/calculator/0xtjtrmqfk
        robot = new RobotCore(hardwareMap, HolonomicDrivetrain.class);

        robot.registerDefaults();

        gamepad1.setJoystickDeadzone(constants.JoystickDeadzone);

        TelemetryPacket packet = new TelemetryPacket();
        packet.put("Drivetrain", robot.drivetrain.getClass().getSimpleName());
        dashboard.sendTelemetryPacket(packet);
    }

    @Override
    public void loop() {
        robot.gamepadDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_y);

        TelemetryPacket packet = new TelemetryPacket();
        packet.put("LS: ", String.format(Locale.US, "X[%.3f] Y[%.3f]", gamepad1.left_stick_x, gamepad1.left_stick_y));
        packet.put("RS: ", String.format(Locale.US, "X[%.3f] Y[%.3f]", gamepad1.right_stick_x, gamepad1.right_stick_y));
        dashboard.sendTelemetryPacket(packet);
    }
}
