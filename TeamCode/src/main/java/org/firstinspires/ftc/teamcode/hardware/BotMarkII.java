package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class BotMarkII implements MechanumDriveOpModeUsageMarkI {
    private static final double MM_PER_DRIVE_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_LIFT_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_SLIDER_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double MM_PER_SLIDER_ANGLER_ROTATION = 1; //TODO: MEASURE CORRECT VALUES
    private static final double ROBOT_WEIGHT_LBS = 1; //TODO: MEASURE CORRECT VALUES

    private final MechanumDriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final ArmMarkI arm;

    private static final String FRONT_LEFT_DRIVE_NAME = "FrontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FrontRightDrive";
    private static final String REAR_LEFT_DRIVE_NAME = "RearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_NAME = "RearRightDrive";
    private static final String LIFT_DRIVE_NAME = "LiftMarkI";
    private static final String ARM_SLIDERS_DRIVE_NAME = "SlidersDrive";
    private static final String ARM_ANGULAR_DRIVE_NAME = "ArmAngularDrive";
    private static final String INTAKE_ANGULAR_SERVO_NAME = "IntakeAngular";
    private static final String INTAKE_LID_SERVO_NAME = "IntakeLid";

    public BotMarkII(HardwareMap map) {
        this.driveTrain = new MechanumDriveTrainMarkI(map, FRONT_LEFT_DRIVE_NAME, FRONT_RIGHT_DRIVE_NAME, REAR_LEFT_DRIVE_NAME, REAR_RIGHT_DRIVE_NAME);
        this.lift = new LiftMarkI(map, LIFT_DRIVE_NAME);
        this.arm = new ArmMarkI(map, ARM_SLIDERS_DRIVE_NAME, ARM_ANGULAR_DRIVE_NAME, INTAKE_ANGULAR_SERVO_NAME, INTAKE_LID_SERVO_NAME);
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
    public void setArmAngularDrive(double power) {
        arm.setArmAngularDrive(power);
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
    public void setArmAngularDriveForRotations(double power, int rotations) {
        arm.setArmAngularDriveForRotations(power, rotations);
    }

    @Override
    public void setLiftDriveForRotations(double power, int rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    @Override
    public void setFrontLeftDriveForMM(double power, int mm) {
        driveTrain.setFrontLeftForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setFrontRightDriveForMM(double power, int mm) {
        driveTrain.setFrontRightForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setRearLeftDriveForMM(double power, int mm) {
        driveTrain.setRearLeftForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setRearRightDriveForMM(double power, int mm) {
        driveTrain.setRearRightForRotations(power, (int) ((mm / MM_PER_DRIVE_ROTATION) + .5));
    }

    @Override
    public void setArmSliderDriveForMM(double power, int mm) {
        arm.setSliderDriveForRotations(power, (int) ((mm / MM_PER_SLIDER_ROTATION) + .5));
    }

    @Override
    public void setArmAngularDriveForMM(double power, int mm) {
        arm.setArmAngularDriveForRotations(power, (int) ((mm / MM_PER_SLIDER_ANGLER_ROTATION) + .5));
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForRotations(power, (int) ((mm / MM_PER_LIFT_ROTATION) + .5));
    }

    @Override
    public void freezeLift() {
        lift.freeze(ROBOT_WEIGHT_LBS);
    }

    @Override
    public void coastLift() {
        lift.coast();
    }

    @Override
    public void freezeArmAngular() {
        arm.freezeArmAngular();
    }

    @Override
    public void coastArmAngular() {
        arm.coastArmAngular();
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
    public void forward(double power, long ms) {
        setFrontLeftDrive(power);
        setFrontRightDrive(power);
        setRearLeftDrive(power);
        setRearRightDrive(power);
        sleep(ms);
    }

    @Override
    public void left(double power, long ms) {
        setFrontLeftDrive(-power);
        setFrontRightDrive(-power);
        setRearLeftDrive(power);
        setRearRightDrive(power);
        sleep(ms);
    }

    @Override
    public void right(double power, long ms) {
        setFrontRightDrive(power);
        setFrontRightDrive(power);
        setRearLeftDrive(-power);
        setRearRightDrive(-power);
        sleep(ms);
    }

    @Override
    public void backward(double power, long ms) {
        setFrontLeftDrive(-power);
        setFrontRightDrive(-power);
        setRearLeftDrive(-power);
        setRearRightDrive(-power);
        sleep(ms);
    }

    @Override
    public void turnLeft(double power, long ms) {
        setFrontLeftDrive(-power);
        setFrontRightDrive(power);
        setRearLeftDrive(-power);
        setRearRightDrive(power);
        sleep(ms);
    }

    @Override
    public void turnRight(double power, long ms) {
        setFrontLeftDrive(power);
        setFrontRightDrive(-power);
        setRearLeftDrive(power);
        setRearRightDrive(power);
        sleep(ms);
    }

    @Override
    public void zeroAll() {
        setFrontLeftDrive(0);
        setFrontRightDrive(0);
        setRearLeftDrive(0);
        setRearRightDrive(0);
        lift.freeze(ROBOT_WEIGHT_LBS);
        arm.freezeArmSliders();
        arm.freezeArmAngular();
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
