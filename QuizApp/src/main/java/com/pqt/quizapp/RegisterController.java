/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Account;
import com.pqt.utils.Configs;
import com.pqt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class RegisterController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPasswordConfirm;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private Button btnConfirm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void handleRegister(){
        try {
            if (this.txtUsername.getText().isEmpty() || this.txtPassword.getText().isEmpty()){
                MyAlert.GetInstance().ShowMessage("Vui lòng nhập đầy đủ tài khoản và mật khẩu", Alert.AlertType.WARNING);
                return;
            }
            
            if (!this.txtPassword.getText().equals(this.txtPasswordConfirm.getText())){
                MyAlert.GetInstance().ShowMessage("Mật khẩu xác nhận không khớp với mật khẩu ban đầu", Alert.AlertType.INFORMATION);
                return;
            }
            
            Account.Builder b = new Account.Builder(this.txtUsername.getText(), this.txtPassword.getText());
            
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
