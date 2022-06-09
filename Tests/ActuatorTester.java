import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ActuatorTester {
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
        try{
            main = new Startup();
            main.parseTest("CREATE SENSOR SPEED S1");
            main.parseTest("CREATE SENSOR SPEED S2");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Does Linear Actuator Work?")
    public void actuatorLinearTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0 ");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Sensor Actuator with 1 Sensor Work?")
    public void actuatorLinearSensorOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Sensor Actuator with 2 Sensors Work?")
    public void actuatorLinearSensorTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Sensors Actuator with 1 Sensor Work?")
    public void actuatorLinearSensorsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Sensors Actuator with 2 Sensors Work?")
    public void actuatorLinearSensorsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Actuator with 1 Group Work?")
    public void actuatorLinearGroupOneGroupTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Actuator with 2 Groups Work?")
    public void actuatorLinearGroupTwoGroupsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 G2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Actuator with 1 Group Work?")
    public void actuatorLinearGroupsOneGroupTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Actuator with 2 Groups Work?")
    public void actuatorLinearGroupsTwoGroupsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensor Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorLinearGroupSensorOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensor Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorLinearGroupSensorOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensor Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorLinearGroupSensorTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 G2 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensor Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorLinearGroupSensorTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 G2 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensors Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorLinearGroupSensorsOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensors Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorLinearGroupSensorsOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensors Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorLinearGroupSensorsTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 G2 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Group Sensors Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorLinearGroupSensorsTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUP G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensor Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorLinearGroupsSensorOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensor Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorLinearGroupsSensorOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensor Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorLinearGroupsSensorTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensor Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorLinearGroupsSensorTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensors Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorLinearGroupsSensorsOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensors Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorLinearGroupsSensorsOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensors Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorLinearGroupsSensorsTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Linear Groups Sensors Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorLinearGroupsSensorsTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Actuator Work?")
    public void actuatorRotaryTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0 ");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Sensor Actuator with 1 Sensor Work?")
    public void actuatorRotarySensorOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Sensor Actuator with 2 Sensors Work?")
    public void actuatorRotarySensorTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Sensors Actuator with 1 Sensor Work?")
    public void actuatorRotarySensorsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Sensors Actuator with 2 Sensors Work?")
    public void actuatorRotarySensorsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Actuator with 1 Group Work?")
    public void actuatorRotaryGroupOneGroupTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Actuator with 2 Groups Work?")
    public void actuatorRotaryGroupTwoGroupsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 G2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Actuator with 1 Group Work?")
    public void actuatorRotaryGroupsOneGroupTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Actuator with 2 Groups Work?")
    public void actuatorRotaryGroupsTwoGroupsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensor Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorRotaryGroupSensorOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensor Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorRotaryGroupSensorOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensor Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorRotaryGroupSensorTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 G2 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensor Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorRotaryGroupSensorTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 G2 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensors Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorRotaryGroupSensorsOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensors Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorRotaryGroupSensorsOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensors Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorRotaryGroupSensorsTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 G2 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Group Sensors Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorRotaryGroupSensorsTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUP G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensor Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorRotaryGroupsSensorOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensor Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorRotaryGroupsSensorOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensor Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorRotaryGroupsSensorTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensor Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorRotaryGroupsSensorTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSOR S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensors Actuator with 1 Group And 1 Sensor Work?")
    public void actuatorRotaryGroupsSensorsOneGroupOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensors Actuator with 1 Group And 2 Sensors Work?")
    public void actuatorRotaryGroupsSensorsOneGroupTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensors Actuator with 2 Groups And 1 Sensor Work?")
    public void actuatorRotaryGroupsSensorsTwoGroupsOneSensorTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Rotary Groups Sensors Actuator with 2 Groups And 2 Sensors Work?")
    public void actuatorRotaryGroupsSensorsTwoGroupsTwoSensorsTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Actuator Work With Upper And Lower Case?")
    @MethodSource("generateCaseStrings")
    public void actuatorCaseTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("A1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Leadin?")
    public void actuatorLeadinValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(12.2,theActuator.getAccelerationLeadin());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Leadout?")
    public void actuatorLeadoutValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.4 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(-14.4,theActuator.getAccelerationLeadout());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Relax?")
    public void actuatorRelaxValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.6 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(3.6,theActuator.getAccelerationRelax());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Velocity Limit?")
    public void actuatorVelocityLimitValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4.6 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(4.6,theActuator.getVelocityLimit());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Min Position?")
    public void actuatorMinPositionValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(0.1,theActuator.getValueMin());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Max Position?")
    public void actuatorMaxPositionValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100.4 INITIAL 5 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(100.4,theActuator.getValueMax());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Initial Position?")
    public void actuatorInitialPositionValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5.3 JERK LIMIT 3.0");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(5.3,theActuator.getValueSource());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Jerk Limit?")
    public void actuatorJerkLimitValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.3");
            ActuatorPrototype theActuator = (ActuatorPrototype) ph.getSymbolTableActuator().get(Identifier.make("A1"));
            assertEquals(3.3,theActuator.getInflectionJerkThreshold());

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Actuator Correctly Assign Its ID?")
    public void actuatorIDTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE ACTUATOR ROTARY a1 GROUPS G1 G2 SENSORS S1 ACCELERATION LEADIN 12.2 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.3");
            assertTrue(ph.getSymbolTableActuator().contains(Identifier.make("a1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    private static LinkedList<String> generateCaseStrings(){
        LinkedList<String>arguments = new LinkedList<>();
        String[] editable = {"create","actuator","linear","rotary","group","groups","sensor","sensors","acceleration","leadin","leadout","relax","velocity","limit","value","min","max","initial","jerk"};
        String [] base = {"CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5.1 JERK LIMIT 3.0","CREATE ACTUATOR ROTARY A1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0",
        "CREATE ACTUATOR ROTARY A1 SENSOR S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4.4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.0","CREATE ACTUATOR LINEAR A1 GROUP G1 G2 SENSORS S1 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.0"};
        Random rand = new Random();
        while(arguments.size() < 50){
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
    @DisplayName("Does Actuator Throw Runtime Exceptions Correctly?")
    @MethodSource("generateInvalidStrings")
    public void actuatorRuntimeTest(String parse){
        Exception e =assertThrows(RuntimeException.class, ()->main.parseTest(parse));
        assertEquals(RuntimeException.class,e.getClass());
    }
    private static LinkedList<String> generateInvalidStrings() {
        LinkedList<String> arguments = new LinkedList<>();

        //Syntax
        arguments.add("CEATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4.0 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE CTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LNEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GOUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 SNSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0.1 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5.1 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEAIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEDOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RLAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VEOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VAUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INIIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JRK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIIT 3.0");
        arguments.add("CREATE ACTUATOR ROARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");

        //Values
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX  VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT  VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN  MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX  INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL  JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT ");

        //Groups
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");

        //Sensors
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");

        //Extra Arguments
        arguments.add("CREATE ACTUATOR ROTARY A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0 BAD Text");

        //Bad Spacing
        arguments.add("CREATEACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATORLINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEARA1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPSG1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 SENSORSS1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 ACCELERATIONLEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT-14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITYLIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUEMIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERKLIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT3.0");

        //Double Quotes
        arguments.add("CREATE ACTUATOR \"LINEAR\" A1 GROUPS G1 G2 SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");
        arguments.add("CREATE ACTUATOR LINEAR A1 GROUPS G1 \"G2\" SENSORS S1 S2 ACCELERATION LEADIN 12.0 LEADOUT -14.0 RELAX 3.0 VELOCITY LIMIT 4 VALUE MIN 0 MAX 100 INITIAL 5 JERK LIMIT 3.0");

        return arguments;
    }
}

