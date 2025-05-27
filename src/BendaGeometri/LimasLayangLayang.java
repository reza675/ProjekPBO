package BendaGeometri;

public class LimasLayangLayang extends LayangLayang {
	private double tinggi;
	private double luasAlas;
	private double luasPermukaan;
	private double volume;

	public LimasLayangLayang() {
		super();
		this.tinggi = 0;
	}

	public LimasLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang, double tinggi) {
		super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
		this.tinggi = tinggi;
	}

	public LimasLayangLayang(int diagonal1, int diagonal2, int sisiPendek, int sisiPanjang, int tinggi) {
		super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		luasAlas = super.menghitungLuas();
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(double d1, double d2) {
		luasAlas = super.menghitungLuas(d1, d2);
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int d1, int d2) {
		luasAlas = super.menghitungLuas(d1, d2);
		volume = (1.0 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = super.menghitungLuas();
		double proyeksiKeSisiPendek = super.diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = super.diagonal1 / 2.0;
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
		double luasMukaPendek = sisiPendek * tinggiSegitigaPendek;
		double luasMukaPanjang = sisiPanjang * tinggiSegitigaPanjang;
		luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang) {
		luasAlas = super.menghitungLuas(diagonal1, diagonal2);
		double proyeksiKeSisiPendek = diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = diagonal1 / 2.0;
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
		double luasMukaPendek = sisiPendek * tinggiSegitigaPendek;
		double luasMukaPanjang = sisiPanjang * tinggiSegitigaPanjang;
		luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int sisiPendek, int sisiPanjang) {
		luasAlas = menghitungLuas(diagonal1, diagonal2);
		double proyeksiKeSisiPendek = diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = diagonal1 / 2.0;
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
		double luasMukaPendek = sisiPendek * tinggiSegitigaPendek;
		double luasMukaPanjang = sisiPanjang * tinggiSegitigaPanjang;
		luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Layang-Layang";
	}
}