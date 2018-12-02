package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class BotMarkI implements StandardDriveOpModeUsageMarkI {
    private static final double MM_PER_DRIVE_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_INTAKE_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_LIFT_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_HOOK_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double ROBOT_WEIGHT_LBS = 1; //TODO: MEASURE CORRECT VALUES

    private final DriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final IntakeMarkI intake;
    private final HookMarkI hook;

    public BotMarkI(HardwareMap map) {
        this.driveTrain = new DriveTrainMarkI(map, "LeftDrive", "RightDrive");
        this.lift = new LiftMarkI(map, "CableDrive");
        this.intake = new IntakeMarkI(map, "IntakeDrive");
        this.hook = new HookMarkI(map, "HookDrive", "HookServo");
    }

    @Override
    public double getMMPerDriveRotation() {
        return MM_PER_DRIVE_ROTATION;
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
    public void setLeftDriveForRotations(double power, int rotations) {
        driveTrain.setLeftForRotations(power, rotations);
    }

    @Override
    public void setRightDriveForRotations(double power, int rotations) {
        driveTrain.setRightForRotations(power, rotations);
    }

    @Override
    public void setIntakeDriveForRotations(double power, int rotations) {
        intake.setForRotations(power, rotations);
    }

    @Override
    public void setLiftDriveForRotations(double power, int rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    @Override
    public void setHookDriveForRotations(double power, int rotations) {
        hook.setDriveForRotations(power, rotations);
    }

    //Drive motors by distance in millimeters

    @Override
    public void setLeftDriveForMM(double power, int mm) {
        driveTrain.setLeftForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setRightDriveForMM(double power, int mm) {
        driveTrain.setRightForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setIntakeDriveForMM(double power, int mm) {
        intake.setForRotations(power, (int) ((mm / MM_PER_INTAKE_ROTATION) + .5));
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForRotations(power, (int) ((mm / MM_PER_LIFT_ROTATION) + .5));
    }

    @Override
    public void setHookDriveForMM(double power, int mm) {
        hook.setDriveForRotations(power, (int) ((mm / MM_PER_HOOK_ROTATION) + .5));
    }

    //Freeze and coast hook and lift motors

    @Override
    public void freezeLift() {
        lift.freeze(ROBOT_WEIGHT_LBS);
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
        lift.freeze(ROBOT_WEIGHT_LBS);
        intake.set(0);
        hook.freeze();
    }
}
