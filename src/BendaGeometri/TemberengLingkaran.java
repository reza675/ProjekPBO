package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemberengLingkaran extends Lingkaran {
    private double sudut;

    public TemberengLingkaran(double radius, double sudut) {
        super(radius);
        this.sudut = sudut;
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
        if (radiusBaru <= 0 || sudutBaru <= 0) {
            throw new InputMismatchException("Radius dan sudut harus lebih dari nol.");
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
        double panjangBusur = sudutRadian * super.radius;
        double taliBusur = 2 * super.radius * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    public double menghitungKeliling(double radiusBaru, double sudutBaru) throws InputMismatchException {
        if (radiusBaru <= 0 || sudutBaru <= 0) {
            throw new InputMismatchException("Radius dan sudut harus lebih dari nol.");
        }
        double sudutRadian = Math.toRadians(sudutBaru);
        double panjangBusur = sudutRadian * radiusBaru;
        double taliBusur = 2 * radiusBaru * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }


    @Override
    public String getNamaBenda() {
        return "Tembereng Lingkaran";
    }

    public void prosesInputData() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai radius dan sudut Tembereng Lingkaran? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan radius baru: ");
                        double radiusBaru = inputData.nextDouble();
                        System.out.print("Masukkan sudut baru (dalam derajat): ");
                        double sudutBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (radiusBaru <= 0 || sudutBaru <= 0) {
                            System.out.println("Radius dan sudut harus lebih dari nol.\n");
                            continue;
                        }
                        luas = menghitungLuas(radiusBaru, sudutBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutBaru);
                        System.out.printf("\nLuas Tembereng Lingkaran: %.2f\n", luas);
                        System.out.printf("Keliling Tembereng Lingkaran: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input radius dan sudut harus berupa angka.");
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
