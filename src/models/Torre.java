package models;

import static helpers.Constants.BLOQUES;
import static helpers.Constants.PXBLOQUE;
import static helpers.Constants.TORREB;
import static helpers.Constants.TORREN;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

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
		super.ComprobarMovimiento(tablero);
		
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX(), this.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX(), this.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX()+PXBLOQUE*pos, this.getY(), PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX()-PXBLOQUE*pos, this.getY(), PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		RepresentarMovimientos(tablero, movimientos);
	}
}
