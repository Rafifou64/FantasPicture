package entite;

public class Image {
	
	private String nom;
	private String url;
	
	public Image(String nom, String url) {
		super();
		this.nom = nom;
		this.url = url;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
