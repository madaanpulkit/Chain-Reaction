import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 

class Player
{
	private Color pColor;
	private String name;
	private Sound sfx;

	public Player(Color c , Sound s)
	{
		this.pColor=c;
		this.sfx = s;
		
	}
	public Player(Color c , Sound s , String n)
	{
		this.pColor=c;
		this.sfx = s;
		this.name = n;
	}

	public Color getColor()
	{
		return this.pColor;
	}
	public Sound getSound()
	{
		return this.sfx;
	}
	public String getName()
	{
		return this.name;
	}
	public void setColor(Color c)
	{
		this.pColor=c;
	}
	public void setName(String n)
	{
		this.name = n;
	}
	public void setSound(Sound s)
	{
		this.sfx = s;
	}

}