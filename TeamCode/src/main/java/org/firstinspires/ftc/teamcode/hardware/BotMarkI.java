package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class BotMarkI implements StandardDriveOpModeUsage {
    private static final double MM_PER_DRIVE_ROTATION = 168; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_INTAKE_ROTATION = 300; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_LIFT_ROTATION = 12; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_HOOK_ROTATION = 12; //TODO: MEASURE CORRECT VALUES
    private static final double ROBOT_WEIGHT_LBS = 18; //TODO: MEASURE CORRECT VALUES

    private final DriveTrain driveTrain;
    private final Lift lift;
    private final Intake intake;
    private final Hook hook;

    public BotMarkI(HardwareMap map) {
        this.driveTrain = new DriveTrain(map, "LeftDrive", "RightDrive");
        this.lift = new Lift(map, "CableDrive");
        this.intake = new Intake(map, "IntakeDrive");
        this.hook = new Hook(map, "HookDrive", "HookServo");
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
