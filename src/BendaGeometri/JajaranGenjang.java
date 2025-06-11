package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JajaranGenjang extends Benda2D {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring;

	public JajaranGenjang(double alas, double tinggi, double sisiMiring) throws InputMismatchException {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new InputMismatchException("Nilai alas, tinggi, dan sisi miring harus lebih dari nol.");
        }
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring = sisiMiring;
	}

	@Override
	public double menghitungLuas() {
		luas = (alas * tinggi);
		return luas;
	}

	public double menghitungLuas(double alasBaru, double tinggiBaru) throws InputMismatchException {
        if (alasBaru <= 0 || tinggiBaru <= 0) {
            throw new InputMismatchException("Nilai alas dan tinggi harus lebih dari nol.");
        }
		luas = alasBaru * tinggiBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * (alas + sisiMiring);
		return keliling;
	}

	public double menghitungKeliling(double alasBaru, double sisiMiringBaru) throws InputMismatchException {
        if (alasBaru <= 0 || sisiMiringBaru <= 0) {
            throw new InputMismatchException("Nilai alas dan sisi miring harus lebih dari nol.");
        }
		keliling = 2 * (alasBaru + sisiMiringBaru);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Jajaran Genjang";
	}

	public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai alas, tinggi, dan sisi miring Jajaran Genjang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan alas baru: ");
                        String inputAlasBaru = inputData.nextLine();
                        double alasBaru = Double.parseDouble(inputAlasBaru);
                        System.out.print("Masukkan tinggi baru: ");
                        String inputTinggiBaru = inputData.nextLine();
                        double tinggiBaru = Double.parseDouble(inputTinggiBaru);
                        System.out.print("Masukkan sisi miring baru: ");
                        String inputSisiMiringBaru = inputData.nextLine();
                        double sisiMiringBaru = Double.parseDouble(inputSisiMiringBaru);

                        luas = menghitungLuas(alasBaru, tinggiBaru);
                        keliling = menghitungKeliling(alasBaru, sisiMiringBaru);
                        System.out.printf("\nLuas Jajaran Genjang: %.2f\n", luas);
                        System.out.printf("Keliling Jajaran Genjang: %.2f\n", keliling);
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
