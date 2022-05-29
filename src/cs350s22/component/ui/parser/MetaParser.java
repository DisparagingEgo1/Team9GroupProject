package cs350s22.component.ui.parser;

import cs350s22.support.Clock;

import java.io.IOException;

public class MetaParser {
    private static A_ParserHelper parserHelper;
    private static String[] commandText;
    private static int tokenIndex;

    /**
     * Parses command text and then executes meta commands.
     *
     * @param ph A ParserHelper
     * @param ct Delimited command text beginning with the meta command identifier
     */
    protected static void metaParse(final A_ParserHelper ph, final String[] ct) {
        parserHelper = ph;
        commandText = ct;
        tokenIndex = 0;
        try {
            switch (consumeToken().toUpperCase()) {
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
        switch (consumeToken().toUpperCase()) {
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
        if (!Clock.getInstance().isActive()) {
            if (commandText.length == 2) {
                if (postProcessed()) Clock.getInstance().onestep();
            } else if (commandText.length == 3) {
                int count = Integer.parseInt(consumeToken());
                if (postProcessed()) Clock.getInstance().onestep(count);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    // Sets the clock rate value in milliseconds per update.
    private static void metaClockSetParse() {
        if (consumeToken().equalsIgnoreCase("RATE")) {
            int milliseconds = Integer.parseInt(consumeToken());
            if (postProcessed()) Clock.getInstance().setRate(milliseconds);
        }
    }

    // Exits the system. This must be the last statement; otherwise, log files may not be complete.
    private static void metaExitParse() {
        if (postProcessed()) parserHelper.exit();
    }

    // Loads and runs the script in fully qualified filename string.
    private static void metaRunParse() throws ParseException, IOException {
        String filepath = trimQuotes(consumeToken());
        if (postProcessed()) parserHelper.run(filepath);
    }

    // Defines where the output goes for logging and reporting. This must be the first command issued.
    private static void metaConfigureParse() throws ParseException, IOException {
        String log, dotSeq, network, xml;
        while (!isParsed()) {
            switch (consumeToken().toUpperCase()) {
                case ("LOG"):
                    log = trimQuotes(consumeToken());
                    break;
                case ("DOT"):
                    if (consumeToken().equalsIgnoreCase("SEQUENCE")) {
                        dotSeq = trimQuotes(consumeToken());
                    } else {
                        throw new RuntimeException("Invalid @CONFIGURE Meta Command Entered");
                    }
                    break;
                case ("NETWORK"):
                    network = trimQuotes(consumeToken());
                    break;
                case ("XML"):
                    xml = trimQuotes(consumeToken());
                    break;
                default:
                    throw new RuntimeException("Invalid @CONFIGURE Meta Command Entered");
            }
        }
        // TODO: (TBD) No architecture binding specified yet in PDF
    }

    // Gets the next token within commandText.
    private static String consumeToken() {
        return commandText[tokenIndex++];
    }

    // Return true if there are no more tokens to consume.
    private static boolean isParsed() {
        return commandText.length == tokenIndex;
    }

    // Parsing post-processing, only used for handling invalid appended text to valid commands.
    private static boolean postProcessed() {
        if (commandText.length != tokenIndex) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    // Removes double quotes from beginning and end of string if they exist.
    public static String trimQuotes(String s) {
        return s.replaceAll("^\"|\"$", "");
    }

}
