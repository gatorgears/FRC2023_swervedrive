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

    public final static Rotation2d frontLeftOffset = new Rotation2d();
    public final static Rotation2d frontRightOffset = new Rotation2d();
    public final static Rotation2d backRightOffset = new Rotation2d();;
    public final static Rotation2d backLeftOffset = new Rotation2d();;

    public final static int frontLeftEncoder = 13;
    public final static int frontRightEncoder = 14;
    public final static int backRightEncoder = 15;
    public final static int backLeftEncoder = 15;
}
