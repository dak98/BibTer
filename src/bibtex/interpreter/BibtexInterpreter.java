package bibtex.interpreter;

import bibtex.data.Loader;
import bibtex.data.record.RecordParser;
import bibtex.data.record.RecordStorage;
import bibtex.syntax.Fields;

import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class BibtexInterpreter {
    public static void main(String[] args) {
        Loader loader = new Loader();
        StringBuilder s = null;
        Path path = Paths.get("/home/jakub/Documents/xampl.bib");
        try {
            s = loader.load(path);
        } catch (Exception e) {
            System.out.println("xd");
        }

        RecordParser parser = new RecordParser();
        RecordStorage record = parser.parse("article{xd123,author = \"dak123\",title = \"kappa123 435\",}");

        Set<Map.Entry<Fields,String>> entrySet = record.fieldsMap.entrySet();

        System.out.println("Category: " + record.getCategory());
        System.out.println("Key: " + record.getKey());
        for (Map.Entry<Fields,String> entry : entrySet) {
            System.out.print(entry.getKey());
            System.out.println(" " + entry.getValue());
        }


    }
}
