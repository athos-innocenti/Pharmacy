package com.pharmacy;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Warehouse extends Observable {
    private boolean isAvailable;
    private int medicinesStored;
    private static final int MAX_CAPACITY = 100;
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public Warehouse(int medicinesStored) {
        this.medicinesStored = medicinesStored;
        try {
            Scanner scannerLen = new Scanner(new File("./data/medicinesList.txt"));
            String name;
            int cost, rand, indexLine, countLines = 0;
            while (scannerLen.hasNextLine()) {
                countLines++;
                scannerLen.nextLine();
            }
            scannerLen.close();
            for (int i = 0; i < medicinesStored; i++) {
                rand = (int) (Math.random() * countLines + 1);
                if (rand % 2 == 0)
                    rand -= 1;
                indexLine = 1;
                Scanner scannerMed = new Scanner(new File("./data/medicinesList.txt"));
                while (indexLine != rand) {
                    scannerMed.nextLine();
                    scannerMed.nextLine();
                    indexLine += 2;
                }
                name = scannerMed.nextLine();
                cost = Integer.parseInt(scannerMed.nextLine());
                medicines.add(new Medicine(name, cost));
                scannerMed = null;
            }
            System.out.println("Il magazzino contiene " + medicinesStored + " medicine");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public boolean isAvailable(Medicine medicine) {
        for (int i = 0; i <= medicines.size(); i++) {
            if (medicine.name.equals(medicines.get(i).name))
                isAvailable = true;
        }
        return isAvailable;
    }

    public void requireMedicine(Medicine medicine) throws FullWarehouseException {
        if (medicinesStored < MAX_CAPACITY) {
            System.out.println("Il medicinale: " + medicine.name + " è stato richiesto alla casa farmaceutica");
            // chiamata alla funzione factory, si deve aggiungere se si vuole il medicinale originale o generico
        } else
            throw new FullWarehouseException();
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }
}
