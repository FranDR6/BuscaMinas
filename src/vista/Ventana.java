package vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class Ventana extends JFrame {

	protected JPanel ventana;
	protected JButton btnFacil;
	protected JButton btnMedio;
	protected JButton btnDificil;
	protected JPanel panelBotonera;

	public Ventana() {
		setTitle("Busca Minas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(600, 600, 600, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana = new JPanel();
		ventana.setBackground(Color.DARK_GRAY);
		ventana.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ventana);

		panelBotonera = new JPanel();
		panelBotonera.setBackground(Color.BLACK);

		btnFacil = new JButton("*");
		btnFacil.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GREEN));
		btnFacil.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnFacil.setBackground(Color.WHITE);

		btnMedio = new JButton("**");
		btnMedio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		btnMedio.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnMedio.setBackground(Color.WHITE);

		btnDificil = new JButton("***");
		btnDificil.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		btnDificil.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDificil.setBackground(Color.WHITE);
		GroupLayout gl_ventana = new GroupLayout(ventana);
		gl_ventana.setHorizontalGroup(gl_ventana.createParallelGroup(Alignment.LEADING).addGroup(gl_ventana
				.createSequentialGroup().addGap(10)
				.addGroup(gl_ventana.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ventana.createSequentialGroup()
								.addComponent(panelBotonera, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE).addGap(10))
						.addGroup(gl_ventana.createSequentialGroup()
								.addComponent(btnFacil, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnMedio, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnDificil, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(362, Short.MAX_VALUE)))));
		gl_ventana.setVerticalGroup(gl_ventana.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ventana.createSequentialGroup().addContainerGap()
						.addGroup(gl_ventana.createParallelGroup(Alignment.LEADING)
								.addComponent(btnFacil, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMedio, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDificil, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(12).addComponent(panelBotonera, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
						.addGap(10)));
		panelBotonera.setLayout(new GridLayout(1, 0, 0, 0));
		ventana.setLayout(gl_ventana);
	}
}
