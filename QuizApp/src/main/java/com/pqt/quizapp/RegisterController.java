/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Account;
import com.pqt.services.FlyweightFactory;
import com.pqt.services.authorization.Role;
import com.pqt.utils.Configs;
import com.pqt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class RegisterController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPasswordConfirm;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<Role> cbRoles;
    @FXML private Button btnConfirm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbRoles.setItems(FXCollections.observableArrayList(Role.values()));
    }    
    
    public void handleRegister(){
        try {
            if (!this.txtPassword.getText().equals(this.txtPasswordConfirm.getText())){
                MyAlert.GetInstance().ShowMessage("Mật khẩu xác nhận không khớp với mật khẩu ban đầu", Alert.AlertType.INFORMATION);
                return;
            }
            
            if (this.txtUsername.getText().isEmpty() || this.txtPassword.getText().isEmpty() || this.cbRoles.getSelectionModel().isEmpty()){
                MyAlert.GetInstance().ShowMessage("Vui lòng nhập đầy đủ tài khoản, mật khẩu và vai trò", Alert.AlertType.WARNING);
                return;
            }
    
            List<Account> accounts = FlyweightFactory.getData(Configs.lgService, "accounts");
            for (var a : accounts){
                if (a.getUsername().equals(this.txtUsername.getText())){
                    MyAlert.GetInstance().ShowMessage("Tài khoản này đã tồn tại", Alert.AlertType.WARNING);
                    return;
                }         
            }
            
            Account.Builder b = new Account.Builder(this.txtUsername.getText(), this.txtPassword.getText(), this.cbRoles.getSelectionModel().getSelectedItem().name());
            
            if (this.txtPhone != null)
                b.addPhone(this.txtPhone.getText());
            if (this.txtEmail != null)
                b.addEmail(this.txtEmail.getText());
            
            Account acc = b.build();
            Configs.uAService.addAccount(acc);
            MyAlert.GetInstance().ShowMessage("Đăng ký thành công !!!", Alert.AlertType.CONFIRMATION);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            MyAlert.GetInstance().ShowMessage("Đăng ký thất bại", Alert.AlertType.WARNING);
        }
        catch (Exception ex){
            MyAlert.GetInstance().ShowMessage("Dữ liệu không hợp lệ", Alert.AlertType.WARNING);
        }
    }
    
}
