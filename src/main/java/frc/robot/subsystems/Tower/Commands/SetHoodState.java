package frc.robot.subsystems.Tower.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class SetHoodState extends Command {
    public final Tower tower;
    public final boolean state;

    public SetHoodState(Tower tower, boolean state) {
        this.tower = tower;
        this.state = state;
    }

    @Override
    public void execute() {
        tower.setHood(state);
    }

    @Override
    public void end(boolean interrupted) {
        tower.setHood(false);
    }
}