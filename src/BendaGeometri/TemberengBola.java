package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemberengBola extends Bola {

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

	public double menghitungVolume() {
		volume = ((tinggiTemberengBola * tinggiTemberengBola * PI) / 3) * (3 * radius - tinggiTemberengBola);
		return volume;
	}

	public double menghitungVolume(double radiusBolaBaru, double tinggiTemberengBolaBaru)
			throws InputMismatchException {
		if (radiusBolaBaru <= 0 || tinggiTemberengBolaBaru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
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
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
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
}