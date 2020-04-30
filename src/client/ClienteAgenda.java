package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import control.Control;
import server.InterfaceAgenda;

public class ClienteAgenda {
	public static void main(String[] args) {
		InterfaceAgenda agenda = null;
		try {
			System.out.println("Localizando el registro de objetos remitos”");
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			System.out.println("Obteniendo el stab del objeto remoto");
			agenda = (InterfaceAgenda) registry.lookup("Agenda");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (agenda != null) {
			System.out.println("Realizando operaciones con el objeto remoto");
			@SuppressWarnings("unused")
			Control control = new Control();
		}
	}
}
