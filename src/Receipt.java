import java.math.BigDecimal;
import java.util.ArrayList;

class Receipt {
    private ArrayList<Medicine> purchasedMedicines;
    private double totalCost;

    Receipt() {
        this.purchasedMedicines =  new ArrayList<>();
        this.totalCost = 0;
    }

    void addPurchasedMedicine(String medicineName, boolean isOriginal, double cost) {
        purchasedMedicines.add(new Medicine(medicineName, isOriginal, false));
        totalCost += cost;
    }

    void getPurchasedMedicines(String name) {
        System.out.println(name + " ha acquistato:");
        for (Medicine m: purchasedMedicines) {
            System.out.println(m.getName());
        }
    }

    double getTotalCost() {
        BigDecimal bd = new BigDecimal(Double.toString(totalCost));
        bd = bd.setScale(2, BigDecimal.ROUND_FLOOR);
        return bd.doubleValue();
    }
}
