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
import javafx.animation.*;
import javafx.util.Duration;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.*;

public class gamePage implements Serializable
{
  private ArrayList<Player> players;
  private Cell[][] cells;
  // private Cell[][] oldCells;
  private Dimension dimension;
  private transient Label[][] Plabel;
  private Player curPlayer;
  // private int oldIndex;
  private int curIndex;
  private int count;
  private static animation anima;
  private static homePage home;
  private static winnerPage winner;
  private static Stage stage;
  private String startTime;
  private int limit;
  private boolean isWinner;
  private ArrayList<Player> oPlayers;

    /**
       * Constructs the gamePage of the game
       */
  public gamePage()
  {
    players = new ArrayList<Player>();
    count = 0;
    anima = new animation();
    home = new homePage();
    winner = new winnerPage();
    startTime = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
    curIndex = 0;
    isWinner = false;

  } 
    /**
       * Constructs the Cell with given critical mass and color
       *
       * @param      stage gamePage is set on this stage
       * @param      dimension dimensions of the gamepage
       * @param       list of players playing the game
       */
  public void openGame(Stage stage, Dimension dimension, ArrayList<Player> oPlayers)
  {
    File directory = new File("SavedGames");
    directory.mkdirs();
    this.stage = stage;
    this.dimension = dimension;
    this.oPlayers = oPlayers;
    cells = new Cell[dimension.getColumn()][dimension.getRow()];
    curPlayer = oPlayers.get(0);
    limit = oPlayers.size();

    for(int i=0; i<oPlayers.size(); i++)
    {
        if(oPlayers.get(i).getColor() == null)
        {
          players.add(new Player(oPlayers.get(i).getName(), oPlayers.get(i).getColorString()));
        }
        
        else
        {
          players.add(new Player(oPlayers.get(i).getName(), oPlayers.get(i).getColor()));
        }
    }

    /*    
    for(int i=0; i<players.size(); i++)
    {
      System.out.println(players.get(i));
    }
    */

    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);

    HBox hBox1 = new HBox();
    hBox1.setAlignment(Pos.TOP_RIGHT);
    hBox1.setSpacing(10);
    hBox1.setPadding(new Insets(10, 10, 10, 10));

    // Button settingsBut = new Button("SETTINGS");
    // settingsBut.setOnAction(e -> new settingsPage().openSettings(stage));
     
    Button undoBut = new Button("UNDO");
    
    MenuItem reStart = new MenuItem("START NEW");
    MenuItem backHome = new MenuItem("EXIT TO HOME PAGE");

    backHome.setOnAction(e -> home.start(stage));

    MenuButton cBox = new MenuButton("OPTIONS", null, reStart, backHome);
         
    // hBox1.getChildren().add(settingsBut);
    hBox1.getChildren().addAll(undoBut, cBox);

      
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setAlignment(Pos.CENTER);
    // grid.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

    vBox.getChildren().addAll(hBox1, grid);

    Plabel = new Label[dimension.getColumn()][dimension.getRow()]; 

    cells[0][0] = new Cell(1, players.get(0).getColor());
    cells[dimension.getColumn()-1][0] = new Cell(1, players.get(0).getColor());
    cells[dimension.getColumn()-1][dimension.getRow()-1] = new Cell(1, players.get(0).getColor());
    cells[0][dimension.getRow()-1] = new Cell(1, players.get(0).getColor());


    for(int i=0; i<dimension.getColumn(); i++)
    {
      for(int j=0; j<dimension.getRow(); j++)
      {
        if((i==0 || i==dimension.getColumn()-1) && (j>0 && j<dimension.getRow()-1))
        {
          cells[i][j] = new Cell(2, players.get(0).getColor());
        }

        else if((j==0 || j==dimension.getRow()-1) && (i>0 && i<dimension.getColumn()-1))
        {
          cells[i][j] = new Cell(2, players.get(0).getColor());
        }

        else if(i>0 && i<dimension.getColumn()-1 && j>0 && j<dimension.getRow()-1)
        {
          cells[i][j] = new Cell(3, players.get(0).getColor());
        }

        Plabel[i][j] = new Label(Integer.toString(cells[i][j].getPMass()));
        Plabel[i][j].setAlignment(Pos.CENTER);
        cells[i][j].getPane().getChildren().add(Plabel[i][j]);

        grid.add(cells[i][j].getPane(), i, j);        
      }
    }

    undoBut.setOnAction(new undoHandler(this));

