package BendaGeometri;

import java.io.*;
import java.util.*;

public class PrismaTrapesium extends Trapesium implements IBenda3D {
    private double tinggiPrisma;
    public PrismaTrapesium() {
        super();
        this.tinggiPrisma = 0;
    }

    public PrismaTrapesium(Trapesium bendaAlas, double tinggiPrisma) {
        super(
            bendaAlas.getAlasAtas(), 
            bendaAlas.getAlasBawah(), 
            bendaAlas.getTinggi(), 
            bendaAlas.getSisiKiri(), 
            bendaAlas.getSisiKanan()
        );
        this.tinggiPrisma = tinggiPrisma;
    }
    public PrismaTrapesium(Trapesium bendaAlas, int tinggiPrisma) {
        this(bendaAlas, (double) tinggiPrisma);
    }

    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    @Override
    public double menghitungVolume() {
        return super.menghitungLuas() * tinggiPrisma;
    }

    @Override
    public double menghitungVolume(double[] params) {
        if (params.length >= 1) {
            return super.menghitungLuas() * params[0]; 
        }
        return 0.0;
    }

    @Override
    public double menghitungVolume(int[] params) {
        if (params.length >= 1) {
            return menghitungVolume(new double[] { params[0] });
        }
        return 0.0;
    }

    @Override
    public double menghitungLuasPermukaan() {
        return 2 * super.menghitungLuas() + super.menghitungKeliling() * tinggiPrisma;
    }

    @Override
    public double menghitungLuasPermukaan(double[] params) {
        if (params.length >= 1) {
            return 2 * super.menghitungLuas() + super.menghitungKeliling() * params[0];
        }
        return 0.0;
    }

    @Override
    public double menghitungLuasPermukaan(int[] params) {
        if (params.length >= 1) {
            return menghitungLuasPermukaan(new double[] { params[0] });
        }
        return 0.0;
    }

    @Override
    public void mencetakVolume() {
        System.out.printf("Volume Prisma Trapesium: %.2f\n", menghitungVolume());
    }

    // Cetak luas permukaan
    @Override
    public void mencetakLuasPermukaan() {
        System.out.printf("Luas Permukaan Prisma Trapesium: %.2f\n", menghitungLuasPermukaan());
    }
}