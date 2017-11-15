import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Insets;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.util.*;
import javafx.scene.paint.*;

public class settingsPage
{
   private ArrayList<Player> playerList;
   private ArrayList<Color> colorList;

   /**
     * Sets up the settings Page
     */
   settingsPage()
   {
      playerList = new ArrayList<Player>();
      colorList = new ArrayList<Color>(Arrays.asList(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.BROWN, Color.WHITE, Color.GREY, Color.PINK));

      for(int i=0; i<8; i++)
      {
         playerList.add(new Player("Player " + Integer.toString(i+1), colorList.get(i)));
      }

      for(int i=0; i<playerList.size(); i++)
      {
         System.out.println("Settings " + playerList.get(i));
      }
   }

   /**
     * Returns the object of a player.
     * @param      i i is the location of the player present at location i in the playerlist
     * @return     the Player present at location i in the playerlist
     */
   public Player getPlayer(int i)
   {
      return playerList.get(i);
   }

   /**
     * openSettings function contains the settings for each and every player.
     * It has dropdown menu for each and every player from where the player can choose his color.
     * It also contains the field for entering the name of every player.
     * @param      stage It provides the stage for the game page.
     */
   public void openSettings(Stage stage)
   {
      /**
     * Sets up the Vertical Box of the settings Page with all its specifiations
     */
      VBox vBox1 = new VBox();
      vBox1.setSpacing(20);
      vBox1.setPadding(new Insets(20, 20, 20, 20));
      vBox1.setAlignment(Pos.CENTER);
      /**
     * Label showing the text Settings
     */
      Label headLabel = new Label("SETTINGS"); //
      headLabel.setFont(new Font(50.0));
      vBox1.getChildren().add(headLabel);
      /**
     * Hbox contains the name and color choice of the player
     */
      HBox[] hBox = new HBox[8];
      Label[] pLabel = new Label[8];
      ChoiceBox[] pBox = new ChoiceBox[8];

      for(int i=0; i<8; i++)
      {
         hBox[i] = new HBox();
         hBox[i].setSpacing(30);
         hBox[i].setAlignment(Pos.CENTER);

         pLabel[i] = new Label("Player "+Integer.toString(i+1));
         hBox[i].getChildren().add(pLabel[i]);
         
         pBox[i] = new ChoiceBox();
         pBox[i].getItems().addAll("RED", "YELLOW", "GREEN", "BLUE", "BROWN", "WHITE", "GREY", "PINK");
         pBox[i].getSelectionModel().select(i);
         hBox[i].getChildren().add(pBox[i]);

         vBox1.getChildren().add(hBox[i]);
      }
      /**
     * Button to go back to Home page
     */
      Button backBut = new Button("BACK");
      vBox1.getChildren().add(backBut);

      Scene scene = new Scene(vBox1);  
      
      stage.setTitle("Chain Reaction"); 
         
      stage.setScene(scene);

      EventHandler<MouseEvent> backHandler = new EventHandler<MouseEvent>(){ 
            @Override 
            public void handle(MouseEvent e) {
                 
              new homePage().start(stage);
            } 
         };

      backBut.addEventFilter(MouseEvent.MOUSE_CLICKED, backHandler);
   }
}