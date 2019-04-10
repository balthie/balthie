package org.balthie.springmvc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        System.out.println(String.format(" ContextRefreshedEvent listen event [%s] at [%d]", event.getSource(),
                System.currentTimeMillis()));
    }
}
