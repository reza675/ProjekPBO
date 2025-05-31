package BendaGeometri;

public class CincinBola extends Bola {

	private double radiusCincin;
	private double volume;
	private double luas_permukaan;
	private Bola bolaDalam;
	protected final double PI = 3.14; // valent : duh pake ini ga ya

	public CincinBola() {
		super();
        bolaDalam = new Bola();
	}


	public CincinBola(double radiusBola, double radiusCincin) {
        super(radiusBola);
        this.radiusCincin = radiusCincin;
                
	}

	public CincinBola(int radiusBola, int radiusCincin) {
        super(radiusBola);
        this.radiusCincin = radiusCincin;
        bolaDalam = new Bola(radiusCincin);
	}

	public double menghitungVolume() {
		this.volume =  (PI *this.radiusCincin*this.radiusCincin)/3.0 * (3*super.getRadius() - this.radiusCincin);
        return this.volume;
	}
	
	public double menghitungVolume(double radiusCincin) {
		return (PI *radiusCincin*radiusCincin)/3.0 * (3*super.getRadius() - radiusCincin);
	}
	
	public double menghitungVolume(double radiusCincin, double radiusBola) {
		return (PI *radiusCincin*radiusCincin)/3.0 * (3 * radiusBola - radiusCincin);
	}

	public double menghitungLuasPermukaan() {
		
		this.luas_permukaan = 2 * PI  * super.getRadius() * this.radiusCincin;
        return this.luas_permukaan;
	}

	public double menghitungLuasPermukaan(double radiusCincin) {
		return 2 * PI  * super.getRadius() * radiusCincin;
	}

	public double menghitungLuasPermukaan(double radiusCincin, double radiusBola) {
		return 2 * PI  * radiusBola * radiusCincin;
	}

	@Override
	public String getNamaBenda(){
		return "Cincin Bola";
	}

}