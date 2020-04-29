import java.util.ArrayList;
import java.util.Observable;

public class Warehouse extends Observable {
    private int medicinesStored;
    private static final int MAX_CAPACITY = 100;
    private ArrayList<Medicine> medicines = new ArrayList<>();
    private Medicine soldMedicine, requiredMedicine;

    public Warehouse(int medicinesStored) {
        this.medicinesStored = medicinesStored;
        for (int i = 0; i < medicinesStored; i++) {
            medicines.add(new Medicine());
        }
        System.out.println("\nIl magazzino contiene inizialmente " + medicinesStored + " medicine");
    }

    public boolean isAvailable(String medicineName, boolean isMedicineOriginal) {
        boolean isAvailable = false;
        for (int i = 0; i < medicines.size(); i++) {
            if (medicineName.equals(medicines.get(i).getName()) && isMedicineOriginal == medicines.get(i).isOriginal()) {
                isAvailable = true;
                soldMedicine = new Medicine(medicines.get(i));
                medicines.remove(i);
                medicinesStored--;
                break;
            }
        }
        return isAvailable;
    }

    public void requireMedicine(String medicineName, boolean isOriginal) throws FullWarehouseException {
        if (medicinesStored < MAX_CAPACITY) {
            System.out.println("La medicina: " + medicineName + " è stato richiesto alla casa farmaceutica");
            requiredMedicine = new Medicine(medicineName, isOriginal);
            medicines.add(new Medicine(requiredMedicine));
            medicinesStored++;
            setChanged();
            notifyObservers();
        } else {
            throw new FullWarehouseException();
        }
    }

    public Medicine getSoldMedicine() {
        return soldMedicine;
    }

    public Medicine getRequiredMedicine() {
        return requiredMedicine;
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public int getMedicinesStored() {
        return medicinesStored;
    }
}