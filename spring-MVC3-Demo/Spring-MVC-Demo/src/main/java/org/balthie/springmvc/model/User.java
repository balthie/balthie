package org.balthie.springmvc.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * A default user class
 */
public class User
{
    private String id;
    
    private String email;
    
    private String name;
    
    private Date createdDate;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    //spring json格式化注解
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }
    
}
