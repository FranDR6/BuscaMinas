package modelo;

import java.util.Random;

public class Tablero {

	private Casilla[][] casilla;

	public Tablero(int minas, int i, int j) {
		super();
		casilla = new Casilla[i][j];

		rellenarMatriz();

		colocarMinas(minas, i, j);

		colocarNumeros(i, j);

	}

	public void mostrarMatriz() {
		for (int x = 0; x < casilla.length; x++) {
			System.out.print(" ");
			for (int y = 0; y < casilla[x].length; y++) {
				System.out.print(casilla[x][y].getNumero());
				if (y != casilla[x].length - 1)
					System.out.print("\t");
			}
			System.out.println(" ");
		}
	}

	private void colocarNumeros(int i, int j) {
		for (int x = 0; x < casilla.length; x++) {
			for (int y = 0; y < casilla[x].length; y++) {
				if (casilla[x][y].isMina()) {
					sumarAlrededor(x, y);
				}
			}
		}
	}

	private void sumarAlrededor(int x, int y) {
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 1; j < y + 2; j++) {
				if (i < 0 || i > casilla.length - 1 || j < 0 || j > casilla[0].length - 1) {
					continue;
				}
				if (!casilla[i][j].isMina()) {
					casilla[i][j].setNumero(casilla[i][j].getNumero() + 1);
				}
			}
		}
	}

	private void rellenarMatriz() {
		for (int x = 0; x < casilla.length; x++) {
			for (int y = 0; y < casilla[x].length; y++) {
				casilla[x][y] = new Casilla();
			}
		}
	}

	private void colocarMinas(int minas, int i, int j) {
		int contador = 0;
		do {
			int x = obtenerNumeroAleatorio(0, i - 1);
			int y = obtenerNumeroAleatorio(0, j - 1);
			if (!casilla[x][y].isMina()) {
				casilla[x][y].setMina(true);
				casilla[x][y].setNumero(-1);
				contador++;
			}
		} while (contador != minas);
	}

	private int obtenerNumeroAleatorio(int min, int max) {
		Random aleatorio = new Random();
		return aleatorio.nextInt((max + 1) - min) + min;
	}

	public Casilla[][] getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla[][] casilla) {
		this.casilla = casilla;
	}

}
