package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Demo07 {
    public static void main(String[] args) {
        //通过批量操作往user表中插入100条数据
        try(Connection conn=DBUtils.getConn()){
            String sql="insert into user values(null,?,?)";
            //创建预编译的SQL执行对象
            PreparedStatement ps=conn.prepareStatement(sql);
            for(int i=1;i<=100;i++){
               ps.setString(1,"名字"+1);
               ps.setString(2,"密码"+1);
               ps.addBatch();//添加到批量操作
               //为了避免内存溢出  每20次批量执行一次
               if(i%20==0){
                   ps.executeBatch();//执行批量操作
               }
            }
            ps.executeBatch();//执行批量操作
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
