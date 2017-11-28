package GUI;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JTable;
import Components.CommandLine;
import Components.Process;

public class gui extends Application {

	
	 private final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
	 private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
	
    Button button;
    private BorderPane layout;
    private TextField CommandInput;
    static protected TextArea textArea;
    
    // Creates a table of ready process
    TableView<testTable> readyTable;
    TableView<testTable> waitingTable;
   
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	        primaryStage.setTitle("OS 312");
        
    	        
    	        
      //Ready Columns
        TableColumn<testTable, String> nameColumn = new TableColumn<>("Program");
        //nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<testTable, String> memColumn = new TableColumn<>("Memory Size");
       // memColumn.setMinWidth(75);
        memColumn.setCellValueFactory(new PropertyValueFactory<>("run"));
        
        TableColumn<testTable, Double> arrivalColumn = new TableColumn<>("Arrival Time");
       // arrivalColumn.setMinWidth(75);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        
        TableColumn<testTable, String> runColumn = new TableColumn<>("Run Time");
        runColumn.setMinWidth(75);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("run"));
        
        
        //Waiting Columns
        TableColumn<testTable, String> name2Column = new TableColumn<>("Program");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<testTable, String> mem2Column = new TableColumn<>("Memory Size");
        mem2Column.setMinWidth(75);
        mem2Column.setCellValueFactory(new PropertyValueFactory<>("run"));
        
        TableColumn<testTable, Double> arrival2Column = new TableColumn<>("Arrival Time");
        arrivalColumn.setMinWidth(75);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        
        TableColumn<testTable, String> run2Column = new TableColumn<>("Run Time");
        runColumn.setMinWidth(75);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("run"));
        
        
        
        readyTable = new TableView<>();
        ObservableList<Process> processList = FXCollections.observableArrayList();
        readyTable.setItems(getTestTable());
        readyTable.getColumns().addAll(nameColumn, memColumn, arrivalColumn, runColumn);
        
        waitingTable = new TableView<>();
        waitingTable.setItems(getTestTable());
        waitingTable.getColumns().addAll(nameColumn, memColumn, arrivalColumn, runColumn);
        
        
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        textArea.setPrefRowCount(3);
        textArea.setPrefColumnCount(50);
        textArea.autosize();
        
        CommandInput = new TextField();
        button = new Button();
        button.setText("Submit");
        button.setOnAction(e -> CommandLine.Commands(CommandInput) );
        
        HBox Input = new HBox();
        Input.getChildren().addAll(CommandInput, button);
        
        VBox waitingBox = new VBox();
        waitingBox.setSpacing(10);
        Text waitingTitle = new Text("Waiting Queue");
        waitingTitle.setStyle("-fx-font-size: 18px");
        waitingBox.getChildren().addAll(waitingTitle, waitingTable);

        VBox readyBox = new VBox();
        readyBox.setSpacing(10);
        Text readyTitle = new Text("Ready Queue");
        readyTitle.setStyle("-fx-font-size: 18px");
        readyBox.getChildren().addAll(readyTitle, readyTable);

        HBox lowerBox = new HBox();
        lowerBox.setSpacing(10);
        lowerBox.setPadding(new Insets(10, 10, 10, 10));
        lowerBox.getChildren().addAll(waitingBox, readyBox);

        VBox upperBox = new VBox();
        upperBox.getChildren().addAll(Input, textArea);
                 
        layout = new BorderPane();
        layout.setTop(upperBox);
        layout.setBottom(lowerBox);
       
        Scene scene = new Scene(layout, 1000, 600);
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
    
    
    
}