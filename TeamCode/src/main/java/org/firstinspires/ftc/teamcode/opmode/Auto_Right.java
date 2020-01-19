package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.drivetrain.NullDrivetrain;

@Autonomous(name = "Auto_Right")
public class Auto_Right extends LinearOpMode {
    private RobotCore robot;
    private ElapsedTime timer;

    @Override
    public void runOpMode() {
        robot = new RobotCore(hardwareMap, NullDrivetrain.class);
        timer = robot.timer;

        waitForStart();
        timer.reset();

        while (timer.seconds() <= 1.5) {
            robot.setDrivePower(1, 1, -1, -1);
        }

        while (timer.seconds() <=  3) {
            robot.setDrivePower(1, -1, 1, -1);
        }
    }
}
