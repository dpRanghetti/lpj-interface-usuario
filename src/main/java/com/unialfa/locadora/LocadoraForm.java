package com.unialfa.locadora;

import javax.swing.*;
import java.awt.*;

public class LocadoraForm extends JFrame {

    private JLabel labelFilme;
    private JTextField campoFilme;
    private JButton botaoSalvar;
    private JList<String> listaDeFilmes;

    private String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};

    public LocadoraForm() {
        setTitle("Locadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelFilme = new JLabel("Nome do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelFilme, constraints);

        campoFilme = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoFilme, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        painelEntrada.add(botaoSalvar, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        listaDeFilmes = new JList<>(carregarDadosLocadoras());
        listaDeFilmes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaDeFilmes);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        //pack();
        setLocationRelativeTo(null);
    }

    private DefaultListModel<String> carregarDadosLocadoras() {
        var service = new LocadoraService();
        DefaultListModel<String> model = new DefaultListModel<>();
        service.readerFile().forEach(model::addElement);
        return model;
    }

    private void executarAcaoDoBotao() {
        var service = new LocadoraService();
        if (!campoFilme.getText().isEmpty() || !campoFilme.getText().isBlank()) {
            service.writerFile(campoFilme.getText());
            campoFilme.setText("");
            DefaultListModel<String> model = new DefaultListModel<>();
            service.readerFile().forEach(model::addElement);
            listaDeFilmes.setModel(model);
        }
    }
}
