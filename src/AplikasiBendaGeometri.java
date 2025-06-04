import BendaGeometri.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AplikasiBendaGeometri {
    public static void main(String[] args) {
        Scanner inputMenu = new Scanner(System.in);
        List<BendaGeometri> daftarBendaGeometri = new ArrayList<>();
        int pilihanMenu;
        boolean kembaliMenuUtama = true;
        do {
            clearConsole();
            System.out.println("================================================");
            System.out.println("| Program Aplikasi Perhitungan Benda Geometri  |");
            System.out.println("================================================");
            System.out.println("| 1. Segitiga                                  |");
            System.out.println("| 2. Persegi                                   |");
            System.out.println("| 3. Persegi Panjang                           |");
            System.out.println("| 4. Jajaran Genjang                           |");
            System.out.println("| 5. Trapesium                                 |");
            System.out.println("| 6. Belah Ketupat                             |");
            System.out.println("| 7. Layang-Layang                             |");
            System.out.println("| 8. Lingkaran                                 |");
            System.out.println("| 9. Tembereng Lingkaran                       |");
            System.out.println("| 10. Juring Lingkaran                         |");
            System.out.println("| 11. Prisma Segitiga                          |");
            System.out.println("| 12. Limas Segitiga                           |");
            System.out.println("| 13. Prisma Persegi                           |");
            System.out.println("| 14. Limas Persegi                            |");
            System.out.println("| 15. Prisma Persegi Panjang                   |");
            System.out.println("| 16. Limas Persegi Panjang                    |");
            System.out.println("| 17. Prisma Jajaran Genjang                   |");
            System.out.println("| 18. Limas Jajaran Genjang                    |");
            System.out.println("| 19. Prisma Trapesium                         |");
            System.out.println("| 20. Limas Trapesium                          |");
            System.out.println("| 21. Prisma Belah Ketupat                     |");
            System.out.println("| 22. Limas Belah Ketupat                      |");
            System.out.println("| 23. Prisma Layang-Layang                     |");
            System.out.println("| 24. Limas Layang-Layang                      |");
            System.out.println("| 25. Tabung                                   |");
            System.out.println("| 26. Kerucut                                  |");
            System.out.println("| 27. Kerucut Terpancung                       |");
            System.out.println("| 28. Bola                                     |");
            System.out.println("| 29. Tembereng Bola                           |");
            System.out.println("| 30. Juring Bola                              |");
            System.out.println("| 31. Cincin Bola                              |");
            System.out.println("| 32. Polymorphism                             |");
            System.out.println("================================================");
            System.out.print("Masukkan pilihan menu: ");
            try {
                pilihanMenu = inputMenu.nextInt();
                inputMenu.nextLine();
                switch (pilihanMenu) {
                    case 1:
                        Segitiga segitiga = new Segitiga(10, 15, 12, 14);
                        System.out.println("\n" + segitiga.getNamaBenda());
                        System.out.printf("Luas Segitiga: %.2f\n", segitiga.menghitungLuas());
                        System.out.printf("Keliling Segitiga: %.2f\n", segitiga.menghitungKeliling());
                        segitiga.prosesInputDataUlang();
                        break;
                    case 2:
                        Persegi persegi = new Persegi(10);
                        System.out.println("\n" + persegi.getNamaBenda());
                        System.out.printf("Luas Persegi: %.2f\n", persegi.menghitungLuas());
                        System.out.printf("Keliling Persegi: %.2f\n", persegi.menghitungKeliling());
                        persegi.prosesInputDataUlang();
                        break;
                    case 3:
                        PersegiPanjang persegiPanjang = new PersegiPanjang(10, 15);
                        System.out.println("\n" + persegiPanjang.getNamaBenda());
                        System.out.printf("Luas Persegi Panjang: %.2f\n", persegiPanjang.menghitungLuas());
                        System.out.printf("Keliling Persegi Panjang: %.2f\n", persegiPanjang.menghitungKeliling());
                        persegiPanjang.prosesInputDataUlang();
                        break;
                    case 4:
                        JajaranGenjang jajaranGenjang = new JajaranGenjang(8, 5, 6);
                        System.out.println("\n" + jajaranGenjang.getNamaBenda());
                        System.out.printf("Luas Jajaran Genjang: %.2f\n", jajaranGenjang.menghitungLuas());
                        System.out.printf("Keliling Jajaran Genjang: %.2f\n", jajaranGenjang.menghitungKeliling());
                        jajaranGenjang.prosesInputDataUlang();
                        break;
                    case 5:
                        Trapesium trapesium = new Trapesium(5, 8, 10, 6, 6);
                        System.out.println("\n" + trapesium.getNamaBenda());
                        System.out.printf("Luas Trapesium: %.2f\n", trapesium.menghitungLuas());
                        System.out.printf("Keliling Trapesium: %.2f\n", trapesium.menghitungKeliling());
                        trapesium.prosesInputDataUlang();
                        break;
                    case 6:
                        BelahKetupat belahKetupat = new BelahKetupat(10, 12, 14);
                        System.out.println("\n" + belahKetupat.getNamaBenda());
                        System.out.printf("Luas Belah Ketupat: %.2f\n", belahKetupat.menghitungLuas());
                        System.out.printf("Keliling Belah Ketupat: %.2f\n", belahKetupat.menghitungKeliling());
                        belahKetupat.prosesInputDataUlang();
                        break;
                    case 7:
                        LayangLayang layangLayang = new LayangLayang(10, 15, 12, 14);
                        System.out.println("\n" + layangLayang.getNamaBenda());
                        System.out.printf("Luas Layang-Layang: %.2f\n", layangLayang.menghitungLuas());
                        System.out.printf("Keliling Layang-Layang: %.2f\n", layangLayang.menghitungKeliling());
                        layangLayang.prosesInputDataUlang();
                        break;
                    case 8:
                        Lingkaran lingkaran = new Lingkaran(14);
                        System.out.println("\n" + lingkaran.getNamaBenda());
                        System.out.printf("Luas Lingkaran: %.2f\n", lingkaran.menghitungLuas());
                        System.out.printf("Keliling Lingkaran: %.2f\n", lingkaran.menghitungKeliling());
                        lingkaran.prosesInputDataUlang();
                        break;
                    case 9:
                        TemberengLingkaran temberengLingkaran = new TemberengLingkaran(20, 90);
                        System.out.println("\n" + temberengLingkaran.getNamaBenda());
                        System.out.printf("Luas Tembereng Lingkaran: %.2f\n", temberengLingkaran.menghitungLuas());
                        System.out.printf("Keliling Tembereng Lingkaran: %.2f\n",
                                temberengLingkaran.menghitungKeliling());
                        temberengLingkaran.prosesInputDataUlang();
                        break;
                    case 10:
                        JuringLingkaran juringLingkaran = new JuringLingkaran(21, 60);
                        System.out.println("\n" + juringLingkaran.getNamaBenda());
                        System.out.printf("Luas Juring Lingkaran: %.2f\n", juringLingkaran.menghitungLuas());
                        System.out.printf("Keliling Juring Lingkaran: %.2f\n", juringLingkaran.menghitungKeliling());
                        juringLingkaran.prosesInputDataUlang();
                        break;
                    case 11:
                        PrismaSegitiga prismaSegitiga = new PrismaSegitiga(8, 10, 4, 4, 15);
                        System.out.println("\n" + prismaSegitiga.getNamaBenda());
                        System.out.printf("Volume Prisma Segitiga: %.2f\n", prismaSegitiga.menghitungVolume());
                        System.out.printf("Luas Permukaan Prisma Segitiga: %.2f\n",
                                prismaSegitiga.menghitungLuasPermukaan());
                        prismaSegitiga.prosesInputDataUlang();
                        break;
                    case 12:
                        LimasSegitiga limasSegitiga = new LimasSegitiga(15, 10, 5, 5, 12);
                        System.out.println("\n" + limasSegitiga.getNamaBenda());
                        System.out.printf("Volume Limas Segitiga: %.2f\n", limasSegitiga.menghitungVolume());
                        System.out.printf("Luas Permukaan Limas Segitiga: %.2f\n",
                                limasSegitiga.menghitungLuasPermukaan());
                        limasSegitiga.prosesInputDataUlang();
                        break;
                    case 13:
                        PrismaPersegi prismaPersegi = new PrismaPersegi(10, 5);
                        System.out.println("\n" + prismaPersegi.getNamaBenda());
                        System.out.printf("Volume prisma persegi: %.2f\n", prismaPersegi.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma persegi: %.2f\n",
                                prismaPersegi.menghitungLuasPermukaan());
                        prismaPersegi.prosesInputDataUlang();
                        break;
                    case 14:
                        LimasPersegi limasPersegi = new LimasPersegi(5, 10);
                        System.out.println("\n" + limasPersegi.getNamaBenda());
                        System.out.printf("Volume limas persegi: %.2f\n", limasPersegi.menghitungVolume());
                        System.out.printf("Luas Permukaan limas persegi: %.2f\n",
                                limasPersegi.menghitungLuasPermukaan());
                        limasPersegi.prosesInputDataUlang();
                        break;
                    case 15:
                        PrismaPersegiPanjang prismaPersegiPanjang = new PrismaPersegiPanjang(10, 5, 8);
                        System.out.println("\n" + prismaPersegiPanjang.getNamaBenda());
                        System.out.printf("Volume prisma persegi panjang: %.2f\n",
                                prismaPersegiPanjang.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma persegi panjang: %.2f\n",
                                prismaPersegiPanjang.menghitungLuasPermukaan());
                        prismaPersegiPanjang.prosesInputDataUlang();
                        break;
                    case 16:
                        LimasPersegiPanjang limasPersegiPanjang = new LimasPersegiPanjang(8, 10, 12);
                        System.out.println("\n" + limasPersegiPanjang.getNamaBenda());
                        System.out.printf("Volume limas persegi panjang: %.2f\n",
                                limasPersegiPanjang.menghitungVolume());
                        System.out.printf("Luas Permukaan limas persegi panjang: %.2f\n",
                                limasPersegiPanjang.menghitungLuasPermukaan());
                        limasPersegiPanjang.prosesInputDataUlang();
                        break;
                    case 17:
                        PrismaJajaranGenjang prismaJajaranGenjang = new PrismaJajaranGenjang(5, 9, 10, 12);
                        System.out.println("\n" + prismaJajaranGenjang.getNamaBenda());
                        System.out.printf("Volume prisma jajaran genjang: %.2f\n",prismaJajaranGenjang.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma jajaran genjang: %.2f\n",prismaJajaranGenjang.menghitungLuasPermukaan());
                        prismaJajaranGenjang.prosesInputDataUlang();
                        break;
                    case 18:
                        LimasJajaranGenjang limasJajaranGenjang = new LimasJajaranGenjang(7, 12, 10, 15);
                        System.out.println("\n" + limasJajaranGenjang.getNamaBenda());
                        System.out.printf("Volume limas jajaran genjang: %.2f\n",limasJajaranGenjang.menghitungVolume());
                        System.out.printf("Luas Permukaan limas jajaran genjang: %.2f\n",limasJajaranGenjang.menghitungLuasPermukaan());
                        limasJajaranGenjang.prosesInputDataUlang();
                        break;
                    case 19:
                        PrismaTrapesium prismaTrapesium = new PrismaTrapesium(10, 10, 8, 6, 6, 12);
                        System.out.println("\n" + prismaTrapesium.getNamaBenda());
                        System.out.printf("Volume prisma trapesium: %.2f\n",prismaTrapesium.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma trapesium: %.2f\n",prismaTrapesium.menghitungLuasPermukaan());
                        prismaTrapesium.prosesInputDataUlang();
                        break;
                    case 20:
                        LimasTrapesium limasTrapesium = new LimasTrapesium(8, 8, 12, 10, 10, 12);
                        System.out.println("\n" + limasTrapesium.getNamaBenda());
                        System.out.printf("Volume limas trapesium: %.2f\n",limasTrapesium.menghitungVolume());
                        System.out.printf("Luas Permukaan limas trapesium: %.2f\n",limasTrapesium.menghitungLuasPermukaan());
                        limasTrapesium.prosesInputDataUlang();
                        break;
                    case 21:
                        PrismaBelahKetupat prismaBelahKetupat = new PrismaBelahKetupat(8, 10, 12, 12);
                        System.out.println("\n" + prismaBelahKetupat.getNamaBenda());
                        System.out.printf("Volume prisma belah ketupat: %.2f\n",prismaBelahKetupat.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma belah ketupat: %.2f\n",prismaBelahKetupat.menghitungLuasPermukaan());
                        prismaBelahKetupat.prosesInputDataUlang();
                        break;
                    case 22:
                        LimasBelahKetupat limasBelahKetupat = new LimasBelahKetupat(8, 10, 12, 12);
                        System.out.println("\n" + limasBelahKetupat.getNamaBenda());
                        System.out.printf("Volume limas belah ketupat: %.2f\n",limasBelahKetupat.menghitungVolume());
                        System.out.printf("Luas Permukaan limas belah ketupat: %.2f\n",limasBelahKetupat.menghitungLuasPermukaan());
                        limasBelahKetupat.prosesInputDataUlang();
                        break;
                    case 23:
                        PrismaLayangLayang prismaLayangLayang = new PrismaLayangLayang(6, 8, 4, 8, 12);
                        System.out.println("\n" + prismaLayangLayang.getNamaBenda());
                        System.out.printf("Volume prisma layang layang: %.2f\n",prismaLayangLayang.menghitungVolume());
                        System.out.printf("Luas Permukaan prisma layang layang: %.2f\n",prismaLayangLayang.menghitungLuasPermukaan());
                        prismaLayangLayang.prosesInputDataUlang();
                        break;
                    case 24:
                        LimasLayangLayang limasLayangLayang = new LimasLayangLayang(8, 10, 12, 12, 12);
                        System.out.println("\n" + limasLayangLayang.getNamaBenda());
                        System.out.printf("Volume limas layang layang: %.2f\n",limasLayangLayang.menghitungVolume());
                        System.out.printf("Luas Permukaan limas layang layang: %.2f\n",limasLayangLayang.menghitungLuasPermukaan());
                        limasLayangLayang.prosesInputDataUlang();
                        break;
                    case 25:
                        Tabung tabung = new Tabung(7, 10);
                        System.out.println("\n" + tabung.getNamaBenda());
                        System.out.printf("Volume tabung: %.2f\n",tabung.menghitungVolume());
                        System.out.printf("Luas Permukaan tabung: %.2f\n",tabung.menghitungLuasPermukaan());
                        tabung.prosesInputDataUlang();
                        break;
                    case 26:
                        Kerucut kerucut = new Kerucut(14, 10);
                        System.out.println("\n" + kerucut.getNamaBenda());
                        System.out.printf("Volume kerucut: %.2f\n",kerucut.menghitungVolume());
                        System.out.printf("Luas Permukaan kerucut: %.2f\n",kerucut.menghitungLuasPermukaan());
                        kerucut.prosesInputDataUlang();
                        break;
                    case 27:
                        KerucutTerpancung kerucutTerpancung = new KerucutTerpancung(7, 14, 12);
                        System.out.println("\n" + kerucutTerpancung.getNamaBenda());
                        System.out.printf("Volume kerucut terpancung: %.2f\n",kerucutTerpancung.menghitungVolume());
                        System.out.printf("Luas Permukaan kerucut terpancung: %.2f\n",kerucutTerpancung.menghitungLuasPermukaan());
                        kerucutTerpancung.prosesInputDataUlang();
                        break;
                    case 28:
                        Bola bola = new Bola(14);
                        System.out.println("\n" + bola.getNamaBenda());
                        System.out.printf("Volume bola: %.2f\n",bola.menghitungVolume());
                        System.out.printf("Luas Permukaan bola: %.2f\n",bola.menghitungLuasPermukaan());
                        bola.prosesInputDataUlang();
                        break;
                    case 29:
                        TemberengBola temberengBola = new TemberengBola(7, 14);
                        System.out.println("\n" + temberengBola.getNamaBenda());
                        System.out.printf("Volume tembereng bola: %.2f\n",temberengBola.menghitungVolume());
                        System.out.printf("Luas Permukaan tembereng bola: %.2f\n",temberengBola.menghitungLuasPermukaan());
                        temberengBola.prosesInputDataUlang();
                        break;
                    case 30:
                        JuringBola juringBola = new JuringBola(7, 30);
                        System.out.println("\n" + juringBola.getNamaBenda());
                        System.out.printf("Volume juring bola: %.2f\n",juringBola.menghitungVolume());
                        System.out.printf("Luas Permukaan juring bola: %.2f\n",juringBola.menghitungLuasPermukaan());
                        juringBola.prosesInputDataUlang();
                        break;
                    case 31:
                        CincinBola cincinBola = new CincinBola(14, 15);
                        System.out.println("\n" + cincinBola.getNamaBenda());
                        System.out.printf("Volume cincin bola: %.2f\n",cincinBola.menghitungVolume());
                        System.out.printf("Luas Permukaan cincin bola: %.2f\n",cincinBola.menghitungLuasPermukaan());
                        cincinBola.prosesInputDataUlang();
                        break;
                    case 32:
                        Benda2D bd1 = new Persegi(5);
                        Benda2D bd2 = new PersegiPanjang(5, 10);
                        Benda2D bd3 = new Segitiga(8,10,8,8);
                        Benda2D bd4 = new Lingkaran(5);
                        Benda2D bd5 = new JuringLingkaran(7, 60);
                        Benda2D bd6 = new TemberengLingkaran(14, 45);
                        Benda2D bd7 = new Trapesium(5, 10, 8, 12,12);
                        Benda2D bd8 = new BelahKetupat(8, 6, 10);
                        Benda2D bd9 = new LayangLayang(5, 8, 10, 12);
                        Benda2D bd10 = new JajaranGenjang(7,5,8);


                        System.out.println("Luas "+ bd1.getNamaBenda() +  " (bd1): "  + bd1.menghitungLuas() + " dan Keliling : " + bd1.menghitungKeliling());
                        System.out.println("Luas "+ bd2.getNamaBenda() +  " (bd2): " + bd2.menghitungLuas() +" dan Keliling : " + bd2.menghitungKeliling());
                        System.out.println("Luas "+ bd3.getNamaBenda() +  " (bd3): " + bd3.menghitungLuas() + " dan Keliling : " + bd3.menghitungKeliling());
                        System.out.println("Luas "+ bd4.getNamaBenda() +  " (bd4): " + bd4.menghitungLuas() + " dan Keliling : " + bd4.menghitungKeliling());
                        System.out.println("Luas "+ bd5.getNamaBenda() +  " (bd5): " + bd5.menghitungLuas() + " dan Keliling : " + bd5.menghitungKeliling());
                        System.out.println("Luas "+ bd6.getNamaBenda() +  " (bd6): " + bd6.menghitungLuas() + " dan Keliling : " + bd6.menghitungKeliling());
                        System.out.println("Luas "+ bd7.getNamaBenda() +  " (bd7): " + bd7.menghitungLuas() + " dan Keliling : " + bd7.menghitungKeliling());
                        System.out.println("Luas "+ bd8.getNamaBenda() +  " (bd8): " + bd8.menghitungLuas() + " dan Keliling : " + bd8.menghitungKeliling());
                        System.out.println("Luas "+ bd9.getNamaBenda() +  " (bd9): " + bd9.menghitungLuas() + " dan Keliling : " + bd9.menghitungKeliling());
                        System.out.println("Luas "+ bd10.getNamaBenda() +  " (bd10): " + bd10.menghitungLuas() + " dan Keliling : " + bd10.menghitungKeliling());
                        break;

                    default:
                        System.out.println("Menu tidak tersedia.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka sesuai pilihan.");
                inputMenu.nextLine();
                pilihanMenu = 0;
            }
        } while (konfirmasiLanjutMenu(inputMenu));

    }

    private static boolean konfirmasiLanjutMenu(Scanner input) {
        int percobaan = 0;
        while (percobaan < 3) {
            System.out.print("Apakah Anda ingin kembali ke menu utama? (y/n): ");
            String jawaban = input.nextLine().trim().toLowerCase();
            if (jawaban.equals("y") || jawaban.equals("ya")) {
                return true;
            }
            if (jawaban.equals("n") || jawaban.equals("no")) {
                System.out.println("\nProgram telah berakhir");
                return false;
            }
            System.out.println("Input tidak dikenali. Silakan masukkan 'y' atau 'n'.");
            percobaan++;
        }
        System.out.println("Terlalu banyak kesalahan input. Program akan keluar...");
        return false;
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
