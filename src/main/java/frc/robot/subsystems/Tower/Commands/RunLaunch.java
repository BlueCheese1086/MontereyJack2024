package frc.robot.subsystems.Tower.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tower.Tower;

public class RunLaunch extends Command {
    private final Tower tower;
    private final double speed;
    
    public RunLaunch(Tower tower, double speed) {
        this.tower = tower;
        this.speed = speed;
    }

    @Override
    public void execute() {
        tower.setLaunchSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        tower.setLaunchSpeed(0);
    }
}