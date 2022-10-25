package models;

import static helpers.Constants.*;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Reina extends Pieza {
	public Reina(int color)
	{
		this.color = color;
				
		if (color == 0)
			this.setImage(REINAB);
		else
			this.setImage(REINAN);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		movimientos.addAll(GetMovimientosDiagonales(tablero));
		movimientos.addAll(GetMovimientosRectos(tablero));
		
		RepresentarMovimientos(tablero, movimientos);
	}
}
