package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import view.BancaView;
import view.CarduriView;
import view.ConturiView;
import view.PersoaneView;

public class BazaUtil {

	private ConturiView cont;

	public Integer checkLogin(String email, String parola) {
		Integer count = null;
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prp = null;
		try {
			prp = connect.prepareStatement(
					"select count(*) as nrconturi from administrator_banca where mail = ? and parola = ?");
			prp.setString(1, email);
			prp.setString(2, parola);
			ResultSet rs = prp.executeQuery();
			if (rs.next()) {
				count = rs.getInt("nrconturi");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public void insertBanca(String numeBanca, String adresa, String swift) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prep = null;
		try {
			prep = connect.prepareStatement("insert into banca(nume, adresa, swift) values(?, ?, ?)");
			prep.setString(1, numeBanca);
			prep.setString(2, adresa);
			prep.setString(3, swift);
			prep.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prep, connect);
		}
	}

	public void insertPersoana(String nume, String prenume, String adresa, String cnp) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prep = null;
		try {
			prep = connect.prepareStatement("insert into persoane(nume, prenume, adresa, cnp) values(?, ?, ?, ?)");
			prep.setString(1, nume);
			prep.setString(2, prenume);
			prep.setString(3, adresa);
			prep.setString(4, cnp);
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prep, connect);
		}
	}

	public List<BancaView> getBanci() {
		List<BancaView> listaBanci = new ArrayList<>();
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		try {
			prep = connect.prepareStatement("select id, nume, adresa, swift from banca");
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				BancaView banca = new BancaView();
				int id = rs.getInt("id");
				String nume = rs.getString("nume");
				String adresa = rs.getString("adresa");
				String swift = rs.getString("swift");

				banca.setId(id);
				banca.setNumeBanca(nume);
				banca.setAdresa(adresa);
				banca.setSwift(swift);

				listaBanci.add(banca);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(prep, connect);
		}

		return listaBanci;

	}

	public List<PersoaneView> getPersoane() {
		List<PersoaneView> listaPersoane = new ArrayList<>();
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement("select id, nume, prenume, adresa, cnp from persoane");
			rs = prep.executeQuery();

			while (rs.next()) {
				PersoaneView prs = new PersoaneView();
				int id = rs.getInt("id");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				String adresa = rs.getString("adresa");
				String cnp = rs.getString("cnp");

				prs.setId(id);
				prs.setNumePer(nume);
				prs.setPrenumePer(prenume);
				prs.setAdresa(adresa);
				prs.setCnp(cnp);

				listaPersoane.add(prs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(rs, prep, connect);
			;
		}

		return listaPersoane;

	}

	public void insertCont(int idbanca, int idpersoana, int sold, String nrcont) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prp = null;
		try {
			prp = connect
					.prepareStatement("insert into conturi(id_banca, id_persoana, sold, nrcont) values (?, ?, ?, ?)");
			prp.setInt(1, idbanca);
			prp.setInt(2, idpersoana);
			prp.setInt(3, sold);
			prp.setString(4, nrcont);
			prp.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prp, connect);
		}

	}

	public void insertCard(int nrCard, int pin, String nrCont) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prp = null;

		try {
			prp = connect.prepareStatement("insert into carduri(nrcard, pin, nrcont) values (?, ?, ?)");
			prp.setInt(1, nrCard);
			prp.setInt(2, pin);
			prp.setString(3, nrCont);
			prp.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prp, connect);
		}

	}

	public List<CarduriView> getCarduri() {
		List<CarduriView> listaCarduri = new ArrayList<>();
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement("select id, nrCard, pin, nrCont from carduri");
			rs = prep.executeQuery();

			while (rs.next()) {
				CarduriView card = new CarduriView();
				int id = rs.getInt("id");
				int nrCard = rs.getInt("nrCard");
				int pin = rs.getInt("pin");
				String nrCont = rs.getString("nrCont");

				card.setId(id);
				card.setNrCard(nrCard);
				card.setPin(pin);
				card.setNrCont(nrCont);

				listaCarduri.add(card);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(rs, prep, connect);
		}

		return listaCarduri;
	}

	public Integer interogareSold(int nrCard, int pin) {
		Integer sold = null;
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement("SELECT co.sold FROM conturi co\r\n" + "INNER JOIN carduri ca\r\n"
					+ "ON co.nrcont = ca.nrcont\r\n" + "WHERE ca.nrcard = ?\r\n" + "AND ca.pin = ?");
			prep.setInt(1, nrCard);
			prep.setInt(2, pin);
			rs = prep.executeQuery();
			if (rs.next()) {
				sold = rs.getInt("co.sold");
			}
		} catch (SQLException e) {

		} finally {
			config.inchideConexiuni(rs, prep, connect);
		}
		return sold;
	}

	public void retragere(int nrCard, int pin, int sumaRetrasa, ConturiView cont) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prp = null;
		try {
			prp = connect.prepareStatement(
					"UPDATE conturi con\r\n" + "INNER JOIN carduri car\r\n" + "ON con.nrcont = car.nrcont\r\n"
							+ "SET con.sold = ?\r\n" + "WHERE car.nrcard = ?\r\n" + "AND car.pin = ?");
			int soldActual = new BazaUtil().interogareSold(nrCard, pin);
			int soldNou = soldActual - sumaRetrasa;
			prp.setInt(1, soldNou);
			prp.setInt(2, nrCard);
			prp.setInt(3, pin);
			prp.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prp, connect);
		}

	}

	public List<ConturiView> getConturiBanca() {
		List<ConturiView> listaConturi = new ArrayList<>();
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement("SELECT id, id_banca, id_persoana, sold, nrcont FROM conturi");

			rs = prep.executeQuery();
			while (rs.next()) {
				ConturiView obj = new ConturiView();
				int id = rs.getInt("id");
				int idBanca = rs.getInt("id_banca");
				int idPersoana = rs.getInt("id_persoana");
				int sold = rs.getInt("sold");
				String nrcont = rs.getString("nrcont");

				obj.setId(id);
				obj.setIdBanca(idBanca);
				obj.setIdPersoana(idPersoana);
				obj.setSold(sold);
				obj.setNrCont(nrcont);

				listaConturi.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(rs, prep, connect);
		}

		return listaConturi;

	}

	public String getNrContRetragere(int nrCard, int pin) {
		String nrContRetragere = null;
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement(
					"select con.nrcont from conturi con inner join carduri car on car.nrcont = con.nrcont WHERE car.nrcard = ? and car.pin = ?");
			prep.setInt(1, nrCard);
			prep.setInt(2, pin);
			rs = prep.executeQuery();

			if (rs.next()) {
				nrContRetragere = rs.getString("con.nrcont");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(prep, connect);
		}

		return nrContRetragere;

	}

	public void depunere(int nrCard, int pin, int sumaDepusa, ConturiView cont) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prp = null;
		try {
			prp = connect.prepareStatement(
					"UPDATE conturi con\r\n" + "INNER JOIN carduri car\r\n" + "ON con.nrcont = car.nrcont\r\n"
							+ "SET con.sold = ?\r\n" + "WHERE car.nrcard = ?\r\n" + "AND car.pin = ?");
			int soldActual = new BazaUtil().interogareSold(nrCard, pin);
			int soldNou = soldActual + sumaDepusa;
			prp.setInt(1, soldNou);
			prp.setInt(2, nrCard);
			prp.setInt(3, pin);
			prp.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prp, connect);
		}

	}

	public Integer interogareSoldNrCard(String nrCont) {
		Integer sold = null;
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prep = null;
		ResultSet rs = null;

		try {
			prep = connect.prepareStatement("select sold from conturi where nrcont = ?");
			prep.setString(1, nrCont);
			rs = prep.executeQuery();
			if (rs.next()) {
				sold = rs.getInt("sold");
			}
		} catch (SQLException e) {

		} finally {
			config.inchideConexiuni(rs, prep, connect);
		}
		return sold;
	}

