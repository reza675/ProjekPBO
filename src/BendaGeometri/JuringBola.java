package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class JuringBola extends Bola implements IBenda3D {

	/**
	 * Default constructor
	 */
	public JuringBola() {
            super();
	}

	/**
	 * 
	 */
	private double sudut, volume, luas_permukaan;

	/**
	 * @param radius 
	 * @param sudut "dalam radian"
	 */
	public JuringBola(double radius, double sudut) {
		this.sudut = sudut;
                super(radius);
	}

	/**
	 * @param radius 
	 * @param sudut
	 */
	public JuringBola(int radius, int sudut) {
		this.sudut = sudut;
                super(radius);
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		// TODO implement here
                this.volume = super.menghitungVolume() * (sudut / (2*Math.PI));
		return this.volume;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungVolume(double sudut) {
		return super.menghitungVolume() * (sudut / (2*Math.PI));
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungVolume(double sudut, double radius) {
		// TODO implement here
		return super.menghitungVolume(radius) * (sudut / (2*Math.PI));
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		// TODO implement here
		this.luas_permukaan = super.menghitungLuasPermukaan() * (sudut / (2*Math.PI));
                return this.luas_permukaan;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungLuasPermukaan(double sudut) {
		return super.menghitungLuasPermukaan() * (sudut / (2*Math.PI));
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungLuasPermukaan(double sudut, double radius) {
		// TODO implement here
		return super.menghitungLuasPermukaan(radius) * (sudut / (2*Math.PI));
	}

	@Override
	public void mencetakVolume() {
		// TODO implement here
                System.out.println("Volume Juring Bola: " + this.volume);
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		// TODO implement here
		System.out.println("LP Juring Bola: " + this.luas_permukaan);
	}

}