public abstract class PaymentHandler {
    static final int IVA = 22;
    static final double gain = 0.25;
    double profit;

    PaymentHandler() {
        this.profit = 0;
    }

    public abstract double pay(int medicineCost, int isee, String paymentMethod);

    double getProfit() {
        return profit;
    }

}
