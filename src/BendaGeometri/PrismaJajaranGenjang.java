package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class PrismaJajaranGenjang extends JajaranGenjang implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;
    private final Object lock = new Object();

    public PrismaJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiPrisma) throws InputMismatchException {
        super(panjangAlas, tinggiAlas, sisiMiringAlas);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi Prisma harus lebih dari nol.");
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

    public double menghitungVolume(double panjangAlasBaru, double tinggiAlasBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double panjangAlas, double tinggiAlas, double sisiMiringAlas,
            double tinggiPrisma) throws InputMismatchException {
        if (panjangAlas <= 0 || tinggiAlas <= 0 || sisiMiringAlas <= 0 || tinggiPrisma <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        kelilingAlas = super.menghitungKeliling(panjangAlas, sisiMiringAlas);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Jajaran Genjang";
    }
    public void prosesInputDataUlang() {
    Scanner inputData = new Scanner(System.in);
    while (true) {
        System.out.print(
            "\nApakah Anda ingin mengubah nilai panjang alas, tinggi alas, sisi miring alas, dan tinggiPrisma pada Prisma Jajaran Genjang? (Y/N): ");
        String jawaban = inputData.nextLine();
        if (jawaban.equalsIgnoreCase("Y")) {
            while (true) {
                try {
                    System.out.print("Masukkan panjang alas baru: ");
                    double panjangAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggi alas baru: ");
                    double tinggiAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan sisi miring alas baru: ");
                    double sisiMiringAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggiPrisma baru: ");
                    double tinggiPrismaBaru = inputData.nextDouble();
                    inputData.nextLine();

                    volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiPrismaBaru);
                    luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru,tinggiAlasBaru,sisiMiringAlasBaru,tinggiPrismaBaru);

                    System.out.printf("\nVolume Prisma Jajaran Genjang: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Prisma Jajaran Genjang: %.2f\n", luasPermukaan);
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