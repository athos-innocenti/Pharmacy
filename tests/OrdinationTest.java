import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {
    // Ok
    private Ordination ordination;

    @BeforeEach
    void setUp() {
        ordination = new Ordination("Paracetamolo", true, 1);
    }

    @Test
    void getName() {
        assertEquals(ordination.getOrderedMedicineName(), "Paracetamolo");
    }

    @Test
    void isOrginal() {
        assertTrue(ordination.isOrginal());
    }

    @Test
    void getClientNum() {
        assertEquals(ordination.getClientNum(), 1);
    }
}