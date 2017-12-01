package GUI;


import javafx.application.Application;
import Components.OperatingSystem;
import javafx.animation.AnimationTimer;
import Components.Scheduler;
import Components.Simulator;
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

	 private  TableView<Process> readyTable;
	  private TableView<Process> waitingTable;
	
	 private final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
	 private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
	
	OperatingSystem os = new OperatingSystem();
    Button button;
    private BorderPane layout;
    public static TextField CommandInput;
    static protected TextArea textArea;
    private ArrayList<String>tokens = new ArrayList<>();
    public ArrayList<String> input;
    String temp;
    private Stage window;
   
   
   
    public static void main(String[] args) {
        launch(args);
    }
    
    public TextField getInput(String Input){
    	return this.CommandInput;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	window = primaryStage;
    	window.setTitle("OS 312");
    	//primaryStage.setTitle("OS 312");
        
    	        
    	        
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
        runColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));
        
        
        //Waiting Columns
        TableColumn<Process, String> name2Column = new TableColumn<>("Program");
       // nameColumn.setMinWidth(150);
        name2Column.setCellValueFactory(new PropertyValueFactory<>("processName"));
        
        TableColumn<Process, String> mem2Column = new TableColumn<>("Memory Size");
        //mem2Column.setMinWidth(75);
        mem2Column.setCellValueFactory(new PropertyValueFactory<>("processMemory"));
        
        TableColumn<Process, Double> arrival2Column = new TableColumn<>("Arrival Time");
       // arrivalColumn.setMinWidth(75);
        arrival2Column.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        
        TableColumn<Process, String> run2Column = new TableColumn<>("Run Time");
        //runColumn.setMinWidth(75);
        run2Column.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));
        
        
        
        readyTable = new TableView<>();
        readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        readyTable.setItems(readyProcessList);
        readyTable.getColumns().addAll(nameColumn, memColumn, arrivalColumn, runColumn);
        
        waitingTable = new TableView<>();
        waitingProcessList.setAll(Scheduler.getWaitingQueue().stream().collect(Collectors.toList()));
        waitingTable.setItems(waitingProcessList);
        waitingTable.getColumns().addAll(name2Column, mem2Column, arrival2Column, run2Column);
        
        
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
             if(!CommandInput.getText().trim().equals(null))
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
        waitingTitle.setStyle("-fx-font-size: 15px");
        waitingBox.getChildren().addAll(waitingTitle, waitingTable);

        VBox readyBox = new VBox();
        readyBox.setSpacing(10);
        Text readyTitle = new Text("Ready Queue");
        readyTitle.setStyle("-fx-font-size: 15px");
        readyBox.getChildren().addAll(readyTitle, readyTable);
                 
        layout = new BorderPane();
        layout.setTop(Input);
        layout.setLeft(waitingBox);
        layout.setRight(readyBox);
        layout.setBottom(textArea);
       
        Scene scene = new Scene(layout, 1000, 600);
        window.setScene(scene);
        window.show();
        
        startSim();
    }
    
    public void startSim() throws InterruptedException {
    	readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        waitingProcessList.setAll(Scheduler.getWaitingQueue().stream().collect(Collectors.toList()));

        final long[] prevTime = {0};
        
        loop();

       new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                if (currentNanoTime > prevTime[0] + 900000000) {
                    try {
                        loop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    prevTime[0] = currentNanoTime + 900000000;
                }
            }
        }.start();
    }
    
    


    public void loop() throws InterruptedException {
    	// Render GUI
        readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        waitingProcessList.setAll(Scheduler.getWaitingQueue().stream().collect(Collectors.toList()));

        
        // Run Simulator
        if (!Simulator.executeSolo && Simulator.executionCycles == 0) {
            return;
        } else {
            Simulator.executionCycles--;
        }
        
        os.run();
        System.out.println("GETS HERE");
    }
    
}