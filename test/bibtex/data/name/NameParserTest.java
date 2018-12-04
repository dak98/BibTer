package bibtex.data.name;

import data.operations.IDataParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameParserTest {

    @Test
    void nameParserTest() {
        String[] names = {"Imię von Nazwisko ", "von    von von Nazwisko Imię  ", "Imię Nazwisko",
                          "von    Nazwisko|        Imię", "  von     von    Imię     Nazwisko|    Imię   Nazwisko",
                          "Imię    Nazwisko| Imię     Nazwisko",
                          "von von von Nazwisko| Jr| Imię", "von Imię Jr Nazwisko| Jr| Imię",
                          "von Nazwisko| Jr| Imię| Imię" };

        IDataParser nameParser = new NameParser();

        assertEquals("Nazwisko", nameParser.parse(names[0]));
        assertEquals("Nazwisko Imię", nameParser.parse(names[1]));
        assertEquals("Nazwisko", nameParser.parse(names[2]));

        assertEquals("Nazwisko", nameParser.parse(names[3]));
        assertEquals("Imię Nazwisko", nameParser.parse(names[4]));
        assertEquals("Imię Nazwisko", nameParser.parse(names[5]));

        assertEquals("Nazwisko|", nameParser.parse(names[6]));
        assertEquals("Imię Jr Nazwisko|", nameParser.parse(names[7]));

        assertEquals(null, nameParser.parse(names[8]));
    }
}