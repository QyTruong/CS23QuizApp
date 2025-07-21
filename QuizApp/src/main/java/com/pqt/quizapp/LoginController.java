/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Account;
import com.pqt.services.FlyweightFactory;
import com.pqt.services.authorization.Role;
import com.pqt.utils.Configs;
import com.pqt.utils.LoginSession;
import com.pqt.utils.MyAlert;
import com.pqt.utils.MyStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PHAM QUY TRUONG
 */
public class LoginController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private ComboBox<Role> cbRoles;
    @FXML private Button btnConfirm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cbRoles.setItems(FXCollections.observableArrayList(Role.values()));
        
    }    
    
    public void handleLogin(ActionEvent event) throws SQLException, IOException{      
        if (this.txtUsername.getText().isEmpty() || this.txtPassword.getText().isEmpty() || this.cbRoles.getSelectionModel().isEmpty()){
                MyAlert.GetInstance().ShowMessage("Vui lòng nhập đầy đủ tài khoản, mật khẩu và vai trò", Alert.AlertType.WARNING);
                return;
        }
        
        if (LoginSession.getInstance() != null){
            MyAlert.GetInstance().ShowMessage("Hãy đăng xuất tài khoản hiện tại", Alert.AlertType.WARNING);
            return;
        }
        
        List<Account> accounts = FlyweightFactory.getData(Configs.lgService, "accounts");
        Account.Builder b = new Account.Builder(this.txtUsername.getText(), this.txtPassword.getText(), this.cbRoles.getSelectionModel().getSelectedItem().name());
        
        Account acc = b.build();
        
        for (var a : accounts){
            if (a.getUsername().equals(acc.getUsername()) && a.getPassword().equals(acc.getPassword()) && a.getRole().equals(acc.getRole())){
                LoginSession.createSession(acc);
                MyAlert.GetInstance().ShowMessage(String.format("Chào %s, bạn đã đăng nhập thành công", LoginSession.getInstance().getAccount().getUsername()), Alert.AlertType.CONFIRMATION);
                MyStage.getInstance().backRootScene();
                return;
            }
        }
        MyAlert.GetInstance().ShowMessage("Tài khoản này không tồn tại", Alert.AlertType.WARNING);
    }
}
