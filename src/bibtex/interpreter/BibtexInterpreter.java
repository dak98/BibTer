package bibtex.interpreter;

import bibtex.data.Loader;

import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        System.out.println(s);
    }
}
