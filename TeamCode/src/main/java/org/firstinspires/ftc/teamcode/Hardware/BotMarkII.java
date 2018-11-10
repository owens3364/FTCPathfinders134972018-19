package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class BotMarkII implements MechanumDriveOpModeUsage {
    private static final double mmPerDriveRotation = 0; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerLiftRotation = 0; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerSliderRotation = 0; //TODO: MEASURE CORRECT VALUES
    private static final double mmPerSliderAnglerRotation = 0; //TODO: MEASURE CORRECT VALUES

    private MechanumDriveTrain driveTrain;
    private Lift lift;
    private Arm arm;

    public static BotMarkII initialize(HardwareMap map) {
        MechanumDriveTrain driveTrain = MechanumDriveTrain.initialize(map, "FrontLeftDrive", "FrontRightDrive", "RearLeftDrive", "RearRightDrive");
        Lift lift = Lift.initialize(map, "Lift");
        Arm arm = Arm.initialize(map, "SlidersDrive", "ArmAnglerDrive", "IntakeAngler", "IntakeLid");
        return new BotMarkII(driveTrain, lift, arm);
    }

    private BotMarkII(MechanumDriveTrain driveTrain, Lift lift, Arm arm) {
        this.driveTrain = driveTrain;
        this.lift = lift;
        this.arm = arm;
    }

    @Override
    public double getMMPerDriveRotation() {
        return 0;
    }

    @Override
    public void setFrontLeftDrive(double power) {
        driveTrain.setFrontLeft(power);
    }

    @Override
    public void setFrontRightDrive(double power) {
        driveTrain.setFrontRight(power);
    }

    @Override
    public void setRearLeftDrive(double power) {
        driveTrain.setRearLeft(power);
    }

    @Override
    public void setRearRightDrive(double power) {
        driveTrain.setRearRight(power);
    }

    @Override
    public void setArmSliderDrive(double power) {
        arm.setSliderDrive(power);
    }

    @Override
    public void setArmAnglerDrive(double power) {
        arm.setArmAnglerDrive(power);
    }

    @Override
    public void setIntakeAngle(double angle) {
        arm.setIntakeAngle(angle);
    }

    @Override
    public void setLiftDrive(double power) {
        lift.setCableDrive(power);
    }

    @Override
    public void setFrontLeftDriveForRotations(double power, int rotations) {
        driveTrain.setFrontLeftForRotations(power, rotations);
    }

    @Override
    public void setFrontRightDriveForRotations(double power, int rotations) {
        driveTrain.setFrontRightForRotations(power, rotations);
    }

    @Override
    public void setRearLeftDriveForRotations(double power, int rotations) {
        driveTrain.setRearLeftForRotations(power, rotations);
    }

    @Override
    public void setRearRightDriveForRotations(double power, int rotations) {
        driveTrain.setRearRightForRotations(power, rotations);
    }

    @Override
    public void setArmSliderDriveForRotations(double power, int rotations) {
        arm.setSliderDriveForRotations(power, rotations);
    }

    @Override
    public void setArmAnglerDriveForRotations(double power, int rotations) {
        arm.setArmAnglerDriveForRotations(power, rotations);
    }

    @Override
    public void setLiftDriveForRotations(double power, int rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    @Override
    public void setFrontLeftDriveForMM(double power, int mm) {
        driveTrain.setFrontLeftForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    @Override
    public void setFrontRightDriveForMM(double power, int mm) {
        driveTrain.setFrontRightForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    @Override
    public void setRearLeftDriveForMM(double power, int mm) {
        driveTrain.setRearLeftForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    @Override
    public void setRearRightDriveForMM(double power, int mm) {
        driveTrain.setRearRightForRotations(power, (int) ((mm / mmPerDriveRotation) + .5));
    }

    @Override
    public void setArmSliderDriveForMM(double power, int mm) {
        arm.setSliderDriveForRotations(power, (int) ((mm / mmPerSliderRotation) + .5));
    }

    @Override
    public void setArmAnglerDriveForMM(double power, int mm) {
        arm.setArmAnglerDriveForRotations(power, (int) ((mm / mmPerSliderAnglerRotation) + .5));
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForRotations(power, (int) ((mm / mmPerLiftRotation) + .5));
    }

    @Override
    public void freezeLift() {
        lift.freeze();
    }

    @Override
    public void coastLift() {
        lift.coast();
    }

    @Override
    public void freezeArmAngler() {
        arm.freezeArmAngler();
    }

    @Override
    public void coastArmAngler() {
        arm.coastArmAngler();
    }

    @Override
    public void freezeArmSliders() {
        arm.freezeArmSliders();
    }

    @Override
    public void coastArmSliders() {
        arm.coastArmSliders();
    }

    @Override
    public void openArmLid() {
        arm.openLid();
    }

    @Override
    public void closeArmLid() {
        arm.closeLid();
    }

    @Override
    public void zeroAll() {
        setFrontLeftDrive(0);
        setFrontRightDrive(0);
        setRearLeftDrive(0);
        setRearRightDrive(0);
        lift.freeze();
        arm.freezeArmSliders();
        arm.freezeArmAngler();
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
    }
}
