package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class PrismaBelahKetupat extends BelahKetupat implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;
    private final Object lock = new Object();

    public PrismaBelahKetupat(double diagonal1, double diagonal2, double sisi, double tinggiPrisma) throws InputMismatchException {
        super(diagonal1, diagonal2, sisi);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi prisma harus lebih dari nol.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public void run() {
        synchronized(lock) {
            try {
                System.out.println("\n=== Perhitungan Prisma Belah Ketupat dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 4) {
                    if (i + 3 < 1000) {
                        double diagonal1Baru = dataArray[i];
                        double diagonal2Baru = dataArray[i + 1];
                        double sisiBaru = dataArray[i + 2];
                        double tinggiPrismaBaru = dataArray[i + 3];
                        try {
                            volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                            luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiBaru, tinggiPrismaBaru);
                            System.out.printf("Data %d-%d: diagonal1=%.1f, diagonal2=%.1f, sisi=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 4, diagonal1Baru, diagonal2Baru, sisiBaru, tinggiPrismaBaru, volume, luasPermukaan);
                        } catch (InputMismatchException e) {
                            System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 4, e.getMessage());
                        }
                    }
                }
                System.out.println("\nPerhitungan selesai untuk 1000 data!");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    public void waitForCalculation() throws InterruptedException {
        synchronized(lock) {
            while (!calculated) {
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for " + getNamaBenda() + " calculations...");
                lock.wait();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " received " + getNamaBenda() + " results:");
            System.out.printf("Volume: %.2f\n", volume);
            System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
        }
    }

    public boolean isCalculated() {
        synchronized(lock) {
            return calculated;
        }
    }

    public double getVolume() {
        synchronized(lock) {
            if (!calculated) {
                throw new IllegalStateException("Calculations not yet complete");
            }
            return volume;
        }
    }

    public double getLuasPermukaan() {
        synchronized(lock) {
            if (!calculated) {
                throw new IllegalStateException("Calculations not yet complete");
            }
            return luasPermukaan;
        }
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiPrismaBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, sisi, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        kelilingAlas = super.menghitungKeliling(sisiBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

     @Override
    public String getNamaBenda() {
        return "Prisma Belah Ketupat";
    }
    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisi, dan tinggi prisma pada Prisma Belah Ketupat? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 baru: ");
                        String inputDiagonal1 = inputData.nextLine();
                        double diagonal1Baru = Double.parseDouble(inputDiagonal1);
                        System.out.print("Masukkan diagonal2 baru: ");
                        String inputDiagonal2 = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2);
                        System.out.print("Masukkan sisi baru: ");
                        String inputSisiBaru = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisiBaru);
                        System.out.print("Masukkan tinggi prisma baru: ");
                        String inputTinggiPrisma = inputData.nextLine();
                        double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);;

                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiBaru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Belah Ketupat: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Belah Ketupat: %.2f\n", luasPermukaan);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            } else if (jawaban.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Jawaban hanya boleh Y atau N.");
            }
        }
    }

}