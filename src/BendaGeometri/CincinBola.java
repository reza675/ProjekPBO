package BendaGeometri;

public class CincinBola extends Bola {	
	private double tinggiCincin;
	private double volume;
	private double luasPermukaan;

	public CincinBola(double radius, double tinggiCincin) {
        super(radius);
		this.tinggiCincin = tinggiCincin;
                
	}

	public CincinBola(int radius, int tinggiCincin) {
        super(radius);
		this.tinggiCincin = tinggiCincin;
	}

	public double menghitungVolume() {
		double h = tinggiCincin;
        double r = super.radius;
        volume = PI * h * h * (r - h / 3.0);
        return volume;
	}
	
	public double menghitungVolume(double radius,double tinggiCincin) {
        volume = PI * tinggiCincin * tinggiCincin * (radius - tinggiCincin / 3.0);
        return volume;
	}
	public double menghitungVolume(int radius,int tinggiCincin) {
        volume = PI * tinggiCincin * tinggiCincin * (radius - tinggiCincin / 3.0);
        return volume;
	}
	
	public double menghitungLuasPermukaan() {
		double h = tinggiCincin;
        double r = super.radius;
        luasPermukaan = 2 * PI * r * h;
        return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double radius, double tinggiCincin) {
		return 2 * PI  * radius * tinggiCincin;
	}

	public double menghitungLuasPermukaan(int radius, int tinggiCincin) {
		return 2 * PI  * radius * tinggiCincin;
	}

	@Override
	public String getNamaBenda(){
		return "Cincin Bola";
	}

}