package santaellamorenofrancisco.Clicker;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;
import santaellamorenofrancisco.Clicker.Modelo.UsersHolder;

public class ChangeNickController {
	@FXML
	private TextField txt;
	
	@FXML
	private JFXButton confirm;
	
	public void changeNick() throws IOException {
		UsersHolder holder = UsersHolder.getInstance();
		UsersDAO u1 = holder.getUser();
		
		
		if(u1.userExist(txt.getText())==false) {
			u1.setNickname(txt.getText());
			
			u1.changeNickname();
			App.setRoot("Perfil");
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Ya hay un usuario con ese nickname :(");
			alert.showAndWait();

		}
	}
	
	
}
