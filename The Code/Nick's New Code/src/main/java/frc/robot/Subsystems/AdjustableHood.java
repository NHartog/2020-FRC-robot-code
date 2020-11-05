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
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class AdjustableHood extends Subsystem {

  public  CANSparkMax hoodMotor = new CANSparkMax(RobotMap.HOODMOTOR.value, MotorType.kBrushless);

  public static boolean readyToShoot = false;
  
  public AdjustableHood(){

    hoodMotor.setIdleMode(IdleMode.kCoast);
    setUpPIDs(hoodMotor, 5e-5, 1e-6, 0, 0.000156, 0, -1, 1);
  

  }

  private void setUpPIDs(CANSparkMax motors, double kp, double ki, double kd, double kf, int kiZone, double min, double max ){
    motors.getPIDController().setD(kd);
    motors.getPIDController().setP(kp);
    motors.getPIDController().setI(ki);
    motors.getPIDController().setFF(kf);
    motors.getPIDController().setIZone(kiZone);
    motors.getPIDController().setOutputRange(min, max);
}

  public void setTheAngle(double angle){
    double encoderValueNeeded = Constants.angleConstant*angle;
    if(encoderValueNeeded < hoodMotor.getEncoder().getPosition()){
      hoodMotor.set(-0.2);
    }else{
      hoodMotor.set(0.2);
    }

    if(encoderValueNeeded == hoodMotor.getEncoder().getPosition()){
      readyToShoot = true;
    }
  }




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
