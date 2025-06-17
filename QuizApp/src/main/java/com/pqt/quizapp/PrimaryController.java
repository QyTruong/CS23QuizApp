package com.pqt.quizapp;

import com.pqt.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class PrimaryController {
    public void HandleQuizManagement(ActionEvent event) throws IOException{
        
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("question.fxml")).load());
   
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz stage");
        stage.show();
    }
    public void HandlePraticeManagement(){
        MyAlert.GetInstance().ShowMessage("Pratice: comming soon....");
    }
    public void HandleTestManagement(){
        MyAlert.GetInstance().ShowMessage("Test: comming soon....");
    }
    public void HandleRegisterManagement(){
        MyAlert.GetInstance().ShowMessage("Register: comming soon....");
    }
    public void HandleLoginManagement(){
        MyAlert.GetInstance().ShowMessage("Login: comming soon....");
    }
}
