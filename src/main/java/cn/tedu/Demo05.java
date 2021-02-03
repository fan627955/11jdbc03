package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.nextLine();
        System.out.println("请输入密码");
        String password=sc.nextLine();
        //获取连接
        try(Connection conn=DBUtils.getConn()) {
//            Statement s=conn.createStatement();
//            String sql="select id from user where username='"+username+"' and password='"+password+"'";
//            System.out.println(sql);
//            //执行查询的SQL语句
//            ResultSet rs=s.executeQuery(sql);
            //*************预编译解决SQL注入*******************
            String sql="select id from user where username=? and password=?";
            //创建预编译的SQL执行对象
            //创建SQL执行对象时对SQL语句进行了编译，此时将SQL语句的业务锁死
            //这样就不会被用户输入的内容所影响，从而解决了SQL注入问题
            PreparedStatement ps=conn.prepareStatement(sql);
            //替换掉sql语句中的?内容
            ps.setString(1,username);
            ps.setString(2,password);
            //执行SQL语句
            ResultSet rs=ps.executeQuery();//切记不能再写SQL语句
            //判断是否查询到了内容 如果返回值为true说明查询到了
            if(rs.next()){
                System.out.println("登录成功!");
            }else{
                System.out.println("登录失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
