package frc.robot.subsystems.Tower;


// Some code in this file is commented out.
// This code is from the CTRE Pheonix V6 API.
// Before implementing this code, make sure that the talons are up to date.
// Note that TalonSRX motors are not supported by the v6 api (yet)
// import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.TowerConstants;

public class Tower {
    // Initializes Pneumatics
    Solenoid hood;

    // Creates motor controllers as TalonFXs and CANSparkMaxes
    TalonFX leftTopRoller = new TalonFX(TowerConstants.LeftTopRollerID);
    TalonFX rightTopRoller = new TalonFX(TowerConstants.RightTopRollerID);
    
    TalonSRX bottomRoller = new TalonSRX(TowerConstants.BottomRollerID);
    
    /** Creates a new Tower subsystem */
    public Tower(PneumaticHub hub) {
        // Creating the solenoid
        hood = hub.makeSolenoid(TowerConstants.SolenoidID);

        // Applying settings to each motor
        // leftTopRoller.getConfigurator().apply(new TalonFXConfiguration());
        // rightTopRoller.getConfigurator().apply(new TalonFXConfiguration());
        leftTopRoller.configFactoryDefault();
        rightTopRoller.configFactoryDefault();
        bottomRoller.configFactoryDefault();

        rightTopRoller.setInverted(true);
        leftTopRoller.setInverted(false);

        hood.set(true);

        // If the backup motor is in use, uncomment this.
        //bottomRoller.setInverted(true);
    }

    /** Sets the speed of the tower. */
    public void setSpeed(double speed) {
        SmartDashboard.putNumber("Tower Speed", speed * 0.6);
        // leftTopRoller.set(speed * 0.6);
        // rightTopRoller.set(speed * 0.6);
        leftTopRoller.set(ControlMode.PercentOutput, speed * 0.6);
        rightTopRoller.set(ControlMode.PercentOutput, speed * 0.6);
        bottomRoller.set(ControlMode.PercentOutput, speed * 0.6);
    }

    /** Returns the average speed of the motors.  Doesn't use encoders, so very unreliable. */
    public double getSpeed() {
        // return (bottomRoller.getMotorOutputPercent() + leftTopRoller.get()) / 2;
        return (bottomRoller.getMotorOutputPercent() + leftTopRoller.getMotorOutputPercent()) / 2;
    }

    /** Sets the state of the hood. true is closed, false is open. */
    public void setHood(boolean state) {
        SmartDashboard.putString("Hood", state ? "Closed" : "Open");
        hood.set(state);
    }

    /** Returns the state of the hood. */
    public boolean getHood() {
        return hood.get();
    }
}