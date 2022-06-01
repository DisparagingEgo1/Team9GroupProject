package cs350s22.component.ui.parser;

import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.support.Identifier;

import java.util.LinkedList;

public class CreateParser {

    protected static void actuatorParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void mapperParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void reporterParse(final A_ParserHelper ph, final Command cmd) {
        LinkedList<Identifier> ids = new LinkedList<>(), groups = new LinkedList<>();
        boolean isChange = false, isFrequency = false, notify = false, hasIds = false, hasGroups = false, delta = false, frequency = false, isValid = false;
        String ID = "";
        int deltaFrequencyValue = 0;

        while (cmd.hasNext() && !isValid) {
            String token = cmd.getNext();

            if (!isChange && !isFrequency) {
                if (token.equalsIgnoreCase("change")) isChange = true;
                else if (token.equalsIgnoreCase("frequency")) isFrequency = true;
            } else if (ID.equals("")) ID = token;
            else if (!notify && token.equalsIgnoreCase("notify")) notify = true;
            else if (token.equalsIgnoreCase("id") || token.equalsIgnoreCase("ids") || token.equalsIgnoreCase("group") || token.equalsIgnoreCase("groups")) {
                if (cmd.hasNext()) {
                    if (token.equalsIgnoreCase("id") || token.equalsIgnoreCase("ids")) {
                        hasIds = true;
                        hasGroups = false;
                    } else {
                        hasGroups = true;
                        hasIds = false;
                    }
                }
                while (cmd.hasNext() && !cmd.equalsNext(new String[]{"id", "ids", "groups", "group", "delta", "frequency"})) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    if (hasIds) ids.add(Identifier.make(token));
                    else if (hasGroups) groups.add(Identifier.make(token));

                }
            } else if ((isChange && token.equalsIgnoreCase("delta")) || (isFrequency && token.equalsIgnoreCase("frequency"))) {
                if (cmd.hasNext()) {
                    token = cmd.getNext();
                    if (cmd.isNumeric(token) && isChange) delta = true;
                    else if (cmd.isNumeric(token) && isFrequency) frequency = true;
                    deltaFrequencyValue = Integer.parseInt(token);
                }
            }
            //Bad Syntax
            else break;
            if ((isChange || isFrequency) && !ID.equals("") && notify && (hasIds || hasGroups) && (delta || frequency))
                isValid = true;
        }
        if (!isValid || cmd.hasNext()) throw new RuntimeException("Invalid Reporter Command");

        if (isChange) {
            ReporterChange reporter = new ReporterChange(ids, groups, deltaFrequencyValue);
            ph.getSymbolTableReporter().add(Identifier.make(ID), reporter);
        } else {
            ReporterFrequency reporter = new ReporterFrequency(ids, groups, deltaFrequencyValue);
            ph.getSymbolTableReporter().add(Identifier.make(ID), reporter);
        }
    }

    protected static void sensorParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void watchdogParse(final A_ParserHelper ph, final Command cmd) {

    }
}
