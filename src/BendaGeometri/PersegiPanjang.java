package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersegiPanjang extends Benda2D implements Runnable {
	protected double panjang;
	protected double lebar;
	private volatile boolean calculated = false;

    public PersegiPanjang(double panjang, double lebar) throws InputMismatchException {
        if (panjang <= 0 || lebar <= 0) {
            throw new InputMismatchException("Panjang dan lebar harus lebih dari nol.");
        }
        this.panjang = panjang;
        this.lebar = lebar;
    }

    @Override
    public double menghitungLuas() {
        luas = (panjang * lebar);
        return luas;
    }
	@Override
	public void run() {
		try {
			System.out.println("\n=== Perhitungan Persegi Panjang dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i += 2) {
				if (i + 1 < 1000) {
					double panjangBaru = dataArray[i];
					double lebarBaru = dataArray[i + 1];
					try {
						luas = menghitungLuas(panjangBaru, lebarBaru);
						keliling = menghitungKeliling(panjangBaru, lebarBaru);
						System.out.printf("Data %d-%d: panjang=%.1f, lebar=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, i + 2, panjangBaru, lebarBaru, luas, keliling);
					} catch (InputMismatchException e) {
						System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 2, e.getMessage());
					}
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}

	public boolean isCalculated() {
		return calculated;
	}

    public double menghitungLuas(double panjangBaru, double lebarBaru) throws InputMismatchException {
        if (panjangBaru <= 0 || lebarBaru <= 0) {
            throw new InputMismatchException("Panjang dan lebar harus lebih dari nol.");
        }
        luas = panjangBaru * lebarBaru;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = 2 * (panjang + lebar);
        return keliling;
    }

    public double menghitungKeliling(double panjangBaru, double lebarBaru) throws InputMismatchException {
        if (panjangBaru <= 0 || lebarBaru <= 0) {
            throw new InputMismatchException("Panjang dan lebar harus lebih dari nol.");
        }
        keliling = 2 * (panjangBaru + lebarBaru);
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Persegi Panjang";
    }
    
    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai panjang dan lebar persegi panjang? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang baru: ");
                        String inputPanjang = inputData.nextLine();
                        double panjangBaru = Double.parseDouble(inputPanjang);
                        
                        System.out.print("Masukkan lebar baru: ");
                        String inputLebar = inputData.nextLine().trim();
                        double lebarBaru = Double.parseDouble(inputLebar);
                        
                        luas = menghitungLuas(panjangBaru, lebarBaru);
                        keliling = menghitungKeliling(panjangBaru, lebarBaru);
                        System.out.printf("\nLuas Persegi Panjang: %.2f\n", luas);
                        System.out.printf("Keliling Persegi Panjang: %.2f\n", keliling);
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