package santaellamorenofrancisco.Clicker;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import santaellamorenofrancisco.Clicker.Modelo.Users;
import santaellamorenofrancisco.Clicker.Modelo.UsersDAO;

public class RankingController {
	
	@FXML
	private ImageView i1;
	
	@FXML
	private ImageView i2;
	
	@FXML
	private ImageView i3;
	
	
	@FXML
	private TableView<Users> t1;

	@FXML
	private TableColumn<Users, String> tc1;

	@FXML
	private TableColumn<Users, Integer> tc2;
	
	
	/*
	 * Al iniciarlizar esta ventana carga en una columna el nickname de los usuarios
	 * y en otra carga los clickcoins que tienen dichos usuarios.
	 * Esta lista es mostrada por pantalla.
	 *  
	 */
	@FXML
	public void initialize() {
		
		tc1.setCellValueFactory(new PropertyValueFactory<Users, String>("nickname"));
		tc2.setCellValueFactory(new PropertyValueFactory<Users, Integer>("clickcoins"));
		UsersDAO u = new UsersDAO();
		ObservableList<Users> items = FXCollections.observableArrayList(u.searchAllUsers());
		
		t1.setItems(items);
		
		
	}
	

	@FXML
	private void switchToPerfil() throws IOException {
		App.setRoot("Perfil");
	}
	
	@FXML
	private void switchToGame() throws IOException {
		App.setRoot("Game");
	}
	

	@FXML
	private void switchToRanking() throws IOException {
		App.setRoot("Ranking");
	}
	
	
	
}
