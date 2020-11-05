package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends TimedRobot {
	
	public static DriveBase driveBase;
	

	public static BallIntake bIntake;
  public static Shooter shoot;// = new Intake();
  public static AdjustableHood ahood;
	public static OI oi;
	public static UI ui;
  public static Compressor compressor;
  public static Spinner spinner;

  public Sensor sensor;


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    driveBase = new DriveBase(Constants.DRIVE_SMOOTH_FACTOR_fast);
    

    sensor = new Sensor();
    ahood = new AdjustableHood();
    
   // limelight = new Limelight();
    //grab = new PneumaticManipulator();
    bIntake = new BallIntake();
    shoot = new Shooter();
    oi = new OI();
    ui = new UI();
    
    CameraServer.getInstance().startAutomaticCapture();
    
    //limelight.robotInit();

    //shoot.setUpValues();
    spinner = new Spinner();

    CameraServer.getInstance().startAutomaticCapture();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    sensor.update();
    //System.out.println("Limelight TY: " +Sensor.getLimelightTY());
    //System.out.println(" Limelight distance: " +Sensor.getLimelightDistance());
    //SmartDashboard.putString("DB/String 0", "Pos: " + encoderPosition);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    Sensor.turnOnCamera();
    Sensor.getEncoderOne().setDistancePerPulse(1/256); 

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  //  oi.update();
  //  ui.update();
    Scheduler.getInstance().run();
    Sensor.turnOnCamera();
    driveBase.autonPeriodic();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out
    Sensor.turnOffCamera();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
      
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    oi.update();	
    ui.update();

    
    sensor.update();
    System.out.println("Limelight TY: " +Sensor.getLimelightTY());
    System.out.println(" Limelight distance: " +Sensor.getLimelightDistance());

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    oi.update();	
		ui.update();
  }
}

