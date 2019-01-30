package org.firstinspires.ftc.teamcode.hardware.components;

import org.w3c.dom.Element;

public final class ElementWrapper {
    private final boolean elementPrimary;
    private final boolean elementSecondary;
    private final Element element;

    public ElementWrapper(boolean isPrimary, Element element) {
        this.elementPrimary = isPrimary;
        this.elementSecondary = !isPrimary;
        this.element = element;
    }

    public boolean isElementPrimary() {
        return elementPrimary;
    }

    public boolean isElementSecondary() {
        return elementSecondary;
    }

    public Element element() {
        return element;
    }
}
