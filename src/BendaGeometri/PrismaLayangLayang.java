package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class PrismaLayangLayang extends LayangLayang implements Runnable {
    private double tinggiPrisma;
    private double luasAlas;
    private double kelilingAlas;
    private double volume;
    private double luasPermukaan;
    private final Object lock = new Object();

    public PrismaLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang,
            double tinggiPrisma) throws InputMismatchException {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        if (tinggiPrisma <= 0) {
            throw new InputMismatchException("Tinggi prisma harus lebih dari nol.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    @Override
    public void run() {
        synchronized(lock) {
            try {
                System.out.println("\n=== Perhitungan Prisma Layang-Layang dengan 1000 Data ===");
                double[] dataArray = new double[1000];
                for (int i = 0; i < 1000; i++) {
                    dataArray[i] = i + 1;
                }
                for (int i = 0; i < 1000; i += 5) {
                    if (i + 4 < 1000) {
                        double diagonal1Baru = dataArray[i];
                        double diagonal2Baru = dataArray[i + 1];
                        double sisiPendekBaru = dataArray[i + 2];
                        double sisiPanjangBaru = dataArray[i + 3];
                        double tinggiPrismaBaru = dataArray[i + 4];
                        try {
                            volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                            luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru, tinggiPrismaBaru);
                            System.out.printf("Data %d-%d: diagonal1=%.1f, diagonal2=%.1f, sisiPendek=%.1f, sisiPanjang=%.1f, tinggi=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 5, diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru, tinggiPrismaBaru, volume, luasPermukaan);
                        } catch (InputMismatchException e) {
                            System.out.printf("Data %d-%d: Error - %s\n", i + 1, i + 5, e.getMessage());
                        }
                    }
                }
                System.out.println("\nPerhitungan selesai untuk 1000 data!");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

   

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = luasAlas * tinggiPrisma;
        return volume;
    }

    public double menghitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiPrismaBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        volume = luasAlas * tinggiPrismaBaru;
        return volume;
    }

    public double menghitungLuasPermukaan() {
        luasAlas = super.menghitungLuas();
        kelilingAlas = super.menghitungKeliling();
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrisma;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiPendekBaru, double sisiPanjangBaru,double tinggiPrismaBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiPrismaBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, sisi pendek, sisi panjang, dan tinggi prisma harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        kelilingAlas = super.menghitungKeliling(sisiPendekBaru, sisiPanjangBaru);
        luasPermukaan = 2 * luasAlas + kelilingAlas * tinggiPrismaBaru;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Prisma Layang-Layang";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai diagonal1, diagonal2, sisi pendek, sisi panjang, dan tinggiPrisma prisma Layang-Layang? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal1 layang-layang: ");
                        String inputDiagonal1 = inputData.nextLine();
                        double diagonal1Baru = Double.parseDouble(inputDiagonal1);
                        System.out.print("Masukkan diagonal2 layang-layang: ");
                        String inputDiagonal2 = inputData.nextLine();
                        double diagonal2Baru = Double.parseDouble(inputDiagonal2);
                        System.out.print("Masukkan sisi pendek layang-layang: ");
                        String inputSisiPendek = inputData.nextLine();
                        double sisiPendekBaru = Double.parseDouble(inputSisiPendek);
                        System.out.print("Masukkan sisi panjang layang-layang: ");
                        String inputSisiPanjang = inputData.nextLine();
                        double sisiPanjangBaru = Double.parseDouble(inputSisiPanjang);
                        System.out.print("Masukkan tinggiPrisma prisma: ");
                        String inputTinggiPrisma = inputData.nextLine();
                        double tinggiPrismaBaru = Double.parseDouble(inputTinggiPrisma);
  
                        volume = menghitungVolume(diagonal1Baru, diagonal2Baru, tinggiPrismaBaru);
                        luasPermukaan = menghitungLuasPermukaan(diagonal1Baru, diagonal2Baru, sisiPendekBaru, sisiPanjangBaru,tinggiPrismaBaru);
                        System.out.printf("\nVolume Prisma Layang-Layang: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Prisma Layang-Layang: %.2f\n", luasPermukaan);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            } else if (jawaban.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Jawaban hanya boleh Y atau N.");
            }
        }
    }

}