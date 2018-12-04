package bibtex.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Class responsible for loading BibTex file data to the program.
 * Uses Byte Streams to covert the data to a single String object.
 *
 * @author dak98
 */
public class DataLoader {
    /**
     * Loads data from a BibTex file to the program.
     *
     * @param path
     *        Path of the BibTex file contained in (@link FilePathStorage)
     * @return StringBuilder with the contents of a specified file.
     */
    public StringBuilder load(Path path) {
        BufferedReader reader = null;
        StringBuilder input = new StringBuilder();
        try {
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line + " @ ");
            }
        } catch (IOException e) {
            System.out.println("The specified file does not exist. The program will now exit.");
            System.exit(-1);
        } catch(SecurityException e) {
            System.out.println("The calling thread does not have permission to access specified file. The program will now exit.");
            System.exit(-1);
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not close the file. The program will now exit.");
            System.exit(-1);
        }

        return input;
    }
}