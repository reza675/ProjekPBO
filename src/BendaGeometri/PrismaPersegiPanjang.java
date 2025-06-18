package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaPersegiPanjang extends PersegiPanjang implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;

    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) throws InputMismatchException {
        super(panjang, lebar);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi prisma harus lebih dari nol.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n=== Perhitungan Prisma Persegi Panjang dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 3) {
                if (i + 2 < 1000) {
                    double panjangBaru = dataArray[i];
                    double lebarBaru = dataArray[i + 1];
                    double tinggiPrismaBaru = dataArray[i + 2];
                    try {
                        volume = menghitungVolume(panjangBaru, lebarBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(panjangBaru, lebarBaru, tinggiPrismaBaru);
                        System.out.printf("Data %d-%d: panjang=%.1f, lebar=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 3, panjangBaru, lebarBaru, tinggiPrismaBaru, volume, luasPermukaan);
                    } catch (InputMismatchException e) {
                        System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 3, e.getMessage());
                    }
                }
            }
            System.out.println("\nPerhitungan selesai untuk 1000 data!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public boolean isCalculated() {
        return calculated;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public void setTinggiPrisma(double tinggiPrisma) {
        this.tinggiPrisma = tinggiPrisma;
    }

    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    public double menghitungVolume(double panjangBaru, double lebarBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Panjang, lebar, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(panjangBaru, lebarBaru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double panjangBaru, double lebarBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Panjang, lebar, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(panjangBaru, lebarBaru);
        kelilingAlas = super.menghitungKeliling(panjangBaru, lebarBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Persegi Panjang";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai panjang, lebar, dan tinggi Prisma Persegi Panjang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang baru: ");
                        String inputPanjang = inputData.nextLine();
                        double panjangBaru = Double.parseDouble(inputPanjang);
                        System.out.print("Masukkan lebar baru: ");
                        String inputLebar = inputData.nextLine();
                        double lebarBaru = Double.parseDouble(inputLebar);
                        System.out.print("Masukkan tinggi prisma baru: ");
                        String inputTinggi = inputData.nextLine();
                        double tinggiBaru = Double.parseDouble(inputTinggi);

                        volume = menghitungVolume(panjangBaru, lebarBaru, tinggiBaru);
                        luasPermukaan = menghitungLuasPermukaan(panjangBaru, lebarBaru, tinggiBaru);

                        System.out.printf("\nVolume Prisma Persegi Panjang: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Persegi Panjang: %.2f\n", luasPermukaan);
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