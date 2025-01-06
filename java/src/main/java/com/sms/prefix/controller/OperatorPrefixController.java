package com.sms.prefix.controller;

import com.sms.prefix.model.OperatorPrefix;
import com.sms.prefix.service.OperatorPrefixService;
import com.sms.prefix.view.OperatorPrefixView;

import java.util.List;

public class OperatorPrefixController {
    private final OperatorPrefixService service;
    private final OperatorPrefixView view;
    private OperatorPrefix currentPrefix;

    public OperatorPrefixController(OperatorPrefixService service, OperatorPrefixView view) {
        this.service = service;
        this.view = view;
        
        // Initialize view listeners
        view.setCreateButtonListener(this::handleCreate);
        view.setRefreshButtonListener(this::handleRefresh);
        
        // Load initial data
        refreshData();
    }

    private void handleCreate() {
        // TODO: Implement create dialog
    }

    private void handleRefresh() {
        refreshData();
    }

    private void refreshData() {
        try {
            List<OperatorPrefix> prefixes = service.findAll();
            view.updateTable(prefixes);
            
            if (!prefixes.isEmpty()) {
                currentPrefix = prefixes.get(prefixes.size() - 1);
                view.setStatusText(prefixes.size() >= 13 ? "Más..." : "Final.");
            } else {
                view.setStatusText("Final.");
            }
        } catch (Exception e) {
            view.showError("Error al cargar datos: " + e.getMessage());
        }
    }

    private void handleNextPage() {
        if (currentPrefix != null) {
            try {
                List<OperatorPrefix> prefixes = service.getNextPage(
                    currentPrefix.getPrefix1(),
                    currentPrefix.getPrefix2(),
                    currentPrefix.getPrefix3()
                );
                view.updateTable(prefixes);
                
                if (!prefixes.isEmpty()) {
                    currentPrefix = prefixes.get(prefixes.size() - 1);
                    view.setStatusText(prefixes.size() >= 13 ? "Más..." : "Final.");
                }
            } catch (Exception e) {
                view.showError("Error al cargar la siguiente página: " + e.getMessage());
            }
        }
    }

    private void handlePreviousPage() {
        if (currentPrefix != null) {
            try {
                List<OperatorPrefix> prefixes = service.getPreviousPage(
                    currentPrefix.getPrefix1(),
                    currentPrefix.getPrefix2(),
                    currentPrefix.getPrefix3()
                );
                view.updateTable(prefixes);
                
                if (!prefixes.isEmpty()) {
                    currentPrefix = prefixes.get(0);
                    view.setStatusText("Más...");
                }
            } catch (Exception e) {
                view.showError("Error al cargar la página anterior: " + e.getMessage());
            }
        }
    }

    private void handlePosition() {
        try {
            String[] values = view.getPositionValues();
            if (values[0].isEmpty() && values[1].isEmpty() && values[2].isEmpty()) {
                return;
            }

            Integer prefix1 = values[0].isEmpty() ? null : Integer.parseInt(values[0]);
            if (prefix1 != null) {
                List<OperatorPrefix> prefixes = service.findByPosition(prefix1);
                view.updateTable(prefixes);
                
                if (!prefixes.isEmpty()) {
                    currentPrefix = prefixes.get(prefixes.size() - 1);
                    view.setStatusText(prefixes.size() >= 13 ? "Más..." : "Final.");
                }
            }
        } catch (NumberFormatException e) {
            view.showError("Valor inválido para posición");
        } catch (Exception e) {
            view.showError("Error al buscar posición: " + e.getMessage());
        }
    }

    public void show() {
        view.setVisible(true);
    }
} 