package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaSegitiga extends Segitiga implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;
    private final Object lock = new Object();

    public PrismaSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2, double tinggiPrisma) throws InputMismatchException {
        super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
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

    public double menghitungVolume(double alasBaru, double tinggiBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (alasBaru <= 0 || tinggiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(alasBaru, tinggiBaru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double alasBaru, double tinggiBaru, double sisiMiring1Baru,double sisiMiring2Baru,double tinggiPrismaBaru) throws InputMismatchException {
        if (alasBaru <= 0 || tinggiBaru <= 0 || sisiMiring1Baru <= 0 || sisiMiring2Baru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(alasBaru, tinggiBaru);
        kelilingAlas = super.menghitungKeliling(alasBaru, sisiMiring1Baru, sisiMiring2Baru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Segitiga";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai alas, tinggi segitiga, sisi miring 1, sisi miring 2, dan tinggi prisma Prisma Segitiga? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang alas segitiga: ");
                        String inputAlas = inputData.nextLine();
                        double alasBaru = Double.parseDouble(inputAlas);
                        System.out.print("Masukkan tinggi segitiga: ");
                        String inputTinggiSegitiga = inputData.nextLine();
                        double tinggiSegitigaBaru = Double.parseDouble(inputTinggiSegitiga);
                        System.out.print("Masukkan sisi miring 1 segitiga: ");
                        String inputSisiMiring1 = inputData.nextLine();
                        double sisiMiring1Baru = Double.parseDouble(inputSisiMiring1);
                        System.out.print("Masukkan sisi miring 2 segitiga: ");
                         String inputSisiMiring2 = inputData.nextLine();
                        double sisiMiring2Baru = Double.parseDouble(inputSisiMiring2);
                        System.out.print("Masukkan tinggi prisma: ");
                         String inputTinggiPrisma = inputData.nextLine();
                        double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);
                        
                        volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru,
                                sisiMiring2Baru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Segitiga: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Segitiga: %.2f\n", luasPermukaan);
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