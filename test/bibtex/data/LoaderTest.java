package bibtex.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    @Test
    void loadShouldThrowNullPointerException() {
        DataLoader dataLoader = new DataLoader();

        assertThrows(NullPointerException.class, () -> {
            dataLoader.load(null);}, "ok");
    }
}