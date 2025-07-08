/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Category;
import com.pqt.pojo.Question;
import com.pqt.services.question.BaseQuestionServices;
import com.pqt.services.question.LimitQuestionServicesDecorator;
import com.pqt.utils.Configs;
import com.pqt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class PracticeController implements Initializable{
    @FXML private TextField txtNum;
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    
    
    @FXML private Text txtContent;
    @FXML private VBox vboxChoices;  
    @FXML private List<Question> questions;
    private int currentIndex = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            this.cbCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
//            this.cbLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
//        } catch (SQLException ex) {
//            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
//        } 
    }
    
    public void start(ActionEvent event) throws SQLException{
        try {
            
            BaseQuestionServices s = Configs.questionService;
            Category c = this.cbCates.getSelectionModel().getSelectedItem();
            
            int num = Integer.parseInt(txtNum.getText());
            //BaseQuestionServices s = new LimitQuestionServicesDecorator(num, Configs.questionService);
            
            questions = s.list();
            currentIndex = 0;
            loadQuestion();
            
        } catch(InputMismatchException ex) {
            MyAlert.GetInstance().ShowMessage("Dữ liệu không hợp lệ", Alert.AlertType.WARNING);
        }
        
        
    }
            
    public void check(ActionEvent event){
        Question q = this.questions.get(this.currentIndex);
        
        for (int i = 0; i < q.getChoices().size(); i++){
            if (q.getChoices().get(i).isCorrect()){
                HBox h = (HBox) this.vboxChoices.getChildren().get(i);
                if (((RadioButton)h.getChildren().get(0)).isSelected())
                    MyAlert.GetInstance().ShowMessage("CORRECT, GUD JOB !!!!");
                else
                    MyAlert.GetInstance().ShowMessage("INCORRECT, Try harder next time");
            }
        }
    }
    
    public void next(ActionEvent event){
        currentIndex++;
        loadQuestion();
    }
    
    public void loadQuestion(){
        // Lấy câu hỏi hiện tại ra và set text cho tiêu đề
        Question q = this.questions.get(this.currentIndex);
        this.txtContent.setText(q.getContent());
        
        // Mỗi lần load câu hỏi mới -> xóa cái cũ đi
        this.vboxChoices.getChildren().clear();
        
        ToggleGroup group = new ToggleGroup();
        
        for (var c: q.getChoices()){
            HBox h = new HBox();
            
            // Tạo radio cho choices         
            RadioButton rdo = new RadioButton();
            rdo.setToggleGroup(group);
            
            // Lấy từng cái text cho mỗi cái radio
            Text txt = new Text(c.getContent());
            
            // Ghép radio và text chung 1 hàng
            h.getChildren().addAll(rdo, txt);
            
            // Ghép các lựa chọn chung 1 cột
            this.vboxChoices.getChildren().add(h);
        }
    
    }
    
}
