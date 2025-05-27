package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Kerucut extends Lingkaran implements IBenda3D {

	/**
	 * Default constructor
	 */
	public Kerucut() {
		super();
		this.tinggi = 0;
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * 
	 */

	/**
	 * @param tinggi
	 */
	public Kerucut(double radius, double tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	/**
	 * @param bendaAlas 
	 * @param tinggi
	 */
	public Kerucut(int radius, int tinggi) {
		super(radius);
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
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(double radius, double tinggi) {
		double luasAlas = menghitungLuas(radius);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(int radius, int tinggi) {
		double luasAlas = menghitungLuas(radius);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = super.menghitungLuas();
		double sisiMiring = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(super.getRadius())); 
		double selimut = Math.PI * super.getRadius() * sisiMiring;
		return luasAlas + sisiMiring;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan(double tinggi, double radius) {
		double luasAlas = super.menghitungLuas();
		double sisiMiring = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(radius)); 
		double selimut = Math.PI * radius * sisiMiring;
		return luasAlas + sisiMiring;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan(int tinggi, int radius) {
		double luasAlas = super.menghitungLuas();
		double sisiMiring = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(radius)); 
		double selimut = Math.PI * radius * sisiMiring;
		return luasAlas + sisiMiring;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume kerucut: " + this.menghitungVolume());
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP kerucut: " + this.menghitungLuasPermukaan());
		
	}
}