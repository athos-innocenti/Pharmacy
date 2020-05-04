import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTest {
    private Pharmacy pharmacy;
    private Pharmacist[] pharmacists;

    @BeforeEach
    void setUp() {
        pharmacists = new Pharmacist[3];
        pharmacists[0] = new Pharmacist("Paolo", "Rossi", true);
        pharmacists[1] = new Pharmacist("Francesco", "Innocenti", false);
        pharmacists[2] = new Pharmacist("Giulia", "Esposito", false);
        pharmacy = Pharmacy.getIstance(3, "Fornacelle", pharmacists);
    }

    @Test
    void getIstance(){
        Pharmacy pharmacyTestSingleton = Pharmacy.getIstance(3, "Fornacelle", pharmacists);
        assertSame(pharmacy, pharmacyTestSingleton);
    }

    @Test
    void sellMedicine() {
        // TO DO
    }

    @Test
    void update() {
        // TO DO
    }

    @Test
    void getTotalGain() {
        assertEquals(pharmacy.getTotalGain(), 0);
    }

    @Test
    void getNumberOfEmployees() {
        assertEquals(pharmacy.getNumberOfEmployees(), 3);
    }

    @Test
    void getName() {
        assertEquals(pharmacy.getName(), "Fornacelle");
    }

    @Test
    void getPharmacists() {
        assertEquals(pharmacy.getPharmacists()[0].getName(), "Paolo");
        assertEquals(pharmacy.getPharmacists()[0].getSurname(), "Rossi");
        assertTrue(pharmacy.getPharmacists()[0].isDirector());
        assertEquals(pharmacy.getPharmacists()[1].getName(), "Francesco");
        assertEquals(pharmacy.getPharmacists()[1].getSurname(), "Innocenti");
        assertFalse(pharmacy.getPharmacists()[1].isDirector());
        assertEquals(pharmacy.getPharmacists()[2].getName(), "Giulia");
        assertEquals(pharmacy.getPharmacists()[2].getSurname(), "Esposito");
        assertFalse(pharmacy.getPharmacists()[2].isDirector());
        assertEquals(pharmacy.getPharmacists().length, pharmacy.getNumberOfEmployees());
    }

    @Test
    void getDirector() {
        assertEquals(pharmacy.getDirector().getName(), "Paolo");
        assertEquals(pharmacy.getDirector().getSurname(), "Rossi");
        assertTrue(pharmacy.getDirector().isDirector());
    }

    @Test
    void getTotalClients() throws FullWarehouseException {
        assertEquals(pharmacy.getTotalClients(), 0);
    }

    @Test
    void getWarehouse() {
        assertTrue(pharmacy.getWarehouse().getMedicinesStored() > 0);
        assertTrue(pharmacy.getWarehouse().getMedicinesStored() <= Warehouse.getMaxCapacity());
    }
}