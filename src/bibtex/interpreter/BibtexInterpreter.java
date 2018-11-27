package bibtex.interpreter;

import bibtex.data.DataParser;
import bibtex.data.DataStorage;
import bibtex.data.Loader;
import bibtex.data.record.RecordStorage;
import bibtex.syntax.Fields;
import data.operations.IDataParser;

import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        DataStorage dataStorage = parser.parse(s.toString());

        List<RecordStorage> records = dataStorage.getRecords();

        for (RecordStorage record : records) {
            System.out.println(record.getCategory());
            System.out.println(record.getKey());

            for (Map.Entry<Fields,String> entries : (Set<Map.Entry<Fields,String>>) record.getFields().entrySet()) {
                System.out.print(entries.getKey() + " ");
                System.out.println(entries.getValue());
            }
        }


    }
}
