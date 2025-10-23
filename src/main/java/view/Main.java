package view;

import controller.AppointmentController;
import controller.DrugController;
import controller.ServiceController;
import controller.UserController;
import model.dto.LoginDto;
import model.entities.AppointmentEntity;
import model.entities.DrugsEntity;
import model.entities.ServiceEntity;
import model.entities.UserEntity;
// imports de datepicker que você já tinha (se não usar, pode remover)
import model.lib.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

public class Main extends JFrame {

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

    private JFrame homeChoiceFrame;
    private JFrame loginFrame;

    UserController userController = new UserController();
    DrugController drugsController = new DrugController();
    ServiceController serviceController = new ServiceController();
    AppointmentController appointmentController =  new AppointmentController();

    public Main() {
        homeChoiceFrame = new JFrame("Home");
        homeChoiceFrame.setSize(300, 200);
        homeChoiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeChoiceFrame.setLayout(new GridLayout(3, 1));

        JButton loginButton = new JButton("Login");
        JButton cadastrarButton = new JButton("Cadastrar");

        loginButton.addActionListener(e -> {
            homeChoiceFrame.setVisible(false);
            Login();
        });

        cadastrarButton.addActionListener(e -> {
            homeChoiceFrame.setVisible(false);
            Register();
        });

        homeChoiceFrame.add(new JLabel("Bem-vindo! Escolha uma opção:", SwingConstants.CENTER));
        homeChoiceFrame.add(loginButton);
        homeChoiceFrame.add(cadastrarButton);
        homeChoiceFrame.setLocationRelativeTo(null);
        homeChoiceFrame.setVisible(true);
    }

    private void Register () {
        setTitle("Cadastrar usuário");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Celular:"));
        celularField = new JTextField();
        panel.add(celularField);

        panel.add(new JLabel("Idade:"));
        idadeField = new JTextField();
        panel.add(idadeField);

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Especialidade:"));
        specialityField = new JTextField();
        panel.add(specialityField);

        panel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        panel.add(new JLabel("Confirmar Senha:"));
        confirmarSenhaField = new JPasswordField();
        panel.add(confirmarSenhaField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> cadastrarUsuario());
        panel.add(cadastrarButton);

        add(panel);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            setVisible(false);
            if (homeChoiceFrame != null) homeChoiceFrame.setVisible(true);
        });
        panel.add(backButton);

        setVisible(true);
    }

    private void Login() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Email:");
        userField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(10);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(senhaField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);
        panel.add(new JLabel()); // espaçador

        loginFrame.getContentPane().add(panel);
        loginFrame.setSize(527, 329);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(e -> loginUser());
    }

    private void abrirHome() {
        // Fecha janelas antigas
        if (loginFrame != null) loginFrame.dispose();
        if (homeChoiceFrame != null) homeChoiceFrame.dispose();

        // Abre a nova janela principal com abas
        HomeFrame home = new HomeFrame();
        home.setVisible(true);
    }

    private void cadastrarRemedios() {
        JFrame frame = new JFrame("Cadastar remédio");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // agora só fecha esta janela
        JPanel panel = new JPanel(new GridLayout(5, 2));

        frame.getContentPane().add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(10);

        JLabel descLabel = new JLabel("Descrição:");
        descField = new JTextField(10);

        JLabel priceLabel = new JLabel("Preço:");
        priceField = new JTextField(10);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(descLabel);
        panel.add(descField);
        panel.add(priceLabel);
        panel.add(priceField);

        JButton registerDrugsBtn = new JButton("Cadastrar remédio");
        panel.add(registerDrugsBtn);

        registerDrugsBtn.addActionListener(e -> registerDrugs());
    }

    private void cadastrarServico() {
        JFrame frame = new JFrame("Cadastar Serviço");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(4, 2));

        frame.getContentPane().add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(10);

        JLabel descLabel = new JLabel("Descrição:");
        descField = new JTextField(10);

        JLabel priceLabel = new JLabel("Preço:");
        priceField = new JTextField(10);

        JLabel durationLabel = new JLabel("Duração do serviço realizado");
        durationField = new JTextField(10);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(descLabel);
        panel.add(descField);
        panel.add(priceLabel);
        panel.add(priceField);

        JButton registerServiceBtn = new JButton("Cadastrar serviço");
        panel.add(registerServiceBtn);

        registerServiceBtn.addActionListener(e -> registerService());
    }

    private void cadastrarConsulta(UserEntity user) {
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

        JButton registerAppointmentBtn = new JButton("Cadastrar consulta");
        panel.add(registerAppointmentBtn);

        registerAppointmentBtn.addActionListener(e -> {
            frame.setVisible(false);
            registerAppointment(user,services);
        });
    }

    private void fillServiceComboBox() {
        List<ServiceEntity> services = serviceController.findAll();
        for (ServiceEntity service : services) {
            serviceComboBox.addItem(service);
        }
    }

    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String celular = celularField.getText();
        int idade = Integer.parseInt(idadeField.getText());
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());
        String confirmarSenha = new String(confirmarSenhaField.getPassword());
        String speciality = new String(specialityField.getText());

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserEntity newUser =
                new UserEntity(
                        nome,
                        email,
                        senha,
                        celular,
                        idade,
                        cpf,
                        true,
                        LocalTime.now(),
                        speciality);

        userController.createUser(newUser);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
    }

    private void loginUser() {
        String email = userField.getText();
        String password = new String(senhaField.getPassword());
        LoginDto login = new LoginDto(email, password);
        try {
            UserEntity user = userController.login(login);
            abrirHome();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginFrame, "Falha no login: " + ex.getMessage(),
                    "Login", JOptionPane.ERROR_MESSAGE);
            if (homeChoiceFrame != null) homeChoiceFrame.setVisible(true);
        }
    }

    private void registerDrugs() {
        String name = nomeField.getText();
        String desc = descField.getText();
        String price = priceField.getText();

        double pr = Double.parseDouble(price);
        DrugsEntity drugs = new DrugsEntity(name, desc, pr);

        drugsController.createDrug(drugs);
        JOptionPane.showMessageDialog(this, "Remédio cadastrado com sucesso!");
    }

    private void registerService() {
        String name = nomeField.getText();
        String desc = descField.getText();
        String price = priceField.getText();
        String duration = durationField.getText();

        double pr = Double.parseDouble(price);
        ServiceEntity service = new ServiceEntity(name, desc, pr, duration);

        serviceController.createService(service);
        JOptionPane.showMessageDialog(this, "Serviço cadastrado com sucesso!");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(false));
    }
}
