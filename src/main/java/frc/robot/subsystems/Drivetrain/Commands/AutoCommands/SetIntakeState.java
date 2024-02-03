package frc.robot.subsystems.Drivetrain.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Intake;

public class SetIntakeState extends Command {
    public final Intake intake;
    public final boolean state;

    /**
     * Creates a new SetIntakeState command.
     * This sets the state of the intake to state.
     */
    public SetIntakeState(Intake intake, boolean state) {
        this.intake = intake;
        this.state = state;
    }

    @Override
    /** Runs every cycle.  Contains the code that the command will execute. */
    public void execute() {
        intake.setState(state);
    }
}