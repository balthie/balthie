package org.balthie.demo.my.other.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MemeryUtils
{
    public static void main(String[] args) throws IOException
    {
        Date d = new Date();
        System.out.println(size(1));
    }
    
    /**
     * 计算一个对象所占字节数
     * 
     * @param o对象
     *            ，该对象必须继承Serializable接口即可序列化
     * @return
     * @throws IOException
     */
    public static int size(final Object o) throws IOException
    {
        if(o == null)
        {
            return 0;
        }
        ByteArrayOutputStream buf = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(o);
        out.flush();
        buf.close();
        
        return buf.size();
    }
    
    /**
     * 赋值对象，返回对象的引用，如果参数o为符合对象，则内部每一个对象必须可序列化
     * 
     * @param o对象
     *            ，该对象必须继承Serializable接口即可序列化
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object copy(final Object o) throws IOException, ClassNotFoundException
    {
        if(o == null)
        {
            return null;
        }
        
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(4096);
        ObjectOutput out = new ObjectOutputStream(outbuf);
        out.writeObject(o);
        out.flush();
        outbuf.close();
        
        ByteArrayInputStream inbuf = new ByteArrayInputStream(outbuf.toByteArray());
        ObjectInput in = new ObjectInputStream(inbuf);
        return in.readObject();
    }
}
