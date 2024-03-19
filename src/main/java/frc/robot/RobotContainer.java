// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Drivetrain.Commands.*;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Intake.Commands.*;
import frc.robot.subsystems.Tower.Tower;
import frc.robot.subsystems.Tower.Commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // Creates the pneumatics hub
    private final PneumaticHub hub  = new PneumaticHub(31);

    // Initializes the subsystems
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake(hub);
    private final Tower tower = new Tower(hub);

    // Creates the remote that controls the robot.
    // Found a new type of XboxController class that makes it a lot easier to make triggers!
    // It even has all of the functionality from the plain XboxController class!
    private final CommandXboxController xbox = new CommandXboxController(0);

    /** The container for the robot. Contains subsystems, IO devices, and commands. */
    public RobotContainer() {
        // Configures the trigger -> command bindings
        configureBindings();
    }

    /** Use this method to define your trigger->command mappings. */
    private void configureBindings() {
        /*
         * A - Runs the feed.
         * B - Opens/closes the intake.
         * X - Runs the intake.
         * Y - Toggles the flywheels.
         * POV Up - Opens shooter (higher shots)
         * POV Down - Closes shooter (farther shots)
         * Left Bumper - Run intake/shooter in reverse.
         */
        xbox.a().whileTrue(new RunFeed(tower, 1)); // A
        xbox.b().toggleOnTrue(new SetIntakeState(intake, true)); // B
        xbox.x().whileTrue(new RunIntake(intake, 1)); // X
        xbox.y().toggleOnTrue(new RunLaunch(tower, 1)); // Y
        xbox.povUp().onTrue(new SetHoodState(tower, false)); // POV Up
        xbox.povDown().onTrue(new SetHoodState(tower, true)); // POV Down

        // The exception to this method is the left bumper, as it runs all of the subsystems in reverse, and opens all of the solenoids.
        xbox.leftBumper().whileTrue(new SetIntakeState(intake, true)); // Left Bumper
        xbox.leftBumper().whileTrue(new RunIntake(intake, -1)); // Left Bumper
        xbox.leftBumper().whileTrue(new RunFeed(tower, -1)); // Left Bumper
        xbox.leftBumper().whileTrue(new RunLaunch(tower, -1)); // Left Bumper
    }

    /** Passes the autonomous command to the {@link Robot} class. */
    public Command getAutonomousCommand() {
        return new Autonomous(drivetrain, intake, tower);
    }

    /** Passes the teleop command to the {@link Robot} class. */
    public Command getTeleopCommand() {
        return new ArcadeDrive(drivetrain, () -> -xbox.getRawAxis(1), () -> xbox.getRawAxis(4));
    }
}