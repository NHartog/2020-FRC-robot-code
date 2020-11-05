/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {
  public TalonSRX roller1 = new TalonSRX(RobotMap.ROLLER_1.value);
  public TalonSRX roller2 = new TalonSRX(RobotMap.ROLLER_2.value);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Rollers(){
     /* Ensure motor output is neutral during init */
     roller1.set(ControlMode.PercentOutput, 0);
     roller2.set(ControlMode.PercentOutput, 0);
    
     roller1.configOpenloopRamp(Constants.shootConfig, Constants.shootConfig);
     roller2.configOpenloopRamp(Constants.shootConfig, Constants.shootConfig);

     roller1.configClosedloopRamp(Constants.shootConfig,Constants.shootConfig);
     roller2.configClosedloopRamp(Constants.shootConfig,Constants.shootConfig);
     


    /* Factory Default all hardware to prevent unexpected behaviour */
     roller1.configFactoryDefault();
     roller2.configFactoryDefault();
    
 
    /* Set Neutral mode */
    roller1.setNeutralMode(NeutralMode.Brake);
    roller2.setNeutralMode(NeutralMode.Brake);
  
    /* Configure output direction */
     //roller1.setInverted(true);
  }

  public void setTheRoller(){
    if(DriveBase.moved && AdjustableHood.readyToShoot){
      roller1.set(ControlMode.PercentOutput, 0.5, DemandType.Neutral, 0);
      roller2.set(ControlMode.PercentOutput, 0.5, DemandType.Neutral, 0);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
