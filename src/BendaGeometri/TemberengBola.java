package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemberengBola extends Bola {

	private double tinggiTemberengBola;
	private double volume;
	private double luasPermukaan;

	public TemberengBola(double radius, double tinggiTemberengBola) {
		super(radius);
		this.tinggiTemberengBola = tinggiTemberengBola;
	}

	public double menghitungVolume() {
		volume = ((tinggiTemberengBola * tinggiTemberengBola * PI) / 3) * (3 * radius - tinggiTemberengBola);
		return volume;
	}

	public double menghitungVolume(double radiusBolaBaru, double tinggiTemberengBolaBaru) {
		volume = ((tinggiTemberengBolaBaru * tinggiTemberengBolaBaru * PI) / 3)
				* (3 * radiusBolaBaru - tinggiTemberengBolaBaru);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasPermukaan = 2 * radius * tinggiTemberengBola * PI + radius * radius * PI;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiTemberengBolaBaru) {
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
						double radiusBaru = inputData.nextDouble();
						System.out.print("Masukkan tinggi tembereng bola: ");
						double tinggiTemberengBaru = inputData.nextDouble();
						inputData.nextLine();

						if (radiusBaru <= 0 || tinggiTemberengBaru <= 0 || tinggiTemberengBaru > radiusBaru * 2) {
							System.out.println(
									"Nilai tidak valid. Jari-jari dan tinggi harus lebih dari 0, dan tinggi maksimal 2 kali jari-jari.\n");
							continue;
						}

						volume = menghitungVolume(radiusBaru, tinggiTemberengBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTemberengBaru);

						System.out.printf("\nVolume Tembereng Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Tembereng Bola: %.2f\n", luasPermukaan);
						break;
					} catch (InputMismatchException e) {
						System.out.println("Input harus berupa angka.");
						inputData.nextLine(); // clear buffer
					}
				}
				break;
			} else if (jawaban.equalsIgnoreCase("N")) {
				volume = menghitungVolume();
				luasPermukaan = menghitungLuasPermukaan();
				break;
			} else {
				System.out.println("Jawaban hanya boleh Y atau N.");
			}
		}

	}
}