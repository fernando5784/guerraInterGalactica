package domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Puntaje implements Dibujable {

	private int posicionX;
	private int posicionY;
	private Font fuente;
	private Color color;
	private int cantidadPuntos;

	public Puntaje(int posicionX, int posicionY, Font fuente, Color color) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.fuente = fuente;
		this.color = color;
		this.cantidadPuntos = 0;
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.setColor(color);
		grafico.setFont(fuente);
		grafico.drawString("Puntos: " + String.valueOf(cantidadPuntos), posicionX, posicionY);
	}

	public int getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void sumarPunto() {
		cantidadPuntos++;
	}

	public void reiniciar() {
		cantidadPuntos = 0;
	}

}
