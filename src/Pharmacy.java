package com.pharmacy;

import java.util.Map;

public class Pharmacy implements Observer{
    private static String name;
    private static Pharmacist[] pharmacists;
    private static Pharmacist director;

    private Warehouse warehouse = new Warehouse((int)(Math.random() * (Warehouse.getMaxCapacity() + 1)));
    private Map<Client, Medicine[]> requiredMedicines;

    private static Pharmacy istance = null;

    private Pharmacy(String name, Pharmacist director, Pharmacist[] pharmacists) {
        Pharmacy.name = name;
        Pharmacy.director = director;
        Pharmacy.pharmacists = pharmacists;
    }

    public static Pharmacy getIstance(String name, Pharmacist[] pharmacists) {
        if (istance == null)
            istance = new Pharmacy(name, pharmacists[0], pharmacists);
        return istance;
    }

    public void listOfPharmacists() {
        System.out.println("Componenti della " + Pharmacy.name);
        System.out.println("Direttore:");
        System.out.println(director.getName() + " " + director.getSurname());
        System.out.println("Dipendenti: ");
        for (int i = 1; i < pharmacists.length; i++)
            System.out.println(pharmacists[i].getName() + " " + pharmacists[i].getSurname());
    }

    public void sellMedicine(Medicine medicine, Client client) {
        if (warehouse.isAvailable(medicine)) {
            // scelta del metodo di pagamento -> PROXY
            System.out.println("È stata venduta la medicina: " + medicine.name);
            // update del numero di medicine nel magazzino
            // si deve eliminare la medicina dal magazzino
            client.removeRequestedMedicine(medicine);
        } else {
            client.addRequestedMedicine(medicine);
        }
    }

    @Override
    public void update() {}

}
