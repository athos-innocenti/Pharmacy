import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Medicine {
    private final String name;
    private final int cost;
    private final boolean isOriginal;
    private final boolean isReserved;

    Medicine() {
        int rand = setRand();
        this.name = setMedicineName(rand);
        this.cost = setMedicineCost(rand + 1);
        this.isOriginal = setIsOriginal();
        this.isReserved = false;
    }

    Medicine(String name, boolean isOriginal, boolean isReserved) {
        this.name = name;
        this.cost = setMedicineCost(name);
        this.isOriginal = isOriginal;
        this.isReserved = isReserved;
    }

    Medicine(Medicine med) {
        this.name = med.getName();
        this.cost = med.getCost();
        this.isOriginal = med.isOriginal();
        this.isReserved = med.isReserved();
    }

    static int setRand() {
        int rand = 0;
        try {
            Scanner scanner = new Scanner(new File("src/data/medicinesList.txt"));
            int countLines = 0;
            while (scanner.hasNextLine()) {
                countLines++;
                scanner.nextLine();
            }
            scanner.close();
            rand = (int) (Math.random() * countLines + 1);
            if (rand % 2 == 0) {
                rand -= 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return rand;
    }

    static String setMedicineName(int random) {
        String name = "";
        int indexLine = 1;
        try {
            Scanner scannerName = new Scanner(new File("src/data/medicinesList.txt"));
            while (indexLine != random) {
                scannerName.nextLine();
                indexLine += 1;
            }
            name = scannerName.nextLine();
            scannerName.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return name;
    }

    private int setMedicineCost(int random) {
        int indexLine = 1;
        int cost = 0;
        try {
            Scanner scannerCost = new Scanner(new File("src/data/medicinesList.txt"));
            while (indexLine != random) {
                scannerCost.nextLine();
                indexLine += 1;
            }
            cost = Integer.parseInt(scannerCost.nextLine());
            scannerCost.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return cost;
    }

    private int setMedicineCost(String medicineName) {
        int cost = 0;
        try {
            Scanner scanner = new Scanner(new File("src/data/medicinesList.txt"));
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(medicineName)) {
                    cost = Integer.parseInt(scanner.nextLine());
                    break;
                }
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return cost;
    }

    static boolean setIsOriginal() {
        return (Math.random() < 0.5);
    }

    public String getName() {
        return name;
    }

    int getCost() {
        return cost;
    }

    boolean isOriginal() {
        return isOriginal;
    }

    boolean isReserved() {
        return isReserved;
    }
}