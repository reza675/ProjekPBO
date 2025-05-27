package BendaGeometri;

public class Trapesium extends Benda2D {

    protected double alasAtas;
    protected double alasBawah;
    protected double tinggi;
    protected double sisiMiringKiri;
    protected double sisiMiringKanan;
    
    public Trapesium() {
        this.alasAtas = 0;
        this.alasBawah = 0;
        this.tinggi = 0;
        this.sisiMiringKiri = 0;
        this.sisiMiringKanan = 0;
    }

    public Trapesium(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri, double sisiMiringKanan) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.sisiMiringKiri = sisiMiringKiri;
        this.sisiMiringKanan = sisiMiringKanan;
    }

    public Trapesium(int alasAtas, int alasBawah, int tinggi, int sisiMiringKiri, int sisiMiringKanan) {
        this.alasAtas = alasAtas;
        this.alasBawah = alasBawah;
        this.tinggi = tinggi;
        this.sisiMiringKiri = sisiMiringKiri;
        this.sisiMiringKanan = sisiMiringKanan;
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
        keliling = (alasAtas + alasBawah + sisiMiringKiri + sisiMiringKanan);
        return keliling;
    }

    public double menghitungKeliling(double alasAtas, double alasBawah, double sisiMiringKanan, double sisiMiringKiri) {
        keliling = alasAtas + alasBawah + sisiMiringKanan + sisiMiringKiri;
        return keliling;
    }

    public double menghitungKeliling(int alasAtas, int alasBawah, int sisiMiringKanan, int sisiMiringKiri) {
        keliling = alasAtas + alasBawah + sisiMiringKanan + sisiMiringKiri;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Trapesium";
    }
    
}
