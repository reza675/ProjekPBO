package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasJajaranGenjang extends JajaranGenjang implements Runnable {
	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiLimas) throws InputMismatchException {
		super(panjangAlas, tinggiAlas, sisiMiringAlas);
		if (tinggiLimas <= 0) {
			throw new InputMismatchException("Tinggi limas harus lebih dari nol.");
		}
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double panjangAlasBaru, double tinggiAlasBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Panjang alas, tinggi alas, dan tinggi limas harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		double panjangAlas = super.alas;
		double tinggiAlas = super.tinggi;
		double panjangSisiMiring = super.sisiMiring;
		double jarakTengahKeTepiAlas = tinggiAlas / 2.0;
		double jarakTengahKeTepiSisiMiring = panjangAlas / 2.0;
		double tinggiMiringAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakTengahKeTepiAlas, 2));
		double tinggiMiringSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakTengahKeTepiSisiMiring, 2));
		double luasSisiAlas = 0.5 * panjangSisiMiring * tinggiMiringAlas;
		double luasSisiSisiMiring = 0.5 * panjangAlas * tinggiMiringSisiMiring;

		luasPermukaan = luasAlas + 2 * luasSisiAlas + 2 * luasSisiSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double panjangAlasBaru, double tinggiAlasBaru, double sisiMiringAlasBaru,
			double tinggiLimasBaru) throws InputMismatchException {
		if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 || sisiMiringAlasBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Panjang alas, tinggi alas, sisi miring alas, dan tinggi limas harus lebih dari nol.");
		}
		double luasAlasBaru = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
        double jarakTengahKeTepiAlas = tinggiAlasBaru / 2.0;
        double jarakTengahKeTepiSisiMiring = panjangAlasBaru / 2.0;
        double tinggiMiringAlasBaru = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(jarakTengahKeTepiAlas, 2));
        double tinggiMiringSisiMiringBaru = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(jarakTengahKeTepiSisiMiring, 2));
        double luasSisiAlasBaru = 0.5 * sisiMiringAlasBaru * tinggiMiringAlasBaru;
        double luasSisiSisiMiringBaru = 0.5 * panjangAlasBaru * tinggiMiringSisiMiringBaru;

        luasPermukaan = luasAlasBaru + 2 * luasSisiAlasBaru + 2 * luasSisiSisiMiringBaru;

        return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Jajaran Genjang";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print(
					"\nApakah Anda ingin mengubah nilai panjang alas, tinggi alas, sisi miring alas, dan tinggiLimas pada Limas Jajaran Genjang? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan panjang alas baru: ");
						String inputPanjangAlas = inputData.nextLine();
						double panjangAlasBaru = Double.parseDouble(inputPanjangAlas);
						System.out.print("Masukkan tinggi alas baru: ");
						String inputTinggiAlas = inputData.nextLine();
						double tinggiAlasBaru = Double.parseDouble(inputTinggiAlas);
						System.out.print("Masukkan sisi miring alas baru: ");
						String inputSisiMiringAlas = inputData.nextLine();
						double sisiMiringAlasBaru = Double.parseDouble(inputSisiMiringAlas);
						System.out.print("Masukkan tinggi limas baru: ");
						String inputTinggiLimas = inputData.nextLine();
						double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);
						
						volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiLimasBaru);
						luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru, tinggiAlasBaru, sisiMiringAlasBaru,
								tinggiLimasBaru);

						System.out.printf("\nVolume Limas Jajaran Genjang: %.2f\n", volume);
						System.out.printf("Luas Permukaan Limas Jajaran Genjang: %.2f\n", luasPermukaan);
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
			System.out.println("\n=== Perhitungan Limas Jajaran Genjang dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 4) {
				if (i + 3 < 1000) {
					double panjangAlasBaru = dataArray[i];
					double tinggiAlasBaru = dataArray[i + 1];
					double sisiMiringAlasBaru = dataArray[i + 2];
					double tinggiLimasBaru = dataArray[i + 3];
					try {
						volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiLimasBaru);
						luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru, tinggiAlasBaru, sisiMiringAlasBaru, tinggiLimasBaru);
						System.out.printf("Data %d-%d: panjangAlas=%.1f, tinggiAlas=%.1f, sisiMiringAlas=%.1f, tinggiLimas=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 4, panjangAlasBaru, tinggiAlasBaru, sisiMiringAlasBaru, tinggiLimasBaru, volume, luasPermukaan);
					} catch (InputMismatchException e) {
						System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 4, e.getMessage());
					}
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}
}
