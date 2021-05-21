package santaellamorenofrancisco.Clicker.Modelo;


public class UsersHolder {

	  private UsersDAO users;
	  private final static UsersHolder INSTANCE = new UsersHolder();

	  private UsersHolder() {
	  }

	  public static UsersHolder getInstance() {
	    return INSTANCE;
	  }

	  public void setUserDAO(UsersDAO u) {
	    this.users = u;
	  }

	  public UsersDAO getUser() {
	    return this.users;

	  }
}
