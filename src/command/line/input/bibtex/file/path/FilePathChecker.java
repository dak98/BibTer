package command.line.input.bibtex.file.path;

import data.operations.IDataChecker;

import java.io.File;

public class FilePathChecker implements IDataChecker {
    /**
     *
     * @param path
     *         Path of file to be checked.
     * @return True if file path is correct (file exists).
     *         False otherwise.
     */
    @Override
    public boolean check(String path) {
        if (isNull(path) || path.equals("")) {
            return false;
        }

        File f = new File(path);
        return (f.isFile() && !f.isDirectory());
    }
}
