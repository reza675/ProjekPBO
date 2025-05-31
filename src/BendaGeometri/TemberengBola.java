package BendaGeometri;

public class TemberengBola extends Bola {

	private double tinggi;
	private double volume;
	private double luasPermukaan;
	
	public TemberengBola() {
		super();
		this.tinggi = 0;
	}


	public TemberengBola(double radius, double tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public TemberengBola(int radius, int tinggi) {
		super(radius);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi);
		return volume; 
	}

	public double menghitungVolume(double radius, double tinggi) {
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi);
		return volume;
	}

	public double menghitungVolume(int radius, int tinggi) {
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi);
		return volume; 
	}

	public double menghitungLuasPermukaan() {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radius, double tinggi) {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int radius, int tinggi) {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda(){
		return "Tembereng Bola";
	}

}