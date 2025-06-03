/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threading;

import BendaGeometri.Benda2D;
import BendaGeometri.Persegi;
import BendaGeometri.Segitiga;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author developbyarya
 */
public class ExecutorBenda2D {
    public static void hitungBenda(Benda2D benda2d ){
        System.out.println("Menghitung benda: " + benda2d.getNamaBenda());
        System.out.printf("\tLuas: %.2f\n", benda2d.menghitungLuas());
        System.out.printf("\tKeliling: %.2f\n", benda2d.menghitungKeliling());
    }
    
    public static void threadBanyakBenda2D(List<Benda2D> daftarBenda2d){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (Benda2D benda2d: daftarBenda2d){
            executor.submit(() -> hitungBenda(benda2d));
        }
        executor.shutdown();
    }
    
    public static void main(String[] args){
        List<Benda2D> daftarBendaPersegi = new ArrayList<>();
        List<Benda2D> daftarBendaSegitiga = new ArrayList<>();
        
        // Buat 10000 variasi persegi
        for (int i=0; i < 10000; i++){
            Benda2D persegi = new Persegi((2*i)*i);
            daftarBendaPersegi.add(persegi);
        }
        // buat 12000 variasi segitiga
        for (int i=0; i < 12000; i++){
            double sisi_miring = i+1;
            Benda2D segitiga = new Segitiga(i+1.0, i*i*4.0, sisi_miring, sisi_miring);
            daftarBendaSegitiga.add(segitiga);
        }
        
        threadBanyakBenda2D(daftarBendaPersegi);
        threadBanyakBenda2D(daftarBendaSegitiga);
        
        
    }
    
}
