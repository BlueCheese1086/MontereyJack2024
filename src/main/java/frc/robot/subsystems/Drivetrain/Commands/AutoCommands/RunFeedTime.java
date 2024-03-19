package frc.robot.subsystems.Drivetrain.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class RunFeedTime extends Command {
    private final Tower tower;
    private final double speed;
    private final double seconds;
    private double end;

    /**
     * Creates a new RunTowerTime
     * This command runs the tower at x speed for y seconds.
     */
    public RunFeedTime(Tower tower, double speed, double seconds) {
        this.tower = tower;
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
        tower.setFeedSpeed(speed);
    }

    @Override
    /** Runs every cycle to see if the command should finish. */
    public boolean isFinished() {
        return System.currentTimeMillis() == end;
    }

    @Override
    /** Runs once when the command finishes. */
    public void end(boolean interrupted) {
        tower.setFeedSpeed(0);
    }
}