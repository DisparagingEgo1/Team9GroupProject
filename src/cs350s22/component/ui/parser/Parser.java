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
        String[] inputs = this.commandText.split("//")[0].strip().split("\\s+");
        if (inputs[0] == "" || inputs.length < 2)
        {
            throw new RuntimeException("Invalid Input");
        }
        if (inputs[0].charAt(0) == '@'){
            MetaParser.metaParse(this.ph, new Command(inputs));
        }
        else if (inputs[0].charAt(0) == '@'){
            MetaParser.metaParse(this.ph, inputs);
        }
        else {
            switch (inputs[0].toUpperCase()) {
                case "BUILD":
                    switch (inputs[1].toUpperCase()){
                        case "NETWORK":
                            BuildParser.networkParse(this.ph, new Command(inputs));
                    }
                    break;

                case "CREATE":
                    switch (inputs[1].toUpperCase()){
                        case "ACTUATOR":
                            CreateParser.actuatorParse(this.ph, new Command(inputs));
                            break;
                        case "MAPPER":
                            CreateParser.mapperParse(this.ph, new Command(inputs));
                            break;
                        case "REPORTER":
                            CreateParser.reporterParse(this.ph, new Command(inputs));
                            break;
                        case "SENSOR":
                            CreateParser.sensorParse(this.ph, new Command(inputs));
                            break;
                        case "WATCHDOG":
                            CreateParser.watchdogParse(this.ph, new Command(inputs));
                    }
                    break;

                case "SEND":
                    switch (inputs[1].toUpperCase()){
                        case "MESSAGE":
                            SendParser.sendParse(this.ph, new Command(inputs));
                    }
                    break;

                default:
                    throw new RuntimeException("Invalid Command Entered");
            }
        }



    }
}
