package models;

import static helpers.Constants.TORREB;
import static helpers.Constants.TORREN;

public class Torre extends Pieza {
	public Torre(int color)
	{
		this.color = color;
		
		if (color == 0)
			this.setImage(TORREB);
		else
			this.setImage(TORREN);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		RepresentarMovimientos(tablero, GetMovimientosRectos(tablero));
	}
}
