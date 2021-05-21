package santaellamorenofrancisco.Clicker.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Users {
	protected int id;
	protected String nickname;
	protected String email;
	protected String password;
	protected int clickcoins = 0;
	protected List<Achievements> myachievements;

	public Users(int id, String nickname, String email, String password, int clickcoins, List<Achievements>myachievements) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.clickcoins = clickcoins;
		this.myachievements = myachievements;
	}
	
	public Users(int id, String nickname, String email, String password) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}


	public Users(String nickname, String email, String password) {
		super();
		this.id = UsersDAO.getmaxid()+1;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

	public Users(String nickname, String password) {
		super();
		this.id = UsersDAO.getmaxid()+1;
		this.nickname = nickname;
		this.password = password;
	}
	public Users(String nickname, int clickcoins) {
		super();
		this.nickname = nickname;
		this.clickcoins = clickcoins;
	}

	public Users() {
		
		this(-1, "UsuarioRandom", "Random@gmail.com", "1234",-1,new ArrayList<Achievements>());
		
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public int getClickcoins() {
		return clickcoins;
	}

	public void setClickcoins(int clickcoins) {
		this.clickcoins = clickcoins;
	}

	public List<Achievements> getMisusuarios() {
		return myachievements;
	}

	public void setMisusuarios(List<Achievements> misusuarios) {
		this.myachievements = misusuarios;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password
				+ ", clickcoins=" + clickcoins;
	}
	
	
}
