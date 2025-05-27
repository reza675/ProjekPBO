package BendaGeometri;
public class PersegiPanjang extends Benda2D {
	protected double panjang;
	protected double lebar;

	public PersegiPanjang() {
		this.panjang = 0;
		this.lebar = 0;
	}

	public PersegiPanjang(double panjang, double lebar) {
		this.panjang = panjang;
		this.lebar = lebar;
	}

	public PersegiPanjang(int panjang, double lebar) {
		this.panjang = panjang;
		this.lebar = lebar;
	}

	public PersegiPanjang(double panjang, int lebar) {
		this.panjang = panjang;
		this.lebar = lebar;
	}

	public PersegiPanjang(int panjang, int lebar) {
		this.panjang = panjang;
		this.lebar = lebar;
	}

	@Override
	public double menghitungLuas() {
		luas = (panjang * lebar);
		return luas;
	}

	public double menghitungLuas(double panjang, double lebar) {
		luas = panjang * lebar;
		return luas;
	}

	public double menghitungLuas(int panjang, int lebar) {
		luas = panjang * lebar;
		return luas;
	}

	@Override
	public double menghitungKeliling() {
		keliling = (2 * (panjang + lebar));
		return keliling;
	}

	public double menghitungKeliling(double panjang, double lebar) {
		keliling = 2 * (panjang + lebar);
		return keliling;
	}

	public double menghitungKeliling(int panjang, int lebar) {
		keliling = 2 * (panjang + lebar);
		return keliling;
	}
	@Override
	public String getNamaBenda() {
		return "Persegi Panjang";
	}
}
