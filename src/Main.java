import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoEmployeeException {

        Scanner scanner = new Scanner(System.in);

        int numberOfEmployees;
        String pharmacyName;
        String name;
        String surname;

        // CREAZIONE FARMACISTI e FARMACIA
        System.out.println("Quanti sono i dipendenti della farmacia? ");
        numberOfEmployees = scanner.nextInt();
        if (numberOfEmployees == 0)
            throw new NoEmployeeException();
        scanner.nextLine();
        System.out.println("Nome della farmacia: ");
        pharmacyName = scanner.nextLine();

        Pharmacist[] pharmacists = new Pharmacist[numberOfEmployees];

        System.out.println("Nome del direttore: ");
        name = scanner.nextLine();
        System.out.println("Cognome del direttore: ");
        surname = scanner.nextLine();
        pharmacists[0] = new Pharmacist(name, surname, true);

        for (int i = 1; i < pharmacists.length; i++) {
            System.out.println("Nome del " + i + "^o dipendente: ");
            name = scanner.nextLine();
            System.out.println("Cognome del " + i + "^o dipendente: ");
            surname = scanner.nextLine();
            pharmacists[i] = new Pharmacist(name, surname, false);
        }

        Pharmacy.getIstance(pharmacyName, pharmacists).listOfPharmacists();

        // CREAZIONE CLIENTE
        String fiscalCode;

        System.out.println("Nome del cliente: ");
        name = scanner.nextLine();
        System.out.println("Cognome del cliente: ");
        surname = scanner.nextLine();
        System.out.println("Codice fiscale del cliente: ");
        fiscalCode = scanner.nextLine();
        System.out.println("ISEE del cliente calcolato e regisrato");
        int isee = (int)(Math.random() * 1000000000 + 100000);

        Client client = new Client(name, surname, fiscalCode, isee);

        // VENDITA MEDICINALE

    }
}
