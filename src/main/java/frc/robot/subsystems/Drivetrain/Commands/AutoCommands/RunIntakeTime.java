package frc.robot.subsystems.Drivetrain.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Intake;

public class RunIntakeTime extends Command {
    private final Intake intake;
    private final double speed;
    private final double seconds;
    private double end;

    /**
     * Creates a new RunIntakeTime command.
     * This command runs the intake at a x speed for y seconds.
     */
    public RunIntakeTime(Intake intake, double speed, double seconds) {
        this.intake = intake;
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
        intake.setSpeed(speed);
    }

    @Override
    /** Runs every cycle to see if the command should finish. */
    public boolean isFinished() {
        return System.currentTimeMillis() == end;
    }

    @Override
    /** Runs once when the command finishes. */
    public void end(boolean interrupted) {
        intake.setSpeed(0);
    }
}