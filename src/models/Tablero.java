package models;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static helpers.Constants.*;

public class Tablero extends Pane {
	private ArrayList<Pieza> piezasBlancas;
	private ArrayList<Pieza> piezasNegras;
	
	private Pieza piezaSelec;
	
	private int turno = 0;
	
	public Tablero()
	{
		piezasBlancas = new ArrayList<>();
		piezasNegras = new ArrayList<>();
		
		GenerarTablero();
		GenerarPiezas();
	}
	
	public void CambiarTurno() {
		if (turno == 0)
			turno = 1;
		else
			turno = 0;
	}

	private void GenerarPiezas() {
		piezasNegras.addAll(Arrays.asList(new Torre(1),
											new Caballo(1),
											new Alfil(1),
											new Reina(1),
											new Rey(1),
											new Alfil(1),
											new Caballo(1),
											new Torre(1)));
		
		for (int p = 0; p < 8; p++)
		{
			piezasNegras.add(new Peon(1));
		}

		for (int pos = 0;pos<piezasNegras.size();pos++)
		{
			Pieza pieza = piezasNegras.get(pos);
			
			pieza.setX(pos%8*PXBLOQUE);
			pieza.setY(pos/8*PXBLOQUE);
			
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				
				QuitarMovimientos();
				
				if (turno == piezaSelec.color)
				{
					pieza.ComprobarMovimiento(this);
				}
			});
		}
		
		this.getChildren().addAll(piezasNegras);
		
		piezasBlancas.addAll(Arrays.asList(new Torre(0),
											new Caballo(0),
											new Alfil(0),
											new Reina(0),
											new Rey(0),
											new Alfil(0),
											new Caballo(0),
											new Torre(0)));
		
		for (int p = 0; p < 8; p++)
		{
			piezasBlancas.add(new Peon(0));
		}
		
		for (int pos = 0;pos<piezasBlancas.size();pos++) {
			Pieza pieza = piezasBlancas.get(pos);
			
			pieza.setX(pos%8*PXBLOQUE);
			pieza.setY((BLOQUES-1-pos/8)*PXBLOQUE);
			
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				
				QuitarMovimientos();
				
				if (turno == pieza.color)
				{
					pieza.ComprobarMovimiento(this);
				}
			});
		}

		this.getChildren().addAll(piezasBlancas);
	}

	private void GenerarTablero() {
		ArrayList<Rectangle> cuadriculas = new ArrayList<Rectangle>();
		
		for (int y = 0;y<BLOQUES;y++)
		{
			for (int x = 0;x<BLOQUES;x++)
			{
				Rectangle nc = new Rectangle(x*PXBLOQUE, y*PXBLOQUE, PXBLOQUE, PXBLOQUE);;
				if((x%2!=0 && y%2==0) || (x%2==0 && y%2!=0))
				{
					nc.setFill(Color.rgb(100, 100, 100));
				} else {
					nc.setFill(Color.rgb(160, 160, 160));
				}
				nc.setId("tablero");
				nc.setOnMouseClicked(e->QuitarMovimientos());
				cuadriculas.add(nc);
			}
		}
		
		this.getChildren().addAll(cuadriculas);
	}
	
	public void QuitarMovimientos() {
		for (int pos = 0;pos<this.getChildren().size();pos++) {
			if (this.getChildren().get(pos).getId()!=null)
				if (this.getChildren().get(pos).getId().equalsIgnoreCase("mov") || this.getChildren().get(pos).getId().equalsIgnoreCase("com")) {
					this.getChildren().remove(pos);
					pos--;
				}
		}
	}
}
