package org.balthie.demo.my.json.jackson2.complex.dataentity;

import java.util.List;

public class Envelop
{
    private Location location;
    
    private DeviceStatus deviceStatus;
    
    private List<StepAnalyze> stepList;

    /**
     * @return the location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * @return the deviceStatus
     */
    public DeviceStatus getDeviceStatus()
    {
        return deviceStatus;
    }

    /**
     * @param deviceStatus the deviceStatus to set
     */
    public void setDeviceStatus(DeviceStatus deviceStatus)
    {
        this.deviceStatus = deviceStatus;
    }

    /**
     * @return the stepList
     */
    public List<StepAnalyze> getStepList()
    {
        return stepList;
    }

    /**
     * @param stepList the stepList to set
     */
    public void setStepList(List<StepAnalyze> stepList)
    {
        this.stepList = stepList;
    }

}
