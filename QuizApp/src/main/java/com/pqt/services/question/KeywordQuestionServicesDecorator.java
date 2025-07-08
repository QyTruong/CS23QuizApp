/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services.question;

import java.util.List;

/**
 *
 * @author admin
 */
public class KeywordQuestionServicesDecorator extends QuestionServicesDecorator{
    private String kw;

    public KeywordQuestionServicesDecorator(String kw, BaseQuestionServices decorator) {
        super(decorator);
        this.kw = kw;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND content like concat('%', ?, '%')";
        params.add(kw);
        
        return sql;
    }
    
    
}
