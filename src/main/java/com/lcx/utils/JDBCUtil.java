package com.lcx.utils;

import java.sql.*;

public class JDBCUtil {
    private static final String driver = "org.apache.hive.jdbc.HiveDriver";
    private static final String url = "jdbc:hive2://192.168.88.110:10000/db_user";
    private static final String userName = "root";
    private static final String passwd = "123456";
    public static Connection conn = null;


    //获取连接对象
    public static Connection getConnection(){
        //加载驱动
        try {
            Class.forName(driver);
            //获取连接对象
            conn = DriverManager.getConnection(url, userName, passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关流
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
