import cs350s22.startup.Startup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTester {
    private static Startup main;
    @BeforeAll
    public static void setup(){
        try {
            main = new Startup();
            main.parseTest("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @BeforeEach
    public void setupStartup(){
        main = new Startup();
    }
    @ParameterizedTest
    @DisplayName("Does Parser.parse() pass valid commands?")
    @MethodSource("validCommands")
    public void parseValidCommandsTest(String parse){
        try{
            main.parseTest(parse);
        }
        catch(Exception e){
            String [] result = e.getStackTrace()[0].getClassName().split("\\.");
            if(result[result.length-1].equals("Parser")){
                fail("Parser.parse() threw an exception when it shouldn't have");
            }
        }
    }
    private static LinkedList<String> validCommands(){
        LinkedList<String>arguments = new LinkedList<>();
        arguments.add(" CREATE ACTUATOR");
        arguments.add("create actuator");
        arguments.add("CREATE actuator");
        arguments.add("CREATE        actuator");

        arguments.add("CREATE SENSOR");
        arguments.add("create sensor");
        arguments.add("CREATE SeNSoR");
        arguments.add("CREATE        senSOR");

        arguments.add("      CREATE MAPPER");
        arguments.add("create mapper");
        arguments.add("CREATE MaPpER");
        arguments.add("CREATE        mapPER");

        arguments.add("CREATE REPORTER");
        arguments.add("create reporter");
        arguments.add("CREATE RePOrTER");
        arguments.add("CREATE        RepoRTER");

        arguments.add("CREATE WATCHDOG");
        arguments.add("create watchdog");
        arguments.add("CREATE WAtCHdOG");
        arguments.add("CREATE        WatCHDOG");

        arguments.add("BUILD NETWORK");
        arguments.add("build network");
        arguments.add("BUILD NeTwORK");
        arguments.add("BUILD        NeTWoRK");

        arguments.add("SEND MESSAGE");
        arguments.add("send message");
        arguments.add("SEND MeSsaGE");
        arguments.add("SEND        MeSSAgE");

        arguments.add("@CLOCK PAUSE");
        arguments.add("@clock PaUSE");
        arguments.add("@CLOCK pause");
        arguments.add("@clock          PAUSE");
        arguments.add("      @clock          PAUSE");

        return arguments;
    }
    @ParameterizedTest
    @DisplayName("Does Parser.parse() fail invalid commands?")
    @MethodSource("invalidCommands")
    public void parseInvalidCommandsTest(String parse){
        try{
            main.parseTest(parse);
        }
        catch(Exception e){
            String [] result = e.getStackTrace()[0].getClassName().split("\\.");
            if(!result[result.length-1].equals("Parser") || (result[result.length-1].equals("Parser")&&!e.getClass().getSimpleName().equals("RuntimeException"))){
                fail("Parser.parse() did not throw an exception when it should have");
            }
        }
    }
    private static LinkedList<String> invalidCommands(){
        LinkedList<String>arguments = new LinkedList<>();
        arguments.add("CREATE");
        arguments.add("create build");
        arguments.add("CRE   ATE actu   ator");
        arguments.add("\"BUILD\" NETWORK");

        return arguments;
    }
}
