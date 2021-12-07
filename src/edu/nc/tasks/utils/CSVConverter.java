package edu.nc.tasks.utils;

import java.util.HashMap;

/**
 * Utility to convert the tasklist hashmap
 * to .csv format
 */
public class CSVConverter {

    /**
     * Converts the tasklist to
     * a csv string.
     *
     * @param tasklist the HashMap<Integer, String> object
     *                 containing the tasklist
     * @return the csv string representing the tasklist
     */
    public static String toCSV(HashMap<Integer, String> tasklist) {

        StringBuilder csv = new StringBuilder();
        tasklist.forEach((key, task) ->
                csv.append(key + "," + task + "\n")
        );

        return csv.toString();
    }
}
