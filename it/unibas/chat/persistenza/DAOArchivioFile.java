package it.unibas.chat.persistenza;

import it.unibas.chat.modello.*;
import java.io.*;

public class DAOArchivioFile implements IDAOArchivio {
	
	public void salva(Archivio archivio, String nomeFile) throws DAOException {
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(nomeFile);
			for(Chat chat : archivio.getListaChat()){
				writer.println(chat.getNome() + "|" + chat.getAmministratore());
				for(Messaggio messaggio : chat.getListaMessaggi()){
					writer.println("\t" + messaggio.getTesto() + "|" + messaggio.getUtente() + "|" + messaggio.getTipologia());
				}
			}
		}catch(Exception e){
			throw new DAOException("Errore nel salvataggio del file " + e.getMessage());
		} finally {
			if(writer != null){
				writer.close();
			}
		}
	}

}