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
import model.lib.DateLabelFormatter;
import model.repositories.ServiceRepository;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.*;

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

	UserController userController = new UserController();
	DrugController drugsController = new DrugController();
	ServiceController serviceController = new ServiceController();
	AppointmentController appointmentController =  new AppointmentController();

	public Main() {
		JFrame frame = new JFrame("Home");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new GridLayout(3, 1));

		JButton loginButton = new JButton("Login");
		JButton cadastrarButton = new JButton("Cadastrar");

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Login clicked");
				frame.setVisible(false);
				Login();
			}
		});

		cadastrarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(frame, "Cadastrar clicked");s
				frame.setVisible(false);
				Register();
			}
		});

		// Adicionando os botões ao frame
		frame.add(new JLabel("Bem-vindo! Escolha uma opção:", SwingConstants.CENTER));
		frame.add(loginButton);
		frame.add(cadastrarButton);

		// Tornando o frame visível
		frame.setVisible(true);
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

		panel.add(new JLabel("especialidade:"));
		specialityField = new JTextField();
		panel.add(specialityField);

		panel.add(new JLabel("Senha:"));
		senhaField = new JPasswordField();
		panel.add(senhaField);

		panel.add(new JLabel("Confirmar Senha:"));
		confirmarSenhaField = new JPasswordField();
		panel.add(confirmarSenhaField);

		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});
		panel.add(cadastrarButton);

		add(panel);

		JButton backButton = new JButton("Voltar");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Main().setVisible(true);
			}
		});
		panel.add(backButton);
	}

	private void Login() {
		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel userLabel = new JLabel("Email:");
		userField = new JTextField(10);
		JLabel passwordLabel = new JLabel("Senha:");
		senhaField = new JPasswordField(10);
		panel.add(userLabel);		panel.add(userField);
		panel.add(passwordLabel);
		panel.add(senhaField);
		JButton loginButton = new JButton("Login");

		panel.add(loginButton);
		panel.add(new JLabel()); // Adiciona um componente vazio para manter o layout
		frame.getContentPane().add(panel);
		frame.setSize(527, 329);
		frame.setVisible(true);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				loginUser();
			}
		});
	}

	private void MenuPage(UserEntity user) {
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(3, 2));
		frame.setVisible(true);
		frame.getContentPane().add(panel);
		frame.setSize(527, 329);
		frame.setVisible(true);

		JButton registerDrugsBtn = new JButton("Cadastre remedios");
		JButton registerServiceBtn = new JButton("Cadastre serviços");
		JButton registerAppointmentBtn = new JButton("Cadastre Consultas");

		registerDrugsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				cadastrarRemedios();
			}
		});

		registerServiceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarServico();
			}
		});

		registerAppointmentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarConsulta(user);
			}
		});

		// Adicionando os botões ao frame
		panel.add(new JLabel("Bem-vindo! Escolha uma opção:", SwingConstants.CENTER));
		panel.add(registerDrugsBtn);
		panel.add(registerServiceBtn);
		panel.add(registerAppointmentBtn);
	}

	private void cadastrarRemedios() {
		JFrame frame = new JFrame("Cadastar remédio");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(5, 2));

		frame.setVisible(true);
		frame.getContentPane().add(panel);
		frame.setSize(600, 300);
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

		JButton registerDrugsBtn = new JButton("Cadastre remedios");
		panel.add(registerDrugsBtn);

		registerDrugsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerDrugs();
			}
		});
	}

	private void cadastrarServico() {
		JFrame frame = new JFrame("Cadastar Servico");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(4, 2));

		frame.setVisible(true);
		frame.getContentPane().add(panel);
		frame.setSize(600, 300);
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

		JButton registerServiceBtn = new JButton("Cadastre o serviço");
		panel.add(registerServiceBtn);

		registerServiceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerService();
			}
		});
	}

	private void cadastrarConsulta(UserEntity user) {
		JFrame frame = new JFrame("Cadastar Consulta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(8, 2));

		frame.setVisible(true);
		frame.getContentPane().add(panel);
		frame.setSize(600, 300);
		frame.setVisible(true);

		JLabel nomeLabel = new JLabel("Titulo:");
		nomeField = new JTextField(10);

		JLabel dateAppointmentLabel = new JLabel("Digite a data da consulta:");
		dateAppointmentField = new JTextField(10);

		JLabel hourAppointmentLabel = new JLabel("Digite a hora da consulta:");
		hourAppointmentField = new JTextField(10);

		JLabel clientNameLabel = new JLabel("Digite o nome do cliente:");
		nameClientField = new JTextField(10);

//		SqlDateModel model = new SqlDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();

//		dateAppointmentField = selectedDate;


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


		serviceComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceEntity selectedService = (ServiceEntity) serviceComboBox.getSelectedItem();
				if (selectedService != null) {
					System.out.println("Serviço selecionado: " + selectedService.getName()); // Exemplo de exibição do nome do serviço

					services = selectedService;
				}
			}
		});

		JButton registerAppointmentBtn = new JButton("Cadastre a consulta");
		panel.add(registerAppointmentBtn);

		registerAppointmentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				registerAppointment(user,services);
			}
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
		String password = senhaField.getText();
		LoginDto login = new LoginDto(email, password);
		var user = userController.login(login);
		MenuPage(user);
	}

	private void registerDrugs() {
		String name = nomeField.getText();
		String desc = descField.getText();
		String price = priceField.getText();

		double pr = Double.parseDouble(price);
		DrugsEntity drugs = new DrugsEntity(
				name,
				desc,
				pr
		);

		drugsController.createDrug(drugs);
		JOptionPane.showMessageDialog(this, "Remédio cadastrado com sucesso!");
	}

	private void registerService() {
		String name = nomeField.getText();
		String desc = descField.getText();
		String price = priceField.getText();
		String duration = durationField.getText();

		double pr = Double.parseDouble(price);
		ServiceEntity service = new ServiceEntity(
				name,
				desc,
				pr,
				duration
		);

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

		appointmentController.createDrug(app);
		JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().setVisible(true);
			}
		});
	}
}
