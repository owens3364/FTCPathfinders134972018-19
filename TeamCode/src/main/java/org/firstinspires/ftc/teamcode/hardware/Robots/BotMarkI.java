package org.firstinspires.ftc.teamcode.hardware.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.RobotComponents.DriveTrainMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.HookMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.IntakeMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.LiftMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotInterfaces.StandardDriveOpModeUsageMarkI;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class BotMarkI implements StandardDriveOpModeUsageMarkI {

    private final DriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final IntakeMarkI intake;
    private final HookMarkI hook;

    private static final String driveTrainLeftDriveName = "LeftDrive";
    private static final String driveTrainRightDriveName = "RightDrive";
    private static final String liftDriveName = "CableDrive";
    private static final String intakeDriveName = "IntakeDrive";
    private static final String hookDriveName = "hookDrive";
    private static final String hookServoName = "hookServo";

    public BotMarkI(HardwareMap map) {
        this.driveTrain = new DriveTrainMarkI(map, driveTrainLeftDriveName,
                driveTrainRightDriveName);
        this.lift = new LiftMarkI(map, liftDriveName);
        this.intake = new IntakeMarkI(map, intakeDriveName);
        this.hook = new HookMarkI(map, hookDriveName, hookServoName);
    }

    //Drive motors by power

    @Override
    public void setLeftDrive(double power) {
        driveTrain.setLeft(power);
    }

    @Override
    public void setRightDrive(double power) {
        driveTrain.setRight(power);
    }

    @Override
    public void setLiftDrive(double power) {
        lift.setCableDrive(power);
    }

    @Override
    public void setHookDrive(double power) {
        hook.setDrive(power);
    }

    @Override
    public void setIntakeDrive(double power) {
        intake.set(power);
    }

    //Drive motors by rotations

    @Override
    public void setLeftDriveForRotations(double power, double rotations) {
        driveTrain.setLeftForRotations(power, rotations);
    }

    @Override
    public void setRightDriveForRotations(double power, double rotations) {
        driveTrain.setRightForRotations(power, rotations);
    }

    @Override
    public void setIntakeDriveForRotations(double power, double rotations) {
        intake.setForRotations(power, rotations);
    }

    @Override
    public void setLiftDriveForRotations(double power, double rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    @Override
    public void setHookDriveForRotations(double power, double rotations) {
        hook.setDriveForRotations(power, rotations);
    }

    //Drive motors by distance in millimeters

    @Override
    public void setLeftDriveForMM(double power, int mm) {
        driveTrain.setLeftForMM(power, mm);
    }

    @Override
    public void setRightDriveForMM(double power, int mm) {
        driveTrain.setRightForMM(power, mm);
    }

    @Override
    public void setIntakeDriveForMM(double power, int mm) {
        intake.setForMM(power, mm);
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForMM(power, mm);
    }

    @Override
    public void setHookDriveForMM(double power, int mm) {
        hook.setDriveForMM(power, mm);
    }

    //Freeze and coast hook and lift motors

    @Override
    public void freezeLift() {
        lift.freeze();
    }

    @Override
    public void freezeHook() {
        hook.freeze();
    }

    @Override
    public void coastLift() {
        lift.coast();
    }

    @Override
    public void coastHook() {
        hook.coast();
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
    }

    //Set servo positions

    @Override
    public void activateHook() {
        hook.activate();
    }

    @Override
    public void retractHook() {
        hook.retract();
    }

    //Zero all the motors

    @Override
    public void zeroAll() {
        driveTrain.setLeft(0);
        driveTrain.setRight(0);
        lift.freeze();
        intake.set(0);
        hook.freeze();
    }

    //TODO: IMPLEMENT THESE
    @Override
    public boolean createConfigurationFile() {
        return false;
    }

    @Override
    public boolean createConfigurationFile(String name) {
        return false;
    }
}
