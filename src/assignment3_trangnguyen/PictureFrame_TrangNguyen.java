
package assignment3_trangnguyen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * In this program, user can upload their picture and modify picture frame as they wish
 * @author Trang Nguyen
 */

public class PictureFrame_TrangNguyen extends Application {
    private Label text;
    private double size;
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        HBox frame = new HBox();
        frame.setPrefWidth(780);
        VBox onTheLeft = new VBox();
        VBox onTheRight = new VBox();
        onTheLeft .setPrefWidth(frame.getPrefWidth()/2);
        onTheRight .setPrefWidth(frame.getPrefWidth()/2);
        
        
        
// on the right

        // rectangle
        StackPane forPicture = new StackPane();
        
        Rectangle rec = new Rectangle();               
        rec.setWidth(300);
        rec.setHeight(300);
        rec.setFill(Color.GRAY);
               
        forPicture.getChildren().add(rec);
        onTheRight.getChildren().add(forPicture);
        
        onTheRight.setSpacing(20);
        onTheRight.setPadding(new Insets(40, 40, 40, 40));
        
        // button to upload picture
        Button btn = new Button("Upload");
        onTheRight.getChildren().add( btn);
        
        
        btn.setOnAction((ActionEvent e)->{
        FileChooser fileChooser = new FileChooser ();
        File file = fileChooser.showOpenDialog(null);
        
        ImageView myImageView = new ImageView();       
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
            Image importImage = SwingFXUtils.toFXImage(bufferedImage,null);           
            myImageView.setImage(importImage);           
            rec.setFill(new ImagePattern(importImage));
           
        }catch(IOException ex){
            System.out.println(ex);
        }
        });
        
        
