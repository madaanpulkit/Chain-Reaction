import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class settingsPage extends Application 
{ 
   @Override 
   public void start(Stage stage) 
   {    
      VBox vBox1 = new VBox();
      vBox1.setSpacing(20);
      vBox1.setPadding(new Insets(20, 20, 20, 20));
      vBox1.setAlignment(Pos.CENTER);

      Label headLabel = new Label("SETTINGS");
      headLabel.setFont(new Font((double) 50.0));
      vBox1.getChildren().add(headLabel);

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

      Button backBut = new Button("BACK");
      vBox1.getChildren().add(backBut);

      Scene scene = new Scene(vBox1);  
      
      stage.setTitle("Chain Reaction"); 
         
      stage.setScene(scene); 
         
      stage.show(); 
   } 

   public static void main(String args[])
   { 
      launch(args); 
   } 
}