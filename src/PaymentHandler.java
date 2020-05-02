public abstract class PaymentHandler {
    protected static final int IVA = 22;
    protected static final double gain = 0.25;
    public double profit;

    public PaymentHandler() {
        this.profit = 0;
    }

    public abstract double pay(int medicineCost, int isee, String paymentMethod);

    public double getProfit() {
        return profit;
    }

}
