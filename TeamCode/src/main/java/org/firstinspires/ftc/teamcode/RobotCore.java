/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;

import java.lang.reflect.InvocationTargetException;

public class RobotCore {
    private static DcMotor frontLeft;
    private static DcMotor frontRight;
    private static DcMotor rearLeft;
    private static DcMotor rearRight;

    public static Drivetrain drivetrain;
    public ElapsedTime runtime = new ElapsedTime();

    // TODO Allow for defining Translator constants at runtime
    public RobotCore(Class<? extends Drivetrain> t, Object... args) {
        try {
            drivetrain = t.getConstructor(Object.class).newInstance(args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

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
        double[] powerArray = drivetrain.gamepadTranslate(LX, LY, RX, RY);
        setDrivePower(powerArray[0], powerArray[1], powerArray[2], powerArray[3]);
    }

    public void vectorDrive(double lon, double lat, double yaw) {
        double[] powerArray = drivetrain.vectorTranslate(lon, lat, yaw);
        setDrivePower(powerArray[0], powerArray[1], powerArray[2], powerArray[3]);
    }

    private void setDrivePower(double FrontLeftPower, double FrontRightPower, double RearLeftPower, double RearRightPower) {
        frontLeft.setPower(FrontLeftPower);
        frontRight.setPower(FrontRightPower);
        rearLeft.setPower(RearLeftPower);
        rearRight.setPower(RearRightPower);
    }
}
