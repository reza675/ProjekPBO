package View;
import javax.swing.*;
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

    public ShapeCalculatorDialog(JFrame parent, String shapeName, String[] labels, String[] defaults, ShapeCalculator calculator) {
        super(parent, "Perhitungan " + shapeName, true);
        this.shapeName = shapeName;
        this.calculator = calculator;
        
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel input
        JPanel inputPanel = createInputPanel(labels, defaults);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        mainPanel.add(inputPanel, gbc);

        // Panel hasil
        JPanel resultPanel = createResultPanel();
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(resultPanel, gbc);

        // Panel tombol
        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        calculateAndDisplay();
    }

    private JPanel createInputPanel(String[] labels, String[] defaults) {
        JPanel inputPanel = new JPanel(new GridLayout(labels.length, 2, 10, 15));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180)),
            "Input Nilai",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12),
            new Color(70, 130, 180)
        ));
        inputPanel.setBackground(Color.WHITE);

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            
            JTextField textField = new JTextField(10);
            textField.setText(defaults[i]);
            textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        calculateAndDisplay();
                    }
                }
            });
            
            inputPanel.add(label);
            inputPanel.add(textField);
            inputFields.add(textField);
        }
        return inputPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 100, 0)),
            "Hasil Perhitungan",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12),
            new Color(0, 100, 0)
        ));
        resultPanel.setBackground(Color.WHITE);
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBackground(new Color(240, 255, 240));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        return resultPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton calculateButton = new JButton("Hitung");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        calculateButton.setBackground(new Color(70, 130, 180));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> calculateAndDisplay());
        
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        resetButton.setBackground(new Color(220, 220, 220));
        resetButton.addActionListener(e -> resetFields());
        
        JButton closeButton = new JButton("Tutup");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(205, 92, 92));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(closeButton);
        
        return buttonPanel;
    }

    private void calculateAndDisplay() {
        try {
            double[] values = new double[inputFields.size()];
            for (int i = 0; i < inputFields.size(); i++) {
                values[i] = Double.parseDouble(inputFields.get(i).getText());
                if (values[i] <= 0) {
                    throw new IllegalArgumentException("Nilai harus positif");
                }
            }
            
            CalculationResult result = calculator.calculate(values);
            String resultText = String.format(
                "=== HASIL PERHITUNGAN %s ===\n\n" +
                "Nama: %s\n\n" +
                "%s\n\n" +
                "Luas: %.2f\n" +
                "Keliling: %.2f\n\n" +
                "Method: %s",
                shapeName.toUpperCase(),
                result.getShapeName(),
                result.getDimensionInfo(),
                result.getArea(),
                result.getPerimeter(),
                result.getMethodInfo()
            );
            
            resultArea.setText(resultText);
        } catch (NumberFormatException e) {
            resultArea.setText("");
            JOptionPane.showMessageDialog(this,
                "Input harus berupa angka yang valid!",
                "Error Input",
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            resultArea.setText("");
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error Validasi",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            resultArea.setText("");
            JOptionPane.showMessageDialog(this,
                "Terjadi kesalahan: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        for (JTextField field : inputFields) {
            field.setText("");
        }
        resultArea.setText("");
    }

    public interface ShapeCalculator {
        CalculationResult calculate(double... values) throws Exception;
    }

    public static class CalculationResult {
        private String shapeName;
        private String dimensionInfo;
        private double area;
        private double perimeter;
        private String methodInfo;

        public CalculationResult(String shapeName, String dimensionInfo, 
                                double area, double perimeter, String methodInfo) {
            this.shapeName = shapeName;
            this.dimensionInfo = dimensionInfo;
            this.area = area;
            this.perimeter = perimeter;
            this.methodInfo = methodInfo;
        }

        // Getters
        public String getShapeName() { return shapeName; }
        public String getDimensionInfo() { return dimensionInfo; }
        public double getArea() { return area; }
        public double getPerimeter() { return perimeter; }
        public String getMethodInfo() { return methodInfo; }
    }
}