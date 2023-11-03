package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderStatusFrame;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

    

public class SwerveWheelMotor {
    public TalonFX drive;
    public TalonFX steer;
    public CANCoder encoder;
    public Rotation2d angleOffset;
    public Rotation2d lastAngle;
    public Rotation2d rotationAngle;

    public SwerveModuleState prevState;

    public int printLock = 0;

    public SwerveWheelMotor(
            int driveID,
            int steerID,
            int encoderID,
            Rotation2d offset,
            Rotation2d rotatingAngle) {
        drive = new TalonFX(driveID);
        steer = new TalonFX(steerID);
        encoder = new CANCoder(encoderID);
        angleOffset = offset;
        prevState = new SwerveModuleState(0, offset);
        rotationAngle = rotatingAngle;

        configMotorsAndEncorders();

        // boolean boostenabled = false; 
    }

    public void update(DriveState controllerState) {
        // update motors
        SwerveModuleState state = optimize(controllerState.swerveState, prevState.angle);

        if (controllerState.isRotating) {
            // logic
            state = new SwerveModuleState(controllerState.rotationVelocity, rotationAngle);
        }

        setSpeed(state);
        setAngle(state);

        // printStuff(state);

        prevState = state; 
    }

    public void outputTelemetry(){
        SmartDashboard.putNumber("Wheel " + this.drive.getDeviceID(), getAngle().getDegrees());
        SmartDashboard.putNumber("Encoder " + this.drive.getDeviceID(), getCanCoder().getDegrees());
        SmartDashboard.putNumber("Current Position " + this.drive.getDeviceID(), this.drive.getSelectedSensorPosition());
        SmartDashboard.putNumber("Current Offset " + this.drive.getDeviceID(), this.angleOffset.getDegrees());
    }

    public void setSpeed(SwerveModuleState swerveState) {
    
        drive.set(ControlMode.PercentOutput, swerveState.speedMetersPerSecond * 0.7);

        // set speed function
    }


    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(
                falconToDegrees(steer.getSelectedSensorPosition(), MotorMap.steerGearRatio) % 360);

        
    }

    public void setAngle(SwerveModuleState swerveState) {
        steer.set(ControlMode.Position, degreesToFalcon(swerveState.angle.getDegrees(), MotorMap.steerGearRatio));
    }

    public double falconToDegrees(double counts, double gearRatio) {
        return counts * (360.0 / (gearRatio * 2048.0));
    }

    public double degreesToFalcon(double degrees, double gearRatio) {
        return degrees / (360.0 / (gearRatio * 2048.0));
    }

    public Rotation2d getCanCoder() {
        return Rotation2d.fromDegrees(encoder.getAbsolutePosition());
    }

    public void resetToAbsolute() {
        double absolutePosition = degreesToFalcon(getCanCoder().getDegrees() - angleOffset.getDegrees(),
                MotorMap.steerGearRatio);
        System.out.println("setting absolute position:");
        System.out.println(absolutePosition + ", " + getCanCoder().getDegrees() + ", " + angleOffset.getDegrees());
        
        // occasionally errors out
        ErrorCode err = steer.setSelectedSensorPosition(absolutePosition);
        while (err != ErrorCode.OK) {
            System.out.println("ERROR ): -" + err);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            err = steer.setSelectedSensorPosition(absolutePosition);
        }
    }

    public void configMotorsAndEncorders() {
        // config encoder
        //
        CANCoderConfiguration encoderConfig = new CANCoderConfiguration();
        encoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
        encoderConfig.sensorDirection = false;
        // encoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        encoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;

        encoder.configFactoryDefault();
        encoder.configAllSettings(encoderConfig);

        encoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 255);
        encoder.setStatusFramePeriod(CANCoderStatusFrame.VbatAndFaults, 255);

        // steer
        //
        TalonFXConfiguration steerConfig = new TalonFXConfiguration();
        SupplyCurrentLimitConfiguration steerSupplyLimit = new SupplyCurrentLimitConfiguration(
                MotorMap.steerEnableCurrentLimit,
                MotorMap.steerContinuousCurrentLimit,
                MotorMap.steerPeakCurrentLimit,
                MotorMap.steerPeakCurrentDuration);

        steerConfig.slot0.kP = MotorMap.steerKP;
        steerConfig.slot0.kI = MotorMap.steerKI;
        steerConfig.slot0.kD = MotorMap.steerKD;
        steerConfig.slot0.kF = MotorMap.steerKF;
        steerConfig.supplyCurrLimit = steerSupplyLimit;
        // steerConfig.initializationStrategy = SensorInitializationStrategy.BootToZero;

        steer.configFactoryDefault();
        steer.configAllSettings(steerConfig);
        steer.setInverted(true);
        steer.setNeutralMode(NeutralMode.Coast);
        resetToAbsolute();

        // drive
        //
        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        SupplyCurrentLimitConfiguration driveSupplyLimit = new SupplyCurrentLimitConfiguration(
                MotorMap.driveEnableCurrentLimit,
                MotorMap.driveContinuousCurrentLimit,
                MotorMap.drivePeakCurrentLimit,
                MotorMap.drivePeakCurrentDuration);

        driveConfig.slot0.kP = MotorMap.driveKP;
        driveConfig.slot0.kI = MotorMap.driveKI;
        driveConfig.slot0.kD = MotorMap.driveKD;
        driveConfig.slot0.kF = MotorMap.driveKF;
        driveConfig.supplyCurrLimit = driveSupplyLimit;
        //driveConfig.initializationStrategy = SensorInitializationStrategy.BootToZero;
        driveConfig.openloopRamp = MotorMap.openLoopRamp;
        driveConfig.closedloopRamp = MotorMap.closedLoopRamp;

        drive.configFactoryDefault();
        drive.configAllSettings(driveConfig);
        drive.setInverted(true);
        drive.setNeutralMode(NeutralMode.Brake);
        drive.setSelectedSensorPosition(0);
    }

    public static SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle) {
        double targetAngle = placeInAppropriate0To360Scope(currentAngle.getDegrees(), desiredState.angle.getDegrees());
        double targetSpeed = desiredState.speedMetersPerSecond;
        double delta = targetAngle - currentAngle.getDegrees();
        if (Math.abs(delta) > 90) {
            targetSpeed = -targetSpeed;
            targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
        }
        return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
    }

    /**
     * @param scopeReference Current Angle
     * @param newAngle       Target Angle
     * @return Closest angle within scope
     */
    private static double placeInAppropriate0To360Scope(double scopeReference, double newAngle) {
        double lowerBound;
        double upperBound;
        double lowerOffset = scopeReference % 360;
        if (lowerOffset >= 0) {
            lowerBound = scopeReference - lowerOffset;
            upperBound = scopeReference + (360 - lowerOffset);
        } else {
            upperBound = scopeReference - lowerOffset;
            lowerBound = scopeReference - (360 + lowerOffset);
        }
        while (newAngle < lowerBound) {
            newAngle += 360;
        }
        while (newAngle > upperBound) {
            newAngle -= 360;
        }
        if (newAngle - scopeReference > 180) {
            newAngle -= 360;
        } else if (newAngle - scopeReference < -180) {
            newAngle += 360;
        }
        return newAngle;
    }

    public void printStuff(Object o) {
        printLock += 1;
        printLock %= 100;
        if (printLock == 0) {
            System.out.println(o);
        }
    }
}
