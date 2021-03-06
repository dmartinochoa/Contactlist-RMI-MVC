package control;

import view.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;

import modelo.ContactInfo;
import server.InterfaceAgenda;
import server.ServerAgenda;

public class Control {
	private Login login;
	private CreateAccount createAcc;
	private Home home;
	private InterfaceAgenda agenda;

	public Control() {
		agenda = new ServerAgenda();
		Login login = new Login(this);
		this.setVista(login);
		login.setVisible(true);
	}

// Login View
	public void loginPress(String userName, String userPass) {
		try {
			if (agenda.loginUser(userName, userPass)) {
				this.login.dispose();
				this.home = new Home(this);
				this.home.setVisible(true);
			} else {
				login.loginMessage();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void goToCreateAcc() {
		this.login.setVisible(false);
		if (createAcc != null) {
			this.createAcc.setVisible(true);
			this.createAcc.cleanFields();
		} else {
			this.createAcc = new CreateAccount(this);
			this.createAcc.setVisible(true);
		}
	}

// Home View
	public String getUser() {
		try {
			return agenda.getUser();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "User not found";
	}

	public String[] getContacts() {
		try {
			return agenda.getContacts();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ContactInfo getContactInfo(String contactName) {
		try {
			return agenda.getContactInfo(contactName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] getMessages() {
		try {
			return agenda.getMessages();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendMessage(String message, String recName) {
		try {
			agenda.sendMessage(message, recName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(int id) {
		try {
			agenda.deleteMessage(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void openUrl(String url) {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	public void addContact(ContactInfo contact) {
		try {
			agenda.addContact(contact);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void deleteContact(String name) {
		try {
			agenda.deleteContact(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void editContact(ContactInfo contact, String name) {
		try {
			agenda.editContact(contact, name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

// Create Account View
	public boolean createAcc(String username, String pwd) {
		try {
			return agenda.registerUser(username, pwd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Generic method to return to login screen from anywhere
	public void goToLogin() {
		try {
			agenda.closeSession();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (createAcc != null) {
			this.createAcc.dispose();
		}
		if (home != null) {
			this.home.dispose();
		}
		if (login != null) {
			this.login.setVisible(true);
		} else {
			this.login = new Login(this);
			this.login.setVisible(true);
		}
	}

	public void setVista(Login login) {
		this.login = login;
	}
}
