package command.line.input;

import bibtex.syntax.Categories;
import command.line.input.bibtex.file.path.FilePathStorage;
import command.line.input.frame.symbol.FrameSymbolStorage;
import command.line.input.lastname.LastNameStorage;
import data.operations.IDataStorage;

import java.util.LinkedList;
import java.util.List;

/**
 * Container class for storing input from command line.
 *
 * Implements {@link data.operations.IDataStorage}.
 *
 * @author dak98
 */
public class CmdInputStorage implements IDataStorage {
    private FilePathStorage filePath;
    private FrameSymbolStorage frameSymbol;
    private List lastNamesList = new LinkedList<LastNameStorage>();
    private List categoriesList = new LinkedList<Categories>();

    /**
     *
     * @return Path of the BibTex's file.
     */
    public String getFilePath() {
        return filePath.getFilePath();
    }

    /**
     *
     * @param filePath Path of the BibTex's file to set.
     */
    public void setFilePath(FilePathStorage filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return Symbol of the frame of record's output.
     */
    public Character getFrameSymbol() {
        return frameSymbol.getFrameSymbol();
    }

    /**
     *
     * @param frameSymbol Symbol of the frame of record's output to set.
     */
    public void setFrameSymbol(FrameSymbolStorage frameSymbol) {
        this.frameSymbol = frameSymbol;
    }

    /**
     *
     * @param lastName Single last name given through command line.
     */
    public void addLastName(LastNameStorage lastName) {
        this.lastNamesList.add(lastName);
    }

    /**
     *
     * @return List of last names given through command line.
     */
    public List getLastNamesList() {
        return this.lastNamesList;
    }


    /**
     *
     * @param category Single category given through command line.
     */
    public void addCategory(Categories category) {
        this.categoriesList.add(category);
    }

    /**
     *
     * @return List of categories given through command line.
     */
    public List getCategoriesList() {
        return this.categoriesList;
    }


}
