/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Account;
import com.pqt.services.FlyweightFactory;
import com.pqt.utils.Configs;
import com.pqt.utils.LoginSession;
import com.pqt.utils.MyAlert;
import com.pqt.utils.MyStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PHAM QUY TRUONG
 */
public class LoginController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private Button btnConfirm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleLogin(ActionEvent event) throws SQLException, IOException{
        if (this.txtUsername.getText().isEmpty() || this.txtPassword.getText().isEmpty()){
            MyAlert.GetInstance().ShowMessage("Xin vui lòng điền đầy đủ thông tin", Alert.AlertType.WARNING);
            return;
        }
        
        List<Account> accounts = FlyweightFactory.getData(Configs.lgService, "accounts");
        Account.Builder b = new Account.Builder(this.txtUsername.getText(), this.txtPassword.getText());
        Account acc = b.build();
        
        for (var a : accounts){
            if (a.getUsername().equals(acc.getUsername()) && a.getPassword().equals(acc.getPassword())){
                LoginSession.createSession(acc);
                MyAlert.GetInstance().ShowMessage(String.format("Chào %s, bạn đã đăng nhập thành công", LoginSession.getInstance().getAccount().getUsername()), Alert.AlertType.CONFIRMATION);
                MyStage.getInstance().backRootScene();
                return;
            }
        }
        MyAlert.GetInstance().ShowMessage("Tài khoản này không tồn tại", Alert.AlertType.WARNING);
    }
}
