package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestingManualGUI extends JFrame {
    
    // Visible components
    private JLabel titleLabel;
    private JLabel luasLabel;
    private JLabel kelilingLabel;
    private JLabel questionLabel;
    private JButton yaButton;
    private JButton tidakButton;
    
    // Hidden components (initially invisible)
    private JLabel alasLabel;
    private JTextField alasTextField;
    private JLabel tinggiLabel;
    private JTextField tinggiTextField;
    private JLabel sisiMiring1Label;
    private JTextField sisiMiring1TextField;
    private JLabel sisiMiring2Label;
    private JTextField sisiMiring2TextField;
    
    public TestingManualGUI() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        
        setTitle("Benda Segitiga");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }
    
    private void initializeComponents() {
        // Visible components
        titleLabel = new JLabel("Benda ?Segitiga");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        luasLabel = new JLabel("Luas ?Segitiga : ?ResultLuas");
        luasLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        kelilingLabel = new JLabel("Keliling ?Segitiga : ?ResultKeliling");
        kelilingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        questionLabel = new JLabel("Apakah Anda ingin Mengubah Nilai ?Params");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        yaButton = new JButton("Ya");
        yaButton.setBackground(new Color(92, 184, 92));
        yaButton.setForeground(Color.WHITE);
        yaButton.setFont(new Font("Arial", Font.BOLD, 14));
        yaButton.setFocusPainted(false);
        
        tidakButton = new JButton("Tidak");
        tidakButton.setBackground(new Color(217, 83, 79));
        tidakButton.setForeground(Color.WHITE);
        tidakButton.setFont(new Font("Arial", Font.BOLD, 14));
        tidakButton.setFocusPainted(false);
        
        // Hidden components (initially invisible)
        alasLabel = new JLabel("Masukkan alas baru");
        alasLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        alasLabel.setVisible(false);
        
        alasTextField = new JTextField();
        alasTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        alasTextField.setVisible(false);
        
        tinggiLabel = new JLabel("Masukkan tinggi baru");
        tinggiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tinggiLabel.setVisible(false);
        
        tinggiTextField = new JTextField();
        tinggiTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        tinggiTextField.setVisible(false);
        
        sisiMiring1Label = new JLabel("Masukkan sisi miring 1 baru");
        sisiMiring1Label.setFont(new Font("Arial", Font.PLAIN, 14));
        sisiMiring1Label.setVisible(false);
        
        sisiMiring1TextField = new JTextField();
        sisiMiring1TextField.setFont(new Font("Arial", Font.PLAIN, 14));
        sisiMiring1TextField.setVisible(false);
        
        sisiMiring2Label = new JLabel("Masukkan sisi miring 2 baru");
        sisiMiring2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        sisiMiring2Label.setVisible(false);
        
        sisiMiring2TextField = new JTextField();
        sisiMiring2TextField.setFont(new Font("Arial", Font.PLAIN, 14));
        sisiMiring2TextField.setVisible(false);
    }
    
    private void setupLayout() {
        // Add visible components
        add(titleLabel);
        add(luasLabel);
        add(kelilingLabel);
        add(questionLabel);
        add(yaButton);
        add(tidakButton);
        
        // Add hidden components
        add(alasLabel);
        add(alasTextField);
        add(tinggiLabel);
        add(tinggiTextField);
        add(sisiMiring1Label);
        add(sisiMiring1TextField);
        add(sisiMiring2Label);
        add(sisiMiring2TextField);
        
        // Set bounds for visible components
        titleLabel.setBounds(150, 50, 300, 40);
        luasLabel.setBounds(150, 120, 300, 25);
        kelilingLabel.setBounds(150, 160, 300, 25);
        questionLabel.setBounds(120, 220, 360, 25);
        yaButton.setBounds(200, 270, 80, 35);
        tidakButton.setBounds(320, 270, 80, 35);
        
        // Set bounds for hidden components (positioned below the buttons)
        alasLabel.setBounds(150, 330, 200, 25);
        alasTextField.setBounds(150, 360, 300, 30);
        tinggiLabel.setBounds(150, 400, 200, 25);
        tinggiTextField.setBounds(150, 430, 300, 30);
        sisiMiring1Label.setBounds(150, 470, 200, 25);
        sisiMiring1TextField.setBounds(150, 500, 300, 30);
        sisiMiring2Label.setBounds(150, 540, 200, 25);
        sisiMiring2TextField.setBounds(150, 570, 300, 30);
    }
    
    private void setupEventHandlers() {
        yaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Make hidden components visible
                alasLabel.setVisible(true);
                alasTextField.setVisible(true);
                tinggiLabel.setVisible(true);
                tinggiTextField.setVisible(true);
                sisiMiring1Label.setVisible(true);
                sisiMiring1TextField.setVisible(true);
                sisiMiring2Label.setVisible(true);
                sisiMiring2TextField.setVisible(true);
                
                // Repaint the frame to show the changes
                repaint();
            }
        });
        
        tidakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the components if they were visible
                alasLabel.setVisible(false);
                alasTextField.setVisible(false);
                tinggiLabel.setVisible(false);
                tinggiTextField.setVisible(false);
                sisiMiring1Label.setVisible(false);
                sisiMiring1TextField.setVisible(false);
                sisiMiring2Label.setVisible(false);
                sisiMiring2TextField.setVisible(false);
                
                // Repaint the frame to show the changes
                repaint();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestingManualGUI ui = new TestingManualGUI();
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
    }
}