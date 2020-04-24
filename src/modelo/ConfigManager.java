package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private String username;
	private String pwd;
	private String url;
	private String driver;

	public ConfigManager() {
		Properties properties = new Properties();
		InputStream input = null;
		try {
			File config = new File("config/dbInfo.ini");
			if (config.exists()) {
				input = new FileInputStream(config);
				properties.load(input);
				this.username = properties.getProperty("USUARIO");
				this.pwd = properties.getProperty("PWD");
				this.url = properties.getProperty("URL");
				this.driver = properties.getProperty("DRIVER");
			} else
				System.err.println("File not found");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
