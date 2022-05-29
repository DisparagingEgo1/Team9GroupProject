import cs350s22.startup.Startup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class NetworkTester {
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
}
