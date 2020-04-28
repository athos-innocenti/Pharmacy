import java.util.Scanner;

public class ProxyPaymentHandler extends PaymentHandler {
    private RealPaymentHandler realPaymentHandler;
    private Scanner scanner = new Scanner(System.in);

    public ProxyPaymentHandler() {
        super();
        System.out.println("\nAvvio pagamento...");
    }

    public double pay(int medicineCost, int isee) {
        double cost = 0;
        System.out.println("Si desidera acquistare a prezzo pieno o ridotto? (pieno o ridotto)");
        if (scanner.nextLine().equals("pieno")) {
            System.out.println("Pagamento pieno avviato");
            cost = medicineCost + ((IVA / (double) 100) * medicineCost) + (gain * medicineCost);
        } else {
            realPaymentHandler = new RealPaymentHandler();
            cost = realPaymentHandler.pay(medicineCost, isee);
        }
        return cost;
    }
}
