package com.salgun.model;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{ 
	
	public void findDestination(Square[][] mSquares)
	{
		if (isBlack()) {
			//Siyah için hesaplamaya þu an gerek yok
		}
		else {
			Square dest1 = mSquares[getCurrentSquare().getRow()-2][getCurrentSquare().getCol()-1];
			Square dest2 = mSquares[getCurrentSquare().getRow()-2][getCurrentSquare().getCol()+1];
			
			List<Square> list = new ArrayList<Square>();
			list.add(dest1);
			list.add(dest2);
			
			this.setDestinations(list);
		}
		
	}

	

}
