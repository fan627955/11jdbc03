package cn.tedu;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.nextLine();
        System.out.println("请输入密码");
        String password=sc.nextLine();
        //获取连接
        try(Connection conn=DBUtils.getConn()) {
            Statement s=conn.createStatement();
            String sql="insert into user values(null,'"+username+"','"+password+"')";
            System.out.println(sql);
            //执行插入SQL语句
            s.executeUpdate(sql);
            System.out.println("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
