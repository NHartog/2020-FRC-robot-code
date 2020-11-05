/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.RobotMap;
import frc.robot.Sensor;
import frc.robot.Commands.*;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class DriveBase extends pidLoop {

    
    private TalonSRX _rightMaster = new TalonSRX(RobotMap.RIGHTDRIVEMASTER.value);
    private TalonSRX _rightSlave = new TalonSRX(RobotMap.RIGHTDRIVESLAVE.value);
    private TalonSRX _leftMaster = new TalonSRX(RobotMap.LEFTDRIVEMASTER.value);
    private TalonSRX _leftSlave = new TalonSRX(RobotMap.LEFTDRIVESLAVE.value);
    //private CANSparkMax _rightMast = new CANSparkMax(0, MotorType.kBrushless);
    
    //private Shooter shooter = new Shooter();
    
    
    private int state;
    private boolean buttonState;
    public static boolean moved;

    

    private double leftXRequest;
    private double leftYRequest;
    //private double rightXRequest;
    private double rightYRequest;
    private double targetPositionRotations;

    private double leftSpeed;
    private double rightSpeed;

    private final double SMOOTH_FACTOR_FAST;



    private boolean invert = false;
    private boolean invert2 = false;
    private float Kp = -0.1f;
    float min_command = 0.1f;
    double steering_adjust = 0.0;
    int count = 2000;
   
    

    public DriveBase(double smoothFactorfast) {
        SMOOTH_FACTOR_FAST = smoothFactorfast;
        
        //Ensure motor output is neutral during init 
		_leftMaster.set(ControlMode.Velocity, Constants.NO_VALUE);
		_rightMaster.set(ControlMode.Velocity, Constants.NO_VALUE);
		_leftSlave.set(ControlMode.Velocity, Constants.NO_VALUE);
		_rightSlave.set(ControlMode.Velocity, Constants.NO_VALUE);

        int absolutePosition = _leftMaster.getSensorCollection().getPulseWidthPosition();

        setupMotor(_leftMaster, absolutePosition);
        setupMotor(_leftSlave, absolutePosition);

        _leftMaster.setInverted(true);
		_leftSlave.follow(_leftMaster);
        _rightSlave.follow(_rightMaster);
        
        moved = false;

    }

    public void setControlRequest(int driveType, OI oi) {
    
        
        double llTX = Sensor.getLimelightTX();
        double tv = Sensor.getLimelightTV();
       
        SmartDashboard.putString("DB/String 6", "TX"+ llTX);


        float tx =(float) Sensor.getLimelightTX();


        if(oi.b){
            Sensor.turnOnCamera();
           if(tx!=0){ 
            moved = false;
            //Robot.shoot.update(Robot.shoot.getIndexWanted()[1], Robot.shoot.getIndexWanted()[2]);
            System.out.println("we can see the target");
            float heading_error = -tx;
            if (tx > 1.0)
            {
                    steering_adjust = Kp*heading_error - min_command;
            }
            else if (tx < 1.0)
            {
                    steering_adjust = Kp*heading_error + min_command;
            }
            leftSpeed = steering_adjust;
            rightSpeed = steering_adjust;
    
            System.out.println(steering_adjust); 
            _rightMaster.set(ControlMode.PercentOutput, rightSpeed, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);
            _leftMaster.set(ControlMode.PercentOutput, leftSpeed, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);
    
        }
        if(tx == 0){
            moved = true;
        }
       
     
       // System.out.println("driver2"); 

       }else{
           moved = false;
           AdjustableHood.readyToShoot = false;
       Sensor.turnOffCamera();
            this.leftXRequest = ( oi.leftX);
            this.leftYRequest = (oi.leftY);
            //this.rightXRequest = oi.rightX;
            this.rightYRequest = (oi.rightY);
        }

        this.targetPositionRotations = _rightMaster.getSelectedSensorPosition();

        
        //System.out.println("calllll");
        if(driveType == RobotMap.ARCADE_DRIVE.value){
           arcadeSet();
          //// System.out.println("arcade call");
       }
         
    
        else{
        _rightMaster.set(ControlMode.Velocity, 0, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);
        _leftMaster.set(ControlMode.Velocity, 0, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);
        }
    }
         
    


    public void autonPeriodic(){
        Sensor.turnOnCamera();
        setAuton(0.62, true);
    }

    public void setAuton(double speed, boolean timeToShoot) {
      
    }

    public void moveToTarget(OI oi){
        
    }
    
    public void arcadeSet() {
    
        leftSpeed += ((-leftXRequest+leftYRequest-leftSpeed) * SMOOTH_FACTOR_FAST);
        rightSpeed += (leftXRequest+leftYRequest-rightSpeed) * SMOOTH_FACTOR_FAST;

        _leftMaster.set(ControlMode.PercentOutput, -leftSpeed, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);
  
        _rightMaster.set(ControlMode.PercentOutput, rightSpeed, DemandType.ArbitraryFeedForward, Constants.NO_VALUE);  

        
    }


    public double getLeftSpeed() {
        return leftSpeed;
    }

    public double getRightSpeed() {
        return rightSpeed;
    }   
    
    public TalonSRX getLeftMotor(){
        return _leftMaster;
    }

    public TalonSRX getRightMotor(){
        return _rightMaster;
    }



    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
}
