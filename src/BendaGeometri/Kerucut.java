package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kerucut extends Lingkaran implements Runnable {

	protected double tinggiKerucut;
	protected double luasAlas;
	protected double sisiMiring;
	protected double luasSelimut;
	protected double volume;
	protected double luasPermukaan;
	private volatile boolean calculated = false;
	private final Object lock = new Object();

	public Kerucut(double radius, double tinggiKerucut) throws InputMismatchException {
		super(radius);
		if (tinggiKerucut <= 0) {
			throw new InputMismatchException("Tinggi kerucut harus lebih dari nol.");
		}
		this.tinggiKerucut = tinggiKerucut;
		this.sisiMiring = Math.sqrt(radius * radius + tinggiKerucut * tinggiKerucut);
	}

	@Override
	public void run() {
		synchronized (lock) {
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
		synchronized (lock) {
			while (!calculated) {
				System.out.println("Thread " + Thread.currentThread().getName() + " waiting for " + getNamaBenda()
						+ " calculations...");
				lock.wait();
			}
			System.out.println(
					"Thread " + Thread.currentThread().getName() + " received " + getNamaBenda() + " results:");
			System.out.printf("Volume: %.2f\n", volume);
			System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
		}
	}

	public boolean isCalculated() {
		synchronized (lock) {
			return calculated;
		}
	}

	public double getVolume() {
		synchronized (lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return volume;
		}
	}

	public double getLuasPermukaan() {
		synchronized (lock) {
			if (!calculated) {
				throw new IllegalStateException("Calculations not yet complete");
			}
			return luasPermukaan;
		}
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggiKerucut;
		return volume;
	}

	public double menghitungVolume(double radiusBaru, double tinggiKerucutBaru) throws InputMismatchException {
		if (radiusBaru <= 0 || tinggiKerucutBaru <= 0) {
			throw new InputMismatchException("Radius baru dan tinggi kerucut harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(radiusBaru);
		volume = (1.0 / 3.0) * luasAlas * tinggiKerucutBaru;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		sisiMiring = Math.sqrt(tinggiKerucut * tinggiKerucut + super.radius * super.radius);
		luasSelimut = (super.menghitungKeliling() / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double tinggiKerucutBaru, double radiusBaru) throws InputMismatchException {
		if (radiusBaru <= 0 || tinggiKerucutBaru <= 0) {
			throw new InputMismatchException("Radius baru dan tinggi kerucut harus lebih dari nol.");
		}
		luasAlas = super.menghitungLuas(radiusBaru);
		sisiMiring = Math.sqrt(tinggiKerucutBaru * tinggiKerucutBaru + radiusBaru * radiusBaru);
		luasSelimut = (super.menghitungKeliling(radiusBaru) / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Kerucut";
	}

	public void prosesInputDataUlang() {
		Scanner inputData = new Scanner(System.in);
		while (true) {
			System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan tinggi kerucut? (Y/N): ");
			String jawaban = inputData.nextLine();
			if (jawaban.equalsIgnoreCase("Y")) {
				while (true) {
					try {
						System.out.print("Masukkan jari-jari kerucut: ");
						String inputRadius = inputData.nextLine();
						double radiusBaru = Double.parseDouble(inputRadius);
						System.out.print("Masukkan tinggi kerucut: ");
						String inputTinggiKerucut = inputData.nextLine();
						double tinggiKerucutBaru = Double.parseDouble(inputTinggiKerucut);

						volume = menghitungVolume(radiusBaru, tinggiKerucutBaru);
						luasPermukaan = menghitungLuasPermukaan(tinggiKerucutBaru, radiusBaru);

						System.out.printf("\nVolume Kerucut: %.2f\n", volume);
						System.out.printf("Luas Permukaan Kerucut: %.2f\n", luasPermukaan);
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
