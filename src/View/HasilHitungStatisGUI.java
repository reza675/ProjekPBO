package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import BendaGeometri.*;

public class HasilHitungStatisGUI extends JFrame {
    private JTextArea areaHasil;
    private String namaBentuk;
    private String[] labelInput;
    private double[] nilaiStatis;
    private Benda2D bentuk; // Objek bentuk untuk perhitungan
    private boolean lanjutKeDinamis = false;

    public HasilHitungStatisGUI() {
        super("Perhitungan Statis");
        inisialisasiFrame();
    }

    // Setter untuk inisialisasi data
    public void setBentuk(Benda2D bentuk, String namaBentuk, String[] labelInput, double[] nilaiStatis) {
        this.bentuk = bentuk;
        this.namaBentuk = namaBentuk;
        this.labelInput = labelInput;
        this.nilaiStatis = nilaiStatis;
         hitungDanTampilkan();
    }

    private void inisialisasiFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(600, 500);
        setResizable(false);

        // Panel Header
        JPanel panelHeader = buatPanelHeader();
        panelHeader.setBounds(0, 0, 600, 100);
        add(panelHeader);

        // Panel Hasil
        JPanel panelHasil = buatPanelHasil();
        panelHasil.setBounds(0, 100, 600, 300);
        add(panelHasil);

        // Panel Tombol
        JPanel panelTombol = buatPanelTombol();
        panelTombol.setBounds(0, 400, 600, 100);
        add(panelTombol);

        hitungDanTampilkan();
    }

    private JPanel buatPanelHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(70, 130, 180));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelJudul = new JLabel("Perhitungan Statis", SwingConstants.CENTER);
        labelJudul.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelJudul.setForeground(Color.WHITE);

        JLabel labelBentuk = new JLabel(namaBentuk, SwingConstants.CENTER);
        labelBentuk.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelBentuk.setForeground(new Color(220, 220, 220));

        panel.add(labelJudul, BorderLayout.CENTER);
        panel.add(labelBentuk, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buatPanelHasil() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(0, 120, 0)),
                        "Hasil Perhitungan Statis", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), new Color(0, 120, 0)),
                new EmptyBorder(15, 15, 15, 15)));

        areaHasil = new JTextArea();
        areaHasil.setEditable(false);
        areaHasil.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaHasil.setBackground(new Color(248, 255, 248));
        areaHasil.setBorder(new EmptyBorder(15, 15, 15, 15));
        areaHasil.setLineWrap(true);
        areaHasil.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(areaHasil);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel buatPanelTombol() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                new EmptyBorder(15, 20, 15, 20)));

        JLabel labelKonfirmasi = new JLabel(buatPesanKonfirmasi(), SwingConstants.CENTER);
        labelKonfirmasi.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelKonfirmasi.setForeground(new Color(80, 80, 80));
        panel.add(labelKonfirmasi, BorderLayout.CENTER);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelTombol.setBackground(Color.WHITE);

        JButton tombolYa = buatTombolBergaya("Ya, Ubah Nilai", new Color(40, 167, 69), Color.WHITE);
        tombolYa.addActionListener(this::onLanjutKeDinamis);

        JButton tombolTidak = buatTombolBergaya("Tidak", new Color(108, 117, 125), Color.WHITE);
        tombolTidak.addActionListener(e -> dispose());

        panelTombol.add(tombolYa);
        panelTombol.add(tombolTidak);

        panel.add(panelTombol, BorderLayout.SOUTH);
        return panel;
    }

    private JButton buatTombolBergaya(String teks, Color bg, Color fg) {
        JButton tombol = new JButton(teks);
        tombol.setFont(new Font("SansSerif", Font.BOLD, 13));
        tombol.setBackground(bg);
        tombol.setForeground(fg);
        tombol.setFocusPainted(false);
        tombol.setBorder(new EmptyBorder(10, 20, 10, 20));
        tombol.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tombol.addMouseListener(new MouseAdapter() {
            Color bgAsli = bg;

            @Override
            public void mouseEntered(MouseEvent e) {
                tombol.setBackground(bg.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tombol.setBackground(bgAsli);
            }
        });

        return tombol;
    }

    private void hitungDanTampilkan() {
        if (bentuk == null) {
            areaHasil.setText("Bentuk belum diinisialisasi");
            return;
        }
        try {
            double luas = bentuk.menghitungLuas();
            double keliling = bentuk.menghitungKeliling();
            tampilkanHasil(luas, keliling);
        } catch (Exception ex) {
            areaHasil.setText("Error dalam perhitungan: " + ex.getMessage());
        }
    }

    private void tampilkanHasil(double luas, double keliling) {
        StringBuilder sb = new StringBuilder();
        sb.append(" HASIL PERHITUNGAN STATIS - ").append(namaBentuk.toUpperCase()).append("\n");
        sb.append("============================================\n\n");

        sb.append("METODE: Menggunakan method tanpa parameter\n\n");

        sb.append(" NILAI INPUT STATIS:\n");
        String satuan;
        for (int i = 0; i < labelInput.length && i < nilaiStatis.length; i++) {
            if (namaBentuk.contains("Lingkaran")) {
                satuan = (i == 1 ? "°" : " cm");
            } else {
                satuan = " cm";
            }
            sb.append(String.format("   • %-9s : %6.2f%s\n",
                    labelInput[i], nilaiStatis[i], satuan));
        }

        sb.append("\n");
        sb.append(" HASIL PERHITUNGAN:\n");
        sb.append(String.format("   • Luas      : %6.2f cm²\n", luas));
        sb.append(String.format("   • Keliling  : %6.2f cm\n", keliling));

        areaHasil.setText(sb.toString());
        areaHasil.setCaretPosition(0);
    }

    private String buatPesanKonfirmasi() {
        if (labelInput == null || labelInput.length == 0) {
            return "Apakah Anda ingin mengubah nilai?";
        } else if (labelInput.length == 1) {
            return "Apakah Anda ingin mengubah " + labelInput[0].toLowerCase() +
                    " " + namaBentuk.toLowerCase() + "?";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Apakah Anda ingin mengubah ");
            for (int i = 0; i < labelInput.length; i++) {
                sb.append(labelInput[i].toLowerCase());
                if (i < labelInput.length - 2) {
                    sb.append(", ");
                } else if (i == labelInput.length - 2) {
                    sb.append(" dan ");
                }
            }
            sb.append(" ").append(namaBentuk.toLowerCase()).append("?");
            return sb.toString();
        }
    }

    private void onLanjutKeDinamis(ActionEvent e) {
        lanjutKeDinamis = true;
        dispose();
        
        // Di sini Anda bisa membuka dialog dinamis
        JOptionPane.showMessageDialog(this, "Membuka perhitungan dinamis...");
    }

    public static void main(String[] args) {
        // Contoh penggunaan untuk persegi
        SwingUtilities.invokeLater(() -> {
        Benda2D Segitiga = new Persegi(5.0);
        String[] label = {"sisi"};
        double[] nilai = {5.0};
        HasilHitungStatisGUI ui = new HasilHitungStatisGUI();
        ui.setBentuk(persegi, "Persegi", label, nilai);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        });
    }
}