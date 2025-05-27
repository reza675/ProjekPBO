package BendaGeometri;

public class PrismaPersegi extends Persegi{
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    public PrismaPersegi() {
        super();
        this.tinggi = 0;
    }

    public PrismaPersegi(double sisi, double tinggi) {
        super(sisi);
        this.tinggi = tinggi;
    }

    public PrismaPersegi(int sisi, int tinggi) {
        super(sisi);
        this.tinggi = tinggi;
    }

    @Override
    public double menghitungVolume() {
        double luasAlas = menghitungLuas();
        return luasAlas * tinggi;
    }

    public double menghitungVolume(double sisi, double tinggi) {
        double luasAlas = super.menghitungLuas(sisi);
        return luasAlas * tinggi;
    }

    public double menghitungVolume(int sisi, int tinggi) {
        double luasAlas = super.menghitungLuas(sisi);
        return luasAlas * tinggi;
    }

    @Override
    public double menghitungLuasPermukaan() {
        double luasAlas = menghitungLuas();
        double kelilingAlas = menghitungKeliling();
        return 2 * luasAlas * kelilingAlas * tinggi;
    }

    public double menghitungLuasPermukaan(double sisi, double tinggi) {
        double luasAlas = menghitungLuas(sisi);
        double kelilingAlas = menghitungKeliling(tinggi);
        return 2 * luasAlas * kelilingAlas * tinggi;    
    }

    public double menghitungLuasPermukaan(int sisi, int tinggi) {
        double luasAlas = menghitungLuas(sisi);
        double kelilingAlas = menghitungKeliling(tinggi);
        return 2 * luasAlas * kelilingAlas * tinggi;    
    }

    @Override
    public void mencetakVolume() {
        System.out.printf("Volume Prisma Persegi: %.2f\n", menghitungVolume());
    }

    @Override
    public void mencetakLuasPermukaan() {
        System.out.printf("Luas Permukaan Prisma Persegi: %.2f\n", menghitungLuasPermukaan());
    }
}