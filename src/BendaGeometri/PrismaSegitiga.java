package BendaGeometri;

public class PrismaSegitiga extends Segitiga {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaSegitiga(double alas, double tinggi, double sisiMiring1, double sisiMiring2, double tinggiPrisma) {
        super(alas, tinggiPrisma, sisiMiring1, sisiMiring2);
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaSegitiga(int alas, int tinggi, int sisiMiring1, int sisiMiring2, int tinggiPrisma) {
        super(alas, tinggiPrisma, sisiMiring1, sisiMiring2);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double alas, double tinggi, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(alas, tinggi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(int alas, int tinggi, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(alas, tinggi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double alas, double tinggi, double sisiMiring1, double sisiMiring2,
            double tinggiPrisma) {
        luasAlas = super.menghitungLuas(alas, tinggi);
        kelilingAlas = super.menghitungKeliling(alas, sisiMiring1, sisiMiring1);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int alas, int tinggi, int sisiMiring1, int sisiMiring2, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(alas, tinggi);
        kelilingAlas = super.menghitungKeliling(alas, sisiMiring1, sisiMiring1);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }
    @Override
    public String getNamaBenda() {
        return "Prisma Segitiga";
    }
}