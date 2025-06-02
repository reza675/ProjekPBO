package BendaGeometri;

public class PrismaPersegi extends Persegi{
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;


    public PrismaPersegi(double sisi, double tinggiPrisma) {
        super(sisi);
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaPersegi(int sisi, int tinggiPrisma) {
        super(sisi);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double sisi, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(sisi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(int sisi, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(sisi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double sisi, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(sisi);
        kelilingAlas = super.menghitungKeliling(tinggiPrisma);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;   
    }

    public double menghitungLuasPermukaan(int sisi, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(sisi);
        kelilingAlas = super.menghitungKeliling(tinggiPrisma);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;  
    }
    @Override
    public String getNamaBenda() {
        return "Prisma Persegi";
    }

}