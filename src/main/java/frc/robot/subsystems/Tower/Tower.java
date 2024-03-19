package frc.robot.subsystems.Tower;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.TowerConstants;

public class Tower {
    // Initializing Motors
    TalonFX leftLaunchMotor = new TalonFX(TowerConstants.LeftLaunchRollerID);
    TalonFX rightLaunchMotor = new TalonFX(TowerConstants.RightLaunchRollerID);
    TalonSRX feedMotor = new TalonSRX(TowerConstants.FeedRollerID);

    // Initializing Pneumatics
    Solenoid hood;
    
    /**
     * Creates a new Tower subsystem.
     * <p>
     * This subsystem is in charge of controlling the motors and solenoids that are used to launch game objects.
     * 
     * @param hub The Pneumatics Hub connected to the robot.
     */
    public Tower(PneumaticHub hub) {
        // Creating the solenoid.
        hood = hub.makeSolenoid(TowerConstants.SolenoidID);

        // Restoring the factory defaults of each motor.
        leftLaunchMotor.getConfigurator().apply(new TalonFXConfiguration());
        rightLaunchMotor.getConfigurator().apply(new TalonFXConfiguration());
        feedMotor.configFactoryDefault();

        // Making the right motor follow the left one.
        rightLaunchMotor.setControl(new Follower(leftLaunchMotor.getDeviceID(), true));

        // Setting the initial state of the solenoid.
        hood.set(true);
    }

    /**
     * Sets the speed of the launch motors.
     * 
     * @param speed The duty cycle speed of the launch motors.
     */
    public void setLaunchSpeed(double speed) {
        SmartDashboard.putNumber("Tower/Launch Speed", speed * TowerConstants.maxLaunchSpeed);
        leftLaunchMotor.set(speed * TowerConstants.maxLaunchSpeed);
    }

    /**
     * Sets the speed of the tower.
     * 
     * @param speed The duty cycle speed of the tower.
     */
    public void setFeedSpeed(double speed) {
        SmartDashboard.putNumber("Tower/Feed Speed", speed * TowerConstants.maxFeedSpeed);
        feedMotor.set(ControlMode.PercentOutput, speed * TowerConstants.maxFeedSpeed);
    }

    /**
     * Returns the estimated average speed of the launch motors.
     * 
     * @return A double representing the speed of the launch motors.
     */
    public double getLaunchSpeed() {
        return (leftLaunchMotor.get() + rightLaunchMotor.get()) / 2;
    }

    /**
     * Returns the estimated speed of the feed motor.
     * 
     * @return A double representing the speed of the feed motor.
     */
    public double getFeedSpeed() {
        return feedMotor.getMotorOutputPercent();
    }

    /**
     * Sets the state of the hood.
     * <p>
     * Values -> Physical state:
     * <p>
     * True  -> Closed
     * <p>
     * False -> Open
     * <p>
     * 
     * @param state The new state of the hood.
     */
    public void setHood(boolean state) {
        SmartDashboard.putString("Hood", state ? "Closed" : "Open");
        hood.set(state);
    }

    /**
     * Returns the state of the hood.
     * 
     * @return The current state of the hood.
     */
    public boolean getHood() {
        return hood.get();
    }
}