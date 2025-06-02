package BendaGeometri;

public class Kerucut extends Lingkaran {

	protected double tinggi;
	protected double luasAlas;
	protected double sisiMiring;
	protected double luasSelimut;
	protected double volume;
	protected double luasPermukaan;

	public Kerucut(double radius, double tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public Kerucut(int radius, int tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int radius, int tinggi) {
		luasAlas = super.menghitungLuas(radius);
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		sisiMiring = Math.sqrt(tinggi * tinggi + super.radius * super.radius);
		luasSelimut = (super.menghitungKeliling() / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int tinggi, int radius) {
		luasAlas = super.menghitungLuas(radius);
		sisiMiring = Math.sqrt(tinggi * tinggi + radius * radius);
		luasSelimut = (super.menghitungKeliling(radius) / 2.0) * sisiMiring;
		luasPermukaan = luasAlas + luasSelimut;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Kerucut";
	}
}
