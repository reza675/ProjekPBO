package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaPersegiPanjang extends PersegiPanjang {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;


    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) {
        super(panjang, lebar);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double panjangBaru, double lebarBaru, double tinggiPrismaBaru) {
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

    public double menghitungLuasPermukaan(double panjangBaru, double lebarBaru, double tinggiPrismaBaru) {
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
        System.out.print("\nApakah Anda ingin mengubah nilai panjang, lebar, dan tinggi Prisma Persegi Panjang? (Y/N): ");
        String jawaban = inputData.nextLine();
        if (jawaban.equalsIgnoreCase("Y")) {
            while (true) {
                try {
                    System.out.print("Masukkan panjang baru: ");
                    double panjangBaru = inputData.nextDouble();
                    System.out.print("Masukkan lebar baru: ");
                    double lebarBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggi prisma baru: ");
                    double tinggiBaru = inputData.nextDouble();
                    inputData.nextLine();

                    if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiBaru <= 0) {
                        System.out.println("Panjang,; lebar, dan tinggi harus lebih dari nol.\n");
                        continue;
                    }

                    volume = menghitungVolume(panjangBaru, lebarBaru, tinggiBaru);
                    luasPermukaan = menghitungLuasPermukaan(panjangBaru, lebarBaru, tinggiBaru);

                    System.out.printf("\nVolume Prisma Persegi Panjang: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Prisma Persegi Panjang: %.2f\n", luasPermukaan);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input harus berupa angka.");
                    inputData.nextLine();
                }
            }
            break;
        } else if (jawaban.equalsIgnoreCase("N")) {
            volume = menghitungVolume();
            luasPermukaan= menghitungLuasPermukaan();
            break;
        } else {
            System.out.println("Jawaban hanya boleh Y atau N.\n");
        }
    }
}

}