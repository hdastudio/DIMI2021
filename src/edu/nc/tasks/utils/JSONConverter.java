package edu.nc.tasks.utils;

import java.util.HashMap;

/**
 * Utility to convert the tasklist hashmap
 * to .json format
 */
public class JSONConverter {

    /**
     * Converts the tasklist to
     * a json string.
     *
     * @param tasklist the HashMap<Integer, String> object
     *                 containing the tasklist
     * @return the json string representing the tasklist
     */
    public static String toJSON(HashMap<Integer, String> tasklist) {

        StringBuilder json = new StringBuilder("{\n");
        tasklist.forEach((key, task) ->
                json.append("\t" + key + ": \"" + task + "\",\n")
        );
        json.append("}");

        return json.toString();
    }
}
