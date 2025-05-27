package BendaGeometri;

/**
 * 
 */
public class LimasLayangLayang extends LayangLayang implements IBenda3D {

	// Height of the pyramid
	public double tinggi;

	/**
	 * Default constructor
	 */
	public LimasLayangLayang() {
	}

	/**
	 * Constructor with parameters
	 * @param diagonal1 
	 * @param diagonal2 
	 * @param tinggi
	 */
	public LimasLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang, double tinggi) {
		super(diagonal1, diagonal2, sisiPendek, sisiPanjang); // Initialize the base (LayangLayang)
		this.tinggi = tinggi; // Initialize the height
	}

	/**
	 * Calculate the volume of the pyramid
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		double baseArea = super.getLuas(); // Get the base area from LayangLayang
		return (1.0 / 3.0) * baseArea * tinggi;
	}

	/**
	 * Calculate the volume of the pyramid with given parameters
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(double d1, double d2) {
			double baseArea = (d1 * d2) / 2; // Area of the LayangLayang base
			return (1.0 / 3.0) * baseArea * tinggi;
	}

	/**
	 * Calculate the volume of the pyramid with given parameters
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(int d1, int d2) {
			double baseArea = (d1 * d2) / 2; // Area of the LayangLayang base
			return (1.0 / 3.0) * baseArea * tinggi;
	}

	/**
	 * Calculate the surface area of the pyramid
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double baseArea = super.getLuas(); // Get the base area from LayangLayang
		double sideLength1 = super.getSisiPendek(); // Get one side length of the LayangLayang
		double sideLength2 = super.getSisiPanjang(); // Get the other side length of the LayangLayang
		double slantHeight1 = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(sideLength1 / 2, 2));
		double slantHeight2 = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(sideLength2 / 2, 2));
		double lateralArea = (sideLength1 * slantHeight1) + (sideLength2 * slantHeight2);
		return baseArea + lateralArea;
	}

	/**
	 * Calculate the surface area of the pyramid with given parameters
	 * @param params 
	 * @return
	 */
	public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang) {
		double luasAlas = super.menghitungLuas(diagonal1, diagonal2); // 0.5 * d1 * d2

		// Asumsikan titik puncak limas tepat di atas pusat alas
		// Maka proyeksi ke sisi pendek dan sisi panjang dari pusat alas:
		double proyeksiKeSisiPendek = diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = diagonal1 / 2.0;

		// Hitung tinggi segitiga sisi tegak dengan Pythagoras
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPanjang, 2));

		// Hitung luas 4 sisi tegak (2 sisi pendek dan 2 sisi panjang)
		double luasSegitigaPendek = 2 * (0.5 * sisiPendek * tinggiSegitigaPendek);
		double luasSegitigaPanjang = 2 * (0.5 * sisiPanjang * tinggiSegitigaPanjang);

		// Total luas permukaan
		double luasPermukaan = luasAlas + luasSegitigaPendek + luasSegitigaPanjang;

		return luasPermukaan;
	}

	/**
	 * Calculate the surface area of the pyramid with given parameters
	 * @param params 
	 * @return
	 */
	public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int sisiPendek, int sisiPanjang) {
		double luasAlas = super.menghitungLuas(diagonal1, diagonal2); // 0.5 * d1 * d2

		// Asumsikan titik puncak limas tepat di atas pusat alas
		// Maka proyeksi ke sisi pendek dan sisi panjang dari pusat alas:
		double proyeksiKeSisiPendek = diagonal2 / 2.0;
		double proyeksiKeSisiPanjang = diagonal1 / 2.0;

		// Hitung tinggi segitiga sisi tegak dengan Pythagoras
		double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPendek, 2));
		double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(proyeksiKeSisiPanjang, 2));

		// Hitung luas 4 sisi tegak (2 sisi pendek dan 2 sisi panjang)
		double luasSegitigaPendek = 2 * (0.5 * sisiPendek * tinggiSegitigaPendek);
		double luasSegitigaPanjang = 2 * (0.5 * sisiPanjang * tinggiSegitigaPanjang);

		// Total luas permukaan
		double luasPermukaan = luasAlas + luasSegitigaPendek + luasSegitigaPanjang;

		return luasPermukaan;
	}

	/**
	 * Print the volume of the pyramid
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume: " + menghitungVolume());
	}

	/**
	 * Print the surface area of the pyramid
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {

		// TODO implement here
		System.out.println("Luas Permukaan: " + menghitungLuasPermukaan());
	}
}