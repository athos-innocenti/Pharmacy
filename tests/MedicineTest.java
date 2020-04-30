import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MedicineTest {
    private Medicine medicine1, medicine2, medicine3;

    @BeforeEach
    void setUp() {
        medicine1 = new Medicine();
        medicine2 = new Medicine("Paracetamolo", false);
        medicine3 = new Medicine(medicine1);
    }

    @Test
    void setRand() throws FileNotFoundException {

        Scanner scannerRand = new Scanner(new File("src/data/medicinesList.txt"));
        int countLines = 0;
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

        boolean found = false;
        Scanner scanner = new Scanner(new File("src/data/medicinesList.txt"));
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            if (Integer.parseInt(scanner.nextLine()) == medicine1.getCost()) {
                found = true;
                break;
            }
            scanner.nextLine();
        }
        scanner.close();

        assertTrue(found);
        assertEquals(medicine2.getCost(), 5);
        assertEquals(medicine3.getCost(), medicine1.getCost());
    }

    @Test
    void isOriginal() {
        assertFalse(medicine2.isOriginal());
        assertEquals(medicine3.isOriginal(), medicine1.isOriginal());
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
}