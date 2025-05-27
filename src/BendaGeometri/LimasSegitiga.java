package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasSegitiga extends Segitiga implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasSegitiga() {
		super();
		this.tinggiLimas = 0;
	}

	/**
	 * 
	 */
	private double tinggiLimas;

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param sisiMiring1
	 * @param sisiMiring2
	 * @param tinggiLimas
	 */
	public LimasSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2, double tinggiLimas) {
		super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
		this.tinggiLimas = tinggiLimas;
	}

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param sisiMiring1
	 * @param sisiMiring2
	 * @param tinggiLimas
	 */
	public LimasSegitiga(int alas, int tinggiSegitiga, int sisiMiring1, int sisiMiring2, int tinggiLimas) {
		super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
		this.tinggiLimas = tinggiLimas;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		double luasAlas = menghitungLuas();
		return (1/3.0) * luasAlas * tinggiLimas;
	}

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungVolume(double alas, double tinggiSegitiga, double tinggiLimas) {
		double luasAlas = menghitungLuas(alas, tinggiSegitiga);
		return (1/3.0) * luasAlas * tinggiLimas;
	}

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungVolume(int alas, int tinggiSegitiga, int tinggiLimas) {
		double luasAlas = menghitungLuas(alas, tinggiSegitiga);
		return (1/3.0) * luasAlas * tinggiLimas;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = menghitungLuas();
		double alas = getAlas();
		double tinggiSegitiga = getTinggiSegitiga();
		double sisiMiring1 = getSisiMiring1();
		double sisiMiring2 = getSisiMiring2();
		
		// Calculate the height of each triangular face from the apex to the base
		double tinggiSisiAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiSegitiga/3.0, 2));
		double tinggiSisiMiring1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring1/2.0, 2));
		double tinggiSisiMiring2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring2/2.0, 2));
		
		// Calculate areas of the three triangular faces
		double luasSisiAlas = 0.5 * alas * tinggiSisiAlas;
		double luasSisiMiring1 = 0.5 * sisiMiring1 * tinggiSisiMiring1;
		double luasSisiMiring2 = 0.5 * sisiMiring2 * tinggiSisiMiring2;
		
		// Total surface area = base area + 3 triangular faces
		return luasAlas + luasSisiAlas + luasSisiMiring1 + luasSisiMiring2;
	}

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param sisiMiring1
	 * @param sisiMiring2
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungLuasPermukaan(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2, double tinggiLimas) {
		double luasAlas = menghitungLuas(alas, tinggiSegitiga);
		
		// Calculate the height of each triangular face from the apex to the base
		double tinggiSisiAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiSegitiga/3.0, 2));
		double tinggiSisiMiring1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring1/2.0, 2));
		double tinggiSisiMiring2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring2/2.0, 2));
		
		// Calculate areas of the three triangular faces
		double luasSisiAlas = 0.5 * alas * tinggiSisiAlas;
		double luasSisiMiring1 = 0.5 * sisiMiring1 * tinggiSisiMiring1;
		double luasSisiMiring2 = 0.5 * sisiMiring2 * tinggiSisiMiring2;
		
		// Total surface area = base area + 3 triangular faces
		return luasAlas + luasSisiAlas + luasSisiMiring1 + luasSisiMiring2;
	}

	/**
	 * @param alas
	 * @param tinggiSegitiga
	 * @param sisiMiring1
	 * @param sisiMiring2
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungLuasPermukaan(int alas, int tinggiSegitiga, int sisiMiring1, int sisiMiring2, int tinggiLimas) {
		double luasAlas = menghitungLuas(alas, tinggiSegitiga);
		
		// Calculate the height of each triangular face from the apex to the base
		double tinggiSisiAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiSegitiga/3.0, 2));
		double tinggiSisiMiring1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring1/2.0, 2));
		double tinggiSisiMiring2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiMiring2/2.0, 2));
		
		// Calculate areas of the three triangular faces
		double luasSisiAlas = 0.5 * alas * tinggiSisiAlas;
		double luasSisiMiring1 = 0.5 * sisiMiring1 * tinggiSisiMiring1;
		double luasSisiMiring2 = 0.5 * sisiMiring2 * tinggiSisiMiring2;
		
		// Total surface area = base area + 3 triangular faces
		return luasAlas + luasSisiAlas + luasSisiMiring1 + luasSisiMiring2;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume limas segitiga: " + this.menghitungVolume());
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP limas segitiga: " + this.menghitungLuasPermukaan());
	}
}