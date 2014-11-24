/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:26:45
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.key;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:26:45
 * @description TODO һ�仰����
 */
public class AlgorithmParameterGeneratorTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException
    {
        AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance("DSA");
        
        apg.init(512);
        
        AlgorithmParameters ap = apg.generateParameters();
        
        byte[] b = ap.getEncoded();
        
        System.out.println(new BigInteger(b));
    }
}
