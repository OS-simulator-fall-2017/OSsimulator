
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JTable;

public class gui extends Application {

    Button button;
    private BorderPane layout;
    private TextField CommandInput;
    static protected TextArea textArea;
    
    TableView<testTable> table;
   
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("OS 312");
        
      //Name column
        TableColumn<testTable, String> nameColumn = new TableColumn<>("Job");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<testTable, Double> arrivalColumn = new TableColumn<>("Arrival Time");
        arrivalColumn.setMinWidth(75);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        
        TableColumn<testTable, String> runColumn = new TableColumn<>("Run Time");
        runColumn.setMinWidth(75);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("run"));
        
        table = new TableView<>();
        table.setItems(getTestTable());
        table.getColumns().addAll(nameColumn, arrivalColumn, runColumn);
        
        button = new Button();
        button.setText("Submit");
        VBox center = new VBox();
        center.getChildren().add(table);
        
        button.setOnAction(e -> Commands(CommandInput) );
        
        CommandInput = new TextField();
          
        HBox Input = new HBox();
        Input.getChildren().addAll(CommandInput, button);
                 
        layout = new BorderPane();
        layout.setTop(Input);
        layout.setBottom(center);
       
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ObservableList<testTable> getTestTable(){
        ObservableList<testTable> jobs = FXCollections.observableArrayList();
       jobs.add(new testTable("text", 859.00, 20));
        jobs.add(new testTable("test", 2.49, 198));
        jobs.add(new testTable("idk", 99.00, 74));
        jobs.add(new testTable("paintballs", 19.99, 12));
        jobs.add(new testTable("Wrecked", 1.49, 856));
        return jobs;
    }
    
    private boolean Commands(TextField input) {
    		 if(input.getText().equals("proc"))
    	         return true;
    		 if(input.getText().equals("mem"))
    			 return true;
    		 if(input.getText().equals("load"))
    			 return true;    
    		 if(input.getText().equals("exe"))
    			 return true;
    		 if(input.getText().equals("reset"))
    			 return true;
    		 if(input.getText().equals("exit"))
    			 return true;
    	System.out.println("Error: not a proper command");
    	return false;
    }
    
    
}