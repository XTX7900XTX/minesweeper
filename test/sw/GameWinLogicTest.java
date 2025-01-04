package sw;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;
import org.junit.Test;

public class GameWinLogicTest {
    @Test
    public void testWonCase() throws Exception {
        Method wonMethod = GameWinLogic.class.getDeclaredMethod("won", int[][].class, boolean[][].class);
        wonMethod.setAccessible(true);

        boolean expectedTrue = true;

        int[][] field1 = { {} };
        boolean[][] exposed1 = { {} };
        boolean actual1 = (boolean) wonMethod.invoke(null, field1, exposed1);
        assertEquals("Empty array is a win", expectedTrue, actual1);

        int[][] field2 = { { 0 } };
        boolean[][] exposed2 = { { true } };
        boolean actual2 = (boolean) wonMethod.invoke(null, field2, exposed2);
        assertEquals("All exposed with no bombs is a win", expectedTrue, actual2);

        int[][] field5 = { { -1 } };
        boolean[][] exposed5 = { { false } };
        boolean actual5 = (boolean) wonMethod.invoke(null, field5, exposed5);
        assertEquals("All exposed except bombs is a win", expectedTrue, actual5);

        int[][] field7 = { { -1, -1, -1 }, { -1, -1, 3 } };
        boolean[][] exposed7 = { { false, false, false }, { false, false, true } };
        boolean actual7 = (boolean) wonMethod.invoke(null, field7, exposed7);
        assertEquals("All exposed except bombs is a win", expectedTrue, actual7);
    }
    
    @Test
    public void testLoseCase() throws Exception {
        Method wonMethod = GameWinLogic.class.getDeclaredMethod("won", int[][].class, boolean[][].class);
        wonMethod.setAccessible(true);

        boolean expectedFalse = false;
        
        int[][] field3 = { { -1 } };
        boolean[][] exposed3 = { { true } };
        boolean actual3 = (boolean) wonMethod.invoke(null, field3, exposed3);
        assertEquals("All exposed with all bombs is a loss", expectedFalse, actual3);

        int[][] field4 = { { 0 } };
        boolean[][] exposed4 = { { false } };
        boolean actual4 = (boolean) wonMethod.invoke(null, field4, exposed4);
        assertEquals("Not exposed is not a win", expectedFalse, actual4);

        int[][] field6 = { { 1, 1, 1, 0 }, { 1, -1, 1, 0 } };
        boolean[][] exposed6 = { { true, true, true, false }, { true, false, true, false } };
        boolean actual6 = (boolean) wonMethod.invoke(null, field6, exposed6);
        assertEquals("Not exposed is not a win", expectedFalse, actual6);
    }
}
