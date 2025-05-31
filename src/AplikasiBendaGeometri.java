/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import BendaGeometri.*;

public class AplikasiBendaGeometri {
    public static void main(String[] args) {
        // PrismaPersegiPanjang prisma = new PrismaPersegiPanjang(5, 4, 3);
        // System.out.println("Luas Permukaan Prisma = " + prisma.menghitungLuasPermukaan(5,5,5));
        // System.out.println("Volume Prisma        = " + prisma.menghitungVolume(5,5,5));
        
        // LimasSegitiga ls = new LimasSegitiga(10, 20, 30, 10, 50);
        // System.out.println("Volume Limas Segitiga = " + ls.menghitungVolume(5,5,5));
        // System.out.println("Luas Permukaan Limas Segitiga = " + ls.menghitungLuasPermukaan(5,5,5,5,5));

        // LimasJajaranGenjang lj = new LimasJajaranGenjang(10, 20, 30, 10);
        // System.out.println("Volume Limas Jajaran Genjang = " + lj.menghitungVolume());
        // System.out.println("Luas Permukaan Limas Jajaran Genjang = " + lj.menghitungLuasPermukaan());

        Bola bola = new Bola(10);
        System.out.println("Luas Permukaan Bola = " + bola.menghitungLuasPermukaan(10));
        System.out.println("Volume Bola = " + bola.menghitungVolume(10));
        
        JuringBola jb = new JuringBola(10, 90);
        System.out.println("Luas Permukaan Juring Bola = " + jb.menghitungLuasPermukaan());
        System.out.println("Volume Juring Bola = " + jb.menghitungVolume());
    }

}
