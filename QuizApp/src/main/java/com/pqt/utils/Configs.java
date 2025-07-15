/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils;

import com.pqt.services.CategoryServices;
import com.pqt.services.LevelServices;
import com.pqt.services.question.QuestionServices;
import com.pqt.services.UpdateQuestionServices;
import com.pqt.services.question.BaseQuestionServices;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class Configs {
    public static final CategoryServices cateService = new CategoryServices();
    public static final LevelServices levelService = new LevelServices();
    public static BaseQuestionServices questionService = new QuestionServices();
    public static UpdateQuestionServices uQService = new UpdateQuestionServices();
    public static final int NUMBER_OF_QUESTIONS = 10;
    
}
