package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		//try {
			
			//Starting up Server and Client
			PrimeNumberClient client = new PrimeNumberClient();
			PrimeNumberServer server = new PrimeNumberServer();
			server.start();
			client.connect();
			
			//Setup for Scene 1
			Text enterNumberText = new Text("Test Number");
			TextField inputText = new TextField();
			Button calculateButton = new Button("Calculate");
			BorderPane inputLayout = new BorderPane();
			inputLayout.setPadding(new Insets(100,100,100,100));
			inputLayout.setBottom(calculateButton);
			inputLayout.setTop(enterNumberText);
			inputLayout.setCenter(inputText);
			BorderPane.setAlignment(calculateButton, Pos.TOP_CENTER);
			BorderPane.setAlignment(inputText, Pos.TOP_CENTER);
			BorderPane.setAlignment(enterNumberText, Pos.TOP_CENTER);
			Scene scene = new Scene(inputLayout,400,400);
			
			
			
			//Setup for Scene 2
			Text outputText = new Text("Something went wrong");
			Button restartButton = new Button("Restart");
			BorderPane outputLayout = new BorderPane();
			outputLayout.setPadding(new Insets(100,100,100,100));
			outputLayout.setCenter(outputText);
			outputLayout.setBottom(restartButton);
			BorderPane.setAlignment(outputText, Pos.CENTER);
			BorderPane.setAlignment(restartButton, Pos.TOP_CENTER);
			
			Scene scene2 = new Scene(outputLayout,400,400);
			
			
			
			//Adding Stage title; Displaying Scene One
			primaryStage.setTitle("Prime Number Check");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
			calculateButton.setOnAction(actionEvent ->  {
			   
				
				try {
					outputText.setText(client.calculate(Double.parseDouble(inputText.getText())));
				} catch (NumberFormatException e) {
					
					Alert nanAlert = new Alert(AlertType.ERROR);
					nanAlert.setTitle("Error");
					nanAlert.setHeaderText("Not a Number Error");
					nanAlert.setContentText("Please use a number!");

					nanAlert.showAndWait();
					
				}
				
				primaryStage.setScene(scene2);
				
				
				
			});
			
			
			 restartButton.setOnAction(actionEvent ->  {
			   
				primaryStage.setScene(scene);
				
			});
			
			
			  primaryStage.setOnCloseRequest((ae) -> {
		            Platform.exit();
		            System.exit(0);
		            server.close();
		            client.close();
		        });
			
		//} catch(Exception e) {
			//e.printStackTrace();
		//}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
