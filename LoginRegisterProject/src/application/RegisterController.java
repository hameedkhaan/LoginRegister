package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private Label info;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField phoneField;
   
    @FXML
    void submitAction(ActionEvent event) {
    	
    	if(firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()
    			|| usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
    		info.setTextFill(Color.BLUE);
    		info.setText("All fields need to be filled");
    	}
    		
    	else if(event.getSource() == btnSubmit) {
    		 insertUser();
    		info.setText("Record Addes Successfully");
     	}     
    }

    @FXML
    void clearAction(ActionEvent event) {
            if(event.getSource() == btnClear) {
            	firstnameField.setText("");
            	lastnameField.setText("");
            	emailField.setText("");
            	phoneField.setText("");
            	usernameField.setText("");
            	passwordField.setText("");
            }    
    }

    @FXML
    void cancelAction(ActionEvent event) {
             System.exit(0);
    }
    
    @FXML
    void loginAction(ActionEvent event) {
    	loadStage("Login.fxml");  	
    }
    
    public void loadStage(String fxml) {
    		
    	try {
    		Parent root;
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,600,450);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void insertUser() {
    	getUser(); 	
    }
    
    public void getUser() {
    	DbUtil connection = new DbUtil();
    	Connection conn = connection.getConnection();
    	String query = 
    			
    			" INSERT INTO user VALUES\n"
    			
    			+ " ("+"default"+ ",'"
    			+firstnameField.getText() +"',\n"
    			+ " '"+lastnameField.getText() +"',\n"
    			+ " '"+emailField.getText() +"',\n"
    			+ " '"+phoneField.getText() + "',\n"
    			+ " '"+usernameField.getText() + "',\n"
    			+ " '"+passwordField.getText()+"'); ";
    	
    	Statement st;
    	
    	try   {
    		
    		st = conn.createStatement();
    		st.executeUpdate(query);
    				
    	}catch(Exception e) {
    		System.out.println("Error inserting query " + e.getMessage());
    	}
    
    }
}
