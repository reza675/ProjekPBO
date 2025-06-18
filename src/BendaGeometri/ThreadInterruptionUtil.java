package BendaGeometri;

/**
 * Utility class for handling thread interruption in geometric calculations
 */
public class ThreadInterruptionUtil {
    
    /**
     * Checks if the current thread has been interrupted and throws InterruptedException if so
     * @throws InterruptedException if the thread has been interrupted
     */
    public static void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Thread interrupted during calculation");
        }
    }
    
    /**
     * Checks if the current thread has been interrupted and returns true if so
     * @return true if the thread has been interrupted, false otherwise
     */
    public static boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }
    
    /**
     * Checks for interruption and prints a message if interrupted
     * @param operationName name of the operation being performed
     * @return true if interrupted, false otherwise
     */
    public static boolean checkAndHandleInterruption(String operationName) {
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interrupted during: " + operationName);
            return true;
        }
        return false;
    }
    
    /**
     * Checks for interruption every N iterations in a loop
     * @param currentIndex current loop index
     * @param checkInterval interval to check for interruption
     * @param operationName name of the operation being performed
     * @return true if interrupted, false otherwise
     */
    public static boolean checkInterruptionPeriodic(int currentIndex, int checkInterval, String operationName) {
        if (currentIndex % checkInterval == 0 && Thread.currentThread().isInterrupted()) {
            System.out.println("Thread interrupted during: " + operationName);
            return true;
        }
        return false;
    }
} 