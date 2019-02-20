package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.controls.Scaler;
import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class DcMotorServoAdaptation implements Component {
    private final PositionerService positioner;

    private final DcMotorWrapper primaryMotor;
    private final String primaryMotorName;
    private final boolean primaryMotorIsPrimary;
    private final String primaryMotorPort;

    private final DcMotorWrapper[] auxiliaryMotors;
    private final String[] auxiliaryMotorNames;
    private final boolean[] auxiliaryMotorsArePrimary;
    private final String[] auxiliaryMotorPorts;
    private final DcMotorSimple.Direction[] auxiliaryMotorDirections;

    private double min;
    private double max;

    DcMotorServoAdaptation(HardwareMap map, String primaryMotorName,
                           String[] auxiliaryMotorNames, boolean primaryMotorIsPrimary,
                           boolean[] auxiliaryMotorsArePrimary, String primaryMotorPort,
                           String[] auxiliaryMotorPorts, Motor primaryMotorType,
                           Motor[] auxiliaryMotorTypes,
                           DcMotorSimple.Direction primaryMotorDirection,
                           DcMotorSimple.Direction[] auxiliaryMotorDirections,
                           double primaryWheelDiameter, double[] auxiliaryWheelDiameters) {
        primaryMotor = new DcMotorWrapper(map, primaryMotorName, primaryMotorDirection,
                primaryMotorType, primaryWheelDiameter);
        primaryMotor.freezeAtZeroPower();

        this.primaryMotorName = primaryMotorName;
        this.auxiliaryMotorNames = auxiliaryMotorNames;

        this.primaryMotorIsPrimary = primaryMotorIsPrimary;
        this.auxiliaryMotorsArePrimary = auxiliaryMotorsArePrimary;

        this.primaryMotorPort = primaryMotorPort;
        this.auxiliaryMotorPorts = auxiliaryMotorPorts;

        this.auxiliaryMotorDirections = auxiliaryMotorDirections;

        auxiliaryMotors = constructAuxiliaryMotors(map, auxiliaryMotorTypes, auxiliaryWheelDiameters);

        min = 0;
        max = primaryMotor.getMotorType().encoderTicks() / 3;

        positioner = new PositionerService();
        positioner.execute(constructPositionerArgs());
    }



    void setPosition(double power, double position) {
        if (positioner != null) {
            positioner.setPosition(power, Scaler.scale(position,
                    0,
                    1,
                    min,
                    max));
        }
    }

    double getPosition() {
        if (positioner != null) {
            return positioner.getPosition();
        }
        return 0.0;
    }

    double getMin() {
        return min;
    }

    double getMax() {
        return max;
    }

    void setMin(double encoderCount) {
        this.min = encoderCount;
    }

    void setMax(double encoderCount) {
        this.max = encoderCount;
    }

    void killPositioner() {
        if (positioner != null) {
            positioner.releasePosition();
        }
    }

    private DcMotorWrapper[] constructAuxiliaryMotors(HardwareMap map,
                                                      Motor[] auxiliaryMotorTypes,
                                                      double[] auxiliaryWheelDiameters) {
        if (map != null) {
            if (auxiliaryMotorNames != null && auxiliaryMotorsArePrimary != null &&
                    auxiliaryMotorPorts != null && auxiliaryMotorTypes != null &&
            auxiliaryMotorDirections != null && auxiliaryWheelDiameters != null) {
                if (auxiliaryMotorNames.length > 0) {
                    if (auxiliaryMotorNames.length == auxiliaryMotorsArePrimary.length &&
                            auxiliaryMotorsArePrimary.length == auxiliaryMotorPorts.length &&
                            auxiliaryMotorPorts.length == auxiliaryMotorDirections.length &&
                            auxiliaryMotorDirections.length == auxiliaryWheelDiameters.length) {
                        DcMotorWrapper[] auxiliaryMotors = new
                                DcMotorWrapper[auxiliaryMotorNames.length];
                        for (int i = 0; i < auxiliaryMotorNames.length; i++) {
                            auxiliaryMotors[i] = new DcMotorWrapper(map, auxiliaryMotorNames[i],
                                    auxiliaryMotorDirections[i], auxiliaryMotorTypes[i],
                                    auxiliaryWheelDiameters[i]);
                        }
                        return auxiliaryMotors;
                    }
                }
            }
        }
        return null;
    }

    private DcMotorWrapper[] constructPositionerArgs() {
        DcMotorWrapper[] motors;
        if (auxiliaryMotors != null) {
            motors = new DcMotorWrapper[auxiliaryMotors.length + 1];
            motors[0] = primaryMotor;
            System.arraycopy(auxiliaryMotors, 0, motors, 1, motors.length - 1);
        } else {
            motors = new DcMotorWrapper[] {
                    primaryMotor
            };
        }
        return motors;
    }

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        if (auxiliaryMotors == null) {
            return new ElementWrapper[] {
                    new ElementWrapper(primaryMotorIsPrimary,
                            XMLUtils.generateHardwareElement(primaryMotor.getMotorType(),
                                    primaryMotorName, primaryMotorPort))
            };
        }

        ElementWrapper[] elements = new ElementWrapper[auxiliaryMotorPorts.length + 1];
        elements[0] = new ElementWrapper(primaryMotorIsPrimary,
                XMLUtils.generateHardwareElement(primaryMotor.getMotorType(),
                        primaryMotorName, primaryMotorPort));
        for (int i = 0; i < elements.length; i++)  {
            elements[i + 1] = new ElementWrapper(auxiliaryMotorsArePrimary[i],
                    XMLUtils.generateHardwareElement(auxiliaryMotors[i].getMotorType(),
                            auxiliaryMotorNames[i], auxiliaryMotorPorts[i]));
        }
        return elements;
    }
}
