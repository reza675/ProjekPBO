package BendaGeometri;

public class LimasBelahKetupat extends BelahKetupat {

	private double tinggi;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasBelahKetupat(double d1, double d2, double sisi, double tinggi) {
		super(d1, d2, sisi);
		this.tinggi = tinggi;
	}

	public LimasBelahKetupat(int d1, int d2, int sisi, int tinggi) {
		super(d1, d2, sisi);
		this.tinggi = tinggi;
	}


	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(double diagonal1, double diagonal2, double tinggi) {
		luasAlas = super.menghitungLuas(diagonal1, diagonal2);
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int d1, int d2, int tinggi) {
		luasAlas = super.menghitungLuas(diagonal1, diagonal2);
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
        double setengahDiagonal1 = super.diagonal1 / 2.0;
        double setengahDiagonal2 = super.diagonal2 / 2.0;
        double tinggiSegitiga = Math.sqrt(tinggi * tinggi + setengahDiagonal1 * setengahDiagonal2);
        double luasSegitiga = 0.5 * super.sisi * tinggiSegitiga;
        luasPermukaan =  luasAlas + 4 * luasSegitiga;
        return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double tinggi, double sisi) {
		luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        double setengahDiagonal1 = diagonal1 / 2.0;
        double setengahDiagonal2 = diagonal2 / 2.0;
        double tinggiSegitiga = Math.sqrt(tinggi * tinggi + setengahDiagonal1 * setengahDiagonal2);
        double luasSegitiga = 0.5 * sisi * tinggiSegitiga;
        luasPermukaan =  luasAlas + 4 * luasSegitiga;
        return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int tinggi, int sisi) {
		luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        double setengahDiagonal1 = diagonal1 / 2.0;
        double setengahDiagonal2 = diagonal2 / 2.0;
        double tinggiSegitiga = Math.sqrt(tinggi * tinggi + setengahDiagonal1 * setengahDiagonal2);
        double luasSegitiga = 0.5 * sisi * tinggiSegitiga;
        luasPermukaan =  luasAlas + 4 * luasSegitiga;
        return luasPermukaan;
	}
	@Override
	public String getNamaBenda() {
		return "Limas Jajaran Genjang";
	}

}