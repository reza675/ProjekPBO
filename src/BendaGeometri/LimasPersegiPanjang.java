package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasPersegiPanjang extends PersegiPanjang {

	private double tinggiLimas;
	private double luasAlas;
	private double sisiMiringPanjang;
	private double sisiMiringLebar;
	private double luasSegitigaPanjang;
	private double luasSegitigaLebar;
	private double volume;
	private double luasPermukaan;

	public LimasPersegiPanjang(double panjang, double lebar, double tinggiLimas) {
		super(panjang, lebar);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double panjangBaru, double lebarBaru, double tinggiLimasBaru) {
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

	public double menghitungLuasPermukaan(double panjangBaru, double lebarBaru, double tinggiLimasBaru) {
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

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai panjang, lebar, dan tinggi Limas Persegi Panjang? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan panjang baru: ");
						double panjangBaru = inputData.nextDouble();
						System.out.print("Masukkan lebar baru: ");
						double lebarBaru = inputData.nextDouble();
						System.out.print("Masukkan tinggi limas baru: ");
						double tinggiBaru = inputData.nextDouble();
						inputData.nextLine();
	
						if (panjangBaru <= 0 || lebarBaru <= 0 || tinggiBaru <= 0) {
							System.out.println("Panjang,; lebar, dan tinggi harus lebih dari nol.\n");
							continue;
						}
	
						volume = menghitungVolume(panjangBaru, lebarBaru, tinggiBaru);
						luasPermukaan = menghitungLuasPermukaan(panjangBaru, lebarBaru, tinggiBaru);
	
						System.out.printf("\nVolume Limas Persegi Panjang: %.2f\n", volume);
						System.out.printf("Luas Permukaan Limas Persegi Panjang: %.2f\n", luasPermukaan);
						break;
					} catch (InputMismatchException e) {
						System.out.println("Input harus berupa angka.");
						inputData.nextLine();
					}
				}
				break;
			} else if (jawaban.equalsIgnoreCase("N")) {
				volume = menghitungVolume();
				luasPermukaan= menghitungLuasPermukaan();
				break;
			} else {
				System.out.println("Jawaban hanya boleh Y atau N.");
			}  
		}
	}
}