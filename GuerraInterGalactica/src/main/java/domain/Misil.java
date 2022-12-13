package domain;

import java.awt.Color;
import java.awt.Graphics;

public class Misil extends ElementoBasico {

	public Misil(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.setColor(getColor());
		grafico.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
	}

}
