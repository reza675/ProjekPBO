package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class PrismaTrapesium extends Trapesium implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;
    private final Object lock = new Object();

    public PrismaTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiMiringKiri,
            double sisiMiringKanan,
            double tinggiPrisma) throws InputMismatchException {
        super(alasAtas, alasBawah, tinggiTrapesium, sisiMiringKiri, sisiMiringKanan);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi prisma harus lebih dari nol.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("\n=== Perhitungan Prisma Trapesium dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 6) {
                    if (i + 5 < 1000) {
                        double alasAtasBaru = dataArray[i];
                        double alasBawahBaru = dataArray[i + 1];
                        double tinggiBaru = dataArray[i + 2];
                        double sisiMiringKiriBaru = dataArray[i + 3];
                        double sisiMiringKananBaru = dataArray[i + 4];
                        double tinggiPrismaBaru = dataArray[i + 5];
                        try {
                            volume = menghitungVolume(alasAtasBaru, alasBawahBaru, tinggiBaru, tinggiPrismaBaru);
                            luasPermukaan = menghitungLuasPermukaan(alasAtasBaru, alasBawahBaru, tinggiBaru, sisiMiringKiriBaru, sisiMiringKananBaru, tinggiPrismaBaru);
                            System.out.printf("Data %d-%d: alasAtas=%.1f, alasBawah=%.1f, tinggi=%.1f, sisiMiringKiri=%.1f, sisiMiringKanan=%.1f, tinggiPrisma=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 6, alasAtasBaru, alasBawahBaru, tinggiBaru, sisiMiringKiriBaru, sisiMiringKananBaru, tinggiPrismaBaru, volume, luasPermukaan);
                        } catch (InputMismatchException e) {
                            System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 6, e.getMessage());
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
        synchronized (lock) {
            while (!calculated) {
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for " + getNamaBenda()
                        + " calculations...");
                lock.wait();
            }
            System.out.println(
                    "Thread " + Thread.currentThread().getName() + " received " + getNamaBenda() + " results:");
            System.out.printf("Volume: %.2f\n", volume);
            System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
        }
    }

    public boolean isCalculated() {
        synchronized (lock) {
            return calculated;
        }
    }

    public double getVolume() {
        synchronized (lock) {
            if (!calculated) {
                throw new IllegalStateException("Calculations not yet complete");
            }
            return volume;
        }
    }

    public double getLuasPermukaan() {
        synchronized (lock) {
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

    public double menghitungVolume(double alasAtasBaru, double alasBawahBaru, double tinggiBaru,
            double tinggiPrismaBaru) throws InputMismatchException {
        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Alas atas, alas bawah, tinggi trapesium, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiBaru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double alasAtasBaru, double alasBawahBaru, double tinggiBaru,
            double sisiMiringKiriBaru,
            double sisiMiringKananBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiBaru <= 0 || sisiMiringKiriBaru <= 0
                || sisiMiringKananBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Alas atas, alas bawah, tinggi trapesium, sisi miring kiri, sisi miring kanan, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiBaru);
        kelilingAlas = super.menghitungKeliling(alasAtasBaru, alasBawahBaru, sisiMiringKananBaru, sisiMiringKiriBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Trapesium";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai alas atas, alas bawah, tinggi, sisi miring kiri, sisi miring kanan, dan tinggi prisma pada Prisma Trapesium? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan alas atas baru: ");
                        String inputAlasAtas = inputData.nextLine();
                        double alasAtasBaru = Double.parseDouble(inputAlasAtas);
                        System.out.print("Masukkan alas bawah baru: ");
                        String inputAlasBawah = inputData.nextLine();
                        double alasBawahBaru = Double.parseDouble(inputAlasBawah);
                        System.out.print("Masukkan tinggi trapesium (alas) baru: ");
                        String inputTinggi = inputData.nextLine();
                        double tinggiBaru = Double.parseDouble(inputTinggi);
                        System.out.print("Masukkan sisi miring kiri baru: ");
                        String inputSisiKiri = inputData.nextLine();
                        double sisiKiriBaru = Double.parseDouble(inputSisiKiri);
                        System.out.print("Masukkan sisi miring kanan baru: ");
                        String inputSisiKanan = inputData.nextLine();
                        double sisiKananBaru = Double.parseDouble(inputSisiKanan);
                        System.out.print("Masukkan tinggi prisma baru: ");
                        String inputTinggiPrisma = inputData.nextLine();
                        double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);

                        volume = menghitungVolume(alasAtasBaru, alasBawahBaru, tinggiBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasAtasBaru, alasBawahBaru, tinggiBaru, sisiKiriBaru,
                                sisiKananBaru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Trapesium: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Trapesium: %.2f\n", luasPermukaan);
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