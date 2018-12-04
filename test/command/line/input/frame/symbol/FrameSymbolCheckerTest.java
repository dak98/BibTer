package command.line.input.frame.symbol;

import data.operations.IDataChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameSymbolCheckerTest {

    @Test
    void frameSymbolShouldBeCorrect() {
        IDataChecker frameSymbolChecker = new FrameSymbolChecker();

        assertTrue(frameSymbolChecker.check("    @    "));
        assertTrue(frameSymbolChecker.check("@"));

        assertFalse(frameSymbolChecker.check(null));
        assertFalse(frameSymbolChecker.check(""));
        assertFalse(frameSymbolChecker.check("@#"));
    }
}