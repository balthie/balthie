package org.balthie.demo.jdk.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

//盐值应该使用加密的安全伪随机数生成器（ Cryptographically Secure Pseudo-Random Number Generator，CSPRNG ）产生。
//CSPRNG和普通的伪随机数生成器有很大不同，如“ C ”语言的rand()函数。顾名思义， CSPRNG 被设计成用于加密安全，这意味着它能提供高度随机、完全不可预测的随机数。我们不希望盐值能够被预测到，所以必须使用 CSPRNG 。
public class SecureRandomTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException
    {
        // 有以下两种请求 SecureRandom 对象的方法：仅指定算法名称，或者既指定算法名称又指定包提供程序。
        // 如果仅指定算法名称，如下所示：
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        
        // 如果既指定了算法名称又指定了包提供程序，如下所示：
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte bytes[] = new byte[20];
        for(int i = 0; i < 10; i++)
        {
            System.out.println(sr.nextInt());
           
            sr.nextBytes(bytes);
        }
    }
}
