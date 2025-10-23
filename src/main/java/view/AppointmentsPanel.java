package view;

import controller.AppointmentController;
import model.entities.AppointmentEntity;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsPanel extends JPanel {

    private static final int STATUS_PENDENTE = 0;
    private static final int STATUS_REALIZADA = 1;
    private static final int STATUS_CANCELADA = 2;


    private final boolean mostrarPendentes;
    private final AppointmentController controller = new AppointmentController();


    private final DefaultTableModel model;
    private final JTable table;

    public AppointmentsPanel(boolean mostrarPendentes) {
        this.mostrarPendentes = mostrarPendentes;
        setLayout(new BorderLayout());


        model = new DefaultTableModel(new String[]{"ID", "Data", "Hora", "Paciente", "Profissional", "Status"}, 0) {
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


        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildActions(), BorderLayout.SOUTH);


        recarregar();
    }


    private JPanel buildActions() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        JButton btRecarregar = new JButton("Recarregar");
        btRecarregar.addActionListener(e -> recarregar());


        JButton btMarcarRealizada = new JButton("Marcar como realizada");
        btMarcarRealizada.addActionListener(e -> marcarRealizadaSelecionada());


        JButton btCancelar = new JButton("Cancelar consulta");
        btCancelar.addActionListener(e -> cancelarSelecionada());


        JButton btNova = new JButton("Nova consulta");
        btNova.addActionListener(e -> abrirFormularioNovaConsulta()); // TODO: implemente seu dialog/form


        p.add(btNova);
        p.add(btCancelar);
        p.add(btMarcarRealizada);
        p.add(btRecarregar);
        return p;
    }

    private void recarregar() {
        model.setRowCount(0);
        List<AppointmentEntity> todas = safeFindAll();


        for (AppointmentEntity a : todas) {
            boolean isPendente = a.getStatus() == STATUS_PENDENTE;
            boolean pertence = mostrarPendentes ? isPendente : !isPendente; // histórico = realizadas ou canceladas
            if (!pertence) continue;


            model.addRow(new Object[]{
                    a.getId(),
                    nvl(a.getDate()),
                    nvl(a.getHour()),
                    nvl(a.getName_client()),
                    nvl(a.getName_professional()),
                    statusToText(a.getStatus())
            });
        }
    }


    private List<AppointmentEntity> safeFindAll() {
        try {
            List<AppointmentEntity> list = controller.findAll(); // adapte se seu controller tiver outro nome
            return (list != null) ? list : new ArrayList<>();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, "Falha ao carregar consultas: " + t.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }


    private void marcarRealizadaSelecionada() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        Long id = (Long) model.getValueAt(row, 0);
        try {
            AppointmentEntity a = controller.findAppointmentById(id);
            if (a == null) return;
            a.setStatus(STATUS_REALIZADA);
            controller.update(a);
            recarregar();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + t.getMessage());
        }
    }


    private void cancelarSelecionada() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        Long id = (Long) model.getValueAt(row, 0);
        try {
            AppointmentEntity a = controller.findAppointmentById(id);
            if (a == null) return;
            int opt = JOptionPane.showConfirmDialog(this, "Confirma cancelamento desta consulta?", "Cancelar", JOptionPane.YES_NO_OPTION);
            if (opt != JOptionPane.YES_OPTION) return;
            a.setStatus(STATUS_CANCELADA);
            controller.update(a);
            recarregar();
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, "Erro ao cancelar: " + t.getMessage());
        }
    }

    private void abrirFormularioNovaConsulta() {
// TODO: abra seu diálogo de criação/edição de consulta, então:
// new AppointmentFormDialog(...).setVisible(true);
        JOptionPane.showMessageDialog(this, "Formulário de nova consulta ainda não implementado.");
    }

    private String statusToText(int s) {
        return switch (s) {
            case STATUS_PENDENTE -> "Pendente";
            case STATUS_REALIZADA -> "Realizada";
            case STATUS_CANCELADA -> "Cancelada";
            default -> "Desconhecido (" + s + ")";
        };
    }


    private String nvl(String s) {
        return (s == null || s.isBlank()) ? "—" : s;
    }
}