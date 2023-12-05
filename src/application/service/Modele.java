package application.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import entite.Image;
import entite.User;
import entite.UserImage;
import javafx.fxml.Initializable;

public class Modele {	

	private ArrayList<User> lstUser;
	private ArrayList<Image> lstImage;
	private ArrayList<UserImage> lstUserImage;
	
	public Modele() {
		this.InitImageDatabase();
		this.InitUserDatabase();
		this.InitUserImageDatabase();
	}
	
	public ArrayList<User> getLstUser() {
		return lstUser;
	}

	public void setLstUser(ArrayList<User> lstUser) {
		this.lstUser = lstUser;
	}

	public ArrayList<Image> getLstImage() {
		return lstImage;
	}

	public void setLstImage(ArrayList<Image> lstImage) {
		this.lstImage = lstImage;
	}

	public ArrayList<UserImage> getLstUserImage() {
		return lstUserImage;
	}

	public void setLstUserImage(ArrayList<UserImage> lstUserImage) {
		this.lstUserImage = lstUserImage;
	}
	
	public ArrayList<Image> InitImageDatabase()
	{
		ArrayList<Image> lstImage = new ArrayList<Image>();
		
		Image paris = new Image("Paris","assets/paris.jpg");
		lstImage.add(paris);
		Image pau = new Image("Pau","assets/pau.jpg");
		lstImage.add(pau);
		Image bordeaux = new Image("Bordeaux","assets/bordeaux.jpg");
		lstImage.add(bordeaux);
		
		this.lstImage = lstImage;
		return this.lstImage;
	}
	
	public ArrayList<User> InitUserDatabase()
	{
		ArrayList<User> lstUser = new ArrayList<User>();
		
		User bob = new User("Bob","test");
		lstUser.add(bob);
		User alice = new User("Alice","test");
		lstUser.add(alice);
		User jack = new User("Jack","test");
		lstUser.add(jack);
		
		this.lstUser = lstUser;
		return this.lstUser;
	}
	
	public ArrayList<UserImage> InitUserImageDatabase()
	{
		Data data = new Data();
		User bob = this.lstUser.stream().filter(s -> s.getLogin().equals("Bob")).findFirst().orElse(null);
		User alice = this.lstUser.stream().filter(s -> s.getLogin().equals("Alice")).findFirst().orElse(null);
		User jack = this.lstUser.stream().filter(s -> s.getLogin().equals("Jack")).findFirst().orElse(null);
		
		Image paris = this.lstImage.stream().filter(s -> s.getNom().equals("Paris")).findFirst().orElse(null);
		Image pau = this.lstImage.stream().filter(s -> s.getNom().equals("Pau") ).findFirst().orElse(null);
		Image bordeaux = this.lstImage.stream().filter(s -> s.getNom().equals("Bordeaux")).findFirst().orElse(null);
		
		ArrayList<UserImage> lstUserImage = new ArrayList<UserImage>();
		
		UserImage bobparis = new UserImage(bob, paris, data.getGrade(bob.getLogin(), paris.getNom()));
		lstUserImage.add(bobparis);
		UserImage alicepau = new UserImage(alice, pau, data.getGrade(alice.getLogin(), pau.getNom()));
		lstUserImage.add(alicepau);
		UserImage jackbordeaux = new UserImage(jack,bordeaux, data.getGrade(jack.getLogin(), bordeaux.getNom()));
		lstUserImage.add(jackbordeaux);
		UserImage jackpau = new UserImage(jack,pau, data.getGrade(jack.getLogin(), pau.getNom()));
		lstUserImage.add(jackpau);
		
		this.lstUserImage = lstUserImage;
		return this.lstUserImage;
	}
	
	public boolean CheckCredentials(String login, String password)
	{		
		User user = getUserByUserLogin(login);
		
		if(user.getLogin().equals(login) && user.getPassword().equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void GradeImage(Image image, User user, int note)
	{
		Data data = new Data();
		UserImage userImage = this.lstUserImage.stream().filter(s -> image.equals(s.getImage()) && user.equals(s.getUser())).findFirst().orElse(null);
		if(userImage != null)
		{
			userImage.setNote(note);
			data.writeGradeLine(userImage.getHistoryLine());
		}
	}
	
	public String getURLImage(String nomImage)
	{
		for(int i = 0 ; i < this.lstImage.size(); i++)
		{
			if(this.lstImage.get(i).getNom() == nomImage)
			{
				return this.lstImage.get(i).getUrl();
			}
		}
		return null;
	}
	
	public ArrayList<Image> getLstImageByUserLogin(String login)
	{
		User user = getUserByUserLogin(login);
		ArrayList<UserImage> lstUserImage = getUserImageByUser(user);
		
		ArrayList<Image> lstImageRes = new ArrayList<Image>();
		for(int i = 0 ; i < lstUserImage.size(); i++)
		{
			if(lstUserImage.get(i).getUser() == user)
			{
				lstImageRes.add(lstUserImage.get(i).getImage());
			}
		}
		
		return lstImageRes;
	}
	
	public User getUserByUserLogin(String login)
	{
		for(int i = 0 ; i < this.lstUser.size(); i++)
		{
			if(this.lstUser.get(i).getLogin().equals(login))
			{
				return this.lstUser.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<UserImage> getUserImageByUser(User user)
	{
		ArrayList<UserImage> lstUserImageRes = new ArrayList<UserImage>();
		for(int i = 0 ; i < this.lstUserImage.size(); i++)
		{
			if(this.lstUserImage.get(i).getUser() == user)
			{
				lstUserImageRes.add(this.lstUserImage.get(i));
			}
		}
		return lstUserImageRes;
	}
	
	public Image getImageByNomImage(String nom)
	{
		Image imageRes = null;
		for(int i = 0 ; i < this.lstImage.size(); i++)
		{
			if(this.lstImage.get(i).getNom() == nom)
			{
				imageRes = this.lstImage.get(i);
			}
		}
		return imageRes;
	}
	
	public UserImage getUserImageByLoginAndNomImage(String login, String nomImage)
	{
		UserImage userImageRes = null;
		for(int i = 0 ; i < this.lstUserImage.size(); i++)
		{
			if(this.lstUserImage.get(i).getUser().getLogin().equals(login) && this.lstUserImage.get(i).getImage().getNom().equals(nomImage))
			{
				userImageRes = this.lstUserImage.get(i);
			}
		}
		return userImageRes;
	}
}
