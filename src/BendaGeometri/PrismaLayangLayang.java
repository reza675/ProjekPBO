package BendaGeometri;

public class PrismaLayangLayang extends LayangLayang implements IBenda3D {

    private double tinggi;

    public PrismaLayangLayang(double diagonal1, double diagonal2,double sisiPendek, double sisiPanjang,double tinggi) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        this.tinggi = tinggi;
    }

    public PrismaLayangLayang(int diagonal1, int diagonal2,int sisiPendek, int sisiPanjang,int tinggi) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        this.tinggi = tinggi;
    }


    public double menghitungVolume(double diagonal1, double diagonal2) {
        double volume = super.getLuas() * tinggi;
        return volume;
    }

    @Override
    public double menghitungLuasPermukaan() {
        return 2 * super.getLuas() + super.getKeliling() * tinggi;
    }

    public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double sisi_pendek, double sisi_panjang) {
        double luasBendaDasar = (diagonal1 * diagonal2) / 2;
        double luasSisiPendek = (sisi_pendek * tinggi);
        double luasSisiPanjang = (sisi_panjang * tinggi);

        return 2 * luasBendaDasar + 2 * luasSisiPendek + 2 * luasSisiPanjang;
    }

    public double menghitungVolume(int diagonal1, int diagonal2) {
        double luasAlas = (diagonal1 * diagonal2) / 2;
        return luasAlas * tinggi;
    }

    @Override
    public double menghitungVolume() {
        // TODO: Implement logic
        return super.getLuas() * tinggi;
    }

    public double menghitungLuasPermukaan(int diagonal1, int diagonal2, int sisi_pendek, int sisi_panjang) {
        // TODO: Implement logic

        double luasBendaDasar = (diagonal1 * diagonal2) / 2;
        double luasSisiPendek = (sisi_pendek * tinggi);
        double luasSisiPanjang = (sisi_panjang * tinggi);

        return 2 * luasBendaDasar + 2 * luasSisiPendek + 2 * luasSisiPanjang;

    }

    @Override
    public void mencetakVolume() {
        System.out.println("Volume: " + menghitungVolume());
    }

    @Override
    public void mencetakLuasPermukaan() {
        System.out.println("Luas Permukaan: " + menghitungLuasPermukaan());
    }
}