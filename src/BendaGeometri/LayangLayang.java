package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LayangLayang extends Benda2D implements Runnable {

    protected double diagonal1;
    protected double diagonal2;
    protected double sisiPendek;
    protected double sisiPanjang;

    public LayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang)
            throws InputMismatchException {
        if (diagonal1 <= 0 || diagonal2 <= 0 || sisiPendek <= 0 || sisiPanjang <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
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

    public double menghitungLuas(double diagonal1Baru, double diagonal2Baru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
        luas = (diagonal1Baru * diagonal2Baru) / 2.0;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = 2 * (sisiPendek + sisiPanjang);
        return keliling;
    }

    public double menghitungKeliling(double sisiPendekBaru, double sisiPanjangBaru) throws InputMismatchException {
        if (sisiPendekBaru <= 0 || sisiPanjangBaru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
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
                        String inputDiagonal1 = inputData.nextLine();
                        double diagonal1Baru = Double.parseDouble(inputDiagonal1);
                        System.out.print("Masukkan diagonal2 baru: ");
                        String inputDiagonal2 = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2);
                        System.out.print("Masukkan sisi pendek baru: ");
                        String inputSisiPendekBaru = inputData.nextLine();
                        double sisiPendekBaru = Double.parseDouble(inputSisiPendekBaru);
                        System.out.print("Masukkan sisi panjang baru: ");
                        String inputSisiPanjangBaru = inputData.nextLine();
                        double sisiPanjangBaru = Double.parseDouble(inputSisiPanjangBaru);

                        luas = menghitungLuas(diagonal1Baru, diagonal2Baru);
                        keliling = menghitungKeliling(sisiPendekBaru, sisiPanjangBaru);

                        System.out.printf("\nLuas Layang-Layang: %.2f\n", luas);
                        System.out.printf("Keliling Layang-Layang: %.2f\n", keliling);
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

    @Override
    public void run() {
        luas = menghitungLuas();
        keliling = menghitungKeliling();
        System.out.printf("\nLuas %s: %.2f\n", getNamaBenda(), luas);
        System.out.printf("Keliling %s: %.2f\n", getNamaBenda(), keliling);
        prosesInputDataUlang();
    }
}
