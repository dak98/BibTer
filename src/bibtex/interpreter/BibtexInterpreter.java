package bibtex.interpreter;

import bibtex.data.DataParser;
import bibtex.data.DataPrint;
import bibtex.data.DataStorage;
import bibtex.data.Loader;
import data.operations.IDataParser;
import data.operations.IDataPrint;
import data.operations.IDataStorage;

import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;


public class BibtexInterpreter {
    public static void main(String[] args) {
        Loader loader = new Loader();
        StringBuilder s = null;
        Path path = Paths.get("/home/jakub/Documents/xd.bib");
        try {
            s = loader.load(path);
        } catch (Exception e) {
            System.out.println("xd");
        }

        IDataParser<DataStorage> parser = new DataParser();

        IDataStorage<DataStorage> dataStorage = parser.parse(s.toString());

        IDataPrint<DataStorage> printer = new DataPrint();

        printer.print((DataStorage) dataStorage, '#');

    }
}
