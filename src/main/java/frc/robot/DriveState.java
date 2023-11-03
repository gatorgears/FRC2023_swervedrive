package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveState {
    // swerve state
    public SwerveModuleState swerveState = new SwerveModuleState();
    
    public boolean isRotating = false;
    public double rotationVelocity = 0;
    // this is a comment

    public void update(XboxController xboxController) {
        // get rotation and distances for swerve state
        swerveState = new SwerveModuleState(getDistance(xboxController), getRotation(xboxController));
        isRotating = getDistanceRight(xboxController) > 0.1;
        // boolean boostenabled = false;
        
        

        
        if (isRotating) {
            rotationVelocity = xboxController.getRightX();
            
        }

    }

    public Rotation2d getRotation(XboxController xboxController) {
        return new Rotation2d(xboxController.getLeftX(), -xboxController.getLeftY()).plus(Rotation2d.fromDegrees(-90));
    }

    // probably wrong
    public double getDistance(XboxController xboxController) {
        double distance =  Math.min(
            1,
            Math.sqrt(
                Math.pow(xboxController.getLeftX(), 2) + Math.pow(xboxController.getLeftY(), 2)
            )
        );

        return distance;

    }
    public double getDistanceRight(XboxController xboxController) {
        double distance =  Math.min(
            1,
            Math.sqrt(
                Math.pow(xboxController.getRightX(), 2) + Math.pow(xboxController.getRightY(), 2)
            )
        );

        return distance;
    }
}
