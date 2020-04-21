package control;

import view.*;
import model.*;

public class Control {
	private Login login;
	private CreateAccount createAcc;
	private Model model;
	private Home home;

	public Control() {
		Model model = new Model();
		Login login = new Login();
		this.setVista(login);
		this.model = model;
		model.setLogin(login);
		login.setControl(this);
		login.setModel(model);
		login.setVisible(true);
	}

// VISTA LOGIN

	// Boton de iniciar session
	public void loginPress(String userName, String userPass) {
		 //comprobacion de usuario/contraseña para acceder
		if (model.loginUser(userName, userPass)) {
			this.login.dispose();
			if (home != null) {
				this.home.setVisible(true);
//				this.home.clearFields();
			} else {
				this.home = new Home();
				this.home.setControl(this);
				this.home.setModelo(model);
				this.home.setVisible(true);
//				this.home.clearFields();
			}
		} else {
			login.loginMessage();
		}
	}

	// Boton para cambiar a la vista de crear usuario
	public void goToCreateAcc() {
		this.login.setVisible(false);
		if (createAcc != null) {
			this.createAcc.setVisible(true);
			this.createAcc.cleanFields();
		} else {
			this.createAcc = new CreateAccount();
			this.createAcc.setControl(this);
			this.createAcc.setModelo(this.model);
			this.createAcc.setVisible(true);
		}
	}

	public void goToLogin() {
//		model.closeSession();
		if (createAcc != null) {
			this.createAcc.dispose();
		}
		if (login != null) {
			this.login.setVisible(true);
		} else {
			this.login = new Login();
			this.login.setControl(this);
			this.login.setModel(model);
			this.login.setVisible(true);
		}
	}

	public void setVista(Login login) {
		this.login = login;
	}
}
