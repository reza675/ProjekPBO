package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasJajaranGenjang extends JajaranGenjang {
	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;


	public LimasJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiLimas) {
		super(panjangAlas, tinggiAlas, sisiMiringAlas);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double panjangAlasBaru, double tinggiAlasBaru, double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		double alas = super.alas;
		double tinggiAlas = super.tinggi;
		double sisiMiring = super.sisiMiring;
		double tinggiSisiMiring = (alas * tinggiAlas) / sisiMiring;
		double jarakKeAlas = tinggiAlas / 2.0;
		double jarakKeSisiMiring = tinggiSisiMiring / 2.0;
		double slantHeightAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeAlas, 2));
		double slantHeightSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeSisiMiring, 2));
		double luasSegitigaAlas = 0.5 * alas * slantHeightAlas;
		double luasSegitigaSisiMiring = 0.5 * sisiMiring * slantHeightSisiMiring;

		luasPermukaan = luasAlas + 2 * luasSegitigaAlas + 2 * luasSegitigaSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double panjangAlasBaru, double tinggiAlasBaru, double sisiMiringAlasBaru,
			double tinggiLimasBaru) {
		double luasAlas = super.menghitungLuas(panjangAlasBaru, tinggiAlasBaru);
		double tinggiSisiMiring = (panjangAlasBaru * tinggiAlasBaru) / sisiMiringAlasBaru;
		double jarakKeAlas = tinggiAlasBaru / 2.0;
		double jarakKeSisiMiring = tinggiSisiMiring / 2.0;
		double slantHeightAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeAlas, 2));
		double slantHeightSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeSisiMiring, 2));
		double luasSegitigaAlas = 0.5 * panjangAlasBaru * slantHeightAlas;
		double luasSegitigaSisiMiring = 0.5 * sisiMiringAlasBaru * slantHeightSisiMiring;
		
		luasPermukaan = luasAlas + 2 * luasSegitigaAlas + 2 * luasSegitigaSisiMiring;
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
                    double panjangAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggi alas baru: ");
                    double tinggiAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan sisi miring alas baru: ");
                    double sisiMiringAlasBaru = inputData.nextDouble();
                    System.out.print("Masukkan tinggiPrisma baru: ");
                    double tinggiLimasBaru = inputData.nextDouble();
                    inputData.nextLine();

                    if (panjangAlasBaru <= 0 || tinggiAlasBaru <= 0 ||
                        sisiMiringAlasBaru <= 0 || tinggiLimasBaru <= 0) {
                        System.out.println("Semua nilai harus lebih dari nol.\n");
                        continue;
                    }
                    volume = menghitungVolume(panjangAlasBaru, tinggiAlasBaru, tinggiLimasBaru);
                    luasPermukaan = menghitungLuasPermukaan(panjangAlasBaru,tinggiAlasBaru,sisiMiringAlasBaru,tinggiLimasBaru);

                    System.out.printf("\nVolume Limas Jajaran Genjang: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Limas Jajaran Genjang: %.2f\n", luasPermukaan);
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
