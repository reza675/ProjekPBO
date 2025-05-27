package BendaGeometri;

/**
 * 
 */
public class Tabung extends Lingkaran implements IBenda3D {

	/**
	 * Default constructor
	 */
	public Tabung() {
		super();
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * 
	 */
	/**
	 * @param tinggi
	 */
	public Tabung(double radius, double tinggi) {
		// TODO implement here
		super(radius);
		this.tinggi = tinggi;
	}

	/**
	 * @param bendaAlas 
	 * @param tinggi
	 */
	public Tabung(double radius, int tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		double luasAlas = super.menghitungLuas();
		return luasAlas * tinggi;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(double tinggi) {
		double luasAlas = super.menghitungLuas();
		return luasAlas * tinggi;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungVolume(double tinggi, double jarijari) {
		double luasAlas = super.menghitungLuas(jarijari);
		return luasAlas * tinggi;
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		double luasAlas = super.menghitungLuas();
		double selimut = 2 * Math.PI * tinggi;
		return 2*luasAlas + selimut;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungLuasPermukaan(double tinggi) {
		double luasAlas = super.menghitungLuas();
		double selimut = 2 * Math.PI * tinggi;
		return 2*luasAlas + selimut;
	}

	/**
	 * @param params 
	 * @return
	 */
	public double menghitungLuasPermukaan(double tinggi, double radius) {
		double luasAlas = super.menghitungLuas(radius);
		double selimut = 2 * Math.PI * tinggi;
		return 2*luasAlas + selimut;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		System.out.println("Volume Tabung: " + menghitungVolume());
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		System.out.println("LP Tabung: " + menghitungLuasPermukaan());
		
	}
}