package sw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

public class InputHandlerTest {

	@Test
    public void testGamemodeYes() {
        String simulatedInput = "y\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        assertTrue(InputHandler.gamemode()); 
    }
	@Test
    public void testGamemodeYesCap() {
        String simulatedInput = "Y\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        assertTrue(InputHandler.gamemode()); 
    }
	@Test
    public void testGamemodeNo() {
        String simulatedInput = "n\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        assertFalse(InputHandler.gamemode()); 
    }
	@Test
    public void testGamemodeNoCap() {
        String simulatedInput = "N\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        assertFalse(InputHandler.gamemode()); 
    }
	
    @Test
    public void testGameConfigValid() {
        String simulatedInput = "4\n4\n100\n10";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        int[] config = InputHandler.gameConfig();
        assertArrayEquals(new int[]{4, 4, 10}, config);
    }

    @Test
    public void testGameConfigInvalid() {
        String simulatedInput = "7 7\n999\n36";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        InputHandler.setScanner(scanner); 
        int[] config = InputHandler.gameConfig();
        assertArrayEquals(new int[]{7, 7, 36}, config);
    }
}
