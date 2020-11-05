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
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Sensor;

/**
 * Add your docs here.
 */
public class Spinner extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX spinnerSrx = new TalonSRX(RobotMap.SPINNERMODULE.value);

  private String[] colors = {"Red", "Yellow", "Blue", "Green"};

  private String baseColor;
  private String previousColor;
  private int cnt;
  private boolean changedColor;


  public Spinner(){
    spinnerSrx.set(ControlMode.PercentOutput, 0);
      

		/* Factory Default all hardware to prevent unexpected behaviour */
	  spinnerSrx.configFactoryDefault();

		
		/* Set Neutral mode */
		spinnerSrx.setNeutralMode(NeutralMode.Brake);
      

    spinnerSrx.configOpenloopRamp(Constants.shootConfig, Constants.shootConfig);    
        
    spinnerSrx.configClosedloopRamp(Constants.shootConfig,Constants.shootConfig);
  }    
  
  public void checkColor(OI oi){
    
    if(oi.x){
     
      //previousColor = getPreviousColor();
      if(cnt <= 32 ){
        if(!changedColor){
          changedColor = true;
          baseColor = Sensor.getStrColor();
          cnt++;

        }else if (baseColor != Sensor.getStrColor()){
          changedColor = false;
        }
        spinnerSrx.set(ControlMode.PercentOutput, 0.5, DemandType.Neutral, 0);
      }
    }
  }

  public int getCnt(){
    return cnt;
  }

  public String getBaseColor(){
    return baseColor;
  }

  public String getPreviousColor(){
    int currentColor = 0;
    for(int i = 0; i < colors.length; i++){
      if(colors[i] == baseColor){
        currentColor = i;
      }
    }
    return colors[(currentColor + 1) % colors.length];
  }

  @Override
  public void initDefaultCommand() {
      //setDefaultCommand(new SpinnerSystem());
  }
}
