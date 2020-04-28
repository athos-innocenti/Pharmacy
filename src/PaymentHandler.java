public abstract class PaymentHandler {
    protected static final int IVA = 22;
    protected static final double gain = 0.25;

    public PaymentHandler() {
    }

    public abstract double pay(int medicineCost, int isee);
}
