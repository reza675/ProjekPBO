package BendaGeometri;

public class JuringLingkaran extends Lingkaran {
    private double sudut;

    public JuringLingkaran() {
        super();
        this.sudut = 0;
    }

    public JuringLingkaran(double radius, double sudut) {
        super(radius);
        this.sudut = sudut;
    }

    public JuringLingkaran(int radius, int sudut) {
        super(radius);
        this.sudut = sudut;
    }
    @Override
    public double menghitungLuas() {
        luas = (sudut / 360.0) * super.menghitungLuas();
        return luas;
    }

    public double menghitungLuas(double radius, double sudut) {
        luas = (sudut / 360.0) * super.menghitungLuas(radius);
        return luas;
    }

    public double menghitungLuas(int radius, int sudut) {
        luas = (sudut / 360.0) * super.menghitungLuas(radius);
        return luas;
    }

    @Override
    public double menghitungKeliling() {
		double r = super.radius;
        double busur = super.menghitungKeliling() * (sudut / 360.0);
        keliling = (busur + 2 * r);
        return keliling;
    }

    public double menghitungKeliling(double radius, double sudut) {
        double busur = super.menghitungKeliling(radius) * (sudut / 360.0);
        keliling = busur + 2 * radius;
        return keliling;
    }

    public double menghitungKeliling(int radius, int sudut) {
        double busur = super.menghitungKeliling(radius) * (sudut / 360.0);
        keliling = busur + 2 * radius;
        return keliling;
    }
    @Override
    public String getNamaBenda() {
        return "Juring Lingkaran";
    }
}
