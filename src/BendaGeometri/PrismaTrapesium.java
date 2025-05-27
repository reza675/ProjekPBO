package BendaGeometri;

public class PrismaTrapesium extends Trapesium {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaTrapesium() {
        super();
        this.tinggiPrisma = 0;
    }

    public PrismaTrapesium(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri, double sisiMiringKanan,
            double tinggiPrisma) {
        super(alasAtas, alasBawah, tinggiPrisma, sisiMiringKiri, sisiMiringKanan);
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaTrapesium(int alasAtas, int alasBawah, int tinggi, int sisiMiringKiri, int sisiMiringKanan, int tinggiPrisma) {
        super(alasAtas, alasBawah, tinggiPrisma, sisiMiringKiri, sisiMiringKanan);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double alasAtas, double alasBawah, double tinggi, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(alasAtas, alasBawah, tinggi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(int alasAtas, int alasBawah, int tinggi, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(alasAtas, alasBawah, tinggi);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double alasAtas, double alasBawah, double tinggi, double sisiMiringKiri,
            double sisiMiringKanan, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(alasAtas, alasBawah, tinggi);
        kelilingAlas = super.menghitungKeliling(alasAtas, alasBawah, sisiMiringKanan, sisiMiringKiri);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int alasAtas, int alasBawah, int sisiMiringKiri, int sisiMiringKanan, int tinggi,
            int tinggiPrisma) {
        luasAlas = super.menghitungLuas(alasAtas, alasBawah, tinggi);
        kelilingAlas = super.menghitungKeliling(alasAtas, alasBawah, sisiMiringKiri, sisiMiringKanan);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Trapesium";
    }

}