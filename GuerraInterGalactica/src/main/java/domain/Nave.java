package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Nave extends ElementoBasico {

	private BufferedImage imagenNave;
	private BufferedImage imagenExplosion;

	public Nave(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);

		try {
			String ruta1 = Paths.get(Nave.class.getClassLoader().getResource("imagenes/nave.png").toURI()).toString();
			imagenNave = ImageIO.read(new File(ruta1));
			String ruta2 = Paths.get(Nave.class.getClassLoader().getResource("imagenes/explosion.png").toURI()).toString();
			imagenExplosion = ImageIO.read(new File(ruta2));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.drawImage(imagenNave, getPosicionX(), getPosicionY(), getAncho(), getLargo(), null);
	}

	public void explotar(Graphics grafico) {
		grafico.drawImage(imagenExplosion, getPosicionX(), getPosicionY(), getAncho(), getLargo(), null);
	}

}
