package com.sms.prefix.view;

import com.sms.prefix.model.OperatorPrefix;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class OperatorPrefixView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField positionField1;
    private JTextField positionField2;
    private JTextField positionField3;
    private JLabel statusLabel;
    private JButton createButton;
    private JButton refreshButton;
    
    public OperatorPrefixView() {
        setTitle("PREFIJOS OPERADORAS");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = new JPanel(new GridLayout(4, 1));
        headerPanel.add(new JLabel("SOCIOS - SMS"));
        headerPanel.add(new JLabel("Teclee opciones, pulse Intro"));
        
        // Create options panel
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.add(new JLabel("2=Modificar"));
        optionsPanel.add(new JLabel("4=Eliminar"));
        optionsPanel.add(new JLabel("5=Visualizar"));
        headerPanel.add(optionsPanel);
        
        // Create position panel
        JPanel positionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        positionPanel.add(new JLabel("Situar en:"));
        positionField1 = new JTextField(2);
        positionField2 = new JTextField(2);
        positionField3 = new JTextField(2);
        positionPanel.add(positionField1);
        positionPanel.add(positionField2);
        positionPanel.add(positionField3);
        headerPanel.add(positionPanel);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"Opc", "Prefijo1", "Prefijo2", "Prefijo3", "Operadora"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Only option column is editable
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        
        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createButton = new JButton("F6=Crear");
        refreshButton = new JButton("F5=Renovar");
        JButton exitButton = new JButton("F3=Salir");
        
        buttonsPanel.add(exitButton);
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(createButton);
        footerPanel.add(buttonsPanel, BorderLayout.WEST);
        
        // Create status label
        statusLabel = new JLabel("Más...");
        footerPanel.add(statusLabel, BorderLayout.EAST);
        
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Add key bindings
        KeyStroke f3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        KeyStroke f6 = KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0);
        
        getRootPane().registerKeyboardAction(e -> dispose(), "exit", f3, JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e -> refreshButton.doClick(), "refresh", f5, JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(e -> createButton.doClick(), "create", f6, JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        add(mainPanel);
        setLocationRelativeTo(null);
    }
    
    public void updateTable(List<OperatorPrefix> prefixes) {
        tableModel.setRowCount(0);
        for (OperatorPrefix prefix : prefixes) {
            tableModel.addRow(new Object[]{
                    "",
                    prefix.getPrefix1(),
                    prefix.getPrefix2(),
                    prefix.getPrefix3(),
                    prefix.getOperatorCode()
            });
        }
    }
    
    public void setCreateButtonListener(Runnable listener) {
        createButton.addActionListener(e -> listener.run());
    }
    
    public void setRefreshButtonListener(Runnable listener) {
        refreshButton.addActionListener(e -> listener.run());
    }
    
    public void setStatusText(String text) {
        statusLabel.setText(text);
    }
    
    public String[] getPositionValues() {
        return new String[]{
                positionField1.getText(),
                positionField2.getText(),
                positionField3.getText()
        };
    }
    
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
} 