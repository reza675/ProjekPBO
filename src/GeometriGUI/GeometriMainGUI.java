package GeometriGUI;

import BendaGeometri.*;
import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

public class GeometriMainGUI extends JFrame {
    private final String[] menuUtama = {
        "Persegi", "Persegi Panjang", "Segitiga", "Jajaran Genjang", "Belah Ketupat", "Trapesium", "Layang Layang", "Lingkaran", "Polymorphism", "Thread"
    };
    private final Map<String, String[]> turunanMap = new HashMap<>();
    private JTabbedPane tabbedPane;
    private Map<String, JComboBox<String>> comboTurunanMap = new HashMap<>();
    private Map<String, JTextArea> areaKiriMap = new HashMap<>();
    private Map<String, JTextArea> areaKananMap = new HashMap<>();
    private Map<String, JButton> btnHitungKiriMap = new HashMap<>();
    private Map<String, JButton> btnHitungUlangKiriMap = new HashMap<>();
    private Map<String, JButton> btnHitungKananMap = new HashMap<>();
    private Map<String, JButton> btnHitungUlangKananMap = new HashMap<>();
    private JButton btnClear;

    // Mapping menu ke class, parameter default, dan label parameter
    private static class GeometriInfo {
        String className;
        String[] paramLabels;
        Double[] defaultValues;
        String[] resultLabels; // label hasil (luas, keliling, volume, dsb)
        String[] resultMethods; // nama method hasil
        boolean is2D;
        GeometriInfo(String className, String[] paramLabels, Double[] defaultValues, String[] resultLabels, String[] resultMethods, boolean is2D) {
            this.className = className;
            this.paramLabels = paramLabels;
            this.defaultValues = defaultValues;
            this.resultLabels = resultLabels;
            this.resultMethods = resultMethods;
            this.is2D = is2D;
        }
    }
    private final Map<String, GeometriInfo> geometriMap = new HashMap<>();

    public GeometriMainGUI() {
        setTitle("Aplikasi Perhitungan Benda Geometri");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        inisialisasiTurunan();
        initUI();
    }

