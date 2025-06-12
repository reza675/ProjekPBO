package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

/**
 * Kelas untuk perhitungan dinamis
 * Mengambil input dari user dan melakukan overload pada class bentuk geometri
 */
public class DialogPerhitunganDinamis extends JFrame {
    private List<JTextField> fieldInput = new ArrayList<>();
    private JTextArea areaHasil;
    private String namaBentuk;
    private String[] labelInput;
    private double[] nilaiAwal;
    private DialogPerhitunganStatis.KalkulatorBentuk kalkulator;
    private JPanel panelInput;
    private JPanel panelHasil;

    public DialogPerhitunganDinamis(JFrame parent,
            String namaBentuk,
            String[] labelInput,
            double[] nilaiAwal,
            DialogPerhitunganStatis.KalkulatorBentuk kalkulator) {
        super("Perhitungan Dinamis - " + namaBentuk);
        this.namaBentuk = namaBentuk;
        this.labelInput = labelInput;
        this.nilaiAwal = nilaiAwal.clone();
        this.kalkulator = kalkulator;

        // Handle parent window behavior
        if (parent != null) {
            parent.setEnabled(false);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    parent.setEnabled(true);
                    parent.toFront();
                }
            });
        }

        inisialisasiFrame();
        aturTataLetak();
    }

    private void inisialisasiFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }

    private void aturTataLetak() {
        add(buatPanelHeader(), BorderLayout.NORTH);
        add(buatPanelUtama(), BorderLayout.CENTER);
        add(buatPanelTombol(), BorderLayout.SOUTH);
    }

    private JPanel buatPanelHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 167, 69));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel labelJudul = new JLabel("Perhitungan Dinamis", SwingConstants.CENTER);
        labelJudul.setFont(new Font("SansSerif", Font.BOLD, 22));
        labelJudul.setForeground(Color.WHITE);

        JLabel labelBentuk = new JLabel(namaBentuk + " - Input Nilai Kustom", SwingConstants.CENTER);
        labelBentuk.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelBentuk.setForeground(new Color(220, 255, 220));

        panel.add(labelJudul, BorderLayout.CENTER);
        panel.add(labelBentuk, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buatPanelUtama() {
        JPanel panelUtama = new JPanel(new BorderLayout(15, 15));
        panelUtama.setBackground(Color.WHITE);
        panelUtama.setBorder(new EmptyBorder(10, 10, 10, 10));

        panelInput = buatPanelInput();
        panelHasil = buatPanelHasil();

        panelUtama.add(panelInput, BorderLayout.WEST);
        panelUtama.add(panelHasil, BorderLayout.CENTER);

        return panelUtama;
    }

    private JPanel buatPanelInput() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(40, 167, 69)),
                        "Input Nilai Dinamis", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), new Color(40, 167, 69)),
                new EmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        fieldInput.clear();

        for (int i = 0; i < labelInput.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;

            JLabel label = new JLabel(labelInput[i] + ":");
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            label.setForeground(new Color(50, 50, 50));
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;

            JTextField fieldTeks = new JTextField(String.valueOf(nilaiAwal[i]));
            fieldTeks.setFont(new Font("SansSerif", Font.PLAIN, 14));
            fieldTeks.setPreferredSize(new Dimension(150, 40));
            fieldTeks.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true),
                    new EmptyBorder(8, 12, 8, 12)));
            fieldTeks.setHorizontalAlignment(SwingConstants.RIGHT);

            fieldTeks.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    hitungWaktuNyata();
                }
            });

            fieldTeks.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    fieldTeks.selectAll();
                    fieldTeks.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(255, 193, 7), 2, true),
                            new EmptyBorder(8, 12, 8, 12)));
                }

                @Override
                public void focusLost(FocusEvent e) {
                    fieldTeks.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true),
                            new EmptyBorder(8, 12, 8, 12)));
                    hitungWaktuNyata();
                }
            });

            fieldInput.add(fieldTeks);
            panel.add(fieldTeks, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0;

            String satuan;
            if ("Tembereng Lingkaran".equals(namaBentuk) || "Juring Lingkaran".equals(namaBentuk)) {
                satuan = (i == 1 ? "°" : " cm");
            } else {
                satuan = " cm";
            }

            JLabel labelSatuan = new JLabel(satuan);
            labelSatuan.setFont(new Font("SansSerif", Font.ITALIC, 12));
            labelSatuan.setForeground(new Color(100, 100, 100));
            panel.add(labelSatuan, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = labelInput.length;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 0, 0);

        panel.setPreferredSize(new Dimension(350, getHeight()));
        return panel;
    }

    private JPanel buatPanelHasil() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180)),
                        "Hasil Perhitungan Dinamis", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), new Color(70, 130, 180)),
                new EmptyBorder(15, 15, 15, 15)));

        areaHasil = new JTextArea();
        areaHasil.setEditable(false);
        areaHasil.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaHasil.setBackground(new Color(240, 248, 255));
        areaHasil.setBorder(new EmptyBorder(15, 15, 15, 15));
        areaHasil.setLineWrap(true);
        areaHasil.setWrapStyleWord(true);

        hitungWaktuNyata();

        JScrollPane scrollPane = new JScrollPane(areaHasil);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel buatPanelTombol() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                new EmptyBorder(10, 0, 10, 0)));

        JButton tombolReset = buatTombolBergaya("↺ Reset ke Nilai Awal", new Color(255, 193, 7), new Color(50, 50, 50));
        tombolReset.addActionListener(this::resetKeNilaiAwal);

        JButton tombolTutup = buatTombolBergaya("✕ Tutup", new Color(220, 53, 69), Color.WHITE);
        tombolTutup.addActionListener(e -> dispose());

        panel.add(tombolReset);
        panel.add(tombolTutup);

        return panel;
    }

    private JButton buatTombolBergaya(String teks, Color bg, Color fg) {
        JButton tombol = new JButton(teks);
        tombol.setFont(new Font("SansSerif", Font.BOLD, 13));
        tombol.setBackground(bg);
        tombol.setForeground(fg);
        tombol.setFocusPainted(false);
        tombol.setBorder(new EmptyBorder(10, 18, 10, 18));
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

    private void hitungWaktuNyata() {
        try {
            double[] nilai = ambilNilaiInput();
            DialogPerhitunganStatis.HasilPerhitungan hasil = kalkulator.hitung(nilai);
            tampilkanHasil(hasil, nilai);
        } catch (NumberFormatException ex) {
            tampilkanError("Mohon masukkan nilai numerik yang valid");
        } catch (InputMismatchException ex) {
            tampilkanError(ex.getMessage());
        } catch (Exception ex) {
            tampilkanError("Error dalam perhitungan: " + ex.getMessage());
        }
    }

    private double[] ambilNilaiInput() {
        double[] nilai = new double[fieldInput.size()];
        for (int i = 0; i < fieldInput.size(); i++) {
            String teks = fieldInput.get(i).getText().trim();
            if (teks.isEmpty()) {
                throw new NumberFormatException("Field tidak boleh kosong");
            }
            try {
                nilai[i] = Double.parseDouble(teks);
            } catch (NumberFormatException ex) {
                tampilkanError(ex.getMessage());
            }
        }
        return nilai;
    }

    private void tampilkanHasil(DialogPerhitunganStatis.HasilPerhitungan hasil, double[] nilai) {
        StringBuilder sb = new StringBuilder();
        sb.append(" HASIL PERHITUNGAN DINAMIS - ").append(namaBentuk.toUpperCase()).append("\n");
        sb.append("============================================\n\n");

        sb.append("METODE: Menggunakan method dengan parameter\n\n");

        sb.append(" NILAI INPUT DINAMIS:\n");
        String satuan;
        for (int i = 0; i < labelInput.length && i < nilai.length; i++) {
            if ("Tembereng Lingkaran".equals(namaBentuk) || "Juring Lingkaran".equals(namaBentuk)) {
                satuan = (i == 1 ? "°" : " cm");
            } else {
                satuan = " cm";
            }
            sb.append(String.format("   • %-9s : %6.2f%s\n",
                    labelInput[i], nilai[i], satuan));
        }

        sb.append("\n");
        sb.append(" HASIL PERHITUNGAN:\n");
        sb.append(String.format("   • Luas      : %6.2f cm²\n", hasil.getLuas()));
        sb.append(String.format("   • Keliling  : %6.2f cm\n", hasil.getKeliling()));

        areaHasil.setText(sb.toString());
        areaHasil.setCaretPosition(0);
    }

    private void tampilkanError(String pesan) {
        areaHasil.setText(" ERROR\n" + "======\n\n" + pesan);
        areaHasil.setCaretPosition(0);
    }

    private void resetKeNilaiAwal(ActionEvent e) {
        for (int i = 0; i < fieldInput.size(); i++) {
            fieldInput.get(i).setText(String.valueOf(nilaiAwal[i]));
        }
        hitungWaktuNyata();
    }
}