//	public Integer interograreSoldNrCont(String nrCont) {
//		Jdbc config = new Jdbc();
//		Integer sold = null;
//		Connection connect = config.deschideConexiune();
//		PreparedStatement prp = null;
//		ResultSet rs = null;
//		
//		try {
//			prp = connect.prepareStatement("select sold from conturi where nrcont = ?");
//			prp.setString(1, nrCont);
//			rs = prp.executeQuery();
//			
//			if()
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return sold;
//	}

	public void primireTransfer(ConturiView cont, String nrCont, int sumaPrimita) {
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prp = null;

		try {
			prp = connect.prepareStatement("update conturi set sold = ? where nrcont = ?");
			int soldActual = new BazaUtil().interogareSoldNrCard(nrCont);
			int soldNou = soldActual + sumaPrimita;
			prp.setInt(1, soldNou);
			prp.setString(2, nrCont);

			prp.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(prp, connect);
		}

	}

	public void actualizareDupaTransfer(int nrCard, int pin, int sumaTrimisa, ConturiView cont) {
		Connection connect = new Jdbc().deschideConexiune();
		PreparedStatement prp = null;
		try {
			prp = connect.prepareStatement(
					"UPDATE conturi con\r\n" + "INNER JOIN carduri car\r\n" + "ON con.nrcont = car.nrcont\r\n"
							+ "SET con.sold = ?\r\n" + "WHERE car.nrcard = ?\r\n" + "AND car.pin = ?");
			int soldActual = new BazaUtil().interogareSold(nrCard, pin);
			int soldNou = soldActual - sumaTrimisa;
			prp.setInt(1, soldNou);
			prp.setInt(2, nrCard);
			prp.setInt(3, pin);
			prp.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new Jdbc().inchideConexiuni(prp, connect);
		}

	}

	public List<String> getConturi() {
		List<String> listaConturi = new ArrayList<>();
		Jdbc config = new Jdbc();
		Connection connect = config.deschideConexiune();
		PreparedStatement prp = null;
		ResultSet rs = null;
		try {
			prp = connect.prepareStatement("select nrcont from conturi");
			rs = prp.executeQuery();

			while (rs.next()) {
				String nrcont = rs.getString("nrcont");
				listaConturi.add(nrcont);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			config.inchideConexiuni(rs, prp, connect);
		}

		return listaConturi;

	}

}
