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
   public void openWinnerPage(Stage stage , Player p) 
   {  
      VBox vBox = new VBox();
      vBox.setSpacing(20);
      vBox.setPadding(new Insets(20, 20, 20, 20));
      vBox.setAlignment(Pos.CENTER);

      String winnerName = "p.getName()";

      Label winLabel = new Label(winnerName);
      winLabel.setFont(new Font((double) 50.0));
      vBox.getChildren().add(winLabel);

      Button startNewBut = new Button("START NEW GAME");
      vBox.getChildren().add(startNewBut);

      Button exitGameBut = new Button("EXIT GAME");
      vBox.getChildren().add(exitGameBut);

      //vBox1.getChildren().add(hBox1);     
      Scene scene = new Scene(vBox);       
      stage.setTitle("Chain Reaction");       
      stage.setScene(scene);       
      stage.show();
      EventHandler<MouseEvent> gameHandler = new EventHandler<MouseEvent>(){
         @Override
         public void handle(MouseEvent e)
         {
            /*if(gridOption1.isSelected())
            {
               new gamePage().openGame(stage, new Dimension(9, 6));   
            }

            else
            {
               new gamePage().openGame(stage, new Dimension(15, 10));
            }
            */
         }
      };

      //newGameBut.addEventFilter(MouseEvent.MOUSE_CLICKED, gameHandler);
   } 
}
