package BendaGeometri;

public class Tabung extends Lingkaran {

	private double tinggi;
	private double volume;
	private double luasAlas;
	private double selimut;
	private double luasPermukaan;

	public Tabung(double radius, double tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public Tabung(int radius, int tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int radius, int tinggi) {
		luasAlas = super.menghitungLuas(radius);
		volume = luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = 2 * super.menghitungLuas();
		selimut = super.menghitungKeliling() * tinggi;
		luasPermukaan = luasAlas + selimut;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int radius, int tinggi) {
		luasAlas = 2 * super.menghitungLuas(radius);
		selimut = super.menghitungKeliling(radius) * tinggi;
		luasPermukaan = luasAlas + selimut;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Tabung";
	}

}