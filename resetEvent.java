/**
* <h1>Class Dimension</h1>
* The Dimension class determines the dimension of the gamePage in terms of its rows and columns
* <p>
* <b>Note:</b> ......
*
* @author  Pulkit Madaan , Gyanesh Anand
* @version 1.0
* @since   2017-11-16
*/


import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.io.*;

public class resetEvent implements EventHandler<ActionEvent>
{

	Cell[][] cells;
	Label[][] Plabel;
	int row;
	int column;
	gamePage game;
	Player newPlayer;

	/**
	     * Constructs a reset event to Undo the game
	     * @param      game Game on which undo works
	     * @param      cells the array of cells to reset
	     * @param      Plabel labels to dispaly the count of balls in a cell
	     * @param      row row in the grid
	     * @param      column in the grid
	     * @param      Player the player whose game is to reset
	     */
	public resetEvent(gamePage game, Cell[][] cells, Label[][] Plabel, int row, int column, Player newPlayer)
	{
		this.cells = cells;
		this.Plabel = Plabel;
		this.row = row;
		this.column = column;
		this.game = game;
		this.newPlayer = newPlayer;
	}

		/**
	     * Handles the Reset Event
	     * @param      reset listens to reset event
	     */
	@Override
	public void handle(ActionEvent reset)
	{
		game.setCount(0);
		game.setCurIndex(0);
		for(int i=0; i<column; i++)
	    {
	      for(int j=0; j<row; j++)
	      {
	        Plabel[i][j].setText("0");
	        Plabel[i][j].setTextFill(Color.BLACK);
	        cells[i][j].setPMass(0);
	        cells[i][j].setCurPlayer(null);
	        cells[i][j].getOrbPane().getChildren().clear();
	        cells[i][j].setCellColor(newPlayer.getColor());

	      }
	    }

	    game.setCurPlayer(newPlayer);

	    try
	    {
	    	game.serialize();
	    }

	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
}
