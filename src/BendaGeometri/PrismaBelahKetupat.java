package BendaGeometri;


public class PrismaBelahKetupat extends BelahKetupat {
    private double tinggi;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaBelahKetupat() {
        super();
        this.tinggi = 0;
    }

    public PrismaBelahKetupat(double diagonal1, double diagonal2, double sisi, double tinggi) {
        super(diagonal1, diagonal2, sisi);
        this.tinggi = tinggi;
    }

    public PrismaBelahKetupat(int diagonal1, int diagonal2, int sisi, int tinggi) {
        super(diagonal1, diagonal2, sisi);
        this.tinggi = tinggi;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggi;
        return volume;
    }

    public double menghitungVolume(double diagonal1, double diagonal2, double tinggi) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        volume = luasAlas * tinggi;
        return volume;
    }

    public double menghitungVolume(int diagonal1, int diagonal2, int tinggi) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        volume = luasAlas * tinggi;
        return volume;

    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggi;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double sisi, double tinggi) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        kelilingAlas = super.menghitungKeliling(sisi);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggi;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int sisi, int tinggi) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        kelilingAlas = super.menghitungKeliling(sisi);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggi;
        return luasPermukaan;
    }
     @Override
    public String getNamaBenda() {
        return "Prisma Belah Ketupat";
    }
}