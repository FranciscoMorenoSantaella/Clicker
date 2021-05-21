package santaellamorenofrancisco.Clicker;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import santaellamorenofrancisco.Clicker.Modelo.Achievements;
import santaellamorenofrancisco.Clicker.Modelo.AchievementsDAO;
import santaellamorenofrancisco.Clicker.Modelo.Users;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

	 private static Scene scene;
	 protected static Stage rootstage;
	   
	    
	    @Override
	    public void start(Stage stage) throws IOException {
	    	scene = new Scene(loadFXML("login"), 1280, 720);
	        stage.setScene(scene);
	        stage.setResizable(false);
	     
	        stage.show();
	 
	    }
	    
	    static void setRoot(String fxml) throws IOException {
	        scene.setRoot(loadFXML(fxml));
	    }

	    
	    protected static Parent loadFXML(String fxml) throws IOException {
	        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
	        return fxmlLoader.load();
	    }

	    public static void main(String[] args) {
	    	
	        launch();
	    }
	    
	    



}