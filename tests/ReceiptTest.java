import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    // Ok
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
    }

    @Test
    void addPurchasedMedicine() {
        assertEquals(receipt.getPurchasedMedicines().size(), 0);
        receipt.addPurchasedMedicine("Paracetamolo", true, 5);
        assertEquals(receipt.getPurchasedMedicines().size(), 1);
        assertEquals(receipt.getPurchasedMedicines().get(0).getName(), "Paracetamolo");
        assertTrue(receipt.getPurchasedMedicines().get(0).isOriginal());
        assertFalse(receipt.getPurchasedMedicines().get(0).isReserved());
        assertEquals(receipt.getPurchasedMedicines().get(0).getCost(), 5);
        assertEquals(receipt.getTotalCost(), receipt.getPurchasedMedicines().get(0).getCost());
    }

    @Test
    void getTotalCost() {
        assertEquals(receipt.getTotalCost(), 0);
        receipt.addPurchasedMedicine("Paracetamolo", true, 5);
        assertEquals(receipt.getTotalCost(), 5);
    }

    @Test
    void getNumMedicinesBought() {
        assertEquals(receipt.getNumMedicinesBought(), 0);
        receipt.addPurchasedMedicine("Paracetamolo", true, 5);
        assertEquals(receipt.getNumMedicinesBought(), 1);
    }
}