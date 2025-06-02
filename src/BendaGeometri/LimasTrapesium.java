package BendaGeometri;

public class LimasTrapesium extends Trapesium {
	private double tinggiLimas;
	private double luasSegitigaAtas;
	private double luasSegitigaBawah;
	private double luasSegitigaKiri;
	private double luasSegitigaKanan;
	private double selisihBasis;
	private double tinggiSisiMiring;
	private double luasAlas;
	private double volume;
	private double luasPermukaan;


	public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri, double sisiKanan,
			double tinggiLimas) {
		super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
		this.tinggiLimas = tinggiLimas;
	}

	public LimasTrapesium(int alasAtas, int alasBawah, int tinggiTrapesium, int sisiKiri, int sisiKanan,
			int tinggiLimas) {
		super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double alasAtas, double alasBawah, double tinggiTrapesium, double tinggiLimas) {
		luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(int alasAtas, int alasBawah, int tinggiTrapesium, int tinggiLimas) {
		luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		luasSegitigaAtas = 0.5 * super.alasAtas * tinggiLimas;
		luasSegitigaBawah = 0.5 * super.alasBawah * tinggiLimas;
		selisihBasis = (super.alasBawah - super.alasAtas) / 2.0;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + selisihBasis * selisihBasis);
		luasSegitigaKiri = 0.5 * super.sisiMiringKiri * tinggiSisiMiring;
		luasSegitigaKanan = 0.5 * super.sisiMiringKanan * tinggiSisiMiring;
		luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri,
			double sisiKanan, double tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		luasSegitigaAtas = 0.5 * alasAtas * tinggiLimas;
		luasSegitigaBawah = 0.5 * alasBawah * tinggiLimas;
		selisihBasis = (alasBawah - alasAtas) / 2.0;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + selisihBasis * selisihBasis);
		luasSegitigaKiri = 0.5 * sisiMiringKiri * tinggiSisiMiring;
		luasSegitigaKanan = 0.5 * sisiMiringKanan * tinggiSisiMiring;
		luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int alasAtas, int alasBawah, int tinggiTrapesium, int sisiKiri, int sisiKanan,
			int tinggiLimas) {
		double luasAlas = menghitungLuas(alasAtas, alasBawah, tinggiTrapesium);
		luasSegitigaAtas = 0.5 * alasAtas * tinggiLimas;
		luasSegitigaBawah = 0.5 * alasBawah * tinggiLimas;
		selisihBasis = (alasBawah - alasAtas) / 2.0;
		tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + selisihBasis * selisihBasis);
		luasSegitigaKiri = 0.5 * sisiMiringKiri * tinggiSisiMiring;
		luasSegitigaKanan = 0.5 * sisiMiringKanan * tinggiSisiMiring;
		luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Trapesium";
	}
}