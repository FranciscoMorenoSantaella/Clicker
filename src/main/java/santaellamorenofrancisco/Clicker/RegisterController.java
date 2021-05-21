package santaellamorenofrancisco.Clicker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;

public class RegisterController {
	
	@FXML
	private JFXButton singB;
	
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField txtPass;

	
	
	/*
	 * Es un metodo que sirve para registrarnos en la aplicacion, comprueba si un usuario existe, 
	 * si existe sale una alerta diciendo que el usuario ya esta en la base de datos
	 * si no existe se guardan los datos en la base de datos.
	 */
	@FXML
	protected void singUser() throws IOException {
		String nickname = this.txtUser.getText();
		String password = this.txtPass.getText();
		String email = this.txtEmail.getText();
		UsersDAO us1 = new UsersDAO(email,nickname,password);
		
		
		if (us1.userExist(nickname, password)==false && validate(email)) {
			us1.guardar(nickname,email,password);
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Parent modal;
			try {
				switchTologin();

			} catch (IOException ex) {
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Este usuario ya existe en la base de datos o email invalido");
			alert.showAndWait();
		}
	}
	/*
	 * Cambia a la ventana login
	 */
	 @FXML
	    private void switchTologin() throws IOException {
	        App.setRoot("login");
	    }
	 		
	 		/*
	 		 * Patron para saber si un email es valido
	 		 */
	 		public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	 		/*
	 		 * Ha este metodo se le pasa un email para comprobar si es valido
	 		 */
			public static boolean validate(String emailStr) {
			        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
			        return matcher.find();
			}
  
}
