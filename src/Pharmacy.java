import java.util.*;
import java.math.BigDecimal;

public class Pharmacy implements Observer {
    private static String name;
    private static Pharmacist[] pharmacists;
    private static Pharmacist director;
    private static double totalGain;
    private static int totalClients;

    private Warehouse warehouse;
    private ArrayList<Reservation> clientsReservations;

    private String previousClient;
    private PaymentHandler payment;
    private Receipt receipt;

    private static Scanner scanner = new Scanner(System.in);
    private static Pharmacy istance = null;

    private Pharmacy(String name, Pharmacist director, Pharmacist[] pharmacists) {
        Pharmacy.name = name;
        Pharmacy.director = director;
        Pharmacy.pharmacists = pharmacists;
        Pharmacy.totalGain = 0;
        Pharmacy.totalClients = 0;

        clientsReservations = new ArrayList<>();
        previousClient = "";

        warehouse = new Warehouse((int) (Math.random() * (Warehouse.getMaxCapacity() + 1) + 1));
        warehouse.addObserver(this);
    }

    static Pharmacy getIstance() throws NoEmployeeException {
        if (istance == null) {
            System.out.println("Nome della farmacia: ");
            String pharmacyName = scanner.nextLine();

            System.out.println("Quanti sono i dipendenti della farmacia? ");
            int numberOfEmployees = scanner.nextInt();
            if (numberOfEmployees == 0) {
                throw new NoEmployeeException();
            }
            scanner.nextLine();

            Pharmacist[] pharmacists = new Pharmacist[numberOfEmployees];

            String pharmacistsName;
            String pharmacistsSurname;
            System.out.println("Nome del direttore: ");
            pharmacistsName = scanner.nextLine();
            System.out.println("Cognome del direttore: ");
            pharmacistsSurname = scanner.nextLine();
            pharmacists[0] = new Pharmacist(pharmacistsName, pharmacistsSurname, true);

            for (int i = 1; i < pharmacists.length; i++) {
                System.out.println("Nome del " + i + "^o dipendente: ");
                pharmacistsName = scanner.nextLine();
                System.out.println("Cognome del " + i + "^o dipendente: ");
                pharmacistsSurname = scanner.nextLine();
                pharmacists[i] = new Pharmacist(pharmacistsName, pharmacistsSurname, false);
            }

            istance = new Pharmacy(pharmacyName, pharmacists[0], pharmacists);
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
        boolean isDesiredMedicineOriginal, wantsToBuy;
        do {
            desiredMedicineName = client.selectDesiredMedicineName();
            isDesiredMedicineOriginal = client.selectIsDesiredMedicineOriginal();
            if (warehouse.isAvailable(desiredMedicineName, isDesiredMedicineOriginal)) {
                System.out.println("La medicina richiesta è disponibile nel magazzino");
                System.out.println("Si desidera acquistare a prezzo pieno o ridotto? (pieno o ridotto)");
                String paymentMethod = scanner.nextLine();
                if (!client.getFiscalCode().equals(previousClient)) {
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
                System.out.println("La medicina richiesta non è momentaneamente disponibile");
                clientsReservations.add(new Reservation(new Client(client), desiredMedicineName, isDesiredMedicineOriginal));
                System.out.println("È stata creata una prenotazione a nome: " + client.getName() + " per la medicina: " + desiredMedicineName + "\n");
                warehouse.addRequiredMedicine(desiredMedicineName, isDesiredMedicineOriginal, totalClients);
            }
            if (totalClients > 1 && clientsReservations.size() > 0 && Math.random() < 0.5) {
                for (Reservation r : clientsReservations) {
                    if (!r.getClientIdentifier().getName().equals(client.getName())) {
                        warehouse.createRequiredMedicine(clientsReservations.size() - 1, totalClients - 1);
                        break;
                    }
                }
            }
            previousClient = client.getFiscalCode();
            System.out.println("\nSi vuole acquistare un'altra medicina? (si o no)");
            wantsToBuy = scanner.nextLine().equals("si");
        }
        while (wantsToBuy);
        receipt.getPurchasedMedicines(client.getName());
        System.out.println("Spende in totale: " + receipt.getTotalCost());
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = warehouse.getMedicineRecived();
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
}