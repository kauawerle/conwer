package view;

import controller.UserController;
import model.entities.AddressEntity;
import model.entities.UserEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientsPanel extends JPanel {
    private final UserController userController = new UserController();

    private final JTable table;
    private final DefaultTableModel model;
    private final JTextArea detalhes = new JTextArea(8, 40);

    public PatientsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Nome", "Email", "Telefone"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> Long.class;
                    default -> String.class;
                };
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> mostrarDetalhesSelecionado());

        add(new JScrollPane(table), BorderLayout.CENTER);

        detalhes.setEditable(false);
        detalhes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JPanel south = new JPanel(new BorderLayout());
        south.setBorder(BorderFactory.createTitledBorder("Detalhes do paciente"));
        south.add(new JScrollPane(detalhes), BorderLayout.CENTER);

        JButton btRecarregar = new JButton("Recarregar");
        btRecarregar.addActionListener(e -> recarregar());

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btRecarregar);
        south.add(actions, BorderLayout.SOUTH);

        add(south, BorderLayout.SOUTH);

        recarregar();
    }

    private void recarregar() {
        model.setRowCount(0);
        List<UserEntity> usuarios = safeFindAll();
        for (UserEntity u : usuarios) {
            model.addRow(new Object[]{
                    u.getId(),
                    getNome(u),
                    u.getEmail(),
                    getTelefone(u)
            });
        }
        detalhes.setText("");
    }

    private void mostrarDetalhesSelecionado() {
        int row = table.getSelectedRow();
        if (row < 0) {
            detalhes.setText("");
            return;
        }

        Long id = (Long) model.getValueAt(row, 0);
        UserEntity u = userController.findPessoasById(id);
        if (u == null) {
            detalhes.setText("");
            return;
        }

        AddressEntity a = u.getAddress();
        String endereco = (a == null)
                ? "—"
                : String.format("%s, %s - %s/%s CEP: %s%s",
                safe(a.getRua()), safe(a.getNumero()), safe(a.getCidade()),
                safe(a.getUf()), safe(a.getCep()),
                (a.getComplemento() != null && !a.getComplemento().isBlank())
                        ? (" (" + a.getComplemento() + ")")
                        : "");

        String texto = ""
                + "ID: " + u.getId() + "\n"
                + "Nome: " + getNome(u) + "\n"
                + "Email: " + safe(u.getEmail()) + "\n"
                + "Telefone: " + getTelefone(u) + "\n"
                + "Endereço: " + endereco + "\n";

        detalhes.setText(texto);
    }

    private String getNome(UserEntity u) {
        try {
            return (String) u.getClass().getMethod("getNome").invoke(u);
        } catch (Exception ignore) {}
        try {
            return (String) u.getClass().getMethod("getName").invoke(u);
        } catch (Exception ignore) {}
        return String.valueOf(u);
    }

    private String getTelefone(UserEntity u) {
        try {
            return (String) u.getClass().getMethod("getTelefone").invoke(u);
        } catch (Exception ignore) {}
        try {
            return (String) u.getClass().getMethod("getPhone").invoke(u);
        } catch (Exception ignore) {}
        return "—";
    }

    private String safe(String s) {
        return (s == null || s.isBlank()) ? "—" : s;
    }

    private List<UserEntity> safeFindAll() {
        try {
            List<UserEntity> list = userController.findAll();
            return list != null ? list : new ArrayList<>();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this,
                    "Falha ao carregar pacientes: " + t.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
