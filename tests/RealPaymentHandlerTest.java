import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealPaymentHandlerTest {
    // Ok
    private RealPaymentHandler cashDesk;
    private String paymentMethod;

    @BeforeEach
    void setUp() {
        cashDesk = new RealPaymentHandler();
        paymentMethod = "ridotto";
    }

    @Test
    void pay() {
        assertEquals(cashDesk.pay(5, 742501, paymentMethod), 7.35);
        assertEquals(cashDesk.pay(5, 742499, paymentMethod), 6.1);
        assertEquals(cashDesk.pay(5, 247501, paymentMethod), 4.85);
        assertEquals(cashDesk.pay(5, 247499, paymentMethod), 3.59);
    }
}