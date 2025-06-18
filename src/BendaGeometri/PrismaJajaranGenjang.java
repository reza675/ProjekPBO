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
            try {
                System.out.println("\n=== Perhitungan Prisma Jajaran Genjang dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 4) {
                    if (i + 3 < 1000) {
                        double panjangAlasBaru = dataArray[i];
                        double tinggiAlasBaru = dataArray[i + 1];
                        double sisiMiringAlasBaru = dataArray[i + 2];
                        double tinggiPrismaBaru = dataArray[i + 3];
                        try {
                            volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiPrismaBaru);
                            luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru, tinggiAlasBaru, sisiMiringAlasBaru, tinggiPrismaBaru);
                            System.out.printf("Data %d-%d: panjangAlas=%.1f, tinggiAlas=%.1f, sisiMiringAlas=%.1f, tinggiPrisma=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 4, panjangAlasBaru, tinggiAlasBaru, sisiMiringAlasBaru, tinggiPrismaBaru, volume, luasPermukaan);
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

   

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double panjangAlasBaru, double tinggiAlasBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Panjang, tinggi alas, dan tinggi prisma harus lebih dari nol.");
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

    public double menghitungLuasPermukaan(double panjangAlasBaru, double tinggiAlasBaru, double sisiMiringAlasBaru,
            double tinggiPrismaBaru) throws InputMismatchException {
        if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 || sisiMiringAlasBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Panjang, tinggi alas, sisi miring alas, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
        kelilingAlas = super.menghitungKeliling(panjangAlasBaru, sisiMiringAlasBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
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
                    String inputPanjangAlas = inputData.nextLine();
                    double panjangAlasBaru = Double.parseDouble(inputPanjangAlas);
                    System.out.print("Masukkan tinggi alas baru: ");
                    String inputTinggiAlas = inputData.nextLine();
                    double tinggiAlasBaru = Double.parseDouble(inputTinggiAlas);
                    System.out.print("Masukkan sisi miring alas baru: ");
                    String inputSisiMiringAlas = inputData.nextLine();
                    double sisiMiringAlasBaru = Double.parseDouble(inputSisiMiringAlas);
                    System.out.print("Masukkan tinggiPrisma baru: ");
                    String inputTinggiPrisma = inputData.nextLine();
                    double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);
                   

                    volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiPrismaBaru);
                    luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru,tinggiAlasBaru,sisiMiringAlasBaru,tinggiPrismaBaru);

                    System.out.printf("\nVolume Prisma Jajaran Genjang: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Prisma Jajaran Genjang: %.2f\n", luasPermukaan);
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