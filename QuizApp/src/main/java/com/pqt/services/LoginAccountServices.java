/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pqt.services;

import com.pqt.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHAM QUY TRUONG
 */
public class LoginAccountServices extends BaseServices<Account>{

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM account");
    }

    @Override
    public List<Account> getResults(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()){
            Account.Builder b = new Account.Builder(rs.getString("username"), rs.getString("password"));
            Account acc = b.build();
            accounts.add(acc);
        }
        return accounts;
    }
    
}
