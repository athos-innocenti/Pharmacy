import java.math.BigDecimal;
import java.util.ArrayList;

class Receipt {
    private ArrayList<Medicine> purchasedMedicines;
    private double totalCost;
    private int numMedicinesBought;

    Receipt() {
        this.purchasedMedicines =  new ArrayList<>();
        this.totalCost = 0;
        this.numMedicinesBought = 0;
    }

    void addPurchasedMedicine(String medicineName, boolean isOriginal, double cost) {
        purchasedMedicines.add(new Medicine(medicineName, isOriginal, false));
        totalCost += cost;
        numMedicinesBought++;
    }

    void printPurchasedMedicines(String name) {
        System.out.println(name + " ha acquistato " + getNumMedicinesBought() + " medicine:");
        for (Medicine m: purchasedMedicines) {
            System.out.println(m.getName());
        }
    }

    double getTotalCost() {
        BigDecimal bd = new BigDecimal(Double.toString(totalCost));
        bd = bd.setScale(2, BigDecimal.ROUND_FLOOR);
        return bd.doubleValue();
    }

    ArrayList<Medicine> getPurchasedMedicines() {
        return purchasedMedicines;
    }

    int getNumMedicinesBought() {
        return numMedicinesBought;
    }
}
