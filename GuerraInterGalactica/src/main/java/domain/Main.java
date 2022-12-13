package domain;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		int anchoVentana = 800;
		int largoVentana = 600;
		int esperaEntreActualizaciones = 5; // mili segundos
		int enemigosPorLinea = 10;
		int filasDeEnemigos = 6;
		int cantidadVidas = 3;

		// Activar aceleracion de graficos en 2 dimensiones
		System.setProperty("sun.java2d.opengl", "true");

		// Se crea la ventana que es un objeto de tipo JFrame
		JFrame ventana = new JFrame("Guerra Intergalactica");

		// Cierra la app cuando se hace clic en la X
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Abre la ventana en el centro de la pantalla
		ventana.setLocation(230,40);

		// Activa visibilidad de la ventana
		ventana.setVisible(true);

		// Se crea un panel(juego) que va a ir dentro de la ventana.
		// La clase Panel(juego) hereda de JPanel e implmenta Runnable
		Juego juego = new Juego(anchoVentana, largoVentana, esperaEntreActualizaciones, enemigosPorLinea, filasDeEnemigos,
				cantidadVidas);

		// Agregar el panel a la ventana
		ventana.add(juego);

		// Ajustar el tamaño de la ventana para que encaje con los componentes (en este caso el panel)
		ventana.pack();

		// Permitir que la ventana registre eventos del teclado. Juego(Panel) debe implementar KeyListener
		ventana.addKeyListener(juego);

		// Crear un objeto de tipo Thread pasandole como parametro al constuctor un objeto que implemente Runnable
		Thread subproceso = new Thread(juego);
		subproceso.start();
	}

}
