package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaSegitiga extends Segitiga implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
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
            try {
                System.out.println("\n=== Perhitungan Prisma Segitiga dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 5) {
                    if (i + 4 < 1000) {
                        double alasBaru = dataArray[i];
                        double tinggiSegitigaBaru = dataArray[i + 1];
                        double sisiMiring1Baru = dataArray[i + 2];
                        double sisiMiring2Baru = dataArray[i + 3];
                        double tinggiPrismaBaru = dataArray[i + 4];
                        try {
                            volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiPrismaBaru);
                            luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru, sisiMiring2Baru, tinggiPrismaBaru);
                            System.out.printf("Data %d-%d: alas=%.1f, tinggiSegitiga=%.1f, sisiMiring1=%.1f, sisiMiring2=%.1f, tinggiPrisma=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 5, alasBaru, tinggiSegitigaBaru, sisiMiring1Baru, sisiMiring2Baru, tinggiPrismaBaru, volume, luasPermukaan);
                        } catch (InputMismatchException e) {
                            System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 5, e.getMessage());
                        }
                    }
                }
                System.out.println("\nPerhitungan selesai untuk 1000 data!");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double alasBaru, double tinggiBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (alasBaru <= 0 || tinggiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Alas, tinggi, dan tinggi prisma harus lebih dari nol.");
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
            throw new InputMismatchException("Alas, tinggi, dan tinggi prisma harus lebih dari nol.");
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