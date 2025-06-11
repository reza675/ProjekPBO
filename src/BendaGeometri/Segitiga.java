package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Segitiga extends Benda2D {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring1;
	protected double sisiMiring2;

	public Segitiga(double alas, double tinggi, double sisiMiring1, double sisiMiring2) throws InputMismatchException {
		if (alas <= 0 || tinggi <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring1 = sisiMiring1;
		this.sisiMiring2 = sisiMiring2;
	}

	@Override
	public double menghitungLuas() {
		luas = (0.5 * alas * tinggi);
		return luas;
	}

	public double menghitungLuas(double alasBaru, double tinggiBaru) throws InputMismatchException {
		if (alasBaru <= 0 || tinggiBaru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		luas = 0.5 * alasBaru * tinggiBaru;
		return luas;

	}

	@Override
	public double menghitungKeliling() {
		keliling = (alas + sisiMiring1 + sisiMiring2);
		return keliling;
	}

	public double menghitungKeliling(double alasBaru, double sisiMiring1Baru, double sisiMiring2Baru)
			throws InputMismatchException {
		if (alasBaru <= 0 || sisiMiring1Baru <= 0 || sisiMiring2Baru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		keliling = alasBaru + sisiMiring1Baru + sisiMiring2Baru;
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Segitiga";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print(
					"\nApakah Anda ingin mengubah nilai alas, tinggi, sisi miring1, dan sisi miring2 Segitiga? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan alas baru: ");
						String inputAlas = inputData.nextLine();
						double alasBaru = Double.parseDouble(inputAlas);
						System.out.print("Masukkan tinggi baru: ");
						String inputTinggi = inputData.nextLine();
						double tinggiBaru = Double.parseDouble(inputTinggi);
						System.out.print("Masukkan sisi miring 1 baru: ");
						String inputSisiMiring1 = inputData.nextLine();
						double sisi1Baru = Double.parseDouble(inputSisiMiring1);
						System.out.print("Masukkan sisi miring 2 baru: ");
						String inputSisiMiring2 = inputData.nextLine();
						double sisi2Baru = Double.parseDouble(inputSisiMiring2);
						luas = menghitungLuas(alasBaru, tinggiBaru);
						keliling = menghitungKeliling(alasBaru, sisi1Baru, sisi2Baru);
						System.out.printf("\nLuas Segitiga: %.2f\n", luas);
						System.out.printf("Keliling Segitiga: %.2f\n", keliling);
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
}
