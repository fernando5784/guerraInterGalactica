package domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class PantallaImagen implements Dibujable {

	private BufferedImage imagen;
	private int ancho;
	private int largo;
	private String mensaje;
	private int posicionX_mensaje;
	private int posicionY_mensaje;

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public PantallaImagen(int ancho, int largo, String recurso, String mensaje, int posisionX_mensaje,
			int posicionY_mensaje) {

		this.ancho = ancho;
		this.largo = largo;
		this.mensaje = mensaje;
		this.posicionX_mensaje = posisionX_mensaje;
		this.posicionY_mensaje = posicionY_mensaje;

		try {
			String ruta = Paths.get(PantallaImagen.class.getClassLoader().getResource(recurso).toURI()).toString();
			imagen = ImageIO.read(new File(ruta));
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

	@Override
	public void dibujarse(Graphics grafico) {
		grafico.drawImage(imagen, 0, 0, ancho, largo, null);
		if (mensaje != null) {
			grafico.setColor(Color.red);
			grafico.setFont(new Font("Arial", 0, 20));
			grafico.drawString(mensaje, posicionX_mensaje, posicionY_mensaje);
		}
	}

}
