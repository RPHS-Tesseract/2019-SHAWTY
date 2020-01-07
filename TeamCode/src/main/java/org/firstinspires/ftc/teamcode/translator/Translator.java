/**
 * Jacob Bazata (jacobbazata@gmail.com)
 * 9/16/19
 */

package org.firstinspires.ftc.teamcode.translator;

import org.apache.commons.math3.linear.RealMatrix;

/* Translators have two purposes:
    1. Convert desired-velocity vectors to motor power arrays
    2. Modify raw controller input
 */

public interface Translator {
    double[] vectorTranslate(double longitudinal, double lateral, double yaw); // For autonomous
    double[] gamepadTranslate(double rawLeftX, double rawLeftY, double rawRightX, double rawRightY);
}