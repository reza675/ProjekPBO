package BendaGeometri;

public class JajaranGenjang extends Benda2D {

	protected double alas;
	protected double tinggi;
	protected double sisiMiring;

	public JajaranGenjang() {
		this.alas = 0;
		this.tinggi = 0;
		this.sisiMiring = 0;
	}

	public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring = sisiMiring;
	}

	public JajaranGenjang(int alas, int tinggi, int sisiMiring) {
		this.alas = alas;
		this.tinggi = tinggi;
		this.sisiMiring = sisiMiring;
	}
	@Override
	public double menghitungLuas() {
		luas =(alas * tinggi);
		return luas;
	}

	public double menghitungLuas(double alas, double tinggi) {
		luas = alas * tinggi;
		return luas;
	}

	public double menghitungLuas(int alas, int tinggi) {
		luas = alas * tinggi;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * (alas + sisiMiring);
		return keliling;
	}
	
	public double menghitungKeliling(double alas, double sisiMiring) {
		keliling = 2 * (alas + sisiMiring);
		return keliling;
	}

	public double menghitungKeliling(int alas, int sisiMiring) {
		keliling = 2 * (alas + sisiMiring);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Jajaran Genjang";
	}
}
