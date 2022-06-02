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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SensorTester {
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
        try {
            main.parseTest("CREATE MAPPER M1 EQUATION PASSTHROUGH");
            main.parseTest("CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 DELTA 4");
            main.parseTest("CREATE REPORTER CHANGE R2 NOTIFY GROUP MYGROUP3 DELTA 4");
            main.parseTest("CREATE WATCHDOG ACCELERATION W1 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 ");
            main.parseTest("CREATE WATCHDOG ACCELERATION W2 MODE INSTANTANEOUS THRESHOLD LOW 1 HIGH 3 ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Does Sensor Correctly Create Its ID with lower case")
    public void mapperLowerCaseIDTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE SENSOR SPEED s1 ");
            assertTrue(ph.getSymbolTableSensor().contains(Identifier.make("s1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Do All Sensor Permutations Work?")
    @MethodSource("generateParses")
    public void sensorTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableSensor().contains(Identifier.make("S1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    private static LinkedList<String>generateParses() throws FileNotFoundException {
        LinkedList<String>arguments = new LinkedList<>();
        Scanner fin = new Scanner(new File("Command Permutations/SensorPermutations.txt"));
        while(fin.hasNextLine()){
            arguments.add(fin.nextLine());
        }
        return arguments;
    }
    @ParameterizedTest
    @DisplayName("Does Sensor Throw A Runtime Exception?")
    @MethodSource("generateBadParses")
    public void sensorRuntimeTest(String parse){

        assertThrows(RuntimeException.class, ()->main.parseTest(parse));
    }
    private static LinkedList<String> generateBadParses(){
        LinkedList<String> arguments = new LinkedList<>();
        arguments.add("CREATE SENSOR SPEED S1 GRUP MYGROUP3 REPORTER R1 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 GRUPs MYGROUP3 REPORTER R1 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 WATCHDOGS ");
        arguments.add("CREATE SENSOR SPEED S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER ");
        arguments.add("CREATE SENSOR SPEED REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SESOR SPEED S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 APPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 WATCHDGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 REPORER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 CREATE SENSOR SPEED");
        arguments.add("CREATE SENSOR SPEED S1 Bad_Text");
        arguments.add("CREATE SENSOR SPEED S1 REPORTER R1 R2 ");
        arguments.add("CREATE SENSOR SPEED S1 REPORER R1 R2 WATCHDOGS W1 W2 ");
        arguments.add("CRATE SENSOR SPEED S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPED S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR Postion S1 REPORTER R1 R2 WATCHDOGS W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 REPORTER R1 \"WATCHDOGS\" W1 W2 MAPPER M1 ");
        arguments.add("CREATE SENSOR SPEED S1 REPORTER R1 WATCHDOGS \"W1\" W2 MAPPER M1 ");

        return arguments;
    }
}
