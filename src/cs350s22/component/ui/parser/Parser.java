package cs350s22.component.ui.parser;

import java.io.IOException;

public class Parser {

    private final A_ParserHelper ph;
    private final String commandText;

    public Parser(final A_ParserHelper parserHelper, final String commandText) {
        this.ph = parserHelper;
        this.commandText = commandText;
    }

    public void parse() throws IOException {
        Command cmd = new Command(this.commandText.split("//")[0].strip().split("\\s+"));
        if (cmd.tokenAt(0).isEmpty()) {
        } else if (cmd.tokenAt(0).charAt(0) == '@') {
            MetaParser.metaParse(this.ph, cmd);
        } else if (cmd.length() < 2) {
            throw new RuntimeException("Invalid Command: Unexpected Argument Count");
        } else {
            switch (cmd.getNext().toUpperCase()) {
                case "BUILD":
                    if ("NETWORK".equalsIgnoreCase(cmd.getNext())) BuildParser.networkParse(this.ph, cmd);
                    else throw new RuntimeException("Invalid BUILD Command Entered");
                    break;
                case "CREATE":
                    switch (cmd.getNext().toUpperCase()) {
                        case "ACTUATOR" -> CreateParser.actuatorParse(this.ph, cmd);
                        case "MAPPER" -> CreateParser.mapperParse(this.ph, cmd);
                        case "REPORTER" -> CreateParser.reporterParse(this.ph, cmd);
                        case "SENSOR" -> CreateParser.sensorParse(this.ph, cmd);
                        case "WATCHDOG" -> CreateParser.watchdogParse(this.ph, cmd);
                        default -> throw new RuntimeException("Invalid CREATE Command Entered");
                    }
                    break;
                case "SEND":
                    if ("MESSAGE".equalsIgnoreCase(cmd.getNext())) SendParser.sendParse(this.ph, cmd);
                    else throw new RuntimeException("Invalid SEND Command Entered");
                    break;
                default:
                    throw new RuntimeException("Invalid Command Entered");
            }
        }

    }
}
