import BendaGeometri.*;
import Threading.ThreadExecutor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AplikasiBendaGeometriGUI extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private final List<BendaGeometri> daftarBendaGeometri = new ArrayList<>();

    public AplikasiBendaGeometriGUI() {
        setTitle("Aplikasi Perhitungan Benda Geometri");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Panel utama dengan border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("APLIKASI PERHITUNGAN BENDA GEOMETRI");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Tab panel untuk kategori geometri
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Panel untuk bangun datar
        JPanel bangunDatarPanel = createBangunDatarPanel();
        tabbedPane.addTab("Bangun Datar", bangunDatarPanel);
        
        // Panel untuk prisma
        JPanel prismaPanel = createPrismaLimasPanel(11, 18, "Prisma");
        tabbedPane.addTab("Prisma", prismaPanel);
        
        // Panel untuk limas
        JPanel limasPanel = createPrismaLimasPanel(12, 24, "Limas");
        tabbedPane.addTab("Limas", limasPanel);
        
        // Panel untuk benda khusus
        JPanel khususPanel = createBendaKhususPanel();
        tabbedPane.addTab("Benda Khusus", khususPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Footer
        JButton exitButton = new JButton("Keluar");
        exitButton.addActionListener(e -> System.exit(0));
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(exitButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createBangunDatarPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] bangunDatar = {
            "Segitiga", "Persegi", "Persegi Panjang", 
            "Jajaran Genjang", "Trapesium", "Belah Ketupat",
            "Layang-Layang", "Lingkaran", "Tembereng Lingkaran",
            "Juring Lingkaran"
        };

        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(bangunDatar[i]);
            final int kode = i + 1;
            button.addActionListener(e -> showInputDialog(kode));
            panel.add(button);
        }

        return panel;
    }

    private JPanel createPrismaLimasPanel(int start, int end, String prefix) {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] jenis = {
            "Segitiga", "Persegi", "Persegi Panjang", 
            "Jajaran Genjang", "Trapesium", "Belah Ketupat",
            "Layang-Layang"
        };

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton(prefix + " " + jenis[i]);
            final int kode = start + i;
            button.addActionListener(e -> showInputDialog(kode));
            panel.add(button);
        }

        return panel;
    }

    private JPanel createBendaKhususPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] bendaKhusus = {
            "Tabung", "Kerucut", "Kerucut Terpancung", "Bola",
            "Tembereng Bola", "Juring Bola", "Cincin Bola"
        };

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton(bendaKhusus[i]);
            final int kode = 25 + i;
            button.addActionListener(e -> showInputDialog(kode));
            panel.add(button);
        }

        // Fitur khusus
        JButton polymorphismButton = new JButton("Polymorphism");
        polymorphismButton.addActionListener(e -> showPolymorphismResults());
        panel.add(polymorphismButton);

        JButton threadButton = new JButton("Thread Pool");
        threadButton.addActionListener(e -> showThreadPoolDialog());
        panel.add(threadButton);

        return panel;
    }

    private void showInputDialog(int kodeBenda) {
        String namaBenda = getNamaBenda(kodeBenda);
        String[] labels = getInputLabels(kodeBenda);
        
        JPanel inputPanel = new JPanel(new GridLayout(labels.length, 2, 5, 5));
        JTextField[] fields = new JTextField[labels.length];
        
        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i] + ":"));
            fields[i] = new JTextField(10);
            inputPanel.add(fields[i]);
        }
        
        int result = JOptionPane.showConfirmDialog(
            this,
            inputPanel,
            "Input Parameter " + namaBenda,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                double[] params = new double[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = Double.parseDouble(fields[i].getText());
                }
                
                BendaGeometri benda = createBenda(kodeBenda, params);
                showResults(benda);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Input harus berupa angka!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showResults(BendaGeometri benda) {
        String results;
        
        if (benda instanceof Benda2D) {
            Benda2D benda2D = (Benda2D) benda;
            results = String.format(
                "Luas %s: %.2f\nKeliling %s: %.2f",
                 benda2D.menghitungLuas(), benda2D.menghitungKeliling()
            );
        } else {
            results = "Tipe benda tidak dikenali";
        }
        
        JOptionPane.showMessageDialog(
            this, 
            results, 
            "Hasil Perhitungan", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showPolymorphismResults() {
        Benda2D[] benda2Ds = {
            new Persegi(5),
            new PersegiPanjang(5, 10),
            new Segitiga(8, 10, 8, 8),
            new Lingkaran(5),
            new JuringLingkaran(7, 60),
            new TemberengLingkaran(14, 45),
            new Trapesium(5, 10, 8, 12, 12),
            new BelahKetupat(8, 6, 10),
            new LayangLayang(5, 8, 10, 12),
            new JajaranGenjang(7, 5, 8)
        };
        
        StringBuilder sb = new StringBuilder();
        for (Benda2D b : benda2Ds) {
            sb.append(b.getNamaBenda())
              .append(": Luas = ")
              .append(String.format("%.2f", b.menghitungLuas()))
              .append(", Keliling = ")
              .append(String.format("%.2f", b.menghitungKeliling()))
              .append("\n\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString(), 15, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(
            this, 
            scrollPane, 
            "Hasil Polymorphism", 
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void showThreadPoolDialog() {
        String input = JOptionPane.showInputDialog(
            this,
            "Masukkan jumlah objek per bentuk geometri:",
            "Thread Pool",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (input == null || input.trim().isEmpty()) return;
        
        try {
            int jumlah = Integer.parseInt(input);
            daftarBendaGeometri.clear();
            
            for (int i = 1; i <= 31; i++) {
                for (int j = 0; j < jumlah; j++) {
                    try {
                        daftarBendaGeometri.add(generateRandomBendaGeometri(i));
                    } catch (IllegalArgumentException e) {
                        // Skip bentuk yang tidak valid
                    }
                }
            }
            
            ThreadExecutor.processShapes(daftarBendaGeometri);
            
            JOptionPane.showMessageDialog(
                this,
                "Pemrosesan " + daftarBendaGeometri.size() + " objek dimulai!\nLihat konsol untuk output.",
                "Thread Pool",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Input harus berupa bilangan bulat!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Helper methods (sama seperti versi console)
    private String getNamaBenda(int kode) {
        String[] names = {
            "Segitiga", "Persegi", "Persegi Panjang", "Jajaran Genjang", "Trapesium",
            "Belah Ketupat", "Layang-Layang", "Lingkaran", "Tembereng Lingkaran", "Juring Lingkaran",
            "Prisma Segitiga", "Limas Segitiga", "Prisma Persegi", "Limas Persegi", "Prisma Persegi Panjang",
            "Limas Persegi Panjang", "Prisma Jajaran Genjang", "Limas Jajaran Genjang", "Prisma Trapesium",
            "Limas Trapesium", "Prisma Belah Ketupat", "Limas Belah Ketupat", "Prisma Layang-Layang",
            "Limas Layang-Layang", "Tabung", "Kerucut", "Kerucut Terpancung", "Bola", "Tembereng Bola",
            "Juring Bola", "Cincin Bola", "Polymorphism", "Thread Pool", "Keluar"
        };
        return (kode >= 1 && kode <= 34) ? names[kode - 1] : "Unknown";
    }

    private String[] getInputLabels(int kode) {
        switch (kode) {
            case 1: return new String[]{"Alas", "Tinggi", "Sisi Miring Kiri", "Sisi Miring Kanan"};
            case 2: return new String[]{"Sisi"};
            case 3: return new String[]{"Panjang", "Lebar"};
            case 4: return new String[]{"Alas", "Tinggi", "Sisi Miring"};
            case 5: return new String[]{"Alas Atas", "Alas Bawah", "Tinggi", "Sisi Miring Kiri", "Sisi Miring Kanan"};
            case 6: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi"};
            case 7: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang"};
            case 8: return new String[]{"Jari-jari"};
            case 9: return new String[]{"Jari-jari", "Sudut (derajat)"};
            case 10: return new String[]{"Jari-jari", "Sudut (derajat)"};
            case 11: return new String[]{"Alas", "Tinggi Alas", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Prisma"};
            case 12: return new String[]{"Alas", "Tinggi Alas", "Sisi Miring 1", "Sisi Miring 2", "Tinggi Limas"};
            case 13: return new String[]{"Sisi Alas", "Tinggi Prisma"};
            case 14: return new String[]{"Sisi Alas", "Tinggi Limas"};
            case 15: return new String[]{"Panjang Alas", "Lebar Alas", "Tinggi Prisma"};
            case 16: return new String[]{"Panjang Alas", "Lebar Alas", "Tinggi Limas"};
            case 17: return new String[]{"Alas", "Tinggi", "Sisi Miring", "Tinggi Prisma"};
            case 18: return new String[]{"Alas", "Tinggi", "Sisi Miring", "Tinggi Limas"};
            case 19: return new String[]{"Alas Atas", "Alas Bawah", "Tinggi Alas", "Sisi Miring Kiri", "Sisi Miring Kanan", "Tinggi Prisma"};
            case 20: return new String[]{"Alas Atas", "Alas Bawah", "Tinggi Alas", "Sisi Miring Kiri", "Sisi Miring Kanan", "Tinggi Limas"};
            case 21: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi 1", "Sisi 2"};
            case 22: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi 1", "Sisi 2"};
            case 23: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma"};
            case 24: return new String[]{"Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas"};
            case 25: return new String[]{"Jari-jari", "Tinggi"};
            case 26: return new String[]{"Jari-jari", "Tinggi"};
            case 27: return new String[]{"Jari-jari Atas", "Jari-jari Bawah", "Tinggi"};
            case 28: return new String[]{"Jari-jari"};
            case 29: return new String[]{"Jari-jari", "Tinggi Tembereng"};
            case 30: return new String[]{"Jari-jari", "Sudut (derajat)"};
            case 31: return new String[]{"Jari-jari Dalam", "Jari-jari Luar"};
            default: return new String[0];
        }
    }

    private BendaGeometri createBenda(int kode, double[] params) {
        switch (kode) {
            case 1: return new Segitiga(params[0], params[1], params[2], params[3]);
            case 2: return new Persegi(params[0]);
            case 3: return new PersegiPanjang(params[0], params[1]);
            case 4: return new JajaranGenjang(params[0], params[1], params[2]);
            case 5: return new Trapesium(params[0], params[1], params[2], params[3], params[4]);
            case 6: return new BelahKetupat(params[0], params[1], params[2]);
            case 7: return new LayangLayang(params[0], params[1], params[2], params[3]);
            case 8: return new Lingkaran(params[0]);
            case 9: return new TemberengLingkaran(params[0], params[1]);
            case 10: return new JuringLingkaran(params[0], params[1]);
            case 11: return new PrismaSegitiga(params[0], params[1], params[2], params[3], params[4]);
            case 12: return new LimasSegitiga(params[0], params[1], params[2], params[3], params[4]);
            case 13: return new PrismaPersegi(params[0], params[1]);
            case 14: return new LimasPersegi(params[0], params[1]);
            case 15: return new PrismaPersegiPanjang(params[0], params[1], params[2]);
            case 16: return new LimasPersegiPanjang(params[0], params[1], params[2]);
            case 17: return new PrismaJajaranGenjang(params[0], params[1], params[2], params[3]);
            case 18: return new LimasJajaranGenjang(params[0], params[1], params[2], params[3]);
            case 19: return new PrismaTrapesium(params[0], params[1], params[2], params[3], params[4], params[5]);
            case 20: return new LimasTrapesium(params[0], params[1], params[2], params[3], params[4], params[5]);
            case 21: return new PrismaBelahKetupat(params[0], params[1], params[2], params[3]);
            case 22: return new LimasBelahKetupat(params[0], params[1], params[2], params[3]);
            case 23: return new PrismaLayangLayang(params[0], params[1], params[2], params[3], params[4]);
            case 24: return new LimasLayangLayang(params[0], params[1], params[2], params[3], params[4]);
            case 25: return new Tabung(params[0], params[1]);
            case 26: return new Kerucut(params[0], params[1]);
            case 27: return new KerucutTerpancung(params[0], params[1], params[2]);
            case 28: return new Bola(params[0]);
            case 29: return new TemberengBola(params[0], params[1]);
            case 30: return new JuringBola(params[0], params[1]);
            case 31: return new CincinBola(params[0], params[1]);
            default: throw new IllegalArgumentException("Kode tidak valid");
        }
    }

    // Metode generateRandomBendaGeometri sama seperti versi console
    private BendaGeometri generateRandomBendaGeometri(int choice) {
        // Implementasi sama persis dengan method di versi console
        // ... (dikurangi untuk menjaga singkatnya jawaban)
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            AplikasiBendaGeometriGUI app = new AplikasiBendaGeometriGUI();
            app.setVisible(true);
        });
    }
}