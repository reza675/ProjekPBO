package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CincinBola extends Bola implements Runnable {
	private double tinggiCincinBola;
	private double volume;
	private double luasPermukaan;

	public CincinBola(double radius, double tinggiCincinBola) throws InputMismatchException {
		super(radius);
		if (tinggiCincinBola <= 0) {
			throw new InputMismatchException("Tinggi cincin bola harus lebih dari nol");
		}
		this.tinggiCincinBola = tinggiCincinBola;

	}

	public double menghitungVolumeCincin() {
		double radiusBola = super.radius;
		volume = PI * tinggiCincinBola * tinggiCincinBola * (radiusBola - tinggiCincinBola / 3.0);
		return volume;
	}

	public double menghitungVolume(double radiusBola, double tinggiCincinBolaBaru) throws InputMismatchException {
		if (radiusBola <= 0 || tinggiCincinBolaBaru <= 0) {
			throw new InputMismatchException("Radius dan tinggi cincin bola harus lebih dari nol.");
		}
		volume = PI * tinggiCincinBolaBaru * tinggiCincinBolaBaru * (radiusBola - tinggiCincinBolaBaru / 3.0);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		double radiusBola = super.radius;
		luasPermukaan = 2 * PI * radiusBola * tinggiCincinBola;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiCincinBolaBaru)
			throws InputMismatchException {
		if (radiusBaru <= 0 || tinggiCincinBolaBaru <= 0) {
			throw new InputMismatchException("Radius dan tinggi cincin bola harus lebih dari nol.");
		}
		luasPermukaan = 2 * PI * radiusBaru * tinggiCincinBolaBaru;
		return luasPermukaan;
	}

	public void setTinggiCincin(double tinggiCincinBola) {
		this.tinggiCincinBola = tinggiCincinBola;
	}

	public double getTinggiCincin() {
		return tinggiCincinBola;
	}


	@Override
	public String getNamaBenda() {
		return "Cincin Bola";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari bola dan tinggi cincin bola? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari bola: ");
						String inputRadius = inputData.nextLine();
						double radiusBaru = Double.parseDouble(inputRadius);
						System.out.print("Masukkan tinggi cincin bola: ");
						String inputTinggi = inputData.nextLine();
						double tinggiBaru = Double.parseDouble(inputTinggi);

						volume = menghitungVolume(radiusBaru, tinggiBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiBaru);

						System.out.printf("\nVolume Cincin Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Cincin Bola: %.2f\n", luasPermukaan);
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
			System.out.println("\n=== Perhitungan Cincin Bola dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 2) {
				if (i + 1 < 1000) {
					double radiusBaru = dataArray[i];
					double tinggiCincinBolaBaru = dataArray[i + 1];
					try {
						volume = menghitungVolume(radiusBaru, tinggiCincinBolaBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiCincinBolaBaru);
						System.out.printf("Data %d-%d: radius=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n",
								i + 1, i + 2, radiusBaru, tinggiCincinBolaBaru, volume, luasPermukaan);
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