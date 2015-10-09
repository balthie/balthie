package org.balthie.demo.my.json.jackson2.complex.dataentity;

public class Location
{
    private Long time;
    
    private Byte type;
    
    private Float latitude;
    
    private Float longtitude;
    
    private Float speed;
    
    private Float direction;
    
    public Location()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Location(Long time, Byte type, Float latitude, Float longtitude, Float speed, Float direction)
    {
        super();
        this.time = time;
        this.type = type;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * @return the time
     */
    public Long getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time)
    {
        this.time = time;
    }

    /**
     * @return the type
     */
    public Byte getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Byte type)
    {
        this.type = type;
    }

    /**
     * @return the latitude
     */
    public Float getLatitude()
    {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Float latitude)
    {
        this.latitude = latitude;
    }

    /**
     * @return the longtitude
     */
    public Float getLongtitude()
    {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(Float longtitude)
    {
        this.longtitude = longtitude;
    }

    /**
     * @return the speed
     */
    public Float getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Float speed)
    {
        this.speed = speed;
    }

    /**
     * @return the direction
     */
    public Float getDirection()
    {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Float direction)
    {
        this.direction = direction;
    }

}
