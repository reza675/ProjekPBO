/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import javax.swing.* ;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ASUS
 */
public class TestingManual extends JFrame{
    JButton tombolSave, tombolEdit, tombolDelete, tombolOpen ;
    public TestingManual(){
            setTitle("GRID LAYOUT");
            setLayout(new GridLayout(2,2));
            setSize(400,300);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            tombolOpen = new JButton("Open");
            tombolEdit = new JButton("Edit");
            tombolDelete = new JButton("Delete");
            tombolSave = new JButton("Save");
            
            add(tombolOpen);
            add(tombolEdit);
            add(tombolDelete);
            add(tombolSave);
            
//            tombolOpen.setBounds(10,10,150,20);
//            tombolEdit.setBounds(200,10,150,20);
//            tombolSave.setBounds(10,50,150,20);
//            tombolDelete.setBounds(200,50,150,20);
            
            
    }
}