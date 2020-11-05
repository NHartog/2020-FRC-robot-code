package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Constants {




    public static final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        
    public static final NetworkTableEntry tx = table.getEntry("tx");
    public static final NetworkTableEntry ty = table.getEntry("ty");
    public static final NetworkTableEntry ta = table.getEntry("ta");
    public static final NetworkTableEntry tv = table.getEntry("tv");

    public static final NetworkTableEntry ledMode = table.getEntry("ledMode");
    public static final NetworkTableEntry camMode = table.getEntry("camMode");
    public static final NetworkTableEntry pipeline = table.getEntry("pipeline");

    //DriveBase Constants------------------------------------------------------------------------------------
    public final static double JERK_MINIMUM = 0.4;
    public final static double MAX_REDLINE_SPEED_OUTPUT = 0.75;
    public final static double MAX_REDLINE_SPEED_INPUT = 0.5;
    public final static int MAX_INTAKE_SPEED = 1;
    public final static int NO_VALUE = 0;
    public final static double REDLINE_SMOOTH_FACTOR = 0.04;
    public final static double VINTAKE_SMOOTH_FACTOR = 0.04;
    public final static double CIM_SMOOTH_FACTOR = 0.2;
    public final static double DRIVE_SMOOTH_FACTOR_fast = 0.2;
    public final static double DRIVE_SMOOTH_FACTOR_SLOW = 0.1;
    
    //Misc------------------------------------------------------------------------------------------
    public final static double JOYSTICK_DEADBAND = 0.05;
    public final static double JOYSTICK_MAX = 1;
    public final static int OPEN = 0;
    public final static int CLOSED = 1;
    public final static int TOURQE = 0;
    public final static int SPEED = 1;
    public final static int DEFAULT_CASCADE_POSITION = 0;
    public final static int BOTTOM = 0;
    public final static int TOP = 5;
    public static final double kValueToInches = 0.125;
   
    //PID Constants------------------------------------------------------------------------------------
    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;
    public final static int kTimeoutMs = 10;
    public static boolean kSensorPhase = true;
    public static boolean kMotorInvert = false;
    public final static double REDLINE_KP = 0.15;
    public final static double REDLINE_KI = 0.0;
    public final static double REDLINE_KD = 1.0;
    public final static double REDLINE_Kf = 0.0;
    public final static int REDLINE_Kizone = 0;
    public final static double REDLINE_KPeakOutput = 1.0;
    public static final Functions kGains = new Functions(REDLINE_KP, REDLINE_KI,REDLINE_KD,REDLINE_Kf, REDLINE_Kizone, REDLINE_KPeakOutput);
    public static final int shootConfig = 100;


    //Limelight Constants------------------------------------------------------------------------------------
    public static final double LIMELIGHT_HEIGHT = 98.25-15.5;
    public static final double LIMELIGHT_ANGLE = 30.0;
    public static final float kp = -0.1f;


    // Vision Tape Height

    public static final double visionTapeHeightFt = 2 + 7.5/12; // 2 feet, 7.5 inches

    // Camera height and angle

    public static final double cameraHeightInches = 15; // 15 inches

    public static final double cameraMountingAngle = 17.2; // 17.2 degrees

    public static final double angleConstant = 0.5;

    // Distance setpoints

    public static final double HATCH_CONNECTION_INCHES = 0.2;

    // Area setpoints

    public static final double HATCH_CONNECTION_AREA = 8.8; 
	
    public static final double ROCKET_SHOOTING_AREA = 7.5; 

    // Error tolerance

    public static final double rotationalErrorTolerance = 0.5f; // 0.5 degree error tolerance 

    public static final double fowardErrorTolerance = 1.0f; // 1 percent area error tolerance

    // Sensitivity set

    public static final double highGear = 0.5;

    public static final double lowGear = 0.25;

    //Cascade Constant
    
    public static final int spaceToColor = 1500;

    //Hood Constants

    public static final double Max_Degrees = 80;

    public static final double Max_Encoder_Count = 1000;

    public static final double Encoder_Buffer = 20;

}
