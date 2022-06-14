package cs350s22.component.ui.parser;

import java.util.Arrays;

public class SendParser {
    protected static void sendParse(final A_ParserHelper ph, final Command cmd) {
        String[] ids = new String[cmd.length()];
        String[] groups = new String[cmd.length()];
        int idindex = 0;
        int groupindex = 0;
        String cur = "";
        boolean examid = false;
        boolean examgroup = false;
        if (cmd.length() < 3) {
            throw new RuntimeException("Bad Params passed to sendParse");
        }
        while (cmd.hasNext()) {

            cur = cmd.getNext().toUpperCase();

            // failure to specify id or group after asking
            // need to find way to specify to only go into fail check when the thing we're examining is empty
            if ((examid && ids[0] == null) || (examgroup && groups[0] == null) ) {
                if (cur.equals("PING") || cur.equals("POSITION")) {
                    throw new RuntimeException("");
                }
                if (cur.matches("IDS?|GROUPS?")) {
                    throw new RuntimeException("");
                }
            }


            //double quotes
            if (cmd.containsQuotes(cur)) {
                throw new RuntimeException();
            }

            //switch for changing bools to check for id or group
            switch (cur) {
                case "ID":
                case "IDS":
                    examid = true;
                    examgroup = false;
                    break;
                case "GROUP":
                case "GROUPS":
                    examid = false;
                    examgroup = true;
                    break;
                case "PING":
                    //should be done
                    if (cmd.hasNext()) throw new RuntimeException();
                    break;
                case "POSITION":
                    if(!cmd.hasNext()) throw new RuntimeException();
                    // each cmd walks through what should be the next argument
                    if (!examid && !examgroup) throw new RuntimeException();
                    String postest = cmd.getNext();
                    if (postest.equalsIgnoreCase("REQUEST")) {
                        try {
                            //throw if not argument provided after request
                            if (!cmd.hasNext()) throw new RuntimeException();
                            Integer.parseInt(cmd.getNext());
                            //throw for extra arguments
                            if (cmd.hasNext()) throw new RuntimeException();
                            break;
                        } catch (NumberFormatException e) {
                            throw new RuntimeException();
                        }

                        }
                        else if(postest.equalsIgnoreCase("REPORT")) break;
                    throw new RuntimeException();
                case "REPORT":
                case "REQUEST":
                    throw new RuntimeException();
                default:
                    if (examgroup) {
                        groups[groupindex] = cmd.getCurrentToken();
                        groupindex++;
                    } else if (examid) {
                        ids[idindex] = cmd.getCurrentToken();
                        idindex++;
                    } else {
                        throw new RuntimeException();
                    }

            }
        }
    }
}
