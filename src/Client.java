public class Client {
    private final String name;
    private final String surname;
    private final String fiscalCode;
    private final int isee;

    Client(String name, String surname, String fiscalCode, int isee) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.isee = isee;
    }

    Client(Client c) {
        this.name = c.getName();
        this.surname = c.getSurname();
        this.fiscalCode = c.getFiscalCode();
        this.isee = c.getIsee();
    }

    String selectDesiredMedicineName() {
        String nameDesriedMedicine = Medicine.setMedicineName(Medicine.setRand());
        System.out.println("\n" + getName() + " vuole acquistare la medicina: " + nameDesriedMedicine);
        return nameDesriedMedicine;
    }

    boolean selectIsDesiredMedicineOriginal() {
        return Medicine.setIsOriginal();
    }

    public String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    int getIsee() {
        return isee;
    }

    String getFiscalCode() {
        return fiscalCode;
    }
}