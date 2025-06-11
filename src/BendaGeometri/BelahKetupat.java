package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BelahKetupat extends Benda2D implements Runnable {
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
                        String inputDiagonal1Baru = inputData.nextLine();
                        double diagonal1Baru = Double.parseDouble(inputDiagonal1Baru);
                        System.out.print("Masukkan diagonal2 baru: ");
                        String inputDiagonal2Baru = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2Baru);
                        System.out.print("Masukkan sisi baru: ");
                        String inputSisiBaru = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisiBaru);

                        luas = menghitungLuas(diagonal1Baru, diagonal2Baru);
                        keliling = menghitungKeliling(sisiBaru);
                        System.out.printf("\nLuas Belah Ketupat: %.2f\n", luas);
                        System.out.printf("Keliling Belah Ketupat: %.2f\n", keliling);
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
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("\n=== Input Data Belah Ketupat ===");
			System.out.print("Masukkan diagonal1: ");
			diagonal1 = scanner.nextDouble();
			System.out.print("Masukkan diagonal2: ");
			diagonal2 = scanner.nextDouble();
			System.out.print("Masukkan sisi: ");
			sisi = scanner.nextDouble();
			
			if (diagonal1 <= 0 || diagonal2 <= 0 || sisi <= 0) {
				System.out.println("Diagonal dan sisi harus lebih dari nol.");
				return;
			}
			
			luas = menghitungLuas();
			keliling = menghitungKeliling();
			
			System.out.printf("\nLuas Belah Ketupat: %.2f\n", luas);
			System.out.printf("Keliling Belah Ketupat: %.2f\n", keliling);
			
			prosesInputDataUlang();
			
		} catch (InputMismatchException e) {
			System.out.println("Input tidak valid. Pastikan memasukkan angka yang benar.");
		}
	}
}
