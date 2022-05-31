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
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.FixedWidth;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class WatchdogTester {
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
    public void prepareStartup(){
        main = new Startup();
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Acceleration Watchdog Without Grace Work?")
    @ValueSource(strings = {"CREATE watchdog ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3","       CREATE WATCHDOG acceleration W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3","CREATE       WATCHDOG     ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3     "})
    public void watchdogAccelerationInstantaneousNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Acceleration Watchdog With Grace Work?")
    @ValueSource(strings = {"CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4","            CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE       WATCHDOG ACCELERATION          W1 MODE INSTANTANEOUS     THRESHOLD LOW 1 HIGH 3 GRACE 4"})
    public void watchdogAccelerationInstantaneousGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average Acceleration Watchdog Without Grace Work?")
    @ValueSource(strings = {"create WATCHDOG ACCELERATION W1 MODE AVERAGE threshold LOW 1 HIGH 3","          CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3","CREATE      WATCHDOG ACCELERATION       W1 MODE        AVERAGE THRESHOLD LOW 1 HIGH 3"})
    public void watchdogAccelerationAverageNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Average Acceleration Watchdog With Grace Work?")
    @ValueSource(strings = {"CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","             CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE       WATCHDOG ACCELERATION       W1 MODE AVERAGE      THRESHOLD LOW 1     HIGH 3 GRACE 4     "})
    public void watchdogAccelerationAverageGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Acceleration Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","               CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","CREATE          WATCHDOG ACCELERATION W1           MODE STANDARD DEVIATION THRESHOLD    LOW 1 HIGH 3    "})
    public void watchdogAccelerationStandardDeviationNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Standard Deviation Acceleration Watchdog With Grace Work?")
    @ValueSource(strings = {"CREATE WATCHDOG ACCELERATION W1 MODE standard DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","        CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE        WATCHDOG ACCELERATION W1 MODE        STANDARD DEVIATION THRESHOLD LOW       1 HIGH 3 GRACE 4"})
    public void watchdogAccelerationStandardDeviationGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Band Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3","           CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3"," CREATE        WATCHDOG BAND W1         MODE INSTANTANEOUS THRESHOLD LOW 1        HIGH 3"})
    public void watchdogBandInstantaneousNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Band Watchdog With Grace Work?")
    @ValueSource(strings = {"CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4","           CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE        WATCHDOG          BAND W1 MODE         INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4"})
    public void watchdogBandInstantaneousGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Average Band Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3","              CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3","CREATE             WATCHDOG BAND W1 MODE         AVERAGE THRESHOLD LOW 1      HIGH 3       "})
    public void watchdogBandAverageNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average Band Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","            CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE WATCHDOG          BAND W1 MODE             AVERAGE THRESHOLD        LOW 1 HIGH 3 GRACE 4"})
    public void watchdogBandAverageGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Band Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","           CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","CREATE WATCHDOG         BAND W1 MODE         STANDARD DEVIATION          THRESHOLD LOW 1 HIGH 3"})
    public void watchdogBandStandardDeviationNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Band Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","         CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE         WATCHDOG BAND W1          MODE STANDARD DEVIATION        THRESHOLD LOW 1 HIGH         3 GRACE 4"})
    public void watchdogBandStandardDeviationGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Notch Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3","         CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 ", "CREATE WATCHDOG          NOTCH      W1 MODE INSTANTANEOUS      THRESHOLD LOW 1 HIGH 3 "})
    public void watchdogNotchInstantaneousNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Notch Watchdog With Grace Work?")
    @ValueSource(strings = {"CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4","      CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4", "CREATE WATCHDOG NOTCH W1      MODE INSTANTANEOUS        THRESHOLD      LOW 1 HIGH 3 GRACE 4"})
    public void watchdogNotchInstantaneousGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Average Notch Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3","       CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3","CREATE WATCHDOG       NOTCH W1 MODE       AVERAGE THRESHOLD       LOW 1 HIGH 3"})
    public void watchdogNotchAverageNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average Notch Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","      CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE          WATCHDOG NOTCH W1 MODE       AVERAGE THRESHOLD           LOW 1 HIGH 3 GRACE 4"})
    public void watchdogNotchAverageGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Notch Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","        CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3","CREATE WATCHDOG        NOTCH W1 MODE STANDARD           DEVIATION THRESHOLD          LOW 1 HIGH 3"})
    public void watchdogNotchStandardDeviationNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Notch Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","          CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4","CREATE            WATCHDOG NOTCH W1             MODE STANDARD DEVIATION           THRESHOLD LOW 1 HIGH 3 GRACE 4"})
    public void watchdogNotchStandardDeviationGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous Low Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD 3","                CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD 3","CREATE         WATCHDOG LOW W1           MODE INSTANTANEOUS        THRESHOLD 3"})
    public void watchdogLowInstantaneousNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Instantaneous Low Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE 4","         CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE 4","CREATE           WATCHDOG LOW W1 MODE        INSTANTANEOUS       THRESHOLD 3      GRACE 4"})
    public void watchdogLowInstantaneousGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average Low Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD 3","       CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD 3","CREATE       WATCHDOG LOW W1         MODE AVERAGE         THRESHOLD 3"})
    public void watchdogLowAverageNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average Low Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD 3 GRACE 4","        CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD 3 GRACE 4","CREATE         WATCHDOG      LOW W1 MODE        AVERAGE THRESHOLD 3 GRACE 4"})
    public void watchdogLowAverageGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Low Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3","        CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3","CREATE        WATCHDOG LOW W1        MODE STANDARD          DEVIATION THRESHOLD 3"})
    public void watchdogLowStandardDeviationNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation Low Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4","        CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4","CREATE WATCHDOG        LOW W1 MODE         STANDARD        DEVIATION THRESHOLD 3 GRACE 4"})
    public void watchdogLowStandardDeviationGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Instantaneous High Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD 3","     CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD 3","CREATE        WATCHDOG HIGH W1          MODE INSTANTANEOUS       THRESHOLD 3"})
    public void watchdogHighInstantaneousNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Instantaneous High Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE 4","         CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE 4","CREATE        WATCHDOG HIGH              W1 MODE        INSTANTANEOUS THRESHOLD 3 GRACE 4"})
    public void watchdogHighInstantaneousGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average High Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD 3","      CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD 3","CREATE         WATCHDOG HIGH          W1 MODE    AVERAGE THRESHOLD 3"})
    public void watchdogHighAverageNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Average High Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD 3 GRACE 4","              CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD 3 GRACE 4","CREATE          WATCHDOG   HIGH W1       MODE AVERAGE        THRESHOLD 3 GRACE 4"})
    public void watchdogHighAverageGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation High Watchdog Without Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3","        CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3","CREATE       WATCHDOG HIGH          W1 MODE          STANDARD DEVIATION    THRESHOLD 3"})
    public void watchdogHighStandardDeviationNoGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

    @ParameterizedTest
    @DisplayName("Does Standard Deviation High Watchdog With Grace Work?")
    @ValueSource(strings={"CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4","         CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4","CREATE          WATCHDOG HIGH W1         MODE STANDARD           DEVIATION THRESHOLD 3 GRACE 4"})
    public void watchdogHighStandardDeviationGraceTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableWatchdog().contains(Identifier.make("W1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Watchdog Throw Runtime Exception?")
    @MethodSource("getInvalidArguments")
    public void watchdogRuntimeTest(String parse){
        assertThrows(RuntimeException.class, ()-> main.parseTest(parse));
    }
    private static LinkedList<String> getInvalidArguments(){
        LinkedList<String>arguments = new LinkedList<>();
        //Acceleration Instantaneous
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE ");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH  GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW  HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS  LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Acceleration Average
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 1 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CRATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 ");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH ");
        arguments.add("CREATE WATCHDOG ACCELERATION MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW  HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1  3 GRACE 4");
        arguments.add("CREATE WATCHDOG W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3  4");
        arguments.add("CREATE ACCELERATION W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Acceleration Standard Deviation
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE STANDARD THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WTCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE STADARD DEVITION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 GRACE 4");
        arguments.add("CREATE WATCHDOG ACCELERATION MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Band Instantaneous
        arguments.add("CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 0 HIGH 1 GRACE 4 ");
        arguments.add("CREATE WATCHDOGBAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4 ");
        arguments.add("CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 GRACE 4 ");
        arguments.add("CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE  ");
        arguments.add("Build WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4 ");
        arguments.add("Send WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4 ");
        arguments.add("@CREATE WATCHDOG BAND W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4 ");
        //Band Average
        arguments.add("CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 2 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW  HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GRACE ");
        arguments.add("CREATE WATCHDOG BAND W1 MODE AVERAGE THRESHOLD LOW 1 HIGH  GRACE 4");
        //Band Standard Deviation
        arguments.add("CREATE WATCHDOG BAND W1  STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 G");
        arguments.add("CREATE WATCHDOG BAND W1 MODE DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 GRACE 4");
        arguments.add("CREATE WATCHDOG BAND W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE ");
        arguments.add("CREATE WATCHDOG BAND W1 MODE STANDRD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Notch Instantaneous
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE INSTANTANEOUS THRESHOLD HIGH 3");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG NOCH W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Notch Average
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 4 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH 3 GR");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW 1 HIGH ");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERAGE THRESHOLD LOW  HIGH 3");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE AVERGE THRESHOLD LOW 1 HIGH 3 GRACE 4");
        //Notch Standard Deviation
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STNDARD DEVIATION THRESHOLD LOW 1 HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD HIGH 3 GRACE 4");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW 1 HIGH ");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LOW  HIGH 3");
        arguments.add("CREATE WATCHDOG NOTCH W1 MODE STANDARD DEVIATION THRESHOLD LW 1 HIGH 3 GRACE 4");
        //Low Instantaneous
        arguments.add("CREATE WATCHDOG LOW W1 MODE INSTATANEOUS THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD GRACE 4");
        arguments.add("CREATE WATCHDOG LOW W1 MODEINSTANTANEOUS THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG LOW W1 MODE INSTANTANEOUS THRESHOLD ");
        //Low Average
        arguments.add("CREATE WATCHDOG LOW W1 MODE AVERGE THRESHOLD 3 GRACE 4 ");
        arguments.add("CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD GRACE 4 ");
        arguments.add("CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD  ");
        arguments.add("CREATE WATCHDOG LOW W1 MODE AVERAGE THRESHOLD 3 4 ");
        arguments.add("CREATE WATCHDOG LW W1 MODE AVERAGE THRESHOLD 3 GRACE 4 ");
        //Low Standard Deviation
        arguments.add("CREATE WATCHDOG LOW W1 MODE STANDARD DEVATION THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATIONTHRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3  4");
        arguments.add("CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD ");
        arguments.add("CREATE WATCHDOG LOW W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE ");
        //High Instantaneous
        arguments.add("CREATE WATCHDOG HIGH W1 MODE INSTATANEOUS THRESHOLD 3 GRACE 4 ");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS GRACE 4 ");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD  ");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE  ");
        arguments.add("CREATE WATCHDOG HGH W1 MODE INSTANTANEOUS THRESHOLD 3 GRACE 4 ");
        //High Average
        arguments.add("CREATE WATCHDOG HIGH W1 MODE AERAGE THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE AVERAGE  GRACE 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD ");
        arguments.add("CREATE WATCHDOG HIH W1 MODE AVERAGE THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE AVERAGE THRESHOLD 3 GRACE ");
        //High Standard Deviation
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDAD DEVIATION THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION GRACE 4");
        arguments.add("CREATE WATCHDOGHIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 4");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE");
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD GRACE 4");
        //Invalid Arguments
        arguments.add("CREATE WATCHDOG HIGH W1 MODE STANDARD DEVIATION THRESHOLD 3 GRACE 4 Bad Text");
        //Double Quotes
        arguments.add("CREATE WATCHDOG HIGH W1 MODE \"STANDARD\" DEVIATION THRESHOLD 3 GRACE 4");
        return arguments;

    }
}
