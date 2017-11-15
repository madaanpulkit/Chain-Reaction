/**
* <h1>Class Coordinate</h1>
* The Coordinate class determines the coordinates of a cll in terms of its rows and columns.
* <p>
* <b>Note:</b> ......
*
* @author  Pulkit Madaan , Gyanesh Anand
* @version 1.0
* @since   2017-11-16
*/


public final class Coordinate
{
	/**
     * To determine the Coordinate of a cell in the grid
     */
	private final int x;
	/**
     * To determine the Coordinate of a cell in the grid
     */
	private final int y;

	/**
     * Constructs a Coordinate object containg row's and Column's numbers of a cell
     * @param      x To determine the row of a cell in the grid
     * @param      y To determine the column of a cell in the grid
     */
	public Coordinate(int _x , int _y)
	{
		this.x=_x;
		this.y=_y;
	}

	/**
     * returns the x coordinate of a cell
     * @return     Returns the x coordinate of a cell in the grid
     */
	public int getX()
	{
		return x;
	}
	/**
     * returns the y coordinate of a cell
     * @return     Returns the y coordinate of a cell in the grid
     */

	public int getY()
	{
		return y;
	}
	/**
     * returns the coordinate of a cell
     * @return     Returns the coordinate of a cell in the grid
     */

	@Override
	public String toString()
	{
		return x + " " + y;
	}
}