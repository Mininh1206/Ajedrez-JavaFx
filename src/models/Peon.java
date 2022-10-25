package models;

import static helpers.Constants.*;

public class Peon extends Pieza {
	public Peon(int color)
	{
		this.color = color;
		
		if (color == 0)
			this.setImage(PEONB);
		else
			this.setImage(PEONN);
	}
}
