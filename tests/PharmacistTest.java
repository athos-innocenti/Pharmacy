import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacistTest {
    private Pharmacist pharmacist;

    @BeforeEach
    void setUp() {
        pharmacist = new Pharmacist("Paolo", "Rossi", true);
    }

    @Test
    void getName() {
        assertEquals(pharmacist.getName(), "Paolo");
    }

    @Test
    void getSurname() {
        assertEquals(pharmacist.getSurname(), "Rossi");
    }

    @Test
    void isDirector() {
        assertTrue(pharmacist.isDirector());
    }
}