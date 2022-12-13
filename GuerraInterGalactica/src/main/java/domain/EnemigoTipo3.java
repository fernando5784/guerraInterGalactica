package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class EnemigoTipo3 extends ElementoBasico {

	private BufferedImage imagen;

	public EnemigoTipo3(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
			Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);

		try {
			String ruta = Paths.get(EnemigoTipo3.class.getClassLoader().getResource("imagenes/enemigo3.png").toURI())
					.toString();
			imagen = ImageIO.read(new File(ruta));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.drawImage(imagen, getPosicionX(), getPosicionY(), getAncho(), getLargo(), null);
	}

}
