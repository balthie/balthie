package balthie.demo.mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;

public class Demo1
{
    public static void main(String[] args)
    {
        verifyInteractions();
        stubMethodCall();
    }
    
    private static void verifyInteractions()
    {
        // mock creation
        List mockedList = mock(List.class);
        
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();
        
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
    
    public static void stubMethodCall()
    {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        
        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");
        
        // the following prints "first"
        System.out.println(mockedList.get(0));
        
        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        
        // stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");
        // stubbing using custom matcher (let's say isValid() returns your own matcher
        // implementation):
        when(mockedList.contains(argThat(isValid()))).thenReturn(true);
        // following prints "element"
        System.out.println(mockedList.get(999));
        // you can also verify using an argument matcher
        verify(mockedList).get(anyInt());
        // argument matchers can also be written as Java 8 Lambdas
        // verify(mockedList).add(someString -> someString.length() > 5);
    }
    
    private static ArgumentMatcher<Integer> isValid()
    {
        ArgumentMatcher<Integer> m = new ArgumentMatcher() {
            @Override
            public boolean matches(Object argument)
            {
                // TODO Auto-generated method stub
                return false;
            }
            
        };
        return m;
    }
}
