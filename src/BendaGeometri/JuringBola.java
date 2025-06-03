package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JuringBola extends Bola {

	private double sudutBola;
	private double volume;
	private double luasPermukaan;
	private double volumeBola;
	private double luasPermukaanBola;

	public JuringBola(double radius, double sudutBola) {
        super(radius);
		this.sudutBola = sudutBola;
	}

	public double menghitungVolume() {
		volumeBola = super.menghitungVolume();
		volume = volumeBola * (sudutBola / 360.0);
		return volume;
	}
	
	public double menghitungVolume(double sudutBolaBaru, double radiusBolaBaru) {
		volumeBola = super.menghitungVolume(radiusBolaBaru);
		volume = volumeBola * (sudutBolaBaru / 360.0);
		return volume;
	}

	public double menghitungLuasPermukaan() {
		luasPermukaanBola = super.menghitungLuasPermukaan();
		luasPermukaan = luasPermukaanBola * (sudutBola / 360.0); 
		return luasPermukaan;
	}
	
	public double menghitungLuasPermukaan(double sudutBolaBaru, double radiusBolaBaru) {
		luasPermukaanBola = super.menghitungLuasPermukaan(radiusBolaBaru);
		luasPermukaan = luasPermukaanBola * (sudutBolaBaru / 360.0);
		return luasPermukaan;
	}
	
	@Override
	public String getNamaBenda(){
		return "Juring Bola";
	}
	public void prosesInputDataUlang() {
    Scanner inputData = new Scanner(System.in);
    while (true) {
        System.out.print("\nApakah Anda ingin mengubah nilai jari-jari dan sudut juring bola? (Y/N): ");
        String jawaban = inputData.nextLine();
        if (jawaban.equalsIgnoreCase("Y")) {
            while (true) {
                try {
                    System.out.print("Masukkan jari-jari bola: ");
                    double radiusBolaBaru = inputData.nextDouble();
                    System.out.print("Masukkan besar sudut juring (derajat): ");
                    double sudutBolaBaru = inputData.nextDouble();
                    inputData.nextLine();

                    if (radiusBolaBaru <= 0 || sudutBolaBaru <= 0 || sudutBolaBaru > 360) {
                        System.out.println("Nilai tidak valid. Jari-jari dan sudut harus lebih dari 0 dan sudut maksimal 360.\n");
                        continue;
                    }

                    volume = menghitungVolume(sudutBolaBaru, radiusBolaBaru);
                    luasPermukaan = menghitungLuasPermukaan(sudutBolaBaru, radiusBolaBaru);

                    System.out.printf("\nVolume Juring Bola: %.2f\n", volume);
                    System.out.printf("Luas Permukaan Juring Bola: %.2f\n", luasPermukaan);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input harus berupa angka.");
                    inputData.nextLine();
                }
            }
            break;
        } else if (jawaban.equalsIgnoreCase("N")) {
            volume = menghitungVolume();
            luasPermukaan = menghitungLuasPermukaan();
            break;
        } else {
            System.out.println("Jawaban hanya boleh Y atau N.\n");
        }
    }
}

}