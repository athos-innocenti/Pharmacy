import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    class TestObserver implements Observer {
        boolean called  = false;
        @Override
        public void update(Observable o, Object arg) {
            called = true;
        }
    }

    private TestObserver testObserver = new TestObserver();
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse(50);
    }

    @Test
    void isAvailable() {
        String medicineName = warehouse.getMedicines().get(0).getName();
        boolean isMedicineOriginal = warehouse.getMedicines().get(0).isOriginal();
        int medicineStoredBeforeSell = warehouse.getMedicinesStored();
        int lengthBeforeSell = warehouse.getMedicines().size();
        boolean isAvailable = warehouse.isAvailable(medicineName, isMedicineOriginal);
        assertTrue(isAvailable);
        assertEquals(warehouse.getMedicinesStored(), medicineStoredBeforeSell - 1);
        assertEquals(warehouse.getMedicines().size(), lengthBeforeSell - 1);
        assertEquals(warehouse.getSoldMedicine().getName(), medicineName);
        assertEquals(warehouse.getSoldMedicine().isOriginal(), isMedicineOriginal);
    }

    @Test
    void addRequiredMedicine() {
        int lengthBeforeAddition = warehouse.getRequiredMedicines().size();
        warehouse.addRequiredMedicine("Paracetamolo", true, 2);
        assertEquals(warehouse.getRequiredMedicines().size(), lengthBeforeAddition + 1);
        assertEquals(warehouse.getRequiredMedicines().get(lengthBeforeAddition).getOrderedMedicineName(), "Paracetamolo");
        assertEquals(warehouse.getRequiredMedicines().get(lengthBeforeAddition).getClientNum(), 2);
        assertTrue(warehouse.getRequiredMedicines().get(lengthBeforeAddition).isOrginal());
    }

    @Test
    void createRequiredMedicine() throws FullWarehouseException{
        warehouse.addRequiredMedicine("Paracetamolo", true, 1);
        int sizeBeforeCreation = warehouse.getMedicines().size();
        int numStoredBeforeCreation = warehouse.getMedicinesStored();
        int numRequiredBeforeCreation = warehouse.getRequiredMedicines().size();
        warehouse.createRequiredMedicine(1, 2);
        assertEquals(warehouse.getMedicines().get(warehouse.getMedicines().size() - 1).getName(), "Paracetamolo");
        assertTrue(warehouse.getMedicines().get(warehouse.getMedicines().size() - 1).isOriginal());
        assertTrue(warehouse.getMedicines().get(warehouse.getMedicines().size() - 1).isReserved());
        assertEquals(warehouse.getMedicinesStored(), numStoredBeforeCreation + 1);
        assertEquals(warehouse.getMedicines().size(), sizeBeforeCreation + 1);
        assertEquals(warehouse.getRequiredMedicines().size(), numRequiredBeforeCreation - 1);
    }

    @Test
    void getCreatedMedicineIndex() throws FullWarehouseException{
        warehouse.addRequiredMedicine("Paracetamolo", true, 1);
        warehouse.createRequiredMedicine(1, 2);
        assertEquals(warehouse.getCreatedMedicineIndex(), 0);
    }

    @Test
    void getSoldMedicine() {
        String medicineName = warehouse.getMedicines().get(0).getName();
        boolean isMedicineOriginal = warehouse.getMedicines().get(0).isOriginal();
        warehouse.isAvailable(medicineName, isMedicineOriginal);
        assertEquals(warehouse.getSoldMedicine().getName(), medicineName);
        assertEquals(warehouse.getSoldMedicine().isOriginal(), isMedicineOriginal);
        assertFalse(warehouse.getSoldMedicine().isReserved());
    }

    @Test
    void getMedicines() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        for (int i = 0; i < warehouse.getMedicines().size(); i++) {
            medicines.add(new Medicine(warehouse.getMedicines().get(i)));
        }
        assertEquals(medicines.size(), warehouse.getMedicines().size());
        for (int i = 0; i < warehouse.getMedicines().size(); i++) {
            assertEquals(medicines.get(i).getName(), warehouse.getMedicines().get(i).getName());
            assertEquals(medicines.get(i).getCost(), warehouse.getMedicines().get(i).getCost());
            assertEquals(medicines.get(i).isOriginal(), warehouse.getMedicines().get(i).isOriginal());
            assertEquals(medicines.get(i).isReserved(), warehouse.getMedicines().get(i).isReserved());
        }
    }

    @Test
    void getRequiredMedicines() {
        assertEquals(warehouse.getRequiredMedicines().size(), 0);

        warehouse.addRequiredMedicine("Paracetamolo", true, 1);
        assertEquals(warehouse.getRequiredMedicines().size(), 1);
        assertEquals(warehouse.getRequiredMedicines().get(0).getOrderedMedicineName(), "Paracetamolo");
        assertEquals(warehouse.getRequiredMedicines().get(0).getClientNum(), 1);
        assertTrue(warehouse.getRequiredMedicines().get(0).isOrginal());
    }

    @Test
    void getMaxCapacity() {
        assertEquals(Warehouse.getMaxCapacity(), 100);
    }

    @Test
    void getMedicinesStored() throws FullWarehouseException {
        assertTrue(warehouse.getMedicinesStored() > 0 && warehouse.getMedicinesStored() <= Warehouse.getMaxCapacity());

        int medicineStoredBeforeSell = warehouse.getMedicinesStored();
        Medicine medicine = warehouse.getMedicines().get(0);
        warehouse.isAvailable(medicine.getName(), medicine.isOriginal());
        assertEquals(warehouse.getMedicinesStored(), medicineStoredBeforeSell - 1);

        int medicineStoredBeforeCreation = warehouse.getMedicinesStored();
        warehouse.addRequiredMedicine("Paracetamolo", true, 1);
        warehouse.createRequiredMedicine(1, 2);
        assertEquals(warehouse.getMedicinesStored(), medicineStoredBeforeCreation + 1);
    }

    @Test
    void callUpdate() throws FullWarehouseException{
        warehouse.addObserver(testObserver);
        assertEquals(warehouse.countObservers(), 1);
        warehouse.addRequiredMedicine("Paracetamolo", true, 1);
        warehouse.createRequiredMedicine(1, 2);
        assertTrue(testObserver.called);
    }
}