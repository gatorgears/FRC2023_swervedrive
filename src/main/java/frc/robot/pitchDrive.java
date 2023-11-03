
// package frc.robot;

// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// public class pitchDrive extends TimedRobot {
//   private XboxController xboxControllerTwo; 
//   private static final int deviceID = 11;
//   private CANSparkMax elevatorMotor;

//   @Override
//   public void robotInit() {
//     // initialize SPARK MAX with CAN ID
//     elevatorMotor = new CANSparkMax(deviceID, MotorType.kBrushless);

//     // /**
//      * The RestoreFactoryDefaults method can be used to reset the configuration parameters
//      * in the SPARK MAX to their factory default state. If no argument is passed, these
//      * parameters will not persist between power cycles
//      */
//     elevatorMotor.restoreFactoryDefaults();

//     /**
//      * Soft Limits restrict the motion of the motor in a particular direction
//      * at a particular point. Soft limits can be applied in only one direction, 
//      * or both directions at the same time. 
//      * 
//      * If the soft limits are disabled and then re-enabled, they will retain
//      * the last limits that they had for that particular direction.
//      * 
//      * The directions are rev::CANSparkMax::kForward and rev::CANSparkMax::kReverse
//      */
//     elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
//     elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

//     elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 15);
//     elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);

//     SmartDashboard.putBoolean("Forward Soft Limit Enabled",
//                               elevatorMotor.isSoftLimitEnabled(CANSparkMax.SoftLimitDirection.kForward));
//     SmartDashboard.putBoolean("Reverse Soft Limit Enabled",
//                               elevatorMotor.isSoftLimitEnabled(CANSparkMax.SoftLimitDirection.kReverse));                          
//     SmartDashboard.putNumber("Forward Soft Limit",
//                               elevatorMotor.getSoftLimit(CANSparkMax.SoftLimitDirection.kForward));
//     SmartDashboard.putNumber("Reverse Soft Limit",
//                               elevatorMotor.getSoftLimit(CANSparkMax.SoftLimitDirection.kReverse));

//     XboxController xboxControllerTwo = new XboxController(1);
//   }

// //   @Override
// // Dont know why this part is erroring out

// //   public void teleopPeriodic() {
// //     elevatorMotor.set(xboxControllerTwo.getLeftY();

// //     elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 
// //                             SmartDashboard.getBoolean("Forward Soft Limit Enabled", true));
// //     elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 
// //                             SmartDashboard.getBoolean("Reverse Soft Limit Enabled", true));
// //     elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 
// //                          (float)SmartDashboard.getNumber("Forward Soft Limit", 15));
// //     elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse,
// //                          (float)SmartDashboard.getNumber("Reverse Soft Limit", 0));
// //   }
// }
