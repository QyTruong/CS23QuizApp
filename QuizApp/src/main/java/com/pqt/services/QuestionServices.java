/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services;

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
 * @author PHAM QUY TRUONG
 */
public class QuestionServices {
    public void addQuestion(Question q) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        conn.setAutoCommit(false);
        
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());
        
        if (stm.executeUpdate() > 0){
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            
            if (rs.next())
                questionId = rs.getInt(1);
            
            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);
            
            for (var c : q.getChoices()){
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);
                stm.executeUpdate();
            }
            
            conn.commit();
        }
        else {
            conn.rollback();
        }
    }
    
    public boolean deleteQuestion(int id) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, id);
        
        return stm.executeUpdate() > 0;
    }
    
    public List<Question> getQuestion() throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");
        
        List<Question> questions = new ArrayList<Question>();
        while (rs.next()){
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        
        return questions;
    }
    
    public List<Question> getQuestion(String keyword) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%') ORDER BY id DESC");
        stm.setString(1, keyword);
        
        ResultSet rs = stm.executeQuery();
        
        List<Question> questions = new ArrayList<Question>();
        while (rs.next()){
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        
        return questions;
    }
    
    public List<Question> getQuestion(int num) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);
        
        ResultSet rs = stm.executeQuery();
        
        List<Question> questions = new ArrayList<Question>();
        while (rs.next()){
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).addAllChoices(this.getChoicesByQuestion(rs.getInt("id"))).build();
            
            questions.add(q);
        }
        
        return questions;
    }
    
    public List<Choice> getChoicesByQuestion(int questionId) throws SQLException{
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
