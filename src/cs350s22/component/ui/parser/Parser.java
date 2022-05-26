package cs350s22.component.ui.parser;

import java.io.IOException;

public class Parser {

    private final A_ParserHelper ph;
    private final String commandText;
    public Parser(final A_ParserHelper parserHelper, final String commandText){
        this.ph = parserHelper;
        this.commandText = commandText;
    }
    public void parse() throws IOException {

    }
}
