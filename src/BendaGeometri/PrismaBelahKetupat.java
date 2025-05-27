package BendaGeometri;

/**
 * 
 */
public class PrismaBelahKetupat extends BelahKetupat implements IBenda3D {

	/**
	 * Default constructor
	 */
	public PrismaBelahKetupat() {
            super();
	}

	/**
	 * 
	 */
	private double tinggi;



// Constructor
    public PrismaBelahKetupat(double diagonal1, double diagonal2, double tinggi, double sisi) {
        super(diagonal1, diagonal2, sisi);
        this.tinggi = tinggi;
    }

    // Calculate the volume of the prism
    @Override
    public double menghitungVolume() {
        double luasAlas = super.menghitungLuas(); // Area of the rhombus base
        return luasAlas * tinggi;
    }

    public double menghitungVolume(double luasAlas, double tinggi) {
        return luasAlas * tinggi;
    }

    public double menghitungVolume(double d1, double d2, double tinggi) {
        double luasAlas = super.menghitungLuas(d1, d2);
        return luasAlas * tinggi;
       
    }

    // Calculate the surface area of the prism
    @Override
    public double menghitungLuasPermukaan() {
        double luasAlas = this.getLuas(); // Area of the rhombus base
        double kelilingAlas = this.getKeliling(); 
        return (2 * luasAlas) + (kelilingAlas * tinggi);
    }

    public double menghitungLuasPermukaan(double d1, double d2, double tinggi) {
            double luasAlas = super.menghitungLuas(d1, d2);
            double kelilingAlas = 4 * Math.sqrt(Math.pow(d1 / 2, 2) + Math.pow(d2 / 2, 2));
            return (2 * luasAlas) + (kelilingAlas * tinggi);
    }

    public double menghitungLuasPermukaan(double d1, double d2, double tinggi, double sisi) {
            double luasAlas = super.menghitungLuas(d1, d2);
            double kelilingAlas = super.menghitungKeliling(sisi);
            return (2 * luasAlas) + (kelilingAlas * tinggi);
    }

    // Print the volume of the prism
    @Override
    public void mencetakVolume() {
        System.out.println("Volume: " + menghitungVolume());
    }

    // Print the surface area of the prism
    @Override
    public void mencetakLuasPermukaan() {
        System.out.println("Luas Permukaan: " + menghitungLuasPermukaan());
    }

}