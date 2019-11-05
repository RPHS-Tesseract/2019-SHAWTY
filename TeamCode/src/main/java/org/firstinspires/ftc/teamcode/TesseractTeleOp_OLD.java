package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tesseract TeleOp")
public class TesseractTeleOp_OLD extends OpMode {
    private RobotCore robot = new RobotCore();
    private RobotUtilities utilities = new RobotUtilities();

    // Constants
    private final double DEADZONERADIUS = 0.08;
    private final double CONTROLEXPONENT = 2;

    // Code
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double[] ManipulatedDriveInput =  utilities.ManipulateRawControllerInput(gamepad1.left_stick_x, gamepad1.left_stick_y, DEADZONERADIUS, CONTROLEXPONENT);
        double[] ManipulatedRotInput = utilities.ManipulateRawControllerInput(gamepad1.right_stick_x, 0, DEADZONERADIUS, CONTROLEXPONENT);

        double DriveStickX = ManipulatedDriveInput[0];
        double DriveStickY = ManipulatedDriveInput[1];
        double RotStickX = ManipulatedRotInput[0];
        double RotStickY = ManipulatedRotInput[1];



        telemetry.addData("Drive Stick: ", "X[%d], Y[%d]", DriveStickX, DriveStickY);
        //telemetry.addData("Rotation Stick: ", "X: %d, Y: %d", RotStickX, RotStickY);

        double[] WheelPowerArray = utilities.getMotorPowerArrayFromMovementColumnVector(DriveStickY, DriveStickX);

        robot.setDrivePower(WheelPowerArray[0], WheelPowerArray[1], WheelPowerArray[1], WheelPowerArray[0]);
    }
}
