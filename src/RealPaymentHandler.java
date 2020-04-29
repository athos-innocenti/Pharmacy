public class RealPaymentHandler extends PaymentHandler {

    public RealPaymentHandler() {
        super();
    }

    public double pay(int medicineCost, int isee) {
        System.out.println("Pagamento con agevolazione avviato");
        int noBracket = incomeBracket(isee);
        return medicineCost + ((IVA / (double) 100) * medicineCost) + (gain * medicineCost) - ((((4 - noBracket) * 25) / (double) 100) * medicineCost);
    }

    private int incomeBracket(int isee) {
        if (isee < 495000) {
            return isee < 247500 ? 1 : 2;
        } else {
            return isee > 866250 ? 4 : 3;
        }
    }
}