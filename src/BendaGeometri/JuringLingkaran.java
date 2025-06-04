package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JuringLingkaran extends Lingkaran {
    private double sudutJuring;

    public JuringLingkaran(double radius, double sudutJuring) {
        super(radius);
        this.sudutJuring = sudutJuring;
    }

    public JuringLingkaran(int radius, int sudutJuring) {
        super(radius);
        this.sudutJuring = sudutJuring;
    }
    @Override
    public double menghitungLuas() {
        luas = (sudutJuring / 360.0) * super.menghitungLuas();
        return luas;
    }

    public double menghitungLuas(double radiusBaru, double sudutJuringBaru) {
        luas = (sudutJuringBaru / 360.0) * super.menghitungLuas(radiusBaru);
        return luas;
    }

    @Override
    public double menghitungKeliling() {
		double r = super.radius;
        double busur = super.menghitungKeliling() * (sudutJuring / 360.0);
        keliling = (busur + 2 * r);
        return keliling;
    }

    public double menghitungKeliling(double radiusBaru, double sudutJuringBaru) {
        double busur = super.menghitungKeliling(radiusBaru) * (sudutJuringBaru / 360.0);
        keliling = busur + 2 * radiusBaru;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Juring Lingkaran";
    }
    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);

        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai radius dan sudut juring lingkaran? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan radius baru: ");
                        double radiusBaru = inputData.nextDouble();
                        System.out.print("Masukkan sudut juring baru (dalam derajat): ");
                        double sudutJuringBaru = inputData.nextDouble();
                        inputData.nextLine(); 

                        if (radiusBaru <= 0 || sudutJuringBaru <= 0) {
                            System.out.println("Radius dan sudut juring harus lebih dari nol.\n");
                            continue;
                        }

                        luas = menghitungLuas(radiusBaru, sudutJuringBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutJuringBaru);

                        System.out.printf("\nLuas Juring Lingkaran: %.2f\n", luas);
                        System.out.printf("Keliling Juring Lingkaran: %.2f\n", keliling);
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("Input radius dan sudut juring harus berupa angka.");
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
