package org.balthie.demo.javax.net.ssl.demo1;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

/**
 * @author DELL
 *
 */
public class SimpleHandshakeCompletedListener implements HandshakeCompletedListener
{
    
    String identi ;
    
    public SimpleHandshakeCompletedListener(String identi)
    {
        super();
        this.identi = identi;
    }

    @Override
    public void handshakeCompleted(HandshakeCompletedEvent handshakecompletedevent)
    {
        try
        {
            X509Certificate cert = handshakecompletedevent.getPeerCertificateChain()[0];
            String peer = cert.getSubjectDN().getName();
            System.out.println(identi + " : request from "+peer);
        }
        catch (SSLPeerUnverifiedException e)
        {
            System.out.println(identi + " : request unverified ");
        }
        
    }
    
}
