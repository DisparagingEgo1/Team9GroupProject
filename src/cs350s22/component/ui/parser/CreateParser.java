package cs350s22.component.ui.parser;

import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;
import cs350s22.test.MySensor;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

public class CreateParser {

    protected static void actuatorParse(final A_ParserHelper ph, final Command cmd) {
        double accelerationLeadin, accelerationLeadout, accelerationRelax;
        double valueMin, valueMax, valueInitial;
        double velocityLimit, jerkLimit;
        String token, type;
        String[] groups = new String[0], sensors = new String[0];
        List<Identifier> groupIdentifiers;
        List<A_Sensor> sensorIdentifiers;
        Identifier id;


        try {
            // Obtain (LINEAR | ROTARY)
            token = cmd.getNext();
            if (token.equalsIgnoreCase("LINEAR") || token.equalsIgnoreCase("ROTARY")) {
                type = token;
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Invalid Actuator Type");
            }

            // Obtain id
            id = Identifier.make(cmd.getNext());

            // Obtain [groups]
            token = cmd.getNext();
            if (token.equalsIgnoreCase("GROUP") || token.equalsIgnoreCase("GROUPS")) {
                groups = cmd.collateTo(new String[]{"SENSOR", "SENSORS", "ACCELERATION"});
            }

            // Obtain [SENSOR[S] id+]
            if (groups.length == 0) token = cmd.getCurrentToken();
            else token = cmd.getNext();
            if (token.equalsIgnoreCase("SENSOR") || token.equalsIgnoreCase("SENSORS")) {
                sensors = cmd.collateTo("ACCELERATION");
            }

            // Obtain ACCELERATION LEADIN
            if (sensors.length == 0) token = cmd.getCurrentToken();
            else token = cmd.getNext();
            if (token.equalsIgnoreCase("ACCELERATION") && cmd.getNext().equalsIgnoreCase("LEADIN")) {
                accelerationLeadin = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION LEADIN Not Found");
            }

            // Obtain ACCELERATION LEADOUT
            if (cmd.getNext().equalsIgnoreCase("LEADOUT")) {
                accelerationLeadout = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION LEADOUT Not Found");
            }

            // Obtain ACCELERATION RELAX
            if (cmd.getNext().equalsIgnoreCase("RELAX")) {
                accelerationRelax = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION RELAX Not Found");
            }

            // Obtain VELOCITY LIMIT
            if (cmd.getNext().equalsIgnoreCase("VELOCITY") && cmd.getNext().equalsIgnoreCase("LIMIT")) {
                velocityLimit = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VELOCITY LIMIT Not Found");
            }

            // Obtain VALUE MIN
            if (cmd.getNext().equalsIgnoreCase("VALUE") && cmd.getNext().equalsIgnoreCase("MIN")) {
                valueMin = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE MIN Not Found");
            }

            // Obtain VALUE MAX
            if (cmd.getNext().equalsIgnoreCase("MAX")) {
                valueMax = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE MAX Not Found");
            }

            // Obtain VALUE INITIAL
            if (cmd.getNext().equalsIgnoreCase("INITIAL")) {
                valueInitial = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE INITIAL Not Found");
            }

            // Obtain JERK LIMIT
            if (cmd.getNext().equalsIgnoreCase("JERK") && cmd.getNext().equalsIgnoreCase("LIMIT")) {
                jerkLimit = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: JERK LIMIT Not Found");
            }

            if (cmd.hasNext()) {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Unexpected Argument Count");
            }

            // Obtain Identifiers
            groupIdentifiers = getIdentifiers(groups);
            if (identifiersExist(ph.getSymbolTableSensor(), getIdentifiers(sensors))) {
                sensorIdentifiers = ph.getSymbolTableSensor().get(getIdentifiers(sensors));
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Sensor(s) Weren't Found");
            }

            // Create and store Actuator
            ActuatorPrototype actuator = new ActuatorPrototype(id, groupIdentifiers, accelerationLeadin, accelerationLeadout,
                    accelerationRelax, velocityLimit, valueInitial, valueMin, valueMax, jerkLimit, sensorIdentifiers);
            ph.getSymbolTableActuator().add(id, actuator);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Unexpected Argument Count");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid CREATE ACTUATOR Command <value> Entered");
        }

    }

    protected static void mapperParse(final A_ParserHelper ph, final Command cmd) {

    }

    protected static void reporterParse(final A_ParserHelper ph, final Command cmd) {
        boolean  isChange = false, isFrequency = false;
        List<Identifier>ids=null,groups=null;
        Identifier ID;
        int deltaOrFrequencyValue = 0;
        try{
            String token = cmd.getNext();
            //Type: Change or Frequency
            if(token.equalsIgnoreCase("CHANGE"))isChange = true;
            else if(token.equalsIgnoreCase("FREQUENCY")) isFrequency = true;
            else throw new RuntimeException("Invalid Reporter Argument");
            //ID
            ID = Identifier.make(cmd.getNext());
            //Notify
            if(!cmd.getNext().equalsIgnoreCase("NOTIFY"))throw new RuntimeException("Invalid Reporter Argument");
            //Notify: [IDS]
            token = cmd.getNext();
            if(token.toUpperCase().matches("IDS?")){
                ids= getIdentifiers(cmd.collateTo(new String[]{"GROUP","GROUPS","DELTA","FREQUENCY"}));
            }
            //Notify: [Groups]
            if(!(ids ==null)) token= cmd.getNext();
            else ids =new LinkedList<>();
            if(token.toUpperCase().matches("GROUPS?")){
                groups = getIdentifiers(cmd.collateTo(new String[]{"DELTA","FREQUENCY"}));
            }
            //Type Value: Delta Or Frequency
            if(!(groups == null))token = cmd.getNext();
            else groups = new LinkedList<>();
            if(token.equalsIgnoreCase("delta")||token.equalsIgnoreCase("frequency")){
                deltaOrFrequencyValue = Integer.parseInt(cmd.getNext());
            }
            if(cmd.hasNext())throw new RuntimeException("Invalid Reporter Command Argument Count");
            //Construct Reporter
            A_Reporter theReporter = null;
            if(isChange) theReporter = new ReporterChange(ids, groups, deltaOrFrequencyValue);
            else theReporter = new ReporterFrequency(ids, groups, deltaOrFrequencyValue);
            ph.getSymbolTableReporter().add(ID,theReporter);

        }
        catch(ArrayIndexOutOfBoundsException|NumberFormatException e){
            throw new RuntimeException("Invalid Reporter Command Entered");
        }
    }

    protected static void sensorParse(final A_ParserHelper ph, final Command cmd) {
        LinkedList<Identifier> groups = new LinkedList<>();
        LinkedList<A_Watchdog>watchdogs = new LinkedList<>();
        LinkedList<A_Reporter>reporters = new LinkedList<>();
        boolean isSpeed = false, isPosition = false, hasWatchdogs = false, hasReporters = false, hasGroups = false,hasMapper = false, isValid = false;
        String mapperID = "";
        Identifier ID = null;

        while (cmd.hasNext()) {
            String token = cmd.getNext();

            if (!isSpeed && !isPosition) {
                if (token.equalsIgnoreCase("speed")) isSpeed = true;
                else if (token.equalsIgnoreCase("position")) isPosition = true;
            }
            else if (ID == null) ID = Identifier.make(token);
            else if (token.toUpperCase().matches("GROUPS?")) {

                if (cmd.hasNext()&&!cmd.equalsNext(new String[]{"watchdog", "watchdogs", "reporter", "reporters", "mapper"})) hasGroups = true;
                else throw new RuntimeException("Invalid Reporter Command");
                while (cmd.hasNext() && !cmd.equalsNext(new String[]{"watchdog", "watchdogs", "reporter", "reporters", "mapper"})) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    groups.add(Identifier.make(token));
                }
            }
            else if (token.toUpperCase().matches("REPORTERS?")) {

                if (cmd.hasNext()&&!cmd.equalsNext(new String[]{"watchdog", "watchdogs", "mapper"})) hasReporters = true;
                else throw new RuntimeException("Invalid Reporter Command");
                while (cmd.hasNext() && !cmd.equalsNext(new String[]{"watchdog", "watchdogs", "mapper"})) {
                    token = cmd.getNext();
                    if (token.contains("\"")) throw new RuntimeException("Invalid Reporter Command");
                    reporters.add(ph.getSymbolTableReporter().get(Identifier.make(token)));
                }
            }
            else if (token.toUpperCase().matches("WATCHDOGS?")) {

                if (cmd.hasNext()&&!cmd.equalsNext("mapper")) hasWatchdogs = true;
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
                    mapperID = cmd.getNext();
                }
                else throw new RuntimeException("Invalid Reporter Command");
            }
            //Bad Syntax
            else break;
            if ((isSpeed || isPosition) && !(ID==null)&&!cmd.hasNext()) isValid = true;
        }
        if (!isValid || cmd.hasNext()) throw new RuntimeException("Invalid Reporter Command");
        //Dan does not care about position or speed for part 1
        MySensor theSensor = null;
        if (hasGroups||hasReporters||hasWatchdogs||hasMapper) {
            if(!hasMapper) theSensor = new MySensor(ID,groups,reporters,watchdogs);
            else{
                A_Mapper mapper = ph.getSymbolTableMapper().get(Identifier.make(mapperID));
                theSensor = new MySensor(ID,groups,reporters,watchdogs,mapper);
            }
        }
        else theSensor = new MySensor(ID);
        ph.getSymbolTableSensor().add(ID,theSensor);
    }

    protected static void watchdogParse(final A_ParserHelper ph, final Command cmd) {

    }

    /**
     * @param ids Identifier strings
     * @return Identifier objects
     */
    private static List<Identifier> getIdentifiers(final String[] ids) {
        List<Identifier> identifiers = new LinkedList<>();
        for (String id : ids) {
            identifiers.add(Identifier.make(id));
        }
        return identifiers;
    }

    /**
     * @param table   The table to check within for Identifiers
     * @param components The list of identifiers to check for existence
     * @return True if all identifiers exist in the table
     */
    private static boolean identifiersExist(final SymbolTable<?> table, final List<Identifier> components) {
        for (Identifier id : components) {
            if (!table.contains(id)) {
                return false;
            }
        }
        return true;
    }

}
