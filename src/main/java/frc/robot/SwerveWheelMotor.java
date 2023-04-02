package frc.robot;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Rotation2d;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;


public class SwerveWheelMotor {
    public TalonFX drive;
    public TalonFX steer;
    public CANCoder encoder;
    public Rotation2d angleOffset;
    public Rotation2d lastAngle;
    public SwerveModuleState state;

    // public double gearRatio = 8.14 / 1.0;
    public double gearRatio = ((150.0/7.0) / 1.0);


    public int printLock = 0;

    public SwerveWheelMotor(
            int driveID,
            int turnID,
            int encoderID,
            Rotation2d offset
    ) {
        drive = new TalonFX(driveID);
        steer = new TalonFX(turnID);
        encoder = new CANCoder(encoderID);
        angleOffset = offset;
        state = new SwerveModuleState(0, offset);

        configMotorsAndEncorders();
    }

    public void update(ControllerState controllerState) {
        // update motors
        state = SwerveModuleState.optimize(
            controllerState.swerveState,
            state.angle
        );

        // setSpeed(state);
        // setAngle(state);  
        
        // try passing unoptomized state
        setSpeed(controllerState.swerveState);
        // printStuff(controllerState.swerveState);
        setAngle(controllerState.swerveState);  
    }

    public void setSpeed(SwerveModuleState swerveState) {
        drive.set(ControlMode.PercentOutput, swerveState.speedMetersPerSecond * 0.1);
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(
                falconToDegrees(steer.getSelectedSensorPosition(), gearRatio));
                
    }

    public void setAngle(SwerveModuleState swerveState) {
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
        encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
        encoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
        encoder.configSensorDirection(true);

        drive.configFactoryDefault();
        drive.setNeutralMode(NeutralMode.Brake);
        drive.setSelectedSensorPosition(0);

        steer.configFactoryDefault();
        steer.config_kP(0, 0.3);
        steer.config_kI(0, 0);
        steer.config_kD(0, 0);
        steer.config_kF(0,0);
        steer.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);

        steer.setNeutralMode(NeutralMode.Coast);
        steer.setInverted(true);
        // steer.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 500);

        resetToAbsolute();  
    }

    public void printStuff(Object o) {
        printLock += 1;
        printLock %= 100;
        if (printLock == 0) {
            System.out.println(o);
        }
    }
}
