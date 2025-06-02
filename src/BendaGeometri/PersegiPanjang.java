package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersegiPanjang extends Benda2D {
	protected double panjang;
	protected double lebar;

	public PersegiPanjang(double panjang, double lebar) {
		this.panjang = panjang;
		this.lebar = lebar;
	}

	@Override
	public double menghitungLuas() {
		luas = (panjang * lebar);
		return luas;
	}

	public double menghitungLuas(double panjangBaru, double lebarBaru) {
		luas = panjangBaru * lebarBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * (panjang + lebar);
		return keliling;
	}

	public double menghitungKeliling(double panjangBaru, double lebarBaru) {
		keliling = 2 * (panjangBaru + lebarBaru);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Persegi Panjang";
	}
	public void prosesInputData() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai panjang dan lebar persegi panjang? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang persegi panjang baru: ");
                        double panjangBaru = inputData.nextDouble();
						System.out.print("Masukkan lebar persegi panjang baru: ");
                        double lebarBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (panjangBaru <= 0 || lebarBaru <= 0) {
                            System.out.println("panjang dan lebar harus lebih dari nol.\n");
                            continue;
                        }
                        panjang = panjangBaru;
						lebar = lebarBaru;
                        luas = menghitungLuas(panjangBaru, lebarBaru);
                        keliling = menghitungKeliling(panjangBaru, lebarBaru);
                        System.out.printf("\nLuas Persegi Panjang: %.2f\n", luas);
                        System.out.printf("Keliling Persegi Panjang: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input panjang dan lebar persegi panjang harus berupa angka.");
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
