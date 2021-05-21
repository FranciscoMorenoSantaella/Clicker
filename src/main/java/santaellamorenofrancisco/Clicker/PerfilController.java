package santaellamorenofrancisco.Clicker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import santaellamorenofrancisco.Clicker.Modelo.Achievements;
import santaellamorenofrancisco.Clicker.Modelo.AchievementsDAO;
import santaellamorenofrancisco.Clicker.Modelo.Users;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;
import santaellamorenofrancisco.Clicker.Modelo.UsersHolder;

public class PerfilController {
	@FXML
	private ImageView i1;

	@FXML
	private ImageView i2;

	@FXML
	private Text txtnickname;

	@FXML
	private Text numAchievements;

	@FXML
	private JFXButton deleteUser;

	@FXML
	private JFXButton changeNickname;

	@FXML
	private TableView t1;

	@FXML
	private TableColumn tc1;

	UsersHolder holder = UsersHolder.getInstance();
	UsersDAO u = holder.getUser();

	public void initialize() {
		UsersHolder holder = UsersHolder.getInstance();
		UsersDAO u = holder.getUser();
		UsersDAO u1 = getDataUsers(u.getNickname());
		txtnickname.setText(u1.getNickname());
		numAchievements.setText(UsersDAO.countAchievements(u1) + "");
		tc1.setCellValueFactory(new PropertyValueFactory<Achievements, String>("name"));
		
		
		ObservableList<Achievements> items = FXCollections.observableArrayList(UsersDAO.myAchievements(u1.getId()));
		
		t1.setItems(items);
		

	}

	@FXML
	public void deleteAccount() throws IOException {

		UsersDAO u1 = getDataUsers(u.getNickname());
		u1.eliminar();
		switchToLogin();
	}

	@FXML
	public void newNickname() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CambiarNick.fxml"));
		Parent modal;

		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setResizable(false);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();

		} catch (IOException ex) {
			Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public UsersDAO getDataUsers(String nickname) {

		UsersDAO a = new UsersDAO(UsersDAO.searchUsersByNickname(nickname));
		return a;

	}

	@FXML
	private void switchToGame() throws IOException {
		App.setRoot("Game");
	}

	@FXML
	private void switchToRanking() throws IOException {
		App.setRoot("Ranking");
	}

	@FXML
	private void switchToLogin() throws IOException {
		App.setRoot("Login");
	}

}
