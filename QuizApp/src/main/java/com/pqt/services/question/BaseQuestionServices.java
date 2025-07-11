/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pqt.services.question;

import com.pqt.pojo.Choice;
import com.pqt.pojo.Question;
import com.pqt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public interface BaseQuestionServices {
    String getSQL(List<Object> params);
    
    default public List<Question> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        List<Object> params = new ArrayList<>();
        PreparedStatement stm = conn.prepareCall(this.getSQL(params));
        
        for (int i = 0; i < params.size(); i++){
            stm.setObject(i + 1, params.get(i));
        }
        
        ResultSet rs = stm.executeQuery();
        
        List<Question> questions = new ArrayList<Question>();
        while (rs.next()){
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        
        return questions;
    }
    
    default public List<Choice> getChoicesByQuestion(int questionId) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionId);
        
        ResultSet rs = stm.executeQuery();
        
        List<Choice> choices = new ArrayList<>();
        while (rs.next()){
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }
        
        return choices;
    }
}
