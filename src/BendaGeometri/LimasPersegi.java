package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasPersegi extends Persegi {

	private double tinggiLimas;
	private double luasAlas;
	private double setengahSisi;
	private double tinggiSegitiga;
	private double luasSegitiga;
	private double volume;
	private double luasPermukaan;

	public LimasPersegi() {
		super();
		this.tinggiLimas = 0;
	}

	public LimasPersegi(double sisi, double tinggiLimas) {
		super(sisi);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double sisiBaru, double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(sisiBaru);
		volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
		return volume;
	}


	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		setengahSisi = super.sisi / 2;
		tinggiSegitiga = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(setengahSisi, 2));
		luasSegitiga = 0.5 * super.sisi * tinggiSegitiga;
		luasPermukaan = luasAlas + 4 * luasSegitiga;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double sisiBaru, double tinggiLimasBaru) {
		luasAlas = super.menghitungLuas(sisiBaru);
		setengahSisi = sisiBaru / 2;
		tinggiSegitiga = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(setengahSisi, 2));
		luasSegitiga = 0.5 * sisiBaru * tinggiSegitiga;
		luasPermukaan = luasAlas + 4 * luasSegitiga;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Persegi";
	}

	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai sisi dan tinggi Limas Persegi? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan sisi persegi: ");
                        double sisiBaru = inputData.nextDouble();
                        System.out.print("Masukkan tinggi limas: ");
                        double tinggiLimasBaru = inputData.nextDouble();
                        inputData.nextLine();
                        if (sisiBaru <= 0 || tinggiLimasBaru <= 0) {
                            System.out.println("Sisi dan tinggi harus lebih dari nol.\n");
                            continue;
                        }
                        volume = menghitungVolume(sisiBaru,tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(sisiBaru,tinggiLimasBaru);
                        System.out.printf("\nVolume Limas Persegi: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Persegi: %.2f\n", luasPermukaan);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input sisi dan tinggi harus berupa angka.");
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