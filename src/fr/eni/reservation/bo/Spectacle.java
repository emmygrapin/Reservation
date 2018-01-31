package fr.eni.reservation.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Spectacle {
	
	private int idSpectacle;
	private String titre;
	private String artiste;
	private String lieu;
	private Date date;
	private int placesDispos;
	private List<Reservation> reservationsSpectacle;
	
	public Spectacle(int idSpectacle, String titre, String artiste, String lieu, Date date, int placesDispos) {
		
		this.idSpectacle = idSpectacle;
		this.titre = titre;
		this.artiste = artiste;
		this.lieu = lieu;
		this.date = date;
		this.placesDispos = placesDispos;
		this.reservationsSpectacle = new ArrayList<Reservation>();
	}
	
	public Spectacle(String titre, String artiste, String lieu, Date date, int placesDispos) {
	
		this.titre = titre;
		this.artiste = artiste;
		this.lieu = lieu;
		this.date = date;
		this.placesDispos = placesDispos;
	}
	
	public List<Reservation> getReservations() {
		return reservationsSpectacle;
	}
	
	public void addReservation(Reservation reservation){
		reservationsSpectacle.add(reservation);
	}
	
	public void removeReservation(int index){
		reservationsSpectacle.remove(index);
	}
	
	public int getIdSpectacle() {
		return idSpectacle;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getArtiste() {
		return artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPlacesDispos() {
		return placesDispos;
	}

	public void setPlacesDispos(int placesDispos) {
		this.placesDispos = placesDispos;
	}

	@Override
	public String toString() {
		return "Spectacle [idSpectacle=" + idSpectacle + ", titre=" + titre + ", artiste=" + artiste + ", lieu=" + lieu
				+ ", date=" + date + ", placesDispos=" + placesDispos + "]";
	}
	
}
