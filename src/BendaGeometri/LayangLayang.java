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
            throw new InputMismatchException("Diagonal1, diagonal2, sisi pendek, dan sisi panjang harus lebih dari nol.");
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
            throw new InputMismatchException("Diagonal1 dan diagonal2 harus lebih dari nol.");
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
            throw new InputMismatchException("Sisi pendek dan sisi panjang harus lebih dari nol.");
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
        try {
            System.out.println("\n=== Perhitungan Layang-Layang dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 4) {
                if (i + 3 < 1000) {
                    double diagonal1Baru = dataArray[i];
                    double diagonal2Baru = dataArray[i + 1];
                    double sisiPendekBaru = dataArray[i + 2];
                    double sisiPanjangBaru = dataArray[i + 3];
                    try {
                        luas = menghitungLuas(diagonal1Baru, diagonal2Baru);
                        keliling = menghitungKeliling(sisiPendekBaru, sisiPanjangBaru);
                        System.out.printf("Data %d-%d: diagonal1=%.1f, diagonal2=%.1f, sisiPendek=%.1f, sisiPanjang=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 4, diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru, luas, keliling);
                    } catch (InputMismatchException e) {
                        System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 4, e.getMessage());
                    }
                }
            }
            System.out.println("\nPerhitungan selesai untuk 1000 data!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
