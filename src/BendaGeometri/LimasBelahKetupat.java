package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class LimasBelahKetupat extends BelahKetupat implements IBenda3D {

	/**
	 * Default constructor
	 */
	public LimasBelahKetupat() {
            super();
	}

	/**
	 * 
	 */
	private double tinggi;

	/**
	 * 
	 */

	/**
	 * @param bendaAlas 
	 * @param tinggi
	 */
	public LimasBelahKetupat(double d1, double d2, double sisi, double tinggi) {
		// TODO implement here
                super(d1, d2, sisi);
		this.tinggi = tinggi;
	}


	// Implementing menghitungVolume methods
	
	

	@Override
	public double menghitungVolume() {
		// Example implementation: Assuming tinggi and base area are already set
		super.menghitungLuas(); // Assuming BelahKetupat has a getLuas() method
		return (1.0 / 3.0) * super.menghitungLuas() * tinggi;
	}
        
        public double menghitungVolume(double luasAlas, double tinggi) {
            
                return (1.0 / 3.0) * luasAlas * tinggi;
	
	
	}
        public double menghitungVolume(double d1, double d2, double tinggi) {
                double luasAlas = super.menghitungLuas(d1, d2);
                return (1.0 / 3.0) * luasAlas * tinggi;
	}

	
	public double menghitungVolume(int luasAlas, int tinggi) {
            return (1.0 / 3.0) * luasAlas * tinggi;

	}

	// Implementing menghitungLuasPermukaan methods
	@Override
	public double menghitungLuasPermukaan() {
		// Example implementation: Assuming base area and lateral area are calculated
		double baseArea = super.getLuas(); // Assuming BelahKetupat has a getLuas() method
		double lateralArea = calculateLateralArea(); // Custom method to calculate lateral area
		return baseArea + lateralArea;
	}

	public double menghitungLuasPermukaan(double diagonal1, double diagonal2, double tinggi, double sisi) {
		// Example implementation: Assuming params[0] is base area and params[1] is lateral area
		double baseArea = super.menghitungLuas(diagonal1, diagonal2); // Assuming BelahKetupat has a getLuas() method
		double lateralArea = calculateLateralArea(diagonal1, diagonal2, sisi); // Custom method to calculate lateral area
		return baseArea + lateralArea;
	}

	
	public double menghitungLuasPermukaan(int[] params) {
		// Example implementation: Assuming params[0] is base area and params[1] is lateral area
		if (params.length >= 2) {
			return params[0] + params[1];
		}
		return 0;
	}

	// Implementing mencetakVolume method
	@Override
	public void mencetakVolume() {
		// TODO implement here
		System.out.println("Volume: " + menghitungVolume());
	}

	// Implementing mencetakLuasPermukaan method
	@Override
	public void mencetakLuasPermukaan() {
		// TODO implement her
		System.out.println("Luas Permukaan: " + menghitungLuasPermukaan());

	}

	// Example helper method to calculate lateral area
	private double calculateLateralArea() {
		// Get the side length of the rhombus (assuming BelahKetupat has a method to calculate it)
		double sideLength = super.getSisi(); // Assuming getSisi() returns the side length of the rhombus
	
		// Calculate the slant height (assuming tinggi is the perpendicular height of the pyramid)
		double diagonal1 = super.getDiagonal1(); // Assuming getDiagonal1() returns the first diagonal of the rhombus
		double diagonal2 = super.getDiagonal2(); // Assuming getDiagonal2() returns the second diagonal of the rhombus
		double slantHeight = Math.sqrt(Math.pow(tinggi, 2) + Math.pow((diagonal1 / 2), 2));
	
		// Calculate the area of one triangular face
		double triangleArea = 0.5 * sideLength * slantHeight;
	
		// Total lateral area (4 triangular faces)
		return 4 * triangleArea;
	}
        private double calculateLateralArea(double diagonal1, double diagonal2, double sisi) {
		// Get the side length of the rhombus (assuming BelahKetupat has a method to calculate it)
		double sideLength = sisi; // Assuming getSisi() returns the side length of the rhombus
	
		// Calculate the slant height (assuming tinggi is the perpendicular height of the pyramid)
		 // Assuming getDiagonal2() returns the second diagonal of the rhombus
		double slantHeight = Math.sqrt(Math.pow(tinggi, 2) + Math.pow((diagonal1 / 2), 2));
	
		// Calculate the area of one triangular face
		double triangleArea = 0.5 * sideLength * slantHeight;
	
		// Total lateral area (4 triangular faces)
		return 4 * triangleArea;
	}
}