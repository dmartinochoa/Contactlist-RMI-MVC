package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.ContactInfo;

public interface InterfaceAgenda extends Remote {
	public boolean loginUser(String userName, String userPass) throws RemoteException;

	public void closeSession() throws RemoteException;

	public boolean registerUser(String userName, String userPass) throws RemoteException;

	public String getUser() throws RemoteException;

	public String getId() throws RemoteException;

	public String[] getContacts() throws RemoteException;

	public ContactInfo getContactInfo(String contactName) throws RemoteException;
}
