package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JuringLingkaran extends Lingkaran implements Runnable {
    private double sudutJuring;
    private boolean isRunning;

    public JuringLingkaran(double radius, double sudutJuring) throws InputMismatchException {
        super(radius);
        if (sudutJuring <= 0 || sudutJuring > 360){
            throw new InputMismatchException("Sudut juring harus lebih dari nol dan tidak lebih dari 360°.");
        }
        this.sudutJuring = sudutJuring;
        this.isRunning = true;
    }

    public JuringLingkaran(int radius, int sudutJuring) {
        super(radius);
        this.sudutJuring = sudutJuring;
        this.isRunning = true;
    }
    @Override
    public double menghitungLuas() {
        luas = (sudutJuring / 360.0) * super.menghitungLuas();
        return luas;
    }

    public double menghitungLuas(double radiusBaru, double sudutJuringBaru) throws InputMismatchException {
        if (sudutJuringBaru <= 0 || radiusBaru <= 0 || sudutJuringBaru > 360 ) {
            throw new InputMismatchException("Radius,sudut juring harus lebih dari nol dan tidak lebih dari 360°.");
            
        }
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
        if (sudutJuringBaru <= 0 || radiusBaru <= 0 || sudutJuringBaru > 360) {
            throw new InputMismatchException("Radius,sudut juring harus lebih dari nol dan tidak lebih dari 360°.");
        }
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
                        String inputRadiusBaru = inputData.nextLine();
                        double radiusBaru = Double.parseDouble(inputRadiusBaru);
                        System.out.print("Masukkan sudut juring baru (dalam derajat): ");
                        String inputJuringBaru = inputData.nextLine();
                        double sudutJuringBaru = Double.parseDouble(inputJuringBaru);

                        luas = menghitungLuas(radiusBaru, sudutJuringBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutJuringBaru);

                        System.out.printf("\nLuas Juring Lingkaran: %.2f\n", luas);
                        System.out.printf("Keliling Juring Lingkaran: %.2f\n", keliling);
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
            System.out.println("\n=== Perhitungan Juring Lingkaran dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 2) {
                if (i + 1 < 1000) {
                    double radiusBaru = dataArray[i];
                    double sudutJuringBaru = dataArray[i + 1];
                    try {
                        luas = menghitungLuas(radiusBaru, sudutJuringBaru);
                        keliling = menghitungKeliling(radiusBaru, sudutJuringBaru);
                        System.out.printf("Data %d-%d: radius=%.1f, sudut=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 2, radiusBaru, sudutJuringBaru, luas, keliling);
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
}
