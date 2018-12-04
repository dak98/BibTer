package command.line.input.bibtex.file.path;

import data.operations.IDataStorage;

/**
 * Stores path to a BibTex's file
 * given to the program through the
 * command line.
 *
 * @author dak98
 */
public class FilePathStorage implements IDataStorage {
    private String filePath;

    /**
     * Defualt constructor.
     * @param filePath
     */
    public FilePathStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return Path of a file stored in this instance.
     */
    public String getFilePath() {
        return this.filePath;
    }
}
