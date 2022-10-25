package models;

import static helpers.Constants.BLOQUES;
import static helpers.Constants.CABALLOB;
import static helpers.Constants.CABALLON;
import static helpers.Constants.PXBLOQUE;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Caballo extends Pieza{
	public Caballo(int color)
	{
		this.color = color;
		
		if (color == 0)
			this.setImage(CABALLOB);
		else
			this.setImage(CABALLON);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		for (int pos = 0;pos<BLOQUES;pos++) {
			if (pos<BLOQUES/2) {
				if (pos%2==0) {
					if (pos/2==0) {
						mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
					} else {
						mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
					}
				} else {
					if (pos/2==1) {
						mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
					} else {
						mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
					}
				}
			} else {
				if (pos%2==0) {
					if (pos/2==2) {
						mov = new Rectangle(this.getX()+PXBLOQUE*2, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
					} else {
						mov = new Rectangle(this.getX()-PXBLOQUE*2, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
					}
				} else {
					if (pos/2==3) {
						mov = new Rectangle(this.getX()-PXBLOQUE*2, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
					} else {
						mov = new Rectangle(this.getX()+PXBLOQUE*2, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
					}
				}
			}
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
		
		RepresentarMovimientos(tablero, movimientos);
	}
}
