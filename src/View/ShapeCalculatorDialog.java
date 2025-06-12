package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;  
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeCalculatorDialog extends JDialog {
    private List<JTextField> inputFields = new ArrayList<>();
    private JTextArea resultArea;
    private ShapeCalculator calculator;
    private String shapeName;
    private double[] defaultValues;
    private String[] inputLabels;
    private JPanel inputPanel;
    private JPanel buttonPanel;

    /**
     * @param parent        window parent
     * @param shapeName     nama bentuk (eg. "Persegi Panjang")
     * @param labels        label input (eg. {"Panjang","Lebar"})
     * @param defaults      nilai default sebagai string (eg. {"5.0","8.0"})
     * @param calculator    implementasi perhitungan
     */
    public ShapeCalculatorDialog(JFrame parent,
                                 String shapeName,
                                 String[] labels,
                                 String[] defaults,
                                 ShapeCalculator calculator) {
        super(parent, "Perhitungan " + shapeName, true);
        this.shapeName = shapeName;
        this.calculator = calculator;
        this.inputLabels = labels;
        this.defaultValues = new double[defaults.length];
        
        for (int i = 0; i < defaults.length; i++) {
            try { 
                defaultValues[i] = Double.parseDouble(defaults[i]); 
            } catch (NumberFormatException ex) { 
                defaultValues[i] = 0; 
            }
        }

        initializeDialog();
        setupLayout(labels, defaults);
    }

    private void initializeDialog() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        setSize(900, 600);
        setLocationRelativeTo(getParent());
        setResizable(true);
    }

    private void setupLayout(String[] labels, String[] defaults) {
        inputPanel = createInputPanel(labels, defaults);
        add(inputPanel, BorderLayout.WEST);
        add(createResultPanel(), BorderLayout.CENTER);
        
        buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel(String[] labels, String[] defaults) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)),
                "Input Nilai", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12), new Color(70, 130, 180)
            ),
            new EmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputFields.clear(); // Clear existing fields
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; 
            gbc.gridy = i; 
            gbc.weightx = 0;
            
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            lbl.setForeground(new Color(50, 50, 50));
            panel.add(lbl, gbc);

            gbc.gridx = 1; 
            gbc.weightx = 1.0;
            
            JTextField tf = new JTextField(defaults[i]);
            tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
            tf.setPreferredSize(new Dimension(140, 35));
            tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
                new EmptyBorder(5, 8, 5, 8)
            ));
            tf.setHorizontalAlignment(SwingConstants.RIGHT);
            
            // Add focus listener for better UX
            tf.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    tf.selectAll();
                }
            });
            
            inputFields.add(tf);
            panel.add(tf, gbc);
        }
        
        panel.setPreferredSize(new Dimension(300, getHeight()));
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 0)),
                "Hasil Perhitungan", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12), new Color(0, 120, 0)
            ), 
            new EmptyBorder(15, 15, 15, 15)
        ));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(248, 255, 248));
        resultArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JButton btnHitung = createStyledButton("Hitung Statis", new Color(70, 130, 180), Color.WHITE);
        btnHitung.addActionListener(e -> onRequestStatis());

        JButton btnReset = createStyledButton("Reset", new Color(220, 220, 220), Color.BLACK);
        btnReset.addActionListener(e -> resetFields());

        JButton btnTutup = createStyledButton("Tutup", new Color(220, 53, 69), Color.WHITE);
        btnTutup.addActionListener(e -> dispose());

        panel.add(btnHitung);
        panel.add(btnReset);
        panel.add(btnTutup);
        return panel;
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
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

    private void resetFields() {
        for (int i = 0; i < inputFields.size() && i < defaultValues.length; i++) {
            inputFields.get(i).setText(String.valueOf(defaultValues[i]));
        }
        resultArea.setText("");
        resultArea.setCaretPosition(0);
    }

    /**
     * Tahap awal: tampilkan hasil statis via custom dialog dengan opsi lanjut.
     */
    private void onRequestStatis() {
        try {
            CalculationResult baseRes = calculator.calculate(defaultValues);
            showStaticResultDialog(baseRes);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error dalam perhitungan: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStaticResultDialog(CalculationResult baseRes) {
        StaticResultDialog dialog = new StaticResultDialog(
            this, shapeName, baseRes, buildConfirmationMessage()
        );
        
        dialog.setVisible(true);
        
        if (dialog.shouldProceedToInput()) {
            showInputOverrideDialog();
        }
    }

    private void showInputOverrideDialog() {
        InputOverrideDialog overrideDialog = new InputOverrideDialog(
            this, shapeName, inputLabels, getCurrentInputValues()
        );
        
        overrideDialog.setVisible(true);
        
        if (overrideDialog.isConfirmed()) {
            double[] newValues = overrideDialog.getInputValues();
            if (newValues != null) {
                // Update input fields dengan nilai baru
                updateInputFields(newValues);
                // Hitung dan tampilkan hasil
                calculateAndDisplayResult(newValues);
            }
        }
    }

    private String[] getCurrentInputValues() {
        String[] values = new String[inputFields.size()];
        for (int i = 0; i < inputFields.size(); i++) {
            values[i] = inputFields.get(i).getText();
        }
        return values;
    }

    private void updateInputFields(double[] values) {
        for (int i = 0; i < inputFields.size() && i < values.length; i++) {
            inputFields.get(i).setText(String.format("%.2f", values[i]));
        }
    }

    private void calculateAndDisplayResult(double[] values) {
        try {
            CalculationResult result = calculator.calculate(values);
            tampilkanResult(result, "ADAPTIF");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error dalam perhitungan: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    private void tampilkanResult(CalculationResult res, String tag) {
        String txt = String.format(
            "╔═══════════════════════════════════════╗%n" +
            "║        HASIL %s %-15s ║%n" +
            "╠═══════════════════════════════════════╣%n" +
            "║ %s%n" +
            "║%n" +
            "║ 📐 Luas      : %-18.2f ║%n" +
            "║ 📏 Keliling  : %-18.2f ║%n" +
            "╚═══════════════════════════════════════╝%n",
            tag, shapeName.toUpperCase(),
            formatDimensionInfo(res.getDimensionInfo()),
            res.getArea(), res.getPerimeter()
        );
        resultArea.setText(txt);
        resultArea.setCaretPosition(0);
    }

    private String formatDimensionInfo(String info) {
        // Format dimension info to fit in the box
        String[] lines = info.split("\n");
        StringBuilder formatted = new StringBuilder();
        for (String line : lines) {
            if (line.length() > 35) {
                formatted.append("║ ").append(line.substring(0, 35)).append("... ║\n");
            } else {
                formatted.append(String.format("║ %-37s ║", line));
                if (!line.equals(lines[lines.length - 1])) {
                    formatted.append("\n");
                }
            }
        }
        return formatted.toString();
    }

    // Interface dan class yang sudah ada
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

        public String getDimensionInfo() { return dimensionInfo; }
        public double getArea() { return area; }
        public double getPerimeter() { return perimeter; }
    }
}

// Dialog untuk menampilkan hasil statis dengan opsi lanjut
class StaticResultDialog extends JDialog {
    private boolean proceedToInput = false;
    
    public StaticResultDialog(JDialog parent, String shapeName, 
                             ShapeCalculatorDialog.CalculationResult result, 
                             String confirmationMessage) {
        super(parent, "Hasil Perhitungan Statis", true);
        initializeDialog(shapeName, result, confirmationMessage);
    }
    
    private void initializeDialog(String shapeName, 
                                 ShapeCalculatorDialog.CalculationResult result, 
                                 String confirmationMessage) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        
        add(createResultPanel(shapeName, result), BorderLayout.CENTER);
        add(createConfirmationPanel(confirmationMessage), BorderLayout.SOUTH);
    }
    
    private JPanel createResultPanel(String shapeName, 
                                   ShapeCalculatorDialog.CalculationResult result) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setBackground(new Color(248, 255, 248));
        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        String resultText = String.format(
            "╔═══════════════════════════════════════╗%n" +
            "║           HASIL STATIS                ║%n" +
            "║           %s%-15s         ║%n" +
            "╠═══════════════════════════════════════╣%n" +
            "║                                       ║%n" +
            "║ %s%n" +
            "║                                       ║%n" +
            "║ 📐 Luas      : %-18.2f ║%n" +
            "║ 📏 Keliling  : %-18.2f ║%n" +
            "║                                       ║%n" +
            "╚═══════════════════════════════════════╝",
            shapeName.toUpperCase(), "",
            formatDimensionForStatic(result.getDimensionInfo()),
            result.getArea(), result.getPerimeter()
        );
        
        resultArea.setText(resultText);
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 0)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private String formatDimensionForStatic(String info) {
        String[] lines = info.split("\n");
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.length() > 35) {
                formatted.append("║ ").append(line.substring(0, 35)).append("... ║");
            } else {
                formatted.append(String.format("║ %-37s ║", line));
            }
            if (i < lines.length - 1) {
                formatted.append("%n");
            }
        }
        return formatted.toString();
    }
    
    private JPanel createConfirmationPanel(String confirmationMessage) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        JLabel messageLabel = new JLabel("<html><div style='text-align: center; font-size: 13px;'>" + 
                                       confirmationMessage + "</div></html>");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnYa = createConfirmButton("Ya", new Color(40, 167, 69), Color.WHITE);
        btnYa.addActionListener(e -> {
            proceedToInput = true;
            dispose();
        });
        
        JButton btnTidak = createConfirmButton("Tidak", new Color(220, 53, 69), Color.WHITE);
        btnTidak.addActionListener(e -> dispose());
        
        buttonPanel.add(btnYa);
        buttonPanel.add(btnTidak);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JButton createConfirmButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 25, 8, 25));
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
    
    public boolean shouldProceedToInput() {
        return proceedToInput;
    }
}

