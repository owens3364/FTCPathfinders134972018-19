package org.firstinspires.ftc.teamcode.hardware.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.components.ArmMarkII;
import org.firstinspires.ftc.teamcode.hardware.components.Direction;
import org.firstinspires.ftc.teamcode.hardware.components.LiftMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.MechanumDriveTrainMarkI;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.MechanumDriveOpModeUsageMarkII;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.Robot;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo;
import org.firstinspires.ftc.teamcode.xmlio.DOMSourceTransformer;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public class BotMarkIII extends Robot implements MechanumDriveOpModeUsageMarkII {

    final MechanumDriveTrainMarkI driveTrain;
    final LiftMarkI lift;
    private final ArmMarkII arm;

    private static final String FRONT_LEFT_DRIVE_NAME = "FrontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FrontRightDrive";
    private static final String REAR_LEFT_DRIVE_NAME = "RearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_NAME = "RearRightDrive";
    private static final String LIFT_DRIVE_NAME = "LiftMarkI";
    static final String ARM_SLIDERS_DRIVE_NAME = "SlidersDrive";
    static final String ARM_ANGULAR_DRIVE_NAME = "ArmAngularDrive";
    static final String INTAKE_ANGULAR_SERVO_NAME = "IntakeAngular";

    private static final boolean FRONT_LEFT_DRIVE_IS_PRIMARY = true;
    private static final boolean FRONT_RIGHT_DRIVE_IS_PRIMARY = true;
    private static final boolean REAR_LEFT_DRIVE_IS_PRIMARY = true;
    private static final boolean REAR_RIGHT_DRIVE_IS_PRIMARY = true;
    private static final boolean LIFT_DRIVE_IS_PRIMARY = false;
    static final boolean ARM_SLIDERS_DRIVE_IS_PRIMARY = false;
    static final boolean ARM_ANGULAR_DRIVE_IS_PRIMARY = false;
    static final boolean INTAKE_ANGULAR_SERVO_IS_PRIMARY = false;

    private static final String FRONT_LEFT_DRIVE_PORT = "0";
    private static final String FRONT_RIGHT_DRIVE_PORT = "1";
    private static final String REAR_LEFT_DRIVE_PORT = "2";
    private static final String REAR_RIGHT_DRIVE_PORT = "3";
    private static final String LIFT_DRIVE_PORT = "0";
    static final String ARM_SLIDERS_DRIVE_PORT = "0";
    static final String ARM_ANGULAR_DRIVE_PORT = "1";
    static final String INTAKE_ANGULAR_SERVO_PORT = "0";

    private static final Motor FRONT_LEFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor FRONT_RIGHT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor REAR_LEFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor REAR_RIGHT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor LIFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    static final Motor ARM_SLIDERS_DRIVE_TYPE = Motor.TetrixMotor;
    static final Motor ARM_ANGULAR_DRIVE_TYPE = Motor.TetrixMotor;
    static final Servo INTAKE_ANGULAR_SERVO_TYPE = Servo.Servo;

    private static final String CONFIGURATION_FILE_NAME = "BotMarkIII";

    public BotMarkIII(HardwareMap map) {
        driveTrain = new MechanumDriveTrainMarkI(map,
                FRONT_LEFT_DRIVE_NAME,
                FRONT_RIGHT_DRIVE_NAME,
                REAR_LEFT_DRIVE_NAME,
                REAR_RIGHT_DRIVE_NAME,
                FRONT_LEFT_DRIVE_IS_PRIMARY,
                FRONT_RIGHT_DRIVE_IS_PRIMARY,
                REAR_LEFT_DRIVE_IS_PRIMARY,
                REAR_RIGHT_DRIVE_IS_PRIMARY,
                FRONT_LEFT_DRIVE_PORT,
                FRONT_RIGHT_DRIVE_PORT,
                REAR_LEFT_DRIVE_PORT,
                REAR_RIGHT_DRIVE_PORT,
                FRONT_LEFT_DRIVE_TYPE,
                FRONT_RIGHT_DRIVE_TYPE,
                REAR_LEFT_DRIVE_TYPE,
                REAR_RIGHT_DRIVE_TYPE);
        lift = new LiftMarkI(map,
                LIFT_DRIVE_NAME,
                LIFT_DRIVE_IS_PRIMARY,
                LIFT_DRIVE_PORT,
                LIFT_DRIVE_TYPE);
        arm = new ArmMarkII(map,
                ARM_SLIDERS_DRIVE_NAME,
                ARM_ANGULAR_DRIVE_NAME,
                INTAKE_ANGULAR_SERVO_NAME,
                ARM_SLIDERS_DRIVE_IS_PRIMARY,
                ARM_ANGULAR_DRIVE_IS_PRIMARY,
                INTAKE_ANGULAR_SERVO_IS_PRIMARY,
                ARM_SLIDERS_DRIVE_PORT,
                ARM_ANGULAR_DRIVE_PORT,
                INTAKE_ANGULAR_SERVO_PORT,
                ARM_SLIDERS_DRIVE_TYPE,
                ARM_ANGULAR_DRIVE_TYPE,
                INTAKE_ANGULAR_SERVO_TYPE);
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
        return (int) (mm + .5);
    }

    private int degreesToDriveMM(double degrees) {
        return (int) ((degrees * 1) + .5);
    }

    @Override
    public boolean createConfigurationFile() {
        return createConfigurationFile(CONFIGURATION_FILE_NAME);
    }

    @Override
    public boolean createConfigurationFile(String name) {
        return DOMSourceTransformer.writeDocument(name,
                XMLUtils.generateDocument(CommonREVExpansionHubConfiguration
                                .PRIMARY_REV_EXPANSION_HUB_PORTAL_NAME,
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_REV_EXPANSION_HUB_PORTAL_SERIAL_NUMBER,
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_REV_EXPANSION_HUB_NAME,
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_REV_EXPANSION_HUB_ADDRESS,
                        ElementArrUtils.findPrimaryElements(driveTrain,
                                lift,
                                arm),
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_IMU_NAME,
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_IMU_PORT,
                        CommonREVExpansionHubConfiguration
                                .PRIMARY_IMU_BUS,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_REV_EXPANSION_HUB_NAME,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_REV_EXPANSION_HUB_ADDRESS,
                        ElementArrUtils.findSecondaryElements(driveTrain,
                                lift,
                                arm),
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_NAME,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_PORT,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_BUS));
    }
}
