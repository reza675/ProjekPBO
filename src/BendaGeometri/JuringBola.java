package BendaGeometri;

public class JuringBola extends Bola {

	private double sudut;
	private double volume;
	private double luasPermukaan;
	private double volumeBola;
	private double luasPermukaanBola;
	// valent : bola kok gaada yang menghitungVolume(double radius) sama luaspermukaannya
	protected final double PI = 3.14; // valent : duh pake ini ga ya

	public JuringBola() {
        super();
		this.sudut = 0;
	}

	public JuringBola(double radius, double sudut) {
        super(radius);
		this.sudut = sudut;
	}

	public JuringBola(int radius, int sudut) {
        super(radius);
		this.sudut = sudut;
	}

	public double menghitungVolume() {
		volumeBola = super.menghitungVolume();
		volume = volumeBola * (sudut / (2 * PI));
		return volume;
	}
	
	public double menghitungVolume(double sudut, double radius) {
		volumeBola = super.menghitungVolume(radius);
		volume = volumeBola * (sudut / (2 * PI));
		return volume;
	}
	
	public double menghitungVolume(int sudut, int radius) {
		volumeBola = super.menghitungVolume(radius);
		volume = volumeBola * (sudut / (2 * PI));
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasPermukaanBola = super.menghitungLuasPermukaan();
		luasPermukaan = luasPermukaanBola * (sudut / (2 * PI)) + PI * radius * radius; // valent : PI * radius * radius sebenarnya ada dalam lingkaran namun parent dari JuringBola adalah Bola.. Apakah rumus saya benar?
		return luasPermukaan;
	}
	
	public double menghitungLuasPermukaan(double sudut, double radius) {
		luasPermukaanBola = super.menghitungLuasPermukaan(radius);
		luasPermukaan = luasPermukaanBola * (sudut / (2 * PI)) + PI * radius * radius;
		return luasPermukaan;
	}
	
	public double menghitungLuasPermukaan(int sudut, int radius) {
		luasPermukaanBola = super.menghitungLuasPermukaan(radius);
		luasPermukaan = luasPermukaanBola * (sudut / (2 * PI)) + PI * radius * radius;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda(){
		return "Juring Bola";
	}

}