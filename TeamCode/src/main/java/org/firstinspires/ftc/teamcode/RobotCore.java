/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class RobotCore {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;

    private HardwareMap map;
    private HashMap<String, HardwareDevice> registrar;

    public Drivetrain drivetrain;
    public ElapsedTime timer;

    public RobotCore(HardwareMap hwm, Class<? extends Drivetrain> t) {
        map = hwm;
        try {
            drivetrain = t.getConstructor().newInstance();
            timer = new ElapsedTime();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e); // Propagate error wrapped in RuntimeException
        }

    }

    // Register default drive motors
    public void registerDefaults() {
        // Map physical motors to variables
        frontLeft = map.get(DcMotor.class, "FrontLeft");
        frontRight = map.get(DcMotor.class, "FrontRight");
        rearLeft = map.get(DcMotor.class, "RearLeft");
        rearRight = map.get(DcMotor.class, "RearRight");

        // Set motor RunMode

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor Direction
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        // Set motor ZeroPowerBehavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //registrar.put("frontLeft", frontLeft);
        //registrar.put("frontRight", frontRight);
        //registrar.put("rearLeft", rearLeft);
        //registrar.put("rearRight", rearRight);
    }

    /*public void registerDCMotor(Class<? extends DcMotor> type, String deviceName, DcMotor.Direction direction) {
        DcMotor newMotor = map.get(type, deviceName);
        newMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newMotor.setDirection(direction);
        newMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        registrar.put(deviceName, newMotor);
    }*/

    public void gamepadDrive(double LX, double LY, double RX, double RY) {
        double[] powerArray = drivetrain.gamepadTranslate(LX, LY, RX, RY);
        setDrivePower(powerArray[0], powerArray[1], powerArray[2], powerArray[3]);
    }

    public void vectorDrive(double lon, double lat, double yaw) {
        double[] powerArray = drivetrain.vectorTranslate(lon, lat, yaw);
        setDrivePower(powerArray[0], powerArray[1], powerArray[2], powerArray[3]);
    }

    public void setDrivePower(double FL, double FR, double RL, double RR) {
        frontLeft.setPower(FL);
        frontRight.setPower(FR);
        rearLeft.setPower(RL);
        rearRight.setPower(RR);
    }
}
