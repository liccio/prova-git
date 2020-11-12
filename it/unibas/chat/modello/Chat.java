package it.unibas.chat.modello;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chat {

	private static Logger logger = LoggerFactory.getLogger(Chat.class);

	private String nome;
	private String amministratore;
	private List<Messaggio> listaMessaggi = new ArrayList<Messaggio>();

	public Chat(String nome, String amministratore){
		this.nome = nome;
		this.amministratore = amministratore;
	}

	public String getNome(){
		return this.nome;
	}

	public String getAmministratore(){
		return this.amministratore;
	}

	public List<Messaggio> getListaMessaggi(){
		return this.listaMessaggi;
	}

	public void aggiungiMessaggio(Messaggio messaggio){
		this.listaMessaggi.add(messaggio);
	}

	public List<Messaggio> getMessaggiUnici(){
		logger.debug("Dimensione lista: " + listaMessaggi.size());
		Set<Messaggio> insiemeMessaggi = new HashSet<Messaggio>(this.listaMessaggi);
		logger.debug("Dimensione insieme: " + insiemeMessaggi.size());
		List<Messaggio> listaOrdinataMessaggi = new ArrayList<Messaggio>(insiemeMessaggi);
		Collections.sort(listaOrdinataMessaggi);
		return listaOrdinataMessaggi;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" Chat: " + this.nome);
		sb.append(" Amministratore: " + this.amministratore);
		for(Messaggio messaggio : this.listaMessaggi){
			sb.append("\n\t " + messaggio.toString());
		}
		return sb.toString();
	}
	
}