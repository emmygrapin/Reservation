package fr.eni.reservation.bo;

import java.util.Date;

public class Spectacle {
	
	private int idSpectacle;
	private String titre;
	private String artiste;
	private String lieu;
	private Date date;
	private int placesDispos;
	
	public Spectacle(int idSpectacle, String titre, String artiste, String lieu, Date date, int placesDispos) {
		super();
		this.idSpectacle = idSpectacle;
		this.titre = titre;
		this.artiste = artiste;
		this.lieu = lieu;
		this.date = date;
		this.placesDispos = placesDispos;
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
