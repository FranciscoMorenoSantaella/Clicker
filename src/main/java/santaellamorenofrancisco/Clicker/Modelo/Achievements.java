package santaellamorenofrancisco.Clicker.Modelo;
import java.util.List;

import santaellamorenofrancisco.Clicker.Modelo.AchievementsDAO;

public class Achievements {
	protected int id;
	protected String name;
	protected String description;
	protected int passive;
	protected List<Users> myuserslist;
	
	
	
	public Achievements(int id, String name, String description, int passive, List<Users> myuserslist) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.passive = passive;
		this.myuserslist = myuserslist;
	}


	public Achievements(String name, String description, int passive) {
		super();
		this.id = AchievementsDAO.getmaxid()+1;
		this.name = name;
		this.description = description;
		this.passive = passive;
	}


	public Achievements() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPassive() {
		return passive;
	}


	public void setPassive(int passive) {
		this.passive = passive;
	}


	public List<Users> getAchievementsList() {
		return myuserslist;
	}


	public void setAchievementsList(List<Users> myuserslist) {
		this.myuserslist = myuserslist;
	}


	@Override
	public String toString() {
		return "Achievements [id=" + id + ", name=" + name + ", description=" + description + ", passive=" + passive
				+ "]";
	}
	
	
	
	
	
	
	
}
