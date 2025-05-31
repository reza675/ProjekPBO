package BendaGeometri;

public class Lingkaran extends Benda2D {

	protected double radius;
	protected final double PI = 3.14;

	public Lingkaran() {
		this.radius = 0;
	}

	public Lingkaran(double radius) {
		this.radius = radius;
	}

	public Lingkaran(int radius) {
		this.radius = radius;
	}

	@Override
	public double menghitungLuas() {
		luas = PI * radius * radius;
		return luas;
	}

	public double menghitungLuas(double radius) {
		luas = PI * radius * radius;
		return luas;
	}

	public double menghitungLuas(int radius) {
		luas = PI * radius * radius;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = 2 * PI * radius;
		return keliling;
	}
	public double menghitungKeliling(double radius) {
		keliling = 2 * PI * radius;
		return keliling;
	}

	public double menghitungKeliling(int radius) {
		keliling = 2 * PI * radius;
		return keliling;
	}
	@Override
	public String getNamaBenda() {
		return "Lingkaran";
	}
}
