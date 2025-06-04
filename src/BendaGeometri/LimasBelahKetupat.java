package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasBelahKetupat extends BelahKetupat {

	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasBelahKetupat(double diagonal1, double diagonal2, double sisi, double tinggiLimas) {
		super(diagonal1, diagonal2, sisi);
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
        double setengahDiagonal1 = super.diagonal1 / 2.0;
        double setengahDiagonal2 = super.diagonal2 / 2.0;
        double tinggiSegitiga = Math.sqrt(tinggiLimas * tinggiLimas + setengahDiagonal1 * setengahDiagonal2);
        double luasSegitiga = 0.5 * super.sisi * tinggiSegitiga;
        luasPermukaan =  luasAlas + 4 * luasSegitiga;
        return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double tinggiLimasBaru, double sisiBaru) {
		luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        double setengahDiagonal1 = diagonal1Baru / 2.0;
        double setengahDiagonal2 = diagonal2Baru / 2.0;
        double tinggiSegitiga = Math.sqrt(tinggiLimasBaru * tinggiLimasBaru + setengahDiagonal1 * setengahDiagonal2);
        double luasSegitiga = 0.5 * sisiBaru * tinggiSegitiga;
        luasPermukaan =  luasAlas + 4 * luasSegitiga;
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
                "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisi, dan tinggi limas pada Limas Belah Ketupat? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 baru: ");
                        double diagonal1Baru = inputData.nextDouble();
                        System.out.print("Masukkan diagonal2 baru: ");
                        double diagonal2Baru = inputData.nextDouble();
                        System.out.print("Masukkan sisi baru: ");
                        double sisiBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi limas baru: ");
                        double tinggiLimasBaru = inputData.nextDouble();
                        inputData.nextLine();

                        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiBaru <= 0 || tinggiLimasBaru <= 0) {
                            System.out.println("Semua nilai harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, tinggiLimasBaru, sisiBaru);

                        System.out.printf("\nVolume Limas Belah Ketupat: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Belah Ketupat: %.2f\n", luasPermukaan);
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
                System.out.println("Jawaban hanya boleh Y atau N.");
            }
        }
    }

}