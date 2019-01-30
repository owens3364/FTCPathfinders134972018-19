package org.firstinspires.ftc.teamcode.hardware.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.components.ArmMarkIII;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.MechanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.xmlio.DOMSourceTransformer;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class BotMarkIV extends BotMarkIII implements MechanumDriveOpModeUsageMarkIII {

    private final ArmMarkIII arm;

    private static final String SECONDARY_ARM_ANGULAR_DRIVE_NAME = "SecondaryArmAngularDrive";

    private static final boolean SECONDARY_ARM_ANGULAR_DRIVE_IS_PRIMARY = false;

    private static final String SECONDARY_ARM_ANGULAR_DRIVE_PORT = "2";

    private static final Motor SECONDARY_ARM_ANGULAR_DRIVE_TYPE = Motor.TetrixMotor;

    private static final String CONFIGURATION_FILE_NAME = "BotMarkIV";

    public BotMarkIV(HardwareMap map) {
        super(map);
        arm = new ArmMarkIII(map,
                ARM_SLIDERS_DRIVE_NAME, ARM_ANGULAR_DRIVE_NAME, INTAKE_ANGULAR_SERVO_NAME,
                SECONDARY_ARM_ANGULAR_DRIVE_NAME, ARM_SLIDERS_DRIVE_IS_PRIMARY,
                ARM_ANGULAR_DRIVE_IS_PRIMARY, INTAKE_ANGULAR_SERVO_IS_PRIMARY,
                SECONDARY_ARM_ANGULAR_DRIVE_IS_PRIMARY, ARM_SLIDERS_DRIVE_PORT,
                ARM_ANGULAR_DRIVE_PORT, INTAKE_ANGULAR_SERVO_PORT, SECONDARY_ARM_ANGULAR_DRIVE_PORT,
                ARM_SLIDERS_DRIVE_TYPE, ARM_ANGULAR_DRIVE_TYPE, INTAKE_ANGULAR_SERVO_TYPE,
                SECONDARY_ARM_ANGULAR_DRIVE_TYPE);
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
    public void setArmSliderDriveForRotations(double power, double rotations) {
        arm.setSliderDriveForRotations(power, rotations);
    }

    @Override
    public void setArmAngularDriveForRotations(double power, double rotations) {
        arm.setArmAngularDriveForRotations(power, rotations);
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
    public void setSecondaryArmAngularDrive(double power) {
        arm.setSecondaryArmAngularDrive(power);
    }

    @Override
    public double getSecondaryArmAngularDrive() {
        return arm.getSecondaryArmAngularDrive();
    }

    @Override
    public void setSecondaryArmAngularDriveForRotations(double power, double rotations) {
        arm.setSecondaryArmAngularDriveForRotations(power, rotations);
    }

    @Override
    public void setSecondaryArmAngularDriveForMM(double power, int mm) {
        arm.setSecondaryArmAngularDriveForMM(power, mm);
    }

    @Override
    public void freezeSecondaryArmAngularDrive() {
        arm.freezeSecondaryArmAngular();
    }

    @Override
    public boolean getSecondaryArmAngularDriveFrozen() {
        return arm.getSecondaryArmAngularFrozen();
    }

    @Override
    public void coastSecondaryArmAngularDrive() {
        arm.coastSecondaryArmAngular();
    }

    @Override
    public boolean getSecondaryArmAngularDriveCoasting() {
        return arm.getSecondaryArmAngularCoasting();
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
        arm.freezeSecondaryArmAngular();
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
