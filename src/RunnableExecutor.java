/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author developbyarya
 */
import BendaGeometri.*;
import java.util.Random;
public class RunnableExecutor {
    public RunnableExecutor(int iteration){
        
        for (int i=0; i < iteration; i++){
            Random random = new Random();
            int r1 = random.nextInt(1, 20);
            int r2 = random.nextInt(2, 30);
            int r3 = random.nextInt(3, 25);
            double angle = random.nextDouble() * 360;

            // 2D Shapes
            Thread tPersegi = new Thread(new Persegi(r1));
            tPersegi.start();
            
            Thread tPersegiPanjang = new Thread(new PersegiPanjang(r1, r2));
            tPersegiPanjang.start();
            
            Thread tSegitiga = new Thread(new Segitiga(r1, r2, r2, r3));
            tSegitiga.start();
            
            Thread tLingkaran = new Thread(new Lingkaran(r1));
            tLingkaran.start();
            
            Thread tJajaranGenjang = new Thread(new JajaranGenjang(r1, r2, r3));
            tJajaranGenjang.start();
            
            Thread tTrapesium = new Thread(new Trapesium(r1, r2, r3, r2, r1));
            tTrapesium.start();
            
            Thread tBelahKetupat = new Thread(new BelahKetupat(r1, r2, r3));
            tBelahKetupat.start();
            
            Thread tLayangLayang = new Thread(new LayangLayang(r1, r2, r3, r2));
            tLayangLayang.start();
            
            Thread tJuringLingkaran = new Thread(new JuringLingkaran(r1, angle));
            tJuringLingkaran.start();
            
            Thread tTemberengLingkaran = new Thread(new TemberengLingkaran(r1, angle));
            tTemberengLingkaran.start();

            // 3D Shapes
            Thread tPrismaSegitiga = new Thread(new PrismaSegitiga(r1, r2, r2, r3, r1));
            tPrismaSegitiga.start();
            
            Thread tLimasSegitiga = new Thread(new LimasSegitiga(r1, r2, r2, r3, r1));
            tLimasSegitiga.start();
            
            Thread tPrismaPersegi = new Thread(new PrismaPersegi(r1, r2));
            tPrismaPersegi.start();
            
            Thread tLimasPersegi = new Thread(new LimasPersegi(r1, r2));
            tLimasPersegi.start();
            
            Thread tPrismaPersegiPanjang = new Thread(new PrismaPersegiPanjang(r1, r2, r3));
            tPrismaPersegiPanjang.start();
            
            Thread tLimasPersegiPanjang = new Thread(new LimasPersegiPanjang(r1, r2, r3));
            tLimasPersegiPanjang.start();
            
            Thread tPrismaJajaranGenjang = new Thread(new PrismaJajaranGenjang(r1, r2, r3, r1));
            tPrismaJajaranGenjang.start();
            
            Thread tLimasJajaranGenjang = new Thread(new LimasJajaranGenjang(r1, r2, r3, r1));
            tLimasJajaranGenjang.start();
            
            Thread tPrismaTrapesium = new Thread(new PrismaTrapesium(r1, r2, r3, r2, r1, r3));
            tPrismaTrapesium.start();
            
            Thread tLimasTrapesium = new Thread(new LimasTrapesium(r1, r2, r3, r2, r1, r3));
            tLimasTrapesium.start();
            
            Thread tPrismaBelahKetupat = new Thread(new PrismaBelahKetupat(r1, r2, r3, r1));
            tPrismaBelahKetupat.start();
            
            Thread tLimasBelahKetupat = new Thread(new LimasBelahKetupat(r1, r2, r3, r1));
            tLimasBelahKetupat.start();
            
            Thread tPrismaLayangLayang = new Thread(new PrismaLayangLayang(r1, r2, r3, r2, r1));
            tPrismaLayangLayang.start();
            
            Thread tLimasLayangLayang = new Thread(new LimasLayangLayang(r1, r2, r3, r2, r1));
            tLimasLayangLayang.start();
            
            // Spherical Shapes
            Thread tBola = new Thread(new Bola(r1));
            tBola.start();
            
            Thread tTemberengBola = new Thread(new TemberengBola(r1, r2));
            tTemberengBola.start();
            
            Thread tJuringBola = new Thread(new JuringBola(r1, angle));
            tJuringBola.start();
            
            Thread tCincinBola = new Thread(new CincinBola(r1, r2));
            tCincinBola.start();
            
            // Additional Complex Shapes
            Thread tTabung = new Thread(new Tabung(r1, r2));
            tTabung.start();
            
            Thread tKerucut = new Thread(new Kerucut(r1, r2));
            tKerucut.start();
            
            Thread tKerucutTerpancung = new Thread(new KerucutTerpancung(r1, r2, r3));
            tKerucutTerpancung.start();
            
            Thread tParaboloid = new Thread(new Paraboloid(r1, r2));
            tParaboloid.start();
            
            Thread tElipsoid = new Thread(new Elipsoid(r1, r2, r3));
            tElipsoid.start();
        }
    }
}
