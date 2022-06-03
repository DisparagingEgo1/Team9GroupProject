package cs350s22.component.ui.parser;

import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.component.sensor.watchdog.*;
import cs350s22.component.sensor.watchdog.mode.A_WatchdogMode;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeAverage;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeInstantaneous;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeStandardDeviation;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;
import cs350s22.test.MySensor;

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
            // (LINEAR | ROTARY)
            token = cmd.getNext();
            if (token.equalsIgnoreCase("LINEAR") || token.equalsIgnoreCase("ROTARY")) {
                type = token;
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Invalid Actuator Type");
            }

            // id
            id = cmd.getIdentifier(cmd.getNext());

            // [groups]
            token = cmd.getNext();
            if (token.equalsIgnoreCase("GROUP") || token.equalsIgnoreCase("GROUPS")) {
                groups = cmd.collateTo(new String[]{"SENSOR", "SENSORS", "ACCELERATION"});
            }

            // [SENSOR[S] id+]
            if (groups.length == 0) token = cmd.getCurrentToken();
            else token = cmd.getNext();
            if (token.equalsIgnoreCase("SENSOR") || token.equalsIgnoreCase("SENSORS")) {
                sensors = cmd.collateTo("ACCELERATION");
            }

            // ACCELERATION LEADIN
            if (sensors.length == 0) token = cmd.getCurrentToken();
            else token = cmd.getNext();
            if (token.equalsIgnoreCase("ACCELERATION") && cmd.getNext().equalsIgnoreCase("LEADIN")) {
                accelerationLeadin = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION LEADIN Not Found");
            }

            // ACCELERATION LEADOUT
            if (cmd.getNext().equalsIgnoreCase("LEADOUT")) {
                accelerationLeadout = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION LEADOUT Not Found");
            }

            // ACCELERATION RELAX
            if (cmd.getNext().equalsIgnoreCase("RELAX")) {
                accelerationRelax = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: ACCELERATION RELAX Not Found");
            }

            // VELOCITY LIMIT
            if (cmd.getNext().equalsIgnoreCase("VELOCITY") && cmd.getNext().equalsIgnoreCase("LIMIT")) {
                velocityLimit = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VELOCITY LIMIT Not Found");
            }

            // VALUE MIN
            if (cmd.getNext().equalsIgnoreCase("VALUE") && cmd.getNext().equalsIgnoreCase("MIN")) {
                valueMin = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE MIN Not Found");
            }

            // VALUE MAX
            if (cmd.getNext().equalsIgnoreCase("MAX")) {
                valueMax = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE MAX Not Found");
            }

            // VALUE INITIAL
            if (cmd.getNext().equalsIgnoreCase("INITIAL")) {
                valueInitial = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: VALUE INITIAL Not Found");
            }

            // JERK LIMIT
            if (cmd.getNext().equalsIgnoreCase("JERK") && cmd.getNext().equalsIgnoreCase("LIMIT")) {
                jerkLimit = Double.parseDouble(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: JERK LIMIT Not Found");
            }

            if (cmd.hasNext()) {
                throw new RuntimeException("Invalid CREATE ACTUATOR Command Entered: Unexpected Argument Count");
            }

            // Identifiers
            groupIdentifiers = cmd.getIdentifiers(groups);
            sensorIdentifiers = ph.getSymbolTableSensor().get(cmd.getIdentifiers(sensors), true);

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
        boolean isChange = false, isFrequency = false;
        List<Identifier> ids = null, groups = null;
        Identifier ID;
        int deltaOrFrequencyValue = 0;
        try {
            String token = cmd.getNext();
            //Type: Change or Frequency
            if (token.equalsIgnoreCase("CHANGE")) isChange = true;
            else if (token.equalsIgnoreCase("FREQUENCY")) isFrequency = true;
            else throw new RuntimeException("Invalid Reporter Argument");
            //ID
            ID = cmd.getIdentifier(cmd.getNext());
            //Notify
            if (!cmd.getNext().equalsIgnoreCase("NOTIFY")) throw new RuntimeException("Invalid Reporter Argument");
            //Notify: [IDS]
            token = cmd.getNext();
            if (token.toUpperCase().matches("IDS?")) {
                ids = cmd.getIdentifiers(cmd.collateTo(new String[]{"GROUP", "GROUPS", "DELTA", "FREQUENCY"}));
            }
            //Notify: [Groups]
            if (!(ids == null)) token = cmd.getNext();
            else ids = new LinkedList<>();
            if (token.toUpperCase().matches("GROUPS?")) {
                groups = cmd.getIdentifiers(cmd.collateTo(new String[]{"DELTA", "FREQUENCY"}));
            }
            //Type Value: Delta Or Frequency
            if (!(groups == null)) token = cmd.getNext();
            else groups = new LinkedList<>();
            if (token.equalsIgnoreCase("delta") || token.equalsIgnoreCase("frequency")) {
                deltaOrFrequencyValue = Integer.parseInt(cmd.getNext());
            }
            if (cmd.hasNext()) throw new RuntimeException("Invalid Reporter Command Argument Count");
            //Construct Reporter
            A_Reporter theReporter = null;
            if (isChange) theReporter = new ReporterChange(ids, groups, deltaOrFrequencyValue);
            else theReporter = new ReporterFrequency(ids, groups, deltaOrFrequencyValue);
            ph.getSymbolTableReporter().add(ID, theReporter);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new RuntimeException("Invalid Reporter Command Entered");
        }
    }

    //CREATE SENSOR SPEED S1 GROUP G1 REPORTERS R1 R2 WATCHDOGS W1 W2 MAPPER M1
    protected static void sensorParse(final A_ParserHelper ph, final Command cmd) {
        boolean isSpeed = false, isPosition = false;
        List<Identifier> groups = null;
        List<A_Watchdog> watchdogs = null;
        List<A_Reporter> reporters = null;
        A_Mapper mapper = null;
        Identifier ID;
        try {
            String token = cmd.getNext();

            //Type: Speed Or Position
            if (token.equalsIgnoreCase("Speed")) isSpeed = true;
            else if (token.equalsIgnoreCase("Position")) isPosition = true;
            else throw new RuntimeException("Invalid Sensor Argument");

            //ID
            ID = cmd.getIdentifier(cmd.getNext());
            if (cmd.hasNext()) token = cmd.getNext();
            else token = "";

            // [Groups]
            if (token.toUpperCase().matches("GROUPS?")) {
                groups = cmd.getIdentifiers(cmd.collateTo(new String[]{"REPORTER", "REPORTERS", "WATCHDOG", "WATCHDOGS", "MAPPER"}));
            }
            if (!(groups == null)) token = cmd.getNext();
            else groups = new LinkedList<>();

            //[Reporters]
            if (token.toUpperCase().matches("REPORTERS?")) {
                reporters = ph.getSymbolTableReporter().get(cmd.getIdentifiers(cmd.collateTo(new String[]{"WATCHDOG", "WATCHDOGS", "MAPPER"})), true);
            }
            if (!(reporters == null)) token = cmd.getNext();
            else reporters = new LinkedList<>();

            //[Watchdogs]
            if (token.toUpperCase().matches("WATCHDOGS?")) {
                watchdogs = ph.getSymbolTableWatchdog().get(cmd.getIdentifiers(cmd.collateTo(new String[]{"MAPPER"})), true);
            }
            if (!(watchdogs == null)) token = cmd.getNext();
            else watchdogs = new LinkedList<>();

            //[Mapper]
            if (token.equalsIgnoreCase("mapper")) {
                mapper = ph.getSymbolTableMapper().get(Identifier.make(cmd.getNext()));
            }
            if (cmd.hasNext()) throw new RuntimeException("Invalid Sensor Command Argument Count");

            //Construct Sensor
            MySensor theSensor = null;
            if (mapper != null) theSensor = new MySensor(ID, groups, reporters, watchdogs, mapper);
            else theSensor = new MySensor(ID, groups, reporters, watchdogs);
            ph.getSymbolTableSensor().add(ID, theSensor);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new RuntimeException("Invalid Sensor Command Entered");
        }
    }

    protected static void watchdogParse(final A_ParserHelper ph, final Command cmd) {
        String token, type;
        int low = 0, high = 0, grace = 0, modeValue, threshold = 0;
        boolean isModeValueSet = false, isGraceValueSet = false;
        A_Watchdog watchdog;
        A_WatchdogMode mode = null;
        Identifier id;

        try {
            // Type
            type = cmd.getNext();

            // ID
            id = cmd.getIdentifier(cmd.getNext());

            // mode
            if (!cmd.getNext().equalsIgnoreCase("MODE"))
                throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
            token = cmd.getNext();
            if (token.equalsIgnoreCase("INSTANTANEOUS")) {
                token = cmd.getNext();
                mode = new WatchdogModeInstantaneous();
                isModeValueSet = true;
            } else if (token.equalsIgnoreCase("AVERAGE")) {
                token = cmd.getNext();
                if (!token.equalsIgnoreCase("THRESHOLD")) {
                    modeValue = Integer.parseInt(token);
                    isModeValueSet = true;
                }
                mode = new WatchdogModeAverage();
            } else if (token.equalsIgnoreCase("STANDARD") && cmd.getNext().equalsIgnoreCase("DEVIATION")) {
                token = cmd.getNext();
                if (!token.equalsIgnoreCase("THRESHOLD")) {
                    modeValue = Integer.parseInt(token);
                    isModeValueSet = true;
                }
                mode = new WatchdogModeStandardDeviation();
            } else {
                throw new RuntimeException("Invalid CREATE WATCHDOG mode Entered");
            }

            // THRESHOLD(S)
            if (type.equalsIgnoreCase("LOW") || type.equalsIgnoreCase("HIGH")) {
                if (token.equalsIgnoreCase("THRESHOLD"))
                    token = cmd.getNext();
                threshold = Integer.parseInt(token);
            } else if (type.equalsIgnoreCase("ACCELERATION") || type.equalsIgnoreCase("BAND") ||
                    type.equalsIgnoreCase("NOTCH")) {
                if (token.equalsIgnoreCase("THRESHOLD")) {
                    if (!cmd.getNext().equalsIgnoreCase("LOW"))
                        throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
                } else {
                    if (!(cmd.getNext().equalsIgnoreCase("THRESHOLD") && cmd.getNext().equalsIgnoreCase("LOW")))
                        throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
                }
                low = Integer.parseInt(cmd.getNext());
                if (!cmd.getNext().equalsIgnoreCase("HIGH"))
                    throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
                high = Integer.parseInt(cmd.getNext());
            } else {
                throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
            }

            // GRACE
            if (cmd.hasNext()) {
                if (!cmd.getNext().equalsIgnoreCase("GRACE"))
                    throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
                grace = Integer.parseInt(cmd.getNext());
                isGraceValueSet = true;
            }

            // Create Watchdog
            switch (type.toUpperCase()) {
                case ("ACCELERATION"):
                    if (isGraceValueSet) watchdog = new WatchdogAcceleration(low, high, mode, grace);
                    else watchdog = new WatchdogAcceleration(low, high, mode);
                    break;
                case ("BAND"):
                    if (isGraceValueSet) watchdog = new WatchdogBand(low, high, mode, grace);
                    else watchdog = new WatchdogBand(low, high, mode);
                    break;
                case ("NOTCH"):
                    if (isGraceValueSet) watchdog = new WatchdogNotch(low, high, mode, grace);
                    else watchdog = new WatchdogNotch(low, high, mode);
                    break;
                case ("LOW"):
                    if (isGraceValueSet) watchdog = new WatchdogLow(threshold, mode, grace);
                    else watchdog = new WatchdogLow(threshold, mode);
                    break;
                case ("HIGH"):
                    if (isGraceValueSet) watchdog = new WatchdogHigh(threshold, mode, grace);
                    else watchdog = new WatchdogHigh(threshold, mode);
                    break;
                default:
                    throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered");
            }
            ph.getSymbolTableWatchdog().add(id, watchdog);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Invalid CREATE WATCHDOG Command Entered: Unexpected Argument Count");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid CREATE WATCHDOG <value> Entered");
        }
    }

}
