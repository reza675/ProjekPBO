package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasLayangLayang extends LayangLayang {
	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang, double tinggiLimas) {
		super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}


	public double menghitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);

		volume = (1.0 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		double proyeksiKeSisiPendek = super.diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = super.diagonal1 / 2.0;
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
		double luasMukaPendek = sisiPendek * tinggiSegitigaPendek;
		double luasMukaPanjang = sisiPanjang * tinggiSegitigaPanjang;
		luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiPendekBaru, double sisiPanjangBaru, double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
		double proyeksiKeSisiPendek = diagonal2Baru / 2.0;
		double proyeksiKeSisiPanjang = diagonal1Baru / 2.0;
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
		double luasMukaPendek = sisiPendekBaru * tinggiSegitigaPendek;
		double luasMukaPanjang = sisiPanjangBaru * tinggiSegitigaPanjang;
		luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Layang-Layang";
	}

	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisi pendek, sisi panjang, dan tinggiLimas limas Layang-Layang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 layang-layang: ");
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 layang-layang: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi pendek layang-layang: ");
                        double sisiPendekBaru = inputData.nextDouble();
                        System.out.print("Masukkan sisi panjang layang-layang: ");
                        double sisiPanjangBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggiLimas limas: ");
                        double tinggiLimasBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiLimasBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }

                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiLimasBaru);

                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru,tinggiLimasBaru);
                        System.out.printf("\nVolume Limas Layang-Layang: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Layang-Layang: %.2f\n", luasPermukaan);
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