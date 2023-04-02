package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

public class MotorMap {
    public final static int frontLeftDrive = 1;
    public final static int frontRightDrive = 2;
    public final static int backRightDrive = 3;
    public final static int backLeftDrive = 4;

    public final static int frontLeftSteer = 5;
    public final static int frontRightSteer = 6;
    public final static int backRightSteer = 7;
    public final static int backLeftSteer = 8;

    // roughly -180
    public final static Rotation2d frontLeftOffset = new Rotation2d(0);
    public final static Rotation2d frontRightOffset = new Rotation2d(0);
    public final static Rotation2d backRightOffset = new Rotation2d(0);
    public final static Rotation2d backLeftOffset = new Rotation2d(0);

    public final static int frontLeftEncoder = 13;
    public final static int frontRightEncoder = 14;
    public final static int backRightEncoder = 15;
    public final static int backLeftEncoder = 16;

    // Elevator
    public final static int elevator = 11;
    public final static int elevatorEncoder = 0; 

    // Shooter
    public final static int leftshooter = 9;
    public final static int rightshooter = 10;

    // Pitch
    public final static int pitch = 12;
}
