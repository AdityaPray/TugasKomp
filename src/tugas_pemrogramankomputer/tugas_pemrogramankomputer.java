/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas_pemrogramankomputer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class tugas_pemrogramankomputer {
    public static void main(String[] args) {
        new GradeCalculatorFrame();
    }
}

class GradeCalculatorFrame extends JFrame {
    private JTextField nameField, scoreField;
    private JTextArea resultArea;
    private ArrayList<Integer> scores;
    private final int MAX_SCORE = 100;
    private JButton addButton;

    public GradeCalculatorFrame() {
        scores = new ArrayList<>();
        setTitle("Nilai Rata-rata Siswa");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Nama Siswa:"));
        nameField = new JTextField(20);
        add(nameField);
        
        add(new JLabel("Nilai:"));
        scoreField = new JTextField(5);
        add(scoreField);
        
        addButton = new JButton("Tambah");
        add(addButton);
        
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
        
        JButton calculateButton = new JButton("Hitung Rata-rata");
        add(calculateButton);
        
        scoreField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addButton.doClick();
                }
            }
        });
        
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    scoreField.requestFocus();
                }
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addScore();
            }
        });
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });
        
        setVisible(true);
    }
    
    private void addScore() {
        try {
            int score = Integer.parseInt(scoreField.getText());
            if (score < 0 || score > MAX_SCORE) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            scores.add(score);
            String grade = classifyGrade(score);
            resultArea.append(nameField.getText() + " - " + score + " (" + grade + ")\n");
            nameField.setText("");
            scoreField.setText("");
            nameField.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateAverage() {
        if (scores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada nilai yang dimasukkan", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        double average = (double) total / scores.size();
        resultArea.append("\nRata-rata: " + String.format("%.2f", average) + "\n");
    }
    
    private String classifyGrade(int score) {
        if (score >= 85) return "A";
        if (score >= 70) return "B";
        if (score >= 50) return "C";
        return "D";
    }
}
