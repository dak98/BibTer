package command.line.input.frame.symbol;

import data.operations.IDataParser;

/**
 * Class responsible for parsing command line
 * input to get symbol of the frame of record
 * printing.
 *
 * @author dak98
 */
public class FrameSymbolParser implements IDataParser {
    /**
     *
     * @param dataToParse
     *         String to get frame symbol from. Must begin
     *         with the symbol.
     * @return Symbol for the frame of record printing.
     */
    @Override
    public FrameSymbolStorage parse(String dataToParse) {
        return new FrameSymbolStorage(dataToParse.charAt(0));
    }
}
