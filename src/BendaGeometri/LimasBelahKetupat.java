package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasBelahKetupat extends BelahKetupat implements Runnable {

	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasBelahKetupat(double diagonal1, double diagonal2, double sisi, double tinggiLimas) throws InputMismatchException {
		super(diagonal1, diagonal2, sisi);
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
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
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

	public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double tinggiLimasBaru, double sisiBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || tinggiLimasBaru <= 0 || sisiBaru <= 0) {
            throw new InputMismatchException("Semua nilai harus lebih dari nol.");
        }
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
                        String inputDiagonal1 = inputData.nextLine();
                        double diagonal1Baru = Double.parseDouble(inputDiagonal1);
                        System.out.print("Masukkan diagonal2 baru: ");
                        String inputDiagonal2 = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2);
                        System.out.print("Masukkan sisi baru: ");
                        String inputSisi = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisi);
                        System.out.print("Masukkan tinggi limas baru: ");
                        String inputTinggiLimas = inputData.nextLine();
                        double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);
                        
                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, tinggiLimasBaru, sisiBaru);

                        System.out.printf("\nVolume Limas Belah Ketupat: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Belah Ketupat: %.2f\n", luasPermukaan);
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
		volume = menghitungVolume();
		luasPermukaan = menghitungLuasPermukaan();
		System.out.printf("\nVolume %s: %.2f\n", getNamaBenda(), volume);
		System.out.printf("Luas Permukaan %s: %.2f\n", getNamaBenda(), luasPermukaan);
	}

}