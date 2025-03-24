package tugas_pemrogramankomputer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TugasPemrograman2 {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private ExecutorService executor;
    private int taskId = 1;

    public TugasPemrograman2() { // <-- Perbaikan di sini (tambah tanda ())
        frame = new JFrame("Task Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        model = new DefaultTableModel(new String[]{"Task ID", "Status"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(addButton);
        
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        
        executor = Executors.newFixedThreadPool(5); // Max 5 concurrent tasks
        
        frame.setVisible(true);
    }
    
    private void addTask() {
        int currentTaskId = taskId++;
        model.addRow(new Object[]{currentTaskId, "Running..."});
        executor.submit(() -> {
            try {
                Thread.sleep((int) (Math.random() * 5000 + 1000)); // Simulate task duration
                SwingUtilities.invokeLater(() -> updateTaskStatus(currentTaskId, "Completed"));
            } catch (InterruptedException e) {
                SwingUtilities.invokeLater(() -> updateTaskStatus(currentTaskId, "Failed"));
            }
        });
    }
    
    private void updateTaskStatus(int taskId, String status) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if ((int) model.getValueAt(i, 0) == taskId) {
                model.setValueAt(status, i, 1);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TugasPemrograman2::new);
    }
}
