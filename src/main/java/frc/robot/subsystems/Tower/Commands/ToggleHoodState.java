package frc.robot.subsystems.Tower.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class ToggleHoodState extends Command {
    public final Tower tower;

    public ToggleHoodState(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void initialize() {
        tower.setHood(!tower.getHood());
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}