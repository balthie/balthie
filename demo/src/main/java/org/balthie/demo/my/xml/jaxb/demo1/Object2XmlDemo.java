package org.balthie.demo.my.xml.jaxb.demo1;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

//Marshaller  http://blog.csdn.net/a9529lty/article/details/7211725
public class Object2XmlDemo
{
    public static void main(String[] args)
    {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("mkyong");
        customer.setAge(29);
        
        try
        {
            File file = new File("d:\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(customer, System.out);
            
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        
    }
}
