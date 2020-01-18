/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private CANSparkMax leftMotor = new CANSparkMax(1, null);
  private CANSparkMax rightMotor = new CANSparkMax(2, null);
  private CANEncoder leftEncoder = new CANEncoder(leftMotor);
  private CANEncoder rightEncoder = new CANEncoder(rightMotor);
  private CANPIDController m_pidController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  

  private final Joystick joy1 = new Joystick(0);
  private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    leftMotor.restoreFactoryDefaults();
    rightMotor.restoreFactoryDefaults();
    leftMotor.setInverted(true);
    leftEncoder.setInverted(true);
    leftMotor.follow(rightMotor);

    m_pidController = rightMotor.getPIDController();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds

    
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    //pid coefficients
    kP = 5e-5;
    kI = 1e-6;
    kD = 0;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 5700;
    m_pidController.setP(kP);

    double speed = joy1.getRawAxis(1);
    leftMotor.set(-speed);
    rightMotor.set(speed);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
