package fr.eni.reservation.bo;

import java.util.List;

public class Client {
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private String emailClient;
	private String adresseClient;
	private String cpClient;
	private String villeClient;
	private List<Reservation> reservationsClient;
	
	public Client(int idClient, String nomClient, String prenomClient, String emailClient, String adresseClient,
			String cpClient, String villeClient) {
		
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.adresseClient = adresseClient;
		this.cpClient = cpClient;
		this.villeClient = villeClient;
		
	}
	
	public Client(String nomClient, String prenomClient, String emailClient, String adresseClient,
			String cpClient, String villeClient) {
		
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.emailClient = emailClient;
		this.adresseClient = adresseClient;
		this.cpClient = cpClient;
		this.villeClient = villeClient;
	}
	
	public List<Reservation> getReservations(){
		return reservationsClient;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getEmailClient() {
		return emailClient;
	}
	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}
	public String getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
	public String getCpClient() {
		return cpClient;
	}
	public void setCpClient(String cpClient) {
		this.cpClient = cpClient;
	}
	public String getVilleClient() {
		return villeClient;
	}
	public void setVilleClient(String villeClient) {
		this.villeClient = villeClient;
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient
				+ ", emailClient=" + emailClient + ", adresseClient=" + adresseClient + ", cpClient=" + cpClient
				+ ", villeClient=" + villeClient + "]";
	}
	
}
