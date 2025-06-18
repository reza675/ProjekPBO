package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Trapesium extends Benda2D implements Runnable {

    protected double alasAtas;
    protected double alasBawah;
    protected double tinggi;
    protected double sisiMiringKiri;
    protected double sisiMiringKanan;

    public Trapesium(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri, double sisiMiringKanan) throws InputMismatchException {
        if (alasAtas <= 0 || alasBawah <= 0 || tinggi <= 0 || sisiMiringKiri <= 0 || sisiMiringKanan <= 0) {
            throw new InputMismatchException("Alas atas, alas bawah, tinggi, sisi miring kiri, dan sisi miring kanan harus lebih dari nol.");
        }
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

    public double menghitungLuas(double alasAtasBaru, double alasBawahBaru, double tinggiBaru) throws InputMismatchException {
        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiBaru <= 0 ) {
            throw new InputMismatchException("Alas atas, alas bawah, dan tinggi harus lebih dari nol.");
        }
        luas = 0.5 * (alasAtasBaru + alasBawahBaru) * tinggiBaru;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = (alasAtas + alasBawah + sisiMiringKiri + sisiMiringKanan);
        return keliling;
    }

    public double menghitungKeliling(double alasAtasBaru, double alasBawahBaru, double sisiMiringKananBaru,
            double sisiMiringKiriBaru) throws InputMismatchException {
        if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || sisiMiringKiriBaru <= 0 || sisiMiringKananBaru <= 0) {
            throw new InputMismatchException("Alas atas, alas bawah, tinggi, sisi miring kiri, dan sisi miring kanan harus lebih dari nol.");
        }
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
                        String inputAlasAtasBaru = inputData.nextLine();
                        double alasAtasBaru = Double.parseDouble(inputAlasAtasBaru);
                        System.out.print("Masukkan alasBawah baru: ");
                        String inputAlasBawahBaru = inputData.nextLine();
                        double alasBawahBaru = Double.parseDouble(inputAlasBawahBaru);
                        System.out.print("Masukkan tinggi baru: ");
                        String inputTinggiBaru = inputData.nextLine();
                        double tinggiBaru = Double.parseDouble(inputTinggiBaru);
                        System.out.print("Masukkan sisiMiringKiri baru: ");
                        String inputSisiMiringKiriBaru = inputData.nextLine();
                        double sisiMiringKiriBaru = Double.parseDouble(inputSisiMiringKiriBaru);
                        System.out.print("Masukkan sisiMiringKanan baru: ");
                        String inputSisiMiringKananBaru = inputData.nextLine();
                        double sisiMiringKananBaru = Double.parseDouble(inputSisiMiringKananBaru);

                        luas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiBaru);
                        keliling = menghitungKeliling(alasAtasBaru, alasBawahBaru, sisiMiringKananBaru,
                                sisiMiringKiriBaru);

                        System.out.printf("\nLuas Trapesium: %.2f\n", luas);
                        System.out.printf("Keliling Trapesium: %.2f\n", keliling);
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
            System.out.println("\n=== Perhitungan Trapesium dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 5) {
                if (i + 4 < 1000) {
                    double alasAtasBaru = dataArray[i];
                    double alasBawahBaru = dataArray[i + 1];
                    double tinggiBaru = dataArray[i + 2];
                    double sisiMiringKiriBaru = dataArray[i + 3];
                    double sisiMiringKananBaru = dataArray[i + 4];
                    try {
                        luas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiBaru);
                        keliling = menghitungKeliling(alasAtasBaru, alasBawahBaru, sisiMiringKananBaru, sisiMiringKiriBaru);
                        System.out.printf("Data %d-%d: alasAtas=%.1f, alasBawah=%.1f, tinggi=%.1f, sisiMiringKiri=%.1f, sisiMiringKanan=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 5, alasAtasBaru, alasBawahBaru, tinggiBaru, sisiMiringKiriBaru, sisiMiringKananBaru, luas, keliling);
                    } catch (InputMismatchException e) {
                        System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 5, e.getMessage());
                    }
                }
            }
            System.out.println("\nPerhitungan selesai untuk 1000 data!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

}
