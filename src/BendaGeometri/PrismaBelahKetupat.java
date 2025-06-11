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
            // Calculate both volume and surface area in the thread
            volume = menghitungVolume();
            luasPermukaan = menghitungLuasPermukaan();
            calculated = true;
            System.out.println("Thread " + Thread.currentThread().getName() + " - " + getNamaBenda() + ":");
            System.out.printf("Volume: %.2f\n", volume);
            System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
            lock.notifyAll(); // Notify waiting threads that calculation is complete
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
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
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
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
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
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 baru: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi baru: ");
                        double sisiBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi prisma baru: ");
                        double tinggiPrismaBaru = inputData.nextDouble();
                        inputData.nextLine();
                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiBaru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Belah Ketupat: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Belah Ketupat: %.2f\n", luasPermukaan);
                        break;
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