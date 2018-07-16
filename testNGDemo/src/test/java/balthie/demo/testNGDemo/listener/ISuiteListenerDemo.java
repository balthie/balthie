package balthie.demo.testNGDemo.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ISuiteListenerDemo implements ISuiteListener
{
    
    @Override
    public void onStart(ISuite suite)
    {
        System.out.println(String.format("ISuiteListenerDemo onStart [%s]", suite.getName()));
    }
    
    @Override
    public void onFinish(ISuite suite)
    {
        System.out.println(String.format("ISuiteListenerDemo onFinish [%s]", suite.getName()));
    }
    
}
