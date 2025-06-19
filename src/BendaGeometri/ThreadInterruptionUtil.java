package BendaGeometri;

public class ThreadInterruptionUtil {
    public static void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Thread interrupted during calculation");
        }
    }

    public static boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }
    
    public static boolean checkAndHandleInterruption(String operationName) {
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interrupted during: " + operationName);
            return true;
        }
        return false;
    }
    

    public static boolean checkInterruptionPeriodic(int currentIndex, int checkInterval, String operationName) {
        if (currentIndex % checkInterval == 0 && Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interrupted during: " + operationName);
            return true;
        }
        return false;
    }
} 