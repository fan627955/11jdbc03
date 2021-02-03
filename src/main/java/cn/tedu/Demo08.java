package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo08 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入查询的页数");
        int page=sc.nextInt();
        System.out.println("请输入查询的条数");
        int count=sc.nextInt();
        //获取连接
        try(Connection conn=DBUtils.getConn()){
            //查询出台的数据 并在控制台中输出
            String sql="select * from user limit ?,?";
            PreparedStatement ps=conn.prepareStatement(sql);
            //替换掉？
            //跳过的条数=(请求页数-1)*每页条数
            ps.setInt(1,(page-1)*count);
            ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String username=rs.getString(2);
                String password=rs.getString(3);
                System.out.println(id+":"+username+":"+password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
