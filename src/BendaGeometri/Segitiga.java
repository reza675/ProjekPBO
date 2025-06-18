package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Segitiga extends Benda2D implements Runnable {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring1;
	protected double sisiMiring2;
	private volatile boolean calculated = false;

	public Segitiga(double alas, double tinggi, double sisiMiring1, double sisiMiring2) throws InputMismatchException {
		if (alas <= 0 || tinggi <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
			throw new InputMismatchException("alas, tinggi, sisi miring1, dan sisi miring2 harus lebih dari nol.");
		}
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring1 = sisiMiring1;
		this.sisiMiring2 = sisiMiring2;
	}

	@Override
	public void run() {
		try {
			System.out.println("\n=== Perhitungan Segitiga dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 4) {
				if (i + 3 < 1000) {
					double alasBaru = dataArray[i];
					double tinggiBaru = dataArray[i + 1];
					double sisiMiring1Baru = dataArray[i + 2];
					double sisiMiring2Baru = dataArray[i + 3];
					try {
						luas = menghitungLuas(alasBaru, tinggiBaru);
						keliling = menghitungKeliling(alasBaru, sisiMiring1Baru, sisiMiring2Baru);
						System.out.printf("Data %d-%d: alas=%.1f, tinggi=%.1f, sisiMiring1=%.1f, sisiMiring2=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 4, alasBaru, tinggiBaru, sisiMiring1Baru, sisiMiring2Baru, luas, keliling);
					} catch (InputMismatchException e) {
						System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 4, e.getMessage());
					}
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}

	public boolean isCalculated() {
		return calculated;
	}

	@Override
	public double menghitungLuas() {
		luas = (0.5 * alas * tinggi);
		return luas;
	}

	public double menghitungLuas(double alasBaru, double tinggiBaru) throws InputMismatchException {
		if (alasBaru <= 0 || tinggiBaru <= 0) {
			throw new InputMismatchException("Alas dan tinggi harus lebih dari nol.");
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
			throw new InputMismatchException("Alas,sisi miring 1, dan sisi miring 2, dan tinggi harus lebih dari nol.");
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
