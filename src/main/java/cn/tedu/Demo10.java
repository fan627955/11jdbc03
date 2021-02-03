package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

public class Demo10 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入球队名称");
        String teamName=sc.nextLine();
        System.out.println("请输入球员名称");
        String playerName=sc.nextLine();
        try(Connection conn=DBUtils.getConn()){
           //1.  把球队名保存到球队表中  最后得到自增的主键值为球队的id
            String tsql="insert into team values(null,?)";
            PreparedStatement tps=conn.prepareStatement(tsql,Statement.RETURN_GENERATED_KEYS);
            tps.setString(1,teamName);
            tps.executeUpdate();
            //获取自增主键值
            ResultSet trs=tps.getGeneratedKeys();
            trs.next();
            int teamid=trs.getInt(1);
           //2.  把用户输入的球员姓名和第一步得到的球队id保存到球员表中
           String psql="insert into player values(null,?,?)";
           PreparedStatement pps=conn.prepareStatement(psql);
           pps.setString(1,playerName);
           pps.setInt(2,teamid);
           pps.executeUpdate();
            System.out.println("添加完成!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
