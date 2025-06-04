package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrismaJajaranGenjang extends JajaranGenjang {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiPrisma) {
        super(panjangAlas, tinggiAlas, sisiMiringAlas);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double panjangAlasBaru, double tinggiAlasBaru, double tinggiPrismaBaru) {
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

    public double menghitungLuasPermukaan(double panjangAlas, double tinggiAlas, double sisiMiringAlas,
            double tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        kelilingAlas = super.menghitungKeliling(panjangAlas, sisiMiringAlas);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
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
                    double panjangAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggi alas baru: ");
                    double tinggiAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan sisi miring alas baru: ");
                    double sisiMiringAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggiPrisma baru: ");
                    double tinggiPrismaBaru = inputData.nextDouble();
                    inputData.nextLine();

                    if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 ||
                        sisiMiringAlasBaru <= 0 || tinggiPrismaBaru <= 0) {
                        System.out.println("Semua nilai harus lebih dari nol.\n");
                        continue;
                    }
                    volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiPrismaBaru);
                    luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru,tinggiAlasBaru,sisiMiringAlasBaru,tinggiPrismaBaru);

                    System.out.printf("\nVolume Prisma Jajaran Genjang: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Prisma Jajaran Genjang: %.2f\n", luasPermukaan);
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