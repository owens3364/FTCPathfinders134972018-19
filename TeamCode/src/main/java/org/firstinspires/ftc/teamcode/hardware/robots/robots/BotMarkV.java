package org.firstinspires.ftc.teamcode.hardware.robots.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autonomousGeneration.AutonomousGenerationUtils;
import org.firstinspires.ftc.teamcode.fileIO.FileIOUtils;
import org.firstinspires.ftc.teamcode.fileIO.Purpose;
import org.firstinspires.ftc.teamcode.hardware.components.ArmMarkIV;
import org.firstinspires.ftc.teamcode.hardware.components.Direction;
import org.firstinspires.ftc.teamcode.hardware.components.LiftMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.MecanumDriveTrainMarkI;
import org.firstinspires.ftc.teamcode.hardware.components.TeamMarkerPositionerMarkI;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.AllMecanumDriveMotors;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.AllMecanumDriveMotorsForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.DriveMotorsBySticks;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetFrontLeftDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetFrontLeftDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetFrontRightDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetFrontRightDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetRearLeftDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetRearLeftDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetRearRightDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.SetRearRightDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.Strafe;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.StrafeForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.Turn;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.TurnForDegrees;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum.TurnForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.instantiations.BotMarkVInstantiation;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.DeployMarker;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.misc.ZeroAll;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.CoastArmSliders;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.CoastIntake;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.FreezeArmSliders;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.FreezeIntake;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetAngularMax;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetAngularMin;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmAngular;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmSliderDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetArmSliderDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetIntakeDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetIntakeDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm.SetIntakeLid;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.lift.CoastLift;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.lift.FreezeLift;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.lift.SetLiftDrive;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.lift.SetLiftDriveForMM;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIV;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.GenericRobot;
import org.firstinspires.ftc.teamcode.xmlIO.DOMSourceTransformer;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

import java.util.LinkedList;
import java.util.List;

public final class BotMarkV extends GenericRobot implements MecanumDriveOpModeUsageMarkIV {

    private final LinkedList<RobotMethod> movements = new LinkedList<>();

    private final MecanumDriveTrainMarkI driveTrain;
    private final LiftMarkI lift;
    private final ArmMarkIV arm;
    private final TeamMarkerPositionerMarkI teamMarkerPositioner;

    private static final String FRONT_LEFT_DRIVE_NAME = "FrontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FrontRightDrive";
    private static final String REAR_LEFT_DRIVE_NAME = "RearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_NAME = "RearRightDrive";
    private static final String LIFT_DRIVE_NAME = "LiftDrive";
    private static final String SLIDER_DRIVE_NAME = "SliderDrive";
    private static final String ANGULAR_DRIVE_NAME = "AngularDrive";
    private static final String INTAKE_DRIVE_NAME = "IntakeDrive";
    private static final String INTAKE_LID_NAME = "IntakeLid";
    private static final String TEAM_MARKER_POSITIONER_NAME = "TeamMarkerPositioner";

    private static final boolean FRONT_LEFT_DRIVE_IS_PRIMARY = true;
    private static final boolean FRONT_RIGHT_DRIVE_IS_PRIMARY = true;
    private static final boolean REAR_LEFT_DRIVE_IS_PRIMARY = true;
    private static final boolean REAR_RIGHT_DRIVE_IS_PRIMARY = true;
    private static final boolean LIFT_DRIVE_IS_PRIMARY = false;
    private static final boolean SLIDER_DRIVE_IS_PRIMARY = false;
    private static final boolean ANGULAR_DRIVE_IS_PRIMARY = false;
    private static final boolean INTAKE_DRIVE_IS_PRIMARY = false;
    private static final boolean INTAKE_LID_IS_PRIMARY = true;
    private static final boolean TEAM_MARKER_POSITIONER_IS_PRIMARY = false;

