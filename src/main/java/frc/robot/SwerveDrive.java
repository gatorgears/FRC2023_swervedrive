package frc.robot;

public class SwerveDrive {
    public SwerveWheelMotor frontLeft = new SwerveWheelMotor(
            MotorMap.frontLeftDrive,
            MotorMap.frontLeftSteer,
            MotorMap.frontLeftEncoder,
            MotorMap.frontLeftOffset);

    public SwerveWheelMotor frontRight = new SwerveWheelMotor(
            MotorMap.frontRightDrive,
            MotorMap.frontRightSteer,
            MotorMap.frontRightEncoder,
            MotorMap.frontRightOffset);

    public SwerveWheelMotor backRight = new SwerveWheelMotor(
            MotorMap.backRightDrive,
            MotorMap.backRightSteer,
            MotorMap.backRightEncoder,
            MotorMap.backRightOffset);

    public SwerveWheelMotor backLeft = new SwerveWheelMotor(
            MotorMap.backLeftDrive,
            MotorMap.backLeftSteer,
            MotorMap.backLeftEncoder,
            MotorMap.backLeftOffset);

    public void update(ControllerState controllerState) {
        frontLeft.update(controllerState);
        frontRight.update(controllerState);
        backRight.update(controllerState);
        backLeft.update(controllerState);
    }
}
