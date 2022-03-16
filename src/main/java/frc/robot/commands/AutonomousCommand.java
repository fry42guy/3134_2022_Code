// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.*;
import oi.limelightvision.limelight.frc.LimeLight;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutonomousCommand extends CommandBase {
    private static final Timer Timer = null;
    private final DriveTrain m_driveTrain;
    private final Timer m_timer;
    private final ele m_ele;
    private final intake m_intake;
    private final LimeLight mylimelight;
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

private final double step1 = 2.0; ///                               ***
private final double step2 = 2.0; // time chunk for driving away                            ***     
private final double step3 = 3.0;  
private final double step4 = 2.5;      

private final double autodrivespeed = 0.5;
private final double autoshootspeed = 1.0;
private final double autointakespeed = -1.0;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutonomousCommand(DriveTrain subsystem, ele subsytem, intake intake_subsytem, LimeLight limelight_subsystem) {
          m_timer = new Timer ();
        m_driveTrain = subsystem;
        addRequirements(m_driveTrain);
        m_ele = subsytem;
        addRequirements(m_ele);

        m_intake = intake_subsytem;
        addRequirements(m_intake);
    
        mylimelight = limelight_subsystem;
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // m_subsystem = subsystem;
        // addRequirements(m_subsystem);    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    m_timer.reset();
    m_timer.start();
    
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(m_timer.get() < step1  && m_timer.get() > 0 ){ // Step 1
               m_ele.my_set_ele_speed(0);         //elvivator NO shoot
           // m_driveTrain.my_DriveArcade(autodrivespeed, 0); /// Drive Fwd
           new drivewithLimelightStear(() ->autoshootspeed, mylimelight, m_driveTrain); // autodrive with lime light steer
            m_intake.my_SetSpeed(autointakespeed); // intake FWD

        }

       else if(m_timer.get() > step1 && (m_timer.get() < (step1 + step2))){   // Step 2
        m_ele.my_set_ele_speed(0);         //elvivator NO shoot
        m_driveTrain.my_DriveArcade(autodrivespeed * -1, 0); /// Drive in REV
        m_intake.my_SetSpeed(0); // No Intake for step 2

        }
        else if(m_timer.get() > step1 + step2 && (m_timer.get() < (step1 + step2+ step3))){ // Step 3
        m_ele.my_set_ele_speed(0);         //elvivator NO shoot
        m_driveTrain.my_DriveArcade(0, 0); /// NO DRIVING FOR STEP THREE
        m_intake.my_SetSpeed(autointakespeed); // INtake FWD

        }
        else if(m_timer.get() > step1 + step2 + step3 && (m_timer.get() < (step1 + step2+ step3+step4))){ // Step 4
            m_ele.my_set_ele_speed(autoshootspeed);         //elivator shoot
            m_driveTrain.my_DriveArcade (0,0); /// NO DRIVING FOR STEP FOUR
            m_intake.my_SetSpeed(autointakespeed); // INtake FWD
    
            }
           
        else{ //Step 5 END of Program
            m_ele.my_set_ele_speed(0);         //no elvivator shoot
            m_driveTrain.my_DriveArcade(0, 0);
            m_intake.my_SetSpeed(0); /// NO DRIVING FOR STEP ONE
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_timer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
