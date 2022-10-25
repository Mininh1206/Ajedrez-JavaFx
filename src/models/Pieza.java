package models;

import static helpers.Constants.PXBLOQUE;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Pieza extends ImageView {
	private int numeroMovimientos = 0;
	protected int color;
	
	public Pieza()
	{
		
	}
	
	public void ComprobarMovimiento(Tablero tablero)
	{
	}
	
	public boolean ComprobarComerPieza(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			try {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
				{
					if (this.color!=temp.color)
					{
						return true;
					}
				}
			}
			catch (Exception e) {
			}
		}
		
		return false;
	}
	
	public boolean ComprobarObstruido(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			try {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
				{
					return true;
				}
			}
			catch (Exception e) {
			}
		}
		
		return false;
	}
	
	public void MoverPieza(Tablero tablero, Rectangle movimiento) {
		if (movimiento.getId().equalsIgnoreCase("com"))
		{
			for (int pos = 0; pos<tablero.getChildren().size();pos++) {
				try {
					Pieza temp = (Pieza)tablero.getChildren().get(pos);
					
					if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					{
						tablero.getChildren().remove(pos);
					}
				}
				catch (Exception e) {
				}
			}
		}

		this.setX(movimiento.getX());
		this.setY(movimiento.getY());
		
		tablero.QuitarMovimientos();
		
		numeroMovimientos++;
	}
	
	public int GetMovimientos() {
		return numeroMovimientos;
	}
}
