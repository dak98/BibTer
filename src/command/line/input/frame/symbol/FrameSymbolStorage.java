package command.line.input.frame.symbol;

import data.operations.IDataStorage;

/**
 * Stores a the symbol of the frame of
 * record printing.
 *
 * @author dak98
 */
public class FrameSymbolStorage implements IDataStorage {
    private Character frameSymbol;

    /**
     * Defualt constructor.
     * @param frameSymbol
     */
    public FrameSymbolStorage(Character frameSymbol) {
        this.frameSymbol = frameSymbol;
    }

    /**
     *
     * @return Symbol of the frame stored in this instance.
     */
    public Character getFrameSymbol() {
        return this.frameSymbol;
    }
}
