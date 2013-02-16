/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Axiom extends SimpleRobot {
    /**
     * Configuration for motor controllers, joysticks, and other static variables.
     */
    static final int LEFT_MOTOR_PWM = 2;
    static final int RIGHT_MOTOR_PWM = 1;
    static final int LEFT_JOYSTICK_USB = 2;
    static final int RIGHT_JOYSTICK_USB = 1;
    RobotDrive chassis;
    Joystick leftJoystick;
    Joystick rightJoystick;
    
    public Axiom() {
        chassis = new RobotDrive(LEFT_MOTOR_PWM, RIGHT_MOTOR_PWM);
        leftJoystick = new Joystick(LEFT_JOYSTICK_USB);
        rightJoystick = new Joystick(RIGHT_JOYSTICK_USB);
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        while (isAutonomous()) {
            chassis.drive(0.5, 0.0); //move forward at 0.5 power
            Timer.delay(2.0); //run for 2 seconds
            chassis.drive(0.0, 0.0); //stop
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl()) {
            getWatchdog().feed();
            double speed = leftJoystick.getThrottle();
            chassis.arcadeDrive(speed*leftJoystick.getY(), speed*-leftJoystick.getX());
        }
    }
}
