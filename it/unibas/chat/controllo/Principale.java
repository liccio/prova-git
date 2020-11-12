package it.unibas.chat.controllo;

import it.unibas.chat.modello.*;
import it.unibas.chat.persistenza.*;
import it.unibas.utilita.Console;
import java.util.*;

public class Principale {
	
	public void esegui(){
		Archivio archivio = new Archivio();
		while(true){
			int scelta = this.leggiScelta();
			if(scelta == 0){
				break;
			} else if (scelta == 1){
				this.schermoLeggiChat(archivio);
			} else if (scelta == 2){
				this.schermoLeggiMessaggio(archivio);
			} else if (scelta == 3){
				this.schermoStampaMessaggiUnici(archivio);
			} else if (scelta == 4){
				String tipologiaNumerosa = archivio.getTipologiaNumerosa();
				if(tipologiaNumerosa == null){
					System.out.println("Nessun messaggio inserito nell'archivio");
				} else {
					System.out.println("La tipologia piu' numerosa e' " + tipologiaNumerosa);
				}
			} else if (scelta == 5){
				//this.schermoSalvaDati(archivio);
				Set<String> insieme = archivio.insiemeTipologie();
			}
		}
		System.out.println("Arrivederci!");
	}

	private void schermoLeggiChat(Archivio archivio){
		System.out.println("Inserisci il nome della chat: ");
		String nome = this.leggiStringaNonVuota();
		while(archivio.cercaChat(nome) != null){
			System.out.println("Esiste gia' una chat chiamata " + nome + ". Inserisci un nome non esistente: ");
			nome = this.leggiStringaNonVuota();
		}
		System.out.println("Inserisci il nome dell'amministratore: ");
		String amministratore = leggiStringaNonVuota();
		Chat chat = new Chat(nome, amministratore);
		archivio.aggiungiChat(chat);
		System.out.println("Chat aggiunta correttamente");
	}

	private void schermoLeggiMessaggio(Archivio archivio){
		System.out.println("Inserisci il nome della chat in cui inserire il messaggio: ");
		String nome = this.leggiStringaNonVuota();
		Chat chat = archivio.cercaChat(nome);
		if(chat == null){
			System.out.println("Non esiste alcuna chat chiamata " + nome);
			return;
		}
		System.out.println("Chat selezionata " + chat);
		System.out.println("Inserisci il testo del messaggio: ");
		String testo = this.leggiStringaNonVuota();
		System.out.println("Inserisci il mittente del messaggio: ");
		String utente = this.leggiStringaNonVuota();
		System.out.println("Inserisci la tipologia del messaggio: ");
		String tipologia = this.leggiTipologia();
		while(tipologia.equalsIgnoreCase("audio") && !testo.isEmpty()){
			System.out.println("La tipologia non e' consentita in quanto il messaggio ha del testo. Inserisci una nuova tipologia: ");
			tipologia = this.leggiTipologia();
		}
		Messaggio messaggio = new Messaggio(testo, utente, tipologia);
		chat.aggiungiMessaggio(messaggio);
		System.out.println("Messaggio aggiunto correttamente");
	}

	private void schermoStampaMessaggiUnici(Archivio archivio){
		System.out.println("Inserisci il nome della chat di cui stampare i messaggi: ");
		String nome = this.leggiStringaNonVuota();
		Chat chat = archivio.cercaChat(nome);
		if(chat == null){
			System.out.println("Non esiste alcuna chat chiamata " + nome);
			return;
		}
		List<Messaggio> listaMessaggi = chat.getMessaggiUnici();
		System.out.println("Ci sono " + listaMessaggi.size() + " messaggi unici nella chat");
		for(Messaggio messaggio : listaMessaggi){
			System.out.println(messaggio);
		}
	}

	private void schermoSalvaDati(Archivio archivio){
		System.out.println("Inserisci il nome del file: ");
		String nomeFile = this.leggiStringaNonVuota();
		IDAOArchivio daoArchivio = new DAOArchivioFile();
		try{
			daoArchivio.salva(archivio, nomeFile);
		} catch(DAOException e){
			System.out.println("Impossibile salvare il file: " + e.getMessage());
		}
	}

	private String leggiTipologia(){
		String tipologia = this.leggiStringaNonVuota();
		while(!tipologia.equalsIgnoreCase("testuale") && 
			  !tipologia.equalsIgnoreCase("audio") && 
			  !tipologia.equalsIgnoreCase("video") && 
			  !tipologia.equalsIgnoreCase("immagine")){
			System.out.println("Tipologia non valida. Inserire un valore tra testuale, audio, video e immagine");
			tipologia = this.leggiStringaNonVuota();
		}
		return tipologia;
	}

	private String leggiStringaNonVuota(){
		String stringa = Console.leggiStringa();
		while(stringa.trim().isEmpty()){
			System.out.print("Errore! Inserire una stringa non vuota: ");
			stringa = Console.leggiStringa();
		}
		return stringa.trim();
	}

	private int leggiScelta(){
		System.out.println("------------------");
		System.out.println("--     Chat     --");
		System.out.println("------------------");
		System.out.println("1) Inserisci chat");
		System.out.println("2) Inserisci messaggio");
		System.out.println("3) Stampa messaggi unici");
		System.out.println("4) Cerca tipologia numerosa");
		System.out.println("5) Salva");
		System.out.println("------------------");
		System.out.println("0) Esci");
		System.out.println("------------------");
		int scelta = Console.leggiIntero();
		while(scelta < 0 || scelta > 5){
			System.out.print("Errore! Scelta non corretta. Inserisci una nuova scelta: ");
			scelta = Console.leggiIntero();
		}
		return scelta;
	}

	public static void main(String[] args){
		Principale p = new Principale();
		p.esegui();
	}

}