package BendaGeometri;

public class Bola extends Lingkaran {

	protected double luasAlas;
	protected double volume;
	protected double luasPermukaan;

	public Bola() {
		super();
	}

	public Bola(double radius) {
		super(radius);
	}
	public Bola(int radius) {
		super(radius);
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (4.0 / 3.0) * luasAlas* radius;
		return volume;
	}
	public double menghitungVolume(int radius) {
		luasAlas = super.menghitungLuas(radius);
		volume = (4.0 / 3.0) * luasAlas* radius;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int radius) {
		luasAlas = super.menghitungLuas(radius);
		luasPermukaan = 4 * luasAlas;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Bola";
	}

}