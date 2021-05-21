package santaellamorenofrancisco.Clicker.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import santaellamorenofrancisco.Clicker.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import santaellamorenofrancisco.Clicker.Utils.Conexion;

public class UsersDAO extends Users {

	private final static String GETBYID = "SELECT id,nickname,email,password";
	private final static String INSERT = "INSERT INTO users (id,nickname,email,password,clickcoins) "
			+ "VALUES (?,?,?,?,?) ";
	
	private final static String INSERTUPDATE = "INSERT INTO users (id,nickname,email,password,clickcoins)"
			+ " VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE clickcoins=?";
	private final static String DELETE = "DELETE FROM Users WHERE id=?";
	private final static String SELECTBYNICKNAME = "SELECT id,nickname,email,password,clickcoins FROM Users WHERE nickname LIKE ?";
	private final static String SELECTUSERSORDERCLICKS = "SELECT nickname,clickcoins FROM Users ORDER BY clickcoins desc ";
	private final static String GETMAXID = "SELECT max(id) from users";
	private final static String SELECTNICKANDPASS = "SELECT nickname,password FROM users WHERE nickname LIKE ? and password LIKE ?";
	private final static String WINNEWACHIEVEMENT = "INSERT INTO `users_achievements` (`id_users`, `id_achievements`) VALUES (?, ?);";
	private final static String COUNTACHIEVEMENTS = "SELECT id_achievements, count(*) cuenta FROM users_achievements WHERE id_users = ? ";
	private final static String SELECTNICK = "SELECT nickname FROM users WHERE nickname LIKE ?";
	private final static String UPDATENICK = "INSERT INTO `users`(`id`, `nickname`, `email`, `password`, `clickcoins`) "
			+ "VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE nickname = ?";
	private final static String SELECTNAMEACHIEVEMENTS = "SELECT achievements.name FROM achievements,users_achievements, users WHERE users.id = users_achievements.id_users "
			+ "AND achievements.id = users_achievements.id_achievements AND users_achievements.id_users = ?";
	
	
	
	public UsersDAO(int id,String nickname, String email, String password,int clickcoins, List<Achievements> myachievements) {
		super(id, nickname, email, password,clickcoins, myachievements);
	}

	public UsersDAO(String nickname, String email, String password) {
		super(nickname, email, password);
	}

	public UsersDAO(String nickname, String password) {
		super(nickname, password);
	}

	public UsersDAO() {
		super("UsuarioRandom", "Random@gmail.com", "1234");
	}

	/// DAO
	public UsersDAO(Users u) {
		this.id = u.id;
		this.nickname = u.nickname;
		this.email = u.email;
		this.password = u.password;

	}

	public UsersDAO(int id) {
		// getByID from mariaDB
		// Conexion
		super();
		Connection con=Conexion.getConnection();
		// Stament
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + "'" + id + "'";
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nickname = rs.getString("nickname");
					this.email = rs.getString("email");
					this.password = rs.getString(password);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int guardar(String email, String nickname, String password) {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERT);
				q.setInt(1, this.id);
				q.setString(2, this.email);
				q.setString(3, this.nickname);
				q.setString(4, this.password);
				q.setInt(5, this.clickcoins);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	
	public int guardar() {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTUPDATE);
				q.setInt(1, this.id);
				q.setString(2, this.nickname);
				q.setString(3, this.email);
				q.setString(4, this.password);
				q.setInt(5, this.clickcoins);
				q.setInt(6, this.clickcoins);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public int changeNickname() {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(UPDATENICK);
				q.setInt(1, this.id);
				q.setString(2, this.nickname);
				q.setString(3, this.email);
				q.setString(4, this.password);
				q.setInt(5, this.clickcoins);
				q.setString(6, this.nickname);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	
	
	
	
	
	public int eliminar() {
		int rs=0;
		Connection con=Conexion.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs =q.executeUpdate();
				this.id=-1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public static int eliminar2(int i) {
		int rs=0;
		Connection con=Conexion.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1, i);
				rs =q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}


	public static List<Users> searchByNickname(String nombre) {
		List<Users> result = new ArrayList<Users>();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNICKNAME);
				q.setString(1,nombre);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Users u = new Users();
					u.setId(rs.getInt("id"));
					u.setNickname(rs.getString("nickname"));
					u.setEmail(rs.getString("email"));
					u.setPassword("password");
					result.add(u);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	public static UsersDAO searchUsersByNickname(String nombre) {
		UsersDAO result = new UsersDAO();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNICKNAME);
				q.setString(1,nombre);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					UsersDAO u = new UsersDAO();
					u.setId(rs.getInt("id"));
					u.setNickname(rs.getString("nickname"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setClickcoins(rs.getInt("clickcoins"));
					result = u;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	

	public static List<Users> searchAllUsers() {
		List<Users> result = FXCollections.observableArrayList();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTUSERSORDERCLICKS);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Users u = new Users();
					u.setClickcoins(rs.getInt("clickcoins"));
					u.setNickname(rs.getString("nickname"));
					
					
					result.add(u);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public static int getmaxid() {
		int a = 0;
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETMAXID);
				
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					a = rs.getInt(1);
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return a;

	}

	public static boolean userExist(String nickname, String password) {
		boolean a = false;
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTNICKANDPASS);
				q.setString(1, nickname);
				q.setString(2, password);
				ResultSet rs = q.executeQuery();	
				while (rs.next()) {
					if (rs.getString("nickname")!=null) {
						Users u = new Users();
						u.setNickname(rs.getString("nickname"));
						u.setPassword(rs.getString("password"));
						UsersDAO u1 = new UsersDAO(u);
						u= u1;
						a = true;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return a;
	}
	
	public static boolean userExist(String nickname) {
		boolean a = false;
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTNICK);
				q.setString(1, nickname);
				ResultSet rs = q.executeQuery();	
				while (rs.next()) {
					if (rs.getString("nickname")!=null) {
						Users u = new Users();
						u.setNickname(rs.getString("nickname"));
						UsersDAO u1 = new UsersDAO(u);
						u= u1;
						a = true;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return a;
	}
	
	


	public static  void newAchievementForUser(Users u, Achievements a) {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				
				PreparedStatement q = con.prepareStatement(WINNEWACHIEVEMENT);
				q.setInt(1, u.getId());
				q.setInt(2, a.getId());
				rs = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static int countAchievements(UsersDAO u1) {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int a = 0;
		AchievementsDAO ac = new AchievementsDAO();
		
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				
				PreparedStatement q = con.prepareStatement(COUNTACHIEVEMENTS);
				
				q.setInt(1, u1.getId());
				ResultSet rs = q.executeQuery();
				if(rs.next()) {
					a = rs.getInt("cuenta");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return a;
	}
	
	public static List<Achievements> myAchievements(int id) {
		List<Achievements> result = FXCollections.observableArrayList();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTNAMEACHIEVEMENTS);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while(rs.next()) {
				AchievementsDAO ac = new AchievementsDAO();
					ac.setName(rs.getString("name"));
					result.add(ac);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	
	
	
	
}
