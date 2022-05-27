import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Clock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
/*
 @EXIT will need to be manually tested since there is not an elegant way to block the System.exit() call from the
 architecture.
 3 VALID TESTS: ("@EXIT","@EXIT             ","           @EXIT")
 3 INVALID TESTS: ("@ EXIT","EXIT             ","           @EX IT")

runTest() currently passes. This test needs to be done at the conclusion of the project to make sure it correctly passes

 @CONFIGURE must be manually tested as the architecture holds on to the configuration even between tests. Our
 parser at a minimum needs to parse what Dr. Tappan already gave us: "@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\""
 2 VALID TESTS: ("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"", "@CONFIGURE LOG        \"a.txt\" DOT SEQUENCE       \"b.txt\" NETWORK      \"c.txt\" XML \"d.txt\"")
 3 INVALID TESTS: ("CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"", "@ CONFIGURE LOG        \"a.txt\" DOT SEQUENCE       \"b.txt\" NETWORK      \"c.txt\" XML \"d.txt\"","@CONFI GURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"")
 */
public class MetaTester {
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
    public void setupOutputCatch(){
        main = new Startup();
    }

    @ParameterizedTest
    @DisplayName("Does Clock Pause Work?")
    @ValueSource(strings = {"@CLOCK PAUSE","@CLOCK PAUSE          ","@CLOCK     PAUSE","    @CLOCK PAUSE"})
    public void clockPauseTest(String parse){
        try{
            main.parseTest("@CLOCK RESUME");
            if(!Clock.getInstance().isActive())fail("Failed to start clock for test");
            A_ParserHelper ph = main.parseTest(parse);
            assertFalse(Clock.getInstance().isActive());
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Does Clock Resume Work?")
    @ValueSource(strings = {"@CLOCK RESUME","@CLOCK RESUME          ","@CLOCK     RESUME","    @CLOCK RESUME"})
    public void clockResumeTest(String parse){
        try{
            main.parseTest(parse);
            assertTrue(Clock.getInstance().isActive());
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Does Clock Onestep Work?")
    @ValueSource(strings = {"@CLOCK ONESTEP","@CLOCK ONESTEP               ","@CLOCK               ONESTEP","           @CLOCK ONESTEP"})
    public void clockOnestepTest(String parse){
        try{
            int tick = Clock.getInstance().getTick();
            main.parseTest(parse);
            assertEquals(tick+1, Clock.getInstance().getTick());
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Does Clock Onestep Value Work?")
    @ValueSource(strings = {"@CLOCK ONESTEP 5","@CLOCK ONESTEP         5      ","@CLOCK               ONESTEP 5","           @CLOCK ONESTEP 5"})
    public void clockOnestepValueTest(String parse){
        try{
            int tick = Clock.getInstance().getTick();
            main.parseTest(parse);
            assertEquals(tick+5, Clock.getInstance().getTick());
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Does Clock Set Rate Value Work?")
    @ValueSource(strings = {"@CLOCK SET RATE 20","              @CLOCK SET RATE 20","@CLOCK SET RATE 20             ","@CLOCK     SET       RATE     20"})
    public void clockSetRateValueTest(String parse){
        try{
            main.parseTest(parse);
            assertEquals(20, Clock.getInstance().getRate());
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @Test
    @DisplayName("Does Run File Work?")
    public void runTest(){
        PrintStream originalErr = System.err;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{

            System.setErr(new PrintStream(bos)) ;

            main.parseTest("@RUN \"Program.txt\"");
            assertEquals(0, bos.toString().length());
            bos.close();
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
        System.setErr(originalErr);
    }
    @ParameterizedTest
    @DisplayName("Does Clock Throw A Runtime Exception?")
    @ValueSource(strings = {"@ CLOCK ONESTEP 5","CLOCK ONESTEP         5      ","@CLOCK               ONESTEP 5","           @CLO CK ONESTEP 5","@CLOCK ONSTEP 5","@CLOCK SET RATE "})
    public void clockRuntimeTest(String parse){
        assertThrows(RuntimeException.class,()->main.parseTest(parse));
    }
    @ParameterizedTest
    @DisplayName("Does Run Throw A Runtime Exception?")
    @ValueSource(strings = {"RUN MYFILENAME.TXT","@ RUN MYFILENAME.TXT","@RU N MYFILENAME.TXT","@RU MYFILENAME.TXT", "@RUN Program.txt"})
    @Timeout(3)
    public void runRuntimeTest(String parse){
        assertThrows(RuntimeException.class, () -> main.parseTest(parse));


    }

}
