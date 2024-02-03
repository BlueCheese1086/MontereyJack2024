package frc.robot.subsystems.Drivetrain.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class SetHoodState extends Command {
    public final Tower tower;
    public final boolean state;

    /**
     * Creates a new SetHoodState command.
     * This command sets the state of the hood to state.
     */
    public SetHoodState(Tower tower, boolean state) {
        this.tower = tower;
        this.state = state;
    }

    @Override
    /** Runs every cycle.  Contains the code that the command will execute. */
    public void execute() {
        tower.setHood(state);
    }
}