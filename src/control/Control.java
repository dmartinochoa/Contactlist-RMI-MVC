package control;

import view.*;

import java.rmi.RemoteException;

import modelo.ContactInfo;
import server.InterfaceAgenda;

public class Control {
	private Login login;
	private CreateAccount createAcc;
	private Home home;
	private InterfaceAgenda agenda;

	public Control(InterfaceAgenda agenda) {
		this.agenda = agenda;
		Login login = new Login(this);
		this.setVista(login);
		login.setVisible(true);
	}

// Login View
	public void loginPress(String userName, String userPass) {
		try {
			if (agenda.loginUser(userName, userPass)) {
				this.login.dispose();
				if (home != null) {
					this.home.setVisible(true);
				} else {
					this.home = new Home(this);
					this.home.setVisible(true);
				}
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

	public String getId() {
		try {
			return agenda.getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "Id not found";
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
