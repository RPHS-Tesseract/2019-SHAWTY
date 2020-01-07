/**
 * Jacob Bazata
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.translator.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.crypto.NullCipher;

public class RobotCore {
    private static DcMotor frontLeft;
    private static DcMotor frontRight;
    private static DcMotor rearLeft;
    private static DcMotor rearRight;
    private static Translator translator = new Raw();

    public ElapsedTime runtime = new ElapsedTime();

    public RobotCore() {}

    public void init(HardwareMap map) {
        // Map physical motors to variables
        frontLeft = map.get(DcMotor.class, "FrontLeft");
        frontRight = map.get(DcMotor.class, "FrontRight");
        rearLeft = map.get(DcMotor.class, "RearLeft");
        rearRight = map.get(DcMotor.class, "RearRight");

        // Set motor RunMode
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set motor Direction
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);

        // Set motor ZeroPowerBehavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void gamepadDrive(double LX, double LY, double RX, double RY) {
        double[] powerArray = translator.gamepadTranslate(LX, LY, RX, RY);
        setDrivePower(powerArray[1], powerArray[2], powerArray[3], powerArray[4]);
    }

    public void vectorDrive(double lon, double lat, double yaw) {
        double[] powerArray = translator.vectorTranslate(lon, lat, yaw);
        setDrivePower(powerArray[1], powerArray[2], powerArray[3], powerArray[4]);
    }

    public void setTranslator(Class<? extends Translator> t /*, ArrayList options*/) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        translator = t.getConstructor().newInstance();
    }

    private void setDrivePower(double FrontLeftPower, double FrontRightPower, double RearLeftPower, double RearRightPower) {
        frontLeft.setPower(FrontLeftPower);
        frontRight.setPower(FrontRightPower);
        rearLeft.setPower(RearLeftPower);
        rearRight.setPower(RearRightPower);
    }
}
