import java.util.ArrayList;
import java.util.Observable;

public class Warehouse extends Observable {
    private int medicinesStored, medicineRecived;
    private static final int MAX_CAPACITY = 1000;
    private ArrayList<Medicine> medicines = new ArrayList<>();
    private Medicine soldMedicine;
    private ArrayList<Ordination> requiredMedicines = new ArrayList<>();

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

    public void addRequiredMedicine(String medicineName, boolean isOriginal, int clientId) {
        System.out.println("La medicina: " + medicineName + " è stata richiesta alla casa farmaceutica");
        requiredMedicines.add(new Ordination(medicineName, isOriginal, clientId));
    }
    /*
    public void createRequiredMedicine(int noRequiredMedicines, int currentClientId) throws FullWarehouseException {
        boolean created = false;
        if (medicinesStored < MAX_CAPACITY) {
            while(!created) {
                int randMed = (int)(Math.random() * noRequiredMedicines);
                if(requiredMedicines.get(randMed).getClientId() != currentClientId) {
                    medicines.add(new Medicine(requiredMedicines.get(randMed).getName(), requiredMedicines.get(randMed).isOrginal(), true));
                    System.out.println("\nUNA MEDICINA PRENOTATA È ORA DISPONIBILE!");
                    medicineRecived = randMed;
                    requiredMedicines.remove(randMed);
                    medicinesStored++;
                    created = true;
                    }
                }
            } else {
            throw new FullWarehouseException();
        }
        setChanged();
        notifyObservers();
    }*/

    public void createRequiredMedicine(int noRequiredMedicines, int currentClientId) throws FullWarehouseException {
        if (medicinesStored < MAX_CAPACITY) {
            int randMed = (int) (Math.random() * noRequiredMedicines);
            if (requiredMedicines.get(randMed).getClientId() != currentClientId) {
                medicines.add(new Medicine(requiredMedicines.get(randMed).getName(), requiredMedicines.get(randMed).isOrginal(), true));
                System.out.println("\nUNA MEDICINA PRENOTATA È ORA DISPONIBILE!");
                medicineRecived = randMed;
                requiredMedicines.remove(randMed);
                medicinesStored++;
            }
        } else {
            throw new FullWarehouseException();
        }
        setChanged();
        notifyObservers();
    }

    public int getMedicineRecived() {
        return medicineRecived;
    }

    public Medicine getSoldMedicine() {
        return soldMedicine;
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public int getMedicinesStored() {
        return medicinesStored;
    }
}