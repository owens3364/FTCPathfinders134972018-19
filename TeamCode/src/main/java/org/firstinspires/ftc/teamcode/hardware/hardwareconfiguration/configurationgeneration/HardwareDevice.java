package org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.configurationgeneration;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Device;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo;
import org.w3c.dom.Element;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class HardwareDevice<E extends Enum> {
    private final E device;
    private final String name;
    private final String port;
    private String bus;

    public HardwareDevice(E device, String name, String port, String bus) {
        this(device, name, port);
        this.bus = bus;
    }

    public HardwareDevice(E device, String name, String port) {
        this.device = device;
        this.name = name;
        this.port = port;
    }

    public Element generateXML() {
        if (device.getClass() == Device.class && bus != null) {
            return XMLUtils.generateHardwareElement((Device) device, name, port, bus);
        }
        if (device.getClass() == Motor.class && bus != null) {
            return XMLUtils.generateHardwareElement((Motor) device, name, port);
        }
        if (device.getClass() == Servo.class) {
            return XMLUtils.generateHardwareElement((Servo) device, name, port);
        }
        return null;
    }

}
