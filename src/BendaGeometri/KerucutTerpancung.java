package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class KerucutTerpancung extends Kerucut implements IBenda3D {

	/**
	 * Default constructor
	 */
	public KerucutTerpancung() {
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * 
	 */
	private Lingkaran alas;

	/**
	 * 
	 */
	private Kerucut pancungan;

	/**
	 * @param alas 
	 * @param tinggi 
	 * @param pancungan
	 */
	public KerucutTerpancung(Lingkaran alas, double tinggi, Kerucut pancungan) {
		// TODO implement here
	}

	/**
	 * @param alas 
	 * @param tinggi 
	 * @param pancungan
	 */
	public KerucutTerpancung(Lingkaran alas, int tinggi, Kerucut pancungan) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	@Override
	public float menghitungVolume() {
		// TODO implement here
		return 0.0f;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungVolume(double params) {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungVolume(int params) {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * @return
	 */
	@Override
	public float menghitungLuasPermukaan() {
		// TODO implement here
		return 0.0f;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan(double params) {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan(int params) {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		// TODO implement here
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		// TODO implement here
		
	}

}