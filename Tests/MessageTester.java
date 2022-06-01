import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Identifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTester {
    private static Startup main;
    private static final String fileName = "Command Permutations/MessagePermutations.txt";
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
    public void prepareStartup(){
        main = new Startup();
    }

    @ParameterizedTest
    @DisplayName("Can We Send Messages Without an Error?")
    @MethodSource("getMessageStrings")
    public void validSendMessageTest(String parse){
        PrintStream originalErr = System.err;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{

            System.setErr(new PrintStream(bos)) ;

            main.parseTest(parse);
            assertEquals(0, bos.toString().length());
            bos.close();
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
        System.setErr(originalErr);

    }
    private static LinkedList<String> getMessageStrings(){
        LinkedList<String>arguments = new LinkedList<>();
        try{
            Scanner fin = new Scanner(new File(MessageTester.fileName));
            while(fin.hasNextLine()){
                arguments.add(fin.nextLine());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arguments;
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting an id after ID?")
    public void sendMessageIDNoIDTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message ID group G1 G2 position request 30"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting an id after IDS?")
    public void sendMessageIDSNoIDTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message IDS group G1 G2 position request 30"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting a group id after GROUP?")
    public void sendMessageGroupNoIDTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message IDS ME1 group position request 30"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting a group id after GROUPS?")
    public void sendMessageGroupsNoIDTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message IDS ME1 groups position request 30"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting a value after POSITION REQUEST?")
    public void sendMessagePositionRequestNoValueTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message IDS ME1 groups G1 G2 position request"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting both ID/IDS and GROUP/GROUPS?")
    public void sendMessageNoIdAndGroupTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND message position request 30"));
    }
    @ParameterizedTest
    @DisplayName("Do we get a runtime exception for having an incomplete message?")
    @ValueSource(strings={"SEND message","SEND"})
    public void sendMessageIncompleteTest(String parse){
        assertThrows(RuntimeException.class, ()->main.parseTest(parse));
    }

    @Test
    @DisplayName("Do we get a runtime exception for forgetting POSITION keyword")
    public void sendMessageNoPositionKeywordTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND MESSAGE IDS ME1 ME2 GROUPS G1 G2 REPORT"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting REPORT keyword")
    public void sendMessageNoReportKeywordTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND MESSAGE IDS ME1 ME2 GROUPS G1 G2 POSITION"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for forgetting REQUEST keyword")
    public void sendMessageNoRequestKeywordTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND MESSAGE IDS ME1 ME2 GROUPS G1 G2 POSITION 30"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for having extra arguments")
    public void sendMessageExtraArgumentsTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND MESSAGE IDS ME1 ME2 GROUPS G1 G2 POSITION REQUEST 30 BAd text"));
    }
    @Test
    @DisplayName("Do we get a runtime exception for having double quotes")
    public void sendMessageDoubleQuotesTest(){
        assertThrows(RuntimeException.class, ()->main.parseTest("SEND MESSAGE IDS ME1 ME2 GROUPS G1 \"G2\" POSITION REQUEST 30"));
    }
}