    private static final String FRONT_LEFT_DRIVE_PORT = "0";
    private static final String FRONT_RIGHT_DRIVE_PORT = "1";
    private static final String REAR_LEFT_DRIVE_PORT = "2";
    private static final String REAR_RIGHT_DRIVE_PORT = "3";
    private static final String LIFT_DRIVE_PORT = "0";
    private static final String SLIDER_DRIVE_PORT = "1";
    private static final String ANGULAR_DRIVE_PORT = "2";
    private static final String INTAKE_DRIVE_PORT = "3";
    private static final String INTAKE_LID_PORT = "0";
    private static final String TEAM_MARKER_POSITIONER_PORT = "0";

    private static final Motor FRONT_LEFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor FRONT_RIGHT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor REAR_LEFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor REAR_RIGHT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor LIFT_DRIVE_TYPE = Motor.NeveRest60Gearmotor;
    private static final Motor SLIDER_DRIVE_TYPE = Motor.TetrixMotor;
    private static final Motor ANGULAR_DRIVE_TYPE = Motor.TetrixMotor;
    private static final Motor INTAKE_DRIVE_TYPE = Motor.TetrixMotor;
    private static final Servo INTAKE_LID_TYPE = Servo.Servo;
    private static final Servo TEAM_MARKER_POSITIONER_TYPE = Servo.Servo;

    private static final String CONFIGURATION_FILE_NAME = "BotMarkV";

