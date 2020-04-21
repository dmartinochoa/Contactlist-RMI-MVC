package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import view.*;

public class Model {
	private String USUARIO;
	private String PASS;
	private String URL;
	private String DRIVER;
	private Connection conection;
	private static String user;
	private Login login;

	public Model() {
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

		if (this.USUARIO.equals("root")) {
			this.PASS = "";
		}
		try {
			Class.forName(DRIVER);
			conection = DriverManager.getConnection(URL, conexionUser, PASS);
			System.out.println("Conexi�n OK");
		} catch (Exception e) {
			System.err.println("Error en la conexi�n");
			e.printStackTrace();
		}

	}

	public boolean loginUser(String userName, String userPass) {
		ResultSet rs = null;// las querys
		try {
			this.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
			String query = "select username, password from user where username = ? and password = ?;";
			PreparedStatement pstms = conection.prepareStatement(query);
			pstms.setString(1, userName);
			pstms.setString(2, userPass);
			rs = pstms.executeQuery();
			if (rs.next()) {
				System.out.println("Login correcto");
				conection.close();
				this.conectar(this.USUARIO);
				this.user = userName;
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

	public void setLogin(Login login) {
		this.login = login;
	}
}