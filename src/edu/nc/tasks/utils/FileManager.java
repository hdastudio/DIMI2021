package edu.nc.tasks.utils;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

/**
 * Utility to work with files
 */
public class FileManager {

    /**
     * Checks if a specified file
     * exists
     *
     * @param path the path to the file
     * @return boolean true if the file exists;
     *                 false if the file doesn't exist
     */
    public static boolean fileExists(String path) {

        return Files.exists(Path.of(path));
    }

    /**
     * Creates the specified file
     * and writes to it
     *
     * @param path the path to the file
     * @param con  the contents to write to the file
     * @throws IOException if an I/O error occurs when
     *                     creating or writing to file
     */
    public static void writeFile(String path, String con) throws IOException {

        Path ofile = Path.of(path);
        ofile = ofile = Files.createFile(ofile);
        Files.writeString(ofile, con, StandardOpenOption.TRUNCATE_EXISTING);

        return;
    }

    /**
     * Truncates the specified existing
     * file and writes to it
     *
     * @param path the path to the file
     * @param con  the contents to write to the file
     * @throws IOException if an I/O error occurs when
     *                     writing to file
     */
    public static void rewriteFile(String path, String con) throws IOException {

        Path ofile = Path.of(path);
        Files.writeString(ofile, con, StandardOpenOption.TRUNCATE_EXISTING);

        return;
    }

    /**
     * Reads an .xml file and converts its contents
     * to a hash map
     *
     * @param path the path to the file
     * @return the hash map representing
     *         the contents of the file
     * @throws IOException if an I/O error occurs when
     *                     writing to file
     */
    public static HashMap<Integer, String> readXML(String path) throws IOException {

        Path file = Path.of(path);
        ByteArrayInputStream databytes;
        databytes = new ByteArrayInputStream(Files.readAllBytes(file));

        XMLDecoder xmlDecoder = new XMLDecoder(databytes);
        return (HashMap<Integer, String>) xmlDecoder.readObject();
    }
}