    public BotMarkV(HardwareMap map) {
        driveTrain = new MecanumDriveTrainMarkI(map,
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
        arm = new ArmMarkIV(map,
                SLIDER_DRIVE_NAME,
                ANGULAR_DRIVE_NAME,
                INTAKE_DRIVE_NAME,
                INTAKE_LID_NAME,
                SLIDER_DRIVE_IS_PRIMARY,
                ANGULAR_DRIVE_IS_PRIMARY,
                INTAKE_DRIVE_IS_PRIMARY,
                INTAKE_LID_IS_PRIMARY,
                SLIDER_DRIVE_PORT,
                ANGULAR_DRIVE_PORT,
                INTAKE_DRIVE_PORT,
                INTAKE_LID_PORT,
                SLIDER_DRIVE_TYPE,
                ANGULAR_DRIVE_TYPE,
                INTAKE_DRIVE_TYPE,
                INTAKE_LID_TYPE);
        teamMarkerPositioner = new TeamMarkerPositionerMarkI(map,
                TEAM_MARKER_POSITIONER_NAME,
                TEAM_MARKER_POSITIONER_IS_PRIMARY,
                TEAM_MARKER_POSITIONER_PORT,
                TEAM_MARKER_POSITIONER_TYPE);
        addCommand(movements, new BotMarkVInstantiation());
    }

    @Override
    public void driveMotorsBySticks(double leftRight, double forwardBackward, double turn) {
        driveTrain.driveMotorsBySticks(leftRight, forwardBackward, turn);
        addCommand(movements, new DriveMotorsBySticks(new String[] {
                String.valueOf(leftRight),
                String.valueOf(forwardBackward),
                String.valueOf(turn)
        }));
    }

    @Override
    public double getFrontLeftDrive() {
        return driveTrain.getFrontLeft();
    }

    @Override
    public double getFrontRightDrive() {
        return driveTrain.getFrontRight();
    }

    @Override
    public double getRearLeftDrive() {
        return driveTrain.getRearLeft();
    }

    @Override
    public double getRearRightDrive() {
        return driveTrain.getRearRight();
    }

    @Override
    public double getLiftDrive() {
        return lift.getCableDrive();
    }

    @Override
    public double getSliderDrive() {
        return arm.getSliderDrive();
    }

    @Override
    public double getAngular() {
        return arm.getAngular();
    }

    @Override
    public double getIntakeDrive() {
        return arm.getIntakeDrive();
    }

    @Override
    public double getLid() {
        return arm.getIntakeLid();
    }

    @Override
    public void setAngularMin(double encoderCount) {
        arm.setAngularMin(encoderCount);
        addCommand(movements, new SetAngularMin(new String[] {
                String.valueOf(encoderCount)
        }));
    }

    @Override
    public void setAngularMax(double encoderCount) {
        arm.setAngularMax(encoderCount);
        addCommand(movements, new SetAngularMax(new String[] {
                String.valueOf(encoderCount)
        }));
    }

    @Override
    public double getAngularMin() {
        return arm.getAngularMin();
    }

    @Override
    public double getAngularMax() {
        return arm.getAngularMax();
    }

    @Override
    public void setFrontLeftDrive(double power) {
        driveTrain.setFrontLeft(power);
        addCommand(movements, new SetFrontLeftDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setFrontRightDrive(double power) {
        driveTrain.setFrontRight(power);
        addCommand(movements, new SetFrontRightDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setRearLeftDrive(double power) {
        driveTrain.setRearLeft(power);
        addCommand(movements, new SetRearLeftDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setRearRightDrive(double power) {
        driveTrain.setRearRight(power);
        addCommand(movements, new SetRearRightDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setFrontLeftDrive(double power, double rotations) {
        driveTrain.setFrontLeftForRotations(power, rotations);
        addCommand(movements, new SetFrontLeftDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setFrontRightDrive(double power, double rotations) {
        driveTrain.setFrontRightForRotations(power, rotations);
        addCommand(movements, new SetFrontRightDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setRearLeftDrive(double power, double rotations) {
        driveTrain.setRearLeftForRotations(power, rotations);
        addCommand(movements, new SetRearLeftDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setRearRightDrive(double power, double rotations) {
        driveTrain.setRearRightForRotations(power, rotations);
        addCommand(movements, new SetRearRightDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setFrontLeftDriveForMM(double power, int mm) {
        driveTrain.setFrontLeftForMM(power, mm);
        addCommand(movements, new SetFrontLeftDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setFrontRightDriveForMM(double power, int mm) {
        driveTrain.setFrontRightForMM(power, mm);
        addCommand(movements, new SetFrontRightDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setRearLeftDriveForMM(double power, int mm) {
        driveTrain.setRearLeftForMM(power, mm);
        addCommand(movements, new SetRearLeftDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setRearRightDriveForMM(double power, int mm) {
        driveTrain.setRearRightForMM(power, mm);
        addCommand(movements, new SetRearRightDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setLiftDrive(double power) {
        lift.setCableDrive(power);
        addCommand(movements, new SetLiftDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setLiftDrive(double power, double rotations) {
        lift.setCableDriveForRotations(power, rotations);
        addCommand(movements, new SetLiftDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setLiftDriveForMM(double power, int mm) {
        lift.setCableDriveForMM(power, mm);
        addCommand(movements, new SetLiftDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setArmSliderDrive(double power) {
        arm.setSliderDrive(power);
        addCommand(movements, new SetArmSliderDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setArmSliderDrive(double power, double rotations) {
        arm.setSliderDriveForRotations(power, rotations);
        addCommand(movements, new SetArmSliderDrive(new String[] {
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
    public void setArmAngular(double power, double position) {
        arm.setAngular(power, position);
        addCommand(movements, new SetArmAngular(new String[] {
                String.valueOf(power),
                String.valueOf(position)
        }));
    }

    @Override
    public void setIntakeDrive(double power) {
        arm.setIntakeDrive(power);
        addCommand(movements, new SetIntakeDrive(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void setIntakeDrive(double power, double rotations) {
        arm.setIntakeDriveForRotations(power, rotations);
        addCommand(movements, new SetIntakeDrive(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void setIntakeDriveForMM(double power, int mm) {
        arm.setIntakeDriveForMM(power, mm);
        addCommand(movements, new SetIntakeDriveForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void setIntakeLid(double position) {
        arm.setIntakeLid(position);
        addCommand(movements, new SetIntakeLid(new String[] {
                String.valueOf(position)
        }));
    }

    @Override
    public boolean liftDriveCoasting() {
        return lift.liftCoasting();
    }

    @Override
    public boolean liftDriveFrozen() {
        return lift.liftFrozen();
    }

    @Override
    public boolean sliderDriveCoasting() {
        return arm.getSliderDriveCoasting();
    }

    @Override
    public boolean sliderDriveFrozen() {
        return arm.getSliderDriveFrozen();
    }

    @Override
    public boolean intakeDriveCoasting() {
        return arm.getIntakeDriveCoasting();
    }

    @Override
    public boolean intakeDriveFrozen() {
        return arm.getIntakeDriveFrozen();
    }

    @Override
    public void coastLift() {
        lift.coast();
        addCommand(movements, new CoastLift());
    }

    @Override
    public void freezeLift() {
        lift.freeze();
        addCommand(movements, new FreezeLift());
    }

    @Override
    public void coastArmSliders() {
        arm.coastSliderDrive();
        addCommand(movements, new CoastArmSliders());
    }

    @Override
    public void freezeArmSliders() {
        arm.freezeSliderDrive();
        addCommand(movements, new FreezeArmSliders());
    }

    @Override
    public void coastIntake() {
        arm.coastIntakeDrive();
        addCommand(movements, new CoastIntake());
    }

    @Override
    public void freezeIntakeDrive() {
        arm.freezeintakeDrive();
        addCommand(movements, new FreezeIntake());
    }

    @Override
    public void allMecanumDriveMotors(double power) {
        driveTrain.setFrontLeft(power);
        driveTrain.setFrontRight(power);
        driveTrain.setRearLeft(power);
        driveTrain.setRearRight(power);
        addCommand(movements, new AllMecanumDriveMotors(new String[] {
                String.valueOf(power)
        }));
    }

    @Override
    public void allMecanumDriveMotors(double power, double rotations) {
        driveTrain.setFrontLeftForRotations(power, rotations);
        driveTrain.setFrontRightForRotations(power, rotations);
        driveTrain.setRearLeftForRotations(power, rotations);
        driveTrain.setRearRightForRotations(power, rotations);
        addCommand(movements, new AllMecanumDriveMotors(new String[] {
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void allMecanumDriveMotorsForMM(double power, int mm) {
        driveTrain.setFrontLeftForMM(power, mm);
        addCommand(movements, new AllMecanumDriveMotorsForMM(new String[] {
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void strafe(Direction direction, double power) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeft(power);
            driveTrain.setFrontRight(-power);
            driveTrain.setRearLeft(-power);
            driveTrain.setRearRight(power);
        } else {
            driveTrain.setFrontLeft(-power);
            driveTrain.setFrontRight(power);
            driveTrain.setRearLeft(power);
            driveTrain.setRearRight(-power);
        }
        addCommand(movements, new Strafe(new String[] {
                direction.name(),
                String.valueOf(power)
        }));
    }

    @Override
    public void strafe(Direction direction, double power, double rotations) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeftForRotations(power, rotations);
            driveTrain.setFrontRightForRotations(-power, rotations);
            driveTrain.setRearLeftForRotations(-power, rotations);
            driveTrain.setRearRightForRotations(power, rotations);
        } else {
            driveTrain.setFrontLeftForRotations(-power, rotations);
            driveTrain.setFrontRightForRotations(power, rotations);
            driveTrain.setRearLeftForRotations(power, rotations);
            driveTrain.setRearRightForRotations(-power, rotations);
        }
        addCommand(movements, new Strafe(new String[] {
                direction.name(),
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void strafeForMM(Direction direction, double power, int mm) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeftForMM(power, mm);
            driveTrain.setFrontRightForMM(-power, mm);
            driveTrain.setRearLeftForMM(-power, mm);
            driveTrain.setRearRightForMM(power, mm);
        } else {
            driveTrain.setFrontLeftForMM(-power, mm);
            driveTrain.setFrontRightForMM(power, mm);
            driveTrain.setRearLeftForMM(power, mm);
            driveTrain.setRearRightForMM(-power, mm);
        }
        addCommand(movements, new StrafeForMM(new String[] {
                direction.name(),
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void turn(Direction direction, double power) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeft(-power);
            driveTrain.setRearLeft(-power);
            driveTrain.setFrontLeft(power);
            driveTrain.setFrontRight(power);
        } else {
            driveTrain.setFrontLeft(power);
            driveTrain.setRearLeft(power);
            driveTrain.setFrontRight(-power);
            driveTrain.setRearRight(-power);
        }
        addCommand(movements, new Turn(new String[] {
                direction.name(),
                String.valueOf(power)
        }));
    }

    @Override
    public void turn(Direction direction, double power, double rotations) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeftForRotations(-power, rotations);
            driveTrain.setRearLeftForRotations(-power, rotations);
            driveTrain.setFrontLeftForRotations(power, rotations);
            driveTrain.setFrontRightForRotations(power, rotations);
        } else {
            driveTrain.setFrontLeftForRotations(power, rotations);
            driveTrain.setRearLeftForRotations(power, rotations);
            driveTrain.setFrontRightForRotations(-power, rotations);
            driveTrain.setRearRightForRotations(-power, rotations);
        }
        addCommand(movements, new Turn(new String[] {
                direction.name(),
                String.valueOf(power),
                String.valueOf(rotations)
        }));
    }

    @Override
    public void turnForMM(Direction direction, double power, int mm) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeftForMM(-power, mm);
            driveTrain.setRearLeftForMM(-power, mm);
            driveTrain.setFrontLeftForMM(power, mm);
            driveTrain.setFrontRightForMM(power, mm);
        } else {
            driveTrain.setFrontLeftForMM(power, mm);
            driveTrain.setRearLeftForMM(power, mm);
            driveTrain.setFrontRightForMM(-power, mm);
            driveTrain.setRearRightForMM(-power, mm);
        }
        addCommand(movements, new TurnForMM(new String[] {
                direction.name(),
                String.valueOf(power),
                String.valueOf(mm)
        }));
    }

    @Override
    public void turnForDegrees(Direction direction, double power, double degrees) {
        if (direction == Direction.LEFT) {
            driveTrain.setFrontLeftForMM(-power, degreesToDriveMM(degrees));
            driveTrain.setRearLeftForMM(-power, degreesToDriveMM(degrees));
            driveTrain.setFrontLeftForMM(power, degreesToDriveMM(degrees));
            driveTrain.setFrontRightForMM(power, degreesToDriveMM(degrees));
        } else {
            driveTrain.setFrontLeftForMM(power, degreesToDriveMM(degrees));
            driveTrain.setRearLeftForMM(power, degreesToDriveMM(degrees));
            driveTrain.setFrontRightForMM(-power, degreesToDriveMM(degrees));
            driveTrain.setRearRightForMM(-power, degreesToDriveMM(degrees));
        }
        addCommand(movements, new TurnForDegrees(new String[] {
                direction.name(),
                String.valueOf(power),
                String.valueOf(degrees)
        }));
    }

    @Override
    public void deployMarker() {
        teamMarkerPositioner.dump();
        addCommand(movements, new DeployMarker());
    }

    @Override
    public void zeroAll() {
        driveTrain.setFrontLeft(0);
        driveTrain.setFrontRight(0);
        driveTrain.setRearLeft(0);
        driveTrain.setRearRight(0);
        lift.freeze();
        arm.freezeSliderDrive();
        arm.freezeintakeDrive();
        arm.killPositioner();
        addCommand(movements, new ZeroAll());
    }

    @Override
    public Class getKindOfBot() {
        return this.getClass();
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
                        ElementArrUtils.findPrimaryElements(
                                driveTrain,
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
                        ElementArrUtils.findSecondaryElements(
                                driveTrain,
                                lift,
                                arm,
                                teamMarkerPositioner),
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_NAME,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_PORT,
                        CommonREVExpansionHubConfiguration
                                .SECONDARY_IMU_BUS
                        ));
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
