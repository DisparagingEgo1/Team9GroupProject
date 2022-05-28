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
        String[] inputs = this.commandText.split("//")[0].split(" ");
        for(String s: inputs){
            System.out.println(s);
        }

        if (inputs[0] == "");
        else if (inputs[0].charAt(0) == '@'){
            MetaParser.metaParse(this.ph, inputs);
        }
        else {
            switch (inputs[0]) {
                case "BUILD":
                    switch (inputs[1]){
                        case "NETWORK":
                            BuildParser.networkParse(this.ph, inputs);
                    }
                case "CREATE":
                    switch (inputs[1]){
                        case "ACTUATOR":
                            CreateParser.actuatorParse(this.ph, inputs);
                        case "MAPPER":
                            CreateParser.mapperParse(this.ph, inputs);
                        case "REPORTER":
                            CreateParser.reporterParse(this.ph, inputs);
                        case "SENSOR":
                            CreateParser.sensorParse(this.ph, inputs);
                        case "WATCHDOG":
                            CreateParser.watchdogParse(this.ph, inputs);
                    }
                case "SEND":
                    switch (inputs[1]){
                        case "MESSAGE":
                            SendParser.sendParse(this.ph, inputs);
                    }

                default:
                    throw new RuntimeException("Invalid Command Entered");
            }
        }



    }
}
