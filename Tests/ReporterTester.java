import cs350s22.component.sensor.mapper.MapperEquation;
import cs350s22.component.sensor.mapper.function.equation.EquationNormalized;
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
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

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ReporterTester {
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
    @DisplayName("Does Change Reporter Group With 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 DELTA 4","        CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 DELTA 4        ","CREATE REPORTER        CHANGE R1 NOTIFY       GROUP MYGROUP3 DELTA 4"})
    public void reporterChangeGroupOneGroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");;
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter Group With 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 MYGROUP4 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY GROUP MYGROUP3 MYGROUP4 DELTA 4          ","CREATE REPORTER         CHANGE R1 NOTIFY GROUP           MYGROUP3       MYGROUP4 DELTA 4"})
    public void reporterChangeGroupMultipleGroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter Groups With 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 DELTA 4","      CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 DELTA 4     ","CREATE       REPORTER CHANGE R1      NOTIFY GROUPS        MYGROUP3 DELTA 4"})
    public void reporterChangeGroups1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter Groups With 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 DELTA 4","       CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 DELTA 4    ","CREATE       REPORTER CHANGE       R1 NOTIFY GROUPS        MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeGroupsMultipleGroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID With 1 id Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 DELTA 4","        CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 DELTA 4    ","CREATE REPORTER        CHANGE R1         NOTIFY ID          MYACTUATOR1 DELTA 4"})
    public void reporterChangeID1IDTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID With 2 ids Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 DELTA 4","           CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 DELTA 4        ","CREATE REPORTER         CHANGE R1 NOTIFY ID          MYACTUATOR1       MYACTUATOR2 DELTA 4"})
    public void reporterChangeID2IDsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS With 1 id Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 DELTA 4     ","CREATE        REPORTER         CHANGE R1 NOTIFY        IDS MYACTUATOR1 DELTA 4"})
    public void reporterChangeIDS1IDTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS With 2 ids Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 DELTA 4","       CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 DELTA 4     ","CREATE         REPORTER CHANGE R1        NOTIFY IDS MYACTUATOR1           MYACTUATOR2 DELTA 4"})
    public void reporterChangeIDS2IDsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUP With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 DELTA 4","      CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 DELTA 4      ","CREATE REPORTER CHANGE         R1 NOTIFY ID       MYACTUATOR1       GROUP MYGROUP3 DELTA 4"})
    public void reporterChangeIDGroup1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUP With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4      ","CREATE REPORTER CHANGE R1        NOTIFY ID         MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDGroup1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUPS With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 DELTA 4","     CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 DELTA 4     ","CREATE         REPORTER CHANGE        R1 NOTIFY ID           MYACTUATOR1 GROUPS MYGROUP3 DELTA 4"})
    public void reporterChangeIDGroups1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUPS With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4","            CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4      ","CREATE REPORTER         CHANGE R1 NOTIFY ID MYACTUATOR1          GROUPS         MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDGroups1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUP With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4       ","CREATE REPORTER       CHANGE R1           NOTIFY ID MYACTUATOR1          MYACTUATOR2 GROUP MYGROUP3 DELTA 4"})
    public void reporterChangeIDGroup2ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUP With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4","       CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4      ","CREATE REPORTER         CHANGE R1 NOTIFY           ID MYACTUATOR1        MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDGroup2ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUPS With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4","          CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4      ","CREATE           REPORTER CHANGE R1 NOTIFY ID           MYACTUATOR1 MYACTUATOR2         GROUPS MYGROUP3 DELTA 4"})
    public void reporterChangeIDGroups2ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter ID GROUPS With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4","       CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4       ","CREATE REPORTER            CHANGE R1 NOTIFY ID        MYACTUATOR1 MYACTUATOR2          GROUPS MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDGroups2ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUP With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 DELTA 4       ","CREATE         REPORTER CHANGE R1 NOTIFY         IDS MYACTUATOR1          GROUP MYGROUP3 DELTA 4"})
    public void reporterChangeIDSGroup1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUP With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4","          CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4      ","CREATE REPORTER CHANGE          R1 NOTIFY IDS          MYACTUATOR1 GROUP           MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDSGroup1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUPS With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 DELTA 4       ","CREATE REPORTER         CHANGE R1 NOTIFY IDS         MYACTUATOR1          GROUPS MYGROUP3 DELTA 4"})
    public void reporterChangeIDSGroups1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUPS With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4","        CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 DELTA 4     ","CREATE REPORTER CHANGE R1         NOTIFY            IDS MYACTUATOR1 GROUPS          MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDSGroups1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUP With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4","        CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 DELTA 4      ","CREATE REPORTER         CHANGE R1 NOTIFY        IDS MYACTUATOR1 MYACTUATOR2           GROUP MYGROUP3 DELTA 4 "})
    public void reporterChangeIDSGroup2IDs1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUP With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4","       CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4      ","CREATE REPORTER         CHANGE R1 NOTIFY        IDS MYACTUATOR1         MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDSGroup2IDs2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUPS With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4       ","CREATE REPORTER          CHANGE R1         NOTIFY IDS MYACTUATOR1          MYACTUATOR2 GROUPS MYGROUP3 DELTA 4"})
    public void reporterChangeIDSGroups2IDs1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Change Reporter IDS GROUPS With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4","         CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4      ","CREATE        REPORTER CHANGE R1         NOTIFY IDS MYACTUATOR1          MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4"})
    public void reporterChangeIDSGroups2IDs2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter Group With 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 FREQUENCY 4","        CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 FREQUENCY 4        ","CREATE REPORTER        FREQUENCY R1 NOTIFY       GROUP MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyGroupOneGroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter Group With 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY GROUP MYGROUP3 MYGROUP4 FREQUENCY 4          ","CREATE REPORTER         FREQUENCY R1 NOTIFY GROUP           MYGROUP3       MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyGroupMultipleGroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter Groups With 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 FREQUENCY 4","      CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 FREQUENCY 4     ","CREATE       REPORTER FREQUENCY R1      NOTIFY GROUPS        MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyGroups1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter Groups With 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","       CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4    ","CREATE       REPORTER FREQUENCY       R1 NOTIFY GROUPS        MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyGroupsMultipleGroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID With 1 id Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 FREQUENCY 4","        CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 FREQUENCY 4    ","CREATE REPORTER        FREQUENCY R1         NOTIFY ID          MYACTUATOR1 FREQUENCY 4"})
    public void reporterFrequencyID1IDTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID With 2 ids Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 FREQUENCY 4","           CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 FREQUENCY 4        ","CREATE REPORTER         FREQUENCY R1 NOTIFY ID          MYACTUATOR1       MYACTUATOR2 FREQUENCY 4"})
    public void reporterFrequencyID2IDsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS With 1 id Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 FREQUENCY 4     ","CREATE        REPORTER         FREQUENCY R1 NOTIFY        IDS MYACTUATOR1 FREQUENCY 4"})
    public void reporterFrequencyIDS1IDTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS With 2 ids Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 FREQUENCY 4","       CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 FREQUENCY 4     ","CREATE         REPORTER FREQUENCY R1        NOTIFY IDS MYACTUATOR1           MYACTUATOR2 FREQUENCY 4"})
    public void reporterFrequencyIDS2IDsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUP With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4","      CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4      ","CREATE REPORTER FREQUENCY         R1 NOTIFY ID       MYACTUATOR1       GROUP MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDGroup1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUP With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE REPORTER FREQUENCY R1        NOTIFY ID         MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDGroup1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUPS With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4","     CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4     ","CREATE         REPORTER FREQUENCY        R1 NOTIFY ID           MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDGroups1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUPS With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","            CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE REPORTER         FREQUENCY R1 NOTIFY ID MYACTUATOR1          GROUPS         MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDGroups1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUP With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4       ","CREATE REPORTER       FREQUENCY R1           NOTIFY ID MYACTUATOR1          MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDGroup2ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUP With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","       CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE REPORTER         FREQUENCY R1 NOTIFY           ID MYACTUATOR1        MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDGroup2ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUPS With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4","          CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4      ","CREATE           REPORTER FREQUENCY R1 NOTIFY ID           MYACTUATOR1 MYACTUATOR2         GROUPS MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDGroups2ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter ID GROUPS With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","       CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4       ","CREATE REPORTER            FREQUENCY R1 NOTIFY ID        MYACTUATOR1 MYACTUATOR2          GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDGroups2ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUP With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4       ","CREATE         REPORTER FREQUENCY R1 NOTIFY         IDS MYACTUATOR1          GROUP MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDSGroup1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUP With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","          CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE REPORTER FREQUENCY          R1 NOTIFY IDS          MYACTUATOR1 GROUP           MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDSGroup1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUPS With 1 id and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 FREQUENCY 4       ","CREATE REPORTER         FREQUENCY R1 NOTIFY IDS         MYACTUATOR1          GROUPS MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDSGroups1ID1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUPS With 1 id and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","        CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4     ","CREATE REPORTER FREQUENCY R1         NOTIFY            IDS MYACTUATOR1 GROUPS          MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDSGroups1ID2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUP With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4","        CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 FREQUENCY 4      ","CREATE REPORTER         FREQUENCY R1 NOTIFY        IDS MYACTUATOR1 MYACTUATOR2           GROUP MYGROUP3 FREQUENCY 4 "})
    public void reporterFrequencyIDSGroup2IDs1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUP With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","       CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE REPORTER         FREQUENCY R1 NOTIFY        IDS MYACTUATOR1         MYACTUATOR2 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDSGroup2IDs2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUPS With 2 ids and 1 Group Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4       ","CREATE REPORTER          FREQUENCY R1         NOTIFY IDS MYACTUATOR1          MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4"})
    public void reporterFrequencyIDSGroups2IDs1GroupTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Frequency Reporter IDS GROUPS With 2 ids and 2 Groups Work?")
    @ValueSource(strings={"CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","         CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4      ","CREATE        REPORTER FREQUENCY R1         NOTIFY IDS MYACTUATOR1          MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4"})
    public void reporterFrequencyIDSGroups2IDs2GroupsTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Change Reporter Assign Delta Value Correctly?")
    public void reporterDeltaValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 DELTA 4");
            ReporterChange theReporter = (ReporterChange) ph.getSymbolTableReporter().get(Identifier.make("R1"));
            if(!(4== theReporter.getDeltaThreshold())){
                fail("Bad Delta Value");
            }

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Frequency Reporter Assign Frequency Value Correctly?")
    public void reporterFrequencyValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 FREQUENCY 4");
            ReporterFrequency theReporter = (ReporterFrequency) ph.getSymbolTableReporter().get(Identifier.make("R1"));
            if(!(4== theReporter.getReportingFrequency())){
                fail("Bad Delta Value");
            }

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Reporter Correctly Create Its ID with lower case")
    public void mapperLowerCaseIDTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE REPORTER CHANGE r1 NOTIFY GROUP MYGROUP3 DELTA 4");
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("r1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Does Reporter Work With Upper And Lower Case?")
    @MethodSource("generateCaseStrings")
    public void reporterCaseTest(String parse){

        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableReporter().contains(Identifier.make("R1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    private static LinkedList<String>generateCaseStrings(){
        LinkedList<String>arguments = new LinkedList<>();
        String[] editable = {"create","reporter","change","frequency","id","ids","notify","group","groups","delta"};
        String [] base = {"CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4",
        "CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 DELTA 4","CREATE REPORTER CHANGE R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 DELTA 4",
        "CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4",
        "CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 FREQUENCY 4","CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 GROUP MYGROUP3 MYGROUP4 FREQUENCY 4"};
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
    @DisplayName("Does Reporter Throw A Runtime Exception?")
    @MethodSource("generateBadStrings")
    public void reporterRuntimeTest(String parse){
        assertThrows(RuntimeException.class, ()->main.parseTest(parse));

    }
    private static LinkedList<String> generateBadStrings(){
        LinkedList<String>arguments = new LinkedList<>();

        arguments.add("CREATE REPORTER R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4");
        arguments.add("CREATE REPRTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4 ");
        arguments.add("CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA ");
        arguments.add("CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY  ");
        arguments.add("CREATE REPORTER FREQUENCY R1 NOTIFY IS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4 ");
        arguments.add("CREATE REPORTER CHANGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DETA 4");
        arguments.add("CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUNCY 4 ");
        arguments.add("CREATE REPORTER CHNGE R1 NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4");
        arguments.add("CREATE REPORTER FREQUNCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4");
        arguments.add("CREATE REPORTER CHANGE R1 NOTIFY GROU MYGROUP3 MYGROUP4 DELTA 4");
        arguments.add("CRATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4");
        arguments.add("CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 \"GROUPS\" MYGROUP3 MYGROUP4 FREQUENCY 4");
        arguments.add("CREATE REPORTER R1 CHANGE NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 DELTA 4.4");
        arguments.add("CREATE REPORTER R1 FREQUENCY NOTIFY ID MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4.4");
        arguments.add("CREATE REPORTER FREQUENCY R1 NOTIFY IDS MYACTUATOR1 MYACTUATOR2 GROUPS MYGROUP3 MYGROUP4 FREQUENCY 4 CREATE");
        arguments.add("CREATE REPORTER R1 NOTIFY DELTA 4");

        return arguments;
    }











}
