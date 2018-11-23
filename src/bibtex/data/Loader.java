package bibtex.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

/**
 * Class responsible for loading BibTex file data to the program.
 * Uses Byte Streams to covert the data to a single String object.
 *
 * @author dak98
 */
public class Loader {
    /**
     * Loads data from a BibTex file to the program.
     *
     * @param path
     *        Path of the BibTex file contained in (@link command_line_input.bibtex_file_path.FilePathStorage)
     * @return StringBuilder with the contents of a specified file.
     * @throws IOException
     *          An I/O error occurs opening the file.
     * @throws SecurityException
     *          The calling thread does not have permission to access specified file.
     * @throws NullPointerException
     *          The file path argument is null.
     */
    public StringBuilder load(Path path) throws IOException, NullPointerException {
        BufferedReader reader;
        StringBuilder input = new StringBuilder();

        try {
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line);
            }
        } catch (IOException e) {
            throw new IOException();
        } catch(SecurityException e) {
            throw new SecurityException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        return input;
    }
}