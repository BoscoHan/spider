package FX;
import java.io.File;

import Crawler.Spider.spider;
import Crawler.Spider.threadClass;
import Crawler.control.ControlClass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight; 
import javafx.stage.Stage; 

public class FX extends Application {

 static boolean testProtocol;
  @Override
  public void start(Stage stage) {
	  
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);
    stage.setScene(scene);
    stage.setTitle("Crawler");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(15, 15, 15, 15));
    grid.setVgap(5);
    grid.setHgap(2);

    scene.setRoot(grid);
    
    
    TextField URLBOX = new TextField();
    URLBOX.setPromptText("Insert Base URL here");
    URLBOX.setPrefColumnCount(20);
    URLBOX.getText();

    	
    TextField NUMBOX = new TextField();
    NUMBOX.setPromptText("Insert Number of Links to Visit");
    NUMBOX.setPrefColumnCount(20);
    NUMBOX.getText();
    
    TextField SEARCHBOX = new TextField();
    SEARCHBOX.setPromptText("Insert Word or Phrase to search");
    SEARCHBOX.setPrefColumnCount(20);
    SEARCHBOX.getText();
    
    final Label labelSelectedDirectory = new Label("Save log file here:");   
    //file Dir chooser to save text file
    Button btnOpenDirectoryChooser = new Button();
    btnOpenDirectoryChooser.setText("Choose directory...");
    btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = 
                    directoryChooser.showDialog(stage);
             
            if(selectedDirectory == null){
                labelSelectedDirectory.setText("No Directory selected");
            }else{
            	ControlClass.setLogLocation(selectedDirectory.getAbsolutePath());
            	labelSelectedDirectory.setText(ControlClass.getLogLocation());
            }
        }
    });
    
    
    
    ToggleSwitch switchSearch = new ToggleSwitch();
    Label urllabel = new Label("Insert base URL only:");
    Label numlinklabel = new Label ("Insert # of links to spider:");
    Label proxyaddresslabel = new Label("Proxy Address:");
    Label proxyportLabel = new Label("Proxy Port:");
    Label proxylabel = new Label("Toggle for word or phrase search: ");
    
    TextField ProxyAddress = new TextField();
    ProxyAddress.setPromptText("Insert Proxy Address here");
    ProxyAddress.setPrefColumnCount(20);
    ProxyAddress.getText();
    
    TextField ProxyPort = new TextField();
    ProxyPort.setPromptText("Insert Proxy Port here");
    ProxyPort.setPrefColumnCount(20);
    ProxyPort.getText();
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    Button button = new Button("Click to Crawl");
    button.setOnAction(e -> {
    	
    	System.out.println(URLBOX.getText() + "");
    	ControlClass.setURLWithoutProtocol(URLBOX.getText());
    	System.out.println(NUMBOX.getText());
    	ControlClass.setNumberOfLinksToVisit(Integer.valueOf(NUMBOX.getText()));
    	ControlClass.setProxyHost(ProxyAddress.getText());
    	//System.out.println(ProxyAddress.getText());
    	ControlClass.setProxyPortNumber(ProxyPort.getText());

    	//System.out.println(ProxyPort.getText());
    	
    	//we are using a thread to execute the methods, pass instance into the thread's constructor:
    	   Thread thread = new Thread(new threadClass());
    	   thread.start();   
    	});
    
    button.setMinWidth(100);
    button.setMinHeight(50);
    
    GridPane.setConstraints(urllabel, 0, 0);
    GridPane.setConstraints(URLBOX, 5, 0);
    GridPane.setConstraints(numlinklabel, 0, 5);
    GridPane.setConstraints(NUMBOX, 5, 5);
    GridPane.setConstraints(proxyaddresslabel, 0, 10);
    GridPane.setConstraints(proxyportLabel, 0, 15);
    GridPane.setConstraints(ProxyAddress, 5, 10);
    GridPane.setConstraints(ProxyPort, 5, 15);
    GridPane.setConstraints(proxylabel, 0, 20);
    GridPane.setConstraints(switchSearch, 5, 20);
    GridPane.setConstraints(SEARCHBOX, 5, 25);
    GridPane.setConstraints(labelSelectedDirectory, 0, 30);
    GridPane.setConstraints(btnOpenDirectoryChooser, 0, 31);
    GridPane.setConstraints(button, 5, 35);

    grid.getChildren().addAll(urllabel, URLBOX, numlinklabel, NUMBOX, proxyaddresslabel, proxyportLabel, ProxyAddress, ProxyPort, proxylabel, switchSearch, SEARCHBOX, labelSelectedDirectory, btnOpenDirectoryChooser, button);

    stage.show();
    
    
    
  }

  public static void disableBaseTextField() {
	  
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
  
}