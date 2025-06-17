package BendaGeometri;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KerucutTerpancung extends Kerucut implements Runnable {

    private double radiusAtas;
    private double tinggiTerpancung;
    private double volume;
    private double luasPermukaan;
    private static double selisihRadius;

    public KerucutTerpancung(double radiusAtas, double radiusBawah, double tinggiTerpancung)
            throws InputMismatchException {
        super(radiusBawah, menghitungTinggiKerucutUtuh(radiusBawah, radiusAtas, tinggiTerpancung));
        if (radiusAtas <= 0 || tinggiTerpancung <= 0) {
            throw new InputMismatchException("Radius atas dan tinggi terpancung harus lebih dari nol.");
        }
        this.radiusAtas = radiusAtas;
        this.tinggiTerpancung = tinggiTerpancung;
    }

    private static double menghitungTinggiKerucutUtuh(double radiusBawah, double radiusAtas, double tinggiTerpancung) throws InputMismatchException {
        selisihRadius = (radiusBawah * tinggiTerpancung) / (radiusBawah - radiusAtas);
        if (selisihRadius <= 0) {
            throw new InputMismatchException("Radius bawah harus lebih besar dari radius atas.");
        }
        return (radiusBawah * tinggiTerpancung) / (radiusBawah - radiusAtas);
    }

    @Override
    public double menghitungVolume() {
        double radiusBawah = super.radius;
        double radiusAtas = this.radiusAtas;
        double tinggiTerpancung = this.tinggiTerpancung;
        volume = (1.0 / 3.0) * super.PI * tinggiTerpancung
                * (radiusBawah * radiusBawah + radiusBawah * radiusAtas + radiusAtas * radiusAtas);
        return volume;
    }

    public double menghitungVolume(double radiusAtasBaru, double radiusBawahBaru, double tinggiTerpancungBaru)
            throws InputMismatchException {
        if (radiusAtasBaru <= 0 || radiusBawahBaru <= 0 || tinggiTerpancungBaru <= 0) {
            throw new InputMismatchException("Radius atas, radius bawah, dan tinggi terpancung harus lebih dari nol.");
        }
        volume = (1.0 / 3.0) * super.PI * tinggiTerpancungBaru * (radiusBawahBaru * radiusBawahBaru
                + radiusBawahBaru * radiusAtasBaru + radiusAtasBaru * radiusAtasBaru);
        return volume;
    }

    @Override
    public double menghitungLuasPermukaan() {
        double radiusBawah = super.radius;
        double radiusAtas = this.radiusAtas;
        double tinggiTerpancung = this.tinggiTerpancung;

        double luasAlasBawah = super.menghitungLuas(radiusBawah);
        double luasAlasAtas = super.menghitungLuas(radiusAtas);
        double selisihRadius = radiusBawah - radiusAtas;
        double sisiMiringTerpancung = Math.sqrt(tinggiTerpancung * tinggiTerpancung + selisihRadius * selisihRadius);
        double luasSelimut = super.PI * (radiusBawah + radiusAtas) * sisiMiringTerpancung;

        luasPermukaan = luasAlasBawah + luasAlasAtas + luasSelimut;
        return luasPermukaan;
    }

    public double menghitungLuasPermukaan(double radiusAtasBaru, double radiusBawahBaru, double tinggiTerpancungBaru)
            throws InputMismatchException {
        if (radiusAtasBaru <= 0 || radiusBawahBaru <= 0 || tinggiTerpancungBaru <= 0) {
            throw new InputMismatchException("Radius atas, radius bawah, dan tinggi terpancung harus lebih dari nol.");
        }
        double luasAlasBawah = super.menghitungLuas(radiusBawahBaru);
        double luasAlasAtas = super.menghitungLuas(radiusAtasBaru);
        double selisihRadius = radiusBawahBaru - radiusAtasBaru;
        double sisiMiringTerpancung = Math
                .sqrt(tinggiTerpancungBaru * tinggiTerpancungBaru + selisihRadius * selisihRadius);
        double luasSelimut = super.PI * (radiusBawahBaru + radiusAtasBaru) * sisiMiringTerpancung;

        luasPermukaan = luasAlasBawah + luasAlasAtas + luasSelimut;
        return luasPermukaan;
    }

    @Override
    public String getNamaBenda() {
        return "Kerucut Terpancung";
    }

    public void prosesInputDataUlang() {
        Scanner inputData = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nApakah Anda ingin mengubah nilai jari-jari atas, jari-jari bawah, dan tinggi kerucut terpancung? (Y/N): ");
            String jawaban = inputData.nextLine();
            if (jawaban.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan jari-jari atas: ");
                        String inputRadiusAtas = inputData.nextLine();
                        double radiusAtasBaru = Double.parseDouble(inputRadiusAtas);
                        System.out.print("Masukkan jari-jari bawah: ");
                        String inputRadiusBawah = inputData.nextLine();
                        double radiusBawahBaru = Double.parseDouble(inputRadiusBawah);
                        System.out.print("Masukkan tinggi kerucut terpancung: ");
                        String inputTinggiTerpancung = inputData.nextLine();
                        double tinggiTerpancungBaru = Double.parseDouble(inputTinggiTerpancung);

                        volume = menghitungVolume(radiusAtasBaru, radiusBawahBaru, tinggiTerpancungBaru);
                        luasPermukaan = menghitungLuasPermukaan(radiusAtasBaru, radiusBawahBaru, tinggiTerpancungBaru);

                        System.out.printf("\nVolume Kerucut Terpancung: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Kerucut Terpancung: %.2f\n", luasPermukaan);
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

    @Override
    public void run() {
        try {
            volume = menghitungVolume();
            luasPermukaan = menghitungLuasPermukaan();
            System.out.println("\n=== Hasil Perhitungan " + getNamaBenda() + " ===");
            System.out.printf("Volume: %.2f\n", volume);
            System.out.printf("Luas Permukaan: %.2f\n", luasPermukaan);
            prosesInputDataUlang();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan dalam perhitungan: " + e.getMessage());
        }
    }
}
