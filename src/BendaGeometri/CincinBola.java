package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class CincinBola extends Bola implements IBenda3D {

	/**
	 * Default constructor
	 */
	public CincinBola() {
            bolaDalam = new Bola();
            super();
	}

	/**
	 * 
	 */
	private double radiusCincin, volume, luas_permukaan;
        private Bola bolaDalam;

	/**
	 * @param radiusBola 
	 * @param radiusCincin
	 */
	public CincinBola(double radiusBola, double radiusCincin) {
		// TODO implement here
                super(radiusBola);
                this.radiusCincin = radiusCincin;
                
	}

	/**
	 * @param radiusBola 
	 * @param radiusCincin
	 */
	public CincinBola(int radiusBola, int radiusCincin) {
		// TODO implement here
                super(radiusBola);
                this.radiusCincin = radiusCincin;
                bolaDalam = new Bola(radiusCincin);
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungVolume() {
		this.volume =  (Math.PI*this.radiusCincin*this.radiusCincin)/3.0 * (3*super.getRadius() - this.radiusCincin);
                return this.volume;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungVolume(double radiusCincin) {
		// TODO implement here
		return (Math.PI*radiusCincin*radiusCincin)/3.0 * (3*super.getRadius() - radiusCincin);
	}
	
	public double menghitungVolume(double radiusCincin, double radiusBola) {
		// TODO implement here
		return (Math.PI*radiusCincin*radiusCincin)/3.0 * (3 * radiusBola - radiusCincin);
	}

	/**
	 * @return
	 */
	@Override
	public double menghitungLuasPermukaan() {
		
		this.luas_permukaan = 2 * Math.PI * super.getRadius() * this.radiusCincin;
                return this.luas_permukaan;
	}

	/**
	 * @param params 
	 * @return
	 */
	
	public double menghitungLuasPermukaan(double radiusCincin) {
		// TODO implement here
		return 2 * Math.PI * super.getRadius() * radiusCincin;
	}

	/**
	 * @param params 
	 * @return
	 */

	public double menghitungLuasPermukaan(double radiusCincin, double radiusBola) {
		// TODO implement here
		return 2 * Math.PI * radiusBola * radiusCincin;
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakVolume() {
		// TODO implement here
                System.out.println("Volume Cincin Bola: " + this.volume);
		
	}

	/**
	 * @return
	 */
	@Override
	public void mencetakLuasPermukaan() {
		// TODO implement here
		System.out.println("LP Cincin Bola: " + this.luas_permukaan);
	}

}