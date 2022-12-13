package domain;

public class Utilidades {

	public static boolean hayColision(int E1posicionX, int E1posicionY, int E1ancho, int E1largo, int E2posicionX,
			int E2posicionY, int E2ancho, int E2largo) {

		int aX = E1posicionX;
		int bX = E1posicionX + E1ancho;
		int cX = E2posicionX;
		int dX = E2posicionX + E2ancho;

		int aY = E1posicionY;
		int bY = E1posicionY + E1largo;
		int cY = E2posicionY;
		int dY = E2posicionY + E2largo;

		if (haySolapamientoDeRango(aX, bX, cX, dX) && haySolapamientoDeRango(aY, bY, cY, dY)) {
			return true;
		}
		return false;
	}

	private static boolean haySolapamientoDeRango(int a, int b, int c, int d) {
		if (a < d && b > c) {
			return true;
		}
		return false;
	}

}
