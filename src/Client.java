public class Client {
    private final String name;
    private final String surname;
    private final String fiscalCode;
    private final int isee;

    public Client(String name, String surname, String fiscalCode, int isee) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.isee = isee;
    }

    public Medicine buyMedicine() {
        Medicine medicine = new Medicine();
        System.out.println("\nIl cliente vuole acquistare il medicinale: " + medicine.getName());
        return medicine;
    }

    public int getIsee() {
        return isee;
    }
}