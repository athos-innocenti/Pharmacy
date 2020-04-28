import java.util.HashMap;
import java.util.ArrayList;

public class Pharmacy implements Observer {
    private static String name;
    private static Pharmacist[] pharmacists;
    private static Pharmacist director;

    private Warehouse warehouse = new Warehouse((int) (Math.random() * (Warehouse.getMaxCapacity() + 1) + 1));
    private HashMap<Client, ArrayList<Medicine>> requiredMedicinesPerClient = new HashMap<>();

    private static Pharmacy istance = null;

    private Pharmacy(String name, Pharmacist director, Pharmacist[] pharmacists) {
        Pharmacy.name = name;
        Pharmacy.director = director;
        Pharmacy.pharmacists = pharmacists;
    }

    public static Pharmacy getIstance(String name, Pharmacist[] pharmacists) {
        if (istance == null) {
            istance = new Pharmacy(name, pharmacists[0], pharmacists);
        }
        return istance;
    }

    public void listOfPharmacists() {
        System.out.println("Componenti della Farmacia");
        System.out.println("Direttore:");
        System.out.println(director.getName() + " " + director.getSurname());
        System.out.println("Dipendenti: ");
        for (int i = 1; i < pharmacists.length; i++) {
            System.out.println(pharmacists[i].getName() + " " + pharmacists[i].getSurname());
        }
    }

    public void sellMedicine(Medicine medicine, Client client) throws FullWarehouseException {
        ArrayList<Medicine> medicinesRequired = new ArrayList<>();
        requiredMedicinesPerClient.put(client, medicinesRequired);
        requiredMedicinesPerClient.get(client).add(medicine); // va fatta copia difensiva ?
        if (warehouse.isAvailable(medicine)) {
            System.out.println("Il medicinale è disponibile");
            PaymentHandler cashDesk = new ProxyPaymentHandler();
            double cost = cashDesk.pay(medicine.getCost(), client.getIsee());
            System.out.println("È stato venduto il medicinale: " + medicine.getName() + " al prezzo di: " + cost + "\n");
            for (int i = 0; i < requiredMedicinesPerClient.get(client).size(); i++) {
                if (requiredMedicinesPerClient.get(client).get(i).getName().equals(medicine.getName())) {
                    requiredMedicinesPerClient.get(client).remove(i);
                    break;
                }
            }
            System.out.println("il magazzino contiene ora " + warehouse.getMedicinesStored() + " medicine");
        } else {
            System.out.println("il medicinale non è momentaneamente disponibile");
            warehouse.requireMedicine(medicine);
        }
    }

    @Override
    public void update() {
    }

}