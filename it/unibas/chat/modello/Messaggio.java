package it.unibas.chat.modello;

public class Messaggio implements Comparable<Messaggio> {

	private String testo;
	private String utente;
	private String tipologia;

	public Messaggio(String testo, String utente, String tipologia){
		this.testo = testo;
		this.utente = utente;
		this.tipologia = tipologia;
	}

	public String getTesto(){
		return this.testo;
	}

	public String getUtente(){
		return this.utente;
	}

	public String getTipologia(){
		return this.tipologia;
	}

	public boolean equals(Object o){
		Messaggio altroMessaggio = (Messaggio) o;
		return this.hashString().equals(altroMessaggio.hashString());
	}

	public int hashCode(){
		return this.hashString().hashCode();
	}

	private String hashString(){
		return this.testo + "," + this.utente + "," + this.tipologia;
	}

	public int compareTo(Messaggio altroMessaggio){
		return this.getTesto().compareTo(altroMessaggio.getTesto());
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(testo);
		sb.append(" - Inviato da: " + this.utente);
		sb.append(" - Tipo: " + this.tipologia);
		return sb.toString();
	}
	
}