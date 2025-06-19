package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Persegi extends Benda2D implements Runnable {
	public double sisi;

	public Persegi(double sisi) throws InputMismatchException  {
        if (sisi <= 0) {
            throw new InputMismatchException("Sisi harus lebih dari nol.");
        }
		this.sisi = sisi;
	}

	@Override
	public void run() {
		try {
			System.out.println("\n=== Perhitungan Persegi dengan 1000 Data ===");
			double[] dataArray = new double[1000];
			for (int i = 0; i < 1000; i++) {
				if (ThreadInterruptionUtil.checkAndHandleInterruption("data array initialization")) {
					return;
				}
				dataArray[i] = i + 1;
			}
			for (int i = 0; i < 1000; i++) {
				if (ThreadInterruptionUtil.checkInterruptionPeriodic(i, 50, "persegi calculations")) {
					return;
				}
				
				double sisiBaru = dataArray[i];
				try {
					luas = menghitungLuas(sisiBaru);
					keliling = menghitungKeliling(sisiBaru);
					System.out.printf("Data %d: sisi=%.1f | Luas=%.2f, Keliling=%.2f\n", i + 1, sisiBaru, luas, keliling);
				} catch (InputMismatchException e) {
					System.out.printf("Data %d: Error - %s\n", i + 1, e.getMessage());
				}
			}
			System.out.println("\nPerhitungan selesai untuk 1000 data!");
		} catch (Exception e) {
			System.out.println("Terjadi kesalahan: " + e.getMessage());
		}
	}

	
	@Override
	public double menghitungLuas() {
		luas = (sisi * sisi);
		return luas;
	}

	public double menghitungLuas(double sisiBaru) throws InputMismatchException {
        if (sisiBaru <= 0) {
            throw new InputMismatchException("Sisi harus lebih dari nol.");
        }
		luas = sisiBaru * sisiBaru;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = (4 * this.sisi);
		return keliling;
	}

	public double menghitungKeliling(double sisiBaru) throws InputMismatchException {
        if (sisiBaru <= 0) {
            throw new InputMismatchException("Sisi harus lebih dari nol.");
        }
		keliling = 4 * sisiBaru;
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Persegi";
	}
	public void prosesInputDataUlang()  {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai sisi persegi? (Y/N): ");
            String jawaban = inputData.nextLine();

            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan sisi baru: ");
                        String inputSisi = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisi);
                        luas = menghitungLuas(sisiBaru);
                        keliling = menghitungKeliling(sisiBaru);
                        System.out.printf("\nLuas Persegi: %.2f\n", luas);
                        System.out.printf("Keliling Persegi: %.2f\n", keliling);
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