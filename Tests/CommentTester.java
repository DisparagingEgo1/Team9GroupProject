import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.startup.Startup;
import cs350s22.support.Identifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CommentTester {
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

    @Test
    @DisplayName("Does A Comment On Its Own Line Work?")
    public void commentOwnLineTest(){
        try{
            main.parseTest("//Comment Shouldn't Affect Anything");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does A Comment On The Same Line With Code And A Space Work?")
    public void commentSharedLineSpaceTest(){
        try{
            main.parseTest("CREATE MaPPER M1 EQUATION PASSTHROUGH //Comment Shouldn't Affect Anything");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
    @Test
    @DisplayName("Does A Comment On The Same Line With Code And No Space Work?")
    public void commentSharedLineNoSpaceTest(){
        try{
            main.parseTest("CREATE MaPPER M1 EQUATION PASSTHROUGH//Comment Shouldn't Affect Anything");

        }
        catch(Exception e){
            fail("Exception Thrown When It Shouldn't Have");
        }
    }
}
