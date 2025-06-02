package BendaGeometri;

public class JuringBola extends Bola {

	private double sudut;
	private double volume;
	private double luasPermukaan;
	private double volumeBola;
	private double luasPermukaanBola;

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
		volume = volumeBola * (sudut / 360.0);
		return volume;
	}
	
	public double menghitungVolume(double sudut, double radius) {
		volumeBola = super.menghitungVolume(radius);
		volume = volumeBola * (sudut / 360.0);
		return volume;
	}

	public double menghitungVolume(int sudut, int radius) {
		volumeBola = super.menghitungVolume(radius);
		volume = volumeBola * (sudut / 360);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasPermukaanBola = super.menghitungLuasPermukaan();
		luasPermukaan = luasPermukaanBola * (sudut / 360.0); 
		return luasPermukaan;
	}
	
	public double menghitungLuasPermukaan(double sudut, double radius) {
		luasPermukaanBola = super.menghitungLuasPermukaan(radius);
		luasPermukaan = luasPermukaanBola * (sudut / 360.0);
		return luasPermukaan;
	}
	
	public double menghitungLuasPermukaan(int sudut, int radius) {
		luasPermukaanBola = super.menghitungLuasPermukaan(radius);
		luasPermukaan = luasPermukaanBola * (sudut / 360);
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda(){
		return "Juring Bola";
	}

}