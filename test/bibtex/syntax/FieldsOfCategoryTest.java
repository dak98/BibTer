package bibtex.syntax;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bibtex.syntax.Categories.*;
import static bibtex.syntax.Fields.*;
import static org.junit.jupiter.api.Assertions.*;

class FieldsOfCategoryTest {

    @Test
    void fieldShouldBeObligatory() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertTrue(fieldsOfCategory.isObligatory(article, author));
        assertTrue(fieldsOfCategory.isObligatory(booklet, title));
    }

    @Test
    void fieldShouldNotBeObligatory() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertFalse(fieldsOfCategory.isObligatory(techreport, number));
        assertFalse(fieldsOfCategory.isObligatory(misc, author));
    }

    @Test
    void fieldShouldBeOptional() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertTrue(fieldsOfCategory.isOptional(article, volume));
        assertTrue(fieldsOfCategory.isOptional(booklet, howpublished));
    }

    @Test
    void fieldShouldNotBeOptional() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertFalse(fieldsOfCategory.isOptional(techreport, howpublished));
        assertFalse(fieldsOfCategory.isObligatory(misc, volume));
    }

    @Test
    void categoryShouldContainField() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertTrue(fieldsOfCategory.isFieldOfCategory(article, volume));
        assertTrue(fieldsOfCategory.isFieldOfCategory(article, year));
        assertTrue(fieldsOfCategory.isFieldOfCategory(misc, title));
    }

    @Test
    void categoryShouldNotContainField() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        assertFalse(fieldsOfCategory.isFieldOfCategory(techreport, school));
        assertFalse(fieldsOfCategory.isFieldOfCategory(booklet, number));
        assertFalse(fieldsOfCategory.isFieldOfCategory(inproceedings, chapter));
    }

    @Test
    void getObligatoryFields() {
        FieldsOfCategory fieldsOfCategory = new FieldsOfCategory();
        List<Fields> fieldsOfArticle = Arrays.asList(author, title, journal, year);
        assertEquals(fieldsOfArticle, fieldsOfCategory.getObligatoryFields(article));
        assertNotEquals(fieldsOfArticle, fieldsOfCategory.getObligatoryFields(phdthesis));
    }
}