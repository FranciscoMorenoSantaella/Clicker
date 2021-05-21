package santaellamorenofrancisco.Clicker;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import santaellamorenofrancisco.Clicker.Modelo.Achievements;
import santaellamorenofrancisco.Clicker.Modelo.AchievementsDAO;
import santaellamorenofrancisco.Clicker.Modelo.Users;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;
import santaellamorenofrancisco.Clicker.Modelo.UsersHolder;

public class GameController {
	@FXML
	private Label l1;

	@FXML
	private JFXButton clickB;

	@FXML
	private ImageView iv;
	@FXML
	private ImageView iv2;

	@FXML
	private ImageView iv3;

	@FXML
	private Text txt;

	@FXML
	private Text txtnickname;
	
	@FXML
	private JFXButton save;

	/*
	 * Recibimos los datos del usuario introducidos en el login y los inicializamos
	 * en un nuevo usuario mostramos el nombre del usuario por pantalla y mostramos
	 * las clickcoins que tenia ya el usuario guardadas, si no tenia empieza con 0.
	 */
	public void initialize() {
		UsersHolder holder = UsersHolder.getInstance();
		Users u = holder.getUser();
		UsersDAO u1 = getDataUsers(u.getNickname());
		txtnickname.setText(u.getNickname());
		txt.setText(u.getClickcoins() + "");
	}

	public UsersDAO getDataUsers(String nickname) {

		UsersDAO a = new UsersDAO(UsersDAO.searchUsersByNickname(nickname));
		return a;

	}

	UsersHolder holder = UsersHolder.getInstance();
	UsersDAO u1 = holder.getUser();

	/*
	 * Suma 1 clickcoin cada vez que pulsas el boton, también muestra el numero de
	 * clickcoins en la ventana y se ejecuta el metodo winAchievements(Explico dicho
	 * metodo en su comentario)
	 */
	@FXML
	private void Handle() {
		int points = u1.getClickcoins();
		u1.setClickcoins((points += 1));
		String b = points + "";
		txt.setText(b);
		try {
			winAchievements();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Este metodo cambia a la ventana Ranking
	 */
	@FXML
	private void switchToRanking() throws IOException {

		u1.guardar();
		App.setRoot("Ranking");
	}

	/*
	 * Este metodo cambia a la ventana Perfil
	 */
	@FXML
	private void switchToPerfil() throws IOException {
		u1.guardar();
		App.setRoot("Perfil");
	}
	
	
	/*
	 * Este metodo comprueba el numero de clickcoins del jugador 
	 * si coincide con alguno de los numero del switch dentro del metodo
	 * se le desbloqueara un logro al jugador
	 */
	private void winAchievements() throws IOException {
		Achievements a = new AchievementsDAO();
		

		int num = u1.getClickcoins();
		

		switch (num) {
		case 25:
			a = AchievementsDAO.searchAchievementByName("a quarter past");
			UsersDAO.newAchievementForUser(u1, a);
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Logro1.fxml"));
			Parent modal;
			try {
				modal = fxmlLoader.load();
				Stage modalStage = new Stage();
				modalStage.setTitle(a.getDescription());
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.initOwner(App.rootstage);
				Scene modalScene = new Scene(modal);
				modalStage.setResizable(false);
				modalStage.setScene(modalScene);
				modalStage.show();
				esperar(modalStage);
			} catch (IOException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			}
			break;
		case 50:
			a = AchievementsDAO.searchAchievementByName("eclise");
			UsersDAO.newAchievementForUser(u1, a);
			fxmlLoader = new FXMLLoader(App.class.getResource("Logro2.fxml"));
			
			try {
				modal = fxmlLoader.load();
				Stage modalStage = new Stage();
				modalStage.setTitle(a.getDescription());
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.initOwner(App.rootstage);
				Scene modalScene = new Scene(modal);
				modalStage.setResizable(false);
				modalStage.setScene(modalScene);
				modalStage.show();
				esperar(modalStage);
				

			} catch (IOException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			}
			break;

		case 100:
			a = AchievementsDAO.searchAchievementByName("diamante en flex");
			UsersDAO.newAchievementForUser(u1, a);
			fxmlLoader = new FXMLLoader(App.class.getResource("Logro3.fxml"));
			
			try {
				modal = fxmlLoader.load();
				Stage modalStage = new Stage();
				modalStage.setTitle(a.getDescription());
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.initOwner(App.rootstage);
				Scene modalScene = new Scene(modal);
				modalStage.setResizable(false);
				modalStage.setScene(modalScene);
				modalStage.show();
				esperar(modalStage);
				

			} catch (IOException ex) {
				Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
			}
			break;
		}

	}
	
	@FXML
	public void saveScore() {
		u1.guardar();
	}
	public void esperar(Stage modalStage) {
	PauseTransition delay = new PauseTransition(Duration.seconds(5));
	delay.setOnFinished(event -> modalStage.close());
	delay.play();
	}

}
