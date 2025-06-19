package GUI;

import BendaGeometri.*;
import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class GeometriMainGUI extends JFrame {
    private final String[] menuUtama = {
            "Persegi", "Persegi Panjang", "Segitiga", "Jajaran Genjang", "Belah Ketupat", "Trapesium", "Layang Layang",
            "Lingkaran", "Polymorphism", "Thread"
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

    private static class GeometriInfo {
        String className;
        String[] paramLabels;
        Double[] defaultValues;
        String[] resultLabels; // label hasil (luas, keliling, volume, dsb)
        String[] resultMethods; // nama method hasil
        boolean is2D;

        GeometriInfo(String className, String[] paramLabels, Double[] defaultValues, String[] resultLabels,
                String[] resultMethods, boolean is2D) {
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
        setSize(1200, 750); // Sedikit lebih besar
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        inisialisasiTurunan();
        initUI();
    }

    private void inisialisasiTurunan() {
        turunanMap.put("Persegi", new String[] { "Prisma Persegi", "Limas Persegi" });
        turunanMap.put("Persegi Panjang", new String[] { "Prisma Persegi Panjang", "Limas Persegi Panjang" });
        turunanMap.put("Segitiga", new String[] { "Prisma Segitiga", "Limas Segitiga" });
        turunanMap.put("Jajaran Genjang", new String[] { "Prisma Jajaran Genjang", "Limas Jajaran Genjang" });
        turunanMap.put("Belah Ketupat", new String[] { "Prisma Belah Ketupat", "Limas Belah Ketupat" });
        turunanMap.put("Trapesium", new String[] { "Prisma Trapesium", "Limas Trapesium" });
        turunanMap.put("Layang Layang", new String[] { "Prisma Layang Layang", "Limas Layang Layang" });
        turunanMap.put("Lingkaran", new String[] { "Juring Lingkaran", "Tembereng Lingkaran", "Bola", "Juring Bola", "Tembereng Bola", "Cincin Bola", "Tabung", "Kerucut", "Kerucut Terpancung" });
        turunanMap.put("Polymorphism", new String[] {});
        turunanMap.put("Thread", new String[] {});

        // Mapping menu utama ke info class dan parameter
        geometriMap.put("Persegi", new GeometriInfo(
                "BendaGeometri.Persegi",
                new String[] { "Sisi" },
                new Double[] { 10.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Persegi Panjang", new GeometriInfo(
                "BendaGeometri.PersegiPanjang",
                new String[] { "Panjang", "Lebar" },
                new Double[] { 10.0, 15.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Segitiga", new GeometriInfo(
                "BendaGeometri.Segitiga",
                new String[] { "Alas", "Tinggi", "Sisi Miring 1", "Sisi Miring 2" },
                new Double[] { 10.0, 15.0, 12.0, 14.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Jajaran Genjang", new GeometriInfo(
                "BendaGeometri.JajaranGenjang",
                new String[] { "Alas", "Tinggi", "Sisi Miring" },
                new Double[] { 8.0, 5.0, 6.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Belah Ketupat", new GeometriInfo(
                "BendaGeometri.BelahKetupat",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi" },
                new Double[] { 10.0, 12.0, 14.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Trapesium", new GeometriInfo(
                "BendaGeometri.Trapesium",
                new String[] { "Alas Atas", "Alas Bawah", "Tinggi", "Sisi Miring Kiri", "Sisi Miring Kanan" },
                new Double[] { 5.0, 8.0, 10.0, 6.0, 6.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Layang Layang", new GeometriInfo(
                "BendaGeometri.LayangLayang",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang" },
                new Double[] { 10.0, 15.0, 12.0, 14.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        geometriMap.put("Lingkaran", new GeometriInfo(
                "BendaGeometri.Lingkaran",
                new String[] { "Radius" },
                new Double[] { 14.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                true));
        // Turunan 3D (lengkapi semua turunan sesuai kebutuhan)
        geometriMap.put("Prisma Persegi", new GeometriInfo(
                "BendaGeometri.PrismaPersegi",
                new String[] { "Sisi", "Tinggi Prisma" },
                new Double[] { 10.0, 5.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Persegi", new GeometriInfo(
                "BendaGeometri.LimasPersegi",
                new String[] { "Sisi", "Tinggi Limas" },
                new Double[] { 5.0, 10.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Persegi Panjang", new GeometriInfo(
                "BendaGeometri.PrismaPersegiPanjang",
                new String[] { "Panjang", "Lebar", "Tinggi Prisma" },
                new Double[] { 10.0, 5.0, 8.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Persegi Panjang", new GeometriInfo(
                "BendaGeometri.LimasPersegiPanjang",
                new String[] { "Panjang", "Lebar", "Tinggi Limas" },
                new Double[] { 8.0, 10.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Segitiga", new GeometriInfo(
                "BendaGeometri.PrismaSegitiga",
                new String[] { "Alas", "Tinggi Segitiga", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Prisma" },
                new Double[] { 8.0, 10.0, 4.0, 4.0, 15.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Segitiga", new GeometriInfo(
                "BendaGeometri.LimasSegitiga",
                new String[] { "Alas", "Tinggi Segitiga", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Limas" },
                new Double[] { 15.0, 10.0, 5.0, 5.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Jajaran Genjang", new GeometriInfo(
                "BendaGeometri.PrismaJajaranGenjang",
                new String[] { "Panjang Alas", "Tinggi Alas", "Sisi Miring Alas", "Tinggi Prisma" },
                new Double[] { 5.0, 9.0, 10.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Jajaran Genjang", new GeometriInfo(
                "BendaGeometri.LimasJajaranGenjang",
                new String[] { "Panjang Alas", "Tinggi Alas", "Sisi Miring Alas", "Tinggi Limas" },
                new Double[] { 7.0, 12.0, 10.0, 15.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Belah Ketupat", new GeometriInfo(
                "BendaGeometri.PrismaBelahKetupat",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Prisma" },
                new Double[] { 8.0, 10.0, 12.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Belah Ketupat", new GeometriInfo(
                "BendaGeometri.LimasBelahKetupat",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Limas" },
                new Double[] { 8.0, 10.0, 12.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Trapesium", new GeometriInfo(
                "BendaGeometri.PrismaTrapesium",
                new String[] { "Alas Atas", "Alas Bawah", "Tinggi Trapesium", "Sisi Miring Kiri", "Sisi Miring Kanan",
                        "Tinggi Prisma" },
                new Double[] { 10.0, 10.0, 8.0, 6.0, 6.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Trapesium", new GeometriInfo(
                "BendaGeometri.LimasTrapesium",
                new String[] { "Alas Atas", "Alas Bawah", "Tinggi Trapesium", "Sisi Miring Kiri", "Sisi Miring Kanan",
                        "Tinggi Limas" },
                new Double[] { 8.0, 8.0, 12.0, 10.0, 10.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Prisma Layang Layang", new GeometriInfo(
                "BendaGeometri.PrismaLayangLayang",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma" },
                new Double[] { 6.0, 8.0, 4.0, 8.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Limas Layang Layang", new GeometriInfo(
                "BendaGeometri.LimasLayangLayang",
                new String[] { "Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas" },
                new Double[] { 8.0, 10.0, 12.0, 12.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Tabung", new GeometriInfo(
                "BendaGeometri.Tabung",
                new String[] { "Radius", "Tinggi" },
                new Double[] { 7.0, 10.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Kerucut", new GeometriInfo(
                "BendaGeometri.Kerucut",
                new String[] { "Radius", "Tinggi" },
                new Double[] { 14.0, 10.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Kerucut Terpancung", new GeometriInfo(
                "BendaGeometri.KerucutTerpancung",
                new String[] { "Radius Atas", "Radius Bawah", "Tinggi" },
                new Double[] { 7.0, 14.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Bola", new GeometriInfo(
                "BendaGeometri.Bola",
                new String[] { "Radius" },
                new Double[] { 14.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Juring Lingkaran", new GeometriInfo(
                "BendaGeometri.JuringLingkaran",
                new String[] { "Radius", "Sudut Juring" },
                new Double[] { 21.0, 60.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                false));
        geometriMap.put("Tembereng Lingkaran", new GeometriInfo(
                "BendaGeometri.TemberengLingkaran",
                new String[] { "Radius", "Sudut" },
                new Double[] { 20.0, 90.0 },
                new String[] { "Luas", "Keliling" },
                new String[] { "menghitungLuas", "menghitungKeliling" },
                false));
        geometriMap.put("Juring Bola", new GeometriInfo(
                "BendaGeometri.JuringBola",
                new String[] { "Radius", "Sudut Juring" },
                new Double[] { 7.0, 30.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Tembereng Bola", new GeometriInfo(
                "BendaGeometri.TemberengBola",
                new String[] { "Radius", "Tinggi Tembereng" },
                new Double[] { 14.0, 10.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
        geometriMap.put("Cincin Bola", new GeometriInfo(
                "BendaGeometri.CincinBola",
                new String[] { "Radius", "Tinggi Cincin Bola" },
                new Double[] { 7.0, 12.0 },
                new String[] { "Volume", "Luas Permukaan" },
                new String[] { "menghitungVolume", "menghitungLuasPermukaan" },
                false));
    }

    private void initUI() {
        // Panel atas: Home dan judul
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.setBackground(new Color(30, 136, 229)); // Warna biru modern
        panelAtas.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel labelJudul = new JLabel("Aplikasi Perhitungan Benda Geometri", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Segoe UI", Font.BOLD, 32));
        labelJudul.setForeground(Color.WHITE);

        JButton btnHome = new JButton();
        btnHome.setIcon(new ImageIcon("src/GUI/icons8-home-50.png"));
        btnHome.setPreferredSize(new Dimension(70, 70));
        btnHome.setBackground(new Color(255,0,0));
        btnHome.setBorder(BorderFactory.createLineBorder(new Color(13, 71, 161), 2));
        btnHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHome.addActionListener(e -> dispose());

        panelAtas.add(btnHome, BorderLayout.WEST);
        panelAtas.add(labelJudul, BorderLayout.CENTER);
        add(panelAtas, BorderLayout.NORTH);

        // TabbedPane untuk menu utama
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabbedPane.setBackground(new Color(245, 245, 245)); // Background of the tabbed pane itself

        // Custom UI for the tabs to achieve a modern, flat look
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            private final Color SELECTED_TAB_COLOR = new Color(220, 230, 240); // Light blue for selected tab
            private final Color UNSELECTED_TAB_COLOR = Color.WHITE; // White for unselected tabs
            private final Color BORDER_COLOR = new Color(200, 200, 200); // Light gray border

            @Override
            protected void installDefaults() {
                super.installDefaults();
                // You can override default colors/insets here, but for custom painting, it's less critical
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isSelected) {
                    g2d.setColor(SELECTED_TAB_COLOR);
                } else {
                    g2d.setColor(UNSELECTED_TAB_COLOR);
                }
                g2d.fillRect(x, y, w, h);
                g2d.dispose();
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(BORDER_COLOR);

                if (isSelected) {
                    // Draw a subtle line at the bottom of the selected tab
                    g2d.fillRect(x, y + h - 1, w, 1); // Thin line at the bottom
                } else {
                    // Draw borders for unselected tabs (top, left, right)
                    g2d.drawLine(x, y, x + w, y); // Top
                    g2d.drawLine(x, y, x, y + h); // Left
                    g2d.drawLine(x + w, y, x + w, y + h); // Right
                }
                g2d.dispose();
            }

            @Override
            protected Insets getTabInsets(int tabPlacement, int tabIndex) {
                // Adjust tab padding for a more compact and modern look
                return new Insets(8, 15, 8, 15); // Top, Left, Bottom, Right
            }

            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                // Remove content border insets for a cleaner look
                return new Insets(0, 0, 0, 0);
            }
        });

        for (String menu : menuUtama) {
            tabbedPane.addTab(menu, createTabPanel(menu));
        }
        add(tabbedPane, BorderLayout.CENTER);

        // Panel bawah: Clear
        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(new Color(30, 136, 229));
        panelBawah.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        btnClear = new JButton("Clear All");
        btnClear.setPreferredSize(new Dimension(200, 45));
        btnClear.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnClear.setBackground(new Color(255, 87, 34));
        btnClear.setForeground(Color.WHITE);
        btnClear.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(204, 57, 0), 2),
                new EmptyBorder(5, 15, 5, 15)));
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBawah.add(btnClear);
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
            if (info == null)
                continue;
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
                        if (values == null)
                            return;
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, values);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = buildOverloadedResultString(obj, info, values);
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
                    if (selectedTurunan == null)
                        return;

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
                    if (selectedTurunan == null)
                        return;

                    GeometriInfo info = geometriMap.get(selectedTurunan);
                    if (info == null) {
                        showErrorDialog("Geometri info tidak ditemukan untuk: " + selectedTurunan);
                        return;
                    }

                    try {
                        Double[] values = showInputDialog(info);
                        if (values == null)
                            return;
                        Object obj = null;
                        try {
                            obj = createGeometriInstance(info, values);
                        } catch (InvocationTargetException ex) {
                            Throwable cause = ex.getCause();
                            showErrorDialog(cause != null ? cause.getMessage() : ex.getMessage());
                            return;
                        }
                        String result = buildOverloadedResultString(obj, info, values);
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
            panelPoly.setBackground(Color.WHITE);
            panelPoly.setBorder(new EmptyBorder(20, 20, 20, 20));

            JLabel labelPoly = new JLabel("Hasil Polymorphism");
            labelPoly.setFont(new Font("Segoe UI", Font.BOLD, 20));
            labelPoly.setForeground(new Color(30, 136, 229));
            labelPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPoly.add(labelPoly);
            panelPoly.add(Box.createVerticalStrut(15));

            JTextArea areaPoly = new JTextArea(10, 40);
            areaPoly.setFont(new Font("Consolas", Font.PLAIN, 14));
            areaPoly.setBackground(new Color(249, 249, 249));
            areaPoly.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(10, 10, 10, 10)));
            areaPoly.setEditable(false);

            JScrollPane scrollPoly = new JScrollPane(areaPoly);
            scrollPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelPoly.add(scrollPoly);
            panelPoly.add(Box.createVerticalStrut(15));

            JButton btnPoly = new JButton("Hitung Polymorphism");
            btnPoly.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnPoly.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnPoly.setBackground(new Color(56, 142, 60));
            btnPoly.setForeground(Color.WHITE);
            btnPoly.setBorder(new EmptyBorder(8, 20, 8, 20));
            btnPoly.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelPoly.add(btnPoly);

            areaKiriMap.put(menu, areaPoly);
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
                    sb.append("Luas ").append(bd1.getNamaBenda()).append(" (bd1): ")
                            .append(String.format("%.2f", bd1.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd1.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd2.getNamaBenda()).append(" (bd2): ")
                            .append(String.format("%.2f", bd2.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd2.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd3.getNamaBenda()).append(" (bd3): ")
                            .append(String.format("%.2f", bd3.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd3.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd4.getNamaBenda()).append(" (bd4): ")
                            .append(String.format("%.2f", bd4.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd4.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd5.getNamaBenda()).append(" (bd5): ")
                            .append(String.format("%.2f", bd5.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd5.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd6.getNamaBenda()).append(" (bd6): ")
                            .append(String.format("%.2f", bd6.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd6.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd7.getNamaBenda()).append(" (bd7): ")
                            .append(String.format("%.2f", bd7.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd7.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd8.getNamaBenda()).append(" (bd8): ")
                            .append(String.format("%.2f", bd8.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd8.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd9.getNamaBenda()).append(" (bd9): ")
                            .append(String.format("%.2f", bd9.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd9.menghitungKeliling())).append("\n");
                    sb.append("Luas ").append(bd10.getNamaBenda()).append(" (bd10): ")
                            .append(String.format("%.2f", bd10.menghitungLuas())).append(" dan Keliling : ")
                            .append(String.format("%.2f", bd10.menghitungKeliling())).append("\n");
                    sb.append("\nBenda 3D\n");
                    sb.append("Volume ").append(bd11.getNamaBenda()).append(" (bd11): ")
                            .append(String.format("%.2f", bd11.menghitungVolume())).append(" dan Luas Permukaan : ")
                            .append(String.format("%.2f", bd11.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd12.getNamaBenda()).append(" (bd12): ")
                            .append(String.format("%.2f", bd12.menghitungVolume())).append(" dan Luas Permukaan : ")
                            .append(String.format("%.2f", bd12.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd13.getNamaBenda()).append(" (bd13): ")
                            .append(String.format("%.2f", bd13.menghitungVolume())).append(" dan Luas Permukaan : ")
                            .append(String.format("%.2f", bd13.menghitungLuasPermukaan())).append("\n");
                    sb.append("Volume ").append(bd14.getNamaBenda()).append(" (bd14): ")
                            .append(String.format("%.2f", bd14.menghitungVolume())).append(" dan Luas Permukaan : ")
                            .append(String.format("%.2f", bd14.menghitungLuasPermukaan())).append("\n");
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
            panelThread.setBackground(Color.WHITE);
            panelThread.setBorder(new EmptyBorder(20, 20, 20, 20));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 10, 15, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            JLabel labelInput = new JLabel("Jumlah Thread:");
            labelInput.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            panelThread.add(labelInput, gbc);

            gbc.gridx = 1;
            JTextField fieldJumlah = new JTextField("5", 8);
            fieldJumlah.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            fieldJumlah.setPreferredSize(new Dimension(120, 35));
            fieldJumlah.setBorder(new CompoundBorder(
                    new LineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(5, 8, 5, 8)));
            panelThread.add(fieldJumlah, gbc);

            gbc.gridx = 2;
            JButton btnJalankan = new JButton("Jalankan Thread");
            btnJalankan.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnJalankan.setBackground(new Color(56, 142, 60));
            btnJalankan.setForeground(Color.WHITE);
            btnJalankan.setBorder(new EmptyBorder(8, 15, 8, 15));
            btnJalankan.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelThread.add(btnJalankan, gbc);

            gbc.gridx = 3;
            JButton btnStop = new JButton("Stop Thread");
            btnStop.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnStop.setBackground(new Color(231, 76, 60));
            btnStop.setForeground(Color.WHITE);
            btnStop.setBorder(new EmptyBorder(8, 15, 8, 15));
            btnStop.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnStop.setEnabled(false);
            panelThread.add(btnStop, gbc);

            gbc.gridx = 4;
            JButton btnResume = new JButton("Lanjut Thread");
            btnResume.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnResume.setBackground(new Color(52, 152, 219));
            btnResume.setForeground(Color.WHITE);
            btnResume.setBorder(new EmptyBorder(8, 15, 8, 15));
            btnResume.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnResume.setEnabled(false);
            panelThread.add(btnResume, gbc);

            gbc.gridx = 5;
            JButton btnForceStop = new JButton("Hentikan Total");
            btnForceStop.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnForceStop.setBackground(new Color(192, 57, 43));
            btnForceStop.setForeground(Color.WHITE);
            btnForceStop.setBorder(new EmptyBorder(8, 15, 8, 15));
            btnForceStop.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnForceStop.setEnabled(false);
            panelThread.add(btnForceStop, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 6;
            gbc.insets = new Insets(20, 10, 10, 10);

            JTextArea areaThread = new JTextArea(15, 50);
            areaThread.setFont(new Font("Consolas", Font.PLAIN, 14));
            areaThread.setBackground(new Color(249, 249, 249));
            areaThread.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(10, 10, 10, 10)));
            areaThread.setEditable(false);

            JScrollPane scrollThread = new JScrollPane(areaThread);
            scrollThread.setPreferredSize(new Dimension(800, 300));
            panelThread.add(scrollThread, gbc);

            areaKiriMap.put(menu, areaThread);
            btnHitungKiriMap.put(menu, btnJalankan);

            // Thread management variables
            AtomicBoolean isRunning = new AtomicBoolean(false);
            AtomicBoolean isPaused = new AtomicBoolean(false);
            java.util.List<Thread> activeThreads = new java.util.concurrent.CopyOnWriteArrayList<>();
            java.util.List<BendaGeometri> pausedBendaList = new java.util.ArrayList<>();
            AtomicInteger completedThreads = new AtomicInteger(0);
            AtomicInteger totalThreads = new AtomicInteger(0);
            AtomicInteger currentBendaIndex = new AtomicInteger(0);
            AtomicInteger currentThreadIndex = new AtomicInteger(0);

            // Event handler for Jalankan Thread button
            btnJalankan.addActionListener(e -> {
                try {
                    int jumlahThread = Integer.parseInt(fieldJumlah.getText());
                    if (jumlahThread <= 0) {
                        showErrorDialog("Jumlah thread harus lebih dari 0");
                        return;
                    }

                    // Reset UI
                    areaThread.setText("");
                    btnJalankan.setEnabled(false);
                    btnStop.setEnabled(true);
                    btnResume.setEnabled(false);
                    btnForceStop.setEnabled(true);
                    fieldJumlah.setEnabled(false);
                    isRunning.set(true);
                    isPaused.set(false);
                    activeThreads.clear();
                    completedThreads.set(0);

                    // Create all BendaGeometri objects (excluding parent classes)
                    java.util.List<BendaGeometri> allBendaGeometri = createAllBendaGeometri();
                    totalThreads.set(allBendaGeometri.size() * jumlahThread);

                    areaThread.append("=== EKSEKUSI THREAD DIMULAI ===\n");
                    areaThread.append("Jumlah thread per benda: " + jumlahThread + "\n");
                    areaThread.append("Total benda geometri: " + allBendaGeometri.size() + "\n");
                    areaThread.append("Total thread yang akan dijalankan: " + totalThreads.get() + "\n\n");

                    // Run thread execution in background to avoid blocking UI
                    new Thread(() -> {
                        // Start threads for each BendaGeometri object
                        for (BendaGeometri benda : allBendaGeometri) {
                            for (int i = 0; i < jumlahThread; i++) {
                                if (!isRunning.get()) {
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append("Penghentian diminta, berhenti membuat thread baru\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                    });
                                    break;
                                }
                                
                                // Create thread directly using the benda object
                                if (benda instanceof Runnable) {
                                    Thread thread = new Thread((Runnable) benda);
                                    thread.setName("Thread-" + getBendaName(benda) + "-" + (i + 1));
                                    
                                    // Display start message
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append(" [" + thread.getName() + "] Memulai perhitungan " + getBendaName(benda) + "...\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                    });
                                    
                                    activeThreads.add(thread);
                                    thread.start();
                                    
                                    // Wait for thread to complete using join (in background thread)
                                    try {
                                        thread.join();
                                        
                                        // Display finish message with results
                                        String result = getBendaGeometriResult(benda);
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append(" [" + thread.getName() + "] " + getBendaName(benda) + " selesai\n");
                                            areaThread.append("   " + result + "\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                        });
                                        
                                        // Update progress
                                        int completed = completedThreads.incrementAndGet();
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append(" Progress: " + completed + "/" + totalThreads.get() + " thread selesai\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                            
                                            if (completed >= totalThreads.get()) {
                                                areaThread.append("\n=== EKSEKUSI THREAD SELESAI ===\n");
                                                areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                                
                                                // Reset UI state when all threads complete
                                                btnJalankan.setEnabled(true);
                                                btnStop.setEnabled(false);
                                                btnResume.setEnabled(false);
                                                btnForceStop.setEnabled(false);
                                                fieldJumlah.setEnabled(true);
                                                isRunning.set(false);
                                                isPaused.set(false);
                                                currentBendaIndex.set(0);
                                                currentThreadIndex.set(0);
                                            }
                                        });
                                        
                                    } catch (InterruptedException ex) {
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append("[" + thread.getName() + "] Thread diinterupsi\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                        });
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                } else {
                                    // If benda doesn't implement Runnable, skip it
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append("" + getBendaName(benda) + " tidak mengimplementasikan Runnable, dilewati\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                    });
                                    
                                    // Update progress for skipped items
                                    int completed = completedThreads.incrementAndGet();
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append("Progress: " + completed + "/" + totalThreads.get() + " thread selesai\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                        
                                        if (completed >= totalThreads.get()) {
                                            areaThread.append("\n=== EKSEKUSI THREAD SELESAI ===\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                            
                                            // Reset UI state when all threads complete
                                            btnJalankan.setEnabled(true);
                                            btnStop.setEnabled(false);
                                            btnResume.setEnabled(false);
                                            btnForceStop.setEnabled(false);
                                            fieldJumlah.setEnabled(true);
                                            isRunning.set(false);
                                            isPaused.set(false);
                                            currentBendaIndex.set(0);
                                            currentThreadIndex.set(0);
                                        }
                                    });
                                }
                                
                                // Small delay between thread starts
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                            }
                            
                            // Check if we should stop creating more threads
                            if (!isRunning.get()) {
                                break;
                            }
                        }
                    }).start();

                } catch (NumberFormatException ex) {
                    showErrorDialog("Input jumlah thread harus berupa angka");
                    btnJalankan.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnResume.setEnabled(false);
                    btnForceStop.setEnabled(false);
                    fieldJumlah.setEnabled(true);
                } catch (Exception ex) {
                    showErrorDialog("Error: " + ex.getMessage());
                    btnJalankan.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnResume.setEnabled(false);
                    btnForceStop.setEnabled(false);
                    fieldJumlah.setEnabled(true);
                }
            });

            // Event handler for Stop Thread button
            btnStop.addActionListener(e -> {
                if (isRunning.get()) {
                    areaThread.append("\n=== MENGHEMTI SEMUA THREAD ===\n");
                    
                    isRunning.set(false);
                    isPaused.set(true);
                    AtomicInteger interruptedCount = new AtomicInteger(0);
                    
                    // Simpan state thread yang sedang berjalan
                    pausedBendaList.clear();
                    for (BendaGeometri benda : createAllBendaGeometri()) {
                        pausedBendaList.add(benda);
                    }
                    
                    // First, interrupt all active threads
                    for (Thread thread : activeThreads) {
                        if (thread.isAlive()) {
                            thread.interrupt();
                            interruptedCount.incrementAndGet();
                            areaThread.append("Menginterupsi " + thread.getName() + "\n");
                        }
                    }
                    
                    areaThread.append("Total thread yang diinterupsi: " + interruptedCount.get() + "\n");
                    
                    // Wait for threads to respond to interruption (with timeout)
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000); // Wait 2 seconds for threads to respond
                            
                            // Check which threads are still alive
                            AtomicInteger stillAlive = new AtomicInteger(0);
                            for (Thread thread : activeThreads) {
                                if (thread.isAlive()) {
                                    stillAlive.incrementAndGet();
                                }
                            }
                            
                            SwingUtilities.invokeLater(() -> {
                                if (stillAlive.get() > 0) {
                                    areaThread.append("⚠️ " + stillAlive + " thread masih berjalan setelah 2 detik\n");
                                    areaThread.append("Thread akan berhenti secara otomatis setelah selesai perhitungan\n");
                                    
                                    // Force stop remaining threads after additional timeout
                                    new Thread(() -> {
                                        try {
                                            Thread.sleep(5000); // Wait additional 5 seconds
                                            
                                            AtomicInteger forceStopped = new AtomicInteger(0);
                                            for (Thread thread : activeThreads) {
                                                if (thread.isAlive()) {
                                                    forceStopped.incrementAndGet();
                                                }
                                            }
                                            
                                            if (forceStopped.get() > 0) {
                                                SwingUtilities.invokeLater(() -> {
                                                    areaThread.append("" + forceStopped + " thread masih berjalan setelah timeout total 7 detik\n");
                                                    areaThread.append("Thread ini akan berhenti secara otomatis ketika selesai\n");
                                                    areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                                });
                                            }
                                        } catch (InterruptedException ex) {
                                            // Ignore
                                        }
                                    }).start();
                                }
                                areaThread.append("=== EKSEKUSI THREAD DIHENTIKAN ===\n");
                                areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                
                                // Reset UI state
                                btnJalankan.setEnabled(false);
                                btnStop.setEnabled(false);
                                btnResume.setEnabled(true);
                                btnForceStop.setEnabled(true);
                                fieldJumlah.setEnabled(false);
                            });
                            
                        } catch (InterruptedException ex) {
                            // Ignore
                        }
                    }).start();
                    
                    areaThread.setCaretPosition(areaThread.getDocument().getLength());
                }
            });

            // Event handler for Resume Thread button
            btnResume.addActionListener(e -> {
                if (isPaused.get()) {
                    areaThread.append("\n=== MELANJUTKAN EKSEKUSI THREAD ===\n");
                    
                    isRunning.set(true);
                    isPaused.set(false);
                    btnResume.setEnabled(false);
                    btnStop.setEnabled(true);
                    
                    // Run thread execution in background to avoid blocking UI
                    new Thread(() -> {
                        // Start threads for each remaining BendaGeometri object
                        for (int bendaIndex = currentBendaIndex.get(); bendaIndex < pausedBendaList.size(); bendaIndex++) {
                            BendaGeometri benda = pausedBendaList.get(bendaIndex);
                            for (int i = currentThreadIndex.get(); i < Integer.parseInt(fieldJumlah.getText()); i++) {
                                if (!isRunning.get()) {
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append("Penghentian diminta, berhenti membuat thread baru\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                    });
                                    break;
                                }
                                
                                // Create thread directly using the benda object
                                if (benda instanceof Runnable) {
                                    Thread thread = new Thread((Runnable) benda);
                                    thread.setName("Thread-" + getBendaName(benda) + "-" + (i + 1));
                                    
                                    // Display start message
                                    SwingUtilities.invokeLater(() -> {
                                        areaThread.append(" [" + thread.getName() + "] Memulai perhitungan " + getBendaName(benda) + "...\n");
                                        areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                    });
                                    
                                    activeThreads.add(thread);
                                    thread.start();
                                    
                                    // Wait for thread to complete using join (in background thread)
                                    try {
                                        thread.join();
                                        
                                        // Display finish message with results
                                        String result = getBendaGeometriResult(benda);
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append(" [" + thread.getName() + "] " + getBendaName(benda) + " selesai\n");
                                            areaThread.append("   " + result + "\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                        });
                                        
                                        // Update progress
                                        int completed = completedThreads.incrementAndGet();
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append(" Progress: " + completed + "/" + totalThreads.get() + " thread selesai\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                            
                                            if (completed >= totalThreads.get()) {
                                                areaThread.append("\n=== EKSEKUSI THREAD SELESAI ===\n");
                                                areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                                
                                                // Reset UI state when all threads complete
                                                btnJalankan.setEnabled(true);
                                                btnStop.setEnabled(false);
                                                btnResume.setEnabled(false);
                                                btnForceStop.setEnabled(false);
                                                fieldJumlah.setEnabled(true);
                                                isRunning.set(false);
                                                isPaused.set(false);
                                                currentBendaIndex.set(0);
                                                currentThreadIndex.set(0);
                                            }
                                        });
                                        
                                    } catch (InterruptedException ex) {
                                        SwingUtilities.invokeLater(() -> {
                                            areaThread.append("[" + thread.getName() + "] Thread diinterupsi\n");
                                            areaThread.setCaretPosition(areaThread.getDocument().getLength());
                                        });
                                        Thread.currentThread().interrupt();
                                        break;
                                    }
                                }
                                
                                // Small delay between thread starts
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                    break;
                                }
                            }
                            
                            // Reset thread index for next benda
                            currentThreadIndex.set(0);
                            currentBendaIndex.incrementAndGet();
                            
                            // Check if we should stop creating more threads
                            if (!isRunning.get()) {
                                break;
                            }
                        }
                    }).start();
                }
            });

            // Event handler for Force Stop Thread button
            btnForceStop.addActionListener(e -> {
                if (isRunning.get() || isPaused.get()) {
                    areaThread.append("\n=== MENGHEMTI TOTAL SEMUA THREAD ===\n");
                    
                    isRunning.set(false);
                    isPaused.set(false);
                    
                    // Interrupt all active threads
                    for (Thread thread : activeThreads) {
                        if (thread.isAlive()) {
                            thread.interrupt();
                            areaThread.append("Menginterupsi " + thread.getName() + "\n");
                        }
                    }
                    
                    // Clear all thread-related data
                    activeThreads.clear();
                    pausedBendaList.clear();
                    completedThreads.set(0);
                    currentBendaIndex.set(0);
                    currentThreadIndex.set(0);
                    
                    areaThread.append("=== EKSEKUSI THREAD DIHENTIKAN TOTAL ===\n");
                    areaThread.setCaretPosition(areaThread.getDocument().getLength());
                    
                    // Reset UI state
                    btnJalankan.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnResume.setEnabled(false);
                    btnForceStop.setEnabled(false);
                    fieldJumlah.setEnabled(true);
                }
            });

            return panelThread;
        }
        JPanel panelMain = new JPanel(new GridLayout(1, 2, 30, 0));
        panelMain.setBackground(Color.WHITE);
        panelMain.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Panel kiri
        JPanel panelKiri = new JPanel(new GridBagLayout());
        panelKiri.setBackground(Color.WHITE);
        panelKiri.setBorder(new CompoundBorder(
            new TitledBorder(
                new LineBorder(new Color(30, 136, 229), 2, true), 
                menu
            ),
            new EmptyBorder(15, 15, 15, 15)
        ));
        TitledBorder titledBorder = (TitledBorder) ((CompoundBorder) panelKiri.getBorder()).getOutsideBorder();
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 18));
        titledBorder.setTitleColor(new Color(30, 136, 229));
        
        GridBagConstraints gbcKiri = new GridBagConstraints();
        gbcKiri.insets = new Insets(10, 5, 10, 5);
        gbcKiri.gridx = 0;
        gbcKiri.gridy = 0;
        gbcKiri.fill = GridBagConstraints.HORIZONTAL;
        gbcKiri.weightx = 1.0;
        
        // Menambahkan strut vertikal untuk menyelaraskan dengan JComboBox di panel kanan
        panelKiri.add(Box.createVerticalStrut(35), gbcKiri);
        
        JTextArea areaKiri = new JTextArea(7, 28);
        areaKiri.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaKiri.setBackground(new Color(249, 249, 249));
        areaKiri.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(10, 10, 10, 10)
        ));
        areaKiri.setEditable(false);
        
        JScrollPane scrollKiri = new JScrollPane(areaKiri);
        gbcKiri.gridy++;
        panelKiri.add(scrollKiri, gbcKiri);
        
        JPanel panelBtnKiri = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelBtnKiri.setBackground(Color.WHITE);
        
        JButton btnHitungKiri = new JButton("Hitung");
        styleButton(btnHitungKiri, new Color(56, 142, 60));
        
        JButton btnHitungUlangKiri = new JButton("Hitung Ulang");
        styleButton(btnHitungUlangKiri, new Color(30, 136, 229));
        
        panelBtnKiri.add(btnHitungKiri);
        panelBtnKiri.add(btnHitungUlangKiri);
        
        gbcKiri.gridy++;
        gbcKiri.insets = new Insets(15, 5, 5, 5);
        panelKiri.add(panelBtnKiri, gbcKiri);
        
        // Panel kanan
        JPanel panelKanan = new JPanel(new GridBagLayout());
        panelKanan.setBackground(Color.WHITE);
        panelKanan.setBorder(new CompoundBorder(
            new TitledBorder(
                new LineBorder(new Color(30, 136, 229), 2, true), 
                "Turunan " + menu
            ),
            new EmptyBorder(15, 15, 15, 15)
        ));
        titledBorder = (TitledBorder) ((CompoundBorder) panelKanan.getBorder()).getOutsideBorder();
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 18));
        titledBorder.setTitleColor(new Color(30, 136, 229));
        
        GridBagConstraints gbcKanan = new GridBagConstraints();
        gbcKanan.insets = new Insets(10, 5, 10, 5);
        gbcKanan.gridx = 0;
        gbcKanan.gridy = 0;
        gbcKanan.fill = GridBagConstraints.HORIZONTAL;
        gbcKanan.weightx = 1.0;
        
        JComboBox<String> comboTurunan = new JComboBox<>(turunanMap.get(menu));
        comboTurunan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboTurunan.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 8, 5, 8));
                return this;
            }
        });
        comboTurunan.setPreferredSize(new Dimension(250, 35));
        comboTurunan.setBackground(Color.WHITE);

        panelKanan.add(comboTurunan, gbcKanan);
        
        gbcKanan.gridy++;
        JTextArea areaKanan = new JTextArea(7, 28);
        areaKanan.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaKanan.setBackground(new Color(249, 249, 249));
        areaKanan.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(10, 10, 10, 10)
        ));
        areaKanan.setEditable(false);
        
        JScrollPane scrollKanan = new JScrollPane(areaKanan);
        panelKanan.add(scrollKanan, gbcKanan);
        
        JPanel panelBtnKanan = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelBtnKanan.setBackground(Color.WHITE);
        
        JButton btnHitungKanan = new JButton("Hitung");
        styleButton(btnHitungKanan, new Color(56, 142, 60));
        
        JButton btnHitungUlangKanan = new JButton("Hitung Ulang");
        styleButton(btnHitungUlangKanan, new Color(30, 136, 229));
        
        panelBtnKanan.add(btnHitungKanan);
        panelBtnKanan.add(btnHitungUlangKanan);
        
        gbcKanan.gridy++;
        gbcKanan.insets = new Insets(15, 5, 5, 5);
        panelKanan.add(panelBtnKanan, gbcKanan);
        
        // Simpan komponen ke map
        comboTurunanMap.put(menu, comboTurunan);
        areaKiriMap.put(menu, areaKiri);
        areaKananMap.put(menu, areaKanan);
        btnHitungKiriMap.put(menu, btnHitungKiri);
        btnHitungUlangKiriMap.put(menu, btnHitungUlangKiri);
        btnHitungKananMap.put(menu, btnHitungKanan);
        btnHitungUlangKananMap.put(menu, btnHitungUlangKanan);
        
        panelMain.add(panelKiri);
        panelMain.add(panelKanan);
        
        return panelMain;
    }

     private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            sb.append(info.paramLabels[i]).append(": ").append(paramValues[i]).append("\n");
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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String rawClassName = info.className.substring(info.className.lastIndexOf('.') + 1);
        String formattedClassName = rawClassName.replaceAll("([A-Z])", " $1").trim();

        JLabel titleLabel = new JLabel(formattedClassName);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(info.paramLabels.length, 2, 8, 8));
        inputPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        JTextField[] fields = new JTextField[info.paramLabels.length];
        for (int i = 0; i < info.paramLabels.length; i++) {
            inputPanel.add(new JLabel(info.paramLabels[i]));
            fields[i] = new JTextField(info.defaultValues[i].toString());
            inputPanel.add(fields[i]);
        }
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        int res = JOptionPane.showConfirmDialog(this, mainPanel, "Hitung Ulang", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION)
            return null;
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

    // Helper method to create Class<?>[] for reflection
    private Class<?>[] createParamTypes(int count) {
        Class<?>[] types = new Class<?>[count];
        Arrays.fill(types, double.class);
        return types;
    }

    // Safe method to get BendaGeometri name
    private String getBendaName(BendaGeometri benda) {
        try {
            // Try to call getNamaBenda() using reflection
            Method getNamaBendaMethod = benda.getClass().getMethod("getNamaBenda");
            return (String) getNamaBendaMethod.invoke(benda);
        } catch (Exception e) {
            // Fallback to class name if getNamaBenda() is not available
            return benda.getClass().getSimpleName();
        }
    }

    // Create all BendaGeometri objects (excluding parent classes BendaGeometri and Benda2D)
    private java.util.List<BendaGeometri> createAllBendaGeometri() {
        java.util.List<BendaGeometri> bendaList = new CopyOnWriteArrayList<>();
        
        try {
            // 2D Objects
            bendaList.add(new Persegi(10.0));
            bendaList.add(new PersegiPanjang(10.0, 15.0));
            bendaList.add(new Segitiga(8.0, 10.0, 6.0, 6.0));
            bendaList.add(new JajaranGenjang(8.0, 5.0, 6.0));
            bendaList.add(new BelahKetupat(10.0, 12.0, 8.0));
            bendaList.add(new Trapesium(5.0, 8.0, 6.0, 4.0, 4.0));
            bendaList.add(new LayangLayang(8.0, 12.0, 6.0, 8.0));
            bendaList.add(new Lingkaran(7.0));
            bendaList.add(new JuringLingkaran(10.0, 60.0));
            bendaList.add(new TemberengLingkaran(10.0, 90.0));
            
            // 3D Objects
            bendaList.add(new PrismaSegitiga(8.0, 10.0, 6.0, 6.0, 12.0));
            bendaList.add(new PrismaPersegi(10.0, 8.0));
            bendaList.add(new PrismaPersegiPanjang(10.0, 8.0, 12.0));
            bendaList.add(new PrismaJajaranGenjang(8.0, 5.0, 6.0, 12.0));
            bendaList.add(new PrismaTrapesium(5.0, 8.0, 6.0, 4.0, 4.0, 12.0));
            bendaList.add(new PrismaBelahKetupat(10.0, 12.0, 8.0, 12.0));
            bendaList.add(new PrismaLayangLayang(8.0, 12.0, 6.0, 8.0, 12.0));
            
            bendaList.add(new LimasSegitiga(8.0, 10.0, 6.0, 6.0, 12.0));
            bendaList.add(new LimasPersegi(10.0, 12.0));
            bendaList.add(new LimasPersegiPanjang(10.0, 8.0, 12.0));
            bendaList.add(new LimasJajaranGenjang(8.0, 5.0, 6.0, 12.0));
            bendaList.add(new LimasTrapesium(5.0, 8.0, 6.0, 4.0, 4.0, 12.0));
            bendaList.add(new LimasBelahKetupat(10.0, 12.0, 8.0, 12.0));
            bendaList.add(new LimasLayangLayang(8.0, 12.0, 6.0, 8.0, 12.0));
            
            bendaList.add(new Bola(10.0));
            bendaList.add(new Kerucut(8.0, 12.0));
            bendaList.add(new KerucutTerpancung(6.0, 10.0, 8.0));
            bendaList.add(new Tabung(8.0, 12.0));
            bendaList.add(new JuringBola(10.0, 60.0));
            bendaList.add(new TemberengBola(10.0, 6.0));
            bendaList.add(new CincinBola(6.0, 10.0));
            
        } catch (Exception e) {
            System.err.println("Error creating BendaGeometri objects: " + e.getMessage());
        }
        
        return bendaList;
    }

    // Get calculation results for a BendaGeometri object
    private String getBendaGeometriResult(BendaGeometri benda) {
        StringBuilder result = new StringBuilder();
        
        try {
            if (benda instanceof Benda2D) {
                Benda2D benda2D = (Benda2D) benda;
                result.append("Luas: ").append(String.format("%.2f", benda2D.menghitungLuas())).append(" cm², ");
                result.append("Keliling: ").append(String.format("%.2f", benda2D.menghitungKeliling())).append(" cm");
            } else if (benda instanceof PrismaSegitiga || benda instanceof PrismaPersegi ||
                    benda instanceof PrismaPersegiPanjang || benda instanceof PrismaJajaranGenjang ||
                    benda instanceof PrismaTrapesium || benda instanceof PrismaBelahKetupat ||
                    benda instanceof PrismaLayangLayang || benda instanceof LimasSegitiga ||
                    benda instanceof LimasPersegi || benda instanceof LimasPersegiPanjang ||
                    benda instanceof LimasJajaranGenjang || benda instanceof LimasTrapesium ||
                    benda instanceof LimasBelahKetupat || benda instanceof LimasLayangLayang ||
                    benda instanceof Bola || benda instanceof Kerucut ||
                    benda instanceof KerucutTerpancung || benda instanceof Tabung ||
                    benda instanceof TemberengBola || benda instanceof JuringBola ||
                    benda instanceof CincinBola) {
                
                double volume = 0;
                double luasPermukaan = 0;

                if (benda instanceof PrismaSegitiga) {
                    PrismaSegitiga prisma = (PrismaSegitiga) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaPersegi) {
                    PrismaPersegi prisma = (PrismaPersegi) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaPersegiPanjang) {
                    PrismaPersegiPanjang prisma = (PrismaPersegiPanjang) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaJajaranGenjang) {
                    PrismaJajaranGenjang prisma = (PrismaJajaranGenjang) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaTrapesium) {
                    PrismaTrapesium prisma = (PrismaTrapesium) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaBelahKetupat) {
                    PrismaBelahKetupat prisma = (PrismaBelahKetupat) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof PrismaLayangLayang) {
                    PrismaLayangLayang prisma = (PrismaLayangLayang) benda;
                    volume = prisma.menghitungVolume();
                    luasPermukaan = prisma.menghitungLuasPermukaan();
                } else if (benda instanceof LimasSegitiga) {
                    LimasSegitiga limas = (LimasSegitiga) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasPersegi) {
                    LimasPersegi limas = (LimasPersegi) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasPersegiPanjang) {
                    LimasPersegiPanjang limas = (LimasPersegiPanjang) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasJajaranGenjang) {
                    LimasJajaranGenjang limas = (LimasJajaranGenjang) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasTrapesium) {
                    LimasTrapesium limas = (LimasTrapesium) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasBelahKetupat) {
                    LimasBelahKetupat limas = (LimasBelahKetupat) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof LimasLayangLayang) {
                    LimasLayangLayang limas = (LimasLayangLayang) benda;
                    volume = limas.menghitungVolume();
                    luasPermukaan = limas.menghitungLuasPermukaan();
                } else if (benda instanceof Bola) {
                    Bola bola = (Bola) benda;
                    volume = bola.menghitungVolume();
                    luasPermukaan = bola.menghitungLuasPermukaan();
                } else if (benda instanceof Kerucut) {
                    Kerucut kerucut = (Kerucut) benda;
                    volume = kerucut.menghitungVolume();
                    luasPermukaan = kerucut.menghitungLuasPermukaan();
                } else if (benda instanceof KerucutTerpancung) {
                    KerucutTerpancung kerucut = (KerucutTerpancung) benda;
                    volume = kerucut.menghitungVolume();
                    luasPermukaan = kerucut.menghitungLuasPermukaan();
                } else if (benda instanceof Tabung) {
                    Tabung tabung = (Tabung) benda;
                    volume = tabung.menghitungVolume();
                    luasPermukaan = tabung.menghitungLuasPermukaan();
                } else if (benda instanceof TemberengBola) {
                    TemberengBola tembereng = (TemberengBola) benda;
                    volume = tembereng.menghitungVolume();
                    luasPermukaan = tembereng.menghitungLuasPermukaan();
                } else if (benda instanceof JuringBola) {
                    JuringBola juring = (JuringBola) benda;
                    volume = juring.menghitungVolume();
                    luasPermukaan = juring.menghitungLuasPermukaan();
                } else if (benda instanceof CincinBola) {
                    CincinBola cincin = (CincinBola) benda;
                    volume = cincin.menghitungVolume();
                    luasPermukaan = cincin.menghitungLuasPermukaan();
                }

                result.append("Volume: ").append(String.format("%.2f", volume)).append(" cm³, ");
                result.append("Luas Permukaan: ").append(String.format("%.2f", luasPermukaan)).append(" cm²");
            } else {
                result.append("Tipe benda tidak dikenali");
            }
        } catch (Exception e) {
            result.append("Error: ").append(e.getMessage());
        }
        
        return result.toString();
    }

    // New method to handle overloaded calculations and build result string
    private String buildOverloadedResultString(Object obj, GeometriInfo info, Double[] values) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Parameter Input:\n");
        for (int i = 0; i < info.paramLabels.length; i++) {
            sb.append(info.paramLabels[i]).append(": ").append(values[i]).append("\n");
        }
        sb.append("\nHasil Perhitungan (Overload):\n");
        if (obj instanceof PrismaSegitiga) {
            PrismaSegitiga ps = (PrismaSegitiga) obj;
            sb.append("Volume: ").append(String.format("%.2f", ps.menghitungVolume(values[0], values[1], values[4]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", ps.menghitungLuasPermukaan(values[0], values[1], values[2], values[3], values[4]))).append("\n");
        } else if (obj instanceof PrismaPersegi) {
            PrismaPersegi pp = (PrismaPersegi) obj;
            sb.append("Volume: ").append(String.format("%.2f", pp.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", pp.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof PrismaPersegiPanjang) {
            PrismaPersegiPanjang ppp = (PrismaPersegiPanjang) obj;
            sb.append("Volume: ").append(String.format("%.2f", ppp.menghitungVolume(values[0], values[1], values[2]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", ppp.menghitungLuasPermukaan(values[0], values[1], values[2]))).append("\n");
        } else if (obj instanceof PrismaJajaranGenjang) {
            PrismaJajaranGenjang pjg = (PrismaJajaranGenjang) obj;
            sb.append("Volume: ").append(String.format("%.2f", pjg.menghitungVolume(values[0], values[1], values[3]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", pjg.menghitungLuasPermukaan(values[0], values[1], values[2], values[3]))).append("\n");
        } else if (obj instanceof PrismaBelahKetupat) {
            PrismaBelahKetupat pbk = (PrismaBelahKetupat) obj;
            sb.append("Volume: ").append(String.format("%.2f", pbk.menghitungVolume(values[0], values[1], values[3]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", pbk.menghitungLuasPermukaan(values[0], values[1], values[2], values[3]))).append("\n");
        } else if (obj instanceof PrismaTrapesium) {
            PrismaTrapesium pt = (PrismaTrapesium) obj;
            sb.append("Volume: ").append(String.format("%.2f", pt.menghitungVolume(values[0], values[1], values[2], values[5]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", pt.menghitungLuasPermukaan(values[0], values[1], values[2], values[3], values[4], values[5]))).append("\n");
        } else if (obj instanceof PrismaLayangLayang) {
            PrismaLayangLayang pll = (PrismaLayangLayang) obj;
            sb.append("Volume: ").append(String.format("%.2f", pll.menghitungVolume(values[0], values[1], values[4]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", pll.menghitungLuasPermukaan(values[0], values[1], values[2], values[3], values[4]))).append("\n");
        }
        // Limas
        else if (obj instanceof LimasSegitiga) {
            LimasSegitiga ls = (LimasSegitiga) obj;
            sb.append("Volume: ").append(String.format("%.2f", ls.menghitungVolume(values[0], values[1], values[4]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", ls.menghitungLuasPermukaan(values[0], values[1], values[2], values[3], values[4]))).append("\n");
        } else if (obj instanceof LimasPersegi) {
            LimasPersegi lp = (LimasPersegi) obj;
            sb.append("Volume: ").append(String.format("%.2f", lp.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", lp.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof LimasPersegiPanjang) {
            LimasPersegiPanjang lpp = (LimasPersegiPanjang) obj;
            sb.append("Volume: ").append(String.format("%.2f", lpp.menghitungVolume(values[0], values[1], values[2]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", lpp.menghitungLuasPermukaan(values[0], values[1], values[2]))).append("\n");
        } else if (obj instanceof LimasJajaranGenjang) {
            LimasJajaranGenjang ljg = (LimasJajaranGenjang) obj;
            sb.append("Volume: ").append(String.format("%.2f", ljg.menghitungVolume(values[0], values[1], values[3]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", ljg.menghitungLuasPermukaan(values[0], values[1], values[2], values[3]))).append("\n");
        } else if (obj instanceof LimasBelahKetupat) {
            LimasBelahKetupat lbk = (LimasBelahKetupat) obj;
            sb.append("Volume: ").append(String.format("%.2f", lbk.menghitungVolume(values[0], values[1], values[3]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", lbk.menghitungLuasPermukaan(values[0], values[1], values[2], values[3]))).append("\n");
        } else if (obj instanceof LimasTrapesium) {
            LimasTrapesium lt = (LimasTrapesium) obj;
            sb.append("Volume: ").append(String.format("%.2f", lt.menghitungVolume(values[0], values[1], values[2], values[5]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", lt.menghitungLuasPermukaan(values[0], values[1], values[2], values[5]))).append("\n");
        } else if (obj instanceof LimasLayangLayang) {
            LimasLayangLayang lll = (LimasLayangLayang) obj;
            sb.append("Volume: ").append(String.format("%.2f", lll.menghitungVolume(values[0], values[1], values[4]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", lll.menghitungLuasPermukaan(values[0], values[1], values[2], values[3], values[4]))).append("\n");
        }
        else if (obj instanceof Tabung) {
            Tabung t = (Tabung) obj;
            sb.append("Volume: ").append(String.format("%.2f", t.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", t.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof Kerucut) {
            Kerucut k = (Kerucut) obj;
            sb.append("Volume: ").append(String.format("%.2f", k.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", k.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof KerucutTerpancung) {
            KerucutTerpancung kt = (KerucutTerpancung) obj;
            sb.append("Volume: ").append(String.format("%.2f", kt.menghitungVolumeKerucutTerpancung(values[0], values[1], values[2]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", kt.menghitungLuasPermukaan(values[0], values[1], values[2]))).append("\n");
        } else if (obj instanceof Bola) {
            Bola b = (Bola) obj;
            sb.append("Volume: ").append(String.format("%.2f", b.menghitungVolume(values[0]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", b.menghitungLuasPermukaan(values[0]))).append("\n");
        } else if (obj instanceof JuringBola) {
            JuringBola jb = (JuringBola) obj;
            sb.append("Volume: ").append(String.format("%.2f", jb.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", jb.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof TemberengBola) {
            TemberengBola tb = (TemberengBola) obj;
            sb.append("Volume: ").append(String.format("%.2f", tb.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", tb.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        } else if (obj instanceof CincinBola) {
            CincinBola cb = (CincinBola) obj;
            sb.append("Volume: ").append(String.format("%.2f", cb.menghitungVolume(values[0], values[1]))).append("\n");
            sb.append("Luas Permukaan: ").append(String.format("%.2f", cb.menghitungLuasPermukaan(values[0], values[1]))).append("\n");
        }
        else if (obj instanceof Persegi) {
            Persegi p = (Persegi) obj;
            sb.append("Luas: ").append(String.format("%.2f", p.menghitungLuas(values[0]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", p.menghitungKeliling(values[0]))).append("\n");
        } else if (obj instanceof PersegiPanjang) {
            PersegiPanjang pp = (PersegiPanjang) obj;
            sb.append("Luas: ").append(String.format("%.2f", pp.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", pp.menghitungKeliling(values[0], values[1]))).append("\n");
        } else if (obj instanceof Segitiga) {
            Segitiga s = (Segitiga) obj;
            sb.append("Luas: ").append(String.format("%.2f", s.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", s.menghitungKeliling(values[2], values[3], values[0]))).append("\n");
        } else if (obj instanceof JajaranGenjang) {
            JajaranGenjang jg = (JajaranGenjang) obj;
            sb.append("Luas: ").append(String.format("%.2f", jg.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", jg.menghitungKeliling(values[0], values[2]))).append("\n");
        } else if (obj instanceof BelahKetupat) {
            BelahKetupat bk = (BelahKetupat) obj;
            sb.append("Luas: ").append(String.format("%.2f", bk.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", bk.menghitungKeliling(values[2]))).append("\n");
        } else if (obj instanceof Trapesium) {
            Trapesium t = (Trapesium) obj;
            sb.append("Luas: ").append(String.format("%.2f", t.menghitungLuas(values[0], values[1], values[2]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", t.menghitungKeliling(values[0], values[1], values[3], values[4]))).append("\n");
        } else if (obj instanceof LayangLayang) {
            LayangLayang ll = (LayangLayang) obj;
            sb.append("Luas: ").append(String.format("%.2f", ll.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", ll.menghitungKeliling(values[2], values[3]))).append("\n");
        } else if (obj instanceof JuringLingkaran) {
            JuringLingkaran jl = (JuringLingkaran) obj;
            sb.append("Luas: ").append(String.format("%.2f", jl.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", jl.menghitungKeliling(values[0], values[1]))).append("\n");
        } else if (obj instanceof TemberengLingkaran) {
            TemberengLingkaran tl = (TemberengLingkaran) obj;
            sb.append("Luas: ").append(String.format("%.2f", tl.menghitungLuas(values[0], values[1]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", tl.menghitungKeliling(values[0], values[1]))).append("\n");
        } else if (obj instanceof Lingkaran) {
            Lingkaran l = (Lingkaran) obj;
            sb.append("Luas: ").append(String.format("%.2f", l.menghitungLuas(values[0]))).append("\n");
            sb.append("Keliling: ").append(String.format("%.2f", l.menghitungKeliling(values[0]))).append("\n");
        }  else {
            sb.append("Jenis benda tidak dikenali atau perhitungan overload belum diimplementasikan.\n");
        }   

        return sb.toString();
    }

    public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new GeometriMainGUI().setVisible(true));
    }
}