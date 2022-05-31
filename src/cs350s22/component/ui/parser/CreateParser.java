package cs350s22.component.ui.parser;

import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;

public class CreateParser {

    protected static void actuatorParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void mapperParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void reporterParse(final A_ParserHelper ph, final Command cmd) {
        //CREATE REPORTER CHANGE id NOTIFY [ids] [groups] DELTA value
        //CREATE REPORTER FREQUENCY id NOTIFY [ids] [groups] FREQUENCY value
        //ReporterFrequency(List<Identifier> ids, List<Identifier> groups, int reportingFrequency)
        //ReporterChange(List<Identifier> ids, List<Identifier> groups, int deltaThreshold)
        //Create a new ReporterChange object with ids, groups, and value and add it to SymbolTable<A_Reporter>
        //Create a new ReporterFrequency object with ids, groups, and value and add it to SymbolTable<A_Reporter>
    }

    protected static void sensorParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void watchdogParse(final A_ParserHelper ph, final Command cmd) {

    }

}
