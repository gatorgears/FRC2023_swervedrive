package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveDrive {
    public SwerveWheelMotor frontLeft = new SwerveWheelMotor(
            MotorMap.frontLeftDrive,
            MotorMap.frontLeftSteer,
            MotorMap.frontLeftEncoder,
            MotorMap.frontLeftOffset,
            Rotation2d.fromDegrees(-45));

    public SwerveWheelMotor frontRight = new SwerveWheelMotor(
            MotorMap.frontRightDrive,
            MotorMap.frontRightSteer,
            MotorMap.frontRightEncoder,
            MotorMap.frontRightOffset,
            Rotation2d.fromDegrees(225));

    public SwerveWheelMotor backRight = new SwerveWheelMotor(
            MotorMap.backRightDrive,
            MotorMap.backRightSteer,
            MotorMap.backRightEncoder,
            MotorMap.backRightOffset,
            Rotation2d.fromDegrees(-225));

    public SwerveWheelMotor backLeft = new SwerveWheelMotor(
            MotorMap.backLeftDrive,
            MotorMap.backLeftSteer,
            MotorMap.backLeftEncoder,
            MotorMap.backLeftOffset,
            Rotation2d.fromDegrees(45));

    public void update(DriveState controllerState) {
        // custon drive state

        frontLeft.update(controllerState);
        frontRight.update(controllerState);
        backRight.update(controllerState);
        backLeft.update(controllerState);
    }

    public void outputTelemetry() {
        frontLeft.outputTelemetry();
        frontRight.outputTelemetry();
        backLeft.outputTelemetry();
        backRight.outputTelemetry();
    }
}
