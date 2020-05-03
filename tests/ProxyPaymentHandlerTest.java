import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyPaymentHandlerTest {
    private PaymentHandler cashDesk;
    private Medicine medicine;

    @BeforeEach
    void setUp() {
        cashDesk = new ProxyPaymentHandler();
        medicine = new Medicine("Paracetamolo", true, false);
    }

    @Test
    void profit() {
        assertEquals(cashDesk.getProfit(), 0);
        cashDesk.pay(medicine.getCost(), 10000, "pieno");
        assertEquals(cashDesk.getProfit(), 7.35);
        cashDesk.pay(medicine.getCost(), 10000, "pieno");
        assertEquals(cashDesk.getProfit(), 7.35 * 2);
    }

    @Test
    void pay() {
        assertEquals(((ProxyPaymentHandler) cashDesk).getCountOccurence(), 0);
        assertSame(((ProxyPaymentHandler) cashDesk).getRealPaymentHandler(), null);

        cashDesk.pay(medicine.getCost(), 10000, "pieno");

        assertEquals(((ProxyPaymentHandler) cashDesk).getCountOccurence(), 0);
        assertSame(((ProxyPaymentHandler) cashDesk).getRealPaymentHandler(), null);
    }

    @Test
    void getCountOccurence() {
        assertEquals(((ProxyPaymentHandler) cashDesk).getCountOccurence(), 0);
        assertSame(((ProxyPaymentHandler) cashDesk).getRealPaymentHandler(), null);

        cashDesk.pay(medicine.getCost(), 10000, "ridotto");
        assertEquals(((ProxyPaymentHandler) cashDesk).getCountOccurence(), 1);
        assertNotSame(((ProxyPaymentHandler) cashDesk).getRealPaymentHandler(), null);

        RealPaymentHandler rph = ((ProxyPaymentHandler) cashDesk).getRealPaymentHandler();
        cashDesk.pay(medicine.getCost(), 10000, "ridotto");
        assertEquals(((ProxyPaymentHandler) cashDesk).getCountOccurence(), 1);
        assertSame(((ProxyPaymentHandler) cashDesk).getRealPaymentHandler(), rph);
    }
}