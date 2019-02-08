package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import modelo.Tablero;
import vista.Ventana;

public class Principal extends Ventana {

	private JButton[][] botonera;
	private Tablero tablero;

	public static void main(String[] args) {
		Principal juego = new Principal();

	}

	public Principal() {
		super();
		niveles();
	}

	private void niveles() {

		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(5, 8, 8);
				crearBotonera();
			}
		});

		btnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(40, 16, 16);
				crearBotonera();
			}
		});

		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero = new Tablero(99, 16, 30);
				crearBotonera();
			}
		});
	}

	protected void crearBotonera() {
		panelBotonera.removeAll();
		botonera = new JButton[tablero.getCasilla().length][tablero.getCasilla()[0].length];
		panelBotonera.setLayout(new GridLayout(tablero.getCasilla().length, tablero.getCasilla()[0].length));

		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera[i].length; j++) {
				botonera[i][j] = new JButton();
				botonera[i][j].setName(i + " " + j);
				botonera[i][j].setBackground(Color.DARK_GRAY);
				botonera[i][j].setFont(new Font("Segoe UI", Font.BOLD, 30));
				botonera[i][j].setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
				botonera[i][j].addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						JButton boton = (JButton) e.getSource();
						String[] posicion = boton.getName().split(" ");
						int i = Integer.valueOf(posicion[0]);
						int j = Integer.valueOf(posicion[1]);
						// CLICK DERECHO
						if (e.getButton() == 3 && tablero.getCasilla()[i][j].isOculta()) {
							colocarBandera(i, j);
						}
						// CLICK IZQUIERDO
						if (e.getButton() == 1) {
							if (!tablero.getCasilla()[i][j].isBandera()) {
								if (tablero.getCasilla()[i][j].getNumero() > 0
										&& !tablero.getCasilla()[i][j].isOculta()) {
									mostrarAdyacentes(i, j);
								}
								if (tablero.getCasilla()[i][j].getNumero() > 0
										&& tablero.getCasilla()[i][j].isOculta()) {
									mostrarNumero(i, j);
								}
								if (tablero.getCasilla()[i][j].isMina()) {
									gameOver();
								}
								if (tablero.getCasilla()[i][j].getNumero() == 0) {
									recursividad(i, j);
									actualizarPantalla();
								}
								comprobarVictoria();
							}
						}
					}

					public void mousePressed(MouseEvent e) {
					}

					public void mouseExited(MouseEvent e) {
					}

					public void mouseEntered(MouseEvent e) {
					}

					public void mouseClicked(MouseEvent e) {
					}
				});
				panelBotonera.add(botonera[i][j]);
			}
		}
		actualizarPantalla();
	}

	protected void recursividad(int i, int j) {
		if (tablero.getCasilla()[i][j].getNumero() == 0 && tablero.getCasilla()[i][j].isOculta()) {
			tablero.getCasilla()[i][j].setOculta(false);
			botonera[i][j].setVisible(false);
			for (int x = i - 1; x <= i + 1; x++) {
				for (int y = j - 1; y <= j + 1; y++) {
					if (x < 0 || x > tablero.getCasilla().length - 1 || y < 0
							|| y > tablero.getCasilla()[0].length - 1) {
						continue;
					}
					if (tablero.getCasilla()[x][y].getNumero() == 0) {
						recursividad(x, y);
					}
					if (tablero.getCasilla()[x][y].getNumero() > 0) {
						mostrarNumero(x, y);
					}
				}
			}
		}
	}

	private void mostrarAdyacentes(int i, int j) {
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x < 0 || x > tablero.getCasilla().length - 1 || y < 0 || y > tablero.getCasilla()[0].length - 1) {
					continue;
				}
				if (!tablero.getCasilla()[x][y].isBandera() && tablero.getCasilla()[x][y].isOculta()) {
					if (tablero.getCasilla()[x][y].isMina()) {
						gameOver();
						break;
					}
					if (tablero.getCasilla()[x][y].getNumero() > 0) {
						mostrarNumero(x, y);
					}
					if (tablero.getCasilla()[x][y].getNumero() == 0) {
						recursividad(x, y);
					}
				}
			}
		}
	}

	private void comprobarVictoria() {
		boolean win = true;
		for (int x = 0; x < botonera.length; x++) {
			for (int y = 0; y < botonera.length; y++) {
				if (tablero.getCasilla()[x][y].isOculta() && !tablero.getCasilla()[x][y].isMina()) {
					win = false;
				}
			}
		}
		if (win) {
			win();
		}
	}

	private void win() {
		JOptionPane.showMessageDialog(null, "WIN");
		panelBotonera.removeAll();
		actualizarPantalla();
	}

	private void colocarBandera(int i, int j) {
		if (tablero.getCasilla()[i][j].isBandera()) {
			botonera[i][j].setText("");
			tablero.getCasilla()[i][j].setBandera(false);
		} else {
			botonera[i][j].setForeground(Color.YELLOW);
			botonera[i][j].setText("?");
			tablero.getCasilla()[i][j].setBandera(true);
		}
	}

	private void mostrarNumero(int i, int j) {
		botonera[i][j].setForeground(Color.GREEN);
		botonera[i][j].setText(String.valueOf(tablero.getCasilla()[i][j].getNumero()));
		tablero.getCasilla()[i][j].setOculta(false);
	}

	private void gameOver() {
		mostrarMinas();
		JOptionPane.showMessageDialog(null, "GAME OVER");
		panelBotonera.removeAll();
		actualizarPantalla();
	}

	private void mostrarMinas() {
		for (int x = 0; x < botonera.length; x++) {
			for (int y = 0; y < botonera[x].length; y++) {
				if (tablero.getCasilla()[x][y].isMina()) {
					botonera[x][y].setForeground(Color.RED);
					botonera[x][y].setText("!");
				}
			}
		}
	}

	public void actualizarPantalla() {
		JPanel temp = (JPanel) this.getContentPane();
		SwingUtilities.updateComponentTreeUI(temp);
//		temp.validateTree();
	}

}
