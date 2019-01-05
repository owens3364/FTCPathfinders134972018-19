package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.Direction;
import org.firstinspires.ftc.teamcode.hardware.GenericMechanumDriveOpModeUsage;
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

    void land(GenericMechanumDriveOpModeUsage bot) {
        bot.setLiftDriveForMM(1, -3500); //Lowers bot
        sleep(8000); //Waits for bot to go down
        bot.setRearLeftDriveForMM(1, 100); //Backs up bot so it can be lowered more
        bot.setRearRightDriveForMM(1, 100);
        sleep(2000); //Waits for bot to back up
        bot.setLiftDriveForMM(1, -800); //Lowers bot more
        sleep(1000); //Waits for bot to go down
        bot.setRearLeftDriveForMM(1, 60); //Backs up bot so it can be lowered more
        bot.setRearRightDriveForMM(1, 60);
        sleep(1000); //Waits for bot to go down
        bot.setLiftDriveForMM(1, -4000); //Lowers bot
        sleep(3000); //Waits for bot to go down and lift to go up
        bot.setFrontLeftDriveForMM(.5, 40); //Turns out of lander support
        bot.setFrontRightDriveForMM(.5, -40);
        bot.setRearLeftDriveForMM(.5, 40);
        bot.setRearRightDriveForMM(.5, -40);
        sleep(750); //Waits for turn
        bot.allMechanumDriveMotors(.5, 30); //Backs up bot so hook is out of latch
        sleep(750); //Waits for bot to back up
        bot.setFrontLeftDriveForMM(.5, -70); //Turns back to original orientation
        bot.setFrontRightDriveForMM(.5, 70); //Also turns farther
        bot.setRearLeftDriveForMM(.5, -70); //Original orientation tends angle left
        bot.setRearRightDriveForMM(.5, 70);
    }

    void approachAllianceVuMark(GenericMechanumDriveOpModeUsage bot, StartPosition startPosition) {
        //Point the back of the robot straight at the wall
        if (startPosition == StartPosition.CRATER) {
            bot.turn(Direction.LEFT, .5, 45);
            bot.freezeAllMechanumDriveMotors();
        } else {
            bot.turn(Direction.RIGHT, .5, 45);
            bot.freezeAllMechanumDriveMotors();
        }
        //Back up until there's only 6 inches to the wall
        bot.allMechanumDriveMotors(-.5, 0); //TODO: Measure this
        bot.freezeAllMechanumDriveMotors();
        //Strafe until near the VuMark
        if (startPosition == StartPosition.CRATER) {
            bot.strafe(Direction.RIGHT, .5, 0); //TODO: Measure this
        } else {
            bot.strafe(Direction.LEFT, .5, 0); //TODO: Measure this
        }
        bot.freezeAllMechanumDriveMotors();
    }

    void setHomePos(GenericMechanumDriveOpModeUsage bot, StartPosition startPosition) {
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

    void sampling(GenericMechanumDriveOpModeUsage bot) {
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

    void claiming(GenericMechanumDriveOpModeUsage bot, StartPosition startPosition) {
        setVision();
    }

    void parking(GenericMechanumDriveOpModeUsage bot, StartPosition startPosition) {

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

    private void correctPosition(GenericMechanumDriveOpModeUsage bot,
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

    private void correctOrientation(GenericMechanumDriveOpModeUsage bot,
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

    private void goHome(GenericMechanumDriveOpModeUsage bot, StartPosition startPosition) {
        //TODO: DEFINE THIS
        //Robot will start with the camera facing exactly the alliance VuMark
        //Robot will start with the back exactly 6" away from the alliance VuMark
        //Robot will start with the back edge exactly parallel to the alliance wall
        //Robot will end where it began autonomous mode
        //Robot will end with the rear facing phone camera facing AWAY from the lander
        //Robot will end precisely positioned, not necessarily exactly where it started
    }

    private void turnForGlyphPosition(GenericMechanumDriveOpModeUsage bot,
                                      GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            return;
        }
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ?
                Direction.LEFT : Direction.RIGHT;
        bot.turn(turnDirection, .5, 0); //TODO: Measure this
        bot.freezeAllMechanumDriveMotors();
        bot.allMechanumDriveMotors(.5, 0); //TODO: Measure this
        bot.freezeAllMechanumDriveMotors();
    }

    void forwardForGlyphPosition(GenericMechanumDriveOpModeUsage bot,
                                         GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition != GoldMineralPosition.CENTER) {
            return;
        }
        bot.allMechanumDriveMotors(.5, 0); //TODO: Measure this
        bot.freezeAllMechanumDriveMotors();
    }

    void backFromGlyphPosition(GenericMechanumDriveOpModeUsage bot,
                                       GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            //TODO: Proper turn durations and forward durations
            bot.allMechanumDriveMotors(-.5);
            sleep(500);
            bot.freezeAllMechanumDriveMotors();
            return;
        }
        //TODO: Proper turn durations and forward durations
        bot.allMechanumDriveMotors(-.5);
        sleep(500);
        bot.freezeAllMechanumDriveMotors();
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ?
                Direction.RIGHT : Direction.LEFT;
        bot.turn(turnDirection, .5);
        sleep(500);
        bot.freezeAllMechanumDriveMotors();
    }
}
