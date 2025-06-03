package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasSegitiga extends Segitiga {

	private double tinggiLimas;
	private double luasAlas;
	private double kelilingAlas;
	private double radiusDalam;
	private double tinggiSisiMiring;
	private double volume;
	private double luasPermukaan;


	public LimasSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2,
			double tinggiLimas) {
		super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double alasBaru, double tinggiSegitigaBaru, double tinggiLimasBaru) {
		luasAlas = menghitungLuas(alasBaru, tinggiSegitigaBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		kelilingAlas = super.menghitungKeliling();
		radiusDalam = (2 * luasAlas) / kelilingAlas;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + radiusDalam * radiusDalam);
		luasPermukaan = luasAlas + 0.5 * kelilingAlas * tinggiSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double alasBaru, double tinggiSegitigaBaru, double sisiMiring1Baru, double sisiMiring2Baru,
			double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(alasBaru, tinggiSegitigaBaru);
		kelilingAlas = super.menghitungKeliling(alasBaru, sisiMiring1Baru, sisiMiring2Baru);
		radiusDalam = (2 * luasAlas) / kelilingAlas;
		tinggiSisiMiring = Math.sqrt(tinggiLimasBaru * tinggiLimasBaru + radiusDalam * radiusDalam);
		luasPermukaan = luasAlas + 0.5 * kelilingAlas * tinggiSisiMiring;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Segitiga";
	}

	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai alas, tinggi segitiga, sisi miring 1, sisi miring 2, dan tinggi limas Limas Segitiga? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang alas segitiga: ");
                        double alasBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi segitiga: ");
                        double tinggiSegitigaBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring 1 segitiga: ");
                        double sisiMiring1Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi miring 2 segitiga: ");
                        double sisiMiring2Baru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi limas: ");
                        double tinggiLimasBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (alasBaru <= 0 || tinggiSegitigaBaru <= 0 || sisiMiring1Baru <= 0 || sisiMiring2Baru <= 0
                                || tinggiLimasBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru,
                                sisiMiring2Baru, tinggiLimasBaru);

                        System.out.printf("\nVolume Limas Segitiga: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Segitiga: %.2f\n", luasPermukaan);
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