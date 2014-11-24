package org.balthie.demo.jdk.security.digest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OutputStreamMessageDigestTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException
    {
        byte[] originalMessage = " like some important message in stream ".getBytes(); 
                
        java.security.MessageDigest digester = MessageDigest.getInstance("MD5");
                
        DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(), digester);
        
        dos.write(originalMessage);
        
        byte[] output = dos.getMessageDigest().digest();
        
        System.out.println(" digestMessage  = " + output);
        
        dos.flush();
        dos.close();
        
    }
}
