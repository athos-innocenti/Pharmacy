import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Medicine {
    private final String name;
    private final int cost;
    private final boolean isOriginal;

    public Medicine() {
        int rand = setRand();
        this.name = setMedicineName(rand);
        this.cost = setMedicineCost(rand + 1);
        this.isOriginal = setIsOriginal();
    }

    public Medicine(String name, boolean isOriginal) {
        this.name = name;
        this.cost = setMedicineCost(name);
        this.isOriginal = isOriginal;
    }

    public Medicine(Medicine med) {
        this.name = med.getName();
        this.cost = med.getCost();
        this.isOriginal = med.isOriginal();
    }

    public static int setRand() {
        int rand = 0;
        try {
            Scanner scanner = new Scanner(new File("./data/medicinesList.txt"));
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

    public static String setMedicineName(int random) {
        String name = "";
        int indexLine = 1;
        try {
            Scanner scannerName = new Scanner(new File("./data/medicinesList.txt"));
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
            Scanner scannerCost = new Scanner(new File("./data/medicinesList.txt"));
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
            Scanner scanner = new Scanner(new File("./data/medicinesList.txt"));
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(medicineName)) {
                    cost = Integer.parseInt(scanner.nextLine());
                }
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return cost;
    }

    public static boolean setIsOriginal() {
        if ((int) (Math.random()) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean isOriginal() {
        return isOriginal;
    }
}