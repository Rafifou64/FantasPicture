package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.service.Modele;
import entite.Image;
import entite.User;
import entite.UserImage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class IndexController implements Initializable {
	
	@FXML
	private TextField login;
	
	@FXML
	private TextField password;
	
	@FXML
	private TextField note;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button btnValidateNote;
	
	@FXML
	private ListView<String> listViewImage;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Label errorLabel;
	
	private Modele modele;
	
	private String loginSaved;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.modele = new Modele();		
	}
	
	public void Login()
	{
		String login = this.login.getText();
		String password = this.password.getText();
		if(this.modele.CheckCredentials(login, password))
		{
			LoadListView(login);
			this.loginSaved = login;
			this.errorLabel.setText("");
		}
		else
		{
			this.errorLabel.setText("Mot de passe ou login incorrect");
		}
	}
	
	public void GradeImage()
	{
		int note = Integer.parseInt(this.note.getText());
		User user = this.modele.getUserByUserLogin(loginSaved);
		ObservableList selectedImage = listViewImage.getSelectionModel().getSelectedItems();
		Image image = this.modele.getImageByNomImage(selectedImage.get(0).toString());
		this.modele.GradeImage(image, user, note);
	}
	
	public void ChangeImage(MouseEvent event)
	{
		ObservableList selectedImage = listViewImage.getSelectionModel().getSelectedItems();		
		javafx.scene.image.Image image = new javafx.scene.image.Image(this.modele.getURLImage(selectedImage.get(0).toString()));
		imageView.setImage(image);
		
		UserImage userImage = this.modele.getUserImageByLoginAndNomImage(loginSaved, selectedImage.get(0).toString());
		
		if(userImage == null || userImage.getNote() == null)
		{
			this.note.setText("");
		}
		else
		{
			this.note.setText(Integer.toString(userImage.getNote()));
		}
	}
	
	public void LoadListView(String login)
	{
		this.listViewImage.getItems().clear();
		for(int i = 0 ; i < this.modele.getLstImageByUserLogin(login).size(); i++)
		{
			listViewImage.getItems().add(this.modele.getLstImageByUserLogin(login).get(i).getNom());
		}
	}

}
