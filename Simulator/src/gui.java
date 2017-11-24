import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class gui extends Application {

    Button button;
    private BorderPane layout;
    private TextField CommandInput;
    private HBox controls;
    private HBox Input;
    static protected TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("OS 312");
        
        button = new Button();
        button.setText("Click Me");
        StackPane center = new StackPane();
        center.getChildren().add(button);
        
        button.setOnAction(e -> System.out.println("Test"));
        
        CommandInput = new TextField();
      
        
        HBox Input = new HBox();
        Input.getChildren().add(CommandInput);
        
        layout = new BorderPane();
        layout.setTop(center);
        layout.setBottom(Input);
       
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   
   
    
    
}