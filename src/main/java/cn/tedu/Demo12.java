package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo12 {
    public static void main(String[] args) {
        System.out.println("请输入球队名称:");
        String teamName=new Scanner(System.in).nextLine();
        System.out.println("请输入球员名称");
        String playerName=new Scanner(System.in).nextLine();

        try(Connection conn=DBUtils.getConn()){
            //查询球队是否存在
            PreparedStatement ps=conn.prepareStatement("select id from team where name=?;");
            ps.setString(1,teamName);
            ResultSet rs=ps.executeQuery();
            int teamId=0;
            if(rs.next()){//查询到了球队
                //取出查询到的id
                teamId=rs.getInt(1);
            }else{//没有查询到
                //创建执行SQL语句创建对象，并且设置需要获取的自增主键值
                PreparedStatement tps=conn.prepareStatement("insert into team values (null ,?)", Statement.RETURN_GENERATED_KEYS);
                tps.setString(1,teamName);
                tps.executeUpdate();
                //获取自增主键值
                ResultSet trs=tps.getGeneratedKeys();
                trs.next();
                teamId=trs.getInt(1);
            }
            //把得到的球对ID和用户输入的球员名保存到player中
            PreparedStatement pps=conn.prepareStatement("insert into player values (null,?,?)");
            pps.setString(1,playerName);
            pps.setInt(2,teamId);
            pps.executeUpdate();
            System.out.println("添加完成!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
