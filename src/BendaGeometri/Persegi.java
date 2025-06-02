package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Persegi extends Benda2D {
	protected double sisi;

	public Persegi(double sisi) {
		this.sisi = sisi;
	}
	@Override
	public double menghitungLuas() {
		luas = (sisi * sisi);
		return luas;
	}

	public double menghitungLuas(double sisiBaru) {
		luas = sisiBaru * sisiBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = (4 * this.sisi);
		return keliling;
	}

	public double menghitungKeliling(double sisiBaru) {
		keliling = 4 * sisiBaru;
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Persegi";
	}
	public void prosesInputData() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai sisi persegi? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan sisi persegi baru: ");
                        double sisiBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (sisiBaru <= 0) {
                            System.out.println("Sisi harus lebih dari nol.\n");
                            continue;
                        }
                        sisi = sisiBaru;
                        luas = menghitungLuas(sisiBaru);
                        keliling = menghitungKeliling(sisiBaru);
                        System.out.printf("\nLuas Persegi: %.2f\n", luas);
                        System.out.printf("Keliling Persegi: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input sisi persegi harus berupa angka.");
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
