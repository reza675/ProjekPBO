package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BelahKetupat extends Benda2D {
	protected double diagonal1;
	protected double diagonal2;
	protected double sisi;

	public BelahKetupat(double diagonal1, double diagonal2, double sisi) {
		this.diagonal1 = diagonal1;
		this.diagonal2 = diagonal2;
		this.sisi = sisi;
	}
	@Override
	public double menghitungLuas() {
		luas = (diagonal1 * diagonal2) / 2;
		return luas;
	}
	public double menghitungLuas(double diagonal1Baru, double diagonal2Baru) {
		luas = (diagonal1Baru * diagonal2Baru) / 2.0;
		return luas;
	}
	@Override
	public double menghitungKeliling() {
		keliling = (4 * sisi);
		return keliling;
	}
	public double menghitungKeliling(double sisiBaru) {
		keliling = (4 * sisiBaru);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Belah Ketupat";
	}
	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, dan sisi Belah Ketupat? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 baru: ");
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 baru: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi baru: ");
                        double sisiBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiBaru <= 0) {
                            System.out.println("Diagonal dan sisi harus lebih dari nol.\n");
                            continue;
                        }
                        luas = menghitungLuas(diagonal1Baru, diagonal2Baru);
                        keliling = menghitungKeliling(sisiBaru);
                        System.out.printf("\nLuas Belah Ketupat: %.2f\n", luas);
                        System.out.printf("Keliling Belah Ketupat: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input diagonal dan sisi harus berupa angka.");
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
