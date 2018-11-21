package bibtex_interpreter;

import bibtex_data_loader.Loader;

import java.lang.StringBuilder;
import java.io.IOException;

public class BibtexInterpreter {
    public static void main(String[] args) {
        Loader loader = new Loader();
        StringBuilder s = null;
        try {
            s = loader.load("/home/jakub/Documents/xampl.bib");
        } catch (IOException e) {

        } catch (NullPointerException e) {

        }
        System.out.println(s.toString());
    }
}
