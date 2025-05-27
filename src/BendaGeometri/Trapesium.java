package BendaGeometri;

public class Trapesium extends Benda2D {

    private double alasAtas;
    private double alasBawah;
    private double tinggi;
    private double sisiKiri;
    private double sisiKanan;

    public Trapesium() {
        this.alasAtas = 0;
        this.alasBawah = 0;
        this.tinggi = 0;
        this.sisiKiri = 0;
        this.sisiKanan = 0;
    }

    public Trapesium(double alasAtas, double alasBawah, double tinggi, double sisiKiri, double sisiKanan) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    public Trapesium(int alasAtas, int alasBawah, int tinggi, int sisiKiri, int sisiKanan) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    public double menghitungLuas(double alasAtas, double alasBawah, double tinggi) {
        luas = 0.5 * (alasAtas + alasBawah) * tinggi;
        return luas;
    }

    public double menghitungLuas(int alasAtas, int alasBawah, int tinggi) {
        luas = 0.5 * (alasAtas + alasBawah) * tinggi;
        return luas;
    }

    @Override
    public double menghitungLuas() {
        luas = (0.5 * (alasAtas + alasBawah) * tinggi);
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        keliling = (alasAtas + alasBawah + sisiKiri + sisiKanan);
        return keliling;
    }

    public double menghitungKeliling(double alasAtas, double alasBawah, double sisiKanan, double sisiKiri) {
        keliling = alasAtas + alasBawah + sisiKanan + sisiKiri;
        return keliling;
    }

    public double menghitungKeliling(int alasAtas, int alasBawah, int sisiKanan, int sisiKiri) {
        keliling = alasAtas + alasBawah + sisiKanan + sisiKiri;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Trapesium";
    }
    
}
