/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
/**
 * Add your docs here.
 */
import edu.wpi.first.wpilibj.Joystick;

public class OI {

	Joystick _gamepad = new Joystick(0);
	
	public double 
	//Left Joystick
	leftX,
	leftY,  
	//Right Joystick
	rightX, 
	rightY, 
	//Triggers
	leftTrigger, 
	rightTrigger;
	
	public boolean 
	//Buttons
	a, 
	b, 
	y, 
	x, 
	//Bumpers
	lb, 
	rb, 
	//Joystick Press
	lp, 
	rp,
	//D-Pad
	dUp,
	dDown;

	
    public OI() {

    }

    public void update() {
        /* Gamepad processing */
		a = 			_gamepad.getRawButton(RobotMap.JOYSTICK_A.value);
		x = 			_gamepad.getRawButton(RobotMap.JOYSTICK_X.value);
		y = 			_gamepad.getRawButton(RobotMap.JOYSTICK_Y.value);
		b = 			_gamepad.getRawButton(RobotMap.JOYSTICK_B.value);
		lb = 			_gamepad.getRawButton(RobotMap.JOYSTICK_LB.value);
		rb = 			_gamepad.getRawButton(RobotMap.JOYSTICK_RB.value);
		lp = 			_gamepad.getRawButton(RobotMap.JOYSTICK_LP.value);
		rp = 			_gamepad.getRawButton(RobotMap.JOYSTICK_RP.value);
		leftY = 		deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_LY.value));
		leftX = 		deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_LX.value));
		leftTrigger = 	deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_LT.value));
		rightY = 		deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_RY.value));
		rightX = 		deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_RX.value));
		rightTrigger = 	deadband(_gamepad.getRawAxis(RobotMap.JOYSTICK_RT.value));
		dDown = _gamepad.getRawButton(RobotMap.JOYSTICK_A.value);

    }

    /** Deadband (deadzone in the middle of measurement) 5 percent, used on the gamepad */
	private double deadband(double value) {
		/* Upper deadband */
		if (value >= +Constants.JOYSTICK_DEADBAND) 
			return value;
		
		/* Lower deadband */
		if (value <= -Constants.JOYSTICK_DEADBAND)
			return value;
		
		/* Outside deadband */
		return Constants.NO_VALUE;
	}



}
