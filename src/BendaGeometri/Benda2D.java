package BendaGeometri;

public abstract class Benda2D implements BendaGeometri {
	protected double luas, keliling;
	protected String namaBenda;
	
	public Benda2D() {}
	@Override
	public abstract double menghitungLuas();
	@Override
	public abstract double menghitungKeliling();
	public abstract String getNamaBenda();
	
}