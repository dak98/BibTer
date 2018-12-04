package command.line.input;

import bibtex.syntax.ToEnumConverter;
import command.line.input.bibtex.file.path.FilePathStorage;
import command.line.input.frame.symbol.FrameSymbolParser;
import command.line.input.frame.symbol.FrameSymbolStorage;
import command.line.input.lastname.LastNameParser;
import command.line.input.lastname.LastNameStorage;
import data.operations.IDataParser;

import java.util.StringTokenizer;

/**
 * Class responsible for parsing command line
 * input.
 *
 * @author dak98
 */
public class CmdInputParser implements IDataParser {
    /**
     *
     * @param dataToParse
     *         Command line input to parse. It should contain contain at least
     *         the file path and the frame symbol.
     * @return dataToParse in a version helpful to the program.
     * @throws IllegalArgumentException
     *          Unknown category name.
     */
    @Override
    public CmdInputStorage parse(String dataToParse) {
        /* Tokens */
        StringTokenizer cmdInputTokens = new StringTokenizer(dataToParse, " ");
        IDataParser parser;
        /* Storage */
        CmdInputStorage cmdInputStorage = new CmdInputStorage();
        /* File path */
        cmdInputStorage.setFilePath(new FilePathStorage(cmdInputTokens.nextToken()));
        /* Frame symbol */
        parser = new FrameSymbolParser();
        /* While frame symbol is incorrect */
        cmdInputStorage.setFrameSymbol((FrameSymbolStorage) parser.parse(cmdInputTokens.nextToken()));
        /* If last names and categories are specified */
        if (cmdInputTokens.hasMoreTokens()) {
            /* Parsers */
            ToEnumConverter toCategoryConverter = new ToEnumConverter();
            /* Last names */
            parser = new LastNameParser();
            String token = null;
            boolean tokenIsCategory = false;
            while (cmdInputTokens.countTokens() > 0 && !tokenIsCategory) {
                if (!toCategoryConverter.isCategory(token = cmdInputTokens.nextToken())) {
                    cmdInputStorage.addLastName((LastNameStorage) parser.parse(token));
                } else {
                    tokenIsCategory = true;
                }
            }
            /* If there are any categories. */
            if (tokenIsCategory) {
                cmdInputStorage.addCategory(toCategoryConverter.toCategory(token));
                String category = null;
                while (cmdInputTokens.countTokens() > 0) {
                    try {
                        cmdInputStorage.addCategory(toCategoryConverter.toCategory(category = cmdInputTokens.nextToken()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unknown category name: " + category + ".");
                    }
                }
            }
        }
        return cmdInputStorage;
    }
}