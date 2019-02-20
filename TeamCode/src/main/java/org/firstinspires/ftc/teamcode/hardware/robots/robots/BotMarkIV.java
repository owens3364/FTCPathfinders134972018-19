package org.firstinspires.ftc.teamcode.hardware.robots.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autonomousGeneration.AutonomousGenerationUtils;
import org.firstinspires.ftc.teamcode.fileIO.FileIOUtils;
import org.firstinspires.ftc.teamcode.fileIO.Purpose;
import org.firstinspires.ftc.teamcode.hardware.components.ArmMarkIII;
import org.firstinspires.ftc.teamcode.hardware.components.TeamMarkerPositionerMarkI;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.instantiations.BotMarkIVInstantiation;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.DeployMarker;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.misc.ZeroAll;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.CoastArmAngular;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.CoastArmSliders;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.CoastSecondaryArmAngularDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.FreezeArmAngular;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.FreezeArmSliders;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.FreezeSecondaryArmAngularDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmAngularDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmAngularDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmAngularDriveForRotations;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmSliderDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmSliderDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmSliderDriveForRotations;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetIntakeAngle;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetSecondaryArmAngularDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetSecondaryArmAngularDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetSecondaryArmAngularDriveForRotations;
import org.firstinspires.ftc.teamcode.xmlIO.DOMSourceTransformer;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

import java.util.LinkedList;
import java.util.List;

public final class BotMarkIV extends BotMarkIII implements MecanumDriveOpModeUsageMarkIII {

    private final LinkedList<RobotMethod> movements = new LinkedList<>();

    private final ArmMarkIII arm;
    private final TeamMarkerPositionerMarkI teamMarkerPositioner;

    private static final String SECONDARY_ARM_ANGULAR_DRIVE_NAME = "SecondaryArmAngularDrive";
    private static final String TEAM_MARKER_POSITIONER_SERVO_NAME = "TeamMarkerPositioner";

    private static final boolean SECONDARY_ARM_ANGULAR_DRIVE_IS_PRIMARY = false;
    private static final boolean TEAM_MARKER_POSITIONER_IS_PRIMARY = true;

    private static final String SECONDARY_ARM_ANGULAR_DRIVE_PORT = "2";
    private static final String TEAM_MARKER_POSITIONER_PORT = "0";

