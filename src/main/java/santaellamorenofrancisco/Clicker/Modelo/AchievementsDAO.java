package santaellamorenofrancisco.Clicker.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import santaellamorenofrancisco.Clicker.Utils.Conexion;

public class AchievementsDAO extends Achievements {
	private final static String GETMAXID = "SELECT max(id) from Achievements";
	private final static String INSERTUPDATE = "INSERT INTO achievements (id,name,description,passive) "
			+ "VALUES (?,?,?,?) ";
	private final static String SELECTBYNAME = "SELECT id,name,description,passive FROM achievements WHERE name LIKE ?";
	private final static String DELETE = "DELETE FROM achievements WHERE id=?";

	public AchievementsDAO(int id, String name, String description, int passive,  List<Users>myuserslist) {
		super(id, name, description, passive,myuserslist);

	}

	public AchievementsDAO(String name, String description, int passive) {
		super(name, description, passive);

	}

	public AchievementsDAO() {
		super("Desconocido", "??????", 666);
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
				q.setString(2, this.name);
				q.setString(3, this.description);
				q.setInt(4, this.passive);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/*
	 * public int addAchievements() { // INSERT o UPDATE // INSERT -> si no existe
	 * OK // En caso de ERROR -> hago un update int rs = 0; Connection con =
	 * Conexion.getConexion();
	 * 
	 * if (con != null) { try { PreparedStatement q =
	 * con.prepareStatement(INSERTACHIEVEMENTSTOUSER); q.setInt(1, this.id);
	 * q.setString(2, this.name); q.setString(3, this.description); q.setInt(4,
	 * this.passive); rs = q.executeUpdate(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } } return rs; }
	 */

	public static List<Achievements> searchByName(String nombre) {
		List<Achievements> result = new ArrayList<Achievements>();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%" + nombre + "%");
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					Achievements a = new Achievements();
					a.setId(rs.getInt("id"));
					a.setName(rs.getString("name"));
					a.setDescription(rs.getString("description"));
					a.setPassive(rs.getInt("passive"));
					result.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	

	public static Achievements searchAchievementByName(String nombre) {
		Achievements result = new Achievements();
		Connection con=Conexion.getConnection();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECTBYNAME);
				q.setString(1, "%" + nombre + "%");
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// es que hay al menos un resultado
					
					result.setId(rs.getInt("id"));
					result.setName(rs.getString("name"));
					result.setDescription(rs.getString("description"));
					result.setPassive(rs.getInt("passive"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int eliminar() {
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static int eliminar2(int i) {
		int rs = 0;
		Connection con=Conexion.getConnection();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, i);
				rs = q.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
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
					System.out.println();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return a;

	}
	

}