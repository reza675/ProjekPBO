package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lingkaran extends Benda2D {

	protected double radius;
	protected final double PI = 3.14;


	public Lingkaran(double radius) {
		this.radius = radius;
	}

	@Override
	public double menghitungLuas() {
		luas = PI * radius * radius;
		return luas;
	}

	public double menghitungLuas(double radiusBaru) {
		luas = PI * radiusBaru * radiusBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * PI * radius;
		return keliling;
	}
	public double menghitungKeliling(double radiusBaru) {
		keliling = 2 * PI * radiusBaru;
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Lingkaran";
	}
	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai radius lingkaran? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan radius lingkaran baru: ");
                        double radiusBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (radiusBaru <= 0) {
                            System.out.println("Radius harus lebih dari nol.\n");
                            continue;
                        }
                        luas = menghitungLuas(radiusBaru);
                        keliling = menghitungKeliling(radiusBaru);
                        System.out.printf("\nLuas Lingkaran: %.2f\n", luas);
                        System.out.printf("Keliling Lingkaran: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input sisi lingkaran harus berupa angka.");
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
