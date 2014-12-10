package org.balthie.demo.my.json.jackson2;

import java.util.Date;

public class Data
{
	String id;
	String name;
	Date date;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Data(String id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	public Data()
	{
		super();
	}
    /**
     * @param string
     * @param string2
     * @param date2
     */
    public Data(String id, String name, Date date)
    {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
}
