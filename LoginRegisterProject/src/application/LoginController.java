package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label info;
  
    @FXML
    void cancelAction(ActionEvent event) {
    	System.exit(0);
    }
    
    public void closeStage() {
    	Stage stage = new Stage();
    	stage.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void loginAction(ActionEvent event) {	
    	if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
        		info.setTextFill(Color.RED);
        	    info.setText("Provide valid username and password");		
    	}
    
    	else {
    		validateLogin(); 					
    	}	
    }

    @FXML
    void signupAction(ActionEvent event) {
    	loadStage2("Register.fxml");

    }
    
    public void loadStage(String fxml) {
    	
    	Parent root ;
    	try {
    			root = FXMLLoader.load(getClass().getResource("/Wellcome.fxml"));
    			Scene scene = new Scene(root,600,400);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.setTitle("Wellcome");
    			stage.show();

    	}catch(Exception e) {
    		System.out.println("failed loading fxml file " + e.getMessage());
    	}
    } 
    
     public void loadStage2(String fxml) {
    	
    	Parent root ;
    	try {
    			root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
    			Scene scene = new Scene(root,600,400);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.setTitle("Register");
    			stage.show();

    	}catch(Exception e) {
    		
    	}
    }
     
    public void validateLogin() {
    	
    	DbUtil connection = new DbUtil();
    	Connection conn;
    	Statement st;
    	ResultSet rs;
    	
    	String query = " SELECT COUNT(1) FROM user WHERE username = '"+ 
    	usernameField.getText() +"'AND password = '" +passwordField.getText()+ "'  ";  	
    	
    	try {
    		conn = connection.getConnection();
    		st = conn.createStatement();
    		rs = st.executeQuery(query);
    		
    		while(rs.next()) {
    			if(rs.getInt(1)== 1) {
    				info.setTextFill(Color.BLUE);
    				info.setText("login Successfull");
    				loadStage("/Wellcome.fxml");
    				
    			}
    			else {
    				info.setTextFill(Color.RED);
    				info.setText("login Failed");
    			}
    		}
    			
    	}catch(Exception e) {
    		System.out.println("login failed " + e.getMessage());
    	}
    }
}
