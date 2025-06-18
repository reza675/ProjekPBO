 package Threading;

import BendaGeometri.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeometricShapeThreadGUI extends JFrame {
    private JList<String> shapeList;
    private JTextArea outputArea;
    private JButton runButton;
    private JSpinner valueSpinner;
    private ExecutorService executor;

    public GeometricShapeThreadGUI() {
        setTitle("Geometric Shape Thread Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create components
        String[] shapeNames = {
            "Persegi", "PersegiPanjang", "Segitiga", "Lingkaran", "BelahKetupat",
            "JajaranGenjang", "LayangLayang", "Trapesium", "Bola", "Kerucut",
            "Tabung", "PrismaSegitiga", "PrismaPersegiPanjang", "LimasSegitiga",
            "LimasPersegiPanjang"
        };
        
        shapeList = new JList<>(shapeNames);
        shapeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        
        valueSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.1, 100.0, 0.1));
        runButton = new JButton("Run Selected Shapes");
        
        // Layout
        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Base Value:"));
        inputPanel.add(valueSpinner);
        inputPanel.add(runButton);
        
        controlPanel.add(new JScrollPane(shapeList), BorderLayout.CENTER);
        controlPanel.add(inputPanel, BorderLayout.SOUTH);
        
        add(controlPanel, BorderLayout.WEST);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        
        // Event handling
        runButton.addActionListener(e -> runSelectedShapes());
        
        // Initialize executor
        executor = Executors.newFixedThreadPool(5);
        
        // Window settings
        pack();
        setLocationRelativeTo(null);
    }
    
    private void runSelectedShapes() {
        List<String> selected = shapeList.getSelectedValuesList();
        if (selected.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one shape!");
            return;
        }
        
        double value = (Double) valueSpinner.getValue();
        outputArea.setText(""); // Clear previous output
        
        for (String shapeName : selected) {
            try {
                Runnable shape = createShape(shapeName, value);
                if (shape != null) {
                    executor.execute(shape);
                }
            } catch (Exception ex) {
                outputArea.append("Error creating " + shapeName + ": " + ex.getMessage() + "\n");
            }
        }
    }
    
    private Runnable createShape(String shapeName, double value) {
        switch (shapeName) {
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
            
            case "Bola":
                return new Bola(value);
            case "Kerucut":
                return new Kerucut(value, value);
            case "Tabung":
                return new Tabung(value, value);
            case "PrismaSegitiga":
                return new PrismaSegitiga(value, value, value, value, value);
            case "PrismaPersegiPanjang":
                return new PrismaPersegiPanjang(value, value, value);
            case "LimasSegitiga":
                return new LimasSegitiga(value, value, value, value, value);
            case "LimasPersegiPanjang":
                return new LimasPersegiPanjang(value, value, value);
            default:
                return null;
        }
    }
    
    public void shutdown() {
        executor.shutdown();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeometricShapeThreadGUI gui = new GeometricShapeThreadGUI();
            gui.setVisible(true);
            
            // Add shutdown hook to clean up executor
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                gui.shutdown();
            }));
        });
    }
}