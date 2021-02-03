package cn.tedu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo09 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入插入的用户名");
        String username=sc.nextLine();
        System.out.println("请输入插入的密码");
        String password=sc.nextLine();
        //获取连接
        try(Connection conn=DBUtils.getConn()){
            String sql="insert into user values(null,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
            //获取装着自增主键值的结果集
            ResultSet rs=ps.getGeneratedKeys();
            //取出结果集中的主键值
            rs.next();//游标往下移动一格
            //因为结果集中只有一个id 1代表获取第一个数据
            int id=rs.getInt(1);
            System.out.println("添加完成! id="+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
