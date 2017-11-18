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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class settingsPage
{
   private static ArrayList<Player> playerList;
   private ArrayList<Color> colorList;
   // private homePage home;

/*   settingsPage(homePage home)
   {
      this.home = home;
      
   }*/
   /**
        * Returns the object of a player.
        * @param      i i is the location of the player present at location i in the playerlist
        * @return     the Player present at location i in the playerlist
        */
   public static Player getPlayer(int i)
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
      Label headLabel = new Label("SETTINGS");
      headLabel.setFont(new Font(50.0));
      vBox1.getChildren().add(headLabel);
      /**
           * Hbox contains the name and color choice of the player
           */
      HBox[] hBox = new HBox[8];
      Label[] pLabel = new Label[8];
      ArrayList<ChoiceBox<String>> pBox = new ArrayList<ChoiceBox<String>>();

      for(int i=0; i<8; i++)
      {
         hBox[i] = new HBox();
         hBox[i].setSpacing(30);
         hBox[i].setAlignment(Pos.CENTER);

         pLabel[i] = new Label("Player "+Integer.toString(i+1));
         hBox[i].getChildren().add(pLabel[i]);
         
         pBox.add(i,new ChoiceBox<String>());
         pBox.get(i).getItems().addAll("RED", "YELLOW", "GREEN", "BLUE", "BROWN", "WHITE", "GREY", "PINK");
         pBox.get(i).getSelectionModel().select(i);
         hBox[i].getChildren().add(pBox.get(i));

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
               boolean flag = false;
               Label warningLabel = new Label("Two Players cannot have Same Color.");
               warningLabel.setFont(new Font(40.0));
               //boolean warn = false;
               for(int x=0;x<8;x++)
               {
                  for(int y=0 ; y<8 ; y++)
                  {
                     String s1 = pBox.get(x).getSelectionModel().getSelectedItem().substring(0);
                     String s2 = pBox.get(y).getSelectionModel().getSelectedItem().substring(0);
                     if(s1.compareTo(s2)==0 && x!=y)
                     {
                        flag=true;
                     }
                  }
               }
               if(flag)
               {
                  
                  //vBox1.getChildren().add(warningLabel);
                  //warn = true;
                  Alert alert = new Alert(AlertType.WARNING);
                  alert.setTitle("Warning");
                  alert.setHeaderText("Two PLayers Cannot have same color");
                  alert.setContentText("Please ensure different colors for all your Players!");

                  alert.showAndWait();
               }
               else
               {
                  playerList = new ArrayList<Player>();
                  for(int i=0; i<8; i++)
                  {
                     String s = pBox.get(i).getSelectionModel().getSelectedItem().substring(0);
                     System.out.println(s);
                     playerList.add(new Player("Player " + Integer.toString(i+1), s.toLowerCase()));
                  }
                  /*if(warn)
                  {
                     vBox1.getChildren().remove(warningLabel);
                  }*/
                  new homePage().start(stage, playerList);
               }
            } 
         };

      backBut.addEventFilter(MouseEvent.MOUSE_CLICKED, backHandler);
   }
}