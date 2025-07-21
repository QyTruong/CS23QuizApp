/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services.authorization;

/**
 *
 * @author PHAM QUY TRUONG
 */
public enum Role {
    TEACHER("TEACHER"),
    STUDENT("STUDENT");
    
    // Trường để lưu giá trị
    private final String roleName;

    // Constructor để gán giá trị cho trường
    Role(String roleName) {
        this.roleName = roleName;
    }

    // Phương thức getter để lấy giá trị
    public String getRoleName() {
        return this.roleName;
    }
}
