package org.firstinspires.ftc.teamcode.xmlIO;

import org.firstinspires.ftc.teamcode.errorIO.LogUtils;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import org.firstinspires.ftc.teamcode.fileIO.FileIOUtils;
import org.firstinspires.ftc.teamcode.fileIO.Purpose;

public final class DOMSourceTransformer {

    private static final String METHOD = "xml";
    private static final String VERSION = "1.0";
    private static final String ENCODING = "UTF-8";
    private static final String OMIT_XML_DECLARATION = "no";
    private static final String STANDALONE = "yes";
    private static final String INDENT = "yes";

    public static boolean writeDocument(String fileName, DOMSource source) {
        Transformer transformer = getTransformer();
        if (transformer != null) {
            if (assignOutputProperties(transformer)) {
                File path = FileIOUtils.generatePath(FileIOUtils.CONFIGURATION_DIRECTORY);
                if (path != null) {
                    File file = FileIOUtils
                            .generateFile(path, Purpose.CONFIGURATION_FILE, fileName);
                    return FileIOUtils.writeToFile(file, transformer, source);
                }
            }
        }
        return false;
    }

    private static Transformer getTransformer() {
        try {
            return TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            LogUtils.logError(e.getMessage());
            return null;
        }
    }

    private static boolean assignOutputProperties(Transformer transformer) {
        if (transformer == null) {
            return false;
        }
        transformer.setOutputProperty(OutputKeys.METHOD, METHOD);
        transformer.setOutputProperty(OutputKeys.VERSION, VERSION);
        transformer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, OMIT_XML_DECLARATION);
        transformer.setOutputProperty(OutputKeys.STANDALONE, STANDALONE);
        transformer.setOutputProperty(OutputKeys.INDENT, INDENT);
        return true;
    }
}
