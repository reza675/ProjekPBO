package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemberengBola extends Bola implements Runnable {

	private double tinggiTemberengBola;
	private double volume;
	private double luasPermukaan;

	public TemberengBola(double radius, double tinggiTemberengBola) throws InputMismatchException {
		super(radius);
		if (tinggiTemberengBola <= 0) {
			throw new InputMismatchException("Tinggi tembereng bola harus lebih dari nol.");
		}
		this.tinggiTemberengBola = tinggiTemberengBola;
	}

	public double menghitungVolumeTemberengBola() {
		volume = ((tinggiTemberengBola * tinggiTemberengBola * PI) / 3) * (3 * radius - tinggiTemberengBola);
		return volume;
	}

	public double menghitungVolume(double radiusBolaBaru, double tinggiTemberengBolaBaru)
			throws InputMismatchException {
		if (radiusBolaBaru <= 0 || tinggiTemberengBolaBaru <= 0) {
			throw new InputMismatchException("Radius dan tinggi tembereng bola harus lebih dari nol.");
		}
		volume = ((tinggiTemberengBolaBaru * tinggiTemberengBolaBaru * PI) / 3)
				* (3 * radiusBolaBaru - tinggiTemberengBolaBaru);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasPermukaan = 2 * radius * tinggiTemberengBola * PI + radius * radius * PI;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiTemberengBolaBaru)
			throws InputMismatchException {
		if (radiusBaru <= 0 || tinggiTemberengBolaBaru <= 0) {
			throw new InputMismatchException("Radius dan tinggi tembereng bola harus lebih dari nol.");
		}
		luasPermukaan = 2 * radiusBaru * tinggiTemberengBolaBaru * PI + radiusBaru * radiusBaru * PI;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Tembereng Bola";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan tinggi tembereng bola? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari bola: ");
						String inputRadius = inputData.nextLine();
						double radiusBaru = Double.parseDouble(inputRadius);
						System.out.print("Masukkan tinggi tembereng bola: ");
						String inputTinggi = inputData.nextLine();
						double tinggiTemberengBaru = Double.parseDouble(inputTinggi);

						volume = menghitungVolume(radiusBaru, tinggiTemberengBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTemberengBaru);

						System.out.printf("\nVolume Tembereng Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Tembereng Bola: %.2f\n", luasPermukaan);
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
		try {
			System.out.println("\n=== Perhitungan Tembereng Bola dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 2) {
				if (i + 1 < 1000) {
					double radiusBaru = dataArray[i];
					double tinggiTemberengBaru = dataArray[i + 1];
					try {
						volume = menghitungVolume(radiusBaru, tinggiTemberengBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTemberengBaru);
						System.out.printf("Data %d-%d: radius=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 2, radiusBaru, tinggiTemberengBaru, volume, luasPermukaan);
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