package frc.robot.subsystems.Drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {
    // Creates each motor controller as CANSparkMax objects.
    private CANSparkMax frontLeft = new CANSparkMax(DriveConstants.FrontLeftID, MotorType.kBrushless);
    private CANSparkMax frontRight = new CANSparkMax(DriveConstants.FrontRightID, MotorType.kBrushless);
    private CANSparkMax backLeft = new CANSparkMax(DriveConstants.BackLeftID, MotorType.kBrushless);
    private CANSparkMax backRight = new CANSparkMax(DriveConstants.BackRightID, MotorType.kBrushless);

    public Drivetrain() {
        // Applying settings to each motor
        // Creates a list of motors to easily manipulate settings.
        frontLeft.restoreFactoryDefaults();
        frontLeft.setIdleMode(IdleMode.kBrake);
        frontLeft.setSmartCurrentLimit(45);
        frontLeft.setInverted(true);
        frontLeft.burnFlash();

        backLeft.restoreFactoryDefaults();
        backLeft.setIdleMode(IdleMode.kBrake);
        backLeft.setSmartCurrentLimit(45);
        backLeft.follow(frontLeft);
        backLeft.setInverted(true);
        backLeft.burnFlash();

        frontRight.restoreFactoryDefaults();
        frontRight.setIdleMode(IdleMode.kBrake);
        frontRight.setSmartCurrentLimit(45);
        frontRight.setInverted(false);
        frontRight.burnFlash();

        backRight.restoreFactoryDefaults();
        backRight.setIdleMode(IdleMode.kBrake);
        backRight.setSmartCurrentLimit(45);
        backRight.follow(frontRight);
        backRight.setInverted(false);
        backRight.burnFlash();
    }

    /** Drives the robot with the y axis of one joystick and the x axis of another.  Drives robots in a way similar to how most games are played. */
    public void arcadeDrive(double xSpeed, double zRotation) {
        // Applying max speeds
        xSpeed *= DriveConstants.maxDriveSpeed;
        zRotation *= DriveConstants.maxTurnSpeed;

        // Applies a deadband to the inputs.
        MathUtil.applyDeadband(xSpeed, DriveConstants.deadband);
        MathUtil.applyDeadband(zRotation, DriveConstants.deadband);

        // Square the inputs (while preserving the sign) to increase fine control while permitting full power.
        if (DriveConstants.squareInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
            zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }

        // Creates the saturated speeds of the motors
        double leftSpeed = xSpeed - zRotation;
        double rightSpeed = xSpeed + zRotation;

        // Finds the maximum possible value of throttle + turn along the vector that the joystick is pointing, and then desaturates the wheel speeds.
        double greaterInput = Math.max(Math.abs(xSpeed), Math.abs(zRotation));
        double lesserInput = Math.min(Math.abs(xSpeed), Math.abs(zRotation));
        if (greaterInput == 0.0) {
            leftSpeed = 0;
            rightSpeed = 0;
        } else {
            double saturatedInput = (greaterInput + lesserInput) / greaterInput;
            leftSpeed /= saturatedInput;
            rightSpeed /= saturatedInput;
        }

        // Puts the left and right speeds onto SmartDashboard.
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);

        // Sets the speed of the motors.
        frontLeft.set(leftSpeed);
        frontRight.set(rightSpeed);
    }
}