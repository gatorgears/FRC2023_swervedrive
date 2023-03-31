package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;

public class ControllerState {
    // swerve state
    public SwerveModuleState swerveState = new SwerveModuleState();

    public void update(XboxController xboxController) {
        // get rotation and distances for swerve state
        swerveState = new SwerveModuleState(getDistance(xboxController), getRotation(xboxController));
    }

    public Rotation2d getRotation(XboxController xboxController) {
        return new Rotation2d(xboxController.getLeftX(), xboxController.getLeftY());
    }

    public double getDistance(XboxController xboxController) {
        return Math.min(
            1,
            Math.sqrt(
                Math.pow(xboxController.getLeftX(), 2) + Math.pow(xboxController.getLeftY(), 2)
            )
        );
    }
}
