
// package frc.robot;

// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.XboxController;
// import com.ctre.phoenix.motorcontrol.can.*;
// import com.ctre.phoenix.motorcontrol.*;


// class LaunchDrive {
//     /* Hardware */
// 	WPI_TalonSRX leftShooter = new WPI_TalonSRX(9);
//     WPI_TalonSRX rightShooter = new WPI_TalonSRX(10);
//     XboxController xboxControllerTwo = new XboxController(1);
    
//     /* String for output */
//     StringBuilder _sb = new StringBuilder();
    
//     /* Loop tracker for prints */
// 	int _loops = 0;


// 	public void robotInit() {
//         /* Factory Default all hardware to prevent unexpected behaviour */
//         leftShooter.configFactoryDefault();
//         rightShooter.configFactoryDefault();


// 		// /* Config sensor used for Primary PID [Velocity] */
//         leftShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
//                                             Constants.kPIDLoopIdx, 
//                                             Constants.kTimeoutMs);
//                                             leftShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
//                                             Constants.kPIDLoopIdx, 
//                                             Constants.kTimeoutMs);
//         rightShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
//                                             Constants.kPIDLoopIdx, 
//                                             Constants.kTimeoutMs);
//                                             rightShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
//                                             Constants.kPIDLoopIdx, 
//                                             Constants.kTimeoutMs);

//         /**
// 		 * Phase sensor accordingly. 
//          * Positive Sensor Reading should match Green (blinking) Leds on Talon
//          */
// 		leftShooter.setSensorPhase(true);
//         rightShooter.setSensorPhase(true);


// 		// /* Config the peak and nominal outputs */
// 		leftShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
// 		leftShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
// 		leftShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
// 		leftShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);
//         rightShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
// 		rightShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
// 		rightShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
// 		rightShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);

// 		/* Config the Velocity closed loop gains in slot0 */
// 		leftShooter.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
// 		leftShooter.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
// 		rightShooter.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
// 		rightShooter.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
// 	}

// 	/**
// 	 * This function is called periodically during operator control
// 	 */
// 	public void teleopPeriodic() {
// 		/* Get gamepad axis */
// 		double shooterinput = xboxControllerTwo.getRightY();

// 		/* Get Talon/Victor's current output percentage */
// 		double leftmotorOutput = leftShooter.getMotorOutputPercent();
//         double rightmotorOutput = rightShooter.getMotorOutputPercent();

// 		System.out.println(leftmotorOutput);
		
//         /** 
// 		 * When button 1 is held, start and run Velocity Closed loop.
// 		 * Velocity Closed Loop is controlled by joystick position x500 RPM, [-500, 500] RPM
// 		 */
// 		if (xboxControllerTwo.getAButton()) {
// 		// 	/* Velocity Closed Loop */

// 			/**
// 			 * Convert 500 RPM to units / 100ms.
// 			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
// 			 * velocity setpoint is in units/100ms
// 			 */
// 			double targetVelocity_UnitsPer100ms = shooterinput * 500.0 * 4096 / 600;
// 			/* 500 RPM in either direction */
// 			leftShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
//             rightShooter.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

// 		// 	/* Append more signals to print when in speed mode. */
// 		// 	_sb.append("\terr:");
// 		// 	_sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
// 		// 	_sb.append("\ttrg:");
// 		// 	_sb.append(targetVelocity_UnitsPer100ms);
// 		// } else {
// 			/* Percent Output */

// 			leftShooter.set(ControlMode.PercentOutput, xboxControllerTwo.getRightY());
//             rightShooter.set(ControlMode.PercentOutput, -1 * xboxControllerTwo.getRightY());
// 		}

//         /* Print built string every 10 loops */
// 		if (++_loops >= 10) {
// 			_loops = 0;
// 			System.out.println(leftmotorOutput);
//             System.out.println(rightmotorOutput);
//         }

// 	}
// }