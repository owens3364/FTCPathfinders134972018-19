package org.firstinspires.ftc.teamcode.hardware.RobotInterfaces;

public interface MechanumDriveOpModeUsageMarkII extends GenericMechanumDriveOpModeUsage {
    //Any data all bots should have is listed here and implemented by class names starting with
    // "Bot"
    void driveMotorsBySticks(double leftRight, double forwardBackward, double turn);
    double getFrontLeftDrivePower();
    double getFrontRightDrivePower();
    double getRearLeftDrivePower();
    double getRearRightDrivePower();
    double  getArmSliderDrivePower();
    double getArmAngularDrivePower();
    double getIntakeAngle();
    double getLiftDrivePower();
    boolean getLiftFrozen();
    boolean getLiftCoasting();
    boolean getArmAngularFrozen();
    boolean getArmAngularCoasting();
    boolean getArmSlidersFrozen();
    boolean getArmSlidersCoasting();
}