    private static final Motor SECONDARY_ARM_ANGULAR_DRIVE_TYPE = Motor.TetrixMotor;
    private static final Servo TEAM_MARKER_POSITIONER_SERVO_TYPE = Servo.Servo;

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
        teamMarkerPositioner = new TeamMarkerPositionerMarkI(map,
                TEAM_MARKER_POSITIONER_SERVO_NAME,
                TEAM_MARKER_POSITIONER_IS_PRIMARY,
                TEAM_MARKER_POSITIONER_PORT,
                TEAM_MARKER_POSITIONER_SERVO_TYPE);
        addCommand(movements, new BotMarkIVInstantiation());
    }

    @Override
    public void setArmSliderDrive(double power) {
        arm.setSliderDrive(power);
        addCommand(movements, new SetArmSliderDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public double getArmSliderDrivePower() {
        return arm.getSliderDrive();
    }

    @Override
    public void setArmAngularDrive(double power) {
        arm.setArmAngularDrive(power);
        addCommand(movements, new SetArmAngularDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public double getArmAngularDrivePower() {
        return arm.getArmAngularDrive();
    }

    @Override
    public void setIntakeAngle(double angle) {
        arm.setIntakeAngle(angle);
        addCommand(movements, new SetIntakeAngle(new String[] {
                String.valueOf(angle)
        }));
    }

    @Override
    public double getIntakeAngle() {
        return arm.getIntakeAngle();
    }

    @Override
    public void setArmSliderDriveForRotations(double power, double rotations) {
        arm.setSliderDriveForRotations(power, rotations);
        addCommand(movements, new SetArmSliderDriveForRotations(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setArmAngularDriveForRotations(double power, double rotations) {
        arm.setArmAngularDriveForRotations(power, rotations);
        addCommand(movements, new SetArmAngularDriveForRotations(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setArmSliderDriveForMM(double power, int mm) {
        arm.setSliderDriveForMM(power, mm);
        addCommand(movements, new SetArmSliderDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setArmAngularDriveForMM(double power, int mm) {
        arm.setArmAngularDriveForMM(power, mm);
        addCommand(movements, new SetArmAngularDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void freezeArmAngular() {
        arm.freezeArmAngular();
        addCommand(movements, new FreezeArmAngular());
    }

    @Override
    public boolean getArmAngularFrozen() {
        return arm.getArmAngularFrozen();
    }

    @Override
    public void coastArmAngular() {
        arm.coastArmAngular();
        addCommand(movements, new CoastArmAngular());
    }

    @Override
    public boolean getArmAngularCoasting() {
        return arm.getArmAngularCoasting();
    }

    @Override
    public void freezeArmSliders() {
        arm.freezeArmSliders();
        addCommand(movements, new FreezeArmSliders());
    }

    @Override
    public boolean getArmSlidersFrozen() {
        return arm.getArmSlidersFrozen();
    }

    @Override
    public void coastArmSliders() {
        arm.coastArmSliders();
        addCommand(movements, new CoastArmSliders());
    }

    @Override
    public boolean getArmSlidersCoasting() {
        return arm.getArmSlidersCoasting();
    }

    @Override
    public void setSecondaryArmAngularDrive(double power) {
        arm.setSecondaryArmAngularDrive(power);
        addCommand(movements, new SetSecondaryArmAngularDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public double getSecondaryArmAngularDrive() {
        return arm.getSecondaryArmAngularDrive();
    }

    @Override
    public void setSecondaryArmAngularDriveForRotations(double power, double rotations) {
        arm.setSecondaryArmAngularDriveForRotations(power, rotations);
        addCommand(movements, new SetSecondaryArmAngularDriveForRotations(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setSecondaryArmAngularDriveForMM(double power, int mm) {
        arm.setSecondaryArmAngularDriveForMM(power, mm);
        addCommand(movements, new SetSecondaryArmAngularDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void freezeSecondaryArmAngularDrive() {
        arm.freezeSecondaryArmAngular();
        addCommand(movements, new FreezeSecondaryArmAngularDrive());
    }

    @Override
    public boolean getSecondaryArmAngularDriveFrozen() {
        return arm.getSecondaryArmAngularFrozen();
    }

    @Override
    public void coastSecondaryArmAngularDrive() {
        arm.coastSecondaryArmAngular();
        addCommand(movements, new CoastSecondaryArmAngularDrive());
    }

    @Override
    public boolean getSecondaryArmAngularDriveCoasting() {
        return arm.getSecondaryArmAngularCoasting();
    }

    @Override
    public void deployMarker() {
        teamMarkerPositioner.dump();
        addCommand(movements, new DeployMarker());
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
        addCommand(movements, new ZeroAll());
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
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
                                arm,
                                teamMarkerPositioner),
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
                                arm,
                                teamMarkerPositioner),
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_NAME,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_PORT,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_BUS));
    }

    @Override
    public boolean createAutonomous(List<RobotMethod> movements) {
        return FileIOUtils.writeToFile(
                FileIOUtils.generateFile(
                        FileIOUtils.generatePath(FileIOUtils.AUTONOMOUS_GENERATION_DIRECTORY),
                        Purpose.AUTONOMOUS_GENERATION,
                        AUTONOMOUS_GENERATED + FileIOUtils.getSimpleDateFormat()),
                AutonomousGenerationUtils.generateAutonomous(movements));
    }

    @Override
    public LinkedList<RobotMethod> getMovementsForAutonomous() {
        return movements;
    }

    @Override
    public void trackMovementsForAutonomous() {
        movements.clear();
    }

}
