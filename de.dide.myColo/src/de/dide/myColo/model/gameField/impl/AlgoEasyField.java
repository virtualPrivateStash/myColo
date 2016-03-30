package de.dide.myColo.model.gameField.impl;

public class AlgoEasyField { //implements BuildField

	GameCell[][] gameField;
	
	public GameCell[][] buildGameField(int sizeCol, int sizeRow) {
		gameField = new GameCell[sizeCol][sizeRow];
		
		for (int i=0; i < sizeCol; i++) {
			for (int j=0; j < sizeRow; j++) {
//				gameField[sizeCol][sizeRow] = new GameCell();
			}
		}
		
		return null;
	}
	
	
	
	
}
