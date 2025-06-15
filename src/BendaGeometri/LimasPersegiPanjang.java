package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasPersegiPanjang extends PersegiPanjang implements Runnable {

	private double tinggiLimas;
	private double luasAlas;
	private double sisiMiringPanjang;
	private double sisiMiringLebar;
	private double luasSegitigaPanjang;
	private double luasSegitigaLebar;
	private double volume;
	private double luasPermukaan;

	public LimasPersegiPanjang(double panjang, double lebar, double tinggiLimas) throws InputMismatchException {
		super(panjang, lebar);
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

	public double menghitungVolume(double panjangBaru, double lebarBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Panjang, lebar, dan tinggi limas harus lebih dari nol.");
		}
		luasAlas = menghitungLuas(panjangBaru, lebarBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = menghitungLuas();
		sisiMiringPanjang = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.lebar / 2, 2));
		sisiMiringLebar = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.panjang / 2, 2));
		luasSegitigaPanjang = 0.5 * super.panjang * sisiMiringPanjang;
		luasSegitigaLebar = 0.5 * super.lebar * sisiMiringLebar;
		luasPermukaan = luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double panjangBaru, double lebarBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Panjang, lebar, dan tinggi limas harus lebih dari nol.");
		}
		luasAlas = menghitungLuas(panjangBaru, lebarBaru);
		sisiMiringPanjang = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(lebarBaru / 2, 2));
		sisiMiringLebar = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(panjangBaru / 2, 2));
		luasSegitigaPanjang = 0.5 * panjangBaru * sisiMiringPanjang;
		luasSegitigaLebar = 0.5 * lebarBaru * sisiMiringLebar;
		luasPermukaan = luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Persegi Panjang";
	}

	@Override
	public void run() {
		volume = menghitungVolume();
		luasPermukaan = menghitungLuasPermukaan();
		System.out.printf("\nVolume %s: %.2f\n", getNamaBenda(), volume);
		System.out.printf("Luas Permukaan %s: %.2f\n", getNamaBenda(), luasPermukaan);
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai panjang, lebar, dan tinggi Limas Persegi Panjang? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan panjang baru: ");
						String inputPanjang = inputData.nextLine();
						double panjangBaru = Double.parseDouble(inputPanjang);
						System.out.print("Masukkan lebar baru: ");
						String inputLebar = inputData.nextLine();
						double lebarBaru = Double.parseDouble(inputLebar);
						System.out.print("Masukkan tinggi limas baru: ");
						String inputTinggi = inputData.nextLine();
						double tinggiBaru = Double.parseDouble(inputTinggi);
						
	
						volume = menghitungVolume(panjangBaru, lebarBaru, tinggiBaru);
						luasPermukaan = menghitungLuasPermukaan(panjangBaru, lebarBaru, tinggiBaru);
	
						System.out.printf("\nVolume Limas Persegi Panjang: %.2f\n", volume);
						System.out.printf("Luas Permukaan Limas Persegi Panjang: %.2f\n", luasPermukaan);
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