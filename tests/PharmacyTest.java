import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTest {
    private Pharmacy pharmacy;
    private Pharmacist[] pharmacists;
    private Client client;

    @BeforeEach
    void setUp() {
        pharmacists = new Pharmacist[3];
        pharmacists[0] = new Pharmacist("Paolo", "Rossi", true);
        pharmacists[1] = new Pharmacist("Francesco", "Innocenti", false);
        pharmacists[2] = new Pharmacist("Giulia", "Esposito", false);
        pharmacy = Pharmacy.getIstance(3, "Fornacelle", pharmacists);
        client = new Client("Giulio", "Rossi", "a", 50000);
    }

    @Test
    void getIstance(){
        Pharmacy pharmacyTestSingleton = Pharmacy.getIstance(3, "Fornacelle", pharmacists);
        assertSame(pharmacy, pharmacyTestSingleton);
    }

    @Test
    void addReservation() {
        int size = pharmacy.getClientsReservations().size();
        pharmacy.addReservation(client, "Paracetamolo", false);
        assertEquals(pharmacy.getClientsReservations().size(), size + 1);
        assertEquals(pharmacy.getClientsReservations().get(size).getClientIdentifier().getName(), "Giulio");
        assertEquals(pharmacy.getClientsReservations().get(size).getClientIdentifier().getSurname(), "Rossi");
        assertEquals(pharmacy.getClientsReservations().get(size).getClientIdentifier().getFiscalCode(), "a");
        assertEquals(pharmacy.getClientsReservations().get(size).getClientIdentifier().getIsee(), 50000);
        assertEquals(pharmacy.getClientsReservations().get(size).getDesiredMedicineName(), "Paracetamolo");
        assertFalse(pharmacy.getClientsReservations().get(size).isDesiredMedicineOriginal());
    }

    @Test
    void handleRequiredMedicines() throws FullWarehouseException{
        int size = pharmacy.getWarehouse().getMedicinesStored();
        if (pharmacy.handleRequiredMedicines(client)) {
            assertEquals(pharmacy.getWarehouse().getMedicinesStored(), size);
        } else {
            assertEquals(pharmacy.getWarehouse().getMedicinesStored(), size);
        }
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
    void getTotalClients() {
        assertEquals(pharmacy.getTotalClients(), 0);
    }

    @Test
    void getWarehouse() {
        assertTrue(pharmacy.getWarehouse().getMedicinesStored() > 0);
        assertTrue(pharmacy.getWarehouse().getMedicinesStored() <= Warehouse.getMaxCapacity());
    }
}