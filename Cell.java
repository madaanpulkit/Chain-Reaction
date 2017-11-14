import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import java.io.*;
import java.util.*;

class Cell
{
	private StackPane sPane;
	private Box cube;
	private Rectangle rect;
	private final int cMass;
	private int pMass;
	// private ArrayList<Orbs> orb;
	private StackPane orbPane;
	private Player curPlayer;
	private PhongMaterial material;

	public Cell(int c, Color col)
	{
		sPane = new StackPane();
		cube = new Box(50, 50, 50);
		rect = new Rectangle(38,38);
		cMass=c;
		material = new PhongMaterial();
		this.setCellColor(col);	
		this.setPane();
		this.setCube();
		this.setRotate();
		this.setRect();
		curPlayer = null;
		sPane.getChildren().add(orbPane);
	}

	private void setCube()
	{
        cube.setDrawMode(DrawMode.LINE);
        sPane.getChildren().add(cube);
	}

	private void setPane()
	{
		sPane.setPrefWidth(50);
		sPane.setPrefHeight(50);
		sPane.setAlignment(Pos.CENTER);
	}

	private void setRect()
	{
		rect.setFill(Color.WHITE);
		sPane.getChildren().add(rect);
	}

	private void setRotate()
	{
		cube.getTransforms().add(new Rotate(-10, 0, 0, 0, Rotate.Y_AXIS));
		cube.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.X_AXIS));
	}

	public void setCellColor(Color c)
	{
		material.setDiffuseColor(c);
		material.setSpecularColor(c);
		cube.setMaterial(material);
	}

	public int getCMass()
	{
		return cMass;
	}

	public int getPMass()
	{
		return pMass;
	}

	public void setPMass(int p)
	{
		pMass = p;
	}

	public StackPane getPane()
	{
		return sPane;
	}

	public Player getCurPlayer()
	{
		return curPlayer;
	}

	public void setCurPlayer(Player p)
	{
		curPlayer = p;
		// setOrbColor(p.getColor());
	}

	public StackPane getOrbPane()
	{
		return orbPane;
	}
}