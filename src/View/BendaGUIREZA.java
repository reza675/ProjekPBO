package View;

import BendaGeometri.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BendaGUIREZA extends JFrame {
    private Benda2D benda;
    private String[] params;
    private JTextField[] textFields;

    // Komponen GUI
    private JLabel namaLabel;
    private JLabel luasLabel;
    private JLabel kelilingLabel;
    private JLabel luasLabel2;
    private JPanel loopPanel;
    private JPanel frameBawah;
    private JPanel sidebar;
    private JButton hitungButton;

    public BendaGUIREZA(Benda2D benda) {
        this.benda   = benda;
        this.params  = benda.getParameterNames();
        this.textFields = new JTextField[params.length];
        
        initComponents();
        buildDynamicInputs();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Detail " + benda.getNamaBenda());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5,5));
        setPreferredSize(new Dimension(450, 300));

        // Header: nama, luas, keliling
        JPanel header = new JPanel(new GridLayout(4,1,2,2));
        namaLabel     = new JLabel();
        luasLabel     = new JLabel();
        kelilingLabel = new JLabel();
        luasLabel2    = new JLabel();

        String namaBenda    = benda.getNamaBenda();
        String joinedParams = String.join(", ", params);

        namaLabel.setText("Benda: " + namaBenda);
        luasLabel.setText("Luas: " + benda.menghitungLuas() + " cm²");
        kelilingLabel.setText("Keliling: " + benda.menghitungKeliling() + " cm");
        luasLabel2.setText("<html>Apakah Anda ingin mengubah nilai <b>"
            + joinedParams + "</b> dari <b>" + namaBenda + "</b>?</html>");

        header.add(namaLabel);
        header.add(luasLabel);
        header.add(kelilingLabel);
        header.add(luasLabel2);
        add(header, BorderLayout.NORTH);

        // Button Hitung
        hitungButton = new JButton("Hitung Ulang");
        hitungButton.addActionListener(this::onHitung);
        add(hitungButton, BorderLayout.CENTER);

        // Panel bawah container
        frameBawah = new JPanel(null);
        add(frameBawah, BorderLayout.SOUTH);

        // Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(new Color(30,75,112));
        sidebar.setBounds(0, 0, 70, 100);
        frameBawah.add(sidebar);
    }

    private void buildDynamicInputs() {
        // Panel looping input
        loopPanel = new JPanel(new GridLayout(params.length, 2, 5, 5));
        loopPanel.setBackground(Color.WHITE);
        loopPanel.setBounds(70, 0, 350, 100);

        // Buat label + textfield
        for (int i = 0; i < params.length; i++) {
            loopPanel.add(new JLabel(params[i] + ":"));
            textFields[i] = new JTextField();
            loopPanel.add(textFields[i]);
        }
        frameBawah.add(loopPanel);
    }

    private void onHitung(ActionEvent evt) {
        try {
            // Baca input dan konversi
            double[] vals = new double[params.length];
            for (int i = 0; i < params.length; i++) {
                String txt = textFields[i].getText().trim();
                if (txt.isEmpty())
                    throw new NumberFormatException(params[i] + " tidak boleh kosong");
                vals[i] = Double.parseDouble(txt);
            }

            // Inisialisasi variabel untuk hasil
            double newLuas = 0;
            double newKeliling = 0;
            
            // Normalisasi nama benda untuk perbandingan
            String nama = benda.getNamaBenda().toLowerCase().replaceAll("\\s+", "");

            // Penanganan berdasarkan tipe benda
            if (nama.equals("persegipanjang")) {
                if (benda instanceof PersegiPanjang) {
                    PersegiPanjang pp = (PersegiPanjang) benda;
                    
                    // Validasi jumlah parameter
                    if (vals.length >= 2) {
                        newLuas = pp.menghitungLuas(vals[0], vals[1]); // panjang, lebar
                        newKeliling = pp.menghitungKeliling(vals[0], vals[1]); // panjang, lebar
                    } else {
                        throw new IllegalArgumentException("Persegi panjang membutuhkan 2 parameter: panjang dan lebar");
                    }
                } else {
                    throw new IllegalStateException("Objek bukan instance PersegiPanjang");
                }
            } 
            else if (nama.equals("persegi")) {
                // Tambahkan penanganan untuk persegi jika ada
                if (vals.length >= 1) {
                    // Asumsi ada method untuk persegi
                    // Sesuaikan dengan class Persegi yang ada
                    newLuas = benda.menghitungLuas();
                    newKeliling = benda.menghitungKeliling();
                } else {
                    throw new IllegalArgumentException("Persegi membutuhkan 1 parameter: sisi");
                }
            }
            else {
                // Untuk bentuk geometri lain
                newLuas = benda.menghitungLuas();
                newKeliling = benda.menghitungKeliling();
            }

            // Tampilkan hasil
            JOptionPane.showMessageDialog(
                this,
                String.format("Luas %s: %.2f cm²\nKeliling %s: %.2f cm",
                    benda.getNamaBenda(), newLuas,
                    benda.getNamaBenda(), newKeliling),
                "Hasil Perhitungan",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Update label dengan nilai baru
            luasLabel.setText("Luas: " + String.format("%.2f", newLuas) + " cm²");
            kelilingLabel.setText("Keliling: " + String.format("%.2f", newKeliling) + " cm");
            
            // Tutup window setelah berhasil menghitung
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Input tidak valid: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Parameter tidak valid: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Terjadi error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Contoh main untuk testing
    public static void main(String[] args) {
        try {
            // Ganti dengan objek Benda2D yang diinginkan
            Benda2D contoh = new PersegiPanjang(5, 10);
            SwingUtilities.invokeLater(() -> {
                new BendaGUIREZA(contoh).setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("Error creating test object: " + e.getMessage());
        }
    }
}