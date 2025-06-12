package View;

import BendaGeometri.*;
import Threading.ThreadExecutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolGUI extends JFrame {
    private JTextArea resultArea;
    private JButton startButton;
    private JButton backButton;
    private JProgressBar progressBar;
    private Random random;
    private ExecutorService executor;

    public ThreadPoolGUI() {
        setTitle("Thread Pool Demo - Aplikasi Benda Geometri");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Panel judul
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Thread Pool Demo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Panel konten
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Area hasil
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(700, 30));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tombol start
        startButton = new JButton("Start Thread Pool Demo");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(70, 130, 180));
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setMaximumSize(new Dimension(300, 40));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tombol kembali
        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(199, 84, 80));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menambahkan komponen ke panel konten
        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(progressBar);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(startButton);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(backButton);

        // Menambahkan panel ke frame
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Inisialisasi random
        random = new Random();

        // Menambahkan action listener
        startButton.addActionListener(e -> startThreadPoolDemo());
        backButton.addActionListener(e -> {
            if (executor != null) {
                executor.shutdown();
            }
            dispose();
            new MainMenu().setVisible(true);
        });
    }

    private void startThreadPoolDemo() {
        startButton.setEnabled(false);
        resultArea.setText("");
        progressBar.setValue(0);

        // Membuat thread pool dengan 4 thread
        executor = Executors.newFixedThreadPool(4);
        List<BendaGeometri> objects = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        // Generate 10 random objects
        for (int i = 0; i < 10; i++) {
            int choice = random.nextInt(32) + 1;
            BendaGeometri benda = generateRandomBendaGeometri(choice);
            objects.add(benda);
        }

        // Menjalankan perhitungan dalam thread pool
        for (int i = 0; i < objects.size(); i++) {
            final int index = i;
            final BendaGeometri benda = objects.get(i);
            executor.submit(() -> {
                try {
                    // Simulasi perhitungan yang memakan waktu
                    Thread.sleep(1000);

                    String hasil = String.format(
                        "Object %d: %s\n" +
                        "Thread: %s\n" +
                        "Status: Selesai\n\n",
                        index + 1,
                        benda.getNamaBenda(),
                        Thread.currentThread().getName()
                    );

                    SwingUtilities.invokeLater(() -> {
                        resultArea.append(hasil);
                        progressBar.setValue((index + 1) * 10);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Menutup thread pool setelah semua task selesai
        executor.shutdown();
        try {
            if (executor.awaitTermination(15, TimeUnit.SECONDS)) {
                SwingUtilities.invokeLater(() -> {
                    startButton.setEnabled(true);
                    resultArea.append("\nSemua perhitungan selesai!");
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BendaGeometri generateRandomBendaGeometri(int choice) {
        double dimensi = randomDimensi();
        double sudut = randomAngle();

        switch (choice) {
            case 1: return new Segitiga(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.4);
            case 2: return new Persegi(dimensi);
            case 3: return new PersegiPanjang(dimensi, dimensi * 1.5);
            case 4: return new JajaranGenjang(dimensi, dimensi * 1.2, dimensi * 1.3);
            case 5: return new Trapesium(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4);
            case 6: return new BelahKetupat(dimensi, dimensi * 1.5, dimensi * 1.2);
            case 7: return new LayangLayang(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3);
            case 8: return new Lingkaran(dimensi);
            case 9: return new TemberengLingkaran(dimensi, sudut);
            case 10: return new JuringLingkaran(dimensi, sudut);
            case 11: return new PrismaSegitiga(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4);
            case 12: return new LimasSegitiga(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4);
            case 13: return new PrismaPersegi(dimensi, dimensi * 1.5);
            case 14: return new LimasPersegi(dimensi, dimensi * 1.5);
            case 15: return new PrismaPersegiPanjang(dimensi, dimensi * 1.5, dimensi * 1.2);
            case 16: return new LimasPersegiPanjang(dimensi, dimensi * 1.5, dimensi * 1.2);
            case 17: return new PrismaJajaranGenjang(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3);
            case 18: return new LimasJajaranGenjang(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3);
            case 19: return new PrismaTrapesium(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4, dimensi * 1.5);
            case 20: return new LimasTrapesium(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4, dimensi * 1.5);
            case 21: return new PrismaBelahKetupat(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3);
            case 22: return new LimasBelahKetupat(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3);
            case 23: return new PrismaLayangLayang(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4);
            case 24: return new LimasLayangLayang(dimensi, dimensi * 1.5, dimensi * 1.2, dimensi * 1.3, dimensi * 1.4);
            case 25: return new Tabung(dimensi, dimensi * 1.5);
            case 26: return new Kerucut(dimensi, dimensi * 1.5);
            case 27: return new KerucutTerpancung(dimensi, dimensi * 1.2, dimensi * 1.5);
            case 28: return new Bola(dimensi);
            case 29: return new TemberengBola(dimensi, dimensi * 0.5);
            case 30: return new JuringBola(dimensi, sudut);
            case 31: return new CincinBola(dimensi, dimensi * 0.8);
            default: return new Persegi(dimensi);
        }
    }

    private double randomDimensi() {
        return random.nextDouble() * 10 + 1; // 1-11
    }

    private double randomAngle() {
        return random.nextDouble() * 180; // 0-180 derajat
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadPoolGUI().setVisible(true);
        });
    }
} 