public class ProxyPaymentHandler extends PaymentHandler {
    private RealPaymentHandler realPaymentHandler;
    private int countOccurence;

    ProxyPaymentHandler() {
        super();
        this.countOccurence = 0;
        System.out.println("\nAvvio pagamento...");
    }

    public double pay(int medicineCost, int isee, String paymentMethod) {
        double cost;
        if (paymentMethod.equals("pieno")) {
            System.out.println("Pagamento " + paymentMethod + " avviato");
            cost = medicineCost + ((IVA / (double) 100) * medicineCost) + (gain * medicineCost);
            profit += cost;
        } else {
            if (countOccurence == 0) {
                realPaymentHandler = new RealPaymentHandler();
                countOccurence++;
            }
            cost = realPaymentHandler.pay(medicineCost, isee, paymentMethod);
            profit += cost;
        }
        return cost;
    }

    int getCountOccurence() {
        return countOccurence;
    }

    RealPaymentHandler getRealPaymentHandler() {
        return realPaymentHandler;
    }
}