// Dialog untuk input ulang dengan overload
class InputOverrideDialog extends JDialog {
    private List<JTextField> inputFields = new ArrayList<>();
    private boolean confirmed = false;
    private String[] inputLabels;
    private double[] inputValues;
    
    public InputOverrideDialog(JDialog parent, String shapeName, 
                              String[] labels, String[] currentValues) {
        super(parent, "Input Ulang - " + shapeName, true);
        this.inputLabels = labels;
        initializeDialog(shapeName, labels, currentValues);
    }
    
    private void initializeDialog(String shapeName, String[] labels, String[] currentValues) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        setSize(450, 300 + (labels.length * 50));
        setLocationRelativeTo(getParent());
        
        add(createHeaderPanel(shapeName), BorderLayout.NORTH);
        add(createInputPanel(labels, currentValues), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel(String shapeName) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(70, 130, 180));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("Input Ulang - " + shapeName);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel subtitleLabel = new JLabel("Masukkan nilai baru untuk perhitungan");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(230, 230, 230));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(subtitleLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createInputPanel(String[] labels, String[] currentValues) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; 
            gbc.gridy = i; 
            gbc.weightx = 0;
            
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            label.setForeground(new Color(50, 50, 50));
            panel.add(label, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1.0;
            
            JTextField textField = new JTextField(currentValues[i]);
            textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
            textField.setPreferredSize(new Dimension(200, 35));
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
                new EmptyBorder(5, 10, 5, 10)
            ));
            textField.setHorizontalAlignment(SwingConstants.RIGHT);
            
            // Tambahkan focus listener
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    textField.selectAll();
                    textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true),
                        new EmptyBorder(5, 10, 5, 10)
                    ));
                }
                
                @Override
                public void focusLost(FocusEvent e) {
                    textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
                        new EmptyBorder(5, 10, 5, 10)
                    ));
                }
            });
            
            inputFields.add(textField);
            panel.add(textField, gbc);
        }
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)),
            new EmptyBorder(10, 0, 10, 0)
        ));
        
        JButton btnKonfirmasi = createActionButton("Konfirmasi", new Color(40, 167, 69), Color.WHITE);
        btnKonfirmasi.addActionListener(e -> confirmInput());
        
        JButton btnBatal = createActionButton("Batal", new Color(220, 53, 69), Color.WHITE);
        btnBatal.addActionListener(e -> dispose());
        
        panel.add(btnKonfirmasi);
        panel.add(btnBatal);
        
        return panel;
    }
    
    private JButton createActionButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 25, 10, 25));
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
    
    private void confirmInput() {
        try {
            inputValues = new double[inputFields.size()];
            for (int i = 0; i < inputFields.size(); i++) {
                inputValues[i] = Double.parseDouble(inputFields.get(i).getText().trim());
                if (inputValues[i] <= 0) {
                    throw new IllegalArgumentException("Nilai harus lebih besar dari 0");
                }
            }
            confirmed = true;
            dispose();
        } catch (NumberFormatException ex) {
            showError("Input harus berupa angka yang valid!");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public double[] getInputValues() {
        return inputValues;
    }
}