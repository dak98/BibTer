package bibtex.syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToEnumConverterTest {

    @Test
    void toField() {
        int length = Fields.values().length;

        String[] fields = new String[length];
        for (int i = 0; i < length; i++) {
            fields[i] = Fields.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(Fields.values()[i], converter.toField(fields[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toField("string");});
    }

    @Test
    void toCategory() {
        int length = Categories.values().length;

        String[] categories = new String[length];
        for (int i = 0; i < length; i++) {
            categories[i] = Categories.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(Categories.values()[i], converter.toCategory(categories[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toCategory("published");});
    }

    @Test
    void toKeyWord() {
        int length = KeyWords.values().length;

        String[] keywords = new String[length];
        for (int i = 0; i < length; i++) {
            keywords[i] = KeyWords.values()[i].toString();
        }

        ToEnumConverter converter = new ToEnumConverter();

        for (int i = 0; i < length; i++) {
            assertEquals(KeyWords.values()[i], converter.toKeyWord(keywords[i]));
        }

        assertThrows(IllegalArgumentException.class, () -> {converter.toKeyWord("author");});
    }
}