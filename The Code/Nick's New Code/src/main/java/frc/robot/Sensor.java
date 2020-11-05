/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import com.revrobotics.ColorSensorV3.RawColor;

import edu.wpi.first.hal.PortsJNI;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Subsystems.DriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Sensor {
    private static ArrayList<Encoder> encoders = new ArrayList<>();
    private static Encoder encoderOne = new Encoder(0,1);
    private static Encoder encoderTwo = new Encoder(2,3);
    
    private final static AnalogInput m_ultrasonic = new AnalogInput(RobotMap.ULTRASONIC.value);

  // distance in inches the robot wants to stay from an object
  private static final double kHoldDistance = 12.0;

  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // proportional speed constant
  private static final double kP = 0.05;

  //Limit Switch
  private final static DigitalInput limitSwitch = new DigitalInput(8);

  // Encoder---------------------------------------------------------------------------------------------

  public static Encoder getEncoderOne() {
    return encoderOne;
  }

  public static Encoder getEncoderTwo() {
    return encoderTwo;
  }


  



  //Limelight---------------------------------------------------------------------------------------------

  public static double getLimelightDistance(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    return Math.round((Constants.LIMELIGHT_HEIGHT)/Math.tan(Math.toRadians(Constants.LIMELIGHT_ANGLE + table.getEntry("ty").getDouble(0))));
  }

  public static double getLimelightTX(){
    System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public static double getLimelightTV(){
    System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0));
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  }
  
  public static double getLimelightTY(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public static double getLimelightTZ(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tz").getDouble(0);
  }

  public static void turnOnCamera(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }

  public static void turnOffCamera(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }


//Color Sensor---------------------------------------------------------------------------------------------

   
  private final static I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a parameter.
   * The device will be automatically initialized with default parameters.
   */
  private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This
   * can be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final static ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these are
   * here as a basic example.
   */
  private final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private final static Color kWhite = ColorMatch.makeColor(0.15, 0.15, 0.15);

  public void setupSensors() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kWhite);
  }

  public void update() {

    setupSensors();

    SmartDashboard.putString("DB/String 5", "Color " + getStrColor());
  }

  public static String getStrColor() {

    Color detectedColor = m_colorSensor.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else if (match.color == kWhite) {
      colorString = "White";
    } else {
      colorString = "Unknown";
    }

    return colorString;

  }

  public static int getColorDistance(){
    return m_colorSensor.getProximity();
  }


  //Limit Sitch------------------------------------------------------------------------------------------------

  public static boolean getPressed(){
    return limitSwitch.get();
  }

}
