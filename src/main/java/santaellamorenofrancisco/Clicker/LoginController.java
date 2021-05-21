package santaellamorenofrancisco.Clicker;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import santaellamorenofrancisco.Clicker.Modelo.AchievementsDAO;
import santaellamorenofrancisco.Clicker.Modelo.Users;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;
import santaellamorenofrancisco.Clicker.Modelo.UsersHolder;

public class LoginController extends App {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPass;

	@FXML
	private JFXButton logB;

	@FXML
	private JFXButton singUpB;

	private static Scene scene;

	@FXML
	public void initialize() {

	}

	/*
	 * Este metodo comprueba si un usuario existe y si existe inicia sesion en el
	 * programa (Nos lleva a la ventana Game)
	 */
	@FXML
	public void logUser() throws IOException {
		String nickname = this.txtUser.getText();
		String password = this.txtPass.getText();
		UsersDAO us1 = new UsersDAO();
		UsersHolder holder = UsersHolder.getInstance();
		if (us1.userExist(nickname, password) == true) {
			us1 = UsersDAO.searchUsersByNickname(nickname);
			holder.setUserDAO(us1);
			switchToGame();

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Uno de los datos introducidos no es valido");
			alert.showAndWait();
		}
	}

	/*
	 * Es un metodo que cambia a la ventana de Registro
	 */
	@FXML
	private void switchToRegistro() throws IOException {
		App.setRoot("Registro");
	}
	
	/*
	 * Es un metodo que cambia a la ventana Game
	 */
	@FXML
	private void switchToGame() throws IOException {
		App.setRoot("Game");
	}

}
