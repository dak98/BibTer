package options.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandLineDisplayTest {

    @Test
    void shouldReturnCorrectInputData() {
        String[] cmdInput = {"/home/jakub/Documents/xd.bib", "@", "Autor1", "Autor2", "aRtiCle", "BoOk"};
        String output = "/home/jakub/Documents/xd.bib @ Autor1 Autor2 aRtiCle BoOk";

        IDisplay menu = new CmdDisplay();

        assertTrue(menu.menu(cmdInput).equals(output));
    }
}