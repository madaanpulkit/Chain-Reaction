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

class Cell
{
	public StackPane sPane;
	public Box cube;
	public Rectangle rect;
	public int cMass;
	public int pMass;
	public Orbs orb;
	public PhongMaterial material;

	public Cell(int c , int p , Color col)
	{
		sPane = new StackPane();
		cube = new Box(50, 50, 50);
		rect = new Rectangle(38,38);
		this.cMass=c;
		this.pMass=p;
		material = new PhongMaterial();
		this.setCellColor(col);
		this.setPane();
		this.setRect();
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
	}
	public void setCellColor(Color c)
	{
		material.setDiffuseColor(c);
		material.setSpecularColor(c);
		cube.setMaterial(material);
	}
}