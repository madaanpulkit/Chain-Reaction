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

public class homePage extends Application 
{ 
   @Override 
   public void start(Stage stage) 
   {    
      VBox vBox1 = new VBox();

      vBox1.setSpacing(20);
      vBox1.setPadding(new Insets(20, 20, 20, 20));

      vBox1.setAlignment(Pos.CENTER);

      Label winLabel = new Label("CHAIN REACTION");
      winLabel.setFont(new Font((double) 50.0));
      vBox1.getChildren().add(winLabel);

      Label dropLabel = new Label("Select the number of Players");
      vBox1.getChildren().add(dropLabel);

      ChoiceBox numPlayerChoice = new ChoiceBox();
      numPlayerChoice.getItems().addAll("2 Players", "3 Players", "4 Players", "5 Players", "6 Players", "7 Players", "8 Players");
      numPlayerChoice.getSelectionModel().select(0);
      vBox1.getChildren().add(numPlayerChoice);

      RadioButton gridOption1 = new RadioButton("9x6");
      RadioButton gridOption2 = new RadioButton("15x10");
      
      ToggleGroup gridOptionGroup = new ToggleGroup();

      gridOption1.setToggleGroup(gridOptionGroup);
      gridOption2.setToggleGroup(gridOptionGroup);

      gridOption1.setSelected(true);

      Label grSizeLabel = new Label("Select the Grid Size");
      vBox1.getChildren().add(grSizeLabel);

      HBox hBox0 = new HBox(gridOption1, gridOption2);
      hBox0.setAlignment(Pos.CENTER);
      hBox0.setSpacing(30);

      vBox1.getChildren().add(hBox0);


      HBox hBox1 = new HBox();
      hBox1.setAlignment(Pos.CENTER);
      hBox1.setSpacing(30);

      Button resumeBut = new Button("RESUME");
      hBox1.getChildren().add(resumeBut);

      Button newGaneBut = new Button("NEW GAME");
      hBox1.getChildren().add(newGaneBut);

      vBox1.getChildren().add(hBox1);

      HBox hBox2 = new HBox();
      hBox2.setAlignment(Pos.CENTER);
      hBox2.setSpacing(50);

      ToggleButton toggleSound = new ToggleButton("SOUND");
      hBox2.getChildren().add(toggleSound);

      Button settingsBut = new Button("SETTINGS");
      hBox2.getChildren().add(settingsBut);

      vBox1.getChildren().add(hBox2);

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