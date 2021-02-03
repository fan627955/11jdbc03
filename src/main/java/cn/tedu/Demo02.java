package cn.tedu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        //创建读取配置文件的对象
        Properties p=new Properties();
        //得到文件输入流,通过类加载器获取文件的输入流 这种方式
        //会自动查找resources目录下的资源文件并返回输入流
        InputStream ips=Demo02.class.getClassLoader().getResourceAsStream("my.properties");
        //让文件和读取配置文件的对象建立关系 异常抛出
        p.load(ips);
        //开启读取配置文件中的数据
        String name=p.getProperty("name");
        //从配置文件中只能读取到字符串类型
        String age=p.getProperty("age");
        System.out.println(name+":"+age);

    }
}
