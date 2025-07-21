package com.pqt.quizapp;

import com.pqt.services.authorization.Role;
import com.pqt.utils.LoginSession;
import com.pqt.utils.MyAlert;
import com.pqt.utils.MyStage;
import com.pqt.utils.theme.Theme;
import com.pqt.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void HandleQuizManagement(ActionEvent event) throws IOException {
        if (LoginSession.isLoggedIn() && LoginSession.getInstance().getAccount().getRole().equals(Role.STUDENT.getRoleName())){
            MyAlert.GetInstance().ShowMessage("Sinh viên không thể thực hiện chức năng này", Alert.AlertType.WARNING);
            return;
        }      
        if (LoginSession.isLoggedIn() && LoginSession.getInstance().getAccount().getRole().equals(Role.TEACHER.getRoleName()))
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
    
    public void HandleLogoutManagement() {
        if (LoginSession.getInstance() != null){
            LoginSession.deleteSession();
            MyAlert.GetInstance().ShowMessage(String.format("Đăng xuất tài khoản %s thành công", LoginSession.getInstance().getAccount().getUsername()), Alert.AlertType.CONFIRMATION);
        }
        else {
            MyAlert.GetInstance().ShowMessage("Bạn chưa đăng nhập tài khoản", Alert.AlertType.WARNING);
        }    
    }
}

