/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.pojo;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class Choice {
    private int id;
    private String content;
    private boolean correct;
    
    public Choice(int id, String content, boolean correct){
        this.id = id;
        this.content = content;
        this.correct = correct;
    }
    
    public Choice(String content, boolean correct){
        this.content = content;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isCorrect() {
        return correct;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
