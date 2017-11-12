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

class gamePage
{
   public void openGame(Stage stage, Dimension dimension)
   {
      int row = dimension.row;
      int column = dimension.column;

      VBox vBox = new VBox();
      vBox.setAlignment(Pos.CENTER);

      HBox hBox1 = new HBox();
      hBox1.setAlignment(Pos.TOP_RIGHT);
      hBox1.setSpacing(10);
      hBox1.setPadding(new Insets(10, 10, 10, 10));

      Button settingsBut = new Button("SETTINGS");
      settingsBut.setOnAction(e -> new settingsPage().openSettings(stage));
     
      Button undoBut = new Button("UNDO");

      MenuItem reStart = new MenuItem("START NEW");
      MenuItem backHome = new MenuItem("EXIT TO HOME PAGE");

      backHome.setOnAction(e -> new homePage().start(stage));

      MenuButton cBox = new MenuButton("OPTIONS", null, reStart, backHome);
         

      hBox1.getChildren().addAll(settingsBut, undoBut, cBox);

      
      GridPane grid = new GridPane();
      grid.setPadding(new Insets(10, 10, 10, 10));
      grid.setAlignment(Pos.CENTER);
      grid.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

      vBox.getChildren().addAll(hBox1, grid);

      StackPane[][] cell = new StackPane[column][row];

      Box[][] cuboid = new Box[column][row];

      Rectangle[][] rect = new Rectangle[column][row];

      int[][] Cmass = new int[column][row];

      int[][] Pmass = new int[column][row];

      Label[][] Plabel = new Label[column][row]; 

      Cmass[0][0] = 1;
      Cmass[column-1][0] = 1;
      Cmass[column-1][row-1] = 1;
      Cmass[0][row-1] = 1;


      for(int i=0; i<column; i++)
      {
         for(int j=0; j<row; j++)
         {
            if((i==0 || i==column-1) && (j>0 && j<row-1))
            {
               Cmass[i][j] = 2;
            }

            else if((j==0 || j==row-1) && (i>0 && i<column-1))
            {
               Cmass[i][j] = 2;
            }

            else if(i>0 && i<column-1 && j>0 && j<row-1)
            {
               Cmass[i][j] = 3;
            }

            cell[i][j] = new StackPane();
            cell[i][j].setPrefWidth(50);
            cell[i][j].setPrefHeight(50);
            cell[i][j].setAlignment(Pos.CENTER);;
            // cell[i][j].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            cuboid[i][j] = new Box(50, 50, 50);
            cuboid[i][j].setDrawMode(DrawMode.LINE);
            cell[i][j].getChildren().add(cuboid[i][j]);

            // cuboid[i][j].setFill(Color.TRANSPARENT);
            // cuboid[i][j].setStroke(Color.BLACK);

            cuboid[i][j].getTransforms().add(new Rotate(-10, 0, 0, 0, Rotate.Y_AXIS));
            cuboid[i][j].getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.X_AXIS));

            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.GREEN);
            material.setSpecularColor(Color.GREEN);

            cuboid[i][j].setMaterial(material);

            rect[i][j] = new Rectangle(38, 38);
            rect[i][j].setFill(Color.WHITE);

            cell[i][j].getChildren().add(rect[i][j]);


           Plabel[i][j] = new Label(Integer.toString(Pmass[i][j]));
           Plabel[i][j].setAlignment(Pos.CENTER);
           // Plabel[i][j].setPrefHeight(10);
           // Plabel[i][j].setPrefWidth(10);
           cell[i][j].getChildren().add(Plabel[i][j]);

           grid.add(cell[i][j], i, j); 
         }
      }

      EventHandler<MouseEvent>[] eventHandler = new EventHandler[row*column]; 
   
      for(int i=0; i<column; i++)
      {
         for(int j=0; j<row; j++)
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

      reStart.setOnAction(e -> reSet(Plabel, row, column));
   }

   static void reSet(Label[][] Plabel, int row, int column)
   {
      for(int i=0; i<column; i++)
      {
         for(int j=0; j<row; j++)
         {
            Plabel[i][j].setText("0");
         }
      }
   }
}
