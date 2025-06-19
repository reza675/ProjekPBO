package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bola extends Lingkaran implements Runnable {
	

	protected double luasAlas;
	protected double volume;
	protected double luasPermukaan;

	public Bola(double radius) throws InputMismatchException {
		super(radius);
	}
	@Override
	public void run() {
		try {
			System.out.println("\n=== Perhitungan Bola dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				if (ThreadInterruptionUtil.checkAndHandleInterruption("data array initialization")) {
					return;
				}
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i++) {
				if (ThreadInterruptionUtil.checkInterruptionPeriodic(i, 50, "bola calculations")) {
					return;
				}
				
				double radiusBaru = dataArray[i];
				try {
					volume = menghitungVolume(radiusBaru);
					luasPermukaan = menghitungLuasPermukaan(radiusBaru);
					System.out.printf("Data %d: radius=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, radiusBaru, volume, luasPermukaan);
				} catch (InputMismatchException e) {
					System.out.printf("Data %d: Error - %s\n", i + 1, e.getMessage());
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}


	
	

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (4.0 / 3.0) * luasAlas * radius;
		return volume;
	}

	public double menghitungVolume(double radiusBaru) throws InputMismatchException {
		if (radiusBaru <= 0) {
			throw new InputMismatchException("Radius harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(radiusBaru);
		volume = (4.0 / 3.0) * luasAlas * radiusBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru) throws InputMismatchException {
		if (radiusBaru <= 0) {
			throw new InputMismatchException("Radius harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(radiusBaru);
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Bola";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari bola? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari bola: ");
						String inputRadius = inputData.nextLine();
						double radiusBaru = Double.parseDouble(inputRadius);
						
						volume = menghitungVolume(radiusBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru);

						System.out.printf("\nVolume Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Bola: %.2f\n", luasPermukaan);
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