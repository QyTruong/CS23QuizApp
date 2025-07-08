/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pqt.quizapp;

import com.pqt.pojo.Category;
import com.pqt.pojo.Choice;
import com.pqt.pojo.Level;
import com.pqt.pojo.Question;
import com.pqt.services.CategoryServices;
import com.pqt.services.LevelServices;
import com.pqt.services.question.BaseQuestionServices;
import com.pqt.services.question.CategoryQuestionServicesDecorator;
import com.pqt.services.question.KeywordQuestionServicesDecorator;
import com.pqt.services.question.LevelQuestionServicesDecorator;
import com.pqt.services.question.QuestionServices;
import com.pqt.utils.Configs;
import com.pqt.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionController implements Initializable {
    // UI
    @FXML private VBox vboxChoices;
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Category> cbSearchCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private ComboBox<Level> cbSearchLevels;
    @FXML private TextArea txtContent;
    @FXML private ToggleGroup toggleChoice = new ToggleGroup();
    @FXML private TableView<Question> tbQuestion;
    @FXML private TextField txtSearch;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.levelService.getLevels()));
            
            this.loadColumn();
            this.tbQuestion.setItems(FXCollections.observableList(Configs.questionService.list()));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        this.txtSearch.textProperty().addListener((e) -> {
            try {
                BaseQuestionServices s = new KeywordQuestionServicesDecorator(this.txtSearch.getText(), Configs.questionService);
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        this.cbSearchCates.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionServices s = new CategoryQuestionServicesDecorator(this.cbSearchCates.getSelectionModel().getSelectedItem(), Configs.questionService);
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionServices s = new LevelQuestionServicesDecorator(this.cbSearchLevels.getSelectionModel().getSelectedItem(), Configs.questionService);
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        
    }    
    
    public void handleMoreChoice(ActionEvent event){
        HBox h = new HBox();
        h.getStyleClass().add("Main");
        
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        
        h.getChildren().addAll(r, txt);
        
        this.vboxChoices.getChildren().add(h);
    }
    
    public void handleQuestion(ActionEvent event){
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem()
            );
            
            for (var c : vboxChoices.getChildren()){
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField)h.getChildren().get(1)).getText(),
                        ((RadioButton)h.getChildren().get(0)).isSelected()
                );
                
                b.addchoice(choice);
            }
            
            Question q = b.build();
            Configs.uQService.addQuestion(q);
            MyAlert.GetInstance().ShowMessage("Them cau hoi thanh cong");

        }
        catch (SQLException ex){
            MyAlert.GetInstance().ShowMessage("Them cau hoi that bai");
        }
        catch (Exception ex){
            MyAlert.GetInstance().ShowMessage("Du lieu khong hop le");
        }
    }
    
    private void loadColumn(){
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn colContent = new TableColumn("Content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e) -> {
            TableCell cell = new TableCell();
            
            Button btn = new Button("Xóa");
            btn.setOnAction(event -> {
                Optional<ButtonType> type = MyAlert.GetInstance().ShowMessage("Nếu xóa câu hỏi thì các lựa chọn cũng sẽ bị xóa theo. Bạn có chắc chắn không ?:D", Alert.AlertType.CONFIRMATION);
                
                if (type.isPresent() && type.get().equals(ButtonType.OK)){
                    Question q = (Question)cell.getTableRow().getItem();
                    try {
                        if (Configs.uQService.deleteQuestion(q.getId()) == true)
                            MyAlert.GetInstance().ShowMessage("Xóa thành công!");
                        else
                            MyAlert.GetInstance().ShowMessage("Xóa thất bại");
                                    
                    } catch (SQLException ex) {
                        Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            });
            
            cell.setGraphic(btn);
            
            return cell;
        });
        
        this.tbQuestion.getColumns().addAll(colId, colContent, colAction);
    }
    
}