    private void inisialisasiTurunan() {
        turunanMap.put("Persegi", new String[]{"Prisma Persegi", "Limas Persegi"});
        turunanMap.put("Persegi Panjang", new String[]{"Prisma Persegi Panjang", "Limas Persegi Panjang"});
        turunanMap.put("Segitiga", new String[]{"Prisma Segitiga", "Limas Segitiga"});
        turunanMap.put("Jajaran Genjang", new String[]{"Prisma Jajaran Genjang", "Limas Jajaran Genjang"});
        turunanMap.put("Belah Ketupat", new String[]{"Prisma Belah Ketupat", "Limas Belah Ketupat"});
        turunanMap.put("Trapesium", new String[]{"Prisma Trapesium", "Limas Trapesium"});
        turunanMap.put("Layang Layang", new String[]{"Prisma Layang Layang", "Limas Layang Layang"});
        turunanMap.put("Lingkaran", new String[]{"Tabung", "Kerucut", "Bola", "Juring Lingkaran", "Tembereng Lingkaran", "Juring Bola", "Tembereng Bola", "Cincin Bola", "Kerucut Terpancung"});
        turunanMap.put("Polymorphism", new String[]{});
        turunanMap.put("Thread", new String[]{});

        // Mapping menu utama ke info class dan parameter
        geometriMap.put("Persegi", new GeometriInfo(
            "BendaGeometri.Persegi",
            new String[]{"Sisi"},
            new Double[]{10.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Persegi Panjang", new GeometriInfo(
            "BendaGeometri.PersegiPanjang",
            new String[]{"Panjang", "Lebar"},
            new Double[]{10.0, 15.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Segitiga", new GeometriInfo(
            "BendaGeometri.Segitiga",
            new String[]{"Alas", "Tinggi", "Sisi Miring 1", "Sisi Miring 2"},
            new Double[]{10.0, 15.0, 12.0, 14.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Jajaran Genjang", new GeometriInfo(
            "BendaGeometri.JajaranGenjang",
            new String[]{"Alas", "Tinggi", "Sisi Miring"},
            new Double[]{8.0, 5.0, 6.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Belah Ketupat", new GeometriInfo(
            "BendaGeometri.BelahKetupat",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi"},
            new Double[]{10.0, 12.0, 14.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Trapesium", new GeometriInfo(
            "BendaGeometri.Trapesium",
            new String[]{"Alas Atas", "Alas Bawah", "Tinggi", "Sisi Miring Kiri", "Sisi Miring Kanan"},
            new Double[]{5.0, 8.0, 10.0, 6.0, 6.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Layang Layang", new GeometriInfo(
            "BendaGeometri.LayangLayang",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang"},
            new Double[]{10.0, 15.0, 12.0, 14.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        geometriMap.put("Lingkaran", new GeometriInfo(
            "BendaGeometri.Lingkaran",
            new String[]{"Radius"},
            new Double[]{14.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            true
        ));
        // Turunan 3D (lengkapi semua turunan sesuai kebutuhan)
        geometriMap.put("Prisma Persegi", new GeometriInfo(
            "BendaGeometri.PrismaPersegi",
            new String[]{"Sisi", "Tinggi Prisma"},
            new Double[]{10.0, 5.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Persegi", new GeometriInfo(
            "BendaGeometri.LimasPersegi",
            new String[]{"Sisi", "Tinggi Limas"},
            new Double[]{5.0, 10.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Persegi Panjang", new GeometriInfo(
            "BendaGeometri.PrismaPersegiPanjang",
            new String[]{"Panjang", "Lebar", "Tinggi Prisma"},
            new Double[]{10.0, 5.0, 8.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Persegi Panjang", new GeometriInfo(
            "BendaGeometri.LimasPersegiPanjang",
            new String[]{"Panjang", "Lebar", "Tinggi Limas"},
            new Double[]{8.0, 10.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Segitiga", new GeometriInfo(
            "BendaGeometri.PrismaSegitiga",
            new String[]{"Alas", "Tinggi Segitiga", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Prisma"},
            new Double[]{8.0, 10.0, 4.0, 4.0, 15.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Segitiga", new GeometriInfo(
            "BendaGeometri.LimasSegitiga",
            new String[]{"Alas", "Tinggi Segitiga", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Limas"},
            new Double[]{15.0, 10.0, 5.0, 5.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Jajaran Genjang", new GeometriInfo(
            "BendaGeometri.PrismaJajaranGenjang",
            new String[]{"Panjang Alas", "Tinggi Alas", "Sisi Miring Alas", "Tinggi Prisma"},
            new Double[]{5.0, 9.0, 10.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Jajaran Genjang", new GeometriInfo(
            "BendaGeometri.LimasJajaranGenjang",
            new String[]{"Panjang Alas", "Tinggi Alas", "Sisi Miring Alas", "Tinggi Limas"},
            new Double[]{7.0, 12.0, 10.0, 15.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Belah Ketupat", new GeometriInfo(
            "BendaGeometri.PrismaBelahKetupat",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Prisma"},
            new Double[]{8.0, 10.0, 12.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Belah Ketupat", new GeometriInfo(
            "BendaGeometri.LimasBelahKetupat",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Limas"},
            new Double[]{8.0, 10.0, 12.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Trapesium", new GeometriInfo(
            "BendaGeometri.PrismaTrapesium",
            new String[]{"Alas Atas", "Alas Bawah", "Tinggi Trapesium", "Sisi Miring Kiri", "Sisi Miring Kanan", "Tinggi Prisma"},
            new Double[]{10.0, 10.0, 8.0, 6.0, 6.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Trapesium", new GeometriInfo(
            "BendaGeometri.LimasTrapesium",
            new String[]{"Alas Atas", "Alas Bawah", "Tinggi Trapesium", "Sisi Miring Kiri", "Sisi Miring Kanan", "Tinggi Limas"},
            new Double[]{8.0, 8.0, 12.0, 10.0, 10.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Prisma Layang Layang", new GeometriInfo(
            "BendaGeometri.PrismaLayangLayang",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma"},
            new Double[]{6.0, 8.0, 4.0, 8.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Limas Layang Layang", new GeometriInfo(
            "BendaGeometri.LimasLayangLayang",
            new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas"},
            new Double[]{8.0, 10.0, 12.0, 12.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Tabung", new GeometriInfo(
            "BendaGeometri.Tabung",
            new String[]{"Radius", "Tinggi"},
            new Double[]{7.0, 10.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Kerucut", new GeometriInfo(
            "BendaGeometri.Kerucut",
            new String[]{"Radius", "Tinggi"},
            new Double[]{14.0, 10.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Kerucut Terpancung", new GeometriInfo(
            "BendaGeometri.KerucutTerpancung",
            new String[]{"Radius Atas", "Radius Bawah", "Tinggi"},
            new Double[]{7.0, 14.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Bola", new GeometriInfo(
            "BendaGeometri.Bola",
            new String[]{"Radius"},
            new Double[]{14.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Juring Lingkaran", new GeometriInfo(
            "BendaGeometri.JuringLingkaran",
            new String[]{"Radius", "Sudut Juring"},
            new Double[]{21.0, 60.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            false
        ));
        geometriMap.put("Tembereng Lingkaran", new GeometriInfo(
            "BendaGeometri.TemberengLingkaran",
            new String[]{"Radius", "Sudut"},
            new Double[]{20.0, 90.0},
            new String[]{"Luas", "Keliling"},
            new String[]{"menghitungLuas", "menghitungKeliling"},
            false
        ));
        geometriMap.put("Juring Bola", new GeometriInfo(
            "BendaGeometri.JuringBola",
            new String[]{"Radius", "Sudut Juring"},
            new Double[]{7.0, 30.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Tembereng Bola", new GeometriInfo(
            "BendaGeometri.TemberengBola",
            new String[]{"Radius", "Tinggi Tembereng"},
            new Double[]{14.0, 10.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
        geometriMap.put("Cincin Bola", new GeometriInfo(
            "BendaGeometri.CincinBola",
            new String[]{"Radius Dalam", "Radius Luar"},
            new Double[]{7.0, 12.0},
            new String[]{"Volume", "Luas Permukaan"},
            new String[]{"menghitungVolume", "menghitungLuasPermukaan"},
            false
        ));
    }

    private void initUI() {
        // Panel atas: Home dan judul
        JPanel panelAtas = new JPanel(new BorderLayout());
        JLabel labelJudul = new JLabel("Aplikasi Perhitungan Benda Geometri", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Arial", Font.BOLD, 32));
        JButton btnHome = new JButton();
        btnHome.setIcon(new ImageIcon("src/Assets/Images/icons8-home-50.png"));
        btnHome.setPreferredSize(new Dimension(90, 90));
        btnHome.setBackground(Color.RED);
        btnHome.setBorderPainted(false);
        panelAtas.add(btnHome, BorderLayout.WEST);
        panelAtas.add(labelJudul, BorderLayout.CENTER);
        panelAtas.setBackground(Color.WHITE);
        add(panelAtas, BorderLayout.NORTH);

        // TabbedPane untuk menu utama
        tabbedPane = new JTabbedPane();
        for (String menu : menuUtama) {
            tabbedPane.addTab(menu, createTabPanel(menu));
        }
        add(tabbedPane, BorderLayout.CENTER);

        // Panel bawah: Clear
        JPanel panelBawah = new JPanel();
        btnClear = new JButton("Clear");
        btnClear.setPreferredSize(new Dimension(200, 40));
        panelBawah.add(btnClear);
        panelBawah.setBackground(Color.WHITE);
        add(panelBawah, BorderLayout.SOUTH);

        // Event clear
        btnClear.addActionListener(e -> {
            for (String menu : menuUtama) {
                if (areaKiriMap.containsKey(menu)) {
                    areaKiriMap.get(menu).setText("");
                }
                if (areaKananMap.containsKey(menu)) {
                    areaKananMap.get(menu).setText("");
                }
            }
        });

        // Tambahkan event handler tombol hitung/hitung ulang untuk semua tab
        for (String menu : menuUtama) {
            GeometriInfo info = geometriMap.get(menu);
            if (info == null) continue;
            JButton btnHitung = btnHitungKiriMap.get(menu);
            JButton btnHitungUlang = btnHitungUlangKiriMap.get(menu);
            JTextArea area = areaKiriMap.get(menu);
            if (btnHitung != null && area != null) {
                btnHitung.addActionListener(e -> {
                    try {
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, info.defaultValues);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = getResultString(obj, info, info.defaultValues);
                        area.setText(result);
                    } catch (Exception ex) {
                        showErrorDialog(ex.getMessage());
                    }
                });
            }
            if (btnHitungUlang != null && area != null) {
                btnHitungUlang.addActionListener(e -> {
                    try {
                        Double[] values = showInputDialog(info);
                        if (values == null) return;
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, values);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = getResultString(obj, info, values);
                        area.setText(result);
                    } catch (Exception ex) {
                        showErrorDialog(ex.getMessage());
                    }
                });
            }
        }
        
        // Handler untuk panel turunan (kanan) - PERBAIKAN UTAMA
        for (String menu : turunanMap.keySet()) {
            JComboBox<String> combo = comboTurunanMap.get(menu);
            JTextArea area = areaKananMap.get(menu);
            JButton btnHitung = btnHitungKananMap.get(menu);
            JButton btnHitungUlang = btnHitungUlangKananMap.get(menu);
            
            if (combo != null && area != null && btnHitung != null && btnHitungUlang != null) {
                // Event untuk tombol Hitung
                btnHitung.addActionListener(e -> {
                    String selectedTurunan = (String) combo.getSelectedItem();
                    if (selectedTurunan == null) return;
                    
                    GeometriInfo info = geometriMap.get(selectedTurunan);
                    if (info == null) {
                        showErrorDialog("Geometri info tidak ditemukan untuk: " + selectedTurunan);
                        return;
                    }
                    
                    try {
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, info.defaultValues);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = getResultString(obj, info, info.defaultValues);
                        area.setText(result);
                    } catch (Exception ex) {
                        showErrorDialog(ex.getMessage());
                    }
                });
                
                // Event untuk tombol Hitung Ulang
                btnHitungUlang.addActionListener(e -> {
                    String selectedTurunan = (String) combo.getSelectedItem();
                    if (selectedTurunan == null) return;
                    
                    GeometriInfo info = geometriMap.get(selectedTurunan);
                    if (info == null) {
                        showErrorDialog("Geometri info tidak ditemukan untuk: " + selectedTurunan);
                        return;
                    }
                    
                    try {
                        Double[] values = showInputDialog(info);
                        if (values == null) return;
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, values);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = getResultString(obj, info, values);
                        area.setText(result);
                    } catch (Exception ex) {
                        showErrorDialog(ex.getMessage());
                    }
                });
            }
        }
    }

    private JPanel createTabPanel(String menu) {
        if (menu.equals("Polymorphism")) {
            JPanel panelPoly = new JPanel();
            panelPoly.setLayout(new BoxLayout(panelPoly, BoxLayout.Y_AXIS));
            JLabel labelPoly = new JLabel("Hasil Polymorphism");
            labelPoly.setFont(new Font("Arial", Font.BOLD, 16));
            labelPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPoly.add(Box.createVerticalStrut(10));
            panelPoly.add(labelPoly);
            panelPoly.add(Box.createVerticalStrut(8));
            JTextArea areaPoly = new JTextArea(10, 40);
            areaPoly.setFont(new Font("Arial", Font.PLAIN, 15));
            areaPoly.setBackground(Color.LIGHT_GRAY);
            areaPoly.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            areaPoly.setEditable(false);
            JScrollPane scrollPoly = new JScrollPane(areaPoly);
            scrollPoly.setPreferredSize(new Dimension(400, 180));
            scrollPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPoly.add(scrollPoly);
            JButton btnPoly = new JButton("Hitung");
            btnPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPoly.add(Box.createVerticalStrut(8));
            panelPoly.add(btnPoly);
            panelPoly.setBackground(Color.WHITE);
            areaKiriMap.put(menu, areaPoly); // agar bisa diakses clear
            // Event handler untuk tombol Hitung Polymorphism
            btnPoly.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                try {
                    Benda2D bd1 = new Persegi(5);
                    Benda2D bd2 = new PersegiPanjang(5, 10);
                    Benda2D bd3 = new Segitiga(8, 10, 8, 8);
                    Benda2D bd4 = new Lingkaran(5);
                    Benda2D bd5 = new JuringLingkaran(7, 60);
                    Benda2D bd6 = new TemberengLingkaran(14, 45);
                    Benda2D bd7 = new Trapesium(5, 10, 8, 12, 12);
                    Benda2D bd8 = new BelahKetupat(8, 6, 10);
                    Benda2D bd9 = new LayangLayang(5, 8, 10, 12);
                    Benda2D bd10 = new JajaranGenjang(7, 5, 8);
                    Bola bd11 = new CincinBola(7, 12);
                    Bola bd12 = new JuringBola(7, 60);
                    Bola bd13 = new TemberengBola(14, 10);
                    Kerucut bd14 = new KerucutTerpancung(7, 14, 10);
                    sb.append("Benda 2D\n");
                    sb.append("Luas ").append(bd1.getNamaBenda()).append(" (bd1): ").append(String.format("%.2f", bd1.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd1.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd2.getNamaBenda()).append(" (bd2): ").append(String.format("%.2f", bd2.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd2.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd3.getNamaBenda()).append(" (bd3): ").append(String.format("%.2f", bd3.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd3.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd4.getNamaBenda()).append(" (bd4): ").append(String.format("%.2f", bd4.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd4.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd5.getNamaBenda()).append(" (bd5): ").append(String.format("%.2f", bd5.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd5.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd6.getNamaBenda()).append(" (bd6): ").append(String.format("%.2f", bd6.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd6.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd7.getNamaBenda()).append(" (bd7): ").append(String.format("%.2f", bd7.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd7.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd8.getNamaBenda()).append(" (bd8): ").append(String.format("%.2f", bd8.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd8.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd9.getNamaBenda()).append(" (bd9): ").append(String.format("%.2f", bd9.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd9.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd10.getNamaBenda()).append(" (bd10): ").append(String.format("%.2f", bd10.menghitungLuas())).append(" dan Keliling : ").append(String.format("%.2f", bd10.menghitungKeliling())).append("\n");
                    sb.append("Benda 3D\n");
                    sb.append("Volume ").append(bd11.getNamaBenda()).append(" (bd11): ").append(String.format("%.2f", bd11.menghitungVolume())).append(" dan Luas Permukaan : ").append(String.format("%.2f", bd11.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd12.getNamaBenda()).append(" (bd12): ").append(String.format("%.2f", bd12.menghitungVolume())).append(" dan Luas Permukaan : ").append(String.format("%.2f", bd12.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd13.getNamaBenda()).append(" (bd13): ").append(String.format("%.2f", bd13.menghitungVolume())).append(" dan Luas Permukaan : ").append(String.format("%.2f", bd13.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd14.getNamaBenda()).append(" (bd14): ").append(String.format("%.2f", bd14.menghitungVolume())).append(" dan Luas Permukaan : ").append(String.format("%.2f", bd14.menghitungLuasPermukaan())).append("\n");
                } catch (Exception ex) {
                    areaPoly.setText("Terjadi error: " + ex.getMessage());
                    return;
                }
                areaPoly.setText(sb.toString());
            });
            return panelPoly;
        }
        if (menu.equals("Thread")) {
            JPanel panelThread = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel labelInput = new JLabel("Jumlah Thread:");
            labelInput.setFont(new Font("Arial", Font.PLAIN, 20));
            panelThread.add(labelInput, gbc);
            gbc.gridx = 1;
            JTextField fieldJumlah = new JTextField(8);
            fieldJumlah.setFont(new Font("Arial", Font.PLAIN, 20));
            fieldJumlah.setPreferredSize(new Dimension(120, 32));
            panelThread.add(fieldJumlah, gbc);
            gbc.gridx = 2;
            JButton btnJalankan = new JButton("Jalankan");
            btnJalankan.setFont(new Font("Arial", Font.PLAIN, 18));
            panelThread.add(btnJalankan, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(20, 10, 10, 10);
            JTextArea areaThread = new JTextArea(10, 40);
            areaThread.setFont(new Font("Arial", Font.PLAIN, 18));
            areaThread.setBackground(Color.LIGHT_GRAY);
            areaThread.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            areaThread.setEditable(false);
            JScrollPane scrollThread = new JScrollPane(areaThread);
            scrollThread.setPreferredSize(new Dimension(700, 220));
            panelThread.add(scrollThread, gbc);
            panelThread.setBackground(Color.WHITE);
            areaKiriMap.put(menu, areaThread); // agar bisa diakses clear
            // Simpan komponen jika ingin digunakan event selanjutnya
            btnHitungKiriMap.put(menu, btnJalankan);
            return panelThread;
        }
        JPanel panelMain = new JPanel(new GridLayout(1, 2, 30, 0));
        // Panel kiri
        JPanel panelKiri = new JPanel(new GridBagLayout());
        GridBagConstraints gbcKiri = new GridBagConstraints();
        gbcKiri.insets = new Insets(10, 10, 5, 10);
        gbcKiri.gridx = 0;
        gbcKiri.gridy = 0;
        gbcKiri.anchor = GridBagConstraints.WEST;
        JLabel labelKiri = new JLabel(menu);
        labelKiri.setFont(new Font("Arial", Font.PLAIN, 22));
        panelKiri.add(labelKiri, gbcKiri);

        gbcKiri.gridy++;
        gbcKiri.insets = new Insets(0, 10, 10, 10);
        JTextArea areaKiri = new JTextArea(7, 28); // lebih besar
        areaKiri.setFont(new Font("Arial", Font.PLAIN, 16));
        areaKiri.setBackground(Color.LIGHT_GRAY);
        areaKiri.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        areaKiri.setEditable(false);
        JScrollPane scrollKiri = new JScrollPane(areaKiri);
        scrollKiri.setPreferredSize(new Dimension(400, 200));
        panelKiri.add(scrollKiri, gbcKiri);

        gbcKiri.gridy++;
        gbcKiri.insets = new Insets(10, 10, 10, 10);
        JPanel panelBtnKiri = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        JButton btnHitungKiri = new JButton("Hitung");
        JButton btnHitungUlangKiri = new JButton("Hitung Ulang");
        panelBtnKiri.add(btnHitungKiri);
        panelBtnKiri.add(btnHitungUlangKiri);
        panelBtnKiri.setBackground(Color.WHITE);
        panelKiri.add(panelBtnKiri, gbcKiri);
        panelKiri.setBackground(Color.WHITE);
        // Simpan komponen ke map
        areaKiriMap.put(menu, areaKiri);
        btnHitungKiriMap.put(menu, btnHitungKiri);
        btnHitungUlangKiriMap.put(menu, btnHitungUlangKiri);

        // Panel kanan
        JPanel panelKanan = new JPanel(new GridBagLayout());
        GridBagConstraints gbcKanan = new GridBagConstraints();
        gbcKanan.insets = new Insets(10, 10, 5, 10);
        gbcKanan.gridx = 0;
        gbcKanan.gridy = 0;
        gbcKanan.anchor = GridBagConstraints.WEST;
        JPanel panelTurunan = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel labelTurunan = new JLabel("Turunan");
        labelTurunan.setFont(new Font("Arial", Font.PLAIN, 20));
        JComboBox<String> comboTurunan = new JComboBox<>(turunanMap.get(menu));
        comboTurunan.setPreferredSize(new Dimension(250, 28));
        comboTurunan.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTurunan.add(labelTurunan);
        panelTurunan.add(Box.createHorizontalStrut(10));
        panelTurunan.add(comboTurunan);
        panelTurunan.setBackground(Color.WHITE);
        panelKanan.add(panelTurunan, gbcKanan);

        gbcKanan.gridy++;
        gbcKanan.insets = new Insets(0, 10, 10, 10);
        JTextArea areaKanan = new JTextArea(7, 28); // lebih besar
        areaKanan.setFont(new Font("Arial", Font.PLAIN, 16));
        areaKanan.setBackground(Color.LIGHT_GRAY);
        areaKanan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        areaKanan.setEditable(false);
        JScrollPane scrollKanan = new JScrollPane(areaKanan);
        scrollKanan.setPreferredSize(new Dimension(400, 200));
        panelKanan.add(scrollKanan, gbcKanan);

        gbcKanan.gridy++;
        gbcKanan.insets = new Insets(10, 10, 10, 10);
        JPanel panelBtnKanan = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        JButton btnHitungKanan = new JButton("Hitung");
        JButton btnHitungUlangKanan = new JButton("Hitung Ulang");
        panelBtnKanan.add(btnHitungKanan);
        panelBtnKanan.add(btnHitungUlangKanan);
        panelBtnKanan.setBackground(Color.WHITE);
        panelKanan.add(panelBtnKanan, gbcKanan);
        panelKanan.setBackground(Color.WHITE);
        // Simpan komponen ke map
        comboTurunanMap.put(menu, comboTurunan);
        areaKananMap.put(menu, areaKanan);
        btnHitungKananMap.put(menu, btnHitungKanan);
        btnHitungUlangKananMap.put(menu, btnHitungUlangKanan);

        panelMain.add(panelKiri);
        panelMain.add(panelKanan);
        return panelMain;
    }

    private Object createGeometriInstance(GeometriInfo info, Double[] values) throws Exception {
        Class<?> clazz = Class.forName(info.className);
        Class<?>[] paramTypes = new Class<?>[values.length];
        Arrays.fill(paramTypes, double.class);
        Constructor<?> ctor = clazz.getConstructor(paramTypes);
        Object[] params = Arrays.stream(values).toArray();
        return ctor.newInstance(params);
    }

    private String getResultString(Object obj, GeometriInfo info, Double[] paramValues) throws Exception {
        StringBuilder sb = new StringBuilder();
        // Tampilkan parameter input
        for (int i = 0; i < info.paramLabels.length; i++) {
            sb.append(info.paramLabels[i]).append(": ").append(paramValues[i]).append(" cm\n");
        }
        sb.append("\n");
        // Tampilkan hasil perhitungan
        for (int i = 0; i < info.resultLabels.length; i++) {
            Method m = obj.getClass().getMethod(info.resultMethods[i]);
            Object val = m.invoke(obj);
            sb.append(info.resultLabels[i]).append(": ").append(String.format("%.2f", val)).append("\n");
        }
        return sb.toString();
    }

    private Double[] showInputDialog(GeometriInfo info) {
        JPanel panel = new JPanel(new GridLayout(info.paramLabels.length, 2, 8, 8));
        JTextField[] fields = new JTextField[info.paramLabels.length];
        for (int i = 0; i < info.paramLabels.length; i++) {
            panel.add(new JLabel(info.paramLabels[i]));
            fields[i] = new JTextField(info.defaultValues[i].toString());
            panel.add(fields[i]);
        }
        int res = JOptionPane.showConfirmDialog(this, panel, "Input Parameter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) return null;
        Double[] values = new Double[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                values[i] = Double.parseDouble(fields[i].getText());
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Input harus berupa angka.");
            return null;
        }
        return values;
    }

    private void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeometriMainGUI().setVisible(true));
    }
} 