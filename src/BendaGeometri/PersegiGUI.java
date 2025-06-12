/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BendaGeometri;
import javax.swing.* ;
import java.awt.*;
import java.io.ByteArrayInputStream;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ASUS
 */
public class PersegiGUI extends JFrame {
    private Persegi persegi;
    private JTextField inputSisi;
    private JLabel hasilLabel;

    public PersegiGUI(Persegi persegi) {
        this.persegi = persegi;

        int pilihan = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda ingin mengubah nilai sisi persegi?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan != JOptionPane.YES_OPTION) {
            // Kalau user pilih "Tidak", keluar tanpa tampilkan GUI
            return;
        }

        setTitle("Ubah Sisi Persegi");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        inputSisi = new JTextField();
        inputSisi.setBorder(BorderFactory.createTitledBorder("Masukkan sisi baru"));
        add(inputSisi);

        JButton tombolHitung = new JButton("Hitung");
        add(tombolHitung);

        hasilLabel = new JLabel("");
        add(hasilLabel);

        tombolHitung.addActionListener(e -> {
            try {
                double sisiBaru = Double.parseDouble(inputSisi.getText());
                double luas = persegi.menghitungLuas(sisiBaru);
                double keliling = persegi.menghitungKeliling(sisiBaru);
                hasilLabel.setText(String.format("Luas: %.2f | Keliling: %.2f", luas, keliling));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input harus berupa angka.");
            }
        });

        setVisible(true);
    }
}
