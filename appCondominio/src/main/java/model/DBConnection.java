/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class DBConnection {
    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/poo3?useSSL=false&serveTimezone=UTC";
                String usr="root";
                String senha ="1234";
                connection = DriverManager.getConnection(url,usr,senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
