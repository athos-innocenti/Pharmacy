import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        Client client = new Client("Paolo", "Rossi", "fiscalCode", 10000);
        reservation = new Reservation(client, "Paracetamolo", true);
    }

    @Test
    void getClientIdentifier() {
        assertEquals(reservation.getClientIdentifier().getName(), "Paolo");
        assertEquals(reservation.getClientIdentifier().getSurname(), "Rossi");
        assertEquals(reservation.getClientIdentifier().getFiscalCode(), "fiscalCode");
        assertEquals(reservation.getClientIdentifier().getIsee(), 10000);
    }

    @Test
    void getDesiredMedicineName() {
        assertEquals(reservation.getDesiredMedicineName(), "Paracetamolo");
    }

    @Test
    void isDesiredMedicineOriginal() {
        assertTrue(reservation.isDesiredMedicineOriginal());
    }
}