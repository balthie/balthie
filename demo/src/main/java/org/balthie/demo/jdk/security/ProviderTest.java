/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����11:39:17
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����11:39:17
 * @description     ��ȫ�����ṩ����Ϣ
 */
public class ProviderTest
{
    public static void main(String[] args)
    {
        /**
         * Provider����ȫ�ṩ�ߣ��ṩ�㷨�����룬��ʽ �ȼ��ܹ��ܵ�ʵ��
         * �磺SUNJCE �� BouncyCastleProvider �� 
         * jdk�е����� jre/lib/security/java.security
         */
        for(Provider p : Security.getProviders())
        {
            System.out.println(p);
            
            // print service Algorithm
            System.out.println(" -------------------------------------print service Algorithm begin---------------------------------------------");
            for(Provider.Service ser :  p.getServices())
            {
                System.out.println(ser.getAlgorithm());
            }
            System.out.println(" -------------------------------------print service Algorithm end ----------------------------------------------\r\n");
            
            for(Map.Entry<Object, Object>  entry : p.entrySet())
            {
                System.out.println("\t|-"+entry.getKey());
            }
        }
    }
}
