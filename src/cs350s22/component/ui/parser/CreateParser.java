package cs350s22.component.ui.parser;

import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.support.Identifier;
import cs350s22.test.MySensor;

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
            }
            else if (ID.equals("")) ID = token;
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
            }
            else if ((isChange && token.equalsIgnoreCase("delta")) || (isFrequency && token.equalsIgnoreCase("frequency"))) {
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
        LinkedList<Identifier> groups = new LinkedList<>();
        LinkedList<A_Watchdog>watchdogs = new LinkedList<>();
        LinkedList<A_Reporter>reporters = new LinkedList<>();
        boolean isSpeed = false, isPosition = false, hasWatchdogs = false, hasReporters = false, hasGroups = false,hasMapper = false, isValid = false;
        String ID = "",mapperID = "";

        while (cmd.hasNext()) {
            String token = cmd.getNext();

            if (!isSpeed && !isPosition) {
                if (token.equalsIgnoreCase("speed")) isSpeed = true;
                else if (token.equalsIgnoreCase("position")) isPosition = true;
            }
            else if (ID.equals("")) ID = token;
            else if (token.equalsIgnoreCase("group") || token.equalsIgnoreCase("groups")) {
                if (cmd.hasNext()&&!cmd.equalsNext(new String[]{"watchdog", "watchdogs", "reporter", "reporters", "mapper"})) {
                    hasGroups = true;
                }
                else throw new RuntimeException("Invalid Reporter Command");
                while (cmd.hasNext() && !cmd.equalsNext(new String[]{"watchdog", "watchdogs", "reporter", "reporters", "mapper"})) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    groups.add(Identifier.make(token));

                }
            }
            else if (token.equalsIgnoreCase("reporter") || token.equalsIgnoreCase("reporters")) {
                if (cmd.hasNext()&&!cmd.equalsNext(new String[]{"watchdog", "watchdogs", "mapper"})) {
                    hasReporters = true;
                }
                else throw new RuntimeException("Invalid Reporter Command");
                while (cmd.hasNext() && !cmd.equalsNext(new String[]{"watchdog", "watchdogs", "mapper"})) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    reporters.add(ph.getSymbolTableReporter().get(Identifier.make(token)));

                }
            }
            else if (token.equalsIgnoreCase("watchdog") || token.equalsIgnoreCase("watchdogs")) {
                if (cmd.hasNext()&&!cmd.equalsNext("mapper")) {
                    hasWatchdogs = true;
                }
                else throw new RuntimeException("Invalid Reporter Command");
                while (cmd.hasNext() && !cmd.equalsNext("mapper")) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    watchdogs.add(ph.getSymbolTableWatchdog().get(Identifier.make(token)));
                }
            }
            else if ( token.equalsIgnoreCase("mapper")) {
                if (cmd.hasNext()) {
                    hasMapper = true;
                    token = cmd.getNext();
                    mapperID = token;

                }
                else throw new RuntimeException("Invalid Reporter Command");
            }
            //Bad Syntax
            else break;
            if ((isSpeed || isPosition) && !ID.equals("")&&!cmd.hasNext())
                isValid = true;
        }
        //To-DO: Correct this once Dr. Tappan explains where we use the position and speed arguments
        if (!isValid || cmd.hasNext()) throw new RuntimeException("Invalid Reporter Command");
        MySensor theSensor = null;
        if (hasGroups||hasReporters||hasWatchdogs||hasMapper) {
            if(!hasMapper){
                if(isPosition){
                    theSensor = new MySensor(Identifier.make(ID),groups,reporters,watchdogs);
                }
                else{
                    theSensor = new MySensor(Identifier.make(ID),groups,reporters,watchdogs);
                }
            }
            else{
                A_Mapper mapper = ph.getSymbolTableMapper().get(Identifier.make(mapperID));
                if(isPosition){
                    theSensor = new MySensor(Identifier.make(ID),groups,reporters,watchdogs,mapper);
                }
                else{
                    theSensor = new MySensor(Identifier.make(ID),groups,reporters,watchdogs,mapper);
                }
            }
        } else {
            if(isPosition){
                theSensor = new MySensor(Identifier.make(ID));
            }
            else{
                theSensor = new MySensor(Identifier.make(ID));
            }
        }
        ph.getSymbolTableSensor().add(Identifier.make(ID),theSensor);
    }

    protected static void watchdogParse(final A_ParserHelper ph, final Command cmd) {

    }
}
