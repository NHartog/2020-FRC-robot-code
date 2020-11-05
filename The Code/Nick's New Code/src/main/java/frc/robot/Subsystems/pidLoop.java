package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public abstract class pidLoop extends Subsystem {

    public void setupMotor(TalonSRX motor, int absolutePosition){
        /* Config the sensor used for Primary PID and sensor direction */
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        
        /* Ensure sensor is positive when output is positive */
        motor.setSensorPhase(Constants.kSensorPhase);
        
        /**
        * Set based on what direction you want forward/positive to be.
        * This does not affect sensor phase. 
        */ 
        motor.setInverted(Constants.kMotorInvert);

        /* Config the peak and nominal outputs, 12V means full */
        motor.configNominalOutputForward(0, Constants.kTimeoutMs);
        motor.configNominalOutputReverse(0, Constants.kTimeoutMs);
        motor.configPeakOutputForward(1, Constants.kTimeoutMs);
        motor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        /**
        * Config the allowable closed-loop error, Closed-Loop output will be
        * neutral within this range. See Table in Section 17.2.1 for native
        * units per rotation.
        */
        motor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
        motor.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        motor.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        motor.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        motor.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);

        /* Mask out overflows, keep bottom 12 bits */
        absolutePosition &= 0xFFF;
        if (Constants.kSensorPhase) { absolutePosition *= -1; }
        if (Constants.kMotorInvert) { absolutePosition *= -1; }

        /* Set the quadrature (relative) sensor to match absolute */
        motor.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }
}