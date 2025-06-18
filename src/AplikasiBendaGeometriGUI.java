import BendaGeometri.*;
import Threading.ThreadExecutor;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AplikasiBendaGeometriGUI extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private final List<BendaGeometri> daftarBendaGeometri = new ArrayList<>();
    private JPanel mainPanel;
    private JPanel resultPanel;
    private JTextArea resultArea;
    private JButton hitungButton;
    private JButton hitungUlangButton;
    private BendaGeometri currentBenda;
    private String currentNamaBenda;
    private String[] currentLabels;
    private double[] currentValues;

    // Warna modern untuk UI
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color ACCENT_COLOR = new Color(231, 76, 60);
    private final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(44, 62, 80);

    // Warna untuk button
    private final Color BUTTON_COLOR_1 = new Color(46, 204, 113); // Hijau
    private final Color BUTTON_COLOR_2 = new Color(52, 152, 219); // Biru
    private final Color BUTTON_COLOR_3 = new Color(155, 89, 182); // Ungu
    private final Color BUTTON_COLOR_4 = new Color(230, 126, 34); // Oranye
    private final Color BUTTON_COLOR_5 = new Color(231, 76, 60); // Merah

    public AplikasiBendaGeometriGUI() {
        setTitle("Aplikasi Perhitungan Benda Geometri");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Panel utama dengan border layout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Header dengan gradient
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = PRIMARY_COLOR;
                Color color2 = new Color(52, 152, 219);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 100));

        JLabel headerLabel = new JLabel("APLIKASI PERHITUNGAN BENDA GEOMETRI");
        headerLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Home button
        JButton homeButton = new JButton("Keluar");
        homeButton.setBackground(new Color(39, 174, 96));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("Poppins", Font.BOLD, 18));
        homeButton.setFocusPainted(false);
        homeButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        homeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                homeButton.setBackground(new Color(46, 204, 113));
            }

            public void mouseExited(MouseEvent evt) {
                homeButton.setBackground(new Color(39, 174, 96));
            }
        });
        homeButton.addActionListener(e -> {
            dispose();

        });
        headerPanel.add(homeButton, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Tab panel untuk kategori geometri
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Poppins", Font.BOLD, 16));
        tabbedPane.setBackground(BACKGROUND_COLOR);
        tabbedPane.setForeground(TEXT_COLOR);

        // Panel untuk bangun datar
        JPanel bangunDatarPanel = createBangunDatarPanel();
        tabbedPane.addTab("Bangun Datar", bangunDatarPanel);

        // Panel untuk prisma
        JPanel prismaPanel = createPrismaLimasPanel(11, 18, "Prisma");
        tabbedPane.addTab("Prisma", prismaPanel);

        // Panel untuk limas
        JPanel limasPanel = createPrismaLimasPanel(18, 24, "Limas");
        tabbedPane.addTab("Limas", limasPanel);

        // Panel untuk benda khusus
        JPanel bendaKhususPanel = createBendaKhususPanel();
        tabbedPane.addTab("Benda Khusus", bendaKhususPanel);

        // Panel untuk fitur lanjutan
        JPanel fiturLanjutanPanel = createAdvancedFeaturesPanel();
        tabbedPane.addTab("Fitur Lanjutan", fiturLanjutanPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Panel hasil
        resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(CARD_COLOR);
        resultPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel resultTitle = new JLabel("HASIL PERHITUNGAN");
        resultTitle.setFont(new Font("Poppins", Font.BOLD, 18));
        resultTitle.setForeground(TEXT_COLOR);
        resultTitle.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(resultTitle, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        resultArea.setForeground(new Color(60, 60, 60));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel tombol
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(CARD_COLOR);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        hitungUlangButton = new JButton("Hitung Ulang");
        hitungUlangButton.setFont(new Font("Poppins", Font.BOLD, 16));
        hitungUlangButton.setBackground(new Color(41, 128, 185));
        hitungUlangButton.setForeground(Color.WHITE);
        hitungUlangButton.setFocusPainted(false);
        hitungUlangButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        hitungUlangButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hitungUlangButton.setEnabled(false);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Poppins", Font.BOLD, 16));
        resetButton.setBackground(new Color(231, 76, 60));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(hitungUlangButton);
        buttonPanel.add(resetButton);
        resultPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        hitungUlangButton.addActionListener(e -> hitungUlangBenda());
        resetButton.addActionListener(e -> {
            resultArea.setText("");
            hitungUlangButton.setEnabled(false);
            currentBenda = null;
            currentNamaBenda = null;
            currentLabels = null;
            currentValues = null;
        });

        mainPanel.add(resultPanel, BorderLayout.EAST);
        resultPanel.setPreferredSize(new Dimension(450, 0));

        add(mainPanel);
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            private int radius = 15; // Ukuran lengkungan sudut

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gambar background rounded
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gambar border rounded (opsional)
                g2.setColor(getBackground().darker());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2.dispose();
            }
        };

        button.setFont(new Font("Poppins", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12)); // Padding internal
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efek hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JPanel createBangunDatarPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(BACKGROUND_COLOR);

        String[] bangunDatar = {
                "Segitiga", "Persegi", "Persegi Panjang",
                "Jajaran Genjang", "Trapesium", "Belah Ketupat",
                "Layang-Layang", "Lingkaran", "Tembereng Lingkaran",
                "Juring Lingkaran"
        };

        for (int i = 0; i < 10; i++) {
            JButton button = createModernButton(bangunDatar[i], BUTTON_COLOR_3);
            button.setPreferredSize(new Dimension(280, 60));
            final int kode = i + 1;
            button.addActionListener(e -> showInputDialog(kode));

            JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonContainer.setBackground(BACKGROUND_COLOR);
            buttonContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
            buttonContainer.add(button);
            panel.add(buttonContainer);
        }

        return panel;
    }

    private JPanel createPrismaLimasPanel(int start, int end, String prefix) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(BACKGROUND_COLOR);

        String[] jenis = {
                "Segitiga", "Persegi", "Persegi Panjang",
                "Jajaran Genjang", "Trapesium", "Belah Ketupat",
                "Layang-Layang"
        };

        for (int i = 0; i < 7; i++) {
            Color btnColor = prefix.equals("Prisma") ? BUTTON_COLOR_1 : BUTTON_COLOR_2;

            JButton button = createModernButton(prefix + " " + jenis[i], btnColor);
            button.setPreferredSize(new Dimension(280, 60));
            final int kode = start + i;
            button.addActionListener(e -> showInputDialog(kode));

            JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonContainer.setBackground(BACKGROUND_COLOR);
            buttonContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
            buttonContainer.add(button);
            panel.add(buttonContainer);
        }

        return panel;
    }

    private JPanel createBendaKhususPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(BACKGROUND_COLOR);

        String[] bendaKhusus = {
                "Tabung", "Kerucut", "Kerucut Terpancung", "Bola",
                "Tembereng Bola", "Juring Bola", "Cincin Bola"
        };

        for (int i = 0; i < 7; i++) {
            JButton button = createModernButton(bendaKhusus[i], BUTTON_COLOR_4);
            button.setPreferredSize(new Dimension(280, 60));
            final int kode = 25 + i;
            button.addActionListener(e -> showInputDialog(kode));

            JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonContainer.setBackground(BACKGROUND_COLOR);
            buttonContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
            buttonContainer.add(button);
            panel.add(buttonContainer);
        }

        return panel;
    }

    private JPanel createAdvancedFeaturesPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(BACKGROUND_COLOR);

        JButton polymorphismButton = createModernButton("Polymorphism", new Color(142, 68, 173));
        polymorphismButton.setPreferredSize(new Dimension(280, 60));
        polymorphismButton.addActionListener(e -> showPolymorphismResults());
        panel.add(polymorphismButton);

        JButton threadButton = createModernButton("Thread Pool", new Color(41, 128, 185));
        threadButton.setPreferredSize(new Dimension(280, 60));
        threadButton.addActionListener(e -> showThreadPoolDialog());
        panel.add(threadButton);

        return panel;
    }

    private void showInputDialog(int kodeBenda) {
        String namaBenda = getNamaBenda(kodeBenda);
        String[] labels = getInputLabels(kodeBenda);

        JPanel inputPanel = new JPanel(new GridLayout(labels.length, 2, 10, 10));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("Poppins", Font.PLAIN, 16));
            inputPanel.add(label);

            fields[i] = new JTextField(10);
            fields[i].setFont(new Font("Poppins", Font.PLAIN, 16));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(8, 12, 8, 12)));
            inputPanel.add(fields[i]);
        }

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setBackground(BACKGROUND_COLOR);
        dialogPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Parameter " + namaBenda);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dialogPanel.add(titleLabel, BorderLayout.NORTH);

        dialogPanel.add(inputPanel, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this,
                dialogPanel,
                "Input Parameter " + namaBenda,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double[] params = new double[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = Double.parseDouble(fields[i].getText());
                }

                currentBenda = createBenda(kodeBenda, params);
                currentNamaBenda = namaBenda;
                currentLabels = labels;
                currentValues = params;

                hitungUlangButton.setEnabled(true);

                hitungBenda();

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

    private void hitungBenda() {
        if (currentBenda == null)
            return;

        StringBuilder sb = new StringBuilder();
        sb.append("Nama Benda: ").append(currentNamaBenda).append("\n\n");

        // Menampilkan parameter input
        sb.append("Parameter Input:\n");
        for (int i = 0; i < currentLabels.length; i++) {
            // Cek apakah label mengandung kata "sudut" atau "Sudut"
            if (currentLabels[i].toLowerCase().contains("sudut")) {
                sb.append("  • ").append(currentLabels[i]).append(": ").append(currentValues[i]).append("°\n");
            } else {
                sb.append("  • ").append(currentLabels[i]).append(": ").append(currentValues[i]).append(" cm\n");
            }
        }
        sb.append("\n");

        // Menampilkan hasil perhitungan berdasarkan jenis benda
        sb.append("Hasil Perhitungan:\n");

        // 1. Cek Prisma (3D)
        if (currentBenda instanceof PrismaSegitiga) {
            PrismaSegitiga prisma = (PrismaSegitiga) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaPersegi) {
            PrismaPersegi prisma = (PrismaPersegi) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaPersegiPanjang) {
            PrismaPersegiPanjang prisma = (PrismaPersegiPanjang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaJajaranGenjang) {
            PrismaJajaranGenjang prisma = (PrismaJajaranGenjang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaTrapesium) {
            PrismaTrapesium prisma = (PrismaTrapesium) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaBelahKetupat) {
            PrismaBelahKetupat prisma = (PrismaBelahKetupat) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof PrismaLayangLayang) {
            PrismaLayangLayang prisma = (PrismaLayangLayang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", prisma.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma.menghitungLuasPermukaan()))
                    .append(" cm²");
        }
        // 2. Cek Limas (3D)
        else if (currentBenda instanceof LimasSegitiga) {
            LimasSegitiga limas = (LimasSegitiga) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasPersegi) {
            LimasPersegi limas = (LimasPersegi) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasPersegiPanjang) {
            LimasPersegiPanjang limas = (LimasPersegiPanjang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasJajaranGenjang) {
            LimasJajaranGenjang limas = (LimasJajaranGenjang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasTrapesium) {
            LimasTrapesium limas = (LimasTrapesium) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasBelahKetupat) {
            LimasBelahKetupat limas = (LimasBelahKetupat) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof LimasLayangLayang) {
            LimasLayangLayang limas = (LimasLayangLayang) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", limas.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", limas.menghitungLuasPermukaan()))
                    .append(" cm²");
        }
        // 3. Cek Benda Khusus (3D)
        else if (currentBenda instanceof Tabung) {
            Tabung tabung = (Tabung) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", tabung.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", tabung.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof Kerucut) {
            Kerucut kerucut = (Kerucut) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", kerucut.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", kerucut.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof KerucutTerpancung) {
            KerucutTerpancung kerucutTerpancung = (KerucutTerpancung) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", kerucutTerpancung.menghitungVolume()))
                    .append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", kerucutTerpancung.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof Bola) {
            Bola bola = (Bola) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", bola.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", bola.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof TemberengBola) {
            TemberengBola temberengBola = (TemberengBola) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", temberengBola.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", temberengBola.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof JuringBola) {
            JuringBola juringBola = (JuringBola) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", juringBola.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", juringBola.menghitungLuasPermukaan()))
                    .append(" cm²");
        } else if (currentBenda instanceof CincinBola) {
            CincinBola cincinBola = (CincinBola) currentBenda;
            sb.append("  • Volume: ").append(String.format("%.2f", cincinBola.menghitungVolume())).append(" cm³\n");
            sb.append("  • Luas Permukaan: ").append(String.format("%.2f", cincinBola.menghitungLuasPermukaan()))
                    .append(" cm²");
        }
        // 4. Cek Bangun Datar (2D) PALING AKHIR
        else if (currentBenda instanceof Segitiga) {
            Segitiga segitiga = (Segitiga) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", segitiga.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", segitiga.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof Persegi) {
            Persegi persegi = (Persegi) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", persegi.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", persegi.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof PersegiPanjang) {
            PersegiPanjang pp = (PersegiPanjang) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", pp.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", pp.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof JajaranGenjang) {
            JajaranGenjang jg = (JajaranGenjang) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", jg.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", jg.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof Trapesium) {
            Trapesium trapesium = (Trapesium) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", trapesium.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", trapesium.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof BelahKetupat) {
            BelahKetupat bk = (BelahKetupat) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", bk.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", bk.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof LayangLayang) {
            LayangLayang ll = (LayangLayang) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", ll.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", ll.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof Lingkaran) {
            Lingkaran lingkaran = (Lingkaran) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", lingkaran.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", lingkaran.menghitungKeliling())).append(" cm");
        } else if (currentBenda instanceof TemberengLingkaran) {
            TemberengLingkaran tl = (TemberengLingkaran) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", tl.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", tl.menghitungKeliling())).append(" cm\n");
        } else if (currentBenda instanceof JuringLingkaran) {
            JuringLingkaran jl = (JuringLingkaran) currentBenda;
            sb.append("  • Luas: ").append(String.format("%.2f", jl.menghitungLuas())).append(" cm²\n");
            sb.append("  • Keliling: ").append(String.format("%.2f", jl.menghitungKeliling())).append(" cm\n");
        } else {
            sb.append("  • Tipe benda tidak dikenali");
        }
        resultArea.setText(sb.toString());
    }

    private void hitungUlangBenda() {
        if (currentBenda == null)
            return;
        // Tampilkan dialog input ulang parameter
        String[] labels = currentLabels;
        JPanel inputPanel = new JPanel(new GridLayout(labels.length, 2, 10, 10));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("Poppins", Font.PLAIN, 16));
            inputPanel.add(label);
            fields[i] = new JTextField(10);
            fields[i].setFont(new Font("Poppins", Font.PLAIN, 16));
            fields[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(8, 12, 8, 12)));
            inputPanel.add(fields[i]);
        }
        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Input Ulang Parameter " + currentNamaBenda,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double[] params = new double[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    params[i] = Double.parseDouble(fields[i].getText());
                }
                // Perhitungan overload
                StringBuilder sb = new StringBuilder();
                sb.append("Nama Benda: ").append(currentNamaBenda).append("\n\n");
                sb.append("Parameter Input (Overload):\n");
                for (int i = 0; i < labels.length; i++) {
                    if (labels[i].toLowerCase().contains("sudut")) {
                        sb.append("  • ").append(labels[i]).append(": ").append(params[i]).append("°\n");
                    } else {
                        sb.append("  • ").append(labels[i]).append(": ").append(params[i]).append(" cm\n");
                    }
                }
                sb.append("\nHasil Perhitungan (Overload):\n");
                // 2D

                // 3D Prisma
                if (currentBenda instanceof PrismaSegitiga) {
                    PrismaSegitiga prisma = (PrismaSegitiga) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1], params[4])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(String.format("%.2f",
                            prisma.menghitungLuasPermukaan(params[0], params[1], params[2], params[3], params[4])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaPersegi) {
                    PrismaPersegi prisma = (PrismaPersegi) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", prisma.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaPersegiPanjang) {
                    PrismaPersegiPanjang prisma = (PrismaPersegiPanjang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1], params[2])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    prisma.menghitungLuasPermukaan(params[0], params[1], params[2])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaJajaranGenjang) {
                    PrismaJajaranGenjang prisma = (PrismaJajaranGenjang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1], params[3])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    prisma.menghitungLuasPermukaan(params[0], params[1], params[2], params[3])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaTrapesium) {
                    PrismaTrapesium prisma = (PrismaTrapesium) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f",
                                    prisma.menghitungVolume(params[0], params[1], params[2], params[5])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(String.format("%.2f", prisma
                            .menghitungLuasPermukaan(params[0], params[1], params[2], params[3], params[4], params[5])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaBelahKetupat) {
                    PrismaBelahKetupat prisma = (PrismaBelahKetupat) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1], params[3])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    prisma.menghitungLuasPermukaan(params[0], params[1], params[2], params[3])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof PrismaLayangLayang) {
                    PrismaLayangLayang prisma = (PrismaLayangLayang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", prisma.menghitungVolume(params[0], params[1], params[4])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(String.format("%.2f",
                            prisma.menghitungLuasPermukaan(params[0], params[1], params[2], params[3], params[4])))
                            .append(" cm²\n\n");
                }
                // 3D Limas
                else if (currentBenda instanceof LimasSegitiga) {
                    LimasSegitiga limas = (LimasSegitiga) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1], params[4])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(String.format("%.2f",
                            limas.menghitungLuasPermukaan(params[0], params[1], params[2], params[3], params[4])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasPersegi) {
                    LimasPersegi limas = (LimasPersegi) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", limas.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasPersegiPanjang) {
                    LimasPersegiPanjang limas = (LimasPersegiPanjang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1], params[2])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(
                            String.format("%.2f", limas.menghitungLuasPermukaan(params[0], params[1], params[2])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasJajaranGenjang) {
                    LimasJajaranGenjang limas = (LimasJajaranGenjang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1], params[3])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    limas.menghitungLuasPermukaan(params[0], params[1], params[2], params[3])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasTrapesium) {
                    LimasTrapesium limas = (LimasTrapesium) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f",
                                    limas.menghitungVolume(params[0], params[1], params[2], params[5])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    limas.menghitungLuasPermukaan(params[0], params[1], params[2], params[5])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasBelahKetupat) {
                    LimasBelahKetupat limas = (LimasBelahKetupat) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1], params[3])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    limas.menghitungLuasPermukaan(params[0], params[1], params[2], params[3])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof LimasLayangLayang) {
                    LimasLayangLayang limas = (LimasLayangLayang) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", limas.menghitungVolume(params[0], params[1], params[4])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ").append(String.format("%.2f",
                            limas.menghitungLuasPermukaan(params[0], params[1], params[2], params[3], params[4])))
                            .append(" cm²\n\n");
                }
                // 3D Benda Khusus
                else if (currentBenda instanceof Tabung) {
                    Tabung tabung = (Tabung) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", tabung.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", tabung.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof Kerucut) {
                    Kerucut kerucut = (Kerucut) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", kerucut.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", kerucut.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof KerucutTerpancung) {
                    KerucutTerpancung kerucutTerpancung = (KerucutTerpancung) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f",
                                    kerucutTerpancung.menghitungVolume(params[0], params[1], params[2])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f",
                                    kerucutTerpancung.menghitungLuasPermukaan(params[0], params[1], params[2])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof Bola) {
                    Bola bola = (Bola) currentBenda;
                    sb.append("  • Volume: ").append(String.format("%.2f", bola.menghitungVolume(params[0])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", bola.menghitungLuasPermukaan(params[0]))).append(" cm²\n\n");
                } else if (currentBenda instanceof TemberengBola) {
                    TemberengBola temberengBola = (TemberengBola) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", temberengBola.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", temberengBola.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof JuringBola) {
                    JuringBola juringBola = (JuringBola) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", juringBola.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", juringBola.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                } else if (currentBenda instanceof CincinBola) {
                    CincinBola cincinBola = (CincinBola) currentBenda;
                    sb.append("  • Volume: ")
                            .append(String.format("%.2f", cincinBola.menghitungVolume(params[0], params[1])))
                            .append(" cm³\n");
                    sb.append("  • Luas Permukaan: ")
                            .append(String.format("%.2f", cincinBola.menghitungLuasPermukaan(params[0], params[1])))
                            .append(" cm²\n\n");
                }
                // benda 2D akhir
                else if (currentBenda instanceof Persegi) {
                    Persegi persegi = (Persegi) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", persegi.menghitungLuas(params[0])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ").append(String.format("%.2f", persegi.menghitungKeliling(params[0])))
                            .append(" cm\n");
                } else if (currentBenda instanceof PersegiPanjang) {
                    PersegiPanjang pp = (PersegiPanjang) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", pp.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", pp.menghitungKeliling(params[0], params[1]))).append(" cm\n");
                } else if (currentBenda instanceof Segitiga) {
                    Segitiga segitiga = (Segitiga) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", segitiga.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", segitiga.menghitungKeliling(params[0], params[2], params[3])))
                            .append(" cm\n");
                } else if (currentBenda instanceof JajaranGenjang) {
                    JajaranGenjang jg = (JajaranGenjang) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", jg.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", jg.menghitungKeliling(params[0], params[2]))).append(" cm\n");
                } else if (currentBenda instanceof Trapesium) {
                    Trapesium trapesium = (Trapesium) currentBenda;
                    sb.append("  • Luas: ")
                            .append(String.format("%.2f", trapesium.menghitungLuas(params[0], params[1], params[2])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f",
                                    trapesium.menghitungKeliling(params[0], params[1], params[3], params[4])))
                            .append(" cm\n");
                } else if (currentBenda instanceof BelahKetupat) {
                    BelahKetupat bk = (BelahKetupat) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", bk.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ").append(String.format("%.2f", bk.menghitungKeliling(params[2])))
                            .append(" cm\n");
                } else if (currentBenda instanceof LayangLayang) {
                    LayangLayang ll = (LayangLayang) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", ll.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", ll.menghitungKeliling(params[2], params[3]))).append(" cm\n");
                } else if (currentBenda instanceof TemberengLingkaran) {
                    TemberengLingkaran tl = (TemberengLingkaran) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", tl.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", tl.menghitungKeliling(params[0], params[1]))).append(" cm\n");
                } else if (currentBenda instanceof JuringLingkaran) {
                    JuringLingkaran jl = (JuringLingkaran) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", jl.menghitungLuas(params[0], params[1])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ")
                            .append(String.format("%.2f", jl.menghitungKeliling(params[0], params[1]))).append(" cm\n");
                } else if (currentBenda instanceof Lingkaran) {
                    Lingkaran lingkaran = (Lingkaran) currentBenda;
                    sb.append("  • Luas: ").append(String.format("%.2f", lingkaran.menghitungLuas(params[0])))
                            .append(" cm²\n");
                    sb.append("  • Keliling: ").append(String.format("%.2f", lingkaran.menghitungKeliling(params[0])))
                            .append(" cm\n");
                } else {
                    sb.append("  • Tipe benda tidak dikenali");
                }
                resultArea.setText(sb.toString());
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

    private int getKodeBenda(String namaBenda) {
        String[] bangunDatar = {
                "Segitiga", "Persegi", "Persegi Panjang",
                "Jajaran Genjang", "Trapesium", "Belah Ketupat",
                "Layang-Layang", "Lingkaran", "Tembereng Lingkaran",
                "Juring Lingkaran"
        };

        for (int i = 0; i < bangunDatar.length; i++) {
            if (namaBenda.equals(bangunDatar[i])) {
                return i + 1;
            }
        }

        String[] prisma = {
                "Prisma Segitiga", "Prisma Persegi", "Prisma Persegi Panjang",
                "Prisma Jajaran Genjang", "Prisma Trapesium", "Prisma Belah Ketupat",
                "Prisma Layang-Layang"
        };

        for (int i = 0; i < prisma.length; i++) {
            if (namaBenda.equals(prisma[i])) {
                return 11 + i;
            }
        }

        String[] limas = {
                "Limas Segitiga", "Limas Persegi", "Limas Persegi Panjang",
                "Limas Jajaran Genjang", "Limas Trapesium", "Limas Belah Ketupat",
                "Limas Layang-Layang"
        };

        for (int i = 0; i < limas.length; i++) {
            if (namaBenda.equals(limas[i])) {
                return 18 + i;
            }
        }

        String[] bendaKhusus = {
                "Tabung", "Kerucut", "Kerucut Terpancung", "Bola",
                "Tembereng Bola", "Juring Bola", "Cincin Bola"
        };

        for (int i = 0; i < bendaKhusus.length; i++) {
            if (namaBenda.equals(bendaKhusus[i])) {
                return 25 + i;
            }
        }

        return 1; // Default ke Segitiga jika tidak ditemukan
    }

    private String getNamaBenda(int kode) {
        String[] namaBenda = {
                "Segitiga", "Persegi", "Persegi Panjang", "Jajaran Genjang",
                "Trapesium", "Belah Ketupat", "Layang-Layang", "Lingkaran",
                "Tembereng Lingkaran", "Juring Lingkaran", "Prisma Segitiga",
                "Prisma Persegi", "Prisma Persegi Panjang", "Prisma Jajaran Genjang",
                "Prisma Trapesium", "Prisma Belah Ketupat", "Prisma Layang-Layang",
                "Limas Segitiga", "Limas Persegi", "Limas Persegi Panjang",
                "Limas Jajaran Genjang", "Limas Trapesium", "Limas Belah Ketupat",
                "Limas Layang-Layang", "Tabung", "Kerucut", "Kerucut Terpancung",
                "Bola", "Tembereng Bola", "Juring Bola", "Cincin Bola"
        };

        return (kode >= 1 && kode <= namaBenda.length) ? namaBenda[kode - 1] : "Unknown";
    }

    private String[] getInputLabels(int kode) {
        switch (kode) {
            case 1: // Segitiga
                return new String[] { "Alas", "Tinggi", "Sisi 1", "Sisi 2" };
            case 2: // Persegi
                return new String[] { "Sisi" };
            case 3: // Persegi Panjang
                return new String[] { "Panjang", "Lebar" };
            case 4: // Jajaran Genjang
                return new String[] { "Alas", "Tinggi", "Sisi Miring" };
            case 5: // Trapesium
                return new String[] { "Sisi Atas", "Sisi Bawah", "Tinggi", "Sisi Kiri", "Sisi Kanan" };
            case 6: // Belah Ketupat
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi" };
            case 7: // Layang-Layang
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi Atas", "Sisi Bawah" };
            case 8: // Lingkaran
                return new String[] { "Jari-jari" };
            case 9: // Tembereng Lingkaran
                return new String[] { "Jari-jari", "Sudut" };
            case 10: // Juring Lingkaran
                return new String[] { "Jari-jari", "Sudut" };
            case 11: // Prisma Segitiga
                return new String[] { "Alas", "Tinggi", "Sisi 1", "Sisi 2", "Tinggi Prisma" };
            case 12: // Prisma Persegi
                return new String[] { "Sisi", "Tinggi Prisma" };
            case 13: // Prisma Persegi Panjang
                return new String[] { "Panjang", "Lebar", "Tinggi Prisma" };
            case 14: // Prisma Jajaran Genjang
                return new String[] { "Alas", "Tinggi", "Sisi Miring", "Tinggi Prisma" };
            case 15: // Prisma Trapesium
                return new String[] { "Sisi Atas", "Sisi Bawah", "Tinggi", "Sisi Kiri", "Sisi Kanan", "Tinggi Prisma" };
            case 16: // Prisma Belah Ketupat
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Prisma" };
            case 17: // Prisma Layang-Layang
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi Atas", "Sisi Bawah", "Tinggi Prisma" };
            case 18: // Limas Segitiga
                return new String[] { "Alas", "Tinggi", "Sisi 1", "Sisi 2", "Tinggi Limas" };
            case 19: // Limas Persegi
                return new String[] { "Sisi", "Tinggi Limas" };
            case 20: // Limas Persegi Panjang
                return new String[] { "Panjang", "Lebar", "Tinggi Limas" };
            case 21: // Limas Jajaran Genjang
                return new String[] { "Alas", "Tinggi", "Sisi Miring", "Tinggi Limas" };
            case 22: // Limas Trapesium
                return new String[] { "Sisi Atas", "Sisi Bawah", "Tinggi", "Sisi Kiri", "Sisi Kanan", "Tinggi Limas" };
            case 23: // Limas Belah Ketupat
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi", "Tinggi Limas" };
            case 24: // Limas Layang-Layang
                return new String[] { "Diagonal 1", "Diagonal 2", "Sisi Atas", "Sisi Bawah", "Tinggi Limas" };
            case 25: // Tabung
                return new String[] { "Jari-jari", "Tinggi" };
            case 26: // Kerucut
                return new String[] { "Jari-jari", "Tinggi" };
            case 27: // Kerucut Terpancung
                return new String[] { "Jari-jari Atas", "Jari-jari Bawah", "Tinggi" };
            case 28: // Bola
                return new String[] { "Jari-jari" };
            case 29: // Tembereng Bola
                return new String[] { "Jari-jari", "Tinggi" };
            case 30: // Juring Bola
                return new String[] { "Jari-jari", "Sudut" };
            case 31: // Cincin Bola
                return new String[] { "Jari-jari", "Tinggi" };
            default:
                return new String[] { "Parameter" };
        }
    }

    private BendaGeometri createBenda(int kode, double[] params) {
        switch (kode) {
            case 1: // Segitiga
                return new Segitiga(params[0], params[1], params[2], params[3]);
            case 2: // Persegi
                return new Persegi(params[0]);
            case 3: // Persegi Panjang
                return new PersegiPanjang(params[0], params[1]);
            case 4: // Jajaran Genjang
                return new JajaranGenjang(params[0], params[1], params[2]);
            case 5: // Trapesium
                return new Trapesium(params[0], params[1], params[2], params[3], params[4]);
            case 6: // Belah Ketupat
                return new BelahKetupat(params[0], params[1], params[2]);
            case 7: // Layang-Layang
                return new LayangLayang(params[0], params[1], params[2], params[3]);
            case 8: // Lingkaran
                return new Lingkaran(params[0]);
            case 9: // Tembereng Lingkaran
                return new TemberengLingkaran(params[0], params[1]);
            case 10: // Juring Lingkaran
                return new JuringLingkaran(params[0], params[1]);
            case 11: // Prisma Segitiga
                return new PrismaSegitiga(params[0], params[1], params[2], params[3], params[4]);
            case 12: // Prisma Persegi
                return new PrismaPersegi(params[0], params[1]);
            case 13: // Prisma Persegi Panjang
                return new PrismaPersegiPanjang(params[0], params[1], params[2]);
            case 14: // Prisma Jajaran Genjang
                return new PrismaJajaranGenjang(params[0], params[1], params[2], params[3]);
            case 15: // Prisma Trapesium
                return new PrismaTrapesium(params[0], params[1], params[2], params[3], params[4], params[5]);
            case 16: // Prisma Belah Ketupat
                return new PrismaBelahKetupat(params[0], params[1], params[2], params[3]);
            case 17: // Prisma Layang-Layang
                return new PrismaLayangLayang(params[0], params[1], params[2], params[3], params[4]);
            case 18: // Limas Segitiga
                return new LimasSegitiga(params[0], params[1], params[2], params[3], params[4]);
            case 19: // Limas Persegi
                return new LimasPersegi(params[0], params[1]);
            case 20: // Limas Persegi Panjang
                return new LimasPersegiPanjang(params[0], params[1], params[2]);
            case 21: // Limas Jajaran Genjang
                return new LimasJajaranGenjang(params[0], params[1], params[2], params[3]);
            case 22: // Limas Trapesium
                return new LimasTrapesium(params[0], params[1], params[2], params[3], params[4], params[5]);
            case 23: // Limas Belah Ketupat
                return new LimasBelahKetupat(params[0], params[1], params[2], params[3]);
            case 24: // Limas Layang-Layang
                return new LimasLayangLayang(params[0], params[1], params[2], params[3], params[4]);
            case 25: // Tabung
                return new Tabung(params[0], params[1]);
            case 26: // Kerucut
                return new Kerucut(params[0], params[1]);
            case 27: // Kerucut Terpancung
                return new KerucutTerpancung(params[0], params[1], params[2]);
            case 28: // Bola
                return new Bola(params[0]);
            case 29: // Tembereng Bola
                return new TemberengBola(params[0], params[1]);
            case 30: // Juring Bola
                return new JuringBola(params[0], params[1]);
            case 31: // Cincin Bola
                return new CincinBola(params[0], params[1]);
            default:
                throw new IllegalArgumentException("Kode benda tidak valid");
        }
    }

    private void showPolymorphismResults() {
        Benda2D[] benda2Ds = {
                new Persegi(5),
                new PersegiPanjang(5, 10),
                new Segitiga(8, 10, 8, 8),
                new Lingkaran(5),
                new JuringLingkaran(7, 60),
                new TemberengLingkaran(14, 60),
                new Trapesium(5, 10, 8, 12, 12),
                new BelahKetupat(8, 6, 10),
                new LayangLayang(5, 8, 10, 12),
                new JajaranGenjang(7, 5, 8)
        };

        StringBuilder sb = new StringBuilder();
        sb.append("HASIL POLYMORPHISM:\n\n");

        sb.append("BENDA 2D:\n");
        for (Benda2D b : benda2Ds) {
            sb.append("• ").append(b.getNamaBenda())
                    .append(":\n")
                    .append("  - Luas: ").append(String.format("%.2f", b.menghitungLuas())).append(" cm²\n")
                    .append("  - Keliling: ").append(String.format("%.2f", b.menghitungKeliling())).append(" cm\n\n");
        }

        sb.append("\nBenda 3D:\n");
        Bola b1 = new CincinBola(7, 12);
        sb.append("• ").append(b1.getNamaBenda()).append(":\n").append("  - Volume: ")
                .append(String.format("%.2f", b1.menghitungVolume())).append(" cm³\n")
                .append("  - Luas Permukaan: ").append(String.format("%.2f", b1.menghitungLuasPermukaan()))
                .append(" cm²\n\n");
        Bola b2 = new JuringBola(7, 60);
        sb.append("• ").append(b2.getNamaBenda()).append(":\n").append("  - Volume: ")
                .append(String.format("%.2f", b2.menghitungVolume())).append(" cm³\n")
                .append("  - Luas Permukaan: ").append(String.format("%.2f", b2.menghitungLuasPermukaan()))
                .append(" cm²\n\n");
        Bola b3 = new TemberengBola(14, 10);
        sb.append("• ").append(b3.getNamaBenda()).append(":\n").append("  - Volume: ")
                .append(String.format("%.2f", b3.menghitungVolume())).append(" cm³\n")
                .append("  - Luas Permukaan: ").append(String.format("%.2f", b3.menghitungLuasPermukaan()))
                .append(" cm²\n\n");
        Kerucut k1 = new KerucutTerpancung(7, 12, 5);
        sb.append("• ").append(k1.getNamaBenda()).append(":\n").append("  - Volume: ")
                .append(String.format("%.2f", k1.menghitungVolume())).append(" cm³\n")
                .append("  - Luas Permukaan: ").append(String.format("%.2f", k1.menghitungLuasPermukaan()))
                .append(" cm²\n\n");

        resultArea.setText(sb.toString());
        cardLayout.show(cardPanel, "result");
    }

    private void showThreadPoolDialog() {
        JDialog dialog = new JDialog(this, "Thread Pool", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(900, 700);
        dialog.setLocationRelativeTo(this);

        // Create components
        String[] shapeNames = {
                "Persegi", "PersegiPanjang", "Segitiga", "Lingkaran", "BelahKetupat",
                "JajaranGenjang", "LayangLayang", "Trapesium", "TemberengLingkaran", 
                "JuringLingkaran", "Bola", "Kerucut", "KerucutTerpancung", "Tabung", 
                "TemberengBola", "JuringBola", "CincinBola", "PrismaSegitiga", 
                "PrismaPersegi", "PrismaPersegiPanjang", "PrismaJajaranGenjang", 
                "PrismaTrapesium", "PrismaBelahKetupat", "PrismaLayangLayang", 
                "LimasSegitiga", "LimasPersegi", "LimasPersegiPanjang", 
                "LimasJajaranGenjang", "LimasTrapesium", "LimasBelahKetupat", 
                "LimasLayangLayang"
        };

        JList<String> shapeList = new JList<>(shapeNames);
        shapeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        shapeList.setFont(new Font("Poppins", Font.PLAIN, 14));

        JTextArea outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Poppins", Font.PLAIN, 12));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JSpinner valueSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.1, 100.0, 0.1));
        valueSpinner.setFont(new Font("Poppins", Font.PLAIN, 14));

        JButton runButton = createModernButton("Run Selected Shapes", BUTTON_COLOR_2);
        runButton.setPreferredSize(new Dimension(200, 40));

        JButton stopButton = createModernButton("Stop All Threads", BUTTON_COLOR_5);
        stopButton.setPreferredSize(new Dimension(200, 40));
        stopButton.setEnabled(false);

        // Progress tracking
        AtomicBoolean isRunning = new AtomicBoolean(false);
        List<Thread> activeThreads = new ArrayList<>();
        AtomicInteger completedThreads = new AtomicInteger(0);
        AtomicInteger totalThreads = new AtomicInteger(0);

        // Layout
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setBackground(BACKGROUND_COLOR);
        controlPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(BACKGROUND_COLOR);

        JLabel valueLabel = new JLabel("Base Value:");
        valueLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        inputPanel.add(valueLabel);
        inputPanel.add(valueSpinner);
        inputPanel.add(runButton);
        inputPanel.add(stopButton);

        controlPanel.add(new JScrollPane(shapeList), BorderLayout.CENTER);
        controlPanel.add(inputPanel, BorderLayout.SOUTH);

        dialog.add(controlPanel, BorderLayout.WEST);
        dialog.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Event handling
        runButton.addActionListener(e -> {
            List<String> selected = shapeList.getSelectedValuesList();
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "Silakan pilih minimal satu bentuk geometri!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double value = (Double) valueSpinner.getValue();
            outputArea.setText(""); // Clear previous output
            
            // Reset progress tracking
            isRunning.set(true);
            completedThreads.set(0);
            totalThreads.set(selected.size());
            activeThreads.clear();
            
            // Update UI
            runButton.setEnabled(false);
            stopButton.setEnabled(true);
            shapeList.setEnabled(false);
            
            outputArea.append("=== EKSEKUSI THREAD POOL DIMULAI ===\n");
            outputArea.append("Bentuk yang dipilih: " + selected.size() + "\n");
            outputArea.append("Nilai dasar: " + value + "\n");
            outputArea.append("Memulai thread...\n\n");

            // Create threads for each selected shape
            List<Thread> threads = new ArrayList<>();
            List<BendaGeometri> shapes = new ArrayList<>();

            for (String shapeName : selected) {
                try {
                    BendaGeometri shape = createShape(shapeName, value);
                    if (shape != null && shape instanceof Runnable) {
                        shapes.add(shape);
                        
                        // Create custom thread with progress tracking
                        Thread thread = new Thread(() -> {
                            String threadName = Thread.currentThread().getName();
                            try {
                                // Update status to show thread is working
                                SwingUtilities.invokeLater(() -> {
                                    outputArea.append("🔄 [" + threadName + "] Memulai perhitungan...\n");
                                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                });
                                
                                // Run the actual calculation
                                ((Runnable) shape).run();
                                
                                // Check if thread was interrupted after completion
                                if (Thread.currentThread().isInterrupted()) {
                                    SwingUtilities.invokeLater(() -> {
                                        outputArea.append("⚠️ [" + threadName + "] Thread diinterupsi setelah selesai\n");
                                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                    });
                                } else {
                                    // Update status to show completion
                                    SwingUtilities.invokeLater(() -> {
                                        outputArea.append("✅ [" + threadName + "] Perhitungan selesai dengan sukses\n");
                                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                    });
                                }
                                
                            
                            } catch (Exception ex) {
                                SwingUtilities.invokeLater(() -> {
                                    outputArea.append("❌ [" + threadName + "] KESALAHAN: " + ex.getMessage() + "\n");
                                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                });
                            } finally {
                                // Remove from active threads
                                activeThreads.remove(Thread.currentThread());
                                
                                // Update progress
                                int completed = completedThreads.incrementAndGet();
                                SwingUtilities.invokeLater(() -> {
                                    outputArea.append("📊 Kemajuan: " + completed + "/" + totalThreads.get() + " thread selesai\n");
                                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                });
                            }
                        });
                        
                        thread.setName("Thread-" + shapeName);
                        threads.add(thread);
                        activeThreads.add(thread);
                        
                        // Start the thread
                        thread.start();
                        
                        // Small delay between thread starts for better visibility
                        
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            outputArea.append("❌ Kesalahan: " + shapeName + " bukan objek Runnable\n");
                            outputArea.setCaretPosition(outputArea.getDocument().getLength());
                        });
                    }
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        outputArea.append("❌ Kesalahan membuat " + shapeName + ": " + ex.getMessage() + "\n");
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    });
                }
            }

            // Wait for all threads to complete and collect results
            new Thread(() -> {
                try {
                    outputArea.append("\n=== MENUNGGU SEMUA THREAD SELESAI ===\n");
                    
                    // Add a safety timeout - if threads take too long, force UI reset
                    long startTime = System.currentTimeMillis();
                    long timeout = 60000; // 60 seconds timeout
                    
                    for (int i = 0; i < threads.size(); i++) {
                        Thread thread = threads.get(i);
                        BendaGeometri shape = shapes.get(i);
                        
                        try {
                            // Wait for thread to complete with timeout
                            thread.join(30000); // 30 second timeout per thread
                            
                            if (thread.isAlive()) {
                                SwingUtilities.invokeLater(() -> {
                                    outputArea.append("⚠️ [" + thread.getName() + "] TIMEOUT: Thread memakan waktu terlalu lama untuk selesai\n");
                                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                                });
                            }
                            
                        } catch (InterruptedException ex) {
                            SwingUtilities.invokeLater(() -> {
                                outputArea.append("❌ [" + thread.getName() + "] JOIN DIINTERUPSI: " + ex.getMessage() + "\n");
                                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                            });
                            Thread.currentThread().interrupt();
                            break;
                        }
                        
                        // Check if we've exceeded the overall timeout
                        if (System.currentTimeMillis() - startTime > timeout) {
                            SwingUtilities.invokeLater(() -> {
                                outputArea.append("⚠️ TIMEOUT GLOBAL: Memaksa reset UI karena thread terlalu lama\n");
                                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                            });
                            break;
                        }
                    }
                    
                    // Collect and display results
                    SwingUtilities.invokeLater(() -> {
                        outputArea.append("\n=== MENGUMPULKAN HASIL ===\n");
                    });
                    
                    for (int i = 0; i < shapes.size(); i++) {
                        BendaGeometri shape = shapes.get(i);
                        String shapeName = threads.get(i).getName().replace("Thread-", "");
                        
                        try {
                            // Get results based on shape type
                            String result = "";
                            if (shape instanceof Benda2D) {
                                Benda2D benda2D = (Benda2D) shape;
                                result = String.format("📐 %s:\n   Luas = %.2f cm²\n   Keliling = %.2f cm\n\n",
                                        shapeName,
                                        benda2D.menghitungLuas(),
                                        benda2D.menghitungKeliling());
                            } else if (shape instanceof PrismaSegitiga || shape instanceof PrismaPersegi ||
                                    shape instanceof PrismaPersegiPanjang || shape instanceof PrismaJajaranGenjang ||
                                    shape instanceof PrismaTrapesium || shape instanceof PrismaBelahKetupat ||
                                    shape instanceof PrismaLayangLayang || shape instanceof LimasSegitiga ||
                                    shape instanceof LimasPersegi || shape instanceof LimasPersegiPanjang ||
                                    shape instanceof LimasJajaranGenjang || shape instanceof LimasTrapesium ||
                                    shape instanceof LimasBelahKetupat || shape instanceof LimasLayangLayang ||
                                    shape instanceof Bola || shape instanceof Kerucut ||
                                    shape instanceof KerucutTerpancung || shape instanceof Tabung ||
                                    shape instanceof TemberengBola || shape instanceof JuringBola ||
                                    shape instanceof CincinBola) {
                                
                                double volume = 0;
                                double luasPermukaan = 0;
                                String namaBenda = "";

                                if (shape instanceof PrismaSegitiga) {
                                    PrismaSegitiga prisma = (PrismaSegitiga) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Segitiga";
                                } else if (shape instanceof PrismaPersegi) {
                                    PrismaPersegi prisma = (PrismaPersegi) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Persegi";
                                } else if (shape instanceof PrismaPersegiPanjang) {
                                    PrismaPersegiPanjang prisma = (PrismaPersegiPanjang) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Persegi Panjang";
                                } else if (shape instanceof PrismaJajaranGenjang) {
                                    PrismaJajaranGenjang prisma = (PrismaJajaranGenjang) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Jajaran Genjang";
                                } else if (shape instanceof PrismaTrapesium) {
                                    PrismaTrapesium prisma = (PrismaTrapesium) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Trapesium";
                                } else if (shape instanceof PrismaBelahKetupat) {
                                    PrismaBelahKetupat prisma = (PrismaBelahKetupat) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Belah Ketupat";
                                } else if (shape instanceof PrismaLayangLayang) {
                                    PrismaLayangLayang prisma = (PrismaLayangLayang) shape;
                                    volume = prisma.menghitungVolume();
                                    luasPermukaan = prisma.menghitungLuasPermukaan();
                                    namaBenda = "Prisma Layang-Layang";
                                } else if (shape instanceof LimasSegitiga) {
                                    LimasSegitiga limas = (LimasSegitiga) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Segitiga";
                                } else if (shape instanceof LimasPersegi) {
                                    LimasPersegi limas = (LimasPersegi) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Persegi";
                                } else if (shape instanceof LimasPersegiPanjang) {
                                    LimasPersegiPanjang limas = (LimasPersegiPanjang) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Persegi Panjang";
                                } else if (shape instanceof LimasJajaranGenjang) {
                                    LimasJajaranGenjang limas = (LimasJajaranGenjang) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Jajaran Genjang";
                                } else if (shape instanceof LimasTrapesium) {
                                    LimasTrapesium limas = (LimasTrapesium) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Trapesium";
                                } else if (shape instanceof LimasBelahKetupat) {
                                    LimasBelahKetupat limas = (LimasBelahKetupat) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Belah Ketupat";
                                } else if (shape instanceof LimasLayangLayang) {
                                    LimasLayangLayang limas = (LimasLayangLayang) shape;
                                    volume = limas.menghitungVolume();
                                    luasPermukaan = limas.menghitungLuasPermukaan();
                                    namaBenda = "Limas Layang-Layang";
                                } else if (shape instanceof Bola) {
                                    Bola bola = (Bola) shape;
                                    volume = bola.menghitungVolume();
                                    luasPermukaan = bola.menghitungLuasPermukaan();
                                    namaBenda = "Bola";
                                } else if (shape instanceof Kerucut) {
                                    Kerucut kerucut = (Kerucut) shape;
                                    volume = kerucut.menghitungVolume();
                                    luasPermukaan = kerucut.menghitungLuasPermukaan();
                                    namaBenda = "Kerucut";
                                } else if (shape instanceof KerucutTerpancung) {
                                    KerucutTerpancung kerucut = (KerucutTerpancung) shape;
                                    volume = kerucut.menghitungVolume();
                                    luasPermukaan = kerucut.menghitungLuasPermukaan();
                                    namaBenda = "Kerucut Terpancung";
                                } else if (shape instanceof Tabung) {
                                    Tabung tabung = (Tabung) shape;
                                    volume = tabung.menghitungVolume();
                                    luasPermukaan = tabung.menghitungLuasPermukaan();
                                    namaBenda = "Tabung";
                                } else if (shape instanceof TemberengBola) {
                                    TemberengBola tembereng = (TemberengBola) shape;
                                    volume = tembereng.menghitungVolume();
                                    luasPermukaan = tembereng.menghitungLuasPermukaan();
                                    namaBenda = "Tembereng Bola";
                                } else if (shape instanceof JuringBola) {
                                    JuringBola juring = (JuringBola) shape;
                                    volume = juring.menghitungVolume();
                                    luasPermukaan = juring.menghitungLuasPermukaan();
                                    namaBenda = "Juring Bola";
                                } else if (shape instanceof CincinBola) {
                                    CincinBola cincin = (CincinBola) shape;
                                    volume = cincin.menghitungVolume();
                                    luasPermukaan = cincin.menghitungLuasPermukaan();
                                    namaBenda = "Cincin Bola";
                                }

                                result = String.format("📦 %s:\n   Volume = %.2f cm³\n   Luas Permukaan = %.2f cm²\n\n",
                                        namaBenda, volume, luasPermukaan);
                            }

                            final String finalResult = result;
                            SwingUtilities.invokeLater(() -> {
                                outputArea.append(finalResult);
                                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                            });
                            
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(() -> {
                                outputArea.append("❌ Kesalahan memproses hasil untuk " + shapeName + ": " + ex.getMessage() + "\n");
                                outputArea.setCaretPosition(outputArea.getDocument().getLength());
                            });
                        }
                    }
                    
                    SwingUtilities.invokeLater(() -> {
                        outputArea.append("=== EKSEKUSI THREAD POOL SELESAI ===\n");
                        outputArea.append("Total thread yang diproses: " + totalThreads.get() + "\n");
                        outputArea.append("Berhasil diselesaikan: " + completedThreads.get() + "\n");
                        
                        // Reset UI immediately
                        runButton.setEnabled(true);
                        stopButton.setEnabled(false);
                        shapeList.setEnabled(true);
                        isRunning.set(false);
                        
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    });
                    
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        outputArea.append("❌ KESALAHAN KRITIS: " + ex.getMessage() + "\n");
                        outputArea.append("=== EKSEKUSI THREAD POOL GAGAL ===\n");
                        
                        // Reset UI immediately
                        runButton.setEnabled(true);
                        stopButton.setEnabled(false);
                        shapeList.setEnabled(true);
                        isRunning.set(false);
                        
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    });
                } finally {
                    // Final safety check - ensure UI is always reset
                    SwingUtilities.invokeLater(() -> {
                        if (!runButton.isEnabled()) {
                            runButton.setEnabled(true);
                            stopButton.setEnabled(false);
                            shapeList.setEnabled(true);
                            isRunning.set(false);
                            outputArea.append("🔧 UI telah direset secara otomatis\n");
                        }
                    });
                }
            }).start();
        });

        // Stop button functionality
        stopButton.addActionListener(e -> {
            if (isRunning.get()) {
                outputArea.append("\n=== MENGHEMTI SEMUA THREAD ===\n");
                
                AtomicInteger interruptedCount = new AtomicInteger(0);
                // Interrupt all active threads
                for (Thread thread : new ArrayList<>(activeThreads)) {
                    if (thread.isAlive()) {
                        thread.interrupt();
                        interruptedCount.incrementAndGet();
                        outputArea.append("🛑 Menginterupsi " + thread.getName() + "\n");
                    }
                }
                
                // Reset UI immediately
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                shapeList.setEnabled(true);
                isRunning.set(false);
                
                // Wait a bit for threads to respond to interruption
                new Thread(() -> {
                    try {
                        Thread.sleep(500); // Wait 0.5 second for threads to respond
                        
                        // Check which threads are still alive
                        AtomicInteger stillAlive = new AtomicInteger(0);
                        for (Thread thread : new ArrayList<>(activeThreads)) {
                            if (thread.isAlive()) {
                                stillAlive.incrementAndGet();
                            }
                        }
                        
                        SwingUtilities.invokeLater(() -> {
                            if (stillAlive.get() > 0) {
                                outputArea.append("⚠️ " + stillAlive.get() + " thread masih berjalan (mungkin membutuhkan waktu untuk berhenti)\n");
                            }
                            outputArea.append("=== EKSEKUSI THREAD POOL DIHENTIKAN ===\n");
                            outputArea.append("Menginterupsi " + interruptedCount.get() + " thread\n");
                            outputArea.setCaretPosition(outputArea.getDocument().getLength());
                        });
                        
                    } catch (Exception ex) {
                        // Ignore
                    }
                }).start();
            }
        });

        dialog.setVisible(true);
    }

    private BendaGeometri createShape(String shapeName, double value) {
        switch (shapeName) {
            // 2D Shapes
            case "Persegi":
                return new Persegi(value);
            case "PersegiPanjang":
                return new PersegiPanjang(value, value);
            case "Segitiga":
                return new Segitiga(value, value, value, value);
            case "Lingkaran":
                return new Lingkaran(value);
            case "BelahKetupat":
                return new BelahKetupat(value, value, value);
            case "JajaranGenjang":
                return new JajaranGenjang(value, value, value);
            case "LayangLayang":
                return new LayangLayang(value, value, value, value);
            case "Trapesium":
                return new Trapesium(value, value, value, value, value);
            case "TemberengLingkaran":
                return new TemberengLingkaran(value, 60); // value as radius, 60 as angle
            case "JuringLingkaran":
                return new JuringLingkaran(value, 60); // value as radius, 60 as angle
            
            // 3D Special Objects
            case "Bola":
                return new Bola(value);
            case "Kerucut":
                return new Kerucut(value, value);
            case "KerucutTerpancung":
                return new KerucutTerpancung(value, value * 1.5, value); // radius1, radius2, height
            case "Tabung":
                return new Tabung(value, value);
            case "TemberengBola":
                return new TemberengBola(value, value * 0.5); // radius, height
            case "JuringBola":
                return new JuringBola(value, 60); // radius, angle
            case "CincinBola":
                return new CincinBola(value, value * 0.5); // radius, height
            
            // Prisma (3D)
            case "PrismaSegitiga":
                return new PrismaSegitiga(value, value, value, value, value);
            case "PrismaPersegi":
                return new PrismaPersegi(value, value);
            case "PrismaPersegiPanjang":
                return new PrismaPersegiPanjang(value, value, value);
            case "PrismaJajaranGenjang":
                return new PrismaJajaranGenjang(value, value, value, value);
            case "PrismaTrapesium":
                return new PrismaTrapesium(value, value, value, value, value, value);
            case "PrismaBelahKetupat":
                return new PrismaBelahKetupat(value, value, value, value);
            case "PrismaLayangLayang":
                return new PrismaLayangLayang(value, value, value, value, value);
            
            // Limas (3D)
            case "LimasSegitiga":
                return new LimasSegitiga(value, value, value, value, value);
            case "LimasPersegi":
                return new LimasPersegi(value, value);
            case "LimasPersegiPanjang":
                return new LimasPersegiPanjang(value, value, value);
            case "LimasJajaranGenjang":
                return new LimasJajaranGenjang(value, value, value, value);
            case "LimasTrapesium":
                return new LimasTrapesium(value, value, value, value, value, value);
            case "LimasBelahKetupat":
                return new LimasBelahKetupat(value, value, value, value);
            case "LimasLayangLayang":
                return new LimasLayangLayang(value, value, value, value, value);
            
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        try {
            // Set modern look and y
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new AplikasiBendaGeometriGUI().setVisible(true);
        });
    }
}