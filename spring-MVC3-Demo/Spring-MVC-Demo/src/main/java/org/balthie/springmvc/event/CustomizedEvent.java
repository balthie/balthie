package org.balthie.springmvc.event;

import org.springframework.context.ApplicationEvent;

public class CustomizedEvent extends ApplicationEvent
{
    private static final long serialVersionUID = 1L;

    public CustomizedEvent(String eventName)
    {
        super(eventName);
    }
    
}
