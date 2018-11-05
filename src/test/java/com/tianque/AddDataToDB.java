package com.tianque;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description
 * @auther hzh
 * @create 2018-10-26 13:16
 */
public class AddDataToDB {


    @Test
    public void mtTestCase() {
        List<Integer> nums = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums = nums.subList(0, 4);
        for (Integer a : nums) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException, SQLException, ParseException {


        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.100.16:1521/tianque";
        String uid = "cqgrid";
        String pwd = "cqgrid";
        Connection conn = DriverManager.getConnection(url, uid, pwd);


//        String sql = "insert into hzh_test(id,name1,name2,name3,age,age1) VALUES(1,'1','1','1',1,1)";
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//        preparedStatement.executeUpdate();
//
//        conn.commit();
//        preparedStatement.close();
//        conn.close();


        String sql = "insert into hzh_test(id,name1,name2,name3,age,age1) VALUES(?,'1','1','1',1,1)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int recordNum = 0; // 计数器
        int commit_size = 5000;// 每次提交记录数5
        for (int i = 33439; i <= 99999; i++) {
            conn.setAutoCommit(false);//设置数据手动提交，自己管理事务  
            recordNum++; // 计数
            pstmt.setInt(1, i);


            pstmt.addBatch();
            if (recordNum % commit_size == 0) {
                pstmt.executeBatch();
                conn.commit();
                conn.close();
                conn = DriverManager.getConnection(url, uid, pwd);
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(sql);
            }
        }
        if (recordNum % commit_size != 0) {
            pstmt.executeBatch();
            conn.commit();
            System.out.println("提交:" + recordNum);
        }
        pstmt.close();
        conn.close();
        System.out.println("insert success!!!");


    }


}