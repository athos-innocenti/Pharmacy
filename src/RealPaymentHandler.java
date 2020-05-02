import java.math.BigDecimal;

public class RealPaymentHandler extends PaymentHandler {

    RealPaymentHandler() {
        super();
    }

    public double pay(int medicineCost, int isee, String paymentMethod) {
        System.out.println("Pagamento " + paymentMethod + " avviato");
        int noBracket = incomeBracket(isee);
        double cost = medicineCost + ((IVA / (double) 100) * medicineCost) + (gain * medicineCost) - ((((4 - noBracket) * 25) / (double) 100) * medicineCost);
        BigDecimal bd = new BigDecimal(Double.toString(cost));
        bd = bd.setScale(2, BigDecimal.ROUND_FLOOR);
        return bd.doubleValue();
    }

    private int incomeBracket(int isee) {
        if (isee < 495000) {
            return isee < 247500 ? 1 : 2;
        } else {
            return isee > 742500 ? 4 : 3;
        }
    }
}