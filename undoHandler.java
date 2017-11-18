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

public class undoHandler implements EventHandler<ActionEvent>
{
	private Cell[][] cells;
	private Label[][] Plabel;
	// int row;
	// int column;
	private gamePage game;
	// Player curPlayer;
	// Player newPlayer;
	private animation anima;
	private Dimension dimension;
	/**
	     * Constructs a undo event to Undo the game
	     * @param      game Game on which undo works
	     */
	undoHandler(gamePage game)
	{ 
		this.game = game;
		anima = game.getAnimation();
		cells = game.getCells();
		Plabel = game.getPLabel();
		dimension = game.getDimension();
	}
	/**
	     * Handles the Undo Event
	     * @param      reset listens to reset event
	     */
	@Override
	public void handle(ActionEvent reset)
	{
		gamePage oldGame = null;
		
		try
		{
			oldGame = game.deserialize(game.getStartTime());
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		game.setCurPlayer(new Player(oldGame.getCurPlayer().getName(), oldGame.getCurPlayer().getColorString()));

		game.setCurIndex(oldGame.getCurIndex());
		game.setCount(oldGame.getCount());

		Cell[][] oldCells = oldGame.getCells();

		game.removePlayers();

		for(int i=0; i<oldGame.getPlayers().size(); i++)
		{
			game.getPlayers().add(new Player(oldGame.getPlayers().get(i).getName(), oldGame.getPlayers().get(i).getColorString()));
		}

		for(int i=0; i<dimension.getColumn(); i++)
		{
			for(int j=0; j<dimension.getRow(); j++)
			{
				cells[i][j].setPMass(oldCells[i][j].getPMass());
				Plabel[i][j].setText(Integer.toString(cells[i][j].getPMass()));
				cells[i][j].setCellColor(Color.valueOf(oldGame.getCurPlayer().getColorString()));
				// System.out.println(Plabel[i][j].getText());
				if(oldCells[i][j].getCurPlayer() != null)
				{
					cells[i][j].setCurPlayer(new Player(oldCells[i][j].getCurPlayer().getName(), oldCells[i][j].getCurPlayer().getColorString()));
					Plabel[i][j].setTextFill(cells[i][j].getCurPlayer().getColor());
					anima.add(cells[i][j].getCurPlayer().getColor(), cells[i][j].getOrbPane(), cells[i][j].getCMass(), cells[i][j].getPMass(), i, j);
				}

				else
				{
					cells[i][j].setCurPlayer(null);
					cells[i][j].getOrbPane().getChildren().clear();
					Plabel[i][j].setTextFill(Color.BLACK);
				}
			}
		}

	}
}
