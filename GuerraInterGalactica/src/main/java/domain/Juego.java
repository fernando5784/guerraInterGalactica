package domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Juego extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;

	private int anchoPanel;
	private int largoPanel;
	private int esperaEntreActualizaciones;
	private int enemigosPorLinea;
	private int filasDeEnemigos;

	private Nave nave;
	private Vidas vidas;
	private Puntaje puntaje;
	private Sonidos sonidos;
	private List<ElementoBasico> misiles;
	private List<ElementoBasico> enemigos;

	private int estadoActualJuego;
	private final static int MostrandoPortada = 1;
	private final static int Jugando = 2;
	private final static int MostrandoPerdedor = 3;
	private final static int MostrandoGanador = 4;
	private final static int Terminado = 5;

	private PantallaImagen pantallaPortada;
	private PantallaImagen pantallaFondo;
	private PantallaImagen pantallaEsperar;
	private PantallaImagen pantallaGanador;
	private PantallaImagen pantallaPerdedor;
	private PantallaImagen pantallaDespedida;

	public Juego(int anchoVentana, int largoVentana, int esperaEntreActualizaciones, int enemigosPorLinea,
			int filasDeEnemigos, int cantidadVidas) {

		estadoActualJuego = MostrandoPortada;

		this.anchoPanel = anchoVentana;
		this.largoPanel = largoVentana;
		this.esperaEntreActualizaciones = esperaEntreActualizaciones;
		this.enemigosPorLinea = enemigosPorLinea;
		this.filasDeEnemigos = filasDeEnemigos;

		Font fuente = new Font("Arial", 0, 20);
		vidas = new Vidas(20, 60, fuente, Color.red, cantidadVidas);
		puntaje = new Puntaje(20, 30, fuente, Color.red);
		nave = new Nave(anchoPanel / 2 - 20, largoPanel - 80, 0, 0, 40, 60, null);
		misiles = new ArrayList<>();
		enemigos = new ArrayList<>();
		agregarEnemigos(enemigosPorLinea, filasDeEnemigos);

		pantallaPortada = new PantallaImagen(anchoPanel, largoPanel, "imagenes/portada.png", null, 150, 560);
		pantallaFondo = new PantallaImagen(anchoPanel, largoPanel, "imagenes/fondoEspacial.png", null, 0, 0);
		pantallaEsperar = new PantallaImagen(anchoPanel, largoPanel, "imagenes/esperar.png", null, 180, 540);
		pantallaGanador = new PantallaImagen(anchoPanel, largoPanel, "imagenes/ganador.png", null, 160, 550);
		pantallaPerdedor = new PantallaImagen(anchoPanel, largoPanel, "imagenes/perdedor.png", null, 60, 540);
		pantallaDespedida = new PantallaImagen(anchoPanel, largoPanel, "imagenes/despedida.png", null, 0, 0);

		cargarSonidos();
		sonidos.repetirSonido("background");
	}

	@Override
	public void keyTyped(KeyEvent evento) {
	}

	@Override
	public void keyPressed(KeyEvent evento) {

		if (estadoActualJuego == MostrandoPortada) {
			estadoActualJuego = Jugando;
		}

		if (estadoActualJuego == Jugando) {
			if (evento.getKeyCode() == 39) { // Flecha hacia la derecha
				nave.setVelocidadX(3);
			}
			if (evento.getKeyCode() == 37) { // Flecha hacia la izquierda
				nave.setVelocidadX(-3);
			}
			if (evento.getKeyCode() == 38) { // Flecha hacia arriba
				nave.setVelocidadY(-3);
			}
			if (evento.getKeyCode() == 40) { // Flecha hacia abajo
				nave.setVelocidadY(3);
			}
			if (evento.getKeyCode() == 68) { // Letra D para disparar
				misiles.add(new Misil(nave.getPosicionX()+ 12 , nave.getPosicionY(), 0, -5, 15, 15, Color.white));
				sonidos.tocarSonido("toc");
			}
		}

		if (estadoActualJuego == MostrandoGanador || estadoActualJuego == MostrandoPerdedor) {
			if (evento.getKeyCode() == 67) { // C para continuar jugando
				reiniciarJuego();
				estadoActualJuego = Jugando;

			}
			if (evento.getKeyCode() == 83) { // S para salir
				estadoActualJuego = Terminado;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent evento) {
		if (evento.getKeyCode() == 37 || evento.getKeyCode() == 38 || evento.getKeyCode() == 39
				|| evento.getKeyCode() == 40) {
			nave.setVelocidadX(0);
			nave.setVelocidadY(0);
		}
	}

	private void reiniciarJuego() {
		misiles.clear();
		enemigos.clear();
		vidas.reiniciar();
		puntaje.reiniciar();
		nave.setPosicionInicial();
		agregarEnemigos(enemigosPorLinea, filasDeEnemigos);
	}

	private void agregarEnemigos(int enemigosPorLinea, int filasDeEnemigos) {
		for (int y = 0; y < filasDeEnemigos; y++) {
			for (int x = 0; x < enemigosPorLinea; x++) {
				int posicionX = 60 * x + 120;
				int posicionY = 40 * y + 70;
				if (x % 2 == 0) {
					enemigos.add(new EnemigoTipo1(posicionX, posicionY, 0.5, 0.15, 23, 30, null));
				} else if (x % 3 == 0) {
					enemigos.add(new EnemigoTipo2(posicionX, posicionY, 0.5, 0.30, 20, 30, null));
				} else {
					enemigos.add(new EnemigoTipo3(posicionX, posicionY, 0.5, 0.20, 25, 30, null));
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(anchoPanel, largoPanel);
	}

	@Override
	protected void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);

		if (estadoActualJuego == MostrandoPortada) {
			pantallaPortada.setMensaje("Tecla (D) para disparar. Apreta cualquier tecla para comenzar");
			pantallaPortada.dibujarse(grafico);
		}

		if (estadoActualJuego == MostrandoGanador) {
			pantallaGanador.setMensaje("Campeon!! Presiona (C) para continuar o (S) para salir");
			pantallaGanador.dibujarse(grafico);
		}

		if (estadoActualJuego == MostrandoPerdedor) {
			String mensaje = "Derribaste " + puntaje.getCantidadPuntos() + " naves enemigas de "
					+ (enemigos.size() + puntaje.getCantidadPuntos()) + ". Toca (C) para continuar o (S) para salir";
			if (puntaje.getCantidadPuntos() == 0) {
				mensaje = "Mal, no derribaste ningun enemigo!! Toca (C) para continuar o (S) para salir";
			}
			pantallaPerdedor.setMensaje(mensaje);
			pantallaPerdedor.dibujarse(grafico);
		}

		if (estadoActualJuego == Jugando) {
			pantallaFondo.dibujarse(grafico);
			puntaje.dibujarse(grafico);
			vidas.dibujarse(grafico);
			nave.dibujarse(grafico);
			dibujarMisiles(grafico);
			dibujarEnemigos(grafico);
		}
	}

	private void dibujarMisiles(Graphics grafico) {
		for (int i = 0; i < misiles.size(); i++) {
			misiles.get(i).dibujarse(grafico);
		}
	}

	private void dibujarEnemigos(Graphics grafico) {
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).dibujarse(grafico);
		}
	}

	@Override
	public void run() {
		while (true) {
			if (estadoActualJuego == Jugando) {
				actualizarJuego();
			}
			if (estadoActualJuego == Terminado) {
				pantallaDespedida.dibujarse(getGraphics());
				break;
			}
			repaint();
			esperar(esperaEntreActualizaciones);
		}
	}

	private void actualizarJuego() {
		verificarEstadoAmbiente();
		darEnemigosDireccionHorizontalAleatoria();
		moverNave();
		moverEnemigos();
		moverMisiles();
		verificarFinJuego();
	}

	private void moverNave() {
		nave.moverse();
	}

	private void moverEnemigos() {
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).moverse();
		}
	}

	private void moverMisiles() {
		for (int i = 0; i < misiles.size(); i++) {
			misiles.get(i).moverse();
		}
	}

	private void verificarEstadoAmbiente() {
		verificarToqueNaveConBordes();
		verificarToqueEnemigosConBordes();
		verificarColisionNaveConEnemigos();
		verificarColisionMisilConEnemigos();
	}

	private void verificarToqueNaveConBordes() {
		if (nave.getPosicionX() <= 0) {
			nave.setPosicionX(0);
		}
		if (nave.getPosicionX() + nave.getAncho() >= anchoPanel) {
			nave.setPosicionX(anchoPanel - nave.getAncho());
		}
		if (nave.getPosicionY() <= 0) {
			nave.setPosicionY(0);
		}
		if (nave.getPosicionY() + nave.getLargo() >= largoPanel) {
			nave.setPosicionY(largoPanel - nave.getLargo());
		}
	}

	private void verificarToqueEnemigosConBordes() {
		for (int i = 0; i < enemigos.size(); i++) {
			if (enemigos.get(i).getPosicionX() <= 0
					|| enemigos.get(i).getPosicionX() + enemigos.get(i).getAncho() >= anchoPanel) {
				enemigos.get(i).rebotarEjeX();
			}
			if (enemigos.get(i).getPosicionY() + enemigos.get(i).getLargo() >= largoPanel) {
				enemigos.get(i).setPosicionY(0);
				enemigos.get(i).setVelocidadY(enemigos.get(i).getVelocidadY() + 0.2);
				enemigos.get(i).setVelocidadX(enemigos.get(i).getVelocidadX() + 0.5);
			}
		}
	}

	private void verificarColisionNaveConEnemigos() {
		for (int i = 0; i < enemigos.size(); i++) {
			if (enemigos.get(i).hayColision(nave)) {
				nave.explotar(getGraphics());
				sonidos.tocarSonido("muerte");
				esperar(1000);
				procesarPerdidaDeVida();
			}
		}
	}

	private void verificarColisionMisilConEnemigos() {
		int cantidadMisiles = misiles.size();
		int cantidadEnemigos = enemigos.size();
		for (int i = 0; i < cantidadMisiles; i++) {
			for (int j = 0; j < cantidadEnemigos && i < cantidadMisiles; j++) {
				if (misiles.get(i).hayColision(enemigos.get(j))) {
					misiles.remove(i);
					enemigos.remove(j);
					puntaje.sumarPunto();
					sonidos.tocarSonido("tic");
					cantidadMisiles--;
					cantidadEnemigos--;
				}
			}
		}
	}

	int contador = 0;

	private void darEnemigosDireccionHorizontalAleatoria() {
		contador++;
		if (contador == 100) {
			for (int i = 0; i < enemigos.size(); i++) {
				int nroAleatorio = new Random().nextInt(2);
				if (nroAleatorio == 0) {
					enemigos.get(i).rebotarEjeX();
				}
			}
			contador = 0;
		}
	}

	private void procesarPerdidaDeVida() {
		vidas.perderUnaVida();
		if (vidas.getVidasRestantes() > 0) {
			String mensaje = "Tu nave estallo!! Te quedan " + vidas.getVidasRestantes() + " vidas disponibles!!";
			if (vidas.getVidasRestantes() == 1) {
				mensaje = "TE QUEDA LA ULTIMA VIDA!!! APROVECHALA!!";
			}
			pantallaEsperar.setMensaje(mensaje);
			pantallaEsperar.dibujarse(getGraphics());
			nave.setPosicionInicial();
			for (int i = 0; i < enemigos.size(); i++) {
				enemigos.get(i).setPosicionInicial();
			}
			esperar(3000);
		}
	}

	private void verificarFinJuego() {
		if (vidas.getVidasRestantes() <= 0) {
			estadoActualJuego = MostrandoPerdedor;
		}
		if (enemigos.size() == 0) {
			estadoActualJuego = MostrandoGanador;
		}
	}

	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void cargarSonidos() {
		try {
			sonidos = new Sonidos();
			sonidos.agregarSonido("toc", "sonidos/toc.wav");
			sonidos.agregarSonido("tic", "sonidos/tic.wav");
			sonidos.agregarSonido("muerte", "sonidos/muerte.wav");
			sonidos.agregarSonido("background", "sonidos/background.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

}
