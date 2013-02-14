/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Axiom extends SimpleRobot {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    static final int LEFT_MOTOR_PWM = 2;
    static final int RIGHT_MOTOR_PWM = 1;
    static final int LEFT_JOYSTICK_USB = 2;
    static final int RIGHT_JOYSTICK_USB = 1;
    
    RobotDrive chassis = new RobotDrive(LEFT_MOTOR_PWM, RIGHT_MOTOR_PWM);
    Joystick leftJoystick = new Joystick(LEFT_JOYSTICK_USB);
    Joystick rightJoystick = new Joystick(RIGHT_JOYSTICK_USB);
    
    public void autonomous() {
        while (isAutonomous()) {
                chassis.drive(0.5, 0.0);
                Timer.delay(2.0); 
                chassis.drive(0.0, 0.0);
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl()) {
            getWatchdog().feed();
            chassis.tankDrive(leftJoystick, rightJoystick);
        }

    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
