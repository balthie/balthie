package org.balthie.demo.my.json.jackson2.complex.dataentity;

public class DeviceStatus
{
    private Long time;
    
    private Float voltage;
    
    private Integer restVoltage;
    
    private Boolean bReset;
    
    public DeviceStatus()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public DeviceStatus(Long time, Float voltage, Integer restVoltage, Boolean bReset)
    {
        super();
        this.time = time;
        this.voltage = voltage;
        this.restVoltage = restVoltage;
        this.bReset = bReset;
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
     * @return the voltage
     */
    public Float getVoltage()
    {
        return voltage;
    }

    /**
     * @param voltage the voltage to set
     */
    public void setVoltage(Float voltage)
    {
        this.voltage = voltage;
    }

    /**
     * @return the restVoltage
     */
    public Integer getRestVoltage()
    {
        return restVoltage;
    }

    /**
     * @param restVoltage the restVoltage to set
     */
    public void setRestVoltage(Integer restVoltage)
    {
        this.restVoltage = restVoltage;
    }

    /**
     * @return the bReset
     */
    public Boolean getbReset()
    {
        return bReset;
    }

    /**
     * @param bReset the bReset to set
     */
    public void setbReset(Boolean bReset)
    {
        this.bReset = bReset;
    }
    
}
