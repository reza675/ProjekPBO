package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemberengLingkaran extends Lingkaran implements Runnable {
    private double sudut;
    private volatile boolean isRunning;

    public TemberengLingkaran(double radius, double sudut) throws InputMismatchException {
        super(radius);
        if (sudut <= 0 || sudut > 360) {
            throw new InputMismatchException("Sudut tembereng lingkaran harus lebih dari nol dan tidak lebih dari 360°.");
        }
        this.sudut = sudut;
        this.isRunning = true;
    }

    @Override
    public double menghitungLuas() {
        double sudutRadian = Math.toRadians(sudut);
        double luasJuringLingkaran = (sudut / 360.0) * super.PI * super.radius * super.radius;
        double luasSegitiga = 0.5 * super.radius * super.radius * Math.sin(sudutRadian);
        luas = luasJuringLingkaran - luasSegitiga;
        return luas;
    }

    public double menghitungLuas(double radiusBaru, double sudutBaru) throws InputMismatchException {
        if (radiusBaru <= 0 || sudutBaru <= 0 || sudutBaru > 360) {
            throw new InputMismatchException("Radius,sudut harus lebih dari nol dan sudut tidak boleh lebih dari 360°.");
        }
        double sudutRadian = Math.toRadians(sudutBaru);
        double luasJuringLingkaran = (sudutBaru / 360.0) * super.PI * radiusBaru * radiusBaru;
        double luasSegitiga = 0.5 * radiusBaru * radiusBaru * Math.sin(sudutRadian);
        luas = luasJuringLingkaran - luasSegitiga;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        double sudutRadian = Math.toRadians(sudut);
        double panjangBusur = (sudut / 360.0) * 2 * super.PI * super.radius;
        double taliBusur = 2 * super.radius * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    public double menghitungKeliling(double radiusBaru, double sudutBaru) throws InputMismatchException {
        if (radiusBaru <= 0 || sudutBaru <= 0 || sudutBaru > 360) {
            throw new InputMismatchException("Radius,sudut harus lebih dari nol dan sudut tidak boleh lebih dari 360°.");
        }
        double sudutRadian = Math.toRadians(sudutBaru);
        double panjangBusur = (sudutBaru / 360.0) * 2 * super.PI * radiusBaru;
        double taliBusur = 2 * radiusBaru * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Tembereng Lingkaran";
    }

    @Override
    public void run() {
        try {
            System.out.println("\n=== Perhitungan Tembereng Lingkaran dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 2) {
                if (i + 1 < 1000) {
                    double radiusBaru = dataArray[i];
                    double sudutBaru = dataArray[i + 1];
                    try {
                        luas = menghitungLuas(radiusBaru, sudutBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutBaru);
                        System.out.printf("Data %d-%d: radius=%.1f, sudut=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 2, radiusBaru, sudutBaru, luas, keliling);
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

    public void stop() {
        this.isRunning = false;
    }

    @Deprecated
    public void prosesInputData() {
        run();
    }
}
