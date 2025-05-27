package BendaGeometri;

public class LimasSegitiga extends Segitiga {

	private double tinggiLimas;
	private double luasAlas;
	private double kelilingAlas;
	private double radiusDalam;
	private double tinggiSisiMiring;
	private double volume;
	private double luasPermukaan;

	public LimasSegitiga() {
		super();
		this.tinggiLimas = 0;
	}

	public LimasSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2,
			double tinggiLimas) {
		super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
		this.tinggiLimas = tinggiLimas;
	}

	public LimasSegitiga(int alas, int tinggiSegitiga, int sisiMiring1, int sisiMiring2, int tinggiLimas) {
		super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double alas, double tinggiSegitiga, double tinggiLimas) {
		luasAlas = menghitungLuas(alas, tinggiSegitiga);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(int alas, int tinggiSegitiga, int tinggiLimas) {
		luasAlas = menghitungLuas(alas, tinggiSegitiga);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		kelilingAlas = super.menghitungKeliling();
		radiusDalam = (2 * luasAlas) / kelilingAlas;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + radiusDalam * radiusDalam);
		luasPermukaan = luasAlas + 0.5 * kelilingAlas * tinggiSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2,
			double tinggiLimas) {
		luasAlas = super.menghitungLuas(alas, tinggiSegitiga);
		kelilingAlas = super.menghitungKeliling(alas, sisiMiring1, sisiMiring2);
		radiusDalam = (2 * luasAlas) / kelilingAlas;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + radiusDalam * radiusDalam);
		luasPermukaan = luasAlas + 0.5 * kelilingAlas * tinggiSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int alas, int tinggiSegitiga, int sisiMiring1, int sisiMiring2,
			int tinggiLimas) {
		luasAlas = super.menghitungLuas(alas, tinggiSegitiga);
		kelilingAlas = super.menghitungKeliling(alas, sisiMiring1, sisiMiring2);
		radiusDalam = (2 * luasAlas) / kelilingAlas;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + radiusDalam * radiusDalam);
		luasPermukaan = luasAlas + 0.5 * kelilingAlas * tinggiSisiMiring;
		return luasPermukaan;
	}
	@Override
	public String getNamaBenda() {
		return "Limas Segitiga";
	}
}