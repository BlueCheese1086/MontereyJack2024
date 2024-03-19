package frc.robot.subsystems.Tower.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class RunFeed extends Command {
    private final Tower tower;
    private final double speed;
    
    public RunFeed(Tower tower, double speed) {
        this.tower = tower;
        this.speed = speed;
    }

    @Override
    public void execute() {
        tower.setFeedSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        tower.setFeedSpeed(0);
    }
}