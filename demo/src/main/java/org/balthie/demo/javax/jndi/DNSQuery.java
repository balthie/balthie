/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月5日 上午11:11:17
 * @description TODO 一句话描述
 */
package org.balthie.demo.javax.jndi;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @description 使用JNDI API来获得某个域的DNS信息，并从中提取出域的一台SMTP服务器的名称
 */
public class DNSQuery
{
    public static void main(String[] args) throws NamingException
    {
        /*
         * 第一个参数指定要查询的域或主机名，第二个参数指定查询的DNS服务器, 为了程序的简单易读性，省略了严格的参数错误检查
         */
        /*
         * String domain = args[0]; String dnsServer = args.length < 2 ? "" : ("//" + args[1]);
         */
        
        // 目标域名
        String domain = "biostime.com";
        // 北京市 联通DNS服务器
        String dnsServer = "//202.106.46.151";
        
        // 合生元 DNS服务器，如果计算机只能通过代理服务器连接到Internet，那么在该计算机上直接执行如下命令：
        // java  DNSQuery  sina.com 
        // 这也将导致图6.14中的错误。如果要想在通过代理服务器上网的情况下，正确执行上面的程序，可以采用如下命令：
        /*java -DsocksProxyHost=162.105.1.200 -DsocksProxyPort=808 
                        DNSQuery  sina.com  202.106.46.151*/
        // String dnsServer = "//192.168.8.6";
        
        // 通过环境属性来指定Context的工厂类
        Hashtable env = new Hashtable();
        // 指定 目录的实现类, 此处设置为JDK rt.jar中 默认提供的DNS服务
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        // 指定 提供服务的 url地址
        // 这个参数是可选的，为查询时所使用的DNS服务器的IP地址，如果没有指定第二个参数，DNS的JNDI服务程序将使用底层操作系统上设置的DNS服务器。
        env.put(Context.PROVIDER_URL, "dns:" + dnsServer);
        DirContext ctx = new InitialDirContext(env);
        // 分别获取包含所有属性和只包含Mx属性的Attributes对象
        Attributes attrsAll = ctx.getAttributes(domain);
        Attributes attrsMx = ctx.getAttributes(domain, new String[] { "MX" });
        
        /*
         * 上面的整段程序代码也可以用下面这段程序代码来替代，下面这段程序 代码通过查询URL中的Schema信息来自动选择Context的工厂类
         */
        // DirContext ctx = new InitialDirContext();
        // Attributes attrsAll = ctx.getAttributes("dns:" + dnsServer + "/" + domain);
        // Attributes attrsMx = ctx.getAttributes("dns:" + dnsServer + "/" + domain, new String[] {
        // "MX" });
        
        System.out.println("打印出域" + domain + "的Attributes对象中的信息：");
        System.out.println(attrsAll);
        System.out.println("--------------------------");
        System.out.println("打印只检索域" + domain + "的MX记录的Attributes对象：");
        System.out.println(attrsMx);
        
        System.out.println("--------------------------");
        System.out.println("逐一打印出Attributes对象中的各个属性：");
        NamingEnumeration attributes = attrsAll.getAll();
        while (attributes.hasMore())
        {
            System.out.println(attributes.next());
        }
        
        System.out.println("--------------------------");
        // 直接调用get方法从attrsMx集合检索MX属性
        System.out.println("直接检索Attributes对象中的MX属性：");
        Attribute attrMx = attrsAll.get("MX");
        System.out.println(attrMx);
        
        System.out.println("--------------------------");
        // 获取Mx属性中的第一个值:
        System.out.println("获取Mx属性中的第一个值:");
        String recordMx = (String) attrMx.get();
        System.out.println(recordMx);
        // 从Mx属性的第一个值中提取邮件服务器地址
        System.out.println("从MX属性值中提取的邮件服务器地址：");
        String smtpServer = recordMx.substring(recordMx.indexOf(" ") + 1);
        System.out.println(smtpServer);
    }
}
