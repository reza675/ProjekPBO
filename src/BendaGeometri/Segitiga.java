package BendaGeometri;

public class Segitiga extends Benda2D {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring1;
	protected double sisiMiring2;

	public Segitiga() {
		this.alas = 0;
		this.tinggi = 0;
		this.sisiMiring1 = 0;
		this.sisiMiring2 = 0;
	}

	public Segitiga(double alas, double tinggi, double sisiMiring1, double sisiMiring2) {
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring1 = sisiMiring1;
		this.sisiMiring2 = sisiMiring2;
	}

	public Segitiga(int alas, int tinggi, int sisiMiring1, int sisiMiring2) {
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring1 = sisiMiring1;
		this.sisiMiring2 = sisiMiring2;
	}

	@Override
	public double menghitungLuas() {
		luas = (0.5 * alas * tinggi);
		return luas;
	}
	public double menghitungLuas(double alas, double tinggi) {
		luas = 0.5 * alas * tinggi;
		return luas;

	}

	public double menghitungLuas(int alas, int tinggi) {
		luas = 0.5 * alas * tinggi;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = (alas + sisiMiring1 + sisiMiring2);
		return keliling;
	}

	public double menghitungKeliling(double alas, double sisiMiring1, double sisiMiring2) {
		keliling = alas + sisiMiring1 + sisiMiring2;
		return keliling;
	}

	public double menghitungKeliling(int alas, int sisiMiring1, int sisiMiring2) {
		keliling = alas + sisiMiring1 + sisiMiring2;
		return keliling;
	}
	@Override
	public String getNamaBenda() {
		return "Segitiga";
	}
}
