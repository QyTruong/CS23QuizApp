/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services.exam;

import com.pqt.pojo.Question;
import com.pqt.services.question.BaseQuestionServices;
import com.pqt.services.question.LevelQuestionServicesDecorator;
import com.pqt.services.question.LimitQuestionServicesDecorator;
import com.pqt.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedExamServices extends BaseExamServices{

    @Override              
    public List<Question> getQuestions() throws SQLException {
        double[] rates = {0.4, 0,4, 0.2};
        
        List<Question> questions = new ArrayList<>();
        
        for (int i = 0; i < rates.length; i++){
            BaseQuestionServices s = new LimitQuestionServicesDecorator((int)(rates[i] * Configs.NUMBER_OF_QUESTIONS), new LevelQuestionServicesDecorator(i + 1, Configs.questionService));
            questions.addAll(s.list());
        }
        return questions;
    }
    
}
