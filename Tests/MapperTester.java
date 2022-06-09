import cs350s22.component.sensor.mapper.MapperEquation;
import cs350s22.component.sensor.mapper.function.equation.A_Equation;
import cs350s22.component.sensor.mapper.function.equation.EquationNormalized;
import cs350s22.component.sensor.mapper.function.equation.EquationScaled;
import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
/*

 */
public class MapperTester {
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
    @DisplayName("Can We Create a Passthrough Mapper?")
    @ValueSource(strings = {"CREATE MaPPER M1 EQUATION PASSTHROUGH","        create MAPPER M1 EQUATION PASSTHROUGH","CREATE MAPPER M1 EQUATION PASSTHROUGH       ","CREATE           MAPPER     M1     EQUATION     PASSTHROUGH"})
    public void mapperPassthroughTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("M1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Can We Create a Scale Mapper?")
    @ValueSource(strings = {"CREATE MAPPER M1 EQUATION SCALE 1.0","              CREATE MAPPER M1 equation SCALE 1.0","CREATE MAPPER M1 EQUATION SCALE 1.0                 ","CREATE       MAPPER      M1     EQUATION SCALE    1.0"})
    public void mapperScaleTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("M1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @Test
    @DisplayName("Does Mapper Assign Normalize Values Correctly?")
    public void mapperNormalizeValuesTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE MAPPER M1 EQUATION NORMALIZE 1.4 2.2 ");
            MapperEquation theMapper = (MapperEquation) ph.getSymbolTableMapper().get(Identifier.make("M1"));
            EquationNormalized theEquation = (EquationNormalized)theMapper.getEquation();
            if(!(1.4==theEquation.getValueMin() && 2.2 == theEquation.getValueMax())){
                fail("Bad Normalize Values");
            }

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does Mapper Assign Scale Value Correctly?")
    public void mapperScaleValueTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE MAPPER M1 EQUATION SCALE 1.4");
            MapperEquation theMapper = (MapperEquation) ph.getSymbolTableMapper().get(Identifier.make("M1"));
            EquationScaled theEquation = (EquationScaled)theMapper.getEquation();
            if(!(1.4==theEquation.getScaleFactor())){
                fail("Bad Scale Value");
            }

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @ParameterizedTest
    @DisplayName("Can We Create a Normalize Mapper?")
    @ValueSource(strings = {"create MAPPER M1 EQUATION NORMALIZE 1.0 2.0","                   CREATE MAPPER M1 EQUATION NORMALIZE 1.0 2.0","CREATE MAPPER M1 EQUATION NORMALIZE 1.0 2.0              ","CREATE        MAPPER        M1      EQUATION     NORMALIZE   1.0     2.0"})
    public void mapperNormalizeTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("M1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Can We Create a Linear Interpolation Mapper?")
    @ValueSource(strings = {"CREATE MAPPER M1 INTERPOLATION LINEAR DEFINITION \"MAPFILE.map\"","                CREATE MAPPER M1 INTERPOLATION LINEAR DEFINITION \"MAPFILE.map\"","CREATE MAPPER M1 INTERPOLATION LINEAR DEFINITION \"MAPFILE.map\"           ","CREATE    MAPPER    M1         INTERPOLATION     LINEAR   DEFINITION   \"MAPFILE.map\""})
    public void mapperLinearInterpolationTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("M1")));
        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Can We Create a Spline Interpolation Mapper?")
    @ValueSource(strings = {"CREATE MAPPER M1 INTERPOLATION SPLINE DEFINITION \"MAPFILE.map\"","          CREATE MAPPER M1 INTERPOLATION SPLINE DEFINITION \"MAPFILE.map\"","CREATE MAPPER M1 INTERPOLATION SPLINE DEFINITION \"MAPFILE.map\"            ","CREATE       MAPPER       M1      INTERPOLATION     SPLINE     DEFINITION     \"MAPFILE.map\""})
    public void mapperSplineInterpolationTest(String parse){
        try{
            A_ParserHelper ph = main.parseTest(parse);
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("M1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }

    }
    @ParameterizedTest
    @DisplayName("Does Mapper Throw a Runtime Exception?")
    @ValueSource(strings = {"REATE MAPPER M1 INTERPOLATION SPLINE DEFINITION \"MAPFILE.map\"","CREATE MPPER M1 EQUATION SCALE 1.0 ","CREATE MAPPER EQUATION NORMALIZE 1.0 2.0 ","CREATE MAPPER M1 INTERPOLATION DEFINITION MAPFILE.map "
    ,"CREATE MAPPER M1 INTERPOLATION SPLINE DEFINITION","CREATE MAPPER M1 EQUATION NORMALIZE 1.0 ","CREATE MAPPER M1 EQUATION ","CREATE","CREATE MAPPER M1 EQUATION SCALE","CREATE MAPPER M1 EQUATION NORMALIZE 1.0 2.0 bad Text","CREATE MAPPER \"M1\" EQUATION NORMALIZE 1.0 2.0"})
    public void badMapperTest(String parse){
        Exception e = assertThrows(RuntimeException.class,()->main.parseTest(parse));
        assertEquals(RuntimeException.class,e.getClass());

    }
    @Test
    @DisplayName("Does Mapper Correctly Create Its ID with lower case")
    public void mapperLowerCaseIDTest(){
        try{
            A_ParserHelper ph = main.parseTest("CREATE MAPPER m1 EQUATION PASSTHROUGH");
            assertTrue(ph.getSymbolTableMapper().contains(Identifier.make("m1")));

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }

}
