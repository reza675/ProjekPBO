package BendaGeometri;

public class PrismaJajaranGenjang extends JajaranGenjang {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaJajaranGenjang(double panjangAlas, double tinggiAlas, double sisiMiringAlas, double tinggiPrisma) {
        super(panjangAlas, tinggiAlas, sisiMiringAlas);
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaJajaranGenjang(int panjangAlas, int tinggiAlas, int sisiMiringAlas, int tinggiPrisma) {
        super(panjangAlas, tinggiAlas, sisiMiringAlas);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double panjangAlas, double tinggiAlas, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(int panjangAlas, int tinggiAlas, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double panjangAlas, double tinggiAlas, double sisiMiringAlas,
            double tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        kelilingAlas = super.menghitungKeliling(panjangAlas, sisiMiringAlas);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int panjangAlas, int tinggiAlas, int sisiMiringAlas, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjangAlas, tinggiAlas);
        kelilingAlas = super.menghitungKeliling(panjangAlas, sisiMiringAlas);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }
     @Override
    public String getNamaBenda() {
        return "Prisma Jajaran Genjang";
    }
}