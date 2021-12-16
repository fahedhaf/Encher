package miniprojet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.net.Socket;
import java.util.List;



public class Traitement extends Thread {
	private Socket s;
	public Membre m = null;
	private Enchere e=null;

	public Traitement(Socket s) {
		this.s = s;
	}

	public void run() {

		try

		{
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader in_sc = new BufferedReader(in);

			OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
			PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);
			while (true) {
				
				String msg = in_sc.readLine();
				if (msg.startsWith("ENCHERES")) {
						String ss = "";
						for (Enchere e : Serveur.listeEn) {
							ss += e.info() + "----";
							
							
						} 
						if (ss.length() > 0) {
							out_sc.println(ss);
							
						} else {
							out_sc.println("Aucun Enchere dispo");
						}
					
				} 
				
				else if (msg.startsWith("JOIN ")) {
						String cd = msg.substring(5);
						Integer res = Integer.parseInt(cd);
						
						boolean test = false;
						
						for (Enchere e : Serveur.listeEn) {
							Integer i= e.getId();
							
							while(res==i) {
								
                                Serveur.listeEn.add(e);
                                e.setListeM((List<Member>) m);
								
								test = true;
								
							}
							
							
				    	}
						if (test == false) {
							out_sc.println("Enchere introuvable");
						}

				} 
				
				else if (msg.startsWith("OFFRE ")) {
					String cd = msg.substring(6);
					String[] t = cd.split("##");
					int res = Integer.parseInt(t[0]);
					Enchere en = null;

					boolean test = false;
					for (Enchere e : Serveur.listeEn) {
						if (e.getId() == res) {
							en = e;
							test = true;
						}
					}
					double doub = Double.parseDouble(t[1]);
					if (test == false) {
						out_sc.println("Enchere introuvable");
					} else {
						if (en.getPrixD() < doub) {
							en.setPrixD(doub);
							Offre o = new Offre(doub, this.m, en);
							Serveur.listeOff.add(o);
							out_sc.println("Offre Accepte ;)");
						} else {
							out_sc.println("Offre non valide ");
						}
					}
				}  else if (msg.startsWith("LOGIN ")) {
					out_sc.println("Login succes");
					String nom = msg.substring(6);
					this.m = new Membre(nom);
					Serveur.listeMbr.add(m);
				
				} else if(msg.startsWith("ADD ")){
					String enc=msg.substring(4);
					String[] tt = enc.split("##");
					String des =tt[0].toString() ;
					float prix = Float.parseFloat(tt[1]) ;
					int heure = Integer.parseInt(tt[2]) ;
					int etat = Integer.parseInt(tt[3]) ;
					
					this.e=new Enchere( des,prix,heure,etat);
					Serveur.listeEn.add(e);
					out_sc.println("ADD succes = "+e.info());
					
				} 
				else if (msg.startsWith("LIST ")) {
					String cd = msg.substring(5);
					String s = "";
					int res = Integer.parseInt(cd);
					Enchere en = null;
					boolean test = false;
					for (Enchere e : Serveur.listeEn) {
						if (e.getId() == res) {
							en = e;
							test = true;
						}
					}
					if (test == false) {
						out_sc.println("Enchere introuvable");
					} else {
						for (Offre o : Serveur.listeOff) {
							if (o.getE() == en) {
								s += o.info();
							}
						}

						if (s.length() > 0) {
							out_sc.println(s);
						} else {
							out_sc.println("Aucun offre dispo pour cette enchere");
						}
					}

				}
				else {
					out_sc.println("Syntaxe Invalide !");
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
