package BendaGeometri;
public class BelahKetupat extends Benda2D {
	protected double diagonal1;
	protected double diagonal2;
	protected double sisi;

	public BelahKetupat() {
		this.diagonal1 = 0;
		this.diagonal2 = 0;
		this.sisi = 0;
	}
	public BelahKetupat(double diagonal1, double diagonal2, double sisi) {
		this.diagonal1 = diagonal1;
		this.diagonal2 = diagonal2;
		this.sisi = sisi;
	}

	public BelahKetupat(int diagonal1, int diagonal2, int sisi) {
		this.diagonal1 = diagonal1;
		this.diagonal2 = diagonal2;
		this.sisi = sisi;
	}
	@Override
	public double menghitungLuas() {
		luas = (diagonal1 * diagonal2) / 2;
		return luas;
	}
	public double menghitungLuas(double diagonal1, double diagonal2) {
		luas = (diagonal1 * diagonal2) / 2.0;
		return luas;
	}
	public double menghitungLuas(int diagonal1, int diagonal2) {
		luas = (diagonal1 * diagonal2) / 2.0;
		return luas;
  }
	@Override
	public double menghitungKeliling() {
		keliling = (4 * sisi);
		return keliling;
	}
	public double menghitungKeliling(double sisi) {
		keliling = (4 * sisi);
		return keliling;
	}
	public double menghitungKeliling(int sisi) {
		keliling = (4 * sisi);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Belah Ketupat";
	}
}