// on the left
        onTheLeft.setSpacing(10);
        onTheLeft.setPadding(new Insets(10, 10, 10, 10));
        
        
         
        // Border color
        Label bdcl = new Label ("Border color");
        ToggleGroup borderColor = new ToggleGroup();
        RadioButton green = new RadioButton ("green");
        RadioButton yellow = new RadioButton ("yellow");
        RadioButton Orange = new RadioButton ("orange");
        borderColor.getToggles().addAll(green ,yellow,Orange);
        
        
        HBox pane3 = new HBox();
        pane3.setPadding(new Insets(20,20,20,20));
        pane3.setSpacing(10);
        pane3.getChildren().addAll(bdcl,green,yellow,Orange);
        
         green.setOnAction(e->{           
            if (green.isSelected())
                rec.setStroke(Color.GREEN);                    
        });
        
        yellow.setOnAction(e->{           
            if (yellow.isSelected())
                rec.setStroke(Color.YELLOW);                 
        });
        
         Orange.setOnAction(e->{           
            if (Orange.isSelected())
                rec.setStroke(Color.ORANGE);                    
        });
         
        onTheLeft.getChildren().add (pane3);
        
                //Border Size
        Label bdsz = new Label ("Border Size");
        Button btnsz = new Button ("apply");
        TextField borderSize = new TextField();
        Alert errorAlert = new Alert(AlertType.ERROR);
        Label Flag1 = new Label();
        Flag1.setStyle("-fx-text-fill:red");
        
        HBox pane2 = new HBox();
        pane2.setPadding(new Insets(20,20,20,20));
        pane2.setSpacing(10);
        pane2.getChildren().addAll(bdsz,borderSize,btnsz);
        
        btnsz.setOnAction(e->{
              try{                   
                   double size = Double.parseDouble(borderSize.getText());
                   if(size > 0 && size < 100){
                    rec.setStrokeWidth(size);
                    Flag1.setText("");
                   }
                   else
                       Flag1.setText("between 1-100");
                    } catch (Exception y){
                    Flag1.setText("invalid number");
                    }
                       
        });
        
        onTheLeft.getChildren().addAll(pane2,Flag1);
        
        //Add text
        Label addtx = new Label ("Add text"); 
        TextField addText = new TextField();
                
        VBox pane4 = new VBox();
        pane4.setPadding(new Insets(0,0,0,20));
        pane4.setSpacing(10);
        pane4.getChildren().addAll(addtx,addText);
        
        onTheLeft.getChildren().add (pane4);
        
        // text position
        ToggleGroup textPosition = new ToggleGroup();
        RadioButton top = new RadioButton ("top");
        RadioButton center= new RadioButton ("center");
        RadioButton bottom = new RadioButton ("bottom");
        textPosition.getToggles().addAll(top ,center, bottom);
        
        
        HBox pane5 = new HBox();
        pane5.setPadding(new Insets(0,0,0,20));
        pane5.setSpacing(10);
        pane5.getChildren().addAll(top,center,bottom);
        onTheLeft.getChildren().add (pane5);
        
        

        BorderPane textPaneCenter = new BorderPane();
        
        top.setOnAction(e->{ 
            if (top.isSelected()){
        textPaneCenter.getChildren().clear();
         textPaneCenter.setPadding(new Insets(20,20,20,20));  
          text = new Label(addText.getText());

          textPaneCenter.setTop(text);
          forPicture.getChildren().add(textPaneCenter);   
            }
        });
        
        center.setOnAction(e->{
            if (center.isSelected()){
          textPaneCenter.getChildren().clear();  
          
          textPaneCenter.setPadding(new Insets(20,20,20,20));
          text = new Label(addText.getText());
        textPaneCenter.setCenter(text);
        forPicture.getChildren().add(textPaneCenter);   
            }
        });
        
        bottom.setOnAction(e->{ 
            if (bottom.isSelected()){
        textPaneCenter.getChildren().clear();
        textPaneCenter.setPadding(new Insets(20,20,20,20));  
          text = new Label(addText.getText());

          textPaneCenter.setBottom(text);
          forPicture.getChildren().add(textPaneCenter);   
            }
        });

        
       
        //Font size
        Label fntsz = new Label ("Font size (1-100)");
        TextField fontSize = new TextField();
        Button fntsize = new Button ("apply");
        
        fntsize.setOnAction(e->{
            try{
            size = Double.parseDouble(fontSize.getText());
             if(size > 0 && size < 100)           
               text.setFont(Font.font(size));
             
            }catch (Exception y){
                    errorAlert.setContentText("Enter number");
                    errorAlert.showAndWait();
                    }
        });


        
        
        //Font style
        Label fntst = new Label ("Font style");
        
        ToggleGroup fontStyle = new ToggleGroup();
        RadioButton bold = new RadioButton ("bold");
        RadioButton italic= new RadioButton ("italic");
        RadioButton regular = new RadioButton ("regular");
        fontStyle.getToggles().addAll(bold ,italic,regular);
        
        bold.setOnAction(e->{  
            if (bold.isSelected()){
           text.setFont(Font.font("Verdana",FontWeight.BOLD,size));
            }
        });
        
        italic.setOnAction(e->{      
            if (italic.isSelected()){
           text.setFont(Font.font("Verdana",FontPosture.ITALIC,size));
            }
        });
        
        regular.setOnAction(e->{      
          if (regular.isSelected()){
           text.setFont(Font.font(size));
          }
        });
        
        // Font color
        Label fntcl = new Label ("Font color");
        
        ToggleGroup fontColor = new ToggleGroup();
        RadioButton white = new RadioButton ("white");
        RadioButton black = new RadioButton ("black");
        RadioButton blue = new RadioButton ("blue");
        fontColor.getToggles().addAll(white ,black,blue );
        
        white.setOnAction(e->{           
            if (white.isSelected())
                text.setStyle("-fx-text-fill: white");                    
        });
        
        black.setOnAction(e->{           
            if (black.isSelected())
                text.setStyle("-fx-text-fill: black");                  
        });
        
        blue.setOnAction(e->{           
            if (blue.isSelected())
                text.setStyle("-fx-text-fill: blue");                     
        });
        
        
        //Resize picture
        Label resz = new Label ("Resize");
        TextField height = new TextField();
        TextField width = new TextField();
        Label Height = new Label ("height");
        Label Width = new Label ("width");
        Button btnSetsz = new Button ("apply");
        
        Label Flag = new Label ();
        Flag.setStyle("-fx-text-fill:red");
        
         btnSetsz.setOnAction(e->{
             try{
             double w = Double.parseDouble(width.getText());
             double h = Double.parseDouble(height.getText());
             if(w <= 300 && w >0){
             rec.setWidth(w);
             Flag.setText("");
             }
             else
                 Flag.setText("width must be between 1-300");
             
             if(h <= 300 && h >0){
             rec.setHeight(h);   
             Flag.setText("");
             }
             else
                Flag.setText("height must be between 1-300");
                 
             }catch(Exception y){
                  Flag.setText("invalid number");
             }
         });
        
        GridPane pane6= new GridPane();
        pane6.add(fntsz,0,0);
        pane6.add(fontSize,2,0);
        pane6.add(fntsize,3,0);

        
        pane6.add(fntst,0,1,1,1);
        pane6.add(bold,2,1);
        pane6.add(italic,3,1);
        pane6.add(regular,4,1);
        
        pane6.add(fntcl,0,2,1,2);
        pane6.add(white,2,2);
        pane6.add(black,3,2);
        pane6.add(blue,4,2);
        
        pane6.add(resz,0,3,1,3);
        pane6.add(height,2,5);
        pane6.add(Height,3,5);
        pane6.add(width,2,6);       
        pane6.add(Width,3,6);
        pane6.add(btnSetsz,4,6);
       
        pane6.add(Flag,1,7,4,7);
        pane6.setHgap(5.5);
        pane6.setVgap(5.5);
        
        onTheLeft.getChildren().add(pane6);
        
        // Stage
        frame.getChildren().addAll(onTheLeft,onTheRight);
        root.getChildren().add(frame);
        Scene scene = new Scene(root,800,500);     
        primaryStage.setTitle("Picture frame design");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
  
          public static void main(String[] args) {
        launch(args);
    }
}
