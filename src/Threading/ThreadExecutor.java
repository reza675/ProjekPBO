package Threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import BendaGeometri.*;

public class ThreadExecutor {

    public static void processShapes(List<BendaGeometri> shapes) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (BendaGeometri shape : shapes) {
            executor.submit(() -> processShape(shape));
        }

        executor.shutdown(); 
    }

    private static void processShape(BendaGeometri shape) {
        String threadName = Thread.currentThread().getName();

        try {
            if (shape.getClass().getSuperclass().equals(Benda2D.class)) {
                Benda2D bd = (Benda2D) shape;
                double keliling = bd.menghitungKeliling();
                double luas = bd.menghitungLuas();
                System.out.printf("%s - [%s] 2D -> Keliling: %.2f, Luas: %.2f%n",
                        threadName, shape.getClass().getSimpleName(), keliling, luas);
            }

            try {
                var volumeMethod = shape.getClass().getMethod("hitungVolume");
                var luasPermukaanMethod = shape.getClass().getMethod("hitungLuasPermukaan");

                double volume = (double) volumeMethod.invoke(shape);
                double luasPermukaan = (double) luasPermukaanMethod.invoke(shape);

                System.out.printf("%s - [%s] Volume: %.2f, Luas Permukaan: %.2f%n",
                        threadName, shape.getClass().getSimpleName(), volume, luasPermukaan);
            } catch (NoSuchMethodException ignored) {
            }

        } catch (Exception e) {
            System.err.println("Error processing shape " + shape.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
 