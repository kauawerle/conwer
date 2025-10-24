package view;

import controller.AppointmentController;
import controller.ServiceController;
import controller.UserController;
import model.entities.AppointmentEntity;
import model.entities.ServiceEntity;
import model.entities.UserEntity;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsPanel extends JPanel {

    private static final int STATUS_PENDENTE = 0;
    private static final int STATUS_REALIZADA = 1;
    private static final int STATUS_CANCELADA = 2;

    private JTextField nomeField;
    private JTextField emailField;
    private JTextField celularField;
    private JTextField idadeField;
    private JTextField cpfField;
    private JTextField userField;
    private JTextField specialityField;
    private JTextField descField;
    private JTextField priceField;
    private JTextField durationField;
    private JTextField nameClientField;
    private JTextField hourAppointmentField;
    private JTextField dateAppointmentField;
    private JPasswordField senhaField, confirmarSenhaField;

    private JComboBox<ServiceEntity> serviceComboBox;
    private ServiceEntity services;

    AppointmentController appointmentController = new AppointmentController();
    ServiceController serviceController = new ServiceController();
    UserController userController = new UserController();

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
        btNova.addActionListener(e -> abrirFormularioNovaConsulta());

        JButton btNovoService = new JButton("Novo serviço");
        btNovoService.addActionListener(e -> abrirFormularioNovoServico());


        p.add(btNova);
        p.add(btNovoService);
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
        cadastrarConsulta();
    }

    private void abrirFormularioNovoServico() {
        cadastrarServico();
    }

    private void cadastrarServico() {
        JFrame frame = new JFrame("Cadastrar Serviço");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(9, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(10);

        JLabel descLabel = new JLabel("Descrição:");
        JTextField descField = new JTextField(10);

        JLabel priceLabel = new JLabel("Preço:");
        JTextField priceField = new JTextField(10);

        JLabel durationLabel = new JLabel("Duração:");
        JTextField durationField = new JTextField(10);

        JButton registerServiceBtn = new JButton("Cadastrar Serviço");

        panel.add(nomeLabel);     panel.add(nomeField);
        panel.add(descLabel);     panel.add(descField);
        panel.add(priceLabel);    panel.add(priceField);
        panel.add(durationLabel); panel.add(durationField);
        panel.add(new JLabel());  panel.add(registerServiceBtn);

        frame.getContentPane().add(panel);
        frame.pack();                  // ajusta ao conteúdo
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        registerServiceBtn.addActionListener(e -> {
            String name = nomeField.getText().trim();
            String desc = descField.getText().trim();
            String duration = durationField.getText().trim();
            String priceText = priceField.getText().trim();

            // validação básica
            if (name.isEmpty() || desc.isEmpty() || duration.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // aceita vírgula como separador decimal
            priceText = priceText.replace(".", "").replace(",", "."); // remove separador de milhar e troca vírgula por ponto

            double pr;
            try {
                pr = Double.parseDouble(priceText);
            } catch (NumberFormatException ex1) {
                JOptionPane.showMessageDialog(frame, "Preço inválido. Use números (ex: 149,90).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ServiceEntity service = new ServiceEntity(name, desc, pr, duration);

            try {
                serviceController.createService(service);
                JOptionPane.showMessageDialog(frame, "Serviço cadastrado com sucesso!");
                frame.dispose();
            } catch (Exception ex2) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar: " + ex2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private void cadastrarConsulta() {
        JFrame frame = new JFrame("Cadastrar Consulta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(8, 2));

        frame.getContentPane().add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel nomeLabel = new JLabel("Título:");
        nomeField = new JTextField(10);

        JLabel dateAppointmentLabel = new JLabel("Data da consulta:");
        dateAppointmentField = new JTextField(10);

        JLabel hourAppointmentLabel = new JLabel("Hora da consulta:");
        hourAppointmentField = new JTextField(10);

        JLabel clientNameLabel = new JLabel("Nome do cliente:");
        nameClientField = new JTextField(10);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(hourAppointmentLabel);
        panel.add(hourAppointmentField);
        panel.add(dateAppointmentLabel);
        panel.add(dateAppointmentField);
        panel.add(clientNameLabel);
        panel.add(nameClientField);

        JLabel serviceLabel = new JLabel("Selecione o serviço:");
        serviceComboBox = new JComboBox<>();
        fillServiceComboBox();
        panel.add(serviceLabel);
        panel.add(serviceComboBox);

        serviceComboBox.addActionListener(e -> {
            ServiceEntity selectedService = (ServiceEntity) serviceComboBox.getSelectedItem();
            if (selectedService != null) {
                services = selectedService;
            }
        });


        UserEntity user = new UserEntity();
        user.setId(28L);
        JButton registerAppointmentBtn = new JButton("Cadastrar consulta");
        panel.add(registerAppointmentBtn);

        registerAppointmentBtn.addActionListener(e -> {
            frame.setVisible(false);
            registerAppointment(user, services);
        });
    }

    private void fillServiceComboBox() {
        List<ServiceEntity> services = serviceController.findAll();
        for (ServiceEntity service : services) {
            serviceComboBox.addItem(service);
        }
    }

    private String statusToText(int s) {
        return switch (s) {
            case STATUS_PENDENTE -> "Pendente";
            case STATUS_REALIZADA -> "Realizada";
            case STATUS_CANCELADA -> "Cancelada";
            default -> "Desconhecido (" + s + ")";
        };
    }


    public void registerAppointment(UserEntity user, ServiceEntity service) {
        String name = nomeField.getText();
        String dateAppointment = dateAppointmentField.getText();
        String hourAppointment = hourAppointmentField.getText();
        String name_client = nameClientField.getText();

        var userFind = userController.findPessoasById(user.getId());

        AppointmentEntity app = new AppointmentEntity(
                name,
                dateAppointment,
                hourAppointment,
                name_client,
                userFind.getName(),
                0,
                service.getId(),
                userFind.getId()
        );

        appointmentController.createAppointment(app);
        JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!");
    }

    private String nvl(String s) {
        return (s == null || s.isBlank()) ? "—" : s;
    }
}