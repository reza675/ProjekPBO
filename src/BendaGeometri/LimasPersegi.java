package BendaGeometri;

public class LimasPersegi extends Persegi {

	private double tinggi;
	private double luasAlas;
	private double setengahSisi;
	private double tinggiSegitiga;
	private double luasSegitiga;
	private double volume;
	private double luasPermukaan;

	public LimasPersegi() {
		super();
		this.tinggi = 0;
	}

	public LimasPersegi(double sisi, double tinggi) {
		super(sisi);
		this.tinggi = tinggi;
	}

	public LimasPersegi(int sisi, int tinggi) {
		super(sisi);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(double sisi, double tinggi) {
		luasAlas = super.menghitungLuas(sisi);
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int sisi, int tinggi) {
		luasAlas = super.menghitungLuas(sisi);
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		setengahSisi = super.sisi / 2;
		tinggiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(setengahSisi, 2));
		luasSegitiga = 0.5 * super.sisi * tinggiSegitiga;
		luasPermukaan = luasAlas + 4 * luasSegitiga;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double sisi, double tinggi) {
		luasAlas = super.menghitungLuas(sisi);
		setengahSisi = sisi / 2;
		tinggiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(setengahSisi, 2));
		luasSegitiga = 0.5 * sisi * tinggiSegitiga;
		luasPermukaan = luasAlas + 4 * luasSegitiga;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int sisi, int tinggi) {
		luasAlas = super.menghitungLuas(sisi);
		setengahSisi = sisi / 2;
		tinggiSegitiga = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(setengahSisi, 2));
		luasSegitiga = 0.5 *sisi * tinggiSegitiga;
		luasPermukaan = luasAlas + 4 * luasSegitiga;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Persegi";
	}
}