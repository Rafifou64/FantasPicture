package entite;

import java.time.LocalDate;

public class UserImage {

	private Image image;
	private User user;
	private Integer note;
	
	public UserImage(User user, Image image, Integer note) {
		super();
		this.image = image;
		this.user = user;
		this.note = note;
	}

	public Image getImage() {
		return image;
	}
	
	public User getUser() {
		return user;
	}
	
	public Integer getNote() {
		return note;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setNote(int note) {
		this.note = note;
	}
	
	public String getHistoryLine() {
		return this.user.getLogin() + ";" + this.image.getNom() + ";" + Integer.toString(this.note) + "\n";
	}


}
