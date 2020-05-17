import java.util.*;
import java.math.BigDecimal;

public class Pharmacy implements Observer {
    private static String name;
    private static Pharmacist[] pharmacists;
    private static Pharmacist director;
    private static double totalGain;
    private static int totalClients;
    private static int numberOfEmployees;

    private static Warehouse warehouse;
    private static ArrayList<Reservation> clientsReservations;

    private static Scanner scanner = new Scanner(System.in);
    private static Pharmacy istance = null;

    private static String previousClient;
    private PaymentHandler payment;
    private Receipt receipt;

    private Pharmacy(String name, Pharmacist director, Pharmacist[] pharmacists) {
        Pharmacy.name = name;
        Pharmacy.director = director;
        Pharmacy.pharmacists = pharmacists;

        totalGain = 0;
        totalClients = 0;
        clientsReservations = new ArrayList<>();
        previousClient = "";

        warehouse = new Warehouse((int) (Math.random() * (Warehouse.getMaxCapacity() + 1) + 1));
        warehouse.addObserver(this);
    }

    static Pharmacy getIstance(int numberOfEmployees, String pharmacyName, Pharmacist[] pharmacists) {
        if (istance == null) {
            istance = new Pharmacy(pharmacyName, pharmacists[0], pharmacists);
            Pharmacy.setNumberOfEmployees(numberOfEmployees);
        }
        return istance;
    }

    void listOfPharmacists() {
        System.out.println("\nComponenti della farmacia: " + name);
        System.out.println("Direttore:");
        System.out.println(director.getName() + " " + director.getSurname());
        System.out.println("Dipendenti: ");
        for (int i = 1; i < pharmacists.length; i++) {
            System.out.println(pharmacists[i].getName() + " " + pharmacists[i].getSurname());
        }
    }

    void sellMedicine(Client client) throws FullWarehouseException {
        totalClients++;
        String desiredMedicineName;
        int countReservations = 0;
        int previousReservations = 0;
        boolean bought = false;
        boolean isDesiredMedicineOriginal, wantsToBuy, hasBought;
        do {
            desiredMedicineName = client.selectDesiredMedicineName();
            isDesiredMedicineOriginal = client.selectIsDesiredMedicineOriginal();
            hasBought = bought;
            if (warehouse.isAvailable(desiredMedicineName, isDesiredMedicineOriginal)) {
                bought = true;
                System.out.println("La medicina richiesta è disponibile nel magazzino");
                System.out.println("Si desidera acquistare a prezzo pieno o ridotto? (pieno o ridotto)");
                String paymentMethod = scanner.nextLine();
                if (!client.getFiscalCode().equals(previousClient) || (previousReservations < countReservations && !hasBought)) {
                    payment = new ProxyPaymentHandler();
                    receipt = new Receipt();
                }
                double cost = payment.pay(warehouse.getSoldMedicine().getCost(), client.getIsee(), paymentMethod);
                receipt.addPurchasedMedicine(desiredMedicineName, isDesiredMedicineOriginal, cost);
                totalGain += cost;
                System.out.println("\nÈ stata venduta la medicina: " + warehouse.getSoldMedicine().getName() + " al prezzo di: " + cost);
                System.out.println(client.getName() + " ha speso finora: " + payment.getProfit() + "\n");
                System.out.println("Il magazzino contiene ora " + warehouse.getMedicinesStored() + " medicine");
            } else {
                previousReservations = countReservations;
                countReservations++;
                addReservation(client, desiredMedicineName, isDesiredMedicineOriginal);
            }
            handleRequiredMedicines(client);
            System.out.println("\nSi vuole acquistare un'altra medicina? (si o no)");
            wantsToBuy = scanner.nextLine().equals("si");
            previousClient = client.getFiscalCode();
        }
        while (wantsToBuy);
        if (bought) {
            receipt.printPurchasedMedicines(client.getName());
            System.out.println("Spende in totale: " + receipt.getTotalCost());
        }
        if (countReservations > 0) {
            System.out.println(client.getName() + " ha " + countReservations + " medicine prenotate");
        }
    }

    void addReservation(Client client, String desiredMedicineName, boolean isDesiredMedicineOriginal) {
        System.out.println("La medicina richiesta non è momentaneamente disponibile");
        clientsReservations.add(new Reservation(new Client(client), desiredMedicineName, isDesiredMedicineOriginal));
        System.out.println("È stata creata una prenotazione a nome: " + client.getName() + " per la medicina: " + desiredMedicineName + "\n");
        warehouse.addRequiredMedicine(desiredMedicineName, isDesiredMedicineOriginal, totalClients);
    }

    boolean handleRequiredMedicines(Client client) throws FullWarehouseException {
        boolean random = Math.random() < 0.5;
        if (totalClients > 1 && clientsReservations.size() > 0 && random) {
            for (Reservation r : clientsReservations) {
                if (!r.getClientIdentifier().getFiscalCode().equals(client.getFiscalCode())) {
                    warehouse.createRequiredMedicine(clientsReservations.size() - 1, totalClients - 1);
                    break;
                }
            }
        }
        return random;
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = warehouse.getCreatedMedicineIndex();
        String clientName = clientsReservations.get(index).getClientIdentifier().getName();
        String medicineName = clientsReservations.get(index).getDesiredMedicineName();
        System.out.println("È STATO AVVISATO IL CLIENTE " + clientName + " CHE È STATA RISERVATA LA MEDICINA " + medicineName + "!\n");
        clientsReservations.remove(index);
    }

    double getTotalGain() {
        BigDecimal bd = new BigDecimal(Double.toString(totalGain));
        bd = bd.setScale(2, BigDecimal.ROUND_FLOOR);
        return bd.doubleValue();
    }

    int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    String getName() {
        return name;
    }

    Pharmacist[] getPharmacists() {
        return pharmacists;
    }

    Pharmacist getDirector() {
        return director;
    }

    int getTotalClients() {
        return totalClients;
    }

    Warehouse getWarehouse() {
        return warehouse;
    }

    ArrayList<Reservation> getClientsReservations() {
        return clientsReservations;
    }

    static void setNumberOfEmployees(int numberOfEmployees) {
        Pharmacy.numberOfEmployees = numberOfEmployees;
    }
}