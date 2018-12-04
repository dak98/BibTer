package bibtex.interpreter;

import bibtex.data.DataParser;
import bibtex.data.DataPrint;
import bibtex.data.DataStorage;
import bibtex.data.DataLoader;
import command.line.input.CmdInputParser;
import command.line.input.CmdInputStorage;
import data.operations.IDataParser;
import data.operations.IDataPrint;
import data.operations.IDataStorage;
import options.menu.CmdDisplay;
import options.menu.IDisplay;

import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BibtexInterpreter {
    public static void main(String[] args) {
        IDisplay display = new CmdDisplay();
        String userInput = display.menu(args);

        IDataParser cmdInputParser = new CmdInputParser();
        IDataStorage cmdInputStorage = (CmdInputStorage) cmdInputParser.parse(userInput);

        DataLoader dataLoader = new DataLoader();
        StringBuilder file = dataLoader.load(Paths.get(((CmdInputStorage) cmdInputStorage).getFilePath()));

        IDataParser parser = new DataParser();
        IDataStorage dataStorage = (DataStorage) parser.parse(file.toString());
        ((DataStorage) dataStorage).expandRecords();

        DataPrint printer = new DataPrint();
        printer.print(((DataStorage) dataStorage), ((CmdInputStorage) cmdInputStorage).getFrameSymbol());
    }
}
