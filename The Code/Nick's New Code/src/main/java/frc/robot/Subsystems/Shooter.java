/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Sensor;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private final CANSparkMax _CanShooter = new CANSparkMax(RobotMap.SHOOTERTOP.value, MotorType.kBrushless);
 

  public Shooter(){

    _CanShooter.setIdleMode(IdleMode.kCoast);
     setUpPIDs(_CanShooter,5e-5, 1e-6, 0, 0.000156, 0, -1, 1);

  }

  public void testShoot(){

  }

  public void startToShoot(OI oi){
    double angle = Sensor.getLimelightDistance()*0.45;
    Robot.ahood.readyToShoot = false;
    if(oi.b){
      if(Robot.driveBase.moved == true){
        Robot.ahood.setTheAngle(angle);
      }
      if(Robot.ahood.readyToShoot == true){
        _CanShooter.set(0.8);
      }
    }
  }

  private void setUpPIDs(CANSparkMax motors, double kp, double ki, double kd, double kf, int kiZone, double min, double max ){
      motors.getPIDController().setD(kd);
      motors.getPIDController().setP(kp);
      motors.getPIDController().setI(ki);
      motors.getPIDController().setFF(kf);
      motors.getPIDController().setIZone(kiZone);
      motors.getPIDController().setOutputRange(min, max);
  }
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
