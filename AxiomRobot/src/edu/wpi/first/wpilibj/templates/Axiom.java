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
    
    //motor configuration
    static final int FRONT_LEFT_MOTOR_PWM = 1;
    static final int REAR_LEFT_MOTOR_PWM = 2;
    static final int FRONT_RIGHT_MOTOR_PWM = 3;
    static final int REAR_RIGHT_MOTOR_PWM = 4;
    
    //joystick configuration
    static final int LEFT_JOYSTICK_USB = 1;
    static final int RIGHT_JOYSTICK_USB = 2;
    
    Jaguar frontLeft = new Jaguar(FRONT_LEFT_MOTOR_PWM);
    Victor rearLeft = new Victor(REAR_LEFT_MOTOR_PWM);
    Jaguar frontRight = new Jaguar(FRONT_RIGHT_MOTOR_PWM);
    Victor rearRight = new Victor(REAR_RIGHT_MOTOR_PWM);
    
    RobotDrive chassis = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
    Joystick leftJoystick = new Joystick(LEFT_JOYSTICK_USB);
    Joystick rightJoystick = new Joystick(RIGHT_JOYSTICK_USB);
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        /*
        chassis.drive(0.2, 0.0); //move forward at 0.5 power
        Timer.delay(2.0); //run for 2 seconds
        chassis.drive(0.0, 0.0); //stop
        */
        while(isAutonomous()) {
            frontLeft.set(0.2);
            frontRight.set(-0.2);
            rearLeft.set(0.2);
            rearRight.set(-0.2);
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl()) {
            getWatchdog().feed();
            double speed = -((leftJoystick.getZ()-1)/2);
            arcadeDrive(leftJoystick.getY(), leftJoystick.getX(), speed);
            /*frontLeft.set(-leftJoystick.getY());
            frontRight.set(rightJoystick.getY());
            rearLeft.set(-leftJoystick.getY());
            rearRight.set(rightJoystick.getY());*/
        }
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, double speedMult) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;
        
        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        
        frontLeft.set(-leftMotorSpeed*speedMult);
        frontRight.set(rightMotorSpeed*speedMult);
        rearLeft.set(-leftMotorSpeed*speedMult);
        rearRight.set(rightMotorSpeed*speedMult);
    }
}
