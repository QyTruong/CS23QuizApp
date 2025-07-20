package com.pqt.quizapp;

import com.pqt.utils.LoginSession;
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
import javafx.scene.control.Alert;
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
        if (LoginSession.isLoggedIn())
            MyStage.getInstance().showStage("question.fxml");
        else
            MyAlert.GetInstance().ShowMessage("Bạn chưa đăng nhập", Alert.AlertType.WARNING);
    }
    public void HandlePraticeManagement() throws IOException{
        if (LoginSession.isLoggedIn())
            MyStage.getInstance().showStage("practice.fxml");
        else
            MyAlert.GetInstance().ShowMessage("Bạn chưa đăng nhập", Alert.AlertType.WARNING);
    }
    public void HandleTestManagement() throws IOException{
        if (LoginSession.isLoggedIn())
         MyStage.getInstance().showStage("exam.fxml");
        else
            MyAlert.GetInstance().ShowMessage("Bạn chưa đăng nhập", Alert.AlertType.WARNING);
    }
    public void HandleRegisterManagement() throws IOException{
        MyStage.getInstance().showStage("register.fxml");
    }
    public void HandleLoginManagement() throws IOException{
        MyStage.getInstance().showStage("login.fxml");
    }

}

