package BendaGeometri;

public class KerucutTerpancung extends Kerucut {

	private double radiusAtas;
	private double volume;
	private double luasPermukaan;

	public KerucutTerpancung() {
		super();
		this.radiusAtas = 0.0;
	}

	public KerucutTerpancung(double radiusAtas, double radiusBawah, double tinggi) {
		super(radiusBawah, tinggi);
		this.radiusAtas = radiusAtas;
	}

	public KerucutTerpancung(int radiusAtas, int radiusBawah, int tinggi) {
		super(radiusBawah, tinggi);
		this.radiusAtas = radiusAtas;
	}

	private double hitungVolumeCore(double r1, double r2, double h) {
		volume = (1.0 / 3.0) * super.PI * h * (r1 * r1 + r1 * r2 + r2 * r2);
		return volume;	
	}

	@Override
	public double menghitungVolume() {
		return hitungVolumeCore(this.radiusAtas, super.radius, super.tinggi);
	}

	public double menghitungVolume(double radiusAtas, double radiusBawah, double tinggi) {
		return hitungVolumeCore(radiusAtas, radiusBawah, tinggi);
	}

	public double menghitungVolume(int radiusAtas, int radiusBawah, int tinggi) {
		return hitungVolumeCore((double) radiusAtas,(double) radiusBawah,(double) tinggi);
	}

	private double hitungLuasPermukaanCore(double r1, double r2, double h) {
		double s = Math.sqrt(h * h + (r2 - r1) * (r2 - r1));
		double luasAtas = super.menghitungLuas(r1);
		double luasBawah = super.menghitungLuas(r2);
		double luasSelimut = super.PI * (r1 + r2) * s;
		luasPermukaan = luasAtas + luasBawah + luasSelimut;
		return luasPermukaan;
	}

	@Override
	public double menghitungLuasPermukaan() {
		return hitungLuasPermukaanCore(this.radiusAtas, super.radius, super.tinggi);
	}

	public double menghitungLuasPermukaan(double radiusAtas, double radiusBawah, double tinggi) {
		return hitungLuasPermukaanCore(radiusAtas, radiusBawah, tinggi);
	}

	public double menghitungLuasPermukaan(int radiusAtas, int radiusBawah, int tinggi) {
		return hitungLuasPermukaanCore((double) radiusAtas,(double) radiusBawah,(double) tinggi);
	}

	@Override
	public String getNamaBenda() {
		return "Kerucut Terpancung";
	}
}
