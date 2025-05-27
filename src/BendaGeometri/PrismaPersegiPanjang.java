package BendaGeometri;

public class PrismaPersegiPanjang extends PersegiPanjang {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaPersegiPanjang() {
        super();
        this.tinggiPrisma = 0;
    }

    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) {
        super(panjang, lebar);
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaPersegiPanjang(int panjang, int lebar, int tinggiPrisma) {
        super(panjang, lebar);
        this.tinggiPrisma = tinggiPrisma;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double panjang, double lebar, double tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjang, lebar);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(int panjang, int lebar, int tinggiPrisma) {
        luasAlas = super.menghitungLuas(panjang, lebar);
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double panjang, double lebar, double tinggiPrisma) {
        this.tinggiPrisma = tinggiPrisma;
        luasAlas = super.menghitungLuas(panjang, lebar);
        kelilingAlas = super.menghitungKeliling(panjang, lebar);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int panjang, int lebar, int tinggiPrisma) {
         this.tinggiPrisma = tinggiPrisma;
        luasAlas = super.menghitungLuas(panjang, lebar);
        kelilingAlas = super.menghitungKeliling(panjang, lebar);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }
    @Override
    public String getNamaBenda() {
        return "Prisma Persegi Panjang";
    }
}