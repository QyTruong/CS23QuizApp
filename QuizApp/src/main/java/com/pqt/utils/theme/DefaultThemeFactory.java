/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils.theme;

import com.pqt.quizapp.App;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class DefaultThemeFactory implements ThemeFactory{

    @Override
    public String getStyleSheet() {
        return App.class.getResource("style.css").toExternalForm();
    }

}
