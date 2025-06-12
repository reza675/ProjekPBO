package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk perhitungan dinamis
 * Mengambil input dari user dan melakukan overload pada class bentuk geometri
 */
public class DynamicCalculationDialog extends JDialog {
    private List<JTextField> inputFields = new ArrayList<>();
    private JTextArea resultArea;
    private String shapeName;
    private String[] inputLabels;
    private double[] initialValues;
    private StaticCalculationDialog.ShapeCalculator calculator;
    private JPanel inputPanel;
    private JPanel resultPanel;

    public DynamicCalculationDialog(JFrame parent,
            String shapeName,
            String[] inputLabels,
            double[] initialValues,
            StaticCalculationDialog.ShapeCalculator calculator) {
        super(parent, "Perhitungan Dinamis - " + shapeName, true);
        this.shapeName = shapeName;
        this.inputLabels = inputLabels;
        this.initialValues = initialValues.clone();
        this.calculator = calculator;

        initializeDialog();
        setupLayout();
    }

    private void initializeDialog() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }

    private void setupLayout() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 167, 69));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Perhitungan Dinamis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel shapeLabel = new JLabel(shapeName + " - Input Nilai Kustom", SwingConstants.CENTER);
        shapeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        shapeLabel.setForeground(new Color(220, 255, 220));

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(shapeLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel = createInputPanel();
        resultPanel = createResultPanel();

        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createInputPanel() {
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

        // Clear existing fields
        inputFields.clear();

        for (int i = 0; i < inputLabels.length; i++) {
            // Label
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;

            JLabel label = new JLabel(inputLabels[i] + ":");
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            label.setForeground(new Color(50, 50, 50));
            panel.add(label, gbc);

            // Input field
            gbc.gridx = 1;
            gbc.weightx = 1.0;

            JTextField textField = new JTextField(String.valueOf(initialValues[i]));
            textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
            textField.setPreferredSize(new Dimension(150, 40));
            textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true),
                    new EmptyBorder(8, 12, 8, 12)));
            textField.setHorizontalAlignment(SwingConstants.RIGHT);

            // Add real-time calculation on key release
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    calculateRealTime();
                }
            });

            // Focus listeners for better UX
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textField.selectAll();
                    textField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(255, 193, 7), 2, true),
                            new EmptyBorder(8, 12, 8, 12)));
                }

                @Override
                public void focusLost(FocusEvent e) {
                    textField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true),
                            new EmptyBorder(8, 12, 8, 12)));
                    calculateRealTime();
                }
            });

            inputFields.add(textField);
            panel.add(textField, gbc);

            // Unit label
            gbc.gridx = 2;
            gbc.weightx = 0;

            String unit;
            if ("Tembereng Lingkaran".equals(shapeName) || "Juring Lingkaran".equals(shapeName)) {
                unit = (i == 1 ? "°" : " cm");
            } else {
                unit = " cm";
            }

            JLabel unitLabel = new JLabel(unit);
            unitLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            unitLabel.setForeground(new Color(100, 100, 100));
            panel.add(unitLabel, gbc);
        }

        // Add instruction label
        gbc.gridx = 0;
        gbc.gridy = inputLabels.length;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 0, 0);

        panel.setPreferredSize(new Dimension(350, getHeight()));
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180)),
                        "Hasil Perhitungan Dinamis", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), new Color(70, 130, 180)),
                new EmptyBorder(15, 15, 15, 15)));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(240, 248, 255));
        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        // Initial calculation
        calculateRealTime();

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
                new EmptyBorder(10, 0, 10, 0)));

        JButton btnReset = createStyledButton("↺ Reset ke Nilai Awal", new Color(255, 193, 7), new Color(50, 50, 50));
        btnReset.addActionListener(this::resetToInitialValues);

        JButton btnTutup = createStyledButton("✕ Tutup", new Color(220, 53, 69), Color.WHITE);
        btnTutup.addActionListener(e -> dispose());

        panel.add(btnReset);
        panel.add(btnTutup);

        return panel;
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 18, 10, 18));
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

    private void calculateRealTime() {
        try {
            double[] values = getInputValues();
            if (values != null) {
                StaticCalculationDialog.CalculationResult result = calculator.calculate(values);
                displayResult(result, values);
            }
        } catch (Exception ex) {
            displayError("Error: " + ex.getMessage());
        }
    }

    private double[] getInputValues() {
        try {
            double[] values = new double[inputFields.size()];

            for (int i = 0; i < inputFields.size(); i++) {
                String text = inputFields.get(i).getText().trim()
                        .replace(',', '.'); // koma → titik
                double v;

                if (text.isEmpty()) {
                    v = initialValues[i];
                } else {
                    v = Double.parseDouble(text);
                    // validasi
                    if ("Sudut".equals(inputLabels[i])) {
                        if (v < 0 || v > 360) {
                            throw new IllegalArgumentException(
                                    "Sudut harus antara 0° - 360°");
                        }
                    } else {
                        if (v <= 0) {
                            throw new IllegalArgumentException(
                                    inputLabels[i] + " harus lebih dari nol");
                        }
                    }
                }

                values[i] = v;
            }

            return values;

        } catch (NumberFormatException ex) {
            displayError("Input harus berupa angka valid! Gunakan format: 28.5 atau 28,5");
            return null;
        } catch (IllegalArgumentException ex) {
            displayError(ex.getMessage());
            return null;
        }
    }

    private void displayResult(StaticCalculationDialog.CalculationResult result, double[] values) {
        StringBuilder sb = new StringBuilder();

        sb.append(" HASIL PERHITUNGAN DINAMIS - ").append(shapeName.toUpperCase()).append("\n");
        sb.append("====================================================\n\n");

        sb.append("METODE: Menggunakan method overload dengan parameter\n\n");

        sb.append("NILAI INPUT DINAMIS (OVERLOAD):\n");
        for (int i = 0; i < inputLabels.length && i < values.length; i++) {
            String unit;
            if ("Tembereng Lingkaran".equals(shapeName) || "Juring Lingkaran".equals(shapeName)) {
                unit = (i == 1 ? "°" : " cm");
            } else {
                unit = " cm";
            }
            sb.append(String.format("   • %-9s : %6.2f%s\n",
                    inputLabels[i], values[i], unit));
        }

        sb.append("\n");
        sb.append("HASIL PERHITUNGAN:\n");
        sb.append(String.format("   • Luas      : %6.2f cm²\n", result.getArea()));
        sb.append(String.format("   • Keliling  : %6.2f cm\n", result.getPerimeter()));
        resultArea.setText(sb.toString());
        resultArea.setCaretPosition(0);
    }

    private void displayError(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ERROR\n");
        sb.append("---\n\n");

        // Tampilkan pesan error spesifik
        if (message.contains("harus lebih dari nol")) {
            sb.append("Error: ").append(message).append("!");
        } else {
            sb.append("Error: ").append(message);
        }

        resultArea.setText(sb.toString());
    }

    private void resetToInitialValues(ActionEvent e) {
        for (int i = 0; i < inputFields.size() && i < initialValues.length; i++) {
            inputFields.get(i).setText(String.valueOf(initialValues[i]));
        }
        calculateRealTime();

        // Show confirmation
        JOptionPane.showMessageDialog(this,
                "Nilai telah direset ke nilai awal!",
                "Reset Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
    }

}