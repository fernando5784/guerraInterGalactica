package domain;

import java.awt.Color;

public abstract class ElementoBasico implements Elemento {

	private double posicionX;
	private double posicionY;
	private double velocidadX;
	private double velocidadY;
	private int ancho;
	private int largo;
	private Color color;

	private int posicionX_Inicial;
	private int posicionY_Inicial;

	public ElementoBasico(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
			Color color) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.ancho = ancho;
		this.largo = largo;
		this.color = color;

		this.posicionX_Inicial = posicionX;
		this.posicionY_Inicial = posicionY;
	}

	@Override
	public int getPosicionX() {
		return (int) this.posicionX;
	}

	@Override
	public int getPosicionY() {
		return (int) this.posicionY;
	}

	@Override
	public double getVelocidadX() {
		return this.velocidadX;
	}

	@Override
	public double getVelocidadY() {
		return this.velocidadY;
	}

	@Override
	public int getAncho() {
		return this.ancho;
	}

	@Override
	public int getLargo() {
		return this.largo;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	@Override
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	@Override
	public void setVelocidadX(double velocidadX) {
		this.velocidadX = velocidadX;
	}

	@Override
	public void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
	}

	@Override
	public void setPosicionInicial() {
		this.posicionX = this.posicionX_Inicial;
		this.posicionY = this.posicionY_Inicial;
	}

	@Override
	public void moverse() {
		posicionX += velocidadX;
		posicionY += velocidadY;
	}

	@Override
	public void rebotarEjeX() {
		velocidadX = -velocidadX;
	}

	@Override
	public void rebotarEjeY() {
		velocidadY = -velocidadY;
	}

	@Override
	public boolean hayColision(Elemento elemento) {
		if (Utilidades.hayColision((int) posicionX, (int) posicionY, ancho, largo, elemento.getPosicionX(),
				elemento.getPosicionY(), elemento.getAncho(), elemento.getLargo())) {
			return true;
		} else {
			return false;
		}
	}

}
