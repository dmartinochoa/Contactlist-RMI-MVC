package modelo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ConfigManager;
import modelo.ContactInfo;
import modelo.Message;

public class DbManager {
	private ConfigManager configManager = new ConfigManager();
	private String username;
	private String pwd;
	private String url;
	private String driver;
	private Connection connection;
	private static String user;

	public DbManager() {
		this.username = configManager.getUser();
		this.pwd = configManager.getPwd();
		this.url = configManager.getUrl();
		this.driver = configManager.getDriver();
		this.connection = conect(username);
	}

	public Connection conect(String conexionUser) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, conexionUser, pwd);
			System.out.println("Conexión OK");
		} catch (Exception e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
		return connection;
	}

	public void closeSession() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("session cerrada");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean loginUser(String userName, String userPass) {
		try {
			if (this.connection.isClosed()) {
				this.connection = conect(username);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			String query = "select username, password from user where username = ? and password = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, userName);
			pstms.setString(2, userPass);
			rs = pstms.executeQuery();
			if (rs.next()) {
				System.out.println("Login correcto");
				connection.close();
				this.conect(this.username);
				user = userName;
				return true;
			} else {
				System.err.println("Login incorrecto");
				connection.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean registerUser(String userName, String userPass) {
		try {
			if (this.connection.isClosed()) {
				this.connection = conect(username);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			this.conect(this.username);
			String selectQuery = "select username from user where username = ?";
			PreparedStatement selectPstms = connection.prepareStatement(selectQuery);
			selectPstms.setString(1, userName);
			rs = selectPstms.executeQuery();

			if (!rs.next()) {
				String insertQuery = "insert into user(username, password) values(?, ?);";
				PreparedStatement insertPstms = connection.prepareStatement(insertQuery);
				insertPstms.setString(1, userName);
				insertPstms.setString(2, userPass);
				insertPstms.executeUpdate();
				closeSession();
				return true;
			} else {
				closeSession();
				return false;
			}

		} catch (SQLException e) {
			closeSession();
			e.printStackTrace();
			return false;
		}
	}

	public String getUser() throws RemoteException {
		ResultSet rs = null;
		String name = null;
		try {
			String query = "select username from user where username = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, user);
			rs = pstms.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getId() throws RemoteException {
		ResultSet rs = null;
		String id = null;
		try {
			String query = "select id from user where username = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, user);
			rs = pstms.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public String[] getContacts() throws RemoteException {
		ResultSet rs = null;
		ArrayList<String> contactList = new ArrayList<String>();
		try {
			String query = "select name from contacts where idUser = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, getId());
			rs = pstms.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				contactList.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] contactArray = contactList.toArray(new String[contactList.size()]);
		return contactArray; // Returns all the contacts of a user
	}

	public ContactInfo getContactInfo(String contactName) throws RemoteException {
		ResultSet rs = null;
		try {
			String query = "select name, address, phoneNumber, cellNumber, email, website, notes from contacts where name = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, contactName);
			rs = pstms.executeQuery();
			if (rs.next()) {
				ContactInfo contactInfo = new ContactInfo(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				return contactInfo; // Returns the info of a specified contact
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] getMessages() throws RemoteException {
		ResultSet rs = null;
		ArrayList<String> messageList = new ArrayList<String>();
		try {
			String query = "select id, message, username, recName, date from messages where recName = ?;";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, user);
			rs = pstms.executeQuery();
			while (rs.next()) {
				Message msg = new Message(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				messageList.add(msg.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] msgArray = messageList.toArray(new String[messageList.size()]);
		return msgArray; // Returns all the messages of a user
	}

	public void sendMessage(String message, String recName) throws RemoteException {
		try {
			String query = "insert into `messages`(`message`, `username`, `recName`) values (?, ?,?)";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setString(1, message);
			pstms.setString(2, user);
			pstms.setString(3, recName);
			pstms.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(int id) {
		try {
			String query = "delete from `messages` where id = ?";
			PreparedStatement pstms = connection.prepareStatement(query);
			pstms.setInt(1, id);
			pstms.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
