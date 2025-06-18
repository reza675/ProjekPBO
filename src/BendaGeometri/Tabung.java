package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class Tabung extends Lingkaran implements Runnable {

	private double tinggiTabung;
	private double volume;
	private double luasAlas;
	private double selimut;
	private double luasPermukaan;
	private volatile boolean calculated = false;
	private final Object lock = new Object();

	public Tabung(double radius, double tinggiTabung) throws InputMismatchException {
		super(radius);
		if (tinggiTabung <= 0) {
			throw new InputMismatchException("Tinggi tabung harus lebih dari nol.");
		}
		this.tinggiTabung = tinggiTabung;
	}

	@Override
	public void run() {
		synchronized (lock) {
			try {
				System.out.println("\n=== Perhitungan Tabung dengan 1000 Data ===");
				double[] dataArray = new double[1000];
				for (int i = 0; i < 1000; i++) {
					dataArray[i] = i + 1;
				}
				for (int i = 0; i < 1000; i += 2) {
					if (i + 1 < 1000) {
						double radiusBaru = dataArray[i];
						double tinggiTabungBaru = dataArray[i + 1];
						try {
							volume = menghitungVolume(radiusBaru, tinggiTabungBaru);
							luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTabungBaru);
							System.out.printf("Data %d-%d: radius=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 2, radiusBaru, tinggiTabungBaru, volume, luasPermukaan);
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
	}

	public void waitForCalculation() throws InterruptedException {
		synchronized (lock) {
			while (!calculated) {
				System.out.println("Thread " + Thread.currentThread().getName() + " waiting for " + getNamaBenda()
						+ " calculations...");
				lock.wait();
			}
			System.out.println(
					"Thread " + Thread.currentThread().getName() + " received " + getNamaBenda() + " results:");
			System.out.printf("Volume: %.2f\n", volume);
			System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
		}
	}

	public boolean isCalculated() {
		synchronized (lock) {
			return calculated;
		}
	}

	public double getVolume() {
		synchronized (lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return volume;
		}
	}

	public double getLuasPermukaan() {
		synchronized (lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return luasPermukaan;
		}
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = luasAlas * tinggiTabung;
		return volume;
	}

	public double menghitungVolume(double radiusBaru, double tinggiTabungBaru) throws InputMismatchException {
		if (radiusBaru <= 0) {
			throw new InputMismatchException("Radius harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(radiusBaru);
		volume = luasAlas * tinggiTabungBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = 2 * super.menghitungLuas();
		selimut = super.menghitungKeliling() * tinggiTabung;
		luasPermukaan = luasAlas + selimut;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiTabungBaru) throws InputMismatchException {
		if (radiusBaru <= 0 || tinggiTabungBaru <= 0) {
			throw new InputMismatchException("Radius dan tinggi harus lebih dari nol.");
		}
		luasAlas = 2 * super.menghitungLuas(radiusBaru);
		selimut = super.menghitungKeliling(radiusBaru) * tinggiTabungBaru;
		luasPermukaan = luasAlas + selimut;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Tabung";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan tinggi tabung? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari tabung: ");
						String inputRadius = inputData.nextLine();
						double radiusBaru = Double.parseDouble(inputRadius);
						System.out.print("Masukkan tinggi tabung: ");
						String inputTinggiPrisma = inputData.nextLine();
						double tinggiTabungBaru = Double.parseDouble(inputTinggiPrisma);

						volume = menghitungVolume(radiusBaru, tinggiTabungBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTabungBaru);

						System.out.printf("\nVolume Tabung: %.2f\n", volume);
						System.out.printf("Luas Permukaan Tabung: %.2f\n", luasPermukaan);
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