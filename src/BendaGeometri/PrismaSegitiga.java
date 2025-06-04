package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaSegitiga extends Segitiga {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2, double tinggiPrisma) {
        super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double alasBaru, double tinggiBaru, double tinggiPrismaBaru) {
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

    public double menghitungLuasPermukaan(double alasBaru, double tinggiBaru, double sisiMiring1Baru,double sisiMiring2Baru,double tinggiPrismaBaru) {
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
                        double alasBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi segitiga: ");
                        double tinggiSegitigaBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring 1 segitiga: ");
                        double sisiMiring1Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring 2 segitiga: ");
                        double sisiMiring2Baru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi prisma: ");
                        double tinggiPrismaBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (alasBaru <= 0 || tinggiSegitigaBaru <= 0 || sisiMiring1Baru <= 0 || sisiMiring2Baru <= 0
                                || tinggiPrismaBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru,
                                sisiMiring2Baru, tinggiPrismaBaru);

                        System.out.printf("\nVolume Prisma Segitiga: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Segitiga: %.2f\n", luasPermukaan);
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