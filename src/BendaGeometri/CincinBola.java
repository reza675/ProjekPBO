package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CincinBola extends Bola {
	private double tinggiCincinBola;
	private double volume;
	private double luasPermukaan;

	public CincinBola(double radius, double tinggiCincinBola) {
		super(radius);
		this.tinggiCincinBola = tinggiCincinBola;

	}

	public double menghitungVolume() {
		double radiusBola = super.radius;
		volume = PI * tinggiCincinBola * tinggiCincinBola * (radiusBola - tinggiCincinBola / 3.0);
		return volume;
	}

	public double menghitungVolume(double radiusBola, double tinggiCincinBolaBaru) {
		volume = PI * tinggiCincinBolaBaru * tinggiCincinBolaBaru * (radiusBola - tinggiCincinBolaBaru / 3.0);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		double radiusBola = super.radius;
		luasPermukaan = 2 * PI * radiusBola * tinggiCincinBola;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiCincinBolaBaru) {
		return 2 * PI * radiusBaru * tinggiCincinBolaBaru;
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
						double radiusBaru = inputData.nextDouble();
						System.out.print("Masukkan tinggi cincin bola: ");
						double tinggiBaru = inputData.nextDouble();
						inputData.nextLine();

						if (radiusBaru <= 0 || tinggiBaru <= 0) {
							System.out.println("Semua nilai harus lebih dari nol.\n");
							continue;
						}

						volume = menghitungVolume(radiusBaru, tinggiBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiBaru);

						System.out.printf("\nVolume Cincin Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Cincin Bola: %.2f\n", luasPermukaan);
						break;
					} catch (InputMismatchException e) {
						System.out.println("Input harus berupa angka.");
						inputData.nextLine();
					}
				}
				break;
			} else if (jawaban.equalsIgnoreCase("N")) {
				volume = menghitungVolume();
				luasPermukaan = menghitungLuasPermukaan();
				break;
			} else {
				System.out.println("Jawaban hanya boleh Y atau N.\n");
			}
		}
	}
}