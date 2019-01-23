package org.firstinspires.ftc.teamcode.xmlio;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.firstinspires.ftc.teamcode.errorio.LogUtils;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Device;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class XMLUtils {

    private static final String ROOT_ELEMENT = "Robot";
    private static final String ROOT_ELEMENT_ROBOT_KEY = "type";
    private static final String ROOT_ElEMENT_ROBOT_VALUE = "FirstInspires-FTC";

    private static final String NAME = "name";
    private static final String PORT = "port";
    private static final String BUS = "bus";

    private static final String REV_EXPANSION_HUB_PORTAL = "LynxUsbDevice";
    private static final String[] REV_EXPANSION_HUB_PORTAL_KEYS = {
            NAME,
            "serialNumber",
            "parentModuleAddress"
    };

    private static final String REV_EXPANSION_HUB = "LynxModule";
    private static final String[] REV_EXPANSION_HUB_KEYS = {
            NAME,
            PORT
    };

    private static final String REV_EXPANSION_HUB_EMBEDDED_DEVICE = "LynxEmbeddedIMU";
    private static final String[] REV_EXPANSION_HUB_EMBEDDED_DEVICE_KEYS = {
            NAME,
            PORT,
            BUS
    };

    public static DOMSource generateDocument(String primaryREVExpansionHubPortalName,
                                             String primaryREVExpansionHubPortalSerialNumber,
                                             String primaryREVExpansionHubName,
                                             String primaryREVExpansionHubAddress,
                                             Element[] primaryREVExpansionHubDevices,
                                             String primaryIMUName,
                                             String primaryIMUPort,
                                             String primaryIMUBus,
                                             String secondaryREVExpansionHubName,
                                             String secondaryREVExpansionHubAddress,
                                             Element[] secondaryREVExpansionHubDevices,
                                             String secondaryIMUName,
                                             String secondaryIMUPort,
                                             String secondaryIMUBus) {
        if (primaryREVExpansionHubPortalName == null ||
                primaryREVExpansionHubPortalSerialNumber == null ||
                primaryREVExpansionHubName == null ||
                primaryREVExpansionHubAddress == null ||
                primaryREVExpansionHubDevices == null) {
            return null;
        }
        Document doc = generateBaseDocument();
        if (doc != null) {
            Element root = generateDocumentRoot(doc);
            if (root != null) {
                Element revHubPortal = generateElementWithAttributes(doc,
                        REV_EXPANSION_HUB_PORTAL,
                        REV_EXPANSION_HUB_PORTAL_KEYS,
                        new String[]{
                                primaryREVExpansionHubPortalName,
                                primaryREVExpansionHubPortalSerialNumber,
                                primaryREVExpansionHubAddress
                        });
                if (revHubPortal != null) {
                    Element primaryREVExpansionHub = generateREVExpansionHub(doc,
                            primaryREVExpansionHubName,
                            primaryREVExpansionHubAddress,
                            primaryREVExpansionHubDevices,
                            primaryIMUName,
                            primaryIMUPort,
                            primaryIMUBus);
                    if (primaryREVExpansionHub != null) {
                        revHubPortal.appendChild(primaryREVExpansionHub);

                        Element secondaryREVExpansionHub = generateREVExpansionHub(doc,
                                secondaryREVExpansionHubName,
                                secondaryREVExpansionHubAddress,
                                secondaryREVExpansionHubDevices,
                                secondaryIMUName,
                                secondaryIMUPort,
                                secondaryIMUBus);
                        if (secondaryREVExpansionHub != null) {
                            revHubPortal.appendChild(secondaryREVExpansionHub);
                        }
                        root.appendChild(revHubPortal);
                        doc.appendChild(root);
                        return new DOMSource(doc);
                    }
                }
            }
        }
        return null;
    }

    public static Element generateHardwareElement(Device device,
                                                  String deviceName,
                                                  String devicePort,
                                                  String deviceBus) {
        return generateElementWithAttributes(generateBaseDocument(),
                device.toString(),
                new String[] {
                        NAME,
                        PORT,
                        BUS
                },
                new String[] {
                        deviceName,
                        devicePort,
                        deviceBus
                });
    }

    public static Element generateHardwareElement(Motor motor,
                                                  String motorName,
                                                  String motorPort) {
        return generateElementWithAttributes(generateBaseDocument(),
                motor.toString(),
                new String[] {
                        NAME,
                        PORT
                },
                new String[] {
                        motorName,
                        motorPort
                });
    }

    public static Element generateHardwareElement(Servo servo,
                                                  String servoName,
                                                  String servoPort) {
        return generateElementWithAttributes(generateBaseDocument(),
                servo.toString(),
                new String[] {
                    NAME,
                    PORT
                },
                new String[] {
                        servoName,
                        servoPort
                });
    }

    private static Element generateREVExpansionHub(Document doc,
                                                   String revExpansionHubName,
                                                   String revExpansionHubAddress,
                                                   Element[] revExpansionHubDevices,
                                                   String imuName,
                                                   String imuPort,
                                                   String imuBus) {
        if (doc != null &&
                revExpansionHubName != null &&
                revExpansionHubAddress != null &&
                revExpansionHubDevices != null &&
                imuName != null &&
                imuPort != null &&
                imuBus != null) {
            Element revExpansionHub = generateElementWithAttributes(doc,
                    REV_EXPANSION_HUB,
                    REV_EXPANSION_HUB_KEYS,
                    new String[] {
                            revExpansionHubName,
                            revExpansionHubAddress
                    });
            for (Element element : revExpansionHubDevices) {
                if (element == null) {
                    return null;
                }
                revExpansionHub.appendChild(element);
            }
            revExpansionHub.appendChild(generateElementWithAttributes(doc,
                    REV_EXPANSION_HUB_EMBEDDED_DEVICE,
                    REV_EXPANSION_HUB_EMBEDDED_DEVICE_KEYS,
                    new String[] {
                            imuName,
                            imuPort,
                            imuBus
                    }));
        }
        return null;
    }

    private static Document generateBaseDocument() {
        Document doc;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException pce) {
            LogUtils.logError(pce.getMessage());
            return null;
        }
        return doc;
    }

    private static Element generateDocumentRoot(Document doc) {
        Element robot = doc.createElement(ROOT_ELEMENT);
        robot.setAttribute(ROOT_ELEMENT_ROBOT_KEY, ROOT_ElEMENT_ROBOT_VALUE);
        return robot;
    }

    private static Element generateElementWithAttributes(Document doc,
                                                        String element,
                                                        String[] keys,
                                                        String[] values) {
        if (element != null && keys != null && values != null && keys.length == values.length) {
            Element elemental = doc.createElement(element);
            for (int i = 0; i < keys.length; i++) {
                elemental.setAttribute(keys[i], values[i]);
            }
        }
        return null;
    }

}
