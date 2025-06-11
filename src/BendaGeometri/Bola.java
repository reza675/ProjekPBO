package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bola extends Lingkaran implements Runnable {

	protected double luasAlas;
	protected double volume;
	protected double luasPermukaan;
	// protected double jariJari;
	private volatile boolean calculated = false;

	public Bola(double jariJari) {
		super(jariJari);
	}

	@Override
	public void run() {
		// Calculate both area and perimeter in the thread
		luas = menghitungLuas();
		keliling = menghitungKeliling();
		calculated = true;
		System.out.println("Thread " + Thread.currentThread().getName() + " - " + getNamaBenda() + ":");
		System.out.printf("Luas Permukaan: %.2f\n", luas);
		System.out.printf("Volume: %.2f\n", keliling);
	}

	public boolean isCalculated() {
		return calculated;
	}
        
	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (4.0 / 3.0) * luasAlas* radius;
		return volume;
	}
	public double menghitungVolume(double radius) {
		luasAlas = super.menghitungLuas(radius);
		volume = (4.0 / 3.0) * luasAlas* radius;
		return volume;
	}
	public double menghitungVolume(int radius) {
		luasAlas = super.menghitungLuas(radius);
		volume = (4.0 / 3.0) * luasAlas* radius;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}
	public double menghitungLuasPermukaan(double radius) {
		luasAlas = super.menghitungLuas(radius);
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int radius) {
		luasAlas = super.menghitungLuas(radius);
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Bola";
	}

   

}