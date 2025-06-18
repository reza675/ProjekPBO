package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JajaranGenjang extends Benda2D implements Runnable {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring;

	public JajaranGenjang(double alas, double tinggi, double sisiMiring) throws InputMismatchException {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new InputMismatchException("Nilai alas, tinggi, dan sisi miring harus lebih dari nol dan berupa angka.");
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

	@Override
	public void run() {
		try {
			System.out.println("\n=== Perhitungan Jajaran Genjang dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 3) {
				if (i + 2 < 1000) {
					double alasBaru = dataArray[i];
					double tinggiBaru = dataArray[i + 1];
					double sisiMiringBaru = dataArray[i + 2];
					try {
						luas = menghitungLuas(alasBaru, tinggiBaru);
						keliling = menghitungKeliling(alasBaru, sisiMiringBaru);
						System.out.printf("Data %d-%d: alas=%.1f, tinggi=%.1f, sisiMiring=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 3, alasBaru, tinggiBaru, sisiMiringBaru, luas, keliling);
					} catch (InputMismatchException e) {
						System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 3, e.getMessage());
					}
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}
}
