package com.lcx.hive;

import com.lcx.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HiveFramDemo {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
   //需求1.1统计每个省份的农产品市场总数
    public static void getMarketNum() throws Exception {
        conn = JDBCUtil.getConnection();
        String sql = "select province,count(distinct market) counts\n" +
                "from t_farm_day01\n" +
                "group by province having province <> ''";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.print(rs.getString(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.println(rs.getInt(3));
        }
        conn.close();
    }
    //1.2统计没有农产品市场的省份有哪些
    public static void getNoMarketProvience() throws Exception {
        conn = JDBCUtil.getConnection();
        String sql = "select aa.a from\n" +
                "(select t_province.province as a ,t_farm_day01.province as b,t_farm_day01.market as c \n" +
                "from t_province left join t_farm_day01\n" +
                "on t_province.province = t_farm_day01.province)as aa\n" +
                "where aa.a is not null and aa.b is null";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    //1.3统计山东省售卖蛤蜊的农产品市场占全省农产品市场的比例
    public static void getProportion(){

    }

    public static void main(String[] args) throws Exception {
        //1.1统计每个省份的农产品市场总数
        //getMarketNum();
        //1.2统计没有农产品市场的省份有哪些
        getNoMarketProvience();
    }
}
