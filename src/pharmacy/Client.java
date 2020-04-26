package com.pharmacy;

import java.util.ArrayList;

public class Client {
    private String name;
    private String surname;
    private String fiscalCode;
    private ArrayList<Medicine> requestedMedicines;
    private int isee;

    public Client(String name, String surname, String fiscalCode, int isee) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.isee = isee;
        this.requestedMedicines = null;
    }

    // è compito della farmacia
    public void addRequestedMedicine(Medicine medicine) {
        requestedMedicines.add(medicine);
    }

    // è compito della farmacia
    public void removeRequestedMedicine(Medicine medicine) {
        for (int i = 0; i < requestedMedicines.size(); i++) {
            if (requestedMedicines.get(i).name.equals(medicine.name)) {
                requestedMedicines.remove(i);
            }
        }
    }

    /*
    public Medicine buyMedicine() {
        Medicine medicine = new Medicine();
        return medicine;
    }
    */
}
