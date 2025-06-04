package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Trapesium extends Benda2D {

    protected double alasAtas;
    protected double alasBawah;
    protected double tinggi;
    protected double sisiMiringKiri;
    protected double sisiMiringKanan;

    public Trapesium(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri, double sisiMiringKanan) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.sisiMiringKiri = sisiMiringKiri;
        this.sisiMiringKanan = sisiMiringKanan;
    }

    @Override
    public double menghitungLuas() {
        luas = (0.5 * (alasAtas + alasBawah) * tinggi);
        return luas;
    }

    public double menghitungLuas(double alasAtasBaru, double alasBawahBaru, double tinggiBaru) {
        luas = 0.5 * (alasAtasBaru + alasBawahBaru) * tinggiBaru;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = (alasAtas + alasBawah + sisiMiringKiri + sisiMiringKanan);
        return keliling;
    }

    public double menghitungKeliling(double alasAtasBaru, double alasBawahBaru, double sisiMiringKananBaru,
            double sisiMiringKiriBaru) {
        keliling = alasAtasBaru + alasBawahBaru + sisiMiringKananBaru + sisiMiringKiriBaru;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Trapesium";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai alasAtas, alasBawah, tinggi, sisiMiringKiri, dan sisiMiringKanan Trapesium? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan alasAtas baru: ");
                        double alasAtasBaru = inputData.nextDouble();
                        System.out.print("Masukkan alasBawah baru: ");
                        double alasBawahBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi baru: ");
                        double tinggiBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisiMiringKiri baru: ");
                        double sisiMiringKiriBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisiMiringKanan baru: ");
                        double sisiMiringKananBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiBaru <= 0 || sisiMiringKiriBaru <= 0
                                || sisiMiringKananBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        luas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiBaru);
                        keliling = menghitungKeliling(alasAtasBaru, alasBawahBaru, sisiMiringKananBaru,
                                sisiMiringKiriBaru);

                        System.out.printf("\nLuas Trapesium: %.2f\n", luas);
                        System.out.printf("Keliling Trapesium: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input alas, tinggi, dan sisi miring harus berupa angka.");
                        inputData.nextLine();
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
