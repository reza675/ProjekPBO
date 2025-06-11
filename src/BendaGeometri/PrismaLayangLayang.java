package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class PrismaLayangLayang extends LayangLayang implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;
    private final Object lock = new Object();

    public PrismaLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang,
            double tinggiPrisma) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
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

    public double menghitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiPrismaBaru) {
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

    public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiPendekBaru, double sisiPanjangBaru,double tinggiPrismaBaru) {
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        kelilingAlas = super.menghitungKeliling(sisiPendekBaru, sisiPanjangBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Layang-Layang";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisi pendek, sisi panjang, dan tinggiPrisma prisma Layang-Layang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 layang-layang: ");
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 layang-layang: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi pendek layang-layang: ");
                        double sisiPendekBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi panjang layang-layang: ");
                        double sisiPanjangBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggiPrisma prisma: ");
                        double tinggiPrismaBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiPrismaBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru,tinggiPrismaBaru);
                        System.out.printf("\nVolume Prisma Layang-Layang: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Layang-Layang: %.2f\n", luasPermukaan);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa angka.");
                        inputData.nextLine();
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