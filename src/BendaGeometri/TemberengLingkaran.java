package BendaGeometri;

public class TemberengLingkaran extends Lingkaran {
    private double sudut;

    public TemberengLingkaran() {
        super();
        this.sudut = 0;
    }

    public TemberengLingkaran(double radius, double sudut) {
        super(radius);
        this.sudut = sudut;
    }

    public TemberengLingkaran(int radius, int sudut) {
        super(radius);
        this.sudut = sudut;
    }

    @Override
    public double menghitungLuas() {
        double sudutRadian = Math.toRadians(sudut);
        double luasJuringLingkaran = (sudut / 360.0) * super.PI * super.radius * super.radius;
        double luasSegitiga = 0.5 * super.radius * super.radius * Math.sin(sudutRadian);
        luas = luasJuringLingkaran - luasSegitiga;
        return luas;
    }

    public double menghitungLuas(double radius, double sudut) {
        double sudutRadian = Math.toRadians(sudut);
        double luasJuringLingkaran = (sudut / 360.0) * super.PI * super.radius * super.radius;
        double luasSegitiga = 0.5 * super.radius * super.radius * Math.sin(sudutRadian);
        luas = luasJuringLingkaran - luasSegitiga;
        return luas;
    }

    public double menghitungLuas(int radius, int sudut) {
        double sudutRadian = Math.toRadians(sudut);
        double luasJuringLingkaran = (sudut / 360.0) * super.PI * super.radius * super.radius;
        double luasSegitiga = 0.5 * super.radius * super.radius * Math.sin(sudutRadian);
        luas = luasJuringLingkaran - luasSegitiga;
        return luas;
    }

    @Override
    public double menghitungKeliling() {
        double sudutRadian = Math.toRadians(sudut);
        double panjangBusur = sudutRadian * super.radius;
        double taliBusur = 2 * super.radius * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    public double menghitungKeliling(double radius, double sudut) {
        double sudutRadian = Math.toRadians(sudut);
        double panjangBusur = sudutRadian * super.radius;
        double taliBusur = 2 * super.radius * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    public double menghitungKeliling(int radius, int sudut) {
        double sudutRadian = Math.toRadians(sudut);
        double panjangBusur = sudutRadian * super.radius;
        double taliBusur = 2 * super.radius * Math.sin(sudutRadian / 2);
        keliling = panjangBusur + taliBusur;
        return keliling;
    }

    @Override
    public String getNamaBenda() {
        return "Tembereng Lingkaran";
    }
}
