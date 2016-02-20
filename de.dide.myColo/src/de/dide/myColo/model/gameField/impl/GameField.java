package de.dide.myColo.model.gameField.impl;

public class GameField {

	GameCell[][] gameField;
	public BuildField fieldBuildingAlgo;
	
	public GameField() {
		fieldBuildingAlgo = new AlgoEasyField();
	}
	
 
	
	public void setFieldBuildingAlgo(BuildField newBuildingAlgo) {
		fieldBuildingAlgo = newBuildingAlgo;
	}
	
}
