package miniprojet;

import java.io.*;
import java.lang.reflect.Member;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class Enchere {
	
private int id ;
static int c=1000 ;
private String desc;
private double prixD;
private int heueF;
private int etat ;


public static List <Member> lim;





public Enchere(String desc, float prixD, int heueF, int etat) {
	super();
	this.desc = desc;
	this.prixD = prixD;
	this.heueF = heueF;
	this.etat = etat;
	this.id=c++;
	
	
}
public String info() {
	return (id+"##"+desc+"##"+prixD+"##"+heueF+"##"+etat+"##"+lim+"**");
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public static int getC() {
	return c;
}
public static void setC(int c) {
	Enchere.c = c;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public double getPrixD() {
	return prixD;
}
public void setPrixD(double doub) {
	this.prixD = doub;
}
public int getHeueF() {
	return heueF;
}
public void setHeueF(int heueF) {
	this.heueF = heueF;
}
public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}





public static List<Member> getListM() {
	return lim;
}

public static void setListeM(List<Member> listeM) {
	Enchere.lim = listeM;
}







	
	
}
