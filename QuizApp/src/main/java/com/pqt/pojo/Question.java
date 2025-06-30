/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.pojo;

import java.util.List;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class Question {
    private int id;
    private String content;
    private String hint;
    private String image;
    private Category category;
    private Level level;
    private List<Choice> choices;
    
    public Question(Builder b){
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.category = b.category;
        this.category = b.category;
        this.level = b.level;
        this.choices = b.choices;
    }
    
    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices;
        
        public Builder(String content, Category category, Level level) throws Exception {
            if (content.isEmpty() || category == null || level == null)
                throw new Exception("Invalid data");
            
            this.content = content;
            this.category = category;
            this.level = level;
        }
        
        public Builder addHint(String hint){
            this.hint = hint;
            return this;
        }
        
        public Builder addImage(String image){
            this.image = image;
            return this;
        }
        
        public Builder addchoice(Choice choice){
            this.choices.add(choice);
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getHint() {
        return hint;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public Level getLevel() {
        return level;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    
    
    
}
