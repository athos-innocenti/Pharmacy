import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class Pharmacy implements Observer {
    private static String name;
    private static Pharmacist[] pharmacists;
    private static Pharmacist director;

    private static Pharmacy istance = null;

    private final Warehouse warehouse = new Warehouse((int) (Math.random() * (Warehouse.getMaxCapacity() + 1) + 1));
    private PaymentHandler cashDesk;
    private final ArrayList<Reservation> clientsReservations = new ArrayList<>();

    private Pharmacy(String name, Pharmacist director, Pharmacist[] pharmacists) {
        Pharmacy.name = name;
        Pharmacy.director = director;
        Pharmacy.pharmacists = pharmacists;
        warehouse.addObserver(this);
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

    public void sellMedicine(String desiredMedicineName, boolean isDesiredMedicineOriginal, Client client) throws FullWarehouseException {
        clientsReservations.add(new Reservation(new Client(client), desiredMedicineName, isDesiredMedicineOriginal));
        if (warehouse.isAvailable(desiredMedicineName, isDesiredMedicineOriginal)) {
            System.out.println("La medicina richiesta è disponibile nel magazzino");
            if (cashDesk == null) {
                cashDesk = new ProxyPaymentHandler();
            }
            double cost = cashDesk.pay(warehouse.getSoldMedicine().getCost(), client.getIsee());
            System.out.println("È stata venduta la medicina: " + warehouse.getSoldMedicine().getName() + " al prezzo di: " + cost + "\n");
            for (int i = 0; i < clientsReservations.size(); i++) {
                if (clientsReservations.get(i).getClientIdentifier().getFiscalCode().equals(client.getFiscalCode())
                        && clientsReservations.get(i).getDesiredMedicineName().equals(warehouse.getSoldMedicine().getName())
                        && clientsReservations.get(i).isDesiredMedicineOriginal() == warehouse.getSoldMedicine().isOriginal()) {
                    clientsReservations.remove(i);
                    break;
                }
            }
            System.out.println("Il magazzino contiene ora " + warehouse.getMedicinesStored() + " medicine");
            System.out.println("La farmacia ha gaudagnato finora: " + cashDesk.getProfit());
        } else {
            System.out.println("La medicina richiesta non è momentaneamente disponibile");
            warehouse.requireMedicine(desiredMedicineName, isDesiredMedicineOriginal);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            String clientName;
            Thread.sleep(2000);
            System.out.println("\nLa medicina richiesta è ora disponibile");
            for (int i = 0; i < clientsReservations.size(); i++) {
                if (clientsReservations.get(i).getDesiredMedicineName().equals(warehouse.getRequiredMedicine().getName())
                        && clientsReservations.get(i).isDesiredMedicineOriginal() == warehouse.getRequiredMedicine().isOriginal()) {
                    clientName = clientsReservations.get(i).getClientIdentifier().getName();
                    System.out.println("È stato avvisato il cliente " + clientName + " che la medicina " + clientsReservations.get(i).getDesiredMedicineName() + " è ora disponibile");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}