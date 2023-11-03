package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

public class MotorMap {
    // motor ids
    public final static int frontLeftDrive = 1;
    public final static int frontRightDrive = 2;
    public final static int backRightDrive = 3;
    public final static int backLeftDrive = 4;

    public final static int frontLeftSteer = 5;
    public final static int frontRightSteer = 6;
    public final static int backRightSteer = 7;
    public final static int backLeftSteer = 8;

    public final static int frontLeftEncoder = 13;
    public final static int frontRightEncoder = 14;
    public final static int backRightEncoder = 15;
    public final static int backLeftEncoder = 16;
    
    // elevator
    // public final static int elevator = 11;
    // public final static int elevatorEncoder = 0; 

    // shooter
    // public final static int leftshooter = 9;
    // public final static int rightshooter = 10;

    // pitch
    // public final static int pitch = 12;


    // steer offsets
    //
    public final static Rotation2d frontLeftOffset = Rotation2d.fromDegrees(196.171);
    public final static Rotation2d frontRightOffset = Rotation2d.fromDegrees(162.070);
    public final static Rotation2d backRightOffset = Rotation2d.fromDegrees(177.187);
    public final static Rotation2d backLeftOffset = Rotation2d.fromDegrees(174.726);

    // swerve constants
    // public final static double sensorCoefficient = Math.PI / 2048.0;

    // steer constants
    //
    public final static double steerGearRatio = ((150.0/7.0) / 1.0);
    public static final int steerContinuousCurrentLimit = 25;
    public static final int steerPeakCurrentLimit = 40;
    public static final double steerPeakCurrentDuration = 0.1;
    public static final boolean steerEnableCurrentLimit = true;
    public static final double steerKP = 0.3;
    public static final double steerKI = 0.0;
    public static final double steerKD = 0.0;
    public static final double steerKF = 0.0;

    // drive constants
    //
    public static final double driveGearRatio = 6.75;
    public static final int driveContinuousCurrentLimit = 35;
    public static final int drivePeakCurrentLimit = 60;
    public static final double drivePeakCurrentDuration = 0.1;
    public static final boolean driveEnableCurrentLimit = true;
    public static final double openLoopRamp = 0.25;
    public static final double closedLoopRamp = 0.0;
    public static final double driveKP = 0.05;
    public static final double driveKI = 0.0;
    public static final double driveKD = 0.0;
    public static final double driveKF = 0.0;
    public static final double maxSpeed = 4.5; // meters per second
}
