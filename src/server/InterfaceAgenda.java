package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.ContactInfo;

public interface InterfaceAgenda extends Remote {
	public boolean loginUser(String userName, String userPass) throws RemoteException;

	public void closeSession() throws RemoteException;

	public boolean registerUser(String userName, String userPass) throws RemoteException;

	public String getUser() throws RemoteException;

	public String[] getContacts() throws RemoteException;

	public ContactInfo getContactInfo(String contactName) throws RemoteException;

	public String[] getMessages() throws RemoteException;

	public void sendMessage(String message, String recName) throws RemoteException;

	public void deleteMessage(int id) throws RemoteException;

	public void addContact(ContactInfo contact) throws RemoteException;

	public void deleteContact(String name) throws RemoteException;

	public void editContact(ContactInfo contact, String name) throws RemoteException;
}
