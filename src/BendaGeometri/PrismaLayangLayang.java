package BendaGeometri;

public class PrismaLayangLayang extends LayangLayang {
    private double tinggi;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;

    public PrismaLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang,
            double tinggi) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        this.tinggi = tinggi;
    }

    public PrismaLayangLayang(int diagonal1, int diagonal2, int sisiPendek, int sisiPanjang, int tinggi) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        this.tinggi = tinggi;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggi;
        return volume;
    }

    public double menghitungVolume(double diagonal1, double diagonal2) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        volume = luasAlas * tinggi;
        return volume;
    }

    public double menghitungVolume(int diagonal1, int diagonal2) {
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

    public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double sisi_pendek, double sisi_panjang) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        kelilingAlas = super.menghitungKeliling(sisi_pendek, sisi_panjang);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggi;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int sisi_pendek, int sisi_panjang) {
        luasAlas = super.menghitungLuas(diagonal1, diagonal2);
        kelilingAlas = super.menghitungKeliling(sisi_pendek, sisi_panjang);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggi;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Layang-Layang";
    }

}