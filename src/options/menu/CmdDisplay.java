package options.menu;

import bibtex.data.DataPrint;
import bibtex.data.DataStorage;
import bibtex.syntax.Categories;
import command.line.input.CmdInputStorage;
import command.line.input.bibtex.file.path.FilePathChecker;
import command.line.input.frame.symbol.FrameSymbolChecker;
import command.line.input.lastname.LastNameStorage;
import data.operations.IDataChecker;
import data.operations.IDataPrint;
import data.operations.IDataStorage;
import runtime.command.interpreter.RuntimeCommands;

import java.util.List;
import java.util.Scanner;

import static runtime.command.interpreter.RuntimeCommands.*;

/**
 * Class responsible for displaying menu with program's options
 * and actions that user must undertake in order to use the program.
 *
 * @author dak98
 */
public class CmdDisplay implements IDisplay {
    /**
     * Default options menu for the program.
     * Responsible for getting correct program's input
     * from the user.
     *
     * @param dataToParse
     *         Command line input.
     * @return Correct program's input.
     */
    @Override
    public String menu(String[] dataToParse) {
        dataToParse = isCorrect(dataToParse);

        System.out.println("Welcome to BibTex parser!");

        StringBuilder correctedInput = new StringBuilder();

        correctedInput.append(FilePathMenu(dataToParse[0]));

        correctedInput.append(" " + FrameSymbolMenu(dataToParse[1]));

        for (int i = 2; i < dataToParse.length; i++) {
            correctedInput.append(" " + dataToParse[i]);
        }

        return correctedInput.toString();
    }

    /**
     * Options menu for displaying BibTex file based on
     * user's commands.
     *
     * @param dataStorage
     *         Data from the BibTex file.
     * @param frameSymbol
     *         Symbol of the frame of the record's output.
     * @param cmdInputStorage
     *         User's input to the program.
     */
    public void printMenu(IDataStorage dataStorage, Character frameSymbol, IDataStorage cmdInputStorage){
        System.out.println("The file has been loaded successfully.");

        Scanner scanner = new Scanner(System.in);
        IDataPrint<DataStorage> printer = new DataPrint();
        boolean stop = false;
        while (!stop) {
            System.out.print("Please enter one program's commands [");
            for (RuntimeCommands command : RuntimeCommands.values()) {
                System.out.print(" " + command);
            }
            System.out.println(" ]:");
            String command = scanner.next();
            if (toEnum(command) == listAll) {
                    printer.print((DataStorage) dataStorage, frameSymbol);
            } else if (toEnum(command) == listAuthors) {
                for (LastNameStorage lastName : (List<LastNameStorage>) ((CmdInputStorage) cmdInputStorage).getLastNamesList()) {
                    ((DataPrint) printer).printByName((DataStorage) dataStorage, frameSymbol, lastName.getLastName());
                }
            } else if (toEnum(command) == listCategories) {
                for (Categories category : (List<Categories>) ((CmdInputStorage) cmdInputStorage).getCategoriesList()) {
                    ((DataPrint) printer).printByCategory((DataStorage) dataStorage, frameSymbol, category);
                }
            } else {
                System.out.println("The command in not recognized by the program. The program will now exit");
                    stop = true;
            }
        }
    }

    /**
     * Checks if dataToParse is correct.
     * @param dataToParse
     * @return Array with empty string for indexes
     *         where dataToParse doesn't have values.
     *         dataToParse if all values are present.
     */
    private String[] isCorrect(String[] dataToParse) {
        if (dataToParse == null || dataToParse.length == 0) {
            String[] newDataToParse = {"", ""};
            return newDataToParse;
        } else if (dataToParse.length == 1) {
            String[] newDataToParse = { dataToParse[0], "" };
            return newDataToParse;
        } else {
            return dataToParse;
        }
    }

    /**
     * Responsible for checking if given BibTex's file path is correct and getting
     * a correct one from the user if it is not.
     *
     * @param path
     *         File path given through command line.
     * @return Correct BibTex's file path.
     */
    private String FilePathMenu(String path) {
        IDataChecker filePathChecker = new FilePathChecker();
        /* Reading input */
        Scanner scanner = new Scanner(System.in);
        while (!filePathChecker.check(path)) {
            System.out.println("There was no specified BibTex's file path or it was not correct. " +
                                "In order to use the program You must specify a correct file path:");
            path = scanner.next();
        }
        return path;
    }

    /**
     * Responsible for checking if given symbol of record's output frame is correct
     * and getting a correct one from the user if it is not.
     *
     * @param frameSymbol
     *         Frame symbol given through command line.
     * @return Correct frame symbol.
     */
    private String FrameSymbolMenu(String frameSymbol) {
        IDataChecker frameSymbolChecker = new FrameSymbolChecker();
        /* Reading input */
        Scanner scanner = new Scanner(System.in);
        if (!frameSymbolChecker.check(frameSymbol)) {
            System.out.println("Specified symbol of record's output was not correct. " +
                               "In order to use the program You must specify a correct frame symbol");
            frameSymbol = scanner.next();
        }
        return frameSymbol;
    }
}
