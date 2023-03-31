package frc.robot;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Rotation2d;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

public class SwerveWheelMotor {
    public TalonFX drive;
    public TalonFX steer;
    public CANCoder encoder;
    public Rotation2d angleOffset;
    public Rotation2d lastAngle;
    public SwerveModuleState state;

    public double gearRatio = 8.14 / 1.0;

    public SwerveWheelMotor(
            int driveID,
            int turnID,
            int encoderID,
            Rotation2d offset
    ) {
        drive = new TalonFX(driveID);
        steer = new TalonFX(turnID);
        encoder = new CANCoder(encoderID);
        
        configMotorsAndEncorders();

        angleOffset = offset;
    }

    public void update(ControllerState controllerState) {
        // update motors
        state = SwerveModuleState.optimize(
            controllerState.swerveState,
            state.angle
        );
        setSpeed(state);
        setAngle(state);
    }

    public void setSpeed(SwerveModuleState swerveState) {
        drive.set(ControlMode.PercentOutput, swerveState.speedMetersPerSecond);
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(
                falconToDegrees(steer.getSelectedSensorPosition(), gearRatio));
    }

    public void setAngle(SwerveModuleState swerveState) {
        // optimize for angle here
        steer.set(ControlMode.Position, degreesToFalcon(swerveState.angle.getDegrees(), gearRatio));
    }

    public double falconToDegrees(double positionCounts, double gearRatio) {
        return positionCounts * (360.0 / (gearRatio * 2048.0));
    }

    public double degreesToFalcon(double degrees, double gearRatio) {
        return degrees / (360.0 / (gearRatio * 2048.0));
    }

    public Rotation2d getCanCoder() {
        return Rotation2d.fromDegrees(encoder.getAbsolutePosition());
    }

    public void resetToAbsolute() {
        double absolutePosition = degreesToFalcon(getCanCoder().getDegrees() - angleOffset.getDegrees(), gearRatio);
        steer.setSelectedSensorPosition(absolutePosition);
    }

    public void configMotorsAndEncorders() {
        encoder.configFactoryDefault();

        drive.configFactoryDefault();
        drive.setNeutralMode(NeutralMode.Brake);
        drive.setSelectedSensorPosition(0);

        steer.configFactoryDefault();
        steer.setNeutralMode(NeutralMode.Coast);
        steer.setInverted(true);
        resetToAbsolute();
    }
}
