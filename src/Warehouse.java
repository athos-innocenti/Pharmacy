import java.util.ArrayList;

public class Warehouse extends Subject {
    private int medicinesStored;
    private static final int MAX_CAPACITY = 100;
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public Warehouse(int medicinesStored) {
        this.medicinesStored = medicinesStored;
        for (int i = 0; i < medicinesStored; i++) {
            medicines.add(new Medicine());
        }
        System.out.println("\nIl magazzino contiene inizialmente " + medicinesStored + " medicine");
    }

    public boolean isAvailable(Medicine medicine) {
        boolean isAvailable = false;
        for (int i = 0; i < medicines.size(); i++) {
            if (medicine.getName().equals(medicines.get(i).getName()) && medicine.isOriginal() == medicines.get(i).isOriginal()) {
                isAvailable = true;
                medicines.remove(i);
                medicinesStored--;
                break;
            }
        }
        return isAvailable;
    }

    public void requireMedicine(Medicine medicine) throws FullWarehouseException {
        if (medicinesStored < MAX_CAPACITY) {
            System.out.println("Il medicinale: " + medicine.getName() + " è stato richiesto alla casa farmaceutica");
            // Creare medicinale mancante -> FACTORY
            // medicinale mancante è ora disponibile -> OBSERVER
        } else
            throw new FullWarehouseException();
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public int getMedicinesStored() {
        return medicinesStored;
    }
}