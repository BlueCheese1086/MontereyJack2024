package frc.robot.subsystems.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    // Creates solenoids as well, Solenoid objects.
    Solenoid intake;

    // Creates each motor controller as CANSparkMax objects.
    TalonSRX frontRoller = new TalonSRX(IntakeConstants.FrontRollerID);
    TalonSRX leftIndexer = new TalonSRX(IntakeConstants.LeftIndexerID);
    TalonSRX rightIndexer = new TalonSRX(IntakeConstants.RightIndexerID);

    /** Creates a new Intake subsystem */
    public Intake(PneumaticHub hub) {
        // Creating the solenoid
        intake = hub.makeSolenoid(IntakeConstants.SolenoidID);
        // Applying settings to each motor
        leftIndexer.follow(frontRoller);
        leftIndexer.setInverted(false);
        rightIndexer.follow(frontRoller);

        intake.set(true);
    }

    /** Sets the speed of the intake. */
    public void setSpeed(double speed) {
        SmartDashboard.putNumber("Intake Speed", speed * 0.3);

        // Only sets the speed of the front roller because all of the other motors follow it.
        frontRoller.set(ControlMode.PercentOutput, speed * 0.3);
    }

    /** Gets the speed of the intake.  Doesn't use encoders, so very unreliable. */
    public double getSpeed() {
        return frontRoller.getMotorOutputPercent();
    }

    /** Sets the state of the solenoids attached to the intake.  true is open, false is closed. */
    public void setState(boolean state) {
        SmartDashboard.putString("Intake State", state ? "Open" : "Closed");

        intake.set(state);
    }

    /** Gets the state of the solenoids. */
    public boolean getState() {
        // Only gets the state of one solenoid as they are manipulated together.
        return intake.get();
    }
}