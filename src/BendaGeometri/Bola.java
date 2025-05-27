package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Bola extends Lingkaran implements IBenda3D {

	/**
	 * Default constructor
	 */
	public Bola() {
            super();
	}

	/**
	 * 
	 */
	private double radius, volume, luas_permukaan;

	/**
	 * @param radius
	 */
	public Bola(double radius) {
		// TODO implement here
                super(radius);
	}

	/**
	 * @param radius
	 */
	public Bola(int radius) {
		// TODO implement here
                super(radius);
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		// TODO implement here
                this.volume =  (4.0/3.0) * super.getLuas() * radius;
		return volume;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungVolume(double radius) {
		// TODO implement here
		return 4.0/3.0 * super.menghitungLuas(radius) * radius;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungVolume(int radius) {
		// TODO implement here
		return 4.0/3.0 * super.menghitungLuas(radius) * radius;
	}

	
	@Override
	public double menghitungLuasPermukaan() {
		// TODO implement here
                this.luas_permukaan = 2 * super.getKeliling() * radius;
                return this.luas_permukaan;
	}

	
	public double menghitungLuasPermukaan(int radius) {
		// TODO implement here
		return 2 * super.menghitungKeliling(radius) * radius;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungLuasPermukaan(double radius) {
		// TODO implement here
		return 2 * super.menghitungKeliling(radius) * radius;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		// TODO implement here
                System.out.println("Volume Bola: " + this.volume);
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		// TODO implement here
		System.out.println("LP Bola: " + this.luas_permukaan);
	}
}