package bibtex.data.record;

import data.operations.IDataParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordParserTest {
    @Test
    void parseShouldReturnNull() {
        IDataParser recordParser = new RecordParser();
        String dataToParse = "uNkNoWnCaTeGoRy{}";
        assertEquals(null, recordParser.parse(dataToParse));

        dataToParse = "uNkNoWnCaTeGoRy}}";
        assertEquals(null, recordParser.parse(dataToParse));
    }

    @Test
    void parseShouldThrowExceptions() {
        IDataParser recordParser = new RecordParser();
        assertThrows(NullPointerException.class, () -> {recordParser.parse(null);});

        String dataToParse1 = "ArTiClE{, @ }";
        assertThrows(IndexOutOfBoundsException.class, ()->{recordParser.parse(dataToParse1);});

        String dataToParse2 = "ArTiClE{key, @ field = \"field1\" @ }";
        assertThrows(IllegalArgumentException.class, ()->{recordParser.parse(dataToParse2);});

        String dataToParse3 = "iNbOoK{key, @ aUtHor = \"field1 @ }";
        assertThrows(UnsupportedOperationException.class, ()->{recordParser.parse(dataToParse3);});

        String dataToParse4 = "article{key1, @ title = \"title1\", @ author = \"author1\" @ }";
        assertThrows(UnsupportedOperationException.class, () -> {recordParser.parse(dataToParse4);});
    }
}