package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bola extends Lingkaran{

	protected double luasAlas;
	protected double volume;
	protected double luasPermukaan;

	public Bola(double radius) throws InputMismatchException {
		super(radius);
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
						double radiusBaru = inputData.nextDouble();
						inputData.nextLine();

						if (radiusBaru <= 0) {
							System.out.println("Radius harus lebih dari nol.\n");
							continue;
						}

						volume = menghitungVolume(radiusBaru);
						luasPermukaan = menghitungLuasPermukaan(radiusBaru);

						System.out.printf("\nVolume Bola: %.2f\n", volume);
						System.out.printf("Luas Permukaan Bola: %.2f\n", luasPermukaan);
						break;
					} catch (InputMismatchException e) {
						System.out.println("Input harus berupa angka.");
						inputData.nextLine();
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