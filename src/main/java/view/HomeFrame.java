package view;

import javax.swing.*;
import java.awt.*;


public class HomeFrame extends JFrame {
    public HomeFrame() {
        super("Consultório - Agenda e Pacientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 640);
        setLocationRelativeTo(null);


        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Consultas pendentes", new AppointmentsPanel(true));
        tabs.addTab("Realizadas / Canceladas", new AppointmentsPanel(false));
        tabs.addTab("Pacientes", new PatientsPanel());


        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }
}