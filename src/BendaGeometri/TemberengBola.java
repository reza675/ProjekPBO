package BendaGeometri;

public class TemberengBola extends Bola {

	private double tinggi; // tinggi tembereng dari bidang potong ke puncak tembereng
	private double volume;
	private double luasPermukaan;
	protected final double PI = 3.14; // valent : duh pake ini ga ya
	
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
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi); // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return volume; 
	}

	public double menghitungVolume(double radius, double tinggi) {
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi); // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return volume;
	}

	public double menghitungVolume(int radius, int tinggi) {
		volume = ((tinggi * tinggi * PI) / 3) * (3 * radius - tinggi); // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return volume; 
	}

	public double menghitungLuasPermukaan() {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI; // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radius, double tinggi) {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI; // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int radius, int tinggi) {
		luasPermukaan = 2 * radius * tinggi * PI + radius * radius * PI; // valent : rumus dari gpt gaada ngambil dari lingkaran maupun bola bang asli
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda(){
		return "Tembereng Bola";
	}

}