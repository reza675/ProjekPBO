package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasJajaranGenjang extends JajaranGenjang implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasJajaranGenjang() {
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
	private JajaranGenjang bendaAlas;

	/**
	 * @param bendaAlas 
	 * @param tinggi
	 */
	public LimasJajaranGenjang(double panjang_alas, double tinggi_alas, double sisi_miring_alas, double tinggi) {
		super(panjang_alas, tinggi_alas, sisi_miring_alas);
		this.tinggi = tinggi;
	}

	/**
	 * @param bendaAlas 
	 * @param tinggi
	 */
	public LimasJajaranGenjang(int panjang_alas, int tinggi_alas, int sisi_miring_alas, int tinggi) {
		super(panjang_alas, tinggi_alas, sisi_miring_alas);
		this.tinggi = tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public float menghitungVolume() {
		double luasAlas = bendaAlas.menghitungLuas();
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(int panjang_alas, int tinggi_alas, int tinggi) {
		double luasAlas = bendaAlas.menghitungLuas(panjang_alas, tinggi_alas);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(double panjang_alas, double tinggi_alas, double tinggi) {
		double luasAlas = bendaAlas.menghitungLuas(panjang_alas, tinggi_alas);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		float luasAlas =  super.menghitungLuas();
		float alas =  super.getAlas();
		float tinggiAlas =  super.getTinggi();
		float miring =  super.getMiring();
		
		// Menghitung luas segitiga pada sisi tegak
		float luasSegitiga1 = (1/2.0) * alas * tinggi;
		float luasSegitiga2 = (1/2.0) * miring * tinggi;
		float luasSegitiga3 = (1/2.0) * alas * tinggi;
		float luasSegitiga4 = (1/2.0) * miring * tinggi;
		
		return luasAlas + luasSegitiga1 + luasSegitiga2 + luasSegitiga3 + luasSegitiga4;
	}

	/**
	 * @param params 
	 * @return
	 */

	public double menghitungLuasPermukaan(double panjang_alas, double tinggi_alas, double sisi_miring_alas, double tinggi) {
		float luasAlas =  super.menghitungLuas(panjang_alas, tinggi_alas);
	
		
		// Menghitung luas segitiga pada sisi tegak
		float luasSegitiga1 = (1/2.0) * panjang_alas * tinggi;
		float luasSegitiga2 = (1/2.0) * sisi_miring_alas * tinggi;
		float luasSegitiga3 = (1/2.0) * panjang_alas * tinggi;
		float luasSegitiga4 = (1/2.0) * sisi_miring_alas * tinggi;
		
		return luasAlas + luasSegitiga1 + luasSegitiga2 + luasSegitiga3 + luasSegitiga4;
	}

	/**
	 * @param params 
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan(int panjang_alas, int tinggi_alas, int sisi_miring_alas, int tinggi) {
		float luasAlas =  super.menghitungLuas(panjang_alas, tinggi_alas);
	
		
		// Menghitung luas segitiga pada sisi tegak
		float luasSegitiga1 = (1/2.0) * panjang_alas * tinggi;
		float luasSegitiga2 = (1/2.0) * sisi_miring_alas * tinggi;
		float luasSegitiga3 = (1/2.0) * panjang_alas * tinggi;
		float luasSegitiga4 = (1/2.0) * sisi_miring_alas * tinggi;
		
		return luasAlas + luasSegitiga1 + luasSegitiga2 + luasSegitiga3 + luasSegitiga4;
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