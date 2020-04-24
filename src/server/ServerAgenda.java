package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import modelo.ContactInfo;
import modelo.DbManager;

public class ServerAgenda implements InterfaceAgenda {
	private DbManager dbManager;

	public ServerAgenda() {
		this.dbManager = new DbManager();
	}

	@Override
	public boolean loginUser(String userName, String userPass) {
		return dbManager.loginUser(userName, userPass);
	}

	@Override
	public boolean registerUser(String userName, String userPass) {
		return dbManager.registerUser(userName, userPass);
	}

	@Override
	public String getUser() throws RemoteException {
		return dbManager.getUser();
	}

	@Override
	public String getId() throws RemoteException {
		return dbManager.getId();
	}

	@Override
	public String[] getContacts() throws RemoteException {
		return dbManager.getContacts();
	}

	@Override
	public ContactInfo getContactInfo(String contactName) throws RemoteException {
		return dbManager.getContactInfo(contactName);
	}

	@Override
	public String[] getMessages() throws RemoteException {
		return dbManager.getMessages();
	}

	@Override
	public void sendMessage(String message, String recName) throws RemoteException {
		dbManager.sendMessage(message, recName);
	}

	@Override
	public void deleteMessage(int id) {
		dbManager.deleteMessage(id);
	}

	@Override
	public void closeSession() {
		dbManager.closeSession();
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
