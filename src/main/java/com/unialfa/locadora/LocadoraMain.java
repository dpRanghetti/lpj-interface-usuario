package com.unialfa.locadora;

import javax.swing.*;

public class LocadoraMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var form = new LocadoraForm();
            form.setVisible(true);
        });
    }
}
