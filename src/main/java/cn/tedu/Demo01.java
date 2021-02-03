package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) {
        //获取连接
        try(Connection conn=DBUtils.getConn()){
            //创建执行SQL语句的对象
            Statement s=conn.createStatement();
            //执行查询
            ResultSet rs=s.executeQuery("select ename from  emp");
            //遍历结果集
            while(rs.next()){
                String name = rs.getString(1);
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
