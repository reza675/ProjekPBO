package BendaGeometri;

import java.io.*;
import java.util.*;

public class PrismaPersegiPanjang extends PersegiPanjang implements IBenda3D {
    private double tinggiPrisma;

    public PrismaPersegiPanjang() {
        super();
        this.tinggiPrisma = 0;
    }

    public PrismaPersegiPanjang(PersegiPanjang bendaAlas, double tinggiPrisma) {
        super(bendaAlas.getPanjang(), bendaAlas.getLebar());
        this.tinggiPrisma = tinggiPrisma;
    }

    public PrismaPersegiPanjang(PersegiPanjang bendaAlas, int tinggiPrisma) {
        this(bendaAlas, (double) tinggiPrisma);
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
        System.out.printf("Volume Prisma Persegi Panjang: %.2f\n", menghitungVolume());
    }

    @Override
    public void mencetakLuasPermukaan() {
        System.out.printf("Luas Permukaan Prisma Persegi Panjang: %.2f\n", menghitungLuasPermukaan());
    }
}