package cs350s22.component.ui.parser;

import cs350s22.support.Clock;

import java.io.IOException;

public class MetaParser {
    private static A_ParserHelper ph;
    private static Command cmd;

    /**
     * Parses command text and then executes meta commands.
     *
     * @param ph  A ParserHelper
     * @param cmd Delimited command text beginning with the meta command identifier
     */
    protected static void metaParse(final A_ParserHelper ph, final Command cmd) {
        MetaParser.ph = ph;
        MetaParser.cmd = cmd;
        try {
            switch (cmd.getNext().toUpperCase()) {
                case ("@CLOCK") -> metaClockParse();
                case ("@EXIT") -> metaExitParse();
                case ("@RUN") -> metaRunParse();
                case ("@CONFIGURE") -> metaConfigureParse();
                default -> throw new RuntimeException("Invalid Meta Command Entered");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Invalid Meta Command Entered: Unexpected Argument Count");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid Meta Command <value> Entered");
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Invalid Meta Command <filepath> Entered");
        }
    }

    // Identifies @CLOCK command type
    private static void metaClockParse() {
        switch (cmd.getNext().toUpperCase()) {
            case ("PAUSE") -> metaClockPauseParse();
            case ("RESUME") -> metaClockResumeParse();
            case ("ONESTEP") -> metaClockOnestepParse();
            case ("SET") -> metaClockSetParse();
            default -> throw new RuntimeException("Invalid @CLOCK Meta Command Entered");
        }
    }

    // Pauses automated updating by the clock.
    private static void metaClockPauseParse() {
        if (postProcessed()) Clock.getInstance().isActive(false);
    }

    // Resumes automated updating by the clock./
    private static void metaClockResumeParse() {
        if (postProcessed()) Clock.getInstance().isActive(true);
    }

    // Updates the clock manually either once or optionally count times. This is valid only while the clock is paused.
    private static void metaClockOnestepParse() {
        if (cmd.hasNext()) {
            int count = Integer.parseInt(cmd.getNext());
            if (postProcessed()) {
                if (!Clock.getInstance().isActive())
                    Clock.getInstance().onestep(count);
            }
        } else {
            if (postProcessed()) {
                if (!Clock.getInstance().isActive())
                    Clock.getInstance().onestep();
            }
        }
    }

    // Sets the clock rate value in milliseconds per update.
    private static void metaClockSetParse() {
        if (cmd.getNext().equalsIgnoreCase("RATE")) {
            int milliseconds = Integer.parseInt(cmd.getNext());
            if (postProcessed()) Clock.getInstance().setRate(milliseconds);
        } else {
            throw new RuntimeException("Invalid @CLOCK Meta Command Entered");
        }
    }

    // Exits the system. This must be the last statement; otherwise, log files may not be complete.
    private static void metaExitParse() {
        if (postProcessed()) ph.exit();
    }

    // Loads and runs the script in fully qualified filename string.
    private static void metaRunParse() throws ParseException, IOException {
        String filepath = cmd.getNextFilepath();
        if (postProcessed()) ph.run(filepath);
    }

    // Defines where the output goes for logging and reporting. This must be the first command issued.
    private static void metaConfigureParse() throws ParseException, IOException {
        String log, dotSeq, network, xml;
        while (cmd.hasNext()) {
            switch (cmd.getNext().toUpperCase()) {
                case ("LOG"):
                    log = cmd.getNextFilepath();
                    break;
                case ("DOT"):
                    if (cmd.getNext().equalsIgnoreCase("SEQUENCE")) {
                        dotSeq = cmd.getNextFilepath();
                    } else {
                        throw new RuntimeException("Invalid @CONFIGURE Meta Command Entered");
                    }
                    break;
                case ("NETWORK"):
                    network = cmd.getNextFilepath();
                    break;
                case ("XML"):
                    xml = cmd.getNextFilepath();
                    break;
                default:
                    throw new RuntimeException("Invalid @CONFIGURE Meta Command Entered");
            }
        }
        // TODO: (TBD) No architecture binding specified yet in PDF
    }


    // Parsing post-processing, only used for handling invalid appended text to valid commands.
    private static boolean postProcessed() {
        if (cmd.hasNext()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

}
