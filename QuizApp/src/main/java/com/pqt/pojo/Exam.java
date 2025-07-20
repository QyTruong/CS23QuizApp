/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.pojo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class Exam {
    private int id;
    private String title;
    private LocalDateTime createdDate;
    private List<Question> questions;

    public Exam(List<Question> questions) {
        this.title = String.format("Exam-%s", LocalDateTime.now());
        this.createdDate = LocalDateTime.now();
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    
}
