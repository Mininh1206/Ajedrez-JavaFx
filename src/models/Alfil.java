package models;

import static helpers.Constants.ALFILB;
import static helpers.Constants.ALFILN;

public class Alfil extends Pieza {
	public Alfil(int color)
	{
		this.color = color;
		
		if (color == 0)
			this.setImage(ALFILB);
		else
			this.setImage(ALFILN);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		RepresentarMovimientos(tablero, GetMovimientosDiagonales(tablero));
	}
}
