/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Choice;
import com.pqt.pojo.Question;
import com.pqt.services.exam.BaseExamServices;
import com.pqt.services.exam.ExamTypes;
import com.pqt.services.exam.FixedExamServices;
import com.pqt.services.exam.SpecificExamServices;
import com.pqt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {
    @FXML private ComboBox<ExamTypes> cbTypes;
    @FXML private TextField txtNum;
    @FXML private ListView<Question> lvQuestions;
            
    
    private BaseExamServices exServices;
    private List<Question> questions;
    private Map<Integer, Choice> results;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtNum.setVisible(false);
        
        this.cbTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (cbTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
                this.txtNum.setVisible(true);
            else
                this.txtNum.setVisible(false);
        });
        
        this.cbTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
    }    
    
    public void handleStart(ActionEvent event) throws SQLException{
        if (this.cbTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
            exServices = new SpecificExamServices(Integer.parseInt(this.txtNum.getText()));
        else
            exServices = new FixedExamServices();
        
        this.results = new HashMap<>();
        
        this.questions = exServices.getQuestions();
        
        this.lvQuestions.setItems(FXCollections.observableArrayList(questions));
        
        this.lvQuestions.setCellFactory(param -> new ListCell<Question>(){
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);
                
                if (question == null || empty == true)
                    this.setGraphic(null);
                else {
                    VBox v = new VBox(5);
                    
                    v.setStyle("-fx-padding: 10; -fx-border-color: gray; -fx-border-width: 3;");
                    
                    Text t = new Text(question.getContent());
                    v.getChildren().add(t);
                    
                    ToggleGroup g = new ToggleGroup();
                    
                    for (var c : question.getChoices()){
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(g);
                        
                        if (results.get(question.getId()) == c)
                            r.setSelected(true);
                        
                        r.setOnAction(e -> {
                            if (r.isSelected())
                                results.put(question.getId(), c);
                        });
                        
                        v.getChildren().add(r);
                    }
                    
                    this.setGraphic(v);
                }
                
            }
            
        });
    }
    
    public void handleFinish(ActionEvent event){
        int count = 0;
        for (var c : this.results.values()){
            if (c.isCorrect())
                count++;
        }
        
        MyAlert.GetInstance().ShowMessage(String.format("Bạn đã làm đúng %d / %d câu", count, this.results.size()), Alert.AlertType.INFORMATION);
    }
    
}
