package edu.nc.tasks.utils;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Utility to convert the tasklist hashmap
 * to .xml format
 */
public class XMLConverter {

    /**
     * Converts the tasklist to
     * a xml string.
     *
     * @param tasklist the HashMap<Integer, String> object
     *                 containing the tasklist
     * @return the xml string representing the tasklist
     */
    public static String toXML(HashMap<Integer, String> tasklist) {

        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(xml);
        xmlEncoder.writeObject(tasklist);
        xmlEncoder.close();

        return xml.toString();
    }
}
