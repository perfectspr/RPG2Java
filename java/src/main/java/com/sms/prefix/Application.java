package com.sms.prefix;

import com.sms.prefix.controller.OperatorPrefixController;
import com.sms.prefix.repository.MockOperatorPrefixRepository;
import com.sms.prefix.service.OperatorPrefixService;
import com.sms.prefix.view.OperatorPrefixView;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize components
        SwingUtilities.invokeLater(() -> {
            MockOperatorPrefixRepository repository = new MockOperatorPrefixRepository();
            OperatorPrefixService service = new OperatorPrefixService(repository);
            OperatorPrefixView view = new OperatorPrefixView();
            OperatorPrefixController controller = new OperatorPrefixController(service, view);
            
            // Show the main window
            controller.show();
        });
    }
} 