package bibtex.data;

import bibtex.data.record.RecordPrint;
import bibtex.data.record.RecordStorage;
import data.operations.IDataPrint;

import java.util.List;

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
}
