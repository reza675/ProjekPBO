package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasPersegi extends Persegi implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasPersegi() {
		super();
		this.tinggi = 0;
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * @param sisi 
	 * @param tinggi
	 */
	public LimasPersegi(double sisi, double tinggi) {
		super(sisi);
		this.tinggi = tinggi;
	}

	/**
	 * @param sisi 
	 * @param tinggi
	 */
	public LimasPersegi(int sisi, int tinggi) {
		super(sisi);
		this.tinggi = tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		double luasAlas = menghitungLuas();
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param sisi 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungVolume(double sisi, double tinggi) {
		double luasAlas = menghitungLuas(sisi);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param sisi 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungVolume(int sisi, int tinggi) {
		double luasAlas = menghitungLuas(sisi);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = menghitungLuas();
		double sisiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(getSisi()/2, 2));
		double luasSegitiga = 0.5 * getSisi() * sisiSegitiga;
		return luasAlas + 4 * luasSegitiga;
	}

	/**
	 * @param sisi 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungLuasPermukaan(double sisi, double tinggi) {
		double luasAlas = menghitungLuas(sisi);
		double sisiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(sisi/2, 2));
		double luasSegitiga = 0.5 * sisi * sisiSegitiga;
		return luasAlas + 4 * luasSegitiga;
	}

	/**
	 * @param sisi 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungLuasPermukaan(int sisi, int tinggi) {
		double luasAlas = menghitungLuas(sisi);
		double sisiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(sisi/2, 2));
		double luasSegitiga = 0.5 * sisi * sisiSegitiga;
		return luasAlas + 4 * luasSegitiga;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume limas persegi: " + this.menghitungVolume());
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP limas persegi: " + this.menghitungLuasPermukaan());
	}
}