// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * This class may look different than the auto-generated one because I looked into some of the
 * TimedRobot functions, and found a better way to schedule commands.
 */
public class Robot extends TimedRobot {
  // Decares the various commands to be used later.
  private Command autonomousCommand;
  private Command teleopCommand;

  /** This command runs once when the robot has just been enabled. */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our autonomous chooser on the dashboard.
    RobotContainer robotContainer = new RobotContainer();
    
    // Gets the commands for each specific mode from the RobotContainer class.
    this.autonomousCommand = robotContainer.getAutonomousCommand();
    this.teleopCommand = robotContainer.getTeleopCommand();
  }

  /**
   * This function is called every 20 ms, no matter the mode.
   * This duty cycle can be changed by adding a constructor to this class.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.
    CommandScheduler.getInstance().run();
  }

  /** This command runs once when the robot enters Autonomous mode. */
  @Override
  public void autonomousInit() {
    autonomousCommand.schedule();
  }

  /** This function is once when the robot exits Autonomous mode. */
  // This command is not used in the auto-generated file.
  @Override
  public void autonomousExit() {
    autonomousCommand.cancel();
  }

  /** This command runs once when the robot enters Teleop mode. */
  @Override
  public void teleopInit() {
    teleopCommand.schedule();
  }

  /** This function is once when the robot exits Teleop mode. */
  @Override
  public void teleopExit() {
    teleopCommand.cancel();
  }
}