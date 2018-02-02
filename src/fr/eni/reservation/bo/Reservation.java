package fr.eni.reservation.bo;

import java.sql.Timestamp;

public class Reservation {
	private String codeReservation;
	private Client client;
	private Spectacle spectacle;
	private int nbPlacesReservation;
	private Timestamp dateReservation;
	
	public Reservation(String codeReservation, Client client, Spectacle spectacle,
			int nbPlacesReservation, Timestamp dateReservation) {
		super();
		this.codeReservation = codeReservation;
		this.client = client;
		this.spectacle = spectacle;
		this.nbPlacesReservation = nbPlacesReservation;
		this.dateReservation = dateReservation;
	}
	public String getCodeReservation() {
		return codeReservation;
	}
	public void setCodeReservation(String codeReservation) {
		this.codeReservation = codeReservation;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Spectacle getSpectacle() {
		return spectacle;
	}
	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}
	public int getNbPlacesReservation() {
		return nbPlacesReservation;
	}
	public void setNbPlacesReservation(int nbPlacesReservation) {
		this.nbPlacesReservation = nbPlacesReservation;
	}
	public Timestamp getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Timestamp dateReservation) {
		this.dateReservation = dateReservation;
	}
	@Override
	public String toString() {
		return "Reservation [codeReservation=" + codeReservation + ", client="
				+ client + ", spectacle=" + spectacle + ", nbPlacesReservation=" + nbPlacesReservation
				+ ", dateReservation=" + dateReservation + "]";
	}
}
