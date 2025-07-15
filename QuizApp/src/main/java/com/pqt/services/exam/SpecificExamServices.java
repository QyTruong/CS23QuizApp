/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services.exam;

import com.pqt.pojo.Question;
import com.pqt.services.question.BaseQuestionServices;
import com.pqt.services.question.LimitQuestionServicesDecorator;
import com.pqt.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamServices extends BaseExamServices{
    private int num;

    public SpecificExamServices(int num) {
        this.num = num;
    }
    
    @Override
    public List<Question> getQuestions() throws SQLException{
        BaseQuestionServices s = new LimitQuestionServicesDecorator(num, Configs.questionService);
        return s.list();
    }
    
}
