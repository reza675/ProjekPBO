package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JajaranGenjang extends Benda2D {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring;

	public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring = sisiMiring;
	}

	@Override
	public double menghitungLuas() {
		luas = (alas * tinggi);
		return luas;
	}

	public double menghitungLuas(double alasBaru, double tinggiBaru) {
		luas = alasBaru * tinggiBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * (alas + sisiMiring);
		return keliling;
	}

	public double menghitungKeliling(double alasBaru, double sisiMiringBaru) {
		keliling = 2 * (alasBaru + sisiMiringBaru);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Jajaran Genjang";
	}

	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai alas, tinggi, dan sisi miring Jajaran Genjang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan alas baru: ");
                        double alasBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi baru: ");
                        double tinggiBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring baru: ");
                        double sisiMiringBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (alasBaru <= 0 || tinggiBaru <= 0 || sisiMiringBaru <= 0) {
                            System.out.println("Nilai alas, tinggi, dan sisi miring harus lebih dari nol.\n");
                            continue;
                        }
                        luas = menghitungLuas(alasBaru, tinggiBaru);
                        keliling = menghitungKeliling(alasBaru, sisiMiringBaru);
                        System.out.printf("\nLuas Jajaran Genjang: %.2f\n", luas);
                        System.out.printf("Keliling Jajaran Genjang: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input alas, tinggi, dan sisi miring harus berupa angka.");
                        inputData.nextLine();
                    }
                }
                break;
            } else if (jawaban.equalsIgnoreCase("N")) {
                luas = menghitungLuas();
                keliling = menghitungKeliling();
                break;
            } else {
                System.out.println("Jawaban hanya boleh Y atau N.");
            }
        }
	}
}
