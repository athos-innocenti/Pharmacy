import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoEmployeeException, FullWarehouseException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome della farmacia: ");
        String pharmacyName = scanner.nextLine();

        System.out.println("Quanti sono i dipendenti della farmacia? ");
        int numberOfEmployees = scanner.nextInt();
        Pharmacy.setNumberOfEmployees(numberOfEmployees);
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

        Pharmacy pharmacy = Pharmacy.getIstance(numberOfEmployees, pharmacyName, pharmacists);
        pharmacy.listOfPharmacists();

        boolean nextClient;

        do {
            System.out.println("\nNome del cliente: ");
            String clientName = scanner.nextLine();
            System.out.println("Cognome del cliente: ");
            String clientSurname = scanner.nextLine();
            System.out.println("Codice fiscale del cliente: ");
            String fiscalCode = scanner.nextLine();
            System.out.println("ISEE del cliente calcolato e registrato");
            int isee = (int) (Math.random() * 1000000 + 10000);

            Client client = new Client(clientName, clientSurname, fiscalCode, isee);

            pharmacy.sellMedicine(client);

            System.out.println("\nC'è un prossimo cliente? (si o no)");
            nextClient = scanner.nextLine().equals("si");
        } while (nextClient);

        System.out.println("\nLa farmacia chiude");
        System.out.println("Il guadagno totale è stato: " + pharmacy.getTotalGain());
    }
}