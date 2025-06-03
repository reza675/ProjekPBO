package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kerucut extends Lingkaran {

	protected double tinggiKerucut;
	protected double luasAlas;
	protected double sisiMiring;
	protected double luasSelimut;
	protected double volume;
	protected double luasPermukaan;

	public Kerucut(double radius, double tinggiKerucut) {
		super(radius);
		this.tinggiKerucut = tinggiKerucut;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggiKerucut;
		return volume;
	}

	public double menghitungVolume(double radiusBaru, double tinggiKerucutBaru) {
		luasAlas = super.menghitungLuas(radiusBaru);
		volume = (1.0 / 3.0) * luasAlas * tinggiKerucutBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		sisiMiring = Math.sqrt(tinggiKerucut * tinggiKerucut + super.radius * super.radius);
		luasSelimut = (super.menghitungKeliling() / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double tinggiKerucutBaru, double radiusBaru) {
		luasAlas = super.menghitungLuas(radiusBaru);
		sisiMiring = Math.sqrt(tinggiKerucutBaru * tinggiKerucutBaru + radiusBaru * radiusBaru);
		luasSelimut = (super.menghitungKeliling(radiusBaru) / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Kerucut";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan tinggi kerucut? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari kerucut: ");
						double radiusBaru = inputData.nextDouble();
						System.out.print("Masukkan tinggi kerucut: ");
						double tinggiKerucutBaru = inputData.nextDouble();
						inputData.nextLine();

						if (radiusBaru <= 0 || tinggiKerucutBaru <= 0) {
							System.out.println("Semua nilai harus lebih dari nol.\n");
							continue;
						}

						volume = menghitungVolume(radiusBaru, tinggiKerucutBaru);
						luasPermukaan = menghitungLuasPermukaan(tinggiKerucutBaru, radiusBaru);

						System.out.printf("\nVolume Kerucut: %.2f\n", volume);
						System.out.printf("Luas Permukaan Kerucut: %.2f\n", luasPermukaan);
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
