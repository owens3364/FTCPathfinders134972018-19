package org.firstinspires.ftc.teamcode.hardware.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.RobotComponents.ArmMarkII;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.Direction;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.LiftMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotComponents.MechanumDriveTrainMarkI;
import org.firstinspires.ftc.teamcode.hardware.RobotInterfaces.MechanumDriveOpModeUsageMarkII;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class BotMarkIII implements MechanumDriveOpModeUsageMarkII {

    private final MechanumDriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final ArmMarkII arm;

    private static final String FRONT_LEFT_DRIVE_NAME = "FrontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FrontRightDrive";
    private static final String REAR_LEFT_DRIVE_NAME = "RearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_NAME = "RearRightDrive";
    private static final String LIFT_DRIVE_NAME = "LiftMarkI";
    private static final String ARM_SLIDERS_DRIVE_NAME = "SlidersDrive";
    private static final String ARM_ANGULAR_DRIVE_NAME = "ArmAngularDrive";
    private static final String INTAKE_ANGULAR_SERVO_NAME = "IntakeAngular";

    public BotMarkIII(HardwareMap map) {
        this.driveTrain = new MechanumDriveTrainMarkI(map,
                FRONT_LEFT_DRIVE_NAME,
                FRONT_RIGHT_DRIVE_NAME,
                REAR_LEFT_DRIVE_NAME,
                REAR_RIGHT_DRIVE_NAME);
        this.lift = new LiftMarkI(map, LIFT_DRIVE_NAME);
        this.arm = new ArmMarkII(map,
                ARM_SLIDERS_DRIVE_NAME,
                ARM_ANGULAR_DRIVE_NAME,
                INTAKE_ANGULAR_SERVO_NAME);
    }

    @Override
    public void driveMotorsBySticks(double leftRight, double forwardBackward, double turn) {
        driveTrain.driveMotorsBySticks(leftRight, forwardBackward, turn);
    }

    @Override
    public void setFrontLeftDrive(double power) {
        driveTrain.setFrontLeft(power);
    }

    @Override
    public double getFrontLeftDrivePower() {
        return driveTrain.getFrontLeft();
    }

    @Override
    public void setFrontRightDrive(double power) {
        driveTrain.setFrontRight(power);
    }

    @Override
    public double getFrontRightDrivePower() {
        return driveTrain.getFrontRight();
    }

    @Override
    public void setRearLeftDrive(double power) {
        driveTrain.setRearLeft(power);
    }

    @Override
    public double getRearLeftDrivePower() {
        return driveTrain.getRearLeft();
    }

    @Override
    public void setRearRightDrive(double power) {
        driveTrain.setRearRight(power);
    }

    @Override
    public double getRearRightDrivePower() {
        return driveTrain.getRearRight();
    }

    @Override
    public void setArmSliderDrive(double power) {
        arm.setSliderDrive(power);
    }

    @Override
    public double getArmSliderDrivePower() {
        return arm.getSliderDrive();
    }

    @Override
    public void setArmAngularDrive(double power) {
        arm.setArmAngularDrive(power);
    }

    @Override
    public double getArmAngularDrivePower() {
        return arm.getArmAngularDrive();
    }

    @Override
    public void setIntakeAngle(double angle) {
        arm.setIntakeAngle(angle);
    }

    @Override
    public double getIntakeAngle() {
        return arm.getIntakeAngle();
    }

    @Override
    public void setLiftDrive(double power) {
        lift.setCableDrive(power);
    }

    @Override
    public double getLiftDrivePower() {
        return lift.getCableDrive();
    }

    @Override
    public void setFrontLeftDriveForRotations(double power, double rotations) {
        driveTrain.setFrontLeftForRotations(power, rotations);
    }

    @Override
    public void setFrontRightDriveForRotations(double power, double rotations) {
        driveTrain.setFrontRightForRotations(power, rotations);
    }

    @Override
    public void setRearLeftDriveForRotations(double power, double rotations) {
        driveTrain.setRearLeftForRotations(power, rotations);
    }

    @Override
    public void setRearRightDriveForRotations(double power, double rotations) {
        driveTrain.setRearRightForRotations(power, rotations);
    }

    @Override
    public void setArmSliderDriveForRotations(double power, double rotations) {
        arm.setSliderDriveForRotations(power, rotations);
    }

    @Override
    public void setArmAngularDriveForRotations(double power, double rotations) {
        arm.setArmAngularDriveForRotations(power, rotations);
    }

    @Override
    public void setLiftDriveForRotations(double power, double rotations) {
        lift.setCableDriveForRotations(power, rotations);
    }

    @Override
    public void setFrontLeftDriveForMM(double power, int mm) {
        driveTrain.setFrontLeftForMM(power, mm);
    }

    @Override
    public void setFrontRightDriveForMM(double power, int mm) {
        driveTrain.setFrontRightForMM(power, mm);
    }

    @Override
    public void setRearLeftDriveForMM(double power, int mm) {
        driveTrain.setRearLeftForMM(power, mm);
    }

    @Override
    public void setRearRightDriveForMM(double power, int mm) {
        driveTrain.setRearRightForMM(power, mm);
    }

    @Override
    public void setArmSliderDriveForMM(double power, int mm) {
        arm.setSliderDriveForMM(power, mm);
    }

    @Override
    public void setArmAngularDriveForMM(double power, int mm) {
        arm.setArmAngularDriveForMM(power, mm);
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForMM(power, mm);
    }

    @Override
    public void freezeLift() {
        lift.freeze();
    }

    @Override
    public boolean getLiftFrozen() {
        return lift.liftFrozen();
    }

    @Override
    public void coastLift() {
        lift.coast();
    }

    @Override
    public boolean getLiftCoasting() {
        return lift.liftCoasting();
    }

    @Override
    public void freezeArmAngular() {
        arm.freezeArmAngular();
    }

    @Override
    public boolean getArmAngularFrozen() {
        return arm.getArmAngularFrozen();
    }

    @Override
    public void coastArmAngular() {
        arm.coastArmAngular();
    }

    @Override
    public boolean getArmAngularCoasting() {
        return arm.getArmAngularCoasting();
    }

    @Override
    public void freezeArmSliders() {
        arm.freezeArmSliders();
    }

    @Override
    public boolean getArmSlidersFrozen() {
        return arm.getArmSlidersFrozen();
    }

    @Override
    public void coastArmSliders() {
        arm.coastArmSliders();
    }

    @Override
    public boolean getArmSlidersCoasting() {
        return arm.getArmSlidersCoasting();
    }


    @Override
    public void freezeAllMechanumDriveMotors() {
        setFrontLeftDrive(0);
        setFrontRightDrive(0);
        setRearLeftDrive(0);
        setRearRightDrive(0);
    }

    @Override
    public void allMechanumDriveMotors(double power) {
        setFrontLeftDrive(power);
        setFrontRightDrive(power);
        setRearLeftDrive(power);
        setRearRightDrive(power);
    }

    @Override
    public void allMechanumDriveMotors(double power, int mm) {
        setFrontLeftDriveForMM(power, mm);
        setFrontRightDriveForMM(power, mm);
        setRearLeftDriveForMM(power, mm);
        setRearRightDriveForMM(power, mm);
    }

    @Override
    public void allMechanumDriveMotors(double power, double rotations) {
        setFrontLeftDriveForRotations(power, rotations);
        setFrontRightDriveForRotations(power, rotations);
        setRearLeftDriveForRotations(power, rotations);
        setRearRightDriveForRotations(power, rotations);
    }

    @Override
    public void strafe(Direction direction, double power) {
        if (direction == Direction.LEFT) {
            setFrontLeftDrive(power);
            setFrontRightDrive(-power);
            setRearLeftDrive(-power);
            setRearRightDrive(power);
        } else {
            setFrontLeftDrive(-power);
            setFrontRightDrive(power);
            setRearLeftDrive(power);
            setRearRightDrive(-power);
        }
    }

    @Override
    public void strafe(Direction direction, double power, int mm) {
        if (direction == Direction.LEFT) {
            setFrontLeftDriveForMM(power, mmScaledForStrafing(mm));
            setFrontRightDriveForMM(-power, mmScaledForStrafing(mm));
            setRearLeftDriveForMM(-power, mmScaledForStrafing(mm));
            setRearRightDriveForMM(power, mmScaledForStrafing(mm));
        } else {
            setFrontLeftDriveForMM(-power, mmScaledForStrafing(mm));
            setFrontRightDriveForMM(power, mmScaledForStrafing(mm));
            setRearLeftDriveForMM(power, mmScaledForStrafing(mm));
            setRearRightDriveForMM(-power, mmScaledForStrafing(mm));
        }
    }

    @Override
    public void turn(Direction direction, double power) {
        if (direction == Direction.LEFT) {
            setFrontLeftDrive(-power);
            setRearLeftDrive(-power);
            setFrontLeftDrive(power);
            setFrontRightDrive(power);
        } else {
            setFrontLeftDrive(power);
            setRearLeftDrive(power);
            setFrontRightDrive(-power);
            setRearRightDrive(-power);
        }
    }

    @Override
    public void turn(Direction direction, double power, double degrees) {
        if (direction == Direction.LEFT) {
            setFrontLeftDriveForMM(-power, degreesToDriveMM(degrees));
            setRearLeftDriveForMM(-power, degreesToDriveMM(degrees));
            setFrontLeftDriveForMM(power, degreesToDriveMM(degrees));
            setFrontRightDriveForMM(power, degreesToDriveMM(degrees));
        } else {
            setFrontLeftDriveForMM(power, degreesToDriveMM(degrees));
            setRearLeftDriveForMM(power, degreesToDriveMM(degrees));
            setFrontRightDriveForMM(-power, degreesToDriveMM(degrees));
            setRearRightDriveForMM(-power, degreesToDriveMM(degrees));
        }
    }

    @Override
    public void zeroAll() {
        setFrontLeftDrive(0);
        setFrontRightDrive(0);
        setRearLeftDrive(0);
        setRearRightDrive(0);
        lift.freeze();
        arm.freezeArmSliders();
        arm.freezeArmAngular();
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
    }

    private int mmScaledForStrafing(int mm) {
        return (int) ((mm * 1) + .5); //TODO: Measure this
    }

    private int degreesToDriveMM(double degrees) {
        return (int) ((degrees * 1) + .5); //TODO: Measure this
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
