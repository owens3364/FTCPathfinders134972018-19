package org.firstinspires.ftc.teamcode.hardware.robots.robots;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.components.ElementWrapper;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;

final class ElementArrUtils {
    private static ElementWrapper[] findAllElements(Component ... components) {
        if (components != null) {
            ArrayList<ElementWrapper> elements = new ArrayList<>();
            for (Component component : components) {
                elements.addAll(Arrays.asList(component.getHardwareDeviceElements()));
            }
            return (ElementWrapper[]) elements.toArray();
        }
        return null;
    }

    private static Element[] findSelectElements(boolean primary, ElementWrapper[] elements) {
        if (elements != null) {
            ArrayList<Element> selectElements = new ArrayList<>();
            if (primary) {
                for (ElementWrapper element : elements) {
                    if (element.isElementPrimary()) {
                        selectElements.add(element.element());
                    }
                }
            } else {
                for (ElementWrapper element : elements) {
                    if (element.isElementSecondary()) {
                        selectElements.add(element.element());
                    }
                }
            }
            return (Element[]) selectElements.toArray();
        }
        return null;
    }

    static Element[] findPrimaryElements(Component ... components) {
        return findSelectElements(true, findAllElements(components));
    }

    static Element[] findSecondaryElements(Component ... components) {
        return findSelectElements(false, findAllElements(components));
    }
}
