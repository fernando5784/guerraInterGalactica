package domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Vidas implements Dibujable {

	private int posicionX;
	private int posicionY;
	private Font fuente;
	private Color color;
	private int cantidadVidas;
	private int vidasRestantes;

	public Vidas(int posicionX, int posicionY, Font fuente, Color color, int cantidadVidas) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.fuente = fuente;
		this.color = color;
		this.cantidadVidas = cantidadVidas;
		this.vidasRestantes = cantidadVidas;
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.setColor(color);
		grafico.setFont(fuente);
		grafico.drawString("Vidas: " + String.valueOf(vidasRestantes), posicionX, posicionY);
	}

	public int getVidasRestantes() {
		return vidasRestantes;
	}

	public void perderUnaVida() {
		vidasRestantes--;
	}

	public void reiniciar() {
		vidasRestantes = cantidadVidas;
	}
}
