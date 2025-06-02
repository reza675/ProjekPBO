package BendaGeometri;

public class LimasJajaranGenjang extends JajaranGenjang {
	private double tinggiLimas;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;


	public LimasJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiLimas) {
		super(panjangAlas, tinggiAlas, sisiMiringAlas);
		this.tinggiLimas = tinggiLimas;
	}

	public LimasJajaranGenjang(int panjangAlas, int tinggiAlas, int sisiMiringAlas, int tinggiLimas) {
		super(panjangAlas, tinggiAlas, sisiMiringAlas);
		this.tinggiLimas = tinggiLimas;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(int panjangAlas, int tinggiAlas, int tinggiLimas) {
		luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungVolume(double panjangAlas, double tinggiAlas, double tinggiLimas) {
		luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
		volume = (1 / 3.0) * luasAlas * tinggiLimas;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		double alas = super.alas;
		double tinggiAlas = super.tinggi;
		double sisiMiring = super.sisiMiring;
		double tinggiSisiMiring = (alas * tinggiAlas) / sisiMiring;
		double jarakKeAlas = tinggiAlas / 2.0;
		double jarakKeSisiMiring = tinggiSisiMiring / 2.0;
		double slantHeightAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeAlas, 2));
		double slantHeightSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeSisiMiring, 2));
		double luasSegitigaAlas = 0.5 * alas * slantHeightAlas;
		double luasSegitigaSisiMiring = 0.5 * sisiMiring * slantHeightSisiMiring;

		luasPermukaan = luasAlas + 2 * luasSegitigaAlas + 2 * luasSegitigaSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double panjangAlas, double tinggiAlas, double sisiMiringAlas,
			double tinggiLimas) {
		double luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
		double tinggiSisiMiring = (panjangAlas * tinggiAlas) / sisiMiringAlas;
		double jarakKeAlas = tinggiAlas / 2.0;
		double jarakKeSisiMiring = tinggiSisiMiring / 2.0;
		double slantHeightAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeAlas, 2));
		double slantHeightSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeSisiMiring, 2));
		double luasSegitigaAlas = 0.5 * panjangAlas * slantHeightAlas;
		double luasSegitigaSisiMiring = 0.5 * sisiMiringAlas * slantHeightSisiMiring;
		
		luasPermukaan = luasAlas + 2 * luasSegitigaAlas + 2 * luasSegitigaSisiMiring;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int panjangAlas, int tinggiAlas, int sisiMiringAlas, int tinggiLimas) {
			double luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
		double tinggiSisiMiring = (panjangAlas * tinggiAlas) / sisiMiringAlas;
		double jarakKeAlas = tinggiAlas / 2.0;
		double jarakKeSisiMiring = tinggiSisiMiring / 2.0;
		double slantHeightAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeAlas, 2));
		double slantHeightSisiMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(jarakKeSisiMiring, 2));
		double luasSegitigaAlas = 0.5 * panjangAlas * slantHeightAlas;
		double luasSegitigaSisiMiring = 0.5 * sisiMiringAlas * slantHeightSisiMiring;
		
		luasPermukaan = luasAlas + 2 * luasSegitigaAlas + 2 * luasSegitigaSisiMiring;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Jajaran Genjang";
	}
}
