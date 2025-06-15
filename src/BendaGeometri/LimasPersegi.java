package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasPersegi extends Persegi implements Runnable {

	private double tinggiLimas;
	private double luasAlas;
	private double setengahSisi;
	private double tinggiSegitiga;
	private double luasSegitiga;
	private double volume;
	private double luasPermukaan;
	private volatile boolean calculated = false;
	private final Object lock = new Object();

	public LimasPersegi(double sisi, double tinggiLimas) throws InputMismatchException {
		super(sisi);
		if (tinggiLimas <= 0) {
			throw new InputMismatchException("Tinggi limas harus lebih dari nol.");
		}
		this.tinggiLimas = tinggiLimas;
	}

	@Override
	public void run() {
		synchronized(lock) {
			// Calculate both volume and surface area in the thread
			volume = menghitungVolume();
			luasPermukaan = menghitungLuasPermukaan();
			calculated = true;
			System.out.println("Thread " + Thread.currentThread().getName() + " - " + getNamaBenda() + ":");
			System.out.printf("Volume: %.2f\n", volume);
			System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
			lock.notifyAll(); // Notify waiting threads that calculation is complete
		}
	}

	public void waitForCalculation() throws InterruptedException {
		synchronized(lock) {
			while (!calculated) {
				System.out.println("Thread " + Thread.currentThread().getName() + " waiting for " + getNamaBenda() + " calculations...");
				lock.wait();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " received " + getNamaBenda() + " results:");
			System.out.printf("Volume: %.2f\n", volume);
			System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
		}
	}

	public boolean isCalculated() {
		synchronized(lock) {
			return calculated;
		}
	}

	public double getVolume() {
		synchronized(lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return volume;
		}
	}

	public double getLuasPermukaan() {
		synchronized(lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return luasPermukaan;
		}
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double sisiBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (sisiBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Sisi dan tinggi limas harus lebih dari nol.");
		}
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

	public double menghitungLuasPermukaan(double sisiBaru, double tinggiLimasBaru) throws InputMismatchException {
		if (sisiBaru <= 0 || tinggiLimasBaru <= 0) {
			throw new InputMismatchException("Sisi dan tinggi limas harus lebih dari nol.");
		}
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
						String inputSisi = inputData.nextLine();
                        double sisiBaru = Double.parseDouble(inputSisi);
                        System.out.print("Masukkan tinggi limas: ");
						String inputTinggiLimas = inputData.nextLine();
                        double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);
                     
                        volume = menghitungVolume(sisiBaru,tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(sisiBaru,tinggiLimasBaru);
                        System.out.printf("\nVolume Limas Persegi: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Persegi: %.2f\n", luasPermukaan);
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