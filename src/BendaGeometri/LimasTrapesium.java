package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasTrapesium extends Trapesium implements Runnable {
	private double tinggiLimas;
	private double luasSegitigaAtas;
	private double luasSegitigaBawah;
	private double luasSegitigaKiri;
	private double luasSegitigaKanan;
	private double selisihBasis;
	private double tinggiSisiMiring;
	private double luasAlas;
	private double volume;
	private double luasPermukaan;

	public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri, double sisiKanan,
			double tinggiLimas) throws InputMismatchException {
		super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
		if (tinggiLimas <= 0) {
			throw new InputMismatchException("Tinggi limas harus lebih dari nol.");
		}
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double alasAtasBaru, double alasBawahBaru, double tinggiTrapesiumBaru,
			double tinggiLimasBaru) throws InputMismatchException {
		if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiTrapesiumBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		luasAlas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		luasSegitigaAtas = 0.5 * super.alasAtas * tinggiLimas;
		luasSegitigaBawah = 0.5 * super.alasBawah * tinggiLimas;
		selisihBasis = (super.alasBawah - super.alasAtas) / 2.0;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + selisihBasis * selisihBasis);
		luasSegitigaKiri = 0.5 * super.sisiMiringKiri * tinggiSisiMiring;
		luasSegitigaKanan = 0.5 * super.sisiMiringKanan * tinggiSisiMiring;
		luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double alasAtasBaru, double alasBawahBaru, double tinggiTrapesiumBaru,
			double tinggiLimasBaru) throws InputMismatchException {
		if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiTrapesiumBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Semua nilai harus lebih dari nol.");
		}
		luasAlas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru);
		luasSegitigaAtas = 0.5 * alasAtasBaru * tinggiLimasBaru;
		luasSegitigaBawah = 0.5 * alasBawahBaru * tinggiLimasBaru;
		selisihBasis = (alasBawahBaru - alasAtasBaru) / 2.0;
		tinggiSisiMiring = Math.sqrt(tinggiLimasBaru * tinggiLimasBaru + selisihBasis * selisihBasis);
		luasSegitigaKiri = 0.5 * sisiMiringKiri * tinggiSisiMiring;
		luasSegitigaKanan = 0.5 * sisiMiringKanan * tinggiSisiMiring;
		luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Trapesium";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nIngin mengubah alas atas, alas bawah, tinggi trapesium, dan tinggi limas? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan alas atas baru: ");
						String inputAlasAtas = inputData.nextLine();
						double alasAtasBaru = Double.parseDouble(inputAlasAtas);

						System.out.print("Masukkan alas bawah baru: ");
						String inputAlasBawah = inputData.nextLine();
						double alasBawahBaru = Double.parseDouble(inputAlasBawah);

						System.out.print("Masukkan tinggi trapesium baru: ");
						String inputTinggiTrapesium = inputData.nextLine();
						double tinggiTrapesiumBaru = Double.parseDouble(inputTinggiTrapesium);

						System.out.print("Masukkan tinggi limas baru: ");
						String inputTinggiLimas = inputData.nextLine();
						double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);

						volume = menghitungVolume(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru,
								tinggiLimasBaru);
						luasPermukaan = menghitungLuasPermukaan(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru,
								tinggiLimasBaru);

						System.out.printf("\nVolume Limas Trapesium: %.2f\n", volume);
						System.out.printf("Luas Permukaan Limas Trapesium: %.2f\n", luasPermukaan);
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
				System.out.println("Jawaban harus Y atau N.");
			}
		}
	}

	@Override
	public void run() {
		volume = menghitungVolume();
		luasPermukaan = menghitungLuasPermukaan();
		System.out.printf("\nVolume %s: %.2f\n", getNamaBenda(), volume);
		System.out.printf("Luas Permukaan %s: %.2f\n", getNamaBenda(), luasPermukaan);
	}
}