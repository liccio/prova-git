package it.unibas.chat.persistenza;

import it.unibas.chat.modello.*;

public interface IDAOArchivio {
	
	public void salva(Archivio archivio, String nomeFile) throws DAOException;

}