package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LimasSegitiga extends Segitiga implements Runnable {

    private double tinggiLimas;
    private double luasAlas;
    private double volume;
    private double luasPermukaan;
    private volatile boolean calculated = false;

    public LimasSegitiga(double alas, double tinggiSegitiga, double sisiMiring1, double sisiMiring2, double tinggiLimas) throws InputMismatchException {
        super(alas, tinggiSegitiga, sisiMiring1, sisiMiring2);
        if (tinggiLimas <= 0) {
            throw new InputMismatchException("Tinggi limas harus lebih dari nol.");
        }
        this.tinggiLimas = tinggiLimas;
    }

    public double menghitungVolume() {
        luasAlas = super.menghitungLuas();
        volume = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return volume;
    }

    public double menghitungVolume(double alasBaru, double tinggiSegitigaBaru, double tinggiLimasBaru) throws InputMismatchException {
        if (alasBaru <= 0 || tinggiSegitigaBaru <= 0 || tinggiLimasBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, dan tinggi limas harus lebih dari nol.");
        }
        luasAlas = menghitungLuas(alasBaru, tinggiSegitigaBaru);
        volume = (1.0 / 3.0) * luasAlas * tinggiLimasBaru;
        return volume;
    }

    private double menghitungSlantHeight(double sisi) {
        luasAlas = super.menghitungLuas();
        double tinggiKeSisi = (2.0 * luasAlas) / sisi;
        double d = (2.0 / 3.0) * tinggiKeSisi;
        return Math.sqrt(tinggiLimas * tinggiLimas + d * d);

    }

    public double menghitungLuasPermukaan() {
        double luasAlas = super.menghitungLuas();

        double slantA = menghitungSlantHeight(super.alas);
        double slantB = menghitungSlantHeight(super.sisiMiring1);
        double slantC = menghitungSlantHeight(super.sisiMiring2);

        double luasTegakA = 0.5 * super.alas * slantA;
        double luasTegakB = 0.5 * super.sisiMiring1 * slantB;
        double luasTegakC = 0.5 * super.sisiMiring2 * slantC;

        luasPermukaan = luasAlas + luasTegakA + luasTegakB + luasTegakC;
        return luasPermukaan;
    }


    public double menghitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double sisiPendekBaru, double sisiPanjangBaru, double tinggiLimasBaru) throws InputMismatchException {
        if (diagonal1Baru <= 0 || diagonal2Baru <= 0 || sisiPendekBaru <= 0 || sisiPanjangBaru <= 0 || tinggiLimasBaru <= 0) {
            throw new InputMismatchException("Diagonal1, diagonal2, Sisi Pendek, Sisi Panjang, dan tinggi limas harus lebih dari nol.");
        }
        luasAlas = super.menghitungLuas(diagonal1Baru, diagonal2Baru);
        double proyeksiKeSisiPendek = diagonal2Baru / 2.0;
        double proyeksiKeSisiPanjang = diagonal1Baru / 2.0;
        double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(proyeksiKeSisiPendek, 2));
        double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggiLimasBaru, 2) + Math.pow(proyeksiKeSisiPanjang, 2));
        double luasMukaPendek = sisiPendekBaru * tinggiSegitigaPendek;
        double luasMukaPanjang = sisiPanjangBaru * tinggiSegitigaPanjang;
        luasPermukaan = luasAlas + luasMukaPendek + luasMukaPanjang;
        return luasPermukaan;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n=== Perhitungan Limas Segitiga dengan 1000 Data ===");
            double[] dataArray = new double[1000];
            for (int i = 0; i < 1000; i++) {
                dataArray[i] = i + 1;
            }
            for (int i = 0; i < 1000; i += 5) {
                if (i + 4 < 1000) {
                    double alasBaru = dataArray[i];
                    double tinggiSegitigaBaru = dataArray[i + 1];
                    double sisiMiring1Baru = dataArray[i + 2];
                    double sisiMiring2Baru = dataArray[i + 3];
                    double tinggiLimasBaru = dataArray[i + 4];
                    try {
                        volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru, sisiMiring2Baru, tinggiLimasBaru);
                        System.out.printf("Data %d-%d: alas=%.1f, tinggiSegitiga=%.1f, sisiMiring1=%.1f, sisiMiring2=%.1f, tinggiLimas=%.1f | Volume=%.2f, Luas Permukaan=%.2f\n", i + 1, i + 5, alasBaru, tinggiSegitigaBaru, sisiMiring1Baru, sisiMiring2Baru, tinggiLimasBaru, volume, luasPermukaan);
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

    public boolean isCalculated() {
        return calculated;
    }

    @Override
    public String getNamaBenda() {
        return "Limas Segitiga";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah Anda ingin mengubah nilai alas, tinggi segitiga, sisi miring 1, sisi miring 2, dan tinggi limas Limas Segitiga? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan panjang alas segitiga: ");
                        String inputAlas = inputData.nextLine();
                        double alasBaru = Double.parseDouble(inputAlas);
                        System.out.print("Masukkan tinggi segitiga: ");
                        String inputTinggiSegitiga = inputData.nextLine();
                        double tinggiSegitigaBaru = Double.parseDouble(inputTinggiSegitiga);
                        System.out.print("Masukkan sisi miring 1 segitiga: ");
                        String inputSisiMiring1 = inputData.nextLine();
                        double sisiMiring1Baru = Double.parseDouble(inputSisiMiring1);
                        System.out.print("Masukkan sisi miring 2 segitiga: ");
                        String inputSisiMiring2 = inputData.nextLine();
                        double sisiMiring2Baru = Double.parseDouble(inputSisiMiring2);
                        System.out.print("Masukkan tinggi limas: ");
                        String inputTinggiLimas = inputData.nextLine();
                        double tinggiLimasBaru = Double.parseDouble(inputTinggiLimas);
                       
                        volume = menghitungVolume(alasBaru, tinggiSegitigaBaru, tinggiLimasBaru);
                        luasPermukaan = menghitungLuasPermukaan(alasBaru, tinggiSegitigaBaru, sisiMiring1Baru,
                                sisiMiring2Baru, tinggiLimasBaru);

                        System.out.printf("\nVolume Limas Segitiga: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Segitiga: %.2f\n", luasPermukaan);
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
