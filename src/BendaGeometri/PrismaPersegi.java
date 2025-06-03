package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaPersegi extends Persegi {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaPersegi(double sisi, double tinggiPrisma) {
        super(sisi);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double sisiBaru, double tinggiPrismaBaru) {
        luasAlas = super.menghitungLuas(sisiBaru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double sisiBaru, double tinggiPrismaBaru) {
        luasAlas = super.menghitungLuas(sisiBaru);
        kelilingAlas = super.menghitungKeliling(tinggiPrismaBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Persegi";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai sisi dan tinggi Prisma Persegi? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan sisi persegi: ");
                        double sisiBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi prisma: ");
                        double tinggiPrismaBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (sisiBaru <= 0 || tinggiPrismaBaru <= 0) {
                            System.out.println("Sisi dan tinggi harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(sisiBaru,tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(sisiBaru,tinggiPrismaBaru);
                        System.out.printf("\nVolume Prisma Persegi: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Persegi: %.2f\n", luasPermukaan);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input sisi dan tinggi harus berupa angka.");
                        inputData.nextLine();
                    }
                }
                break;
            } else if (jawaban.equalsIgnoreCase("N")) {
                volume = menghitungVolume();
                luasPermukaan = menghitungLuasPermukaan();
                break;
            } else {
                System.out.println("Jawaban hanya boleh Y atau N.\n");
            }
        }
    }

}