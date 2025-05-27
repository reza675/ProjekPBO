package BendaGeometri;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class PrismaJajaranGenjang extends JajaranGenjang implements IBenda3D {

    /**
     * Default constructor
     */
    public PrismaJajaranGenjang() {
        super();
        this.tinggiPrisma = 0;
    }

    /**
     * 
     */
    private double tinggiPrisma;

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param sisi_miring_alas
     * @param tinggiPrisma
     */
    public PrismaJajaranGenjang(double panjang_alas, double tinggi_alas, double sisi_miring_alas, double tinggiPrisma) {
        super(panjang_alas, tinggi_alas, sisi_miring_alas);
        this.tinggiPrisma = tinggiPrisma;
    }

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param sisi_miring_alas
     * @param tinggiPrisma
     */
    public PrismaJajaranGenjang(int panjang_alas, int tinggi_alas, int sisi_miring_alas, int tinggiPrisma) {
        super(panjang_alas, tinggi_alas, sisi_miring_alas);
        this.tinggiPrisma = tinggiPrisma;
    }

    /**
     * @return
     */
    @Override
    public double menghitungVolume() {
        return super.menghitungLuas() * tinggiPrisma;
    }

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param tinggiPrisma
     * @return
     */
    public double menghitungVolume(double panjang_alas, double tinggi_alas, double tinggiPrisma) {
        double luasAlas = super.menghitungLuas(panjang_alas, tinggi_alas);
        return luasAlas * tinggiPrisma;
    }

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param tinggiPrisma
     * @return
     */
    public double menghitungVolume(int panjang_alas, int tinggi_alas, int tinggiPrisma) {
        double luasAlas = super.menghitungLuas(panjang_alas, tinggi_alas);
        return luasAlas * tinggiPrisma;
    }

    /**
     * @return
     */
    @Override
    public double menghitungLuasPermukaan() {
        float luasAlas = super.menghitungLuas();
        float keliling = super.menghitungKeliling();
        return 2 * luasAlas + keliling * tinggiPrisma;
    }

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param sisi_miring_alas
     * @param tinggiPrisma
     * @return
     */
    public double menghitungLuasPermukaan(double panjang_alas, double tinggi_alas, double sisi_miring_alas, double tinggiPrisma) {
        double luasAlas = super.menghitungLuas(panjang_alas, tinggi_alas);
        double keliling = super.menghitungKeliling(panjang_alas, sisi_miring_alas);
        return 2 * luasAlas + keliling * tinggiPrisma;
    }

    /**
     * @param panjang_alas
     * @param tinggi_alas
     * @param sisi_miring_alas
     * @param tinggiPrisma
     * @return
     */
    public double menghitungLuasPermukaan(int panjang_alas, int tinggi_alas, int sisi_miring_alas, int tinggiPrisma) {
        double luasAlas = super.menghitungLuas(panjang_alas, tinggi_alas);
        double keliling = super.menghitungKeliling(panjang_alas, sisi_miring_alas);
        return 2 * luasAlas + keliling * tinggiPrisma;
    }

    /**
     * @return
     */
    @Override
    public void mencetakVolume() {
        System.out.printf("Volume Prisma Jajaran Genjang: %.2f\n", menghitungVolume());
    }

    /**
     * @return
     */
    @Override
    public void mencetakLuasPermukaan() {
        System.out.printf("Luas Permukaan Prisma Jajaran Genjang: %.2f\n", menghitungLuasPermukaan());
    }
}