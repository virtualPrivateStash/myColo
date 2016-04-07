package de.dide.myColo.view.tui;

import de.dide.myColo.view.tui2.Tui;
import de.dide.myColo.view.tui2.TuiCell;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameCellTest {

	private TuiCell sut;
	final static int CELLSIZE = TuiCell.getCellSize();
	char[] constructedArray;
	char BORDERCHAR = TuiCell.getBorderChar(); 
	
	@Before
	public void setUp() throws Exception {
		sut = new TuiCell(null, null);
		constructedArray = new char[CELLSIZE];
		TuiCell.getBorderChar();
	}

	@After
	public void tearDown() throws Exception {
		constructedArray = null;
	}

	@Test
	/**
	 * tests for: 
	 * 		* must return a correct cellArray;
	 */
	public void fillWholeLineTest() {
		// fill constructedArray with BORDERCHAR to test against the constructed elements
		for (int i = 0; i < CELLSIZE; i++) {
			constructedArray[i] = BORDERCHAR;
		}
		//testOneRow(1);
		testOneColumn(1);
	}

	@Test
	private void testOneColumn(int col) {
		
		//assertArrayEquals(constructedArray, sut);
		
		for (int i=0; i < CELLSIZE; i++) {
			//sut[i][col] != BORDERCHAR
		}
	}
	
}
