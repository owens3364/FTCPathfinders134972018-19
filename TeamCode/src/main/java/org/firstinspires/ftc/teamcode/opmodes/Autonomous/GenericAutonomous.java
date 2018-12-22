package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;
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

    private VisionUtils vision;

    @Override
    public void runOpMode() {

    }

    void land(MechanumDriveOpModeUsageMarkII bot) {
        bot.setLiftDrive(1);
        sleep(4000); //TODO: Time this right
        bot.freezeLift();
    }

    void approachAllianceVuMark(MechanumDriveOpModeUsageMarkII bot, StartPosition startPosition) {
        if (startPosition == StartPosition.CRATER) {
            //Turn 45 degrees to the left so the back is pointing straight at the wall
            turn(bot, Direction.LEFT, .5);
            sleep(750); //TODO: Time this right
            freeze(bot);
        } else {
            //Turn 45 degrees to the right so the back is pointing straight at the wall
            turn(bot, Direction.RIGHT, .5);
            sleep(750); //TODO: Time this right
            freeze(bot);
        }
        //Back up until there's only 6 inches to the wall
        allDriveMotors(bot, -.5);
        sleep(2500); //TODO: Time this right
        freeze(bot);
        if (startPosition == StartPosition.CRATER) {
            //Strafe to the right until near the VuMark
            strafe(bot, Direction.RIGHT, .5);
        } else {
            //Strafe to the left until near the VuMark
            strafe(bot, Direction.LEFT, .5);
        }
        sleep(750); //TODO: Time this right
        freeze(bot);
    }

    void setHomePos(MechanumDriveOpModeUsageMarkII bot, StartPosition startPosition) {
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

    void sampling(MechanumDriveOpModeUsageMarkII bot) {
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

    void claiming(MechanumDriveOpModeUsageMarkII bot, StartPosition startPosition) {
        setVision();
    }

    void parking(MechanumDriveOpModeUsageMarkII bot, StartPosition startPosition) {

    }

    void turn(MechanumDriveOpModeUsageMarkII bot, Direction direction, double power) {
        if (direction == Direction.LEFT) {
            bot.setFrontLeftDrive(-power);
            bot.setRearLeftDrive(-power);
            bot.setFrontLeftDrive(power);
            bot.setFrontRightDrive(power);
        } else {
            bot.setFrontLeftDrive(power);
            bot.setRearLeftDrive(power);
            bot.setFrontRightDrive(-power);
            bot.setRearRightDrive(-power);
        }
    }

    void strafe(MechanumDriveOpModeUsageMarkII bot, Direction direction, double power) {
        if (direction == Direction.LEFT) {
            bot.setFrontLeftDrive(power);
            bot.setFrontRightDrive(-power);
            bot.setRearLeftDrive(-power);
            bot.setRearRightDrive(power);
        } else {
            bot.setFrontLeftDrive(-power);
            bot.setFrontRightDrive(power);
            bot.setRearLeftDrive(power);
            bot.setRearRightDrive(-power);
        }
    }

    void allDriveMotors(MechanumDriveOpModeUsageMarkII bot, double power) {
        bot.setFrontLeftDrive(power);
        bot.setRearLeftDrive(power);
        bot.setFrontRightDrive(power);
        bot.setRearRightDrive(power);
    }

    void freeze(MechanumDriveOpModeUsageMarkII bot) {
        bot.setFrontLeftDrive(0);
        bot.setRearLeftDrive(0);
        bot.setFrontRightDrive(0);
        bot.setRearRightDrive(0);
    }

    private void setVision() {
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

    private void correctPosition(MechanumDriveOpModeUsageMarkII bot,
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

    private void correctOrientation(MechanumDriveOpModeUsageMarkII bot,
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

    private void goHome(MechanumDriveOpModeUsageMarkII bot, StartPosition startPosition) {
        //TODO: DEFINE THIS
        //Robot will start with the camera facing exactly the alliance VuMark
        //Robot will start with the back exactly 6" away from the alliance VuMark
        //Robot will start with the back edge exactly parallel to the alliance wall
    }

    private void turnForGlyphPosition(MechanumDriveOpModeUsageMarkII bot, GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            return;
        }
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ? Direction.LEFT : Direction.RIGHT;
        //TODO: Proper turn durations and forward durations
        turn(bot, turnDirection, .5);
        sleep(500);
        freeze(bot);
        allDriveMotors(bot, .5);
        sleep(500);
        freeze(bot);
    }

    private void forwardForGlyphPosition(MechanumDriveOpModeUsageMarkII bot, GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition != GoldMineralPosition.CENTER) {
            return;
        }
        //TODO: Proper turn durations and forward durations
        allDriveMotors(bot, .5);
        sleep(500);
        freeze(bot);
    }

    private void backFromGlyphPosition(MechanumDriveOpModeUsageMarkII bot, GoldMineralPosition goldMineralPosition) {
        if (goldMineralPosition == GoldMineralPosition.CENTER) {
            //TODO: Proper turn durations and forward durations
            allDriveMotors(bot, -.5);
            sleep(500);
            freeze(bot);
            return;
        }
        //TODO: Proper turn durations and forward durations
        allDriveMotors(bot, -.5);
        sleep(500);
        freeze(bot);
        Direction turnDirection = goldMineralPosition == GoldMineralPosition.LEFT ? Direction.RIGHT : Direction.LEFT;
        turn(bot, turnDirection, .5);
        sleep(500);
        freeze(bot);
    }
}
