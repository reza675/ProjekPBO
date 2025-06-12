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
			throw new InputMismatchException("Tinggi cincin bola harus lebih dari nol dan berupa angka.");
		}
		this.tinggiCincinBola = tinggiCincinBola;

	}

	public double menghitungVolume() {
		double radiusBola = super.radius;
		volume = PI * tinggiCincinBola * tinggiCincinBola * (radiusBola - tinggiCincinBola / 3.0);
		return volume;
	}

	public double menghitungVolume(double radiusBola, double tinggiCincinBolaBaru) throws InputMismatchException {
		if (radiusBola <= 0 || tinggiCincinBolaBaru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
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
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		luasPermukaan = 2 * PI * radiusBaru * tinggiCincinBolaBaru;
		return luasPermukaan;
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
		Scanner inputData = new Scanner(System.in);
		try {
			System.out.println("\nMenghitung Cincin Bola...");
			System.out.printf("Volume Cincin Bola awal: %.2f\n", menghitungVolume());
			System.out.printf("Luas Permukaan Cincin Bola awal: %.2f\n", menghitungLuasPermukaan());
			
			prosesInputDataUlang();
			
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		} finally {
			inputData.close();
		}
	}
}