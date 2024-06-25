package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel userLabel = new JLabel("Usuário:");
		JTextField userField = new JTextField(10);
		JLabel passwordLabel = new JLabel("Senha:");
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(userLabel);
		panel.add(userField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(loginButton);
		panel.add(new JLabel()); // Adiciona um componente vazio para manter o layout
		frame.getContentPane().add(panel);
		frame.setSize(527, 329);
		frame.setVisible(true);
	}

}
