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
        Scanner inputData = new Scanner(System.in);
        while (isRunning) {
            System.out.print("\nApakah Anda ingin mengubah nilai radius dan sudut Tembereng Lingkaran? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (isRunning) {
                    try {
                        System.out.print("Masukkan radius baru: ");
                        String inputRadiusBaru = inputData.nextLine();
                        double radiusBaru = Double.parseDouble(inputRadiusBaru);
                        System.out.print("Masukkan sudut baru (dalam derajat): ");
                        String inputSudutBaru = inputData.nextLine();
                        double sudutBaru = Double.parseDouble(inputSudutBaru);
                        luas = menghitungLuas(radiusBaru, sudutBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutBaru);
                        System.out.printf("\nLuas Tembereng Lingkaran: %.2f\n", luas);
                        System.out.printf("Keliling Tembereng Lingkaran: %.2f\n", keliling);
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

    public void stop() {
        this.isRunning = false;
    }

    @Deprecated
    public void prosesInputData() {
        run();
    }
}
