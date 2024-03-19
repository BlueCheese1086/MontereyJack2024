package frc.robot.subsystems.Tower;


// Some code in this file is commented out.
// This code is from the CTRE Pheonix V6 API.
// Before implementing this code, make sure that the talons are up to date.
// Note that TalonSRX motors are not supported by the v6 api (yet)
// import com.ctre.phoenix6.hardware.TalonFX;

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
    // Initializes Pneumatics
    Solenoid hood;

    // Initializes Motors
    TalonFX leftLaunchMotor = new TalonFX(TowerConstants.LeftLaunchRollerID);
    TalonFX rightLaunchMotor = new TalonFX(TowerConstants.RightLaunchRollerID);
    TalonSRX feedMotor = new TalonSRX(TowerConstants.FeedRollerID);
    
    /** Creates a new Tower subsystem */
    public Tower(PneumaticHub hub) {
        // Creating the solenoid
        hood = hub.makeSolenoid(TowerConstants.SolenoidID);

        // Applying settings to each motor
        leftLaunchMotor.getConfigurator().apply(new TalonFXConfiguration());
        rightLaunchMotor.getConfigurator().apply(new TalonFXConfiguration());
        feedMotor.configFactoryDefault();

        // Making the right motor follow the left one.
        rightLaunchMotor.setControl(new Follower(leftLaunchMotor.getDeviceID(), true));

        hood.set(true);

        // If the backup motor is in use, uncomment this.
        //feedMotor.setInverted(true);
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
     * Sets the state of the hood. true is closed, false is open.
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