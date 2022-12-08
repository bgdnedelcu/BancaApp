package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {
		
		public Connection deschideConexiune() {
			Connection conect = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi", "bogdan", "velosity99CR7");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return conect;
		}

		public void inchideConexiune(Connection conect) {
			if (conect != null) {
				try {
					conect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public void inchidePreparedStatement(PreparedStatement stmt) {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public void inchideResultSet(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public void inchideConexiuni(ResultSet rs, PreparedStatement stmt, Connection conect) {
			inchideResultSet(rs);
			inchidePreparedStatement(stmt);
			inchideConexiune(conect);
		}

		public void inchideConexiuni(PreparedStatement stmt, Connection conect) {
			inchideConexiuni(null, stmt, conect);
		}


}
