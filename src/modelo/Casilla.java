package modelo;

public class Casilla {

	private int numero;
	private boolean bandera = false;
	private boolean oculta = true;
	private boolean mina = false;

	public boolean isMina() {
		return mina;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public boolean isOculta() {
		return oculta;
	}

	public void setOculta(boolean oculta) {
		this.oculta = oculta;
	}

}
