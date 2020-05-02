import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoEmployeeException, FullWarehouseException {

        Pharmacy pharmacy = Pharmacy.getIstance();
        pharmacy.listOfPharmacists();

        boolean nextClient;
        Scanner scanner = new Scanner(System.in);

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