package miniprojet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {

	public static List<Membre> listeMbr = new ArrayList<>();

	public static List<Enchere> listeEn = new ArrayList<>();
	public static List<Offre> listeOff = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ServerSocket sc = new ServerSocket(4000, 3, InetAddress.getByName("127.0.0.1"));
		while (true) {
			Socket s = sc.accept();
			Traitement t = new Traitement(s);
			t.start();
			
			
		}

	}

}
