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

import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;

public class winnerPage
{ 
   /**
     * An object of homePage which is opened
     */
   homePage home;
   /**
     * Creates an object of homeClass which is opened when 
     * clicked new game on the winnerPage
     */
   winnerPage()
   {
      home = new homePage();
   }
   /**
     * Takes the input a stage and a player declares it as winner
     * Further more , it opens a new homePage when clicked new
     * Or else exits the game when clicked EXIT
     * @param stage the same stage across entire package
     * @param p the player who has won the match
     */
   public void openWinnerPage(Stage stage , Player p, gamePage game) 
   {  
      VBox vBox = new VBox();
      vBox.setSpacing(20);
      vBox.setPadding(new Insets(20, 20, 20, 20));
      vBox.setAlignment(Pos.CENTER);

      String winnerName = p.getName() + " is Winner ";

      Label winLabel = new Label(winnerName);
      winLabel.setFont(new Font(50.0));
      vBox.getChildren().add(winLabel);

      Button startNewBut = new Button("START NEW GAME");
      startNewBut.setOnAction(e -> home.start(stage));
      vBox.getChildren().add(startNewBut);

      Button undoBut = new Button("UNDO");

      vBox.getChildren().add(undoBut);

      undoBut.setOnAction(new resumeHandler(stage, game.getStartTime() + ".ser"));

      Scene scene = new Scene(vBox);    
      stage.setTitle("Chain Reaction"); 
      stage.setScene(scene);
   } 
}
