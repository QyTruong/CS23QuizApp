/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.utils.theme;

/**
 *
 * @author PHAM QUY TRUONG
 * 
 * 
 */
public enum Theme {
    DEFAULT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new DefaultThemeFactory());
        }
    },
    
    LIGHT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new LightThemeFactory());
        }
    },
    
    DARK {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new DarkThemeFactory());
        }
    };
    
    public abstract void updateTheme();
}