    for(int i=0; i<dimension.getColumn(); i++)
    {
      for(int j=0; j<dimension.getRow(); j++)
      {
           
        int k = i;
        int l = j;
       
        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() { 
        @Override 
        public void handle(MouseEvent e)
        {
          if(cells[k][l].getCurPlayer()==null || cells[k][l].getCurPlayer().equals(curPlayer))
          {
            try
            {
              serialize();
            }
            catch(IOException exception)
            {
              exception.printStackTrace();
            }

            move(k, l, dimension.getColumn(), dimension.getRow());
            System.out.println("k = " + k + "; l = " + l + " ; nextPlayer");
            nextPlayer();
            // System.out.println("Done");


          }
        } 
        };

        cells[i][j].getPane().addEventFilter(MouseEvent.MOUSE_CLICKED, handler); 
      }
    }
    
    Scene scene = new Scene(vBox);    
    stage.setTitle("Chain Reaction"); 
    // System.out.println("resume 1");
    stage.setScene(scene); 
    reStart.setOnAction(new resetEvent(this, cells, Plabel, dimension.getRow(), dimension.getColumn(), players.get(0)));
  }
  /**
     * Handles the turn of next player in the game
     */
  public void nextPlayer()
  {
    // oldIndex = curIndex;
    curIndex = (curIndex + 1) % players.size();
    curPlayer = players.get(curIndex);
    Player selectPlayer = null;
    int selectIndex = curIndex;
    
    
    if(count > limit && !isWinner)
    {  
      boolean flag = false;

      while(selectPlayer == null && !players.isEmpty())
      {
        selectPlayer = players.get(selectIndex);

        for(int i=0; i<dimension.getColumn(); i++)
        {
          for(int j=0; j<dimension.getRow(); j++)
          {
            if(cells[i][j].getCurPlayer() == null)
            {
              continue;
            }

            else if(selectPlayer.equals(cells[i][j].getCurPlayer()))
            {
              flag = true;
              break;
            }
          }

          if(flag)
          {
            break;
          }
        }

        if(flag)
        {
          break;
        }

        players.remove(selectIndex);
        selectPlayer = null;
      }

      curIndex = selectIndex;
      curPlayer = players.get(curIndex);
    }

    for(int i=0; i<dimension.getColumn(); i++) 
    {
      for (int j=0; j<dimension.getRow(); j++) 
      {
        cells[i][j].setCellColor(players.get(curIndex).getColor());    
      }  
    }
  }

 /* static void reSet(Cell[][] cells, Label[][] Plabel, int row, int column)
  {
    for(int i=0; i<column; i++)
    {
      for(int j=0; j<row; j++)
      {
        Plabel[i][j].setText("0");
        Plabel[i][j].setTextFill(Color.BLACK);
        cells[i][j].setPMass(0);
        cells[i][j].setCurPlayer(null);
        cells[i][j].getOrbPane().getChildren().clear();

      }
    }
  }*/
  /**
     * Handles the moves of current player in the game
     */
  private void move(int k, int l, int column, int row)
  {
    /*for(int i=0; i<column; i++)
    {
      for(int j=0; j<row; j++)
      {
        oldCells[i][j] = new Cell(cells[i][j].getCMass(), cells[i][j].getPMass());
      }
    }*/

    Queue<Coordinate> queue = new LinkedList<Coordinate>();
    queue.add(new Coordinate(k, l));
    count ++;
    
    while(!queue.isEmpty())
    {
      Coordinate co = queue.poll();
      int i = co.getX();
      int j = co.getY();

      // System.out.println(co);
      
      if(cells[i][j].getPMass() < cells[i][j].getCMass())
      {
        cells[i][j].setCurPlayer(curPlayer);
        cells[i][j].setPMass(cells[i][j].getPMass() + 1);
        Plabel[i][j].setText(Integer.toString(cells[i][j].getPMass()));
        Plabel[i][j].setTextFill(curPlayer.getColor());
        anima.add(curPlayer.getColor(), cells[i][j].getOrbPane(), cells[i][j].getCMass(), cells[i][j].getPMass(), i, j);

        if(gameComplete())
        {
          System.out.println(curPlayer.getName() + " is Winner from add");
          winner.openWinnerPage(stage, curPlayer, this);
          isWinner = true;
          break;
        }
        
        continue;
      
      }

      cells[i][j].getOrbPane().getChildren().clear();
      // anima.explode(curPlayer.getColor(), cells[i][j].getOrbPane(), cells[i][j].getCMass());
      /*try
      {
        wait();
      }

      catch(InterruptedException except)
      {
        except.printStackTrace();
      }*/
      //TimeUnit.MILLISECONDS.sleep(200);
      // System.out.println("ex 3");
      // cells[i][j].getOrbPane().getChildren().clear();
      cells[i][j].setPMass(0);
      Plabel[i][j].setText("0");
      Plabel[i][j].setTextFill(Color.BLACK);
      cells[i][j].setCurPlayer(null);

      if(i-1>=0)
      {
        queue.add(new Coordinate(i-1, j));
        System.out.println("Left");
      }

      if(j-1>=0)
      {
        queue.add(new Coordinate(i, j-1));
        System.out.println("Up");
      }

      if(i+1<dimension.getColumn())
      {
        queue.add(new Coordinate(i+1, j));
        System.out.println("Right");
      }

      if(j+1<dimension.getRow())
      {
        queue.add(new Coordinate(i, j+1));
        System.out.println("Down");
      }

      if(gameComplete())
      {
        System.out.println(curPlayer.getName() + " is Winner from explode");
        winner.openWinnerPage(stage, curPlayer, this);
        isWinner = true;
        break;
      }
    }
  }
  /**
     * Handles the game completion event
     */
  private boolean gameComplete()
  {
    if(count <= limit)
    {
      return false;
    }

    for(int i=0; i<dimension.getColumn();i++)
    {
      for(int j=0; j<dimension.getRow(); j++)
      {
        if(cells[i][j].getCurPlayer() == null)
        {
          continue;
        }

        if(!curPlayer.equals(cells[i][j].getCurPlayer()))
        {
          return false;                 
        }
      }
    }
  
    return true;
  }
  /**
     * Serilasies the game 
     */
  public void serialize() throws IOException
  {
    ObjectOutputStream out = null;  
    
    try 
    {
      out = new ObjectOutputStream(new FileOutputStream("SavedGames/" + startTime + ".ser"));  
      out.writeObject(this); 
    } 

    finally 
    { 
      out.close();  
    } 
  }
  /**
     * Deserilaises the game
     * @param name of the game to be deserialized
     * @return returns the instance of game deserialized
     */
  public gamePage deserialize(String name) throws IOException, ClassNotFoundException
  {
    ObjectInputStream in = null;  
    gamePage page = null;
      
    try
    {
      in = new ObjectInputStream (new FileInputStream("SavedGames/" + name + ".ser")); 
      page = (gamePage) in.readObject();
    }

    finally
    {
      in.close();
      return page;
    }
  }
  /**
     * Returns the start time of the event
     * 
     * @return Returns the start time of the event
     */
  public String getStartTime()
  {
    return startTime;
  }
  /**
     * sets the start time of the event
     * 
     * @param  the start time of the event
     */
  public void setStartTime(String s)
  {
    startTime = s;
  }
  /**
     * Returns animation of the event
     * 
     * @return Returns the animationof the event
     */
  public animation getAnimation()
  {
    return anima;
  }  
  /**
     * Returns the current player in the game
     * 
     * @return the current player in the game
     */
  public Player getCurPlayer()
  {
    return curPlayer;
  }
   /**
     * Sets the current player in the game
     * 
     * @param the current player in the game
     */
  public void setCurPlayer(Player p)
  {
    curPlayer = p;
  }
   /**
     * Returns the cells of the game
     * 
     * @return cells of the game
     */
  public Cell[][] getCells()
  {
    return cells;
  }
  /**
     * Returns the dimensions
     * 
     * @param Dimension the dimensions
     */
  public Dimension getDimension()
  {
    return dimension;
  }
  /**
     * Returns the label of the cell
     * 
     * @return the label of current cell
     */
  public Label[][] getPLabel()
  {
    return Plabel;
  }
  /**
     * Returns the Arraylist of players
     * 
     * @return the Arraylist of players
     */
  public ArrayList<Player> getPlayers()
  {
    return players;
  }
  /**
     * Returns the currrent index
     * 
     * @param val current index
     */
  public int getCurIndex()
  {
    return curIndex;
  }
  /**
     * Returns the number of moves
     * 
     * @return  NUMBER OF moves
     */
  public int getCount()
  {
    return count;
  }
  /**
     * Sets the currrent index
     * 
     * @param val current index
     */
  public void setCurIndex(int val)
  {
    curIndex = val;
  }
  /**
     * Sets the number of moves
     * 
     * @param val NUMBER OF moves
     */
  public void setCount(int val)
  {
    count = val;
  }
  /**
     * Sets the winner of the game
     * 
     * @param b detemines whether the player is winner
     */
  public void setWinner(boolean b)
  {
    isWinner = b;
  }
  /**
     * Removes all the players of current game
     */
  public void removePlayers()
  {
    players.clear();
  }
  /**
     * Returns the Arraylist of players
     * 
     * @return the Arraylist of players
     */
  public ArrayList<Player> getOPlayers()
  {
    return oPlayers;
  }

}


