import java.util.ArrayList;
import java.util.Observable;

class Warehouse extends Observable {
    private int medicinesStored, createdMedicineIndex;
    private static final int MAX_CAPACITY = 100;
    private ArrayList<Medicine> medicines = new ArrayList<>();
    private Medicine soldMedicine;
    private ArrayList<Ordination> requiredMedicines = new ArrayList<>();

    Warehouse(int medicinesStored) {
        this.medicinesStored = medicinesStored;
        for (int i = 0; i < medicinesStored; i++) {
            medicines.add(new Medicine());
        }
        System.out.println("\nIl magazzino contiene inizialmente " + medicinesStored + " medicine");
    }

    boolean isAvailable(String medicineName, boolean isMedicineOriginal) {
        boolean isAvailable = false;
        for (int i = 0; i < medicines.size(); i++) {
            if (medicineName.equals(medicines.get(i).getName()) && isMedicineOriginal == medicines.get(i).isOriginal() && !medicines.get(i).isReserved()) {
                isAvailable = true;
                soldMedicine = new Medicine(medicines.get(i));
                medicines.remove(i);
                medicinesStored--;
                break;
            }
        }
        return isAvailable;
    }

    void addRequiredMedicine(String medicineName, boolean isOriginal, int clientNum) {
        System.out.println("La medicina: " + medicineName + " è stata richiesta alla casa farmaceutica");
        requiredMedicines.add(new Ordination(medicineName, isOriginal, clientNum));
    }

    void createRequiredMedicine(int noRequiredMedicines, int currentClientId) throws FullWarehouseException {
        if (medicinesStored < MAX_CAPACITY) {
            int randMed = (int) (Math.random() * noRequiredMedicines);
            if (requiredMedicines.get(randMed).getClientNum() != currentClientId) {
                medicines.add(new Medicine(requiredMedicines.get(randMed).getOrderedMedicineName(), requiredMedicines.get(randMed).isOrginal(), true));
                System.out.println("\nUNA MEDICINA PRENOTATA È ORA DISPONIBILE!");
                createdMedicineIndex = randMed;
                requiredMedicines.remove(randMed);
                medicinesStored++;
            }
        } else {
            throw new FullWarehouseException();
        }
        setChanged();
        notifyObservers();
    }

    static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    int getCreatedMedicineIndex() {
        return createdMedicineIndex;
    }

    int getMedicinesStored() {
        return medicinesStored;
    }

    Medicine getSoldMedicine() {
        return soldMedicine;
    }

    ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    ArrayList<Ordination> getRequiredMedicines() {
        return requiredMedicines;
    }
}