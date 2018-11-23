package bibtex.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    @Test
    void loadShouldThrowNullPointerException() {
        Loader loader = new Loader();

        assertThrows(NullPointerException.class, () -> {loader.load(null);}, "ok");
    }
}