package BendaGeometri;

public class LayangLayang extends Benda2D {

	protected double diagonal1;
	protected double diagonal2;
	protected double sisiPendek;
	protected double sisiPanjang;

	public LayangLayang() {
		this.diagonal1 = 0;
		this.diagonal2 = 0;
		this.sisiPendek = 0;
		this.sisiPanjang = 0;
	}

	public LayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang) {
		this.diagonal1 = diagonal1;
		this.diagonal2 = diagonal2;
		this.sisiPendek = sisiPendek;
		this.sisiPanjang = sisiPanjang;
	}

	public LayangLayang(int diagonal1, int diagonal2, int sisiPendek, int sisiPanjang) {
		this.diagonal1 = diagonal1;
		this.diagonal2 = diagonal2;
		this.sisiPendek = sisiPendek;
		this.sisiPanjang = sisiPanjang;
	}

	@Override
	public double menghitungLuas() {
		luas = (diagonal1 * diagonal2) / 2.0;
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
		keliling = 2 * (sisiPendek + sisiPanjang);
		return keliling;
	}	

	public double menghitungKeliling(int sisiPendek, int sisiPanjang) {
		keliling = 2 * (sisiPendek + sisiPanjang);
		return keliling;
	}
	public double menghitungKeliling(double sisiPendek, double sisiPanjang) {
		keliling = 2 * (sisiPendek + sisiPanjang);
		return keliling;
	}

	@Override
	public String getNamaBenda() {
		return "Layang-Layang";
	}
}
