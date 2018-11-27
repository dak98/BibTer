package bibtex.data.record;

import bibtex.syntax.Categories;
import bibtex.syntax.Fields;
import data.operations.IDataParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordStorageTest {

    @Test
    void recordStorageDataFlowTest() {
        String test = "article{key123, author =   \"name\", volume   = \"32\",   title = \"tytył\", joURNAL  = \"journal123\"," +
                "YEar = \"1998\", hOwPuBlIsHeD = \"published\"} ";

        IDataParser<RecordStorage> recordParser = new RecordParser();

        RecordStorage recordStorage = recordParser.parse(test);


        Fields[] fields = {Fields.author, Fields.volume, Fields.title, Fields.journal, Fields.year};
        String[] fieldsValues = { "name", "32", "tytył", "journal123", "1998" };
        
        assertEquals(Categories.article, recordStorage.getCategory());
        assertNotEquals(Categories.book, recordStorage.getCategory());

        assertEquals("name", recordStorage.getValueOfField(Fields.author));
        assertEquals("32", recordStorage.getValueOfField(Fields.volume));
        assertEquals(null, recordStorage.getValueOfField(Fields.howpublished));
        assertNotEquals("1999", recordStorage.getValueOfField(Fields.year));

        assertArrayEquals(fields, recordStorage.getFields().keySet().toArray());
        assertArrayEquals(fieldsValues, recordStorage.getFields().values().toArray());
    }

}