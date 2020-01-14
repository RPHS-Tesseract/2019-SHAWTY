package org.firstinspires.ftc.teamcode.translator;

/* This Translator will do nothing. */
public class NullTranslator implements Translator {
    public NullTranslator() {}

    @Override
    public double[] vectorTranslate(double longitudinal, double lateral, double yaw) {
        return new double[]{};
    }

    @Override
    public double[] gamepadTranslate(double rawLeftX, double rawLeftY, double rawRightX, double rawRightY) {
        return new double[]{};
    }
}
