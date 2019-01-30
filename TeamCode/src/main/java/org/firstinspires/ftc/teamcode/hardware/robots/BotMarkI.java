package org.firstinspires.ftc.teamcode.hardware.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.components.DriveTrainMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.HookMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.IntakeMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.LiftMarkI;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.Robot;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.StandardDriveOpModeUsageMarkI;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo;
import org.firstinspires.ftc.teamcode.xmlio.DOMSourceTransformer;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class BotMarkI extends Robot implements StandardDriveOpModeUsageMarkI {

    private final DriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final IntakeMarkI intake;
    private final HookMarkI hook;

    private static final String LEFT_DRIVE_NAME = "LeftDrive";
    private static final String RIGHT_DRIVE_NAME = "RightDrive";
    private static final String LIFT_DRIVE_NAME = "CableDrive";
    private static final String INTAKE_DRIVE_NAME = "IntakeDrive";
    private static final String HOOK_DRIVE_NAME = "hookDrive";
    private static final String HOOK_NAME = "hookServo";

    private static final boolean LEFT_DRIVE_IS_PRIMARY = true;
    private static final boolean RIGHT_DRIVE_IS_PRIMARY = true;
    private static final boolean LIFT_DRIVE_IS_PRIMARY = true;
    private static final boolean INTAKE_DRIVE_IS_PRIMARY = true;
    private static final boolean HOOK_DRIVE_IS_PRIMARY = false;
    private static final boolean HOOK_IS_PRIMARY = false;

    private static final String LEFT_DRIVE_PORT = "0";
    private static final String RIGHT_DRIVE_PORT = "1";
    private static final String LIFT_DRIVE_PORT = "2";
    private static final String INTAKE_DRIVE_PORT = "3";
    private static final String HOOK_DRIVE_PORT = "0";
    private static final String HOOK_PORT = "0";

    private static final Motor LEFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor RIGHT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor LIFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor INTAKE_DRIVE_TYPE = Motor.TetrixMotor;
    private static final Motor HOOK_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Servo HOOK_TYPE = Servo.Servo;

    private static final String CONFIGURATION_FILE_NAME = "BotMarkI";

    public BotMarkI(HardwareMap map) {
        driveTrain = new DriveTrainMarkI(map, LEFT_DRIVE_NAME, RIGHT_DRIVE_NAME,
                LEFT_DRIVE_IS_PRIMARY, RIGHT_DRIVE_IS_PRIMARY, LEFT_DRIVE_PORT, RIGHT_DRIVE_PORT,
                LEFT_DRIVE_TYPE, RIGHT_DRIVE_TYPE);
        lift = new LiftMarkI(map, LIFT_DRIVE_NAME, LIFT_DRIVE_IS_PRIMARY, LIFT_DRIVE_PORT,
                LIFT_DRIVE_TYPE);
        intake = new IntakeMarkI(map, INTAKE_DRIVE_NAME, INTAKE_DRIVE_IS_PRIMARY, INTAKE_DRIVE_PORT,
                INTAKE_DRIVE_TYPE);
        hook = new HookMarkI(map, HOOK_DRIVE_NAME, HOOK_NAME, HOOK_DRIVE_IS_PRIMARY, HOOK_IS_PRIMARY,
                HOOK_DRIVE_PORT, HOOK_PORT, HOOK_DRIVE_TYPE, HOOK_TYPE);
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
                                intake,
                                hook),
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
                                intake,
                                hook),
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_NAME,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_PORT,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_BUS));
    }
}









