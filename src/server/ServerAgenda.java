package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import modelo.ContactInfo;
import modelo.Message;

public class ServerAgenda implements InterfaceAgenda {
	private String USUARIO;
	private String PASS;
	private String URL;
	private String DRIVER;
	private Connection conection;
	private static String user;

	public ServerAgenda() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("config/Prop.ini");
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				this.USUARIO = propiedades.getProperty("USUARIO");
				this.PASS = propiedades.getProperty("PASS");
				this.URL = propiedades.getProperty("URL");
				this.DRIVER = propiedades.getProperty("DRIVER");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void conectar(String conexionUser) {
		try {
			Class.forName(DRIVER);
			conection = DriverManager.getConnection(URL, conexionUser, PASS);
			System.out.println("Conexión OK");
		} catch (Exception e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
	}

	public void closeSession() {
		if (conection != null) {
			try {
				conection.close();
				System.out.println("session cerrada");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean loginUser(String userName, String userPass) {
		ResultSet rs = null;
		try {
			this.conectar(this.USUARIO);
			String query = "select username, password from user where username = ? and password = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
			pstms.setString(1, userName);
			pstms.setString(2, userPass);
			rs = pstms.executeQuery();
			if (rs.next()) {
				System.out.println("Login correcto");
				conection.close();
				this.conectar(this.USUARIO);
				ServerAgenda.user = userName;
				return true;
			} else {
				System.err.println("Login incorrecto");
				conection.close();
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

	@Override
	public boolean registerUser(String userName, String userPass) {
		ResultSet rs = null;
		try {
			this.conectar(this.USUARIO);
			String selectQuery = "select username from user where username = ?";
			PreparedStatement selectPstms = conection.prepareStatement(selectQuery);
			selectPstms.setString(1, userName);
			rs = selectPstms.executeQuery();

			if (!rs.next()) {
				String insertQuery = "insert into user(username, password) values(?, ?);";
				PreparedStatement insertPstms = conection.prepareStatement(insertQuery);
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

	@Override
	public String getUser() throws RemoteException {
		ResultSet rs = null;
		String name = null;
		try {
			String query = "select username from user where username = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
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

	@Override
	public String getId() throws RemoteException {
		ResultSet rs = null;
		String id = null;
		try {
			String query = "select id from user where username = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
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

	@Override
	public String[] getContacts() throws RemoteException {
		ResultSet rs = null;
		ArrayList<String> contactList = new ArrayList<String>();
		try {
			String query = "select name from contacts where idUser = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
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

	@Override
	public ContactInfo getContactInfo(String contactName) throws RemoteException {
		ResultSet rs = null;
		try {
			String query = "select name, address, phoneNumber, cellNumber, email, website, notes from contacts where name = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
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

	@Override
	public String[] getMessages() throws RemoteException {
		ResultSet rs = null;
		ArrayList<String> messageList = new ArrayList<String>();
		try {
			String query = "select id, message, username, recName, date from messages where recName = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
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

	@Override
	public void sendMessage(String message, String recName) throws RemoteException {
		try {
			String query = "insert into `messages`(`message`, `username`, `recName`) values (?, ?,?)";
			PreparedStatement pstms = conection.prepareStatement(query);
			pstms.setString(1, message);
			pstms.setString(2, user);
			pstms.setString(3, recName);
			pstms.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMessage(int id) {
		try {
			String query = "delete from `messages` where id = ?";
			PreparedStatement pstms = conection.prepareStatement(query);
			pstms.setInt(1, id);
			pstms.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Registry reg = null;
		try {
			System.out.println("Crea el registro");
			reg = LocateRegistry.createRegistry(5555);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("creando el objeto servidor");
		ServerAgenda serverObject = new ServerAgenda();
		try {
			reg.rebind("Agenda", (InterfaceAgenda) UnicastRemoteObject.exportObject(serverObject, 0));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
