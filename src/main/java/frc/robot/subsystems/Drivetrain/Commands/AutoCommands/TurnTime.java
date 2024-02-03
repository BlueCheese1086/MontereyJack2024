package frc.robot.subsystems.Drivetrain.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class TurnTime extends Command {
    private final Drivetrain drivetrain;
    private final double speed;
    private final double seconds;
    private double end;

    /**
     * Creates a new TurnTime command.
     * This command turns the robot at x speed for y seconds.
     */
    public TurnTime(Drivetrain drivetrain, double speed, double seconds) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.seconds = seconds;
    }

    @Override
    /** Runs once on the first cycle the command is scheduled. */
    public void initialize() {
        this.end = System.currentTimeMillis() + seconds * 1000;
    }

    @Override
    /** Runs every cycle.  Contains the code that the command will execute. */
    public void execute() {
        drivetrain.arcadeDrive(0, speed);
    }

    @Override
    /** Runs every cycle to see if the command should finish. */
    public boolean isFinished() {
        return System.currentTimeMillis() == end;
    }

    @Override
    /** Runs once when the command finishes. */
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0);
    }
}