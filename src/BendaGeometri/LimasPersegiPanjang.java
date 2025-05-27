package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasPersegiPanjang extends PersegiPanjang implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasPersegiPanjang() {
		super();
		this.tinggi = 0;
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * @param panjang
	 * @param lebar 
	 * @param tinggi
	 */
	public LimasPersegiPanjang(double panjang, double lebar, double tinggi) {
		super(panjang, lebar);
		this.tinggi = tinggi;
	}

	/**
	 * @param panjang
	 * @param lebar 
	 * @param tinggi
	 */
	public LimasPersegiPanjang(int panjang, int lebar, int tinggi) {
		super(panjang, lebar);
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
	 * @param panjang
	 * @param lebar 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungVolume(double panjang, double lebar, double tinggi) {
		double luasAlas = menghitungLuas(panjang, lebar);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @param panjang
	 * @param lebar 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungVolume(int panjang, int lebar, int tinggi) {
		double luasAlas = menghitungLuas(panjang, lebar);
		return (1/3.0) * luasAlas * tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = menghitungLuas();
		double panjang = getPanjang();
		double lebar = getLebar();
		
		// Calculate the height of triangular faces
		double sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(lebar/2, 2));
		double sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(panjang/2, 2));
		
		// Calculate triangular face areas
		double luasSegitigaPanjang = 0.5 * panjang * sisiMiringPanjang;
		double luasSegitigaLebar = 0.5 * lebar * sisiMiringLebar;
		
		// Total surface area = base area + 2 triangular faces on panjang side + 2 triangular faces on lebar side
		return luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
	}

	/**
	 * @param panjang
	 * @param lebar 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungLuasPermukaan(double panjang, double lebar, double tinggi) {
		double luasAlas = menghitungLuas(panjang, lebar);
		
		// Calculate the height of triangular faces
		double sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(lebar/2, 2));
		double sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(panjang/2, 2));
		
		// Calculate triangular face areas
		double luasSegitigaPanjang = 0.5 * panjang * sisiMiringPanjang;
		double luasSegitigaLebar = 0.5 * lebar * sisiMiringLebar;
		
		// Total surface area = base area + 2 triangular faces on panjang side + 2 triangular faces on lebar side
		return luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
	}

	/**
	 * @param panjang
	 * @param lebar 
	 * @param tinggi 
	 * @return
	 */
	public double menghitungLuasPermukaan(int panjang, int lebar, int tinggi) {
		double luasAlas = menghitungLuas(panjang, lebar);
		
		// Calculate the height of triangular faces
		double sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(lebar/2, 2));
		double sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(panjang/2, 2));
		
		// Calculate triangular face areas
		double luasSegitigaPanjang = 0.5 * panjang * sisiMiringPanjang;
		double luasSegitigaLebar = 0.5 * lebar * sisiMiringLebar;
		
		// Total surface area = base area + 2 triangular faces on panjang side + 2 triangular faces on lebar side
		return luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume limas persegi panjang: " + this.menghitungVolume());
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP limas persegi panjang: " + this.menghitungLuasPermukaan());
	}
}