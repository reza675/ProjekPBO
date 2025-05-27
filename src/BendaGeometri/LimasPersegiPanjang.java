package BendaGeometri;

public class LimasPersegiPanjang extends PersegiPanjang {

	private double tinggi;
	private double luasAlas;
	private double sisiMiringPanjang;
	private double sisiMiringLebar;
	private double luasSegitigaPanjang;
	private double luasSegitigaLebar;
	private double volume;
	private double luasPermukaan;

	public LimasPersegiPanjang() {
		super();
		this.tinggi = 0;
	}

	public LimasPersegiPanjang(double panjang, double lebar, double tinggi) {
		super(panjang, lebar);
		this.tinggi = tinggi;
	}

	public LimasPersegiPanjang(int panjang, int lebar, int tinggi) {
		super(panjang, lebar);
		this.tinggi = tinggi;
	}

	public double menghitungVolume() {
		luasAlas = menghitungLuas();
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(double panjang, double lebar, double tinggi) {
		luasAlas = menghitungLuas(panjang, lebar);
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungVolume(int panjang, int lebar, int tinggi) {
		luasAlas = menghitungLuas(panjang, lebar);
		volume = (1 / 3.0) * luasAlas * tinggi;
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasAlas = menghitungLuas();
		sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(super.lebar / 2, 2));
		sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(super.panjang / 2, 2));
		luasSegitigaPanjang = 0.5 * super.panjang * sisiMiringPanjang;
		luasSegitigaLebar = 0.5 * super.lebar * sisiMiringLebar;
		luasPermukaan = luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(double panjang, double lebar, double tinggi) {
		luasAlas = menghitungLuas(panjang, lebar);
		sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(lebar / 2, 2));
		sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(panjang / 2, 2));
		luasSegitigaPanjang = 0.5 * panjang * sisiMiringPanjang;
		luasSegitigaLebar = 0.5 * lebar * sisiMiringLebar;
		luasPermukaan = luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
		return luasPermukaan;
	}

	public double menghitungLuasPermukaan(int panjang, int lebar, int tinggi) {
		luasAlas = menghitungLuas(panjang, lebar);
		sisiMiringPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(lebar / 2, 2));
		sisiMiringLebar = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(panjang / 2, 2));
		luasSegitigaPanjang = 0.5 * panjang * sisiMiringPanjang;
		luasSegitigaLebar = 0.5 * lebar * sisiMiringLebar;
		luasPermukaan = luasAlas + 2 * luasSegitigaPanjang + 2 * luasSegitigaLebar;
		return luasPermukaan;
	}

	@Override
	public String getNamaBenda() {
		return "Limas Persegi Panjang";
	}
}