package Threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import BendaGeometri.*;

public class ThreadExecutor {
    public static void processShapes(List<BendaGeometri> daftarBendaGeometri) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (BendaGeometri benda : daftarBendaGeometri) {
            executor.submit(() -> processShape(benda));
        }

        executor.shutdown();
        try {
            // Tunggu sampai 1 menit sebelum memanggil shutdown
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                // Jika lebih dari 1 menit shutdown
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static void processShape(BendaGeometri benda) {
        String threadName = Thread.currentThread().getName();

        try {
            if (benda.getClass().getSuperclass().equals(Benda2D.class)) {
                Benda2D bd = (Benda2D) benda;
                double keliling = bd.menghitungKeliling();
                double luas     = bd.menghitungLuas();
                System.out.printf("%s ->  [%s] 2D -> Keliling: %.2f, Luas: %.2f%n",
                                  threadName, benda.getClass().getSimpleName(), keliling, luas);
            }

            try {
                var volumeMethod       = benda.getClass().getMethod("menghitungVolume");
                var luasPermMethod     = benda.getClass().getMethod("menghitungLuasPermukaan");
                double volume          = (double) volumeMethod.invoke(benda);
                double luasPermukaan   = (double) luasPermMethod.invoke(benda);

                System.out.printf("%s -> [%s] Volume: %.2f, Luas Permukaan: %.2f%n",
                                  threadName, benda.getClass().getSimpleName(), volume, luasPermukaan);
            } catch (NoSuchMethodException ignored) {
                // Jika tidak ada metode hitungVolume/hitungLuasPermukaan, lewati
            }

        } catch (Exception e) {
            System.err.println("Error processing " 
                               + benda.getClass().getSimpleName() 
                               + ": " + e.getMessage());
        }
    }
}
