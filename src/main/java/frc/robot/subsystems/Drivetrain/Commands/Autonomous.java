// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Drivetrain.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Drivetrain.Commands.AutoCommands.*;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Tower.Tower;

public final class Autonomous extends SequentialCommandGroup {
  public Autonomous(Drivetrain drivetrain, Intake intake, Tower tower) {
    // Creates a list of commands that will run sequentially.
    addCommands(
      new DriveTime(drivetrain, 1, 2), // Drive the robot at max speed for 2 seconds.
      new TurnTime(drivetrain, 1, 3), // Turns the robot at max speed for 3 seconds.
      new SetIntakeState(intake, true), // Sets the Solenoid controlling the intake to true, opening it.
      new RunIntakeTime(intake, 1, 2), // Runs the intake motors at max speed for 2 seconds.
      new SetIntakeState(intake, false), // Sets the Solenoid controlling the intake to false, closing it.
      new SetHoodState(tower, true), // Sets the Solenoid controlling the hood to true, opening it.
      new RunTowerTime(tower, 1, 3), // Runs the tower motors at max speed for 3 seconds.
      new SetHoodState(tower, false) // Sets the Solenoid controlling the hood to false, closing it.
    );
  }
}