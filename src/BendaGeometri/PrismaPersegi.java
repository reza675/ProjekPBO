package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaPersegi extends Persegi implements Runnable{
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;

    public PrismaPersegi(double sisi, double tinggiPrisma) throws InputMismatchException {
        super(sisi);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi prisma harus lebih dari nol.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public void run() {
        volume = menghitungVolume();
        luasPermukaan = menghitungLuasPermukaan();
        calculated = true;
        System.out.println("Thread " + Thread.currentThread().getName() + " - " + getNamaBenda() + ":");
        System.out.printf("Volume: %.2f\n", volume);
        System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
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



    public double menghitungVolume(double sisiBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (sisiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Sisi dan tinggi prisma harus lebih dari nol.");
        }
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

    public double menghitungLuasPermukaan(double sisiBaru, double tinggiPrismaBaru) throws InputMismatchException {
        if (sisiBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Sisi dan tinggi prisma harus lebih dari nol.");
        }
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
                        String inputSisi = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisi);
                        System.out.print("Masukkan tinggi prisma: ");
                        String inputTinggiPrisma = inputData.nextLine();
                        double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);
                        
                        volume = menghitungVolume(sisiBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(sisiBaru, tinggiPrismaBaru);
                        System.out.printf("\nVolume Prisma Persegi: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Persegi: %.2f\n", luasPermukaan);
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