package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.errorIO.LogUtils;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIV;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkV;
import org.firstinspires.ftc.teamcode.vision.GoldMineralPosition;
import org.firstinspires.ftc.teamcode.vision.VisionUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

@Autonomous(name = "AutoCraterSix")
public final class AutonomousCraterIterationSix extends GenericAutonomous {

    MecanumDriveOpModeUsageMarkIV bot;

    @Override
    public void runOpMode() {
        try {
            super.runOpMode();
            bot = new BotMarkV(hardwareMap);
            VisionUtils vision = VisionUtils.getInstance(hardwareMap);
            waitForStart();
            GoldMineralPosition goldMineralPosition = vision.getGoldMineralPosition();
            telemetry.addData("Gold Mineral Position", goldMineralPosition.name());
            vision.deactivateVuforiaAndTFOD();
            runAutonomous(goldMineralPosition);
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    private void runAutonomous(GoldMineralPosition goldMineralPosition) {
        switch (goldMineralPosition) {
            case LEFT:
                runAutonomousLeft();
            case CENTER:
                runAutonomousCenter();
            case RIGHT:
                runAutonomousRight();
        }
    }

    private void runAutonomousLeft() {
        runAutonomousLeft0();
        runAutonomousLeft1();
        runAutonomousLeft2();
        runAutonomousLeft3();
        runAutonomousLeft4();
        runAutonomousLeft5();
        runAutonomousLeft6();
        runAutonomousLeft7();
        runAutonomousLeft8();
        bot.zeroAll();
    }

    private void runAutonomousLeft0() {
        bot.setLiftDrive(-1.0);
        sleep(1);
        bot.setArmAngular(1.0, 0.0);
        bot.freezeArmSliders();
        bot.freezeIntakeDrive();
        sleep(10);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(9618);
        bot.freezeLift();
        sleep(75);
    }

    private void runAutonomousLeft1() {
        bot.driveMotorsBySticks(0.0, 0.10721736401319504, 0.0);
        sleep(46);
        bot.driveMotorsBySticks(0.0, 0.21568629145622253, 0.0);
        sleep(44);
        bot.driveMotorsBySticks(0.0, 0.32415521144866943, 0.0);
        sleep(80);
        bot.driveMotorsBySticks(0.0, 0.6662495136260986, 0.0);
        sleep(23);
        bot.driveMotorsBySticks(0.0, 0.9499374032020569, 0.0);
        sleep(84);
        bot.driveMotorsBySticks(-0.04046725481748581, 1.0, 0.0);
        sleep(26);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(67);
        bot.driveMotorsBySticks(0.0, 0.8331247568130493, 0.0);
        sleep(31);
        bot.driveMotorsBySticks(-0.04046725481748581, 0.35753026604652405, 0.0);
        sleep(68);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(226);
        bot.driveMotorsBySticks(0.0, 0.05715477839112282, 0.0);
        sleep(31);
        bot.driveMotorsBySticks(0.0, 0.2240300476551056, 0.0);
        sleep(81);
    }

    private void runAutonomousLeft2() {
        bot.driveMotorsBySticks(0.0, 0.3491864800453186, 0.0);
        sleep(37);
        bot.driveMotorsBySticks(0.0, 0.3825615346431732, 0.0);
        sleep(50);
        bot.driveMotorsBySticks(0.0, 0.3992490768432617, 0.0);
        sleep(32);
        bot.driveMotorsBySticks(-0.14059241116046906, 0.4242803454399109, 0.0);
        sleep(44);
        bot.driveMotorsBySticks(-0.2991239130496979, 0.43262410163879395, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(-0.2991239130496979, 0.16562369465827942, 0.0);
        sleep(36);
        bot.driveMotorsBySticks(-0.14059241116046906, 0.007092195563018322, 0.0);
        sleep(63);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(96);
        bot.driveMotorsBySticks(0.0, 0.0, -0.6912807822227478);
        sleep(37);
        bot.driveMotorsBySticks(0.0, 0.0, -0.9582811594009399);
        sleep(67);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(261);
        bot.driveMotorsBySticks(0.0, 0.0, -0.8414685130119324);
        sleep(35);
        bot.driveMotorsBySticks(0.0, 0.0, -0.2574051022529602);
        sleep(55);
    }

    private void runAutonomousLeft3() {
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(188);
        bot.driveMotorsBySticks(0.0, 0.24906134605407715, 0.0);
        sleep(29);
        bot.driveMotorsBySticks(0.0, 0.6328744292259216, 0.0);
        sleep(45);
        bot.driveMotorsBySticks(0.0, 0.9916562438011169, 0.0);
        sleep(55);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(313);
        bot.driveMotorsBySticks(-0.09052983671426773, 1.0, 0.0);
        sleep(56);
        bot.driveMotorsBySticks(-0.04046725481748581, 1.0, 0.0);
        sleep(53);
        bot.driveMotorsBySticks(-0.14059241116046906, 1.0, 0.0);
        sleep(80);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(162);
        bot.driveMotorsBySticks(-0.04046725481748581, 1.0, 0.0);
        sleep(211);
        bot.driveMotorsBySticks(-0.09052983671426773, 1.0, 0.0);
        sleep(68);
        bot.driveMotorsBySticks(-0.24906134605407715, 1.0, 0.0);
        sleep(74);
        bot.driveMotorsBySticks(-0.3825615346431732, 1.0, 0.0);
        sleep(41);
        bot.driveMotorsBySticks(-0.3909052908420563, 1.0, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(-0.4242803454399109, 1.0, 0.0);
        sleep(37);
        bot.driveMotorsBySticks(-0.4576554000377655, 1.0, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.4743429720401764, 1.0, 0.0);
        sleep(87);
    }

    private void runAutonomousLeft4() {
        bot.driveMotorsBySticks(-0.48268672823905945, 1.0, 0.0);
        sleep(27);
        bot.driveMotorsBySticks(-0.49937424063682556, 1.0, 0.0);
        sleep(47);
        bot.driveMotorsBySticks(-0.507718026638031, 1.0, 0.0);
        sleep(88);
        bot.driveMotorsBySticks(-0.5160617828369141, 0.8915311098098755, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.5160617828369141, 0.5828118920326233, 0.0);
        sleep(30);
        bot.driveMotorsBySticks(-0.2907801568508148, 0.14059241116046906, 0.0);
        sleep(42);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(402);
        bot.driveMotorsBySticks(0.0, -0.132248654961586, 0.0);
        sleep(44);
        bot.driveMotorsBySticks(0.0, -0.3491864800453186, 0.0);
        sleep(30);
        bot.driveMotorsBySticks(0.0, -0.6662495136260986, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(0.0, -0.8164372444152832, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(0.0, -0.9332498908042908, 0.0);
        sleep(42);
        bot.driveMotorsBySticks(0.0, -0.9749687314033508, 0.0);
        sleep(162);
        bot.driveMotorsBySticks(0.0, -0.7997496724128723, 0.0);
        sleep(54);
        bot.driveMotorsBySticks(0.0, -0.23237381875514984, 0.0);
        sleep(38);
    }

    private void runAutonomousLeft5() {
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(436);
        bot.driveMotorsBySticks(0.0, 0.0, -0.12390488386154175);
        sleep(46);
        bot.driveMotorsBySticks(0.0, 0.0, -0.26574885845184326);
        sleep(34);
        bot.driveMotorsBySticks(0.0, 0.0, -0.35753026604652405);
        sleep(54);
        bot.driveMotorsBySticks(0.0, 0.0, -0.37421777844429016);
        sleep(25);
        bot.driveMotorsBySticks(0.0, 0.0, -0.4075928330421448);
        sleep(236);
        bot.driveMotorsBySticks(0.0, 0.0, -0.4409678876399994);
        sleep(68);
        bot.driveMotorsBySticks(0.0, 0.0, -0.49937424063682556);
        sleep(28);
        bot.driveMotorsBySticks(0.0, 0.0, -0.5994994044303894);
        sleep(28);
        bot.driveMotorsBySticks(0.0, 0.0, -0.7914059162139893);
        sleep(39);
        bot.driveMotorsBySticks(0.0, 0.0, -0.8331247568130493);
        sleep(37);
        bot.driveMotorsBySticks(0.0, 0.0, -0.8414685130119324);
        sleep(110);
        bot.driveMotorsBySticks(0.0, 0.0, -0.7747184038162231);
        sleep(46);
        bot.driveMotorsBySticks(0.0, 0.0, -0.7663746476173401);
        sleep(42);
        bot.driveMotorsBySticks(0.0, 0.0, -0.749687135219574);
        sleep(38);
        bot.driveMotorsBySticks(0.0, 0.0, -0.5828118920326233);
        sleep(43);
    }

    private void runAutonomousLeft6() {
        bot.driveMotorsBySticks(0.0, 0.0, -0.2240300476551056);
        sleep(28);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(210);
        bot.driveMotorsBySticks(0.0, 0.007092195563018322, 0.0);
        sleep(69);
        bot.driveMotorsBySticks(0.0, 0.30746766924858093, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(0.0, 0.9499374032020569, 0.0);
        sleep(20);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(450);
        bot.driveMotorsBySticks(0.0, 1.0, -0.16562369465827942);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 1.0, -0.758030891418457);
        sleep(25);
        bot.driveMotorsBySticks(0.0, 1.0, -0.9749687314033508);
        sleep(29);
        bot.driveMotorsBySticks(0.0, 1.0, -1.0);
        sleep(155);
        bot.driveMotorsBySticks(0.0, 1.0, -0.716312050819397);
        sleep(86);
        bot.driveMotorsBySticks(0.0, 1.0, -0.2240300476551056);
        sleep(26);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(263);
        bot.driveMotorsBySticks(0.0, 1.0, -0.19065499305725098);
        sleep(34);
        bot.driveMotorsBySticks(0.0, 1.0, -0.26574885845184326);
        sleep(32);
    }

    private void runAutonomousLeft7() {
        bot.driveMotorsBySticks(0.0, 1.0, -0.2907801568508148);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 1.0, -0.32415521144866943);
        sleep(41);
        bot.driveMotorsBySticks(0.0, 1.0, -0.4743429720401764);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 1.0, -0.9249061346054077);
        sleep(50);
        bot.driveMotorsBySticks(0.0, 1.0, -1.0);
        sleep(144);
        bot.driveMotorsBySticks(0.0, 1.0, -0.8247810006141663);
        sleep(39);
        bot.driveMotorsBySticks(0.0, 1.0, -0.507718026638031);
        sleep(44);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(248);
        bot.driveMotorsBySticks(0.0, 1.0, -0.5828118920326233);
        sleep(47);
        bot.driveMotorsBySticks(0.0, 1.0, -0.8915311098098755);
        sleep(39);
        bot.driveMotorsBySticks(0.0, 1.0, -1.0);
        sleep(116);
        bot.driveMotorsBySticks(0.0, 1.0, -0.758030891418457);
        sleep(34);
        bot.driveMotorsBySticks(0.0, 1.0, -0.23237381875514984);
        sleep(36);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(259);
        bot.driveMotorsBySticks(0.0, 1.0, -0.10721736401319504);
        sleep(30);
        bot.driveMotorsBySticks(0.0, 1.0, -0.8498122692108154);
        sleep(44);
        bot.driveMotorsBySticks(0.0, 1.0, -1.0);
        sleep(186);
        bot.driveMotorsBySticks(0.0, 1.0, -0.8331247568130493);
        sleep(48);
    }

    private void runAutonomousLeft8() {
        bot.driveMotorsBySticks(0.0, 1.0, -0.10721736401319504);
        sleep(30);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(276);
        bot.driveMotorsBySticks(0.0, 1.0, -0.37421777844429016);
        sleep(48);
        bot.driveMotorsBySticks(0.0, 1.0, -0.7747184038162231);
        sleep(36);
        bot.driveMotorsBySticks(0.0, 1.0, -0.9833124876022339);
        sleep(31);
        bot.driveMotorsBySticks(0.0, 1.0, -1.0);
        sleep(89);
        bot.driveMotorsBySticks(0.0, 1.0, -0.3491864800453186);
        sleep(51);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(386);
        bot.driveMotorsBySticks(0.0, 1.0, -0.2991239130496979);
        sleep(34);
        bot.driveMotorsBySticks(0.0, 1.0, -0.7329996228218079);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 1.0, -0.9916562438011169);
        sleep(57);
        bot.driveMotorsBySticks(0.0, 1.0, -0.7747184038162231);
        sleep(42);
        bot.driveMotorsBySticks(0.0, 1.0, -0.14059241116046906);
        sleep(29);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(2166);
        bot.driveMotorsBySticks(0.0, 0.8164372444152832, 0.0);
        sleep(23);
        bot.driveMotorsBySticks(0.0, -0.34084272384643555, 0.0);
        sleep(50);
        bot.driveMotorsBySticks(0.0, -0.9582811594009399, 0.0);
        sleep(31);
        bot.driveMotorsBySticks(0.0, -1.0, 0.0);
        sleep(4351);
        bot.driveMotorsBySticks(0.0, -0.8664997816085815, 0.0);
        sleep(38);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(375);
    }

    private void runAutonomousCenter() {
        runAutonomousCenter0();
        runAutonomousCenter1();
        runAutonomousCenter2();
        runAutonomousCenter3();
        runAutonomousCenter4();
        bot.zeroAll();
    }

    private void runAutonomousCenter0() {
        bot.setLiftDrive(-1.0);
        bot.setArmAngular(1.0, 0.0);
        bot.freezeArmSliders();
        bot.freezeIntakeDrive();
        sleep(11);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(11261);
        bot.freezeLift();
        sleep(417);
    }

    private void runAutonomousCenter1() {
        bot.driveMotorsBySticks(0.0, 0.26574885845184326, 0.0);
        sleep(46);
        bot.driveMotorsBySticks(0.0, 0.6328744292259216, 0.0);
        sleep(24);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(1677);
        bot.driveMotorsBySticks(0.0, 0.6996245384216309, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(123);
        bot.driveMotorsBySticks(0.0, -0.05715477839112282, 0.0);
        sleep(76);
        bot.driveMotorsBySticks(0.0, -0.507718026638031, 0.0);
        sleep(66);
        bot.driveMotorsBySticks(0.0, -1.0, 0.0);
        sleep(614);
        bot.driveMotorsBySticks(0.0, -0.5911556482315063, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(116);
    }

    private void runAutonomousCenter2() {
        bot.driveMotorsBySticks(0.0, 0.0, -0.26574885845184326);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 0.0, -0.7997496724128723);
        sleep(35);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(930);
        bot.driveMotorsBySticks(0.0, 0.0, -0.758030891418457);
        sleep(27);
        bot.driveMotorsBySticks(0.0, 0.132248654961586, 0.0);
        sleep(42);
        bot.driveMotorsBySticks(0.0, 0.6579057574272156, 0.0);
        sleep(54);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(2539);
        bot.driveMotorsBySticks(-0.19065499305725098, 0.6412181854248047, 0.0);
        sleep(41);
        bot.driveMotorsBySticks(-0.19065499305725098, 0.12390488386154175, 0.0);
        sleep(36);
    }

    private void runAutonomousCenter3() {
        bot.driveMotorsBySticks(0.0, 0.0, -0.02377972938120365);
        sleep(80);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(316);
        bot.driveMotorsBySticks(0.0, 0.3909052908420563, -1.0);
        sleep(40);
        bot.driveMotorsBySticks(0.0, 0.8748435378074646, -1.0);
        sleep(47);
        bot.driveMotorsBySticks(0.0, 1.0, -0.9249061346054077);
        sleep(27);
        bot.driveMotorsBySticks(0.0, 1.0, -0.43262410163879395);
        sleep(81);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(414);
    }

    private void runAutonomousCenter4() {
        bot.driveMotorsBySticks(0.0, 0.8915311098098755, -0.41593658924102783);
        sleep(33);
        bot.driveMotorsBySticks(0.0, 0.3825615346431732, -0.8664997816085815);
        sleep(68);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(165);
        bot.driveMotorsBySticks(0.0, 0.19065499305725098, -1.0);
        sleep(33);
        bot.driveMotorsBySticks(0.0, 0.9415936470031738, -0.48268672823905945);
        sleep(50);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(2350);
        bot.driveMotorsBySticks(0.0, 0.758030891418457, 0.0);
        sleep(53);
        bot.driveMotorsBySticks(0.0, -0.04046725481748581, 0.0);
        sleep(30);
        bot.driveMotorsBySticks(0.0, -1.0, 0.0);
        sleep(3656);
    }

    private void runAutonomousRight() {
        runAutonomousRight0();
        runAutonomousRight1();
        runAutonomousRight2();
        runAutonomousRight3();
        runAutonomousRight4();
        runAutonomousRight5();
        runAutonomousRight6();
        runAutonomousRight7();
        bot.zeroAll();
    }

    private void runAutonomousRight0() {
        bot.setLiftDrive(-1.0);
        sleep(1);
        bot.setArmAngular(1.0, 0.0);
        bot.freezeArmSliders();
        bot.freezeIntakeDrive();
        sleep(9);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(11127);
        bot.freezeLift();
        sleep(144);
    }

    private void runAutonomousRight1() {
        bot.driveMotorsBySticks(0.0, 0.02218198194769294, 0.0);
        sleep(47);
        bot.driveMotorsBySticks(0.0, 0.6665697740684209, 0.0);
        sleep(25);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, 0.0);
        sleep(48);
        bot.driveMotorsBySticks(-0.24937463221160616, 1.0, 0.0);
        sleep(78);
        bot.driveMotorsBySticks(-0.25777759457321636, 1.0, 0.0);
        sleep(106);
        bot.driveMotorsBySticks(-0.26631976370481425, 1.0, 0.0);
        sleep(100);
        bot.driveMotorsBySticks(-0.3018808055813338, 1.0, 0.0);
        sleep(51);
        bot.driveMotorsBySticks(-0.4328399856558782, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(-0.3300136390882926, 0.6530150856977492, 0.0);
        sleep(24);
        bot.driveMotorsBySticks(0.0, 0.0, 0.1661319175473217);
        sleep(38);
        bot.driveMotorsBySticks(0.0, 0.0, 0.6263233242185038);
        sleep(45);
        bot.driveMotorsBySticks(0.0, 0.0, 1.0);
        sleep(237);
        bot.driveMotorsBySticks(0.0, 0.0, 0.6802636989741053);
        sleep(79);
        bot.driveMotorsBySticks(0.0, 5.029923790411672E-5, 0.0);
        sleep(37);
        bot.driveMotorsBySticks(0.0, 0.18716361331877351, 0.0);
        sleep(49);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(196);
        bot.driveMotorsBySticks(-0.05399759164285123, 1.0, 0.0);
        sleep(78);
    }

    private void runAutonomousRight2() {
        bot.driveMotorsBySticks(-0.22500125512390756, 1.0, 0.0);
        sleep(23);
        bot.driveMotorsBySticks(-0.2411109366473605, 1.0, 0.0);
        sleep(634);
        bot.driveMotorsBySticks(-0.24937463221160616, 1.0, 0.0);
        sleep(419);
        bot.driveMotorsBySticks(-0.26631976370481425, 1.0, 0.0);
        sleep(85);
        bot.driveMotorsBySticks(0.0, -0.1661319175473217, 0.0);
        sleep(43);
        bot.driveMotorsBySticks(0.0, -0.8248610657247468, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(0.0, -1.0, 0.0);
        sleep(957);
        bot.driveMotorsBySticks(0.0, -0.1593998253601967, -0.001637598712463273);
        sleep(45);
        bot.driveMotorsBySticks(0.0, 0.0, -0.6802636989741053);
        sleep(28);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(1280);
        bot.driveMotorsBySticks(0.0, 0.0023825151601104544, -1.0);
        sleep(77);
        bot.driveMotorsBySticks(0.0, 0.1593998253601967, -0.31111915738799567);
        sleep(32);
        bot.driveMotorsBySticks(-0.009775988848886863, 0.9505640272142593, 0.0);
        sleep(73);
        bot.driveMotorsBySticks(-0.019766226075914384, 1.0, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(-0.08947511535816322, 1.0, 0.0);
        sleep(24);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(31);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(84);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(72);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(45);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(51);
        bot.driveMotorsBySticks(-0.09973687526698782, 1.0, 0.0);
        sleep(65);
    }

    private void runAutonomousRight3() {
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(28);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(80);
        bot.driveMotorsBySticks(-0.12782789113929827, 1.0, 0.0);
        sleep(53);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(89);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(37);
        bot.driveMotorsBySticks(-0.03634932637766042, 1.0, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(-0.08947511535816322, 1.0, 0.0);
        sleep(42);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(37);
        bot.driveMotorsBySticks(-0.14003894570377984, 1.0, 0.0);
        sleep(280);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, 0.0);
        sleep(44);
        bot.driveMotorsBySticks(-0.12782789113929827, 1.0, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(403);
        bot.driveMotorsBySticks(-0.14003894570377984, 1.0, 0.0);
        sleep(79);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(36);
        bot.driveMotorsBySticks(-0.08947511535816322, 1.0, 0.0);
        sleep(82);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(35);
        bot.driveMotorsBySticks(-0.14003894570377984, 1.0, 0.0);
        sleep(368);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(-0.08947511535816322, 1.0, 0.0);
        sleep(92);
        bot.driveMotorsBySticks(-0.13386380015403265, 1.0, 0.0);
        sleep(21);
        bot.driveMotorsBySticks(-0.14003894570377984, 1.0, 0.0);
        sleep(626);
    }

    private void runAutonomousRight4() {
        bot.driveMotorsBySticks(-0.1593998253601967, 1.0, 0.0);
        sleep(57);
        bot.driveMotorsBySticks(-0.26631976370481425, 0.6530150856977492, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.18716361331877351, 0.25777759457321636, -0.14003894570377984);
        sleep(38);
        bot.driveMotorsBySticks(-0.07512676140607155, 0.09973687526698782, -0.8554513578307166);
        sleep(41);
        bot.driveMotorsBySticks(0.0, 0.009775988848886863, -1.0);
        sleep(41);
        bot.driveMotorsBySticks(0.0, 0.0, -1.0);
        sleep(362);
        bot.driveMotorsBySticks(0.0, 0.06203155409826877, -1.0);
        sleep(28);
        bot.driveMotorsBySticks(0.0, 0.5746108323446606, -1.0);
        sleep(44);
        bot.driveMotorsBySticks(-0.001637598712463273, 1.0, -1.0);
        sleep(44);
        bot.driveMotorsBySticks(-0.057944950891750446, 1.0, -1.0);
        sleep(44);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, -0.8554513578307166);
        sleep(27);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, -0.8554513578307166);
        sleep(49);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, -1.0);
        sleep(38);
        bot.driveMotorsBySticks(-0.08947511535816322, 1.0, -1.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, -0.8709553586862206);
        sleep(42);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, -0.11617376239905752);
        sleep(91);
        bot.driveMotorsBySticks(-0.019766226075914384, 1.0, 0.0);
        sleep(65);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(64);
        bot.driveMotorsBySticks(-0.02218198194769294, 1.0, 0.0);
        sleep(23);
        bot.driveMotorsBySticks(-0.001637598712463273, 1.0, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(70);
    }

    private void runAutonomousRight5() {
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(40);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(50);
        bot.driveMotorsBySticks(-0.009775988848886863, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(134);
        bot.driveMotorsBySticks(-0.001637598712463273, 1.0, 0.0);
        sleep(59);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, 0.0);
        sleep(22);
        bot.driveMotorsBySticks(-0.06203155409826877, 1.0, 0.0);
        sleep(47);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(25);
        bot.driveMotorsBySticks(-0.001637598712463273, 1.0, 0.0);
        sleep(41);
        bot.driveMotorsBySticks(-0.02218198194769294, 1.0, 0.0);
        sleep(33);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(41);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(56);
        bot.driveMotorsBySticks(-0.02218198194769294, 1.0, 0.0);
        sleep(18);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(36);
        bot.driveMotorsBySticks(-0.06203155409826877, 1.0, 0.0);
        sleep(137);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(65);
        bot.driveMotorsBySticks(-0.019766226075914384, 1.0, 0.0);
        sleep(55);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(76);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(24);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(53);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, 0.0);
        sleep(33);
        bot.driveMotorsBySticks(-0.019766226075914384, 1.0, 0.0);
        sleep(34);
    }

    private void runAutonomousRight6() {
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(54);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(121);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(65);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(83);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(0.0, 1.0, 0.0);
        sleep(45);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(46);
        bot.driveMotorsBySticks(-0.07062245576845783, 1.0, 0.0);
        sleep(120);
        bot.driveMotorsBySticks(-0.09973687526698782, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(-0.042990926926941064, 1.0, 0.0);
        sleep(70);
        bot.driveMotorsBySticks(-0.02218198194769294, 1.0, 0.0);
        sleep(34);
        bot.driveMotorsBySticks(-0.008195651335511922, 1.0, 0.0);
        sleep(75);
        bot.driveMotorsBySticks(-0.09453636763315476, 1.0, 0.0);
        sleep(65);
        bot.driveMotorsBySticks(-0.039600502205505705, 1.0, 0.0);
        sleep(119);
        bot.driveMotorsBySticks(-0.019766226075914384, 1.0, 0.0);
        sleep(31);
        bot.driveMotorsBySticks(-0.0662573866658569, 1.0, 0.0);
        sleep(36);
        bot.driveMotorsBySticks(-0.008195651335511922, 0.45507607969316055, 0.0);
        sleep(43);
        bot.driveMotorsBySticks(0.0, -0.1661319175473217, 0.0);
        sleep(87);
        bot.driveMotorsBySticks(0.0, -1.0, 0.0);
        sleep(395);
        bot.driveMotorsBySticks(2.382690668232268E-4, -1.0, 0.0);
        sleep(140);
        bot.driveMotorsBySticks(5.654755294433045E-4, -1.0, 0.0);
        sleep(252);
    }

    private void runAutonomousRight7() {
        bot.driveMotorsBySticks(0.008195651335511922, -1.0, 0.0);
        sleep(55);
        bot.driveMotorsBySticks(0.0662573866658569, -1.0, 0.0);
        sleep(29);
        bot.driveMotorsBySticks(0.11055556348668816, -1.0, 0.0);
        sleep(42);
        bot.driveMotorsBySticks(0.11617376239905752, -1.0, 0.0);
        sleep(625);
        bot.driveMotorsBySticks(0.07512676140607155, -1.0, 0.0);
        sleep(27);
        bot.driveMotorsBySticks(0.07062245576845783, -1.0, 0.0);
        sleep(101);
        bot.driveMotorsBySticks(0.06203155409826877, -1.0, 0.0);
        sleep(323);
        bot.driveMotorsBySticks(0.05018946225234888, -1.0, 0.0);
        sleep(129);
        bot.driveMotorsBySticks(0.046520576322138574, -1.0, 0.0);
        sleep(76);
        bot.driveMotorsBySticks(0.03634932637766042, -1.0, 0.0);
        sleep(101);
        bot.driveMotorsBySticks(0.033237381651529896, -1.0, 0.0);
        sleep(180);
        bot.driveMotorsBySticks(0.03026467914237352, -1.0, 0.0);
        sleep(260);
        bot.driveMotorsBySticks(0.027431208232258975, -1.0, 0.0);
        sleep(1111);
        bot.driveMotorsBySticks(0.024736979041791507, -1.0, 0.0);
        sleep(294);
        bot.driveMotorsBySticks(0.015352420244742149, -1.0, 0.0);
        sleep(46);
        bot.driveMotorsBySticks(0.0, -0.20188095328919875, 0.0);
        sleep(39);
        bot.driveMotorsBySticks(0.0, 0.0, 0.0);
        sleep(210);
    }
}
