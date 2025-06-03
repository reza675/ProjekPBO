package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LayangLayang extends Benda2D {

    protected double diagonal1;
    protected double diagonal2;
    protected double sisiPendek;
    protected double sisiPanjang;

    public LayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
    }

    @Override
    public double menghitungLuas() {
        luas = (diagonal1 * diagonal2) / 2.0;
        return luas;
    }

    public double menghitungLuas(double diagonal1Baru, double diagonal2Baru) {
        luas = (diagonal1Baru * diagonal2Baru) / 2.0;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }

    public double menghitungKeliling(double sisiPendekBaru, double sisiPanjangBaru) {
        keliling = 2 * (sisiPendekBaru + sisiPanjangBaru);
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Layang-Layang";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);

        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisiPendek, dan sisiPanjang Layang-Layang? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 baru: ");
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 baru: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi pendek baru: ");
                        double sisiPendekBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi panjang baru: ");
                        double sisiPanjangBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }

                        luas = menghitungLuas(diagonal1Baru, diagonal2Baru);
                        keliling = menghitungKeliling(sisiPendekBaru, sisiPanjangBaru);

                        System.out.printf("\nLuas Layang-Layang: %.2f\n", luas);
                        System.out.printf("Keliling Layang-Layang: %.2f\n", keliling);
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("Input diagonal dan sisi harus berupa angka.");
                        inputData.nextLine();
                    }
                }
                break;

            } else if (jawaban.equalsIgnoreCase("N")) {
                luas = menghitungLuas();
                keliling = menghitungKeliling();
                break;

            } else {
                System.out.println("Jawaban hanya boleh Y atau N.\n");
            }
        }
    }
}
