package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.components.Direction;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.GenericMecanumDriveOpModeUsage;
import org.firstinspires.ftc.teamcode.vision.GoldMineralPosition;
import org.firstinspires.ftc.teamcode.vision.VisionData;
import org.firstinspires.ftc.teamcode.vision.VisionUtils;
import org.firstinspires.ftc.teamcode.vision.VuMarkType;

class GenericAutonomous extends LinearOpMode {

    private static final float RED_TARGET_POSITION_X = 0; //TODO: MEASURE THESE
    private static final float RED_TARGET_POSITION_Y = 0;
    private static final float RED_TARGET_POSITION_Z = 0;
    private static final float RED_TARGET_POSITION_A1 = 0;
    private static final float RED_TARGET_POSITION_A2 = 0;
    private static final float RED_TARGET_POSITION_A3 = 0;
    private static final VisionData RED_TARGET_POSITION = new VisionData(
            new VectorF(RED_TARGET_POSITION_X,
                    RED_TARGET_POSITION_Y,
                    RED_TARGET_POSITION_Z),
            new Orientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
            RED_TARGET_POSITION_A1,
            RED_TARGET_POSITION_A2,
            RED_TARGET_POSITION_A3,
            0), VuMarkType.RED_FOOTPRINT);

    private static final float BLUE_TARGET_POSITION_X = 0; //TODO: MEASURE THESE
    private static final float BLUE_TARGET_POSITION_Y = 0;
    private static final float BLUE_TARGET_POSITION_Z = 0;
    private static final float BLUE_TARGET_POSITION_A1 = 0;
    private static final float BLUE_TARGET_POSITION_A2 = 0;
    private static final float BLUE_TARGET_POSITION_A3 = 0;
    private static final VisionData BLUE_TARGET_POSITION = new VisionData(
            new VectorF(BLUE_TARGET_POSITION_X,
                    BLUE_TARGET_POSITION_Y,
                    BLUE_TARGET_POSITION_Z),
            new Orientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
                    BLUE_TARGET_POSITION_A1,
                    BLUE_TARGET_POSITION_A2,
                    BLUE_TARGET_POSITION_A3,
                    0), VuMarkType.BLUE_ROVER);

    VisionUtils vision;

    @Override
    public void runOpMode() {

    }

    void land(GenericMecanumDriveOpModeUsage bot) {
        bot.setLiftDriveForMM(1, -5500); //Lowers bot
        sleep(12000); //Waits for bot to go down
        //Turn out
        bot.setFrontLeftDriveForMM(1, -100);
        bot.setRearLeftDriveForMM(1, -100);
        bot.setFrontRightDriveForMM(1, 100);
        bot.setRearRightDriveForMM(1, 100);
        sleep(750);
        //Backs up
        bot.allMecanumDriveMotors(1, 200);
        sleep(500);
        //Turns normally
        bot.setFrontLeftDriveForMM(1, 100);
        bot.setRearLeftDriveForMM(1, 100);
        bot.setFrontRightDriveForMM(1, -100);
        bot.setRearRightDriveForMM(1, -100);
        sleep(750);
    }

    void approachAllianceVuMark(GenericMecanumDriveOpModeUsage bot, StartPosition startPosition) {
        //Point the back of the robot straight at the wall
        if (startPosition == StartPosition.CRATER) {
            bot.turn(Direction.LEFT, .5, 45);
            bot.freezeAllMecanumDriveMotors();
        } else {
            bot.turn(Direction.RIGHT, .5, 45);
            bot.freezeAllMecanumDriveMotors();
        }
        //Back up until there's only 6 inches to the wall
        bot.allMecanumDriveMotors(-.5, 0); //TODO: Measure this
        bot.freezeAllMecanumDriveMotors();
        //Strafe until near the VuMark
        if (startPosition == StartPosition.CRATER) {
            bot.strafe(Direction.RIGHT, .5, 0); //TODO: Measure this
        } else {
            bot.strafe(Direction.LEFT, .5, 0); //TODO: Measure this
        }
        bot.freezeAllMecanumDriveMotors();
    }

    void setHomePos(GenericMecanumDriveOpModeUsage bot, StartPosition startPosition) {
        setVision();
        VisionData currentPos = onlyValidPosition(vision);
        if (currentPos == null) {
            return;
        }
        Alliance alliance = getAlliance(currentPos);
        if (alliance == null) {
            return;
        }
        VisionData targetPos = alliance == Alliance.RED? RED_TARGET_POSITION : BLUE_TARGET_POSITION;
        correctPosition(bot, targetPos.getX(), targetPos.getY(), targetPos.getZ(),
                currentPos, targetPos);
        correctOrientation(bot, targetPos.getAngle1(), targetPos.getAngle2(), targetPos.getAngle3(),
                currentPos, targetPos);
        goHome(bot, startPosition);
    }

    void sampling(GenericMecanumDriveOpModeUsage bot) {
        setVision();
        switch (vision.getGoldMineralPosition()) {
            case LEFT:
                turnForGlyphPosition(bot, GoldMineralPosition.LEFT);
                backFromGlyphPosition(bot, GoldMineralPosition.LEFT);
            case CENTER:
                forwardForGlyphPosition(bot, GoldMineralPosition.CENTER);
                backFromGlyphPosition(bot, GoldMineralPosition.CENTER);
            case RIGHT:
                turnForGlyphPosition(bot, GoldMineralPosition.RIGHT);
                backFromGlyphPosition(bot, GoldMineralPosition.RIGHT);
        }
    }

    void claiming(GenericMecanumDriveOpModeUsage bot, StartPosition startPosition) {
        setVision();
    }

    void parking(GenericMecanumDriveOpModeUsage bot, StartPosition startPosition) {

    }

    void setVision() {
        if (vision == null) {
            vision = VisionUtils.getInstance(hardwareMap);
        }
    }

    private VisionData onlyValidPosition(VisionUtils vision) {
        VisionData[] positions = vision.checkForTargets();
        int validCount = 0;
        int validIndex = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != null) {
                validIndex = i;
                validCount++;
            }
        }
        return validCount == 1 ? positions[validIndex] : null;
    }

    Alliance getAlliance(VisionData currentPos) {
        VuMarkType vuMarkType = currentPos.getVuMarkType();
        switch (vuMarkType) {
            case RED_FOOTPRINT:
                return Alliance.RED;
            case BLUE_ROVER:
                return Alliance.BLUE;
            default:
                return null;
        }
    }

    private void correctPosition(GenericMecanumDriveOpModeUsage bot,
                                 float targetX, float targetY, float targetZ,
                                 VisionData originalPos, VisionData targetPos) {
        float currentX;
        float currentY;
        float currentZ;
        VisionData currentPos = originalPos;
        while (!currentPos.equals(targetPos)) {
            currentX = currentPos.getX();
            currentY = currentPos.getY();
            currentZ = currentPos.getZ();
            if (currentX < targetX) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentX > targetX) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            if (currentY < targetY) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentY > targetY) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            if (currentZ < targetZ) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentZ > targetZ) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            currentPos = onlyValidPosition(vision);
            if (currentPos == null) {
                return;
            }
        }
    }

    private void correctOrientation(GenericMecanumDriveOpModeUsage bot,
                                    float targetAngle1, float targetAngle2, float targetAngle3,
                                    VisionData originalPos, VisionData targetPos) {
        float currentA1;
        float currentA2;
        float currentA3;
        VisionData currentPos = originalPos;
        while (!currentPos.equals(targetPos)) {
            currentA1 = currentPos.getAngle1();
            currentA2 = currentPos.getAngle2();
            currentA3 = currentPos.getAngle3();
            if (currentA1 < targetAngle1) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentA1 > targetAngle1) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            if (currentA2 < targetAngle2) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentA2 > targetAngle2) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            if (currentA3 < targetAngle3) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            else if (currentA3 > targetAngle3) {
                //TODO: HANDLE THIS POSITIONING STUFF
            }
            currentPos = onlyValidPosition(vision);
            if (currentPos == null) {
                return;
            }
        }
    }

    private void goHome(GenericMecanumDriveOpModeUsage bot, StartPosition startPosition) {
        //TODO: DEFINE THIS
        //GenericRobot will start with the camera facing exactly the alliance VuMark
        //GenericRobot will start with the back exactly 6" away from the alliance VuMark
        //GenericRobot will start with the back edge exactly parallel to the alliance wall
        //GenericRobot will end where it began autonomous mode
        //GenericRobot will end with the rear facing phone camera facing AWAY from the lander
        //GenericRobot will end precisely positioned, not necessarily exactly where it started
    }

    private void turnForGlyphPosition(GenericMecanumDriveOpModeUsage bot,
                                      GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            return;
        }
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ?
                Direction.LEFT : Direction.RIGHT;
        bot.turn(turnDirection, .5, 0); //TODO: Measure this
        bot.freezeAllMecanumDriveMotors();
        bot.allMecanumDriveMotors(.5, 0); //TODO: Measure this
        bot.freezeAllMecanumDriveMotors();
    }

    void forwardForGlyphPosition(GenericMecanumDriveOpModeUsage bot,
                                         GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition != GoldMineralPosition.CENTER) {
            return;
        }
        bot.allMecanumDriveMotors(.5, 0); //TODO: Measure this
        bot.freezeAllMecanumDriveMotors();
    }

    void backFromGlyphPosition(GenericMecanumDriveOpModeUsage bot,
                                       GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            //TODO: Proper turn durations and forward durations
            bot.allMecanumDriveMotors(-.5);
            sleep(500);
            bot.freezeAllMecanumDriveMotors();
            return;
        }
        //TODO: Proper turn durations and forward durations
        bot.allMecanumDriveMotors(-.5);
        sleep(500);
        bot.freezeAllMecanumDriveMotors();
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ?
                Direction.RIGHT : Direction.LEFT;
        bot.turn(turnDirection, .5);
        sleep(500);
        bot.freezeAllMecanumDriveMotors();
    }
}
