package org.balthie.springmvc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomizedEventListener implements ApplicationListener<CustomizedEvent>
{
    @Override
    public void onApplicationEvent(CustomizedEvent event)
    {
        System.out.println(String.format(" CustomizedEventListener listen event [%s] at [%d]", event.getSource(), System.currentTimeMillis()));
    }
}
