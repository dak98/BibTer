package utility;

public class LineCounter {
    private static int numberOfLines = 0;

    public void increment() {
        numberOfLines++;
    }

    public void decrement() {
        numberOfLines--;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }
}
