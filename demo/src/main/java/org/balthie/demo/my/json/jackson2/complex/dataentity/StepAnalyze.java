package org.balthie.demo.my.json.jackson2.complex.dataentity;

public class StepAnalyze
{
    private String date;
    
    private Integer begin;
    
    private Integer end;
    
    private Integer step;
    
    public StepAnalyze()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public StepAnalyze(String date, Integer begin, Integer end, Integer step)
    {
        super();
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.step = step;
    }

    /**
     * @return the date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * @return the begin
     */
    public Integer getBegin()
    {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(Integer begin)
    {
        this.begin = begin;
    }

    /**
     * @return the end
     */
    public Integer getEnd()
    {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Integer end)
    {
        this.end = end;
    }

    /**
     * @return the step
     */
    public Integer getStep()
    {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(Integer step)
    {
        this.step = step;
    }
    
}
