/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testswingcomponent;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.*;


public class TestSwingComponent implements ActionListener, ItemListener, Serializable {

   private JTextArea area;
   private JFrame frame;
   private JCheckBox box;
   
    public static void main(String[] args) throws IOException {
        TestSwingComponent test = new TestSwingComponent();
        test.go();
    }
    private void go(){
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JButton button = new JButton("Click me!");
        button.addActionListener(this);
        
        Clean cl=new Clean();
        cl.action();
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setVisible(true);
        
        area = new JTextArea(12,20);
        area.setLineWrap(true);//перенос на другую строку
        JScrollPane scroll = new JScrollPane(area);//прокрутка
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        
        box = new  JCheckBox("I did'nt vote.");
        frame.getContentPane().add(BorderLayout.WEST,box);
        box.addItemListener(this);
        
        
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        //area.setText("Click me!");
        area.append("Click me!");//добавить текст к существующему
        area.append("\n");
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String s = "I voted. ";
        if(box.isSelected()) box.setText(s);
        else box.setText("I did'nt vote.");
        
    }
    class Clean implements ActionListener{
       
        public void action(){
            JButton cleanButton = new JButton("Clean!");
            cleanButton.addActionListener(this);
            frame.getContentPane().add(BorderLayout.NORTH,cleanButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
             area.setText("");
        }
        
    }
}
