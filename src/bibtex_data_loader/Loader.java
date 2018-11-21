package bibtex_data_loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Paths;

import command_line_input.bibtex_file_path.FilePathStorage;

/**
 * Class responsible for loading BibTex file data to the program.
 * Uses Byte Streams to covert the data to a single String object.
 *
 * @author dak98
 * @version 1.0.0
 */
public class Loader {
    /**
     * Loads data from a BibTex file to the program.
     *
     * @param path
     *        Path of the BibTex file.
     * @return StringBuilder with the contents of a specified file.
     * @throws IOException
     *          An I/O error has occured.
     * @throws NullPointerException
     *          StringBuilder.append(int).
     */
    public StringBuilder load(String path) throws IOException, NullPointerException {
        BufferedReader in = null;
        StringBuilder input = new StringBuilder();

        try {
            in = Files.newBufferedReader(Paths.get(path));

            String c;
            while ((c = in.readLine()) != null) {
                input.append(c);
            }
        } catch (IOException e) {
            throw new IOException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        return input;
    }
}
