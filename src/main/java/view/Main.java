package view;

import controller.UserController;
import model.entities.UserEntity;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.*;

public class Main extends JFrame {

	private JTextField nomeField, emailField, celularField, idadeField, cpfField;
	private JPasswordField senhaField, confirmarSenhaField;

	UserController userController = new UserController();

	public Main() {
		Register();
	}

	private void Register () {
		setTitle("Cadastro de Usuário");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(8, 2));

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
	}

	private void cadastrarUsuario() {
		String nome = nomeField.getText();
		String email = emailField.getText();
		String celular = celularField.getText();
		int idade = Integer.parseInt(idadeField.getText());
		String cpf = cpfField.getText();
		String senha = new String(senhaField.getPassword());
		String confirmarSenha = new String(confirmarSenhaField.getPassword());

		if (!senha.equals(confirmarSenha)) {
			JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

//		UserEntity usuario = new UserEntity(nome, email, senha, celular, idade, cpf, false, LocalTime.now(), "teste");
		UserEntity newUser =
				new UserEntity(
						nome,
						email,
						senha,
						celular,
						idade,
						cpf,
						false,
						LocalTime.now(),
						"teste");

		userController.createUser(newUser);

		JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
		// Aqui você pode adicionar o código para salvar o usuário em um banco de dados ou outro armazenamento
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
