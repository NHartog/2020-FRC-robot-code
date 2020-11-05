/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.DriveBase;

/**
 * Add your docs here.
 */
public class UI {
    public void update() {
        double leftMotorSpeed = Robot.driveBase.getLeftSpeed();
        double rightMotorSpeed = Robot.driveBase.getRightSpeed();
        //double encoderPosition = Robot.encoderSet.getPosition();
        //double encoderVelocity = Robot.encoderSet.getVelocity();
       //speed is always positive, so dashboard is +
        

        //SmartDashboard.putNumber("Cascade", 0);
        // SmartDashboard.putNumber("DB/Slider 0", CascadeLift.shootSpeed);
        //SmartDashboard.putNumber("DB/Slider 1", rightMotorSpeed*2.5+2.5);
        //SmartDashboard.putString("DB/String 0", "Pos: " + encoderPosition);
        //SmartDashboard.putString("DB/String 1", "Vel: " + encoderVelocity);
            
        
       //SmartDashboard.putString("DB/String 2"," limwlight CTIVE" + Robot.oi.b);
       SmartDashboard.putString("DB/String 1", " center"+ Sensor.getLimelightTX());
    //    SmartDashboard.putString("DB/String 4", "Count: " + Robot.spinner.getCnt());
    //    SmartDashboard.putString("DB/String 3", "Previous Color: " + Robot.spinner.getPreviousColor());
    //    SmartDashboard.putString("DB/String 6", "Base Color: " + Robot.spinner.getBaseColor());
    //    SmartDashboard.putString("DB/String 2", "ss"+ Robot.shoot.getSpeed());
       

    //    SmartDashboard.putString("DB/String 8", " distance getting:"+ Robot.shoot.getIndexWanted()[0] );

       System.out.println("Encoder Distance:" +Sensor.getEncoderTwo().getDistance());

    }

}
