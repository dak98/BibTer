package command.line.input.bibtex.file.path;

import command.line.input.frame.symbol.FrameSymbolChecker;
import data.operations.IDataChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilePathCheckerTest {

    @Test
    void shouldCorrectlyCheckFilePath() {
        IDataChecker filePathChecker = new FilePathChecker();

        assertTrue(filePathChecker.check("/home/jakub/Documents/xd.bib"));

        assertFalse(filePathChecker.check(null));
        assertFalse(filePathChecker.check(""));
        assertFalse(filePathChecker.check(" "));
        assertFalse(filePathChecker.check("/home/Documents/jakub/xd.bib"));
        assertFalse(filePathChecker.check("/home/jakub/Documents /xd.bib"));
        assertFalse(filePathChecker.check("/home/jakub/Documents"));
        assertFalse(filePathChecker.check("/home/jakub/Documents/"));
    }
}