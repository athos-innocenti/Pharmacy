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

    public Client(Client c) {
        this.name = c.getName();
        this.surname = c.getSurname();
        this.fiscalCode = c.getFiscalCode();
        this.isee = c.getIsee();
    }

    public String selectDesiredMedicineName() {
        String nameDesriedMedicine = Medicine.setMedicineName(Medicine.setRand());
        System.out.println("\n" + getName() + " vuole acquistare la medicina: " + nameDesriedMedicine);
        return nameDesriedMedicine;
    }

    public boolean selectIsDesiredMedicineOriginal() {
        return Medicine.setIsOriginal();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getIsee() {
        return isee;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }
}