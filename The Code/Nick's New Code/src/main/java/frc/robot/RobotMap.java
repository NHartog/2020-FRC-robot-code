/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */ 
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public enum RobotMap {
    //drive motor left--------------------------
    LEFTDRIVEMASTER(7),
    LEFTDRIVESLAVE(34), 
    //------------------------------------------
    //drive motor right-------------------------
    RIGHTDRIVEMASTER(20), 
    RIGHTDRIVESLAVE(50),
    //------------------------------------------
    CASCADE(13), 
    //------------------------------------------
    INTAKE(4),   
    //------------------------------------------
    SHOOTERTOP(10), 
    HOODMOTOR(27),
    //------------------------------------------
    WINCHMOTOR(6), 
    //------------------------------------------
    SPINNERMODULE(3),
    //IndexSystem Motors------------------------
    ROLLER_1(12),
    ROLLER_2(11),
    INDEXMOTOR_3(17),
    //INDEXMOTOR_4(17),


    //Joystick values---------------------------
    JOYSTICK_LX(0), JOYSTICK_LY(1), JOYSTICK_LT(2),
    JOYSTICK_RX(4), JOYSTICK_RY(5), JOYSTICK_RT(3),
    JOYSTICK_A(1), JOYSTICK_B(2), JOYSTICK_X(3), JOYSTICK_Y(4),
    JOYSTICK_LB(5), JOYSTICK_RB(6),
    JOYSTICK_LP(9), JOYSTICK_RP(10),
    //------------------------------------------

    //Sensors-------------------------------------
    ULTRASONIC(0),
    ENCODER1_VALUE1(1),
    ENCODER1_VALUE2(2),
    ENCODER2_VALUE1(3),
    ENCODER2_VALUE2(4),
    ENCODER3_VALUE1(5),
    ENCODER3_VALUE2(6),
    


    //Different drives--------------------------
    TANK_DRIVE(0), 
    ARCADE_DRIVE(1)
    //------------------------------------------
    ;
    public final int value;
    RobotMap(int value){
        this.value = value;
    }
}
