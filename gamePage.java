import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Sphere;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class gamePage extends Application 
{ 
   @Override 
   public void start(Stage stage) 
   {    
      VBox vBox = new VBox();
      vBox.setAlignment(Pos.CENTER);

      HBox hBox = new HBox();
      hBox.setAlignment(Pos.TOP_RIGHT);
      hBox.setSpacing(10);
      hBox.setPadding(new Insets(10, 10, 10, 10));

      Button settingsBut = new Button("SETTINGS");
      Button undoBut = new Button("UNDO");
      ChoiceBox cBox = new ChoiceBox();
      cBox.getItems().addAll("START NEW", "EXIT TO HOME PAGE");
      cBox.getSelectionModel().select(0);
         

      hBox.getChildren().addAll(settingsBut, undoBut, cBox);
      
      GridPane grid = new GridPane();
      grid.setPadding(new Insets(10, 10, 10, 10));
      grid.setAlignment(Pos.CENTER);
      grid.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

      vBox.getChildren().addAll(hBox, grid);

      int row = 6;
      int column = 9;

      HBox[][] cell = new HBox[row][column];

      int[][] Cmass = new int[row][column];

      int[][] Pmass = new int[row][column];

      Label[][] Plabel = new Label[row][column]; 

      Cmass[0][0] = 1;
      Cmass[0][column-1] = 1;
      Cmass[row-1][column-1] = 1;
      Cmass[row-1][0] = 1;


      for(int i=0; i<row; i++)
      {
         for(int j=0; j<column; j++)
         {
            if((i==0 || i==row-1) && (j>0 && j<column-1))
            {
               Cmass[i][j] = 2;
            }

            else if((j==0 || j==column-1) && (i>0 && i<row-1))
            {
               Cmass[i][j] = 2;
            }

            else if(i>0 && i<row-1 && j>0 && j<column-1)
            {
               Cmass[i][j] = 3;
            }

           cell[i][j] = new HBox();
           cell[i][j].setPrefWidth(50);
           cell[i][j].setPrefHeight(50);
           cell[i][j].setAlignment(Pos.CENTER);;
           cell[i][j].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

           Plabel[i][j] = new Label(Integer.toString(Pmass[i][j]));
           Plabel[i][j].setAlignment(Pos.CENTER);
           Plabel[i][j].setPrefHeight(50);
           Plabel[i][j].setPrefWidth(50);
           cell[i][j].getChildren().add(Plabel[i][j]);

           grid.add(cell[i][j], i, j); 
         }
      }

      EventHandler<MouseEvent>[] eventHandler = new EventHandler[row*column]; 
   
      for(int i=0; i<row; i++)
      {
         for(int j=0; j<column; j++)
         {
           int k = i;
           int l = j;
           
           eventHandler[((i+1)*(j+1))-1] = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) {
               int p = Integer.parseInt(Plabel[k][l].getText()); 
               if(p < Cmass[k][l])
               {
                  Plabel[k][l].setText(Integer.toString(p+1));
               }  

            } 
         };

         Plabel[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler[((i+1)*(j+1))-1]); 
         }
      }

      Scene scene = new Scene(vBox);  
      
      stage.setTitle("Chain Reaction"); 
       
      stage.setScene(scene); 
      
      stage.show(); 
   } 

   public static void main(String args[])
   { 
      launch(args); 
   } 
}
