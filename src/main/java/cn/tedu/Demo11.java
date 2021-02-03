package cn.tedu;

import java.sql.*;

public class Demo11 {
    public static void main(String[] args) {
        //获取连接
        try(Connection conn=DBUtils.getConn()){
           //得到数据库元数据对象
            DatabaseMetaData dmd=conn.getMetaData();
            System.out.println("数据库名称:"+dmd.getDatabaseProductName());
            System.out.println("数据库连接地址:"+dmd.getURL());
            System.out.println("数据库驱动名:"+dmd.getDriverName());
            System.out.println("数据库驱动版本:"+dmd.getDriverVersion());

            String sql="select*from emp";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            //获取表的元数据对象
            ResultSetMetaData rsmd=rs.getMetaData();
            //获取表字段数量
            int count=rsmd.getColumnCount();
            //遍历每一个字段的信息
            for(int i=0;i<count;i++){
                String name=rsmd.getColumnName(i+1);
                String type=rsmd.getColumnTypeName(i+1);
                System.out.println(name+":"+type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
