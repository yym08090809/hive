package com.lcx.hive;

import java.sql.*;

public class HiveClientDemo01 {
    private static final String driver = "org.apache.hive.jdbc.HiveDriver";
    private static final String url = "jdbc:hive2://192.168.88.110:10000/db_user";
    private static final String userName = "root";
    private static final String passwd = "123456";

    //静态代码块
    //获取链接驱动
    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    static {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //创建表
    public static void createTable(String tableName) throws SQLException {
        String sql = "create table test111 (name string,sex string,age int)";
        ps = conn.prepareStatement(sql);
        ps.execute();
        System.out.println("创建"+tableName+"表成功");
        ps.close();
    }
    //插入数据
    public static void insertInfo(String tableName) throws SQLException {
        //String sql = "insert into test111 values('lisi','M',20)";
        String sql = "insert into test111 values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1,"wangwu");
        ps.setString(2, "nan");
        ps.setInt(3,20);
        ps.execute();
        System.out.println("插入成功");
    }
    //查询 日平均菜价
    public static void selectPavg(String tableName) throws SQLException {
        String sql = "select name,if(count(name) >2,round((sum(price)-max(price)-min(price))/(count(name)-2),2),round((sum(price))/(count(name)),2))as pavg from t_farm_day01 where province='山西' group by name order by pavg desc";
        ps = conn.prepareStatement(sql);
//        Statement stat = conn.createStatement();
//        rs = stat.executeQuery(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.print(rs.getString(1)+" ");
            System.out.println(rs.getString(2));
        }
        ps.close();
    }

    //main
    public static void main(String[] args) throws SQLException {
        //createTable("test111");
        insertInfo("test111");
        //selectPavg("t_farm_day01");
    }


}
