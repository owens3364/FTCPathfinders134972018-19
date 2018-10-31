package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class BotMarkI implements OpModeUsage {
    private static final double mmPerDriveRotation = 168; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerIntakeRotation = 300; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerLiftRotation = 12; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerHookRotation = 12; //TODO: MEASURE CORRECT VALUES

    private DriveTrain driveTrain;
    private Lift lift;
    private Intake intake;
    private Hook hook;

    public static BotMarkI initialize(HardwareMap map) {
        DriveTrain driveTrain = DriveTrain.initialize(map, "LeftDrive", "RightDrive");
        Lift lift = Lift.initialize(map, "CableDrive");
        Intake intake = Intake.initialize(map, "IntakeDrive");
        Hook hook = Hook.initialize(map, "HookDrive", "HookServo");
        return new BotMarkI(driveTrain, lift, intake, hook);
    }

    private BotMarkI(DriveTrain driveTrain, Lift lift, Intake intake, Hook hook) {
        this.driveTrain = driveTrain;
        this.lift = lift;
        this.intake = intake;
        this.hook = hook;
    }

    public double getMMPerDriveRotation() {
        return mmPerDriveRotation;
    }

    //Drive motors by power
    public void setLeftDrive(double power) {
        driveTrain.setLeft(power);
    }

    public void setRightDrive(double power) {
        driveTrain.setRight(power);
    }

    public void setLiftDrive(double power) {
        lift.setCableDrive(power);
    }

    public void setHookDrive(double power) {
        hook.setDrive(power);
    }

    public void setIntakeDrive(double power) {
        intake.set(power);
    }

    //Drive motors by rotations
    public void setLeftDriveForRotations(double power, int rotations) {
        driveTrain.setLeftForRotations(power, rotations);
    }

    public void setRightDriveForRotations(double power, int rotations) {
        driveTrain.setRightForRotations(power, rotations);
    }

    public void setIntakeDriveForRotations(double power, int rotations) {
        intake.setForRotations(power, rotations);
    }

    public void setLiftDriveForRotations(double power, int rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    public void setHookDriveForRotations(double power, int rotations) {
        hook.setDriveForRotations(power, rotations);
    }

    //Drive motors by distance in millimeters
    public void setLeftDriveForMM(double power, int mm) {
        driveTrain.setLeftForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    public void setRightDriveForMM(double power, int mm) {
        driveTrain.setRightForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    public void setIntakeDriveForMM(double power, int mm) {
        intake.setForRotations(power, (int) ((mm / mmPerIntakeRotation) + .5));
    }

    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForRotations(power, (int) ((mm / mmPerLiftRotation) + .5));
    }

    public void setHookDriveForMM(double power, int mm) {
        hook.setDriveForRotations(power, (int) ((mm / mmPerHookRotation) + .5));
    }

    //Freeze and coast hook and lift motors
    public void freezeLift() {
        lift.freeze();
    }

    public void freezeHook() {
        hook.freeze();
    }

    public void coastLift() {
        lift.coast();
    }

    public void coastHook() {
        hook.coast();
    }

    public Class getKindOfBot() {
        return this.getClass();
    }

    //Set servo positions
    public void activateHook() {
        hook.activate();
    }

    public void retractHook() {
        hook.retract();
    }

    //Zero all the motors
    public void zeroAll() {
        driveTrain.setLeft(0);
        driveTrain.setRight(0);
        lift.freeze();
        intake.set(0);
        hook.freeze();
    }
}
