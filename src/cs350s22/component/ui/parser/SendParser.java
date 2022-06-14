//Group 9
//Ocean Oestreicher, Andrew Korchemniy, Tyler Wang
package cs350s22.component.ui.parser;

import cs350s22.component.ui.CommandLineInterface;
import cs350s22.message.A_Message;
import cs350s22.message.actuator.MessageActuatorReportPosition;
import cs350s22.message.actuator.MessageActuatorRequestPosition;
import cs350s22.message.ping.MessagePing;
import cs350s22.support.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendParser {
    protected static void sendParse(final A_ParserHelper ph, final Command cmd) {
        List<Identifier> ids = new ArrayList<Identifier>();
        List<Identifier> groups = new ArrayList<Identifier>();
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
            if ((examid && ids.isEmpty()|| (examgroup && groups.isEmpty()))) {
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
                    CommandLineInterface cil = ph.getCommandLineInterface();
                    MessagePing mp = new MessagePing();
                    cil.issueMessage(mp);
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
                            double val = Double.parseDouble(cmd.getNext());

                            //throw for extra arguments
                            if (cmd.hasNext()) throw new RuntimeException();

                            //Shorten ids and groups arrays

                            CommandLineInterface cli = ph.getCommandLineInterface();
                            if (!ids.isEmpty())
                            {
                                A_Message message = new MessageActuatorRequestPosition(ids, val);
                                cli.issueMessage(message);
                            }
                            if (!groups.isEmpty())
                            {

                                A_Message message = new MessageActuatorRequestPosition(groups, val,0);
                                cli.issueMessage(message);
                            }



                            break;
                        } catch (NumberFormatException e) {
                            throw new RuntimeException();
                        }

                        }

                    else if(postest.equalsIgnoreCase("REPORT"))
                    {
                        if (cmd.hasNext()) throw new RuntimeException();

                        CommandLineInterface cli = ph.getCommandLineInterface();
                        if (!ids.isEmpty())
                        {
                            A_Message message = new MessageActuatorReportPosition(ids);
                            cli.issueMessage(message);
                        }
                        if (!groups.isEmpty())
                        {
                            A_Message message = new MessageActuatorRequestPosition(groups,0);
                            cli.issueMessage(message);
                        }
                        break;
                    }
                    throw new RuntimeException();
                case "REPORT":
                case "REQUEST":
                    throw new RuntimeException();
                default:
                    Identifier id = Identifier.make(cur);
                    if (examgroup) {
                        groups.add(id);
                    } else if (examid) {
                        ids.add(id);
                    } else {
                        throw new RuntimeException();
                    }
            }
        }
    }
}
