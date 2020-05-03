import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MedicineTest {
    // Ok
    private Medicine medicine1, medicine2, medicine3;
    private int countLines;

    @BeforeEach
    void setUp() {
        this.countLines = 0;
        medicine1 = new Medicine();
        medicine2 = new Medicine("Paracetamolo", false, true);
        medicine3 = new Medicine(medicine1);
    }

    @Test
    void setRand() throws FileNotFoundException {
        Scanner scannerRand = new Scanner(new File("src/data/medicinesList.txt"));
        while (scannerRand.hasNextLine()) {
            countLines++;
            scannerRand.nextLine();
        }
        scannerRand.close();

        int rand = Medicine.setRand();
        assertTrue( rand > 0 && rand <= countLines);
    }

    @Test
    void setMedicineName() throws FileNotFoundException{
        String medicineNameExpected = Medicine.setMedicineName(Medicine.setRand());
        assertTrue(findName(medicineNameExpected));
    }

    @Test
    void getName() throws FileNotFoundException{
        assertTrue(findName(medicine1.getName()));
        assertEquals(medicine2.getName(), "Paracetamolo");
        assertEquals(medicine3.getName(), medicine1.getName());
    }

    @Test
    void getCost() throws FileNotFoundException {
        assertTrue(findCost(medicine1.getCost()));
        assertTrue(findCost(medicine2.getCost()));
        assertEquals(medicine2.getCost(), 5);
        assertEquals(medicine3.getCost(), medicine1.getCost());
    }

    @Test
    void isOriginal() {
        assertFalse(medicine2.isOriginal());
        assertEquals(medicine3.isOriginal(), medicine1.isOriginal());
    }

    @Test
    void isReserved() {
        assertFalse(medicine1.isReserved());
        assertTrue(medicine2.isReserved());
        assertFalse(medicine3.isReserved());
    }

    @Test
    void notEqualObjects() {
        assertNotSame(medicine1, medicine3);
    }

    private boolean findName(String medicineName) throws FileNotFoundException {
        boolean found = false;
        Scanner scannerName = new Scanner(new File("src/data/medicinesList.txt"));
        while (scannerName.hasNextLine()) {
            if (scannerName.nextLine().equals(medicineName)) {
                found = true;
                break;
            }
            scannerName.nextLine();
        }
        scannerName.close();
        return found;
    }

    private boolean findCost(int medicineCost) throws  FileNotFoundException {
        boolean found = false;
        Scanner scanner = new Scanner(new File("src/data/medicinesList.txt"));
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            if (Integer.parseInt(scanner.nextLine()) == medicineCost) {
                found = true;
                break;
            }
        }
        scanner.close();
        return found;
    }
}