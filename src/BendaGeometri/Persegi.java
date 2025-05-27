package BendaGeometri;

public class Persegi extends Benda2D {
	protected double sisi;

	public Persegi() {
		this.sisi = 0;
	}

	public Persegi(double sisi) {
		this.sisi = sisi;
	}

	public Persegi(int sisi) {
		this.sisi = sisi;

	}

	@Override
	public double menghitungLuas() {
		luas = (sisi * sisi);
		return luas;
	}

	public double menghitungLuas(double sisi) {
		luas = sisi * sisi;
		return luas;
	}

	public double menghitungLuas(int sisi) {
		luas = sisi * sisi;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = (4 * this.sisi);
		return keliling;
	}

	public double menghitungKeliling(double sisi) {
		keliling = 4 * sisi;
		return keliling;
	}

	public double menghitungKeliling(int sisi) {
		keliling = 4 * sisi;
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Persegi";
	}
}
