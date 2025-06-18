package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasLayangLayang extends LayangLayang implements Runnable {
	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang, double tinggiLimas) throws InputMismatchException {
		super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
		if (tinggiLimas <= 0) {
			throw new InputMismatchException("Tinggi limas harus lebih dari nol.");
		}
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}


	public double menghitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiLimasBaru) throws InputMismatchException {
		if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Diagonal1, diagonal2, dan tinggi limas harus lebih dari nol.");
		}
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

	public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiPendekBaru, double sisiPanjangBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Diagonal1, diagonal2, sisi pendek, sisi panjang, dan tinggi limas harus lebih dari nol.");
		}
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
						String inputDiagonal1 = inputData.nextLine();
						double diagonal1Baru = Double.parseDouble(inputDiagonal1);
                        System.out.print("Masukkan diagonal2 layang-layang: ");
						String inputDiagonal2 = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2);
                        System.out.print("Masukkan sisi pendek layang-layang: ");
						String inputSisiPendek = inputData.nextLine();
                        double sisiPendekBaru = Double.parseDouble(inputSisiPendek);
                        System.out.print("Masukkan sisi panjang layang-layang: ");
						String inputSisiPanjang = inputData.nextLine();
                        double sisiPanjangBaru = Double.parseDouble(inputSisiPanjang);
                        System.out.print("Masukkan tinggi limas limas: ");
						String inputTinggiLimas = inputData.nextLine();
                        double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);

                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru,tinggiLimasBaru);
                        System.out.printf("\nVolume Limas Layang-Layang: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Layang-Layang: %.2f\n", luasPermukaan);
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
                System.out.println("\n=== Perhitungan Limas Layang-Layang dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 5) {
                    if (i + 4 < 1000) {
                        double diagonal1Baru = dataArray[i];
                        double diagonal2Baru = dataArray[i + 1];
                        double sisiPendekBaru = dataArray[i + 2];
                        double sisiPanjangBaru = dataArray[i + 3];
                        double tinggiLimasBaru = dataArray[i + 4];
                        try {
                            volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiLimasBaru);
                            luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru, tinggiLimasBaru);
                            System.out.printf("Data %d-%d: diagonal1=%.1f, diagonal2=%.1f, sisiPendek=%.1f, sisiPanjang=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 5, diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru, tinggiLimasBaru, volume, luasPermukaan);
                        } catch (InputMismatchException e) {
                            System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 5, e.getMessage());
                        }
                    }
                }
                System.out.println("\nPerhitungan selesai untuk 1000 data!");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
	

	
}