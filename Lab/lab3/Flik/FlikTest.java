import static org.junit.Assert.*;

import org.junit.Test;


public class FlikTest {
    @Test
    public void testFlik(){
        int a = 128;
        int b = 128;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
