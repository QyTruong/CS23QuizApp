package com.pqt.quizapp;

import com.pqt.utils.MyAlert;
import com.pqt.utils.MyStage;
import com.pqt.utils.theme.Theme;
import com.pqt.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;



public class PrimaryController implements Initializable{
    @FXML private ComboBox<Theme> cbThemes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void HandleTheme(ActionEvent event){
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme();
        ThemeManager.applyTheme(this.cbThemes.getScene());
    }
    
    public void HandleQuizManagement(ActionEvent event) throws IOException{
        MyStage.getInstance().showStage("question.fxml");
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

