package domain;

import java.awt.Color;

public interface Elemento extends Dibujable {

	public int getPosicionX();

	public int getPosicionY();

	public double getVelocidadX();

	public double getVelocidadY();

	public int getAncho();

	public int getLargo();

	public Color getColor();

	public void setPosicionX(int posicionX);

	public void setPosicionY(int posicionY);

	public void setVelocidadX(double velocidadX);

	public void setVelocidadY(double velocidadY);

	public void setPosicionInicial();

	public void moverse();

	public void rebotarEjeX();

	public void rebotarEjeY();

	public boolean hayColision(Elemento elemento);

}
