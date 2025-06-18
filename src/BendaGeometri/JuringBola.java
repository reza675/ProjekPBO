package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JuringBola extends Bola implements Runnable {

    private double sudutBola;
    private double volume;
    private double luasPermukaan;
    private double volumeBola;
    private double luasPermukaanBola;

    public JuringBola(double radius, double sudutBola) throws InputMismatchException {
        super(radius);
        if (sudutBola <= 0 || sudutBola > 360) {
            throw new InputMismatchException("Sudut juring bola harus lebih dari nol dan tidak lebih dari 360°.");
        }
        this.sudutBola = sudutBola;
    }

    public double menghitungVolume() {
        volumeBola = super.menghitungVolume();
        volume = volumeBola * (sudutBola / 360.0);
        return volume;
    }

    public double menghitungVolume(double sudutBolaBaru, double radiusBolaBaru) throws InputMismatchException {
        if (sudutBolaBaru <= 0 || radiusBolaBaru <= 0 ||sudutBolaBaru >360) {
            throw new InputMismatchException("sudut bola,radius bola harus lebih dari 0 dan sudut bola tidak lebih dari 360°.");
        }
        volumeBola = super.menghitungVolume(radiusBolaBaru);
        volume = volumeBola * (sudutBolaBaru / 360.0);
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasPermukaanBola = super.menghitungLuasPermukaan();
        luasPermukaan = luasPermukaanBola * (sudutBola / 360.0);
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double sudutBolaBaru, double radiusBolaBaru) throws InputMismatchException {
        if (sudutBolaBaru <= 0 || radiusBolaBaru <= 0 || sudutBolaBaru > 360) {
            throw new InputMismatchException("sudut bola, radius bola harus lebih dari 0 dan sudut bola tidak lebih dari 360°");
        }
        luasPermukaanBola = super.menghitungLuasPermukaan(radiusBolaBaru);
        luasPermukaan = luasPermukaanBola * (sudutBolaBaru / 360.0);
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Juring Bola";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan sudut juring bola? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan jari-jari bola: ");
                        String inputRadius = inputData.nextLine();
                        double radiusBolaBaru = Double.parseDouble(inputRadius);
                        System.out.print("Masukkan besar sudut juring (derajat): ");
                        String inputSudutBola = inputData.nextLine();
                        double sudutBolaBaru = Double.parseDouble(inputSudutBola);

                        volume = menghitungVolume(sudutBolaBaru, radiusBolaBaru);
                        luasPermukaan = menghitungLuasPermukaan(sudutBolaBaru, radiusBolaBaru);

                        System.out.printf("\nVolume Juring Bola: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Juring Bola: %.2f\n", luasPermukaan);
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
            System.out.println("\n=== Perhitungan Juring Bola dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 2) {
                if (i + 1 < 1000) {
                    double sudutBolaBaru = dataArray[i];
                    double radiusBolaBaru = dataArray[i + 1];
                    try {
                        volume = menghitungVolume(sudutBolaBaru, radiusBolaBaru);
                        luasPermukaan = menghitungLuasPermukaan(sudutBolaBaru, radiusBolaBaru);
                        System.out.printf("Data %d-%d: sudut=%.1f, radius=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 2, sudutBolaBaru, radiusBolaBaru, volume, luasPermukaan);
                    } catch (InputMismatchException e) {
                        System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 2, e.getMessage());
                    }
                }
            }
            System.out.println("\nPerhitungan selesai untuk 1000 data!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}