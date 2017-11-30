package GUI;


import javafx.application.Application;
import Components.Scheduler;
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

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JTable;

import Components.CommandLine;
import Components.CommandLine;
import Components.Process;
import ParseText.ParseText;
public class gui extends Application {

	
	 private final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
	 private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
	
    Button button;
    private BorderPane layout;
    public TextField CommandInput;
    static protected TextArea textArea;
    private ArrayList<String>tokens = new ArrayList<>();
    public ArrayList<String> input;
    String temp;
    
    // Creates a table of ready process
    TableView<Process> readyTable;
    TableView<Process> waitingTable;
   
    public static void main(String[] args) {
        launch(args);
    }
    
    public TextField getInput(String Input){
    	return this.CommandInput;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	        primaryStage.setTitle("OS 312");
        
    	        
    	        
      //Ready Columns
        TableColumn<Process, String> nameColumn = new TableColumn<>("Process");
        //nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("processName"));
        
        TableColumn<Process, String> memColumn = new TableColumn<>("Memory Size");
       // memColumn.setMinWidth(75);
        memColumn.setCellValueFactory(new PropertyValueFactory<>("processMemory"));
        
        TableColumn<Process, Double> arrivalColumn = new TableColumn<>("Arrival Time");
       // arrivalColumn.setMinWidth(75);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        
        TableColumn<Process, String> runColumn = new TableColumn<>("Run Time");
        runColumn.setMinWidth(75);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("calcTime"));
        
        
        //Waiting Columns
        TableColumn<Process, String> name2Column = new TableColumn<>("Program");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("processName"));
        
        TableColumn<Process, String> mem2Column = new TableColumn<>("Memory Size");
        mem2Column.setMinWidth(75);
        mem2Column.setCellValueFactory(new PropertyValueFactory<>("processMemory"));
        
        TableColumn<Process, Double> arrival2Column = new TableColumn<>("Arrival Time");
        arrivalColumn.setMinWidth(75);
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        
        TableColumn<Process, String> run2Column = new TableColumn<>("Run Time");
        runColumn.setMinWidth(75);
        runColumn.setCellValueFactory(new PropertyValueFactory<>("calcTime"));
        
        
        
        readyTable = new TableView<>();
        this.readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        readyTable.setItems(readyProcessList);
        readyTable.getColumns().addAll(nameColumn, memColumn, arrivalColumn, runColumn);
        
        waitingTable = new TableView<>();
        waitingTable.setItems(waitingProcessList);
        waitingTable.getColumns().addAll(nameColumn, memColumn, arrivalColumn, runColumn);
        
        
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        textArea.setPrefRowCount(3);
        textArea.setPrefColumnCount(50);
        textArea.autosize();
        
        this.CommandInput = new TextField();
        this.button = new Button();
        button.setText("Submit");
        
        //Action dependent on user input
        button.setOnAction(e -> {
        	 boolean valid = true;
             if(!CommandInput.getText().trim().equals(""))
                 valid = CommandLine.storeInputs(CommandInput.getText());
             CommandInput.clear();
             if(!valid){
            	 GuiPrompt.println("Invalid Command!");
             }
        }
        		);
        
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
        
        startSim();
    }
    
    private void startSim() {
    	while(true) {
    		this.readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));		
    	}
    }
    
    
    
}