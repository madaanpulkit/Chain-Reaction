import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.io.*;
import javafx.stage.Stage;

public class resumeHandler implements EventHandler<ActionEvent>
{
	private Stage stage;
	private String fileName;
	private gamePage game;
	private Cell[][] cells;
	private Label[][] Plabel;
	private animation anima;
	private Dimension dimension;

	resumeHandler(Stage stage, String name)
	{
		this.stage = stage;
		fileName = name;
		game = new gamePage();
	}

	@Override
	public void handle(ActionEvent resume)
	{
		gamePage oldGame = null;

		try
		{
			oldGame = game.deserialize(fileName.substring(0, fileName.length()-4));
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		dimension = oldGame.getDimension();

		game.openGame(stage, dimension, oldGame.getOPlayers());

		cells = game.getCells();
		anima = game.getAnimation();
		Plabel = game.getPLabel();

		game.setStartTime(oldGame.getStartTime());

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