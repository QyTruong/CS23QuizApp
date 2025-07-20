/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services;

import com.pqt.pojo.Account;
import com.pqt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class UpdateAccountServices {
        public void addAccount(Account acc) throws SQLException{
        Connection conn = JdbcConnector.getInstance().Connect();
        
        conn.setAutoCommit(false);
        
        String sql = "INSERT INTO account(username, password, phone, email) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, acc.getUsername());
        stm.setString(2, acc.getPassword());
        stm.setString(3, acc.getPhone());
        stm.setString(4, acc.getEmail());
        
        if(stm.executeUpdate() > 0)
            conn.commit();
        else
            conn.rollback();
    }

    
}
