import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 

public class Orbs
{
	/**
     * Color of the orb
     */
	private Color oColor;
	/**
     * no. of balls in the or
     */
	private int no_balls;

	/**
     * Constructs an orb with given number of balls
     * and color.
     * @param      c Color of the orb to be set up
     * @param      n no. of balls in the orb
     */
	public Orbs(Color c,int n)
	{
		this.oColor = c;
		this.no_balls = n;
	}

	/**
     * returns the color of the orb
     * @return     the color of the orb
     */
	public Color getOrbColor()
	{
		return this.oColor;
	}

	/**
     * Sets up the color of the current orb
     * @param      c the color of the current orb
     */
	public void setColor(Color c)
	{
		this.oColor=c;
	}
}