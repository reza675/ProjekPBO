package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasTrapesium extends Trapesium implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasTrapesium() {
		super();
		this.tinggiLimas = 0;
	}

	/**
	 * 
	 */
	private double tinggiLimas;

	/**
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param sisiKiri
	 * @param sisiKanan
	 * @param tinggiLimas
	 */
	public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri, double sisiKanan, double tinggiLimas) {
		super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
		this.tinggiLimas = tinggiLimas;
	}

	/**
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param sisiKiri
	 * @param sisiKanan
	 * @param tinggiLimas
	 */
	public LimasTrapesium(int alasAtas, int alasBawah, int tinggiTrapesium, int sisiKiri, int sisiKanan, int tinggiLimas) {
		super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
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
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungVolume(double alasAtas, double alasBawah, double tinggiTrapesium, double tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		return (1/3.0) * luasAlas * tinggiLimas;
	}

	/**
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungVolume(int alasAtas, int alasBawah, int tinggiTrapesium, int tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		return (1/3.0) * luasAlas * tinggiLimas;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = menghitungLuas();
		double alasAtas = getAlasAtas();
		double alasBawah = getAlasBawah();
		double tinggiTrapesium = getTinggi();
		double sisiKiri = getSisiKiri();
		double sisiKanan = getSisiKanan();
		
		// Calculate the distance from center of trapezoid to each side
		double centerToTop = alasAtas / 2;
		double centerToBottom = alasBawah / 2;
		double centerToLeft = Math.sqrt(Math.pow(sisiKiri, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		double centerToRight = Math.sqrt(Math.pow(sisiKanan, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		
		// Calculate the slant heights from apex to each side
		double slantHeightTop = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToTop, 2));
		double slantHeightBottom = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToBottom, 2));
		double slantHeightLeft = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToLeft, 2));
		double slantHeightRight = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToRight, 2));
		
		// Calculate areas of the four triangular faces
		double areaTop = 0.5 * alasAtas * slantHeightTop;
		double areaBottom = 0.5 * alasBawah * slantHeightBottom;
		double areaLeft = 0.5 * sisiKiri * slantHeightLeft;
		double areaRight = 0.5 * sisiKanan * slantHeightRight;
		
		// Total surface area = base area + 4 triangular faces
		return luasAlas + areaTop + areaBottom + areaLeft + areaRight;
	}

	/**
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param sisiKiri
	 * @param sisiKanan
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungLuasPermukaan(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri, double sisiKanan, double tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		
		// Calculate the distance from center of trapezoid to each side
		double centerToTop = alasAtas / 2;
		double centerToBottom = alasBawah / 2;
		double centerToLeft = Math.sqrt(Math.pow(sisiKiri, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		double centerToRight = Math.sqrt(Math.pow(sisiKanan, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		
		// Calculate the slant heights from apex to each side
		double slantHeightTop = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToTop, 2));
		double slantHeightBottom = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToBottom, 2));
		double slantHeightLeft = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToLeft, 2));
		double slantHeightRight = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToRight, 2));
		
		// Calculate areas of the four triangular faces
		double areaTop = 0.5 * alasAtas * slantHeightTop;
		double areaBottom = 0.5 * alasBawah * slantHeightBottom;
		double areaLeft = 0.5 * sisiKiri * slantHeightLeft;
		double areaRight = 0.5 * sisiKanan * slantHeightRight;
		
		// Total surface area = base area + 4 triangular faces
		return luasAlas + areaTop + areaBottom + areaLeft + areaRight;
	}

	/**
	 * @param alasAtas
	 * @param alasBawah
	 * @param tinggiTrapesium
	 * @param sisiKiri
	 * @param sisiKanan
	 * @param tinggiLimas
	 * @return
	 */
	public double menghitungLuasPermukaan(int alasAtas, int alasBawah, int tinggiTrapesium, int sisiKiri, int sisiKanan, int tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		
		// Calculate the distance from center of trapezoid to each side
		double centerToTop = alasAtas / 2;
		double centerToBottom = alasBawah / 2;
		double centerToLeft = Math.sqrt(Math.pow(sisiKiri, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		double centerToRight = Math.sqrt(Math.pow(sisiKanan, 2) - Math.pow(tinggiTrapesium, 2)) / 2;
		
		// Calculate the slant heights from apex to each side
		double slantHeightTop = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToTop, 2));
		double slantHeightBottom = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToBottom, 2));
		double slantHeightLeft = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToLeft, 2));
		double slantHeightRight = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(centerToRight, 2));
		
		// Calculate areas of the four triangular faces
		double areaTop = 0.5 * alasAtas * slantHeightTop;
		double areaBottom = 0.5 * alasBawah * slantHeightBottom;
		double areaLeft = 0.5 * sisiKiri * slantHeightLeft;
		double areaRight = 0.5 * sisiKanan * slantHeightRight;
		
		// Total surface area = base area + 4 triangular faces
		return luasAlas + areaTop + areaBottom + areaLeft + areaRight;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume limas trapesium: " + this.menghitungVolume());
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP limas trapesium: " + this.menghitungLuasPermukaan());
	}
}