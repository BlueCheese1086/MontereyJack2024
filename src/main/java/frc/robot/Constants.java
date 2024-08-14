// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public class DriveConstants {
        // Motor IDs
        public static final int FrontLeftID = 1;
        public static final int FrontRightID = 2;
        public static final int BackLeftID = 3;
        public static final int BackRightID = 4;

        // Other constants
        public static final double deadband = 0.02;
        public static final double maxDriveSpeed = 0.6;
        public static final double maxTurnSpeed = 0.6;
        public static final boolean squareInputs = true;
    }

    public class IntakeConstants {
        // Motor/Solenoid IDs
        public static final int FrontRollerID = 11;
        public static final int LeftIndexerID = 12;
        public static final int RightIndexerID = 13;
        public static final int BottomTrackID = 14;
        public static final int SolenoidID = 8;
        
        // Max Speeds
        public static final double maxIntakeSpeed = 0.4;
    }
    
    public class TowerConstants {
        // Motor/Solenoid IDs
        public static final int LeftLaunchRollerID = 21;
        public static final int RightLaunchRollerID = 22;
        public static final int FeedRollerID = 23;
        public static final int SolenoidID = 9;

        // Max Speeds
        public static final double maxLaunchSpeed = 0.6;
        public static final double maxFeedSpeed = 1;
    }
}