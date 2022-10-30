package models;

import static helpers.Constants.*;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Peon extends Pieza {
	public Peon(int color)
	{
		this.color = color;
		
		if (color == 0)
			this.setImage(PEONB);
		else
			this.setImage(PEONN);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		if (color==1)
		{
			mov = new Rectangle(this.getX(), this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov))
				movimientos.add(mov);
			
			if (this.GetMovimientos()==0 && !ComprobarObstruido(tablero, mov)) {
				mov = new Rectangle(this.getX(), this.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				
				if (!ComprobarObstruido(tablero, mov))
					movimientos.add(mov);
			}
			
			mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			
			if (ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			
			mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			
			if (ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
		else
		{
			mov = new Rectangle(this.getX(), this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov))
				movimientos.add(mov);
			if (this.GetMovimientos()==0 && !ComprobarObstruido(tablero, mov)) {
				mov = new Rectangle(this.getX(), this.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				if (!ComprobarObstruido(tablero, mov))
					movimientos.add(mov);
			}
			mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
		
		RepresentarMovimientos(tablero, movimientos);
	}
	
	@Override
	public void MoverPieza(Tablero tablero, Rectangle movimiento) {
		super.MoverPieza(tablero, movimiento);
		
		//TODO Vista de cambio de corona
	}
}
