/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
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
    RobotDrive chassis = new RobotDrive(1,2);
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    getWatchdog().setExpiration(0.1);
    
    public void autonomous() {
        getWatchdog().setEnabled(false);
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
        getWatchdog().setEnabled(true);
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
