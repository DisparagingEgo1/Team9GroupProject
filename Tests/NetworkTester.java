import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Identifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTester {
    private static Startup main;
    @BeforeAll
    public static void setup(){
        try {
            main = new Startup();
            main.parseTest("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"");
            main.parseTest("CREATE ACTUATOR LINEAR MYACTUATOR1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0 ");
            main.parseTest("CREATE ACTUATOR LINEAR MYACTUATOR2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0 ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @BeforeEach
    public void setupStartup(){
        main = new Startup();
    }

    @Test
    @DisplayName("Does Network Component With 1 Component Work?")
    public void networkComponentOneComponentTest(){
        try{
            main.parseTest("BUILD NETWORK WITH COMPONENT MYACTUATOR1");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Network Component With 2 Components Work?")
    public void networkComponentTwoComponentsTest(){
        try{
            main.parseTest("BUILD NETWORK WITH COMPONENT MYACTUATOR1 MYACTUATOR2");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Network Components With 1 Component Work?")
    public void networkComponentsOneComponentTest(){
        try{
            main.parseTest("BUILD NETWORK WITH COMPONENTS MYACTUATOR1");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Network Components With 2 Components Work?")
    public void networkComponentsTwoComponentsTest(){
        try{
            main.parseTest("BUILD NETWORK WITH COMPONENTS MYACTUATOR1 MYACTUATOR2");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Network Work With Upper And Lower Case?")
    @MethodSource("generateCaseStrings")
    public void networkCaseTest(String parse){
        try{
            main.parseTest(parse);

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    private static LinkedList<String> generateCaseStrings(){
        LinkedList<String>arguments = new LinkedList<>();
        String[] editable = {"build","network","with","component","components"};
        String [] base = {"BUILD NETWORK WITH COMPONENT MYACTUATOR1","BUILD NETWORK WITH COMPONENT MYACTUATOR1 MYACTUATOR2","BUILD NETWORK WITH COMPONENTS MYACTUATOR1","BUILD NETWORK WITH COMPONENTS MYACTUATOR1 MYACTUATOR2"};
        Random rand = new Random();
        while(arguments.size() < 15){
            int chance = rand.nextInt(0,base.length);
            String [] tokens = base[chance].split(" ");
            String arg = "";
            for(String s: tokens){
                chance = rand.nextInt(1,3);
                if(chance == 1){
                    for(String r: editable){
                        if(s.equalsIgnoreCase(r)){
                            arg += r + " ";
                            break;
                        }
                        else if(r.equals(editable[editable.length-1])){
                            arg += s + " ";
                        }
                    }
                }
                else arg += s + " ";
            }
            arguments.add(arg);
        }

        return arguments;
    }
    @ParameterizedTest
    @DisplayName("Does Network Throw A Runtime Exception?")
    @MethodSource("generateInvalidStrings")
    public void networkRuntimeTest(String parse){
        assertThrows(RuntimeException.class,()->main.parseTest(parse));
    }
    private static LinkedList<String> generateInvalidStrings() {
        LinkedList<String> arguments = new LinkedList<>();

        //Syntax
        arguments.add("UILD NETWORK WITH COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NTWORK WITH COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WIT COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WITH COMPOENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WITH COMPOENTS MYCONTROLLER1 MYACTUATOR1");

        //IDS
        arguments.add("BUILD NETWORK WITH COMPONENT");

        //Bad Spaces
        arguments.add("BUILDNETWORK WITH COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORKWITH COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WITHCOMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WITH COMPONENTMYCONTROLLER1 MYACTUATOR1");

        //Double Quotes
        arguments.add("BUILD NETWORK \"WITH\" COMPONENT MYCONTROLLER1 MYACTUATOR1");
        arguments.add("BUILD NETWORK WITH COMPONENT \"MYCONTROLLER1\" MYACTUATOR1");



        return arguments;
    }
}
