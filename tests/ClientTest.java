import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client1, client2;

    @BeforeEach
    void setUp() {
        client1 = new Client("Paolo", "Rossi", "fiscalCode", 10000);
        client2 = new Client(client1);
    }

    @Test
    void selectDesiredMedicineName() throws FileNotFoundException {
        String medicineName = client1.selectDesiredMedicineName();
        boolean nameFound = false;
        Scanner scannerName = new Scanner(new File("src/data/medicinesList.txt"));
        while (scannerName.hasNextLine()) {
            if (scannerName.nextLine().equals(medicineName)) {
                nameFound = true;
                break;
            }
            scannerName.nextLine();
        }
        scannerName.close();
        assertTrue(nameFound);
    }

    @Test
    void getName() {
        assertEquals(client1.getName(), "Paolo");
        assertEquals(client1.getName(), client2.getName());
    }

    @Test
    void getSurname() {
        assertEquals(client1.getSurname(), "Rossi");
        assertEquals(client1.getSurname(), client2.getSurname());
    }

    @Test
    void getIsee() {
        assertEquals(client1.getIsee(), 10000);
        assertEquals(client1.getIsee(), client2.getIsee());
    }

    @Test
    void getFiscalCode() {
        assertEquals(client1.getFiscalCode(), "fiscalCode");
        assertEquals(client1.getFiscalCode(), client2.getFiscalCode());
    }

    @Test
    void notEqualObjects() {
        assertNotSame(client1, client2);
    }
}