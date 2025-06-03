package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tabung extends Lingkaran {

	private double tinggiTabung;
	private double volume;
	private double luasAlas;
	private double selimut;
	private double luasPermukaan;

	public Tabung(double radius, double tinggiTabung) {
		super(radius);
		this.tinggiTabung = tinggiTabung;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = luasAlas * tinggiTabung;
		return volume;
	}

	public double menghitungVolume(double radiusBaru, double tinggiTabungBaru) {
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

	public double menghitungLuasPermukaan(double radiusBaru, double tinggiTabungBaru) {
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
						double radiusBaru = inputData.nextDouble();
						System.out.print("Masukkan tinggi tabung: ");
						double tinggiTabungBaru = inputData.nextDouble();
						inputData.nextLine();

						if (radiusBaru <= 0 || tinggiTabungBaru <= 0) {
							System.out.println("Semua nilai harus lebih dari nol.\n");
							continue;
						}

						volume = menghitungVolume(radiusBaru, tinggiTabungBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru, tinggiTabungBaru);

						System.out.printf("\nVolume Tabung: %.2f\n", volume);
						System.out.printf("Luas Permukaan Tabung: %.2f\n", luasPermukaan);
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