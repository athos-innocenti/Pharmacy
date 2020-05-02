public class ProxyPaymentHandler extends PaymentHandler {
    private RealPaymentHandler realPaymentHandler;

    public ProxyPaymentHandler() {
        super();
        System.out.println("\nAvvio pagamento...");
    }

    public double pay(int medicineCost, int isee, String paymentMethod) {
        double cost;
        if (paymentMethod.equals("pieno")) {
            System.out.println("Pagamento " + paymentMethod + " avviato");
            cost = medicineCost + ((IVA / (double) 100) * medicineCost) + (gain * medicineCost);
            profit += cost;
        } else {
            if (realPaymentHandler == null) {
                realPaymentHandler = new RealPaymentHandler();
            }
            cost = realPaymentHandler.pay(medicineCost, isee, paymentMethod);
            profit += cost;
        }
        return cost;
    }
}
