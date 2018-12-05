package bibtex.data;

import bibtex.data.record.RecordPrint;
import bibtex.data.record.RecordStorage;
import bibtex.syntax.Categories;
import data.operations.IDataPrint;

import java.util.List;

/**
 * Class responsible for printing records
 * from the BibTex's file.
 *
 * @author dak98
 */
public class DataPrint implements IDataPrint<DataStorage> {
    /**
     * Prints all the records of a BibTex file.
     *
     * @param dataStorage
     *         Storage of all the records of a BibTex file.
     * @param borderSymbol
     *          Symbol of the border of output.
     */
    @Override
    public void print(DataStorage dataStorage, Character borderSymbol) {
        List<RecordStorage> recordsList = dataStorage.getRecords();

        IDataPrint printer = new RecordPrint();
        for (RecordStorage record : recordsList) {
            printer.print(record, borderSymbol);
            System.out.println("");
        }
    }

    /**
     * Prints records from dataStorage which
     * have article or editor field with the
     * specified lastName.
     *
     * @param dataStorage
     * @param borderSymbol
     *         Border symbol for printing the record.
     * @param lastName
     */
    public void printByName(DataStorage dataStorage, Character borderSymbol, String lastName) {
        List<RecordStorage> recordsByAuthor = dataStorage.getRecordsByName(lastName);

        IDataPrint printer = new RecordPrint();
        for (RecordStorage record : recordsByAuthor) {
            printer.print(record, borderSymbol);
            System.out.println("");
        }
    }

    /**
     * Prints records from dataStorage which
     * have given category.
     *
     * @param dataStorage
     * @param borderSymbol
     *         Border symbol for printing the record.
     * @param category
     */
    public void printByCategory(DataStorage dataStorage, Character borderSymbol, Categories category) {
        List<RecordStorage> recordsByCategory = dataStorage.getRecordsByCategory(category);

        IDataPrint printer = new RecordPrint();
        for (RecordStorage record : recordsByCategory) {
            printer.print(record, borderSymbol);
            System.out.println("");
        }
    }
}
