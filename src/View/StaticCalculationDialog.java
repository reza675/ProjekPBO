package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StaticCalculationDialog extends JDialog {
    private JTextArea resultArea;
    private String shapeName;
    private String[] inputLabels;
    private double[] staticValues;
    private ShapeCalculator calculator;
    private boolean proceedToDynamic = false;

    public StaticCalculationDialog(JFrame parent,
            String shapeName,
            String[] inputLabels,
            double[] staticValues,
            ShapeCalculator calculator) {
        super(parent, "Perhitungan Statis - " + shapeName, true);
        this.shapeName = shapeName;
        this.inputLabels = inputLabels;
        this.staticValues = staticValues.clone();
        this.calculator = calculator;

        initializeDialog();
        calculateAndDisplay();
    }

    private void initializeDialog() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setSize(600, 500);
        setLocationRelativeTo(getParent());
        setResizable(false);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createResultPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(70, 130, 180));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Perhitungan Statis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel shapeLabel = new JLabel(shapeName, SwingConstants.CENTER);
        shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        shapeLabel.setForeground(new Color(220, 220, 220));

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(shapeLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(0, 120, 0)),
                        "Hasil Perhitungan Statis", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), new Color(0, 120, 0)),
                new EmptyBorder(15, 15, 15, 15)));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(248, 255, 248));
        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                new EmptyBorder(15, 20, 15, 20)));

        // Confirmation message
        JLabel confirmLabel = new JLabel(buildConfirmationMessage(), SwingConstants.CENTER);
        confirmLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        confirmLabel.setForeground(new Color(80, 80, 80));
        panel.add(confirmLabel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnYa = createStyledButton("Ya, Ubah Nilai", new Color(40, 167, 69), Color.WHITE);
        btnYa.addActionListener(this::onProceedToDynamic);

        JButton btnTidak = createStyledButton("Tidak", new Color(108, 117, 125), Color.WHITE);
        btnTidak.addActionListener(e -> dispose());

        buttonPanel.add(btnYa);
        buttonPanel.add(btnTidak);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            Color originalBg = bg;

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bg.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(originalBg);
            }
        });

        return btn;
    }

    private void calculateAndDisplay() {
        try {
            CalculationResult result = calculator.calculate(staticValues);
            displayResult(result);
        } catch (Exception ex) {
            resultArea.setText("Error dalam perhitungan: " + ex.getMessage());
        }
    }

    private void displayResult(CalculationResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(" HASIL PERHITUNGAN STATIS - ").append(shapeName.toUpperCase()).append("\n");
        sb.append("============================================\n\n");

        sb.append("METODE: Menggunakan method tanpa parameter\n\n");

        // Input values
        sb.append(" NILAI INPUT STATIS:\n");
        String unit;
        for (int i = 0; i < inputLabels.length && i < staticValues.length; i++) {
            if ("Tembereng Lingkaran".equals(shapeName) || "Juring Lingkaran".equals(shapeName)) {
                unit = (i == 1 ? "°" : " cm");
            } else {
                unit = " cm";
            }
            sb.append(String.format("   • %-9s : %6.2f%s\n",
                    inputLabels[i], staticValues[i], unit));
        }

        sb.append("\n");
        sb.append(" HASIL PERHITUNGAN:\n");
        sb.append(String.format("   • Luas      : %6.2f cm²\n", result.getArea()));
        sb.append(String.format("   • Keliling  : %6.2f cm\n", result.getPerimeter()));

        resultArea.setText(sb.toString());
        resultArea.setCaretPosition(0);
    }

    private String buildConfirmationMessage() {
        if (inputLabels.length == 0) {
            return "Apakah Anda ingin mengubah nilai?";
        } else if (inputLabels.length == 1) {
            return "Apakah Anda ingin mengubah " + inputLabels[0].toLowerCase() +
                    " " + shapeName.toLowerCase() + "?";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Apakah Anda ingin mengubah ");
            for (int i = 0; i < inputLabels.length; i++) {
                sb.append(inputLabels[i].toLowerCase());
                if (i < inputLabels.length - 2) {
                    sb.append(", ");
                } else if (i == inputLabels.length - 2) {
                    sb.append(" dan ");
                }
            }
            sb.append(" ").append(shapeName.toLowerCase()).append("?");
            return sb.toString();
        }
    }

    // PERBAIKAN: Hanya set flag, tidak langsung buka dialog baru
    private void onProceedToDynamic(ActionEvent e) {
        proceedToDynamic = true;
        dispose();
    }

    public boolean shouldProceedToDynamic() {
        return proceedToDynamic;
    }

    // Interface dan class yang diperlukan
    public interface ShapeCalculator {
        CalculationResult calculate(double... values);
    }

    public static class CalculationResult {
        private String dimensionInfo;
        private double area;
        private double perimeter;

        public CalculationResult(String dimensionInfo, double area, double perimeter) {
            this.dimensionInfo = dimensionInfo;
            this.area = area;
            this.perimeter = perimeter;
        }

        public String getDimensionInfo() {
            return dimensionInfo;
        }

        public double getArea() {
            return area;
        }

        public double getPerimeter() {
            return perimeter;
        }
    }
}