package BendaGeometri;

public class KerucutTerpancung extends Kerucut {

	private double radiusAtas;
	private double tinggiTerpancung;
	private double volume;
	private double luasPermukaan;

	public KerucutTerpancung() {
		super();
		this.radiusAtas = 0.0;
	}

	public KerucutTerpancung(double radiusAtas, double radiusBawah, double tinggiTerpancung) {
		super(radiusBawah, menghitungTinggiKerucutUtuh(radiusBawah, radiusAtas, tinggiTerpancung));
		this.radiusAtas = radiusAtas;
		this.tinggiTerpancung = tinggiTerpancung;
	}
	private static double menghitungTinggiKerucutUtuh(double radiusBawah, double radiusAtas, double tinggiTerpancung) {
        return (radiusBawah * tinggiTerpancung) / (radiusBawah - radiusAtas);
    }

	@Override
	public double menghitungVolume() {
		double R = super.radius;      
        double r = this.radiusAtas;    
        double h = this.tinggiTerpancung;
        volume = (1.0 / 3.0) * super.PI * h * (R * R + R * r + r * r);
        return volume;
	}

	public double menghitungVolume(double radiusAtas, double radiusBawah, double tinggi) {
		double R = radiusBawah;
        double r = radiusAtas;
        double h = tinggi;
        return (1.0 / 3.0) * super.PI * h * (R * R + R * r + r * r);
	}

	@Override
    public double menghitungLuasPermukaan() {
        double R = super.radius;
        double r = this.radiusAtas;
        double h = this.tinggiTerpancung;
        double luasBawah = super.menghitungLuas(R);
        double luasAtas = super.menghitungLuas(r);

        double selisihRadius = R - r;
        double s = Math.sqrt(h * h + selisihRadius * selisihRadius);
        double luasSelimut = super.PI * (R + r) * s;

        luasPermukaan = luasBawah + luasAtas + luasSelimut;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double radiusAtas, double radiusBawah, double tinggiTerpancung) {
        double luasBawah = super.menghitungLuas(radiusBawah);
        double luasAtas = super.menghitungLuas(radiusAtas);
        double selisihRadius = radiusBawah - radiusAtas;
        double s = Math.sqrt(tinggiTerpancung * tinggiTerpancung + selisihRadius * selisihRadius);
        double luasSelimut = super.PI * (radiusBawah + radiusAtas) * s;
        luasPermukaan = luasBawah + luasAtas + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Kerucut Terpancung";
    }
}
