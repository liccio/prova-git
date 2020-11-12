package it.unibas.chat.modello;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Archivio {

	private static Logger logger = LoggerFactory.getLogger(Archivio.class);
	private List<Chat> listaChat = new ArrayList<Chat>();

	public List<Chat> getListaChat(){
		return this.listaChat;
	}

	public void aggiungiChat(Chat chat){
		this.listaChat.add(chat);
		logger.debug("Aggiunta chat " + chat + " alla lista. Numero chat: " + this.listaChat.size());
	}

	public Chat cercaChat(String nome){
		for(Chat chat : this.listaChat){
			if(chat.getNome().equalsIgnoreCase(nome)){
				return chat;
			}
		}
		return null;
	}

	public String getTipologiaNumerosa(){
		Map<String, Integer> numerositaTipologie = this.calcolaNumerositaTipogie();
		logger.debug("Numerosita tipologie: " + numerositaTipologie);
		String massimo = null;
		for(String tipologia : numerositaTipologie.keySet()){
			if(massimo == null || numerositaTipologie.get(tipologia) > numerositaTipologie.get(massimo)){
				massimo = tipologia;
			}
		}
		return massimo;
	}


	private Map<String, Integer> calcolaNumerositaTipogie(){
		Map<String, Integer> numerositaTipologie = new HashMap<String, Integer>();
		for(Messaggio messaggio : this.getTuttiMessaggi()){
			String tipologia = messaggio.getTipologia();
			Integer vecchieOccorrenze = numerositaTipologie.get(tipologia);
			if(vecchieOccorrenze == null){
				numerositaTipologie.put(tipologia, 1);
			} else {
				numerositaTipologie.put(tipologia, vecchieOccorrenze + 1);
			}
		}
		return numerositaTipologie;
	}

	public Set<String> insiemeTipologie(){
		Set<String> numerositaTipologie = new HashSet<String>();
		for(Messaggio messaggio : this.getTuttiMessaggi()){
			numerositaTipologie.add(messaggio.getTipologia());
		}
		System.out.println(numerositaTipologie);
		return numerositaTipologie;
	}

	public List<Messaggio> getTuttiMessaggi(){
		List<Messaggio> tuttiMessaggi = new ArrayList<Messaggio>();
		for(Chat chat : this.listaChat){
			for(Messaggio messaggio : chat.getListaMessaggi()){
				tuttiMessaggi.add(messaggio);
			}
		}
		return tuttiMessaggi;
	}

	
}