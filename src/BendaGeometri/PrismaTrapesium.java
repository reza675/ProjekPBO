package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaTrapesium extends Trapesium {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaTrapesium(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri, double sisiMiringKanan,
            double tinggiPrisma) {
        super(alasAtas, alasBawah, tinggiPrisma, sisiMiringKiri, sisiMiringKanan);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double alasAtasBaru, double alasBawahBaru, double tinggiBaru, double tinggiPrismaBaru) {
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

    public double menghitungLuasPermukaan(double alasAtasBaru, double alasBawahBaru, double tinggiBaru, double sisiMiringKiriBaru,
            double sisiMiringKananBaru, double tinggiPrismaBaru) {
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
                        double alasAtasBaru = inputData.nextDouble();
                        System.out.print("Masukkan alas bawah baru: ");
                        double alasBawahBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi trapesium (alas) baru: ");
                        double tinggiBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring kiri baru: ");
                        double sisiKiriBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring kanan baru: ");
                        double sisiKananBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi prisma baru: ");
                        double tinggiPrismaBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiBaru <= 0 ||
                            sisiKiriBaru <= 0 || sisiKananBaru <= 0 || tinggiPrismaBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(alasAtasBaru, alasBawahBaru, tinggiBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasAtasBaru, alasBawahBaru, tinggiBaru, sisiKiriBaru, sisiKananBaru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Trapesium: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Trapesium: %.2f\n", luasPermukaan);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa angka.");
                        inputData.nextLine();
                    }
                }
                break;
            } else if (jawaban.equalsIgnoreCase("N")) {
                volume = menghitungVolume();
                luasPermukaan = menghitungLuasPermukaan();
                break;
            } else {
                System.out.println("Jawaban hanya boleh Y atau N.");
            }
        }
    }

